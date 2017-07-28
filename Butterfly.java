
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Butterfly extends Boss {

    //横移動の基準距離(振幅)
    int distanceBaseX = MyWorld.WIDTH / 2 - 80;
    //縦移動の基準移動距離
    int distanceBaseY = 100;
    //角度用変数
    double angle = 0;
    //状態判断用変数
    int mode = 0;
    //体力の上限記憶変数
    //final int HITPOINT = 50;
    //状態切替時の位置記憶変数
    int oldX = 0, oldY = 0;
    //撃つかどうかのフラグ
    boolean shoot = false;
    //ストロー引っ込み判定
    boolean finishSting = false;
    //撃つモノの画像を格納する変数
    GreenfootImage bulletImg = new GreenfootImage("./images/powder.png");
    //ストロー？の画像
    GreenfootImage[] strawImg = new GreenfootImage[30];
    //ストロー本体
    EnemyBulletForBut straw;
    //鱗粉射撃音
    static final GreenfootSound shot = new GreenfootSound("./sounds/scales.mp3");
    //ストロー射撃音
    static final GreenfootSound sting = new GreenfootSound("./sounds/straw.mp3");
    //フレームカウンター
    int flameCount = 0;
    //sting用フレームカウンタ
    int flameCountForSting = 0;

    public Butterfly() {
        HITPOINT = 50;
        hitpoint = HITPOINT;
        giveScore = 1;
        range = 15;
        int[] items = {0, 0, 0};
        this.items = items;
        for (int i = 0; i < strawImg.length; i++) {
            strawImg[i] = new GreenfootImage(20 * i + 1, 2);
            strawImg[i].setColor(Color.BLACK);
            strawImg[i].fill();
        }
        img = new GreenfootImage("./images/butterfly.png");
        setImage(img);
    }

    //初期位置の記憶
    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        oldX = getX();
        oldY = getY();
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            if (startBattle) {
                //位置情報の更新
                statusUpdate();

                System.out.println(hitpoint);
                //状態変数をみて行動を切り替え
                switch (mode) {
                    case 0://登場直後
                        //行動開始点についたら状態切替
                        if (x >= MyWorld.WIDTH / 2 && y >= img.getHeight() + 150) {
                            //角度を直す
                            setRotation(0);
                            //状態更新
                            mode = 1;
                        } else {
                            //行動開始点へ移動
                            setLocation(x + (MyWorld.WIDTH / 2 - oldX) / 50, y + (MyWorld.HEIGHT / 2 + 150 - oldY) / 50);
                            //なんとなくまわる
                            turn(10);
                        }
                        break;
                    case 1:
                        //体力が1/3を切ったら
                        if (hitpoint <= HITPOINT / 3) {

                            //初期位置周辺についたら
                            if (x == MyWorld.WIDTH / 2 && y == img.getHeight() + 150) {
                                //角度を直す
                                setRotation(0);
                                //縦方向の振れ幅を1にする
                                distanceBaseY = 1;
                                ///状態更新
                                mode = 2;
                            }
                            //初期位置を目指して移動
                            turnTowards((MyWorld.WIDTH / 2), (img.getHeight() + 150));
                            //なんとなくまわる
                            move(2);
                        } //そうでなければ
                        else {
                            //角度を元に位置を更新
                            setLocation(MyWorld.WIDTH / 2 + (int) (distanceBaseX * Math.sin(angle)), img.getHeight() + 150 + (int) (distanceBaseY * Math.sin(2 * angle)));
                            //角度を10°足す
                            angle += Math.toRadians(1);
                            //angleが360°になっていたら
                            if (angle >= 2 * Math.PI) {
                                //0に直す
                                angle = 0;
                            }
                            //フレームを数えて
                            flameCount++;
                            //100フレームごとに
                            if (flameCount == 100) {
                                //鱗粉を降らす
                                shot();
                                //カウントをリセット
                                flameCount = 0;
                            }

                            //過去位置記憶の更新
                            oldX = x;
                            oldY = y;
                        }
                        break;
                    case 2:
                        if (!shoot) {
                            //角度を元に位置を更新
                            setLocation(MyWorld.WIDTH / 2 + (int) (distanceBaseX * Math.sin(angle)), img.getHeight() + 150 + (int) (distanceBaseY * Math.sin(2 * angle)));
                            //角度を10°足す
                            angle += Math.toRadians(1);
                            //angleが360°になっていたら
                            if (angle >= 2 * Math.PI) {
                                //0に直す
                                angle = 0;
                            }
                            //フレームを数えて
                            flameCount++;
                            //100フレームごとに
                            if (flameCount == 100) {
                                //鱗粉を降らす
                                shot();
                                //カウントをリセット
                                flameCount = 0;
                            }
                            if (x <= Stage.heroX + 2 && x >= Stage.heroX - 2 && !shoot) {
                                shoot = true;
                            }
                        } else {
                            sting();
                            if (finishSting) {
                                flameCountForSting = 0;
                                shoot = false;
                                finishSting = false;
                            }
                        }
                }

                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);

                    //消滅
                    getWorld().addObject(new ClearStageEffect("Stage1", ((Stage) (getWorld())).timer.millisElapsed()), Stage.WIDTH / 2, Stage.HEIGHT / 2);

                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;

                }
            } //戦闘が始まっていなければ
            else {
                if (caution.finishCaution) {
                    getWorld().addObject(new DisplayHitpoint("Butterfly"), 700, Stage.HEIGHT / 2);
                    img.setTransparency(255);
                    startBattle = true;
                }
            }
            if (Greenfoot.isKeyDown("e")) {
                mode = 2;
            }
        }
    }

    @Override
    public void shot() {
        EnemyBullet jel = new EnemyBullet(0, -1, bulletImg);
        shot.play();
        getWorld().addObject(jel, x, y);
        jel.moveFlag = true;
    }

    public void sting() {
        if (flameCountForSting == 0) {
            straw = new EnemyBulletForBut(0, 0, strawImg[0]);
            sting.play();
            getWorld().addObject(straw, x, y + strawImg[0].getWidth() / 2);

            flameCountForSting++;
        } else if (flameCountForSting % 2 == 0) {
            if (flameCountForSting / 2 < strawImg.length - 1) {
                getWorld().removeObject(straw);
                straw.img = strawImg[flameCountForSting / 2];
                getWorld().addObject(straw, x, y + strawImg[flameCountForSting / 2].getWidth() / 2);

            } else if (flameCountForSting / 2 <= 2 * strawImg.length - 2) {
                getWorld().removeObject(straw);
                straw.img = strawImg[(2 * strawImg.length - 2) - flameCountForSting / 2];
                getWorld().addObject(straw, x, y + strawImg[(2 * strawImg.length - 2) - flameCountForSting / 2].getWidth() / 2);

            } else {
                getWorld().removeObject(straw);
                finishSting = true;
            }
        }
        flameCountForSting++;

    }
}
