import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

public class QueenAnt extends Boss {
    //このクラスを作るにあたりAntクラスにも変更有　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　　

    //以下蝶のコピペ
    //横移動の基準距離(振幅)
    int distanceBaseX = MyWorld.WIDTH / 2 - 80;
    //縦移動の基準移動距離
    int distanceBaseY = 100;
    //角度用変数
    double angle = 0;
    //状態判断用変数
    int mode = 0;
    //状態切替時の位置記憶変数
    int oldX = 0, oldY = 0;
    //撃つかどうかのフラグ
    boolean shoot = false;
    //撃つモノの画像を格納する変数
    GreenfootImage bulletImg = new GreenfootImage("./images/honey.png");
    GreenfootSound honey = new GreenfootSound("./sounds/honey.mp3");

    //フレームカウンター
    int flameCount = 0;
    int flameCountFor2 = 0;
    int flameCountFor3 = 0;
    //sting用フレームカウンタ
    int flameCountForSting = 0;

    //自機位置保存(2段階目に使用)
    int heroX;
    //ボス左右移動回数(3段階目に使用)
    int bossMove = 0;
    boolean rightMove = false;
    boolean leftMove = false;
    //3段階目ボスの最初の移動
    boolean firstBossMove = true;

    public QueenAnt() {
        HITPOINT = 100;
        hitpoint = HITPOINT;
        giveScore = 1;
        range = 15;
        int[] items = {0, 0, 0};
        this.items = items;
        img = new GreenfootImage("./images/antQ.png");
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

                //状態変数をみて行動を切り替え
                switch (mode) {
                    case 0://登場直後
                        //行動開始点についたら状態切替
                        if (x >= MyWorld.WIDTH / 2 && y >= img.getHeight() + 150) {
                            //角度を直す
                            setRotation(0); 
                            System.out.println("y="+y);
                            //状態更新
                            mode = 4;
                        } else {
                            //行動開始点へ移動
                            setLocation(x + (MyWorld.WIDTH / 2 - oldX) / 50, y + (MyWorld.HEIGHT / 2 + 150 - oldY) / 50);
                        }
                        break;
                    case 1:

                        //体力が2/3を切ったら
                        if (hitpoint <= HITPOINT * 2 / 3) {
                            heroX = Stage.heroX;
                            mode = 3;
                        } //そうでなければ
                        else {

                            //フレームを数えて
                            flameCount++;
                            //25フレームごとに
                            if (flameCount == 20) {
                                //ランダムな位置にアリが来る
                                RandomAntShot();
                                //カウントをリセット
                                flameCount = 0;
                            }

                            //過去位置記憶の更新
                            oldX = x;
                            oldY = y;
                        }
                        break;
                    case 2:
                        // if (!shoot) {
                        //3段階目の左右往復移動
                        if (rightMove) {
                            move(5);
                            bossMove++;
                        } else if (leftMove) {
                            move(-5);
                            bossMove++;
                        }
                        if (bossMove == 60 && firstBossMove == true || bossMove == 120) {
                            if (rightMove) {
                                rightMove = false;
                                leftMove = true;
                                bossMove = 0;
                                firstBossMove = false;
                            } else if (leftMove) {
                                rightMove = true;
                                leftMove = false;
                                bossMove = 0;
                            }
                        }
                        //フレームを数えて
                        flameCount++;
                        //25フレームごとに
                        if (flameCount == 25) {
                            //アリが来る
                            AntShot();
                            //カウントをリセット
                            flameCount = 0;
                        }
                        //自機の上のほうにいるかつ撃ってから
                        if (x <= Stage.heroX + 2 && x >= Stage.heroX - 2 && !shoot) {
                            //卵を撃つ
                            shot();
                            shoot=true;
                        }
                        if(shoot){
                            flameCountFor3++;
                            System.out.println("フレームカウント:"+flameCountFor3);
                            if(flameCountFor3==50){
                                flameCountFor3=0;
                                shoot=false;
                            }
                        }
                        break;
                    case 3:
                        //体力が1/3を切ったら
                        if (hitpoint <= HITPOINT / 3) {
                            rightMove = true;
                            mode = 2;
                        } else//体力が2/3を切っている状態
                        {
                            //フレームを数えて
                            flameCount++;
                            flameCountFor2++;
                            //25フレームごとに
                            if (flameCount == 25) {
                                //アリが来る
                                AntShot();
                                //カウントをリセット
                                flameCount = 0;
                            }
                            //100フレーム
                            if (flameCountFor2 == 100) {
                                heroX = Stage.heroX;
                                flameCountFor2 = 0;
                                System.out.println("a");
                            }

                            //過去位置記憶の更新
                            oldX = x;
                            oldY = y;

                        }
                        break;
                    case 4:
                        if(y>=150){
                        setLocation(x, y - 5);
                        }else{
                            mode=1;
                        }
                        break;
                }

                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                    getWorld().addObject(new ClearStageEffect("Stage2", ((Stage) (getWorld())).timer.millisElapsed()), Stage.WIDTH / 2, Stage.HEIGHT / 2);

                    //消滅
                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;
                }
            } //戦闘が始まっていなければ
            else {
                if (caution.finishCaution) {
                    getWorld().addObject(new DisplayHitpoint("QueenAnt"), 700, Stage.HEIGHT / 2);
                    img.setTransparency(255);
                    startBattle = true;
                }
            }
        }
    }

    public void RandomAntShot() {
        //蟻の行動パターンをランダムで決定　変更点ここから
        int antMode = Greenfoot.getRandomNumber(4);
        Ant Ant = new Ant(antMode, true);
        switch (antMode) {
            case 0:
                getWorld().addObject(Ant, Stage.heroX + Greenfoot.getRandomNumber(160) - 80, 1);
                break;
            case 1:
                getWorld().addObject(Ant, 600, Greenfoot.getRandomNumber(250));
                break;
            case 2:
                getWorld().addObject(Ant, 0, Greenfoot.getRandomNumber(250));
                break;
            case 3:
                //右壁からくるか左壁からくるか
                if (Greenfoot.getRandomNumber(2) == 0) {
                    getWorld().addObject(Ant, 0, Greenfoot.getRandomNumber(250));
                } else {
                    getWorld().addObject(Ant, 0, Greenfoot.getRandomNumber(250));
                }
                break;
            default:
                break;
        }
    }

    public void AntShot() {
        Ant Ant = new Ant(0, true);
        getWorld().addObject(Ant, heroX, 1);
        Ant Ant1 = new Ant(0, true);
        getWorld().addObject(Ant1, heroX - 20, 1);
        Ant Ant2 = new Ant(0, true);
        getWorld().addObject(Ant2, heroX + 20, 1);
    }

    public void shot() {
        EnemyBullet egg = new EnemyBullet(0, -10, bulletImg);
        honey.play();
        getWorld().addObject(egg, x, y);
        egg.moveFlag = true;

    }
}
