
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Beatle here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Beatle extends Boss {

    int mode = 0;
    GreenfootImage beamImg = new GreenfootImage(600, 20);
    EnemyBulletForStag beams = new EnemyBulletForStag();
    EnemyBullet stones[] = new EnemyBullet[6];
    GreenfootImage stone = new GreenfootImage("./images/stone.png");
    GreenfootSound gaurd;
    GreenfootSound faint;

    //ビーム音
    GreenfootSound beamSound = new GreenfootSound("./sounds/beam.mp3");
    GreenfootSound beamWaitSound = new GreenfootSound("./sounds/beamwait.mp3");

    //攻撃モードパターン遷移
    int modePattern[] = {3, 2, 3, 1, 3, 1, 3, 2};
    int flameCount = 0;
    int countModeChange = 0;
    boolean readyBeam = true;
    int countRotation = 0;
    boolean waving = false;
    int oldHanuke = 3;

    public Beatle() {
        HITPOINT = 800;
        hitpoint = HITPOINT;
        giveScore = 1;
        range = 15;
        int[] Ritems = {0, 0, 0};
        this.items = items;
        mode = 0;
        img = new GreenfootImage("./images/beatle.png");
        setImage(img);
        beamImg.setColor(new Color(92, 244, 66));
        beamImg.fill();
        beamImg.setTransparency(45);
    }

    @Override
    //原理的に画像ファイルの縦横があれば最適解が弾けるので、画像を引数にした版
    public void hitStatusCheck(GreenfootImage img) {
        //int range = 0;
        if (img.getWidth() - img.getHeight() >= 0) {
            range = img.getWidth() / 2;
        } else {
            range = img.getHeight() / 2;
        }

        list = getObjectsInRange(range, Stage.bullets[0].getClass());

        //listの中身の数分forをまわして、それぞれのクラスが｢自機の弾｣｣かを判断する
        for (int i = 0; i < list.size(); i++) {
            //取得したクラスが｢自分に当たっているクラス｣かを判断
            if (isTouching(list.get(i).getClass())) {
                //もし当たっているクラスが｢Bulletクラス = 自機の弾｣なら
                if (list.get(i).toString().matches("Bullet@.*")) {
                    gaurd = new GreenfootSound("./sounds/guard.mp3");
                    gaurd.play();
                    //当たった弾の判別のため、bulletsの要素数でforを回す
                    for (int j = 0; j < Stage.bullets.length; j++) {
                        //当たったオブジェクトの名前が一致する弾を探す
                        if (list.get(i).toString().equals(Stage.bulletsClassName[j])) {
                            //見つけたら該当の弾の使用中フラグを更新
                            Stage.bullets[j].moveFlag = false;
                            //該当の弾を除去
                            getWorld().removeObject(Stage.bullets[j]);
                            break;
                        }
                    }
                }
            }
        }

        list = getObjectsInRange(range, me.getClass());

        //listの中身の数分forをまわして、｢自機本体｣かを判断する
        for (int i = 0; i < list.size(); i++) {
            //取得したクラスが｢自分に当たっているクラス｣かを判断
            if (isTouching(list.get(i).getClass())) {

                //もし当たっているクラスが｢Caterクラス = 自機｣なら
                if (list.get(i).toString().matches("Cater@.*") && Cater.notInvincible) {
                    //自機の体力or残機を減らす処理がここに来る
                    Cater.life--;
                    Cater.updateLife = true;
                    Cater.notInvincible = false;
                }
            }
        }

    }

    public void act() {
        if (Stage.inPause) {
            if (beamSound.isPlaying()) {
                beamSound.pause();
            }
        } else {
            if (startBattle) {
                //位置情報の更新
                statusUpdate();

                //System.out.println(hitpoint);
                //状態変数をみて行動を切り替え
                switch (mode) {
                    //登場時の初期位置(真ん中)への移動
                    case 0:
                        //真ん中を向く
                        turnTowards(Stage.WIDTH / 2, Stage.HEIGHT / 2);
                        //ちょっと動く
                        move(1);
                        //真ん中に来たら
                        if (getX() == Stage.WIDTH / 2 && getY() == Stage.HEIGHT / 2) {
                            //モード切り替え
                            mode = -1;
                            setRotation(90);
                        }
                        break;
                    //反時計回り旋回ビーム
                    case 1:
                        //モード切り替え直後に
                        if (flameCount == 0) {
                            //ビーム画像の設定
                            beams.img = beamImg;
                            //ビーム発射
                            beams = new EnemyBulletForStag();
                            beams.img = beamImg;
                            beams.img.setTransparency(45);
                            getWorld().addObject(beams, x, y + beams.img.getWidth() / 2);
                            faint = new GreenfootSound("./sounds/faint.mp3");
                            //もし予告じゃなければ
                            if (!readyBeam) {
                                //ビームの当たり判定をtrueに
                                beams.isActive = true;
                                beams.img.setTransparency(255);
                            }
                        }
                        //フレームを数える
                        flameCount++;
                        //予告段階なら
                        if (readyBeam) {
                            //100フレームの待機の後
                            if (flameCount >= 100 /*&& flameCount % 2 == 0*/) {
                                if (flameCount == 100) {
                                    faint.play();
                                }
                                //カブトムシをビームの向き-10°に向ける
                                setRotation(-10 * (flameCount - 100));
                                //この時向いた角度が90°だったら
                                if (getRotation() == 90) {
                                    //周回をカウント
                                    countRotation++;
                                    //1周回ってたら
                                    if (countRotation == 1) {
                                        //フレームカウントをリセット
                                        flameCount = 0;
                                        //周回カウントをリセット
                                        countRotation = 0;
                                        //予告を終わる
                                        readyBeam = false;
                                        //ビームを消失
                                        getWorld().removeObject(beams);
                                    } //1周目じゃなければ
                                    else {
                                        //ビームの座標を向く角度に合わせて調整
                                        beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                        //カブトムシと同じ角度を向く
                                        beams.setRotation(getRotation());
                                    }
                                } //角度が90°じゃなければ
                                else {
                                    //ビームの座標を向く角度に合わせて調整
                                    beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                    //カブトムシと同じ角度を向く
                                    beams.setRotation(getRotation());
                                }
                                //カブトムシは下を向いてる(余裕)
                                setRotation(90);
                            }
                        } //予告後の攻撃なら
                        else {
                            if (flameCount >= 100 && flameCount % 2 == 0) {

                                beamSound.playLoop();
                                //カブトムシを-2°回す
                                turn(-3);
                                //この時向いた角度が90°だったら
                                if (getRotation() == 90) {
                                    //周回をカウント
                                    countRotation++;
                                    //3周回ってたら
                                    if (countRotation == 3) {
                                        //フレームカウントをリセット
                                        flameCount = 0;
                                        //周回カウントをリセット
                                        countRotation = 0;
                                        //予告フラグをリセット
                                        readyBeam = true;
                                        //音停止
                                        beamSound.stop();
                                        //ビームを消失
                                        getWorld().removeObject(beams);
                                        //体力減る
                                        hitpoint -= 100;
                                        isDamage = true;
                                        //モード周回ビームを終わる
                                        mode = -1;
                                    } //2周目じゃなければ
                                    else {
                                        //ビームの座標を向く角度に合わせて調整
                                        beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                        //カブトムシと同じ角度を向く
                                        beams.setRotation(getRotation());
                                    }
                                } //角度が90°じゃなければ
                                else {
                                    //ビームの座標を向く角度に合わせて調整
                                    beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                    //カブトムシと同じ角度を向く
                                    beams.setRotation(getRotation());
                                }
                            }
                        }
                        break;
                    //時計回り旋回ビーム
                    case 2:
                        //モード切り替え直後に
                        if (flameCount == 0) {
                            //ビーム画像の設定
                            beams.img = beamImg;
                            //ビーム発射
                            beams = new EnemyBulletForStag();
                            beams.img = beamImg;
                            beams.img.setTransparency(45);
                            getWorld().addObject(beams, x, y + beams.img.getWidth() / 2);
                            faint = new GreenfootSound("./sounds/faint.mp3");
                            //もし予告じゃなければ
                            if (!readyBeam) {
                                //ビームの当たり判定をtrueに
                                beams.isActive = true;
                                beams.img.setTransparency(255);
                            }
                        }
                        //フレームを数える
                        flameCount++;
                        //予告段階なら
                        if (readyBeam) {
                            //100フレームの待機の後
                            if (flameCount >= 100 /*&& flameCount % 2 == 0*/) {
                                if (flameCount == 100) {
                                    faint.play();
                                }
                                //カブトムシをビームの向き+10°に向ける
                                setRotation(100 + 10 * (flameCount - 100));
                                //この時向いた角度が90°だったら
                                if (getRotation() == 90) {
                                    //周回をカウント
                                    countRotation++;
                                    //1周回ってたら
                                    if (countRotation == 1) {
                                        //フレームカウントをリセット
                                        flameCount = 0;
                                        //周回カウントをリセット
                                        countRotation = 0;
                                        //予告を終わる
                                        readyBeam = false;
                                        //ビームを消失
                                        getWorld().removeObject(beams);
                                    } //1周目じゃなければ
                                    else {
                                        //ビームの座標を向く角度に合わせて調整
                                        beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                        //カブトムシと同じ角度を向く
                                        beams.setRotation(getRotation());
                                    }
                                } //角度が90°じゃなければ
                                else {
                                    //ビームの座標を向く角度に合わせて調整
                                    beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                    //カブトムシと同じ角度を向く
                                    beams.setRotation(getRotation());
                                }
                                //カブトムシは下を向いてる(余裕)
                                setRotation(90);
                            }
                        } //予告後の攻撃なら
                        else {
                            if (flameCount >= 100 && flameCount % 2 == 0) {
                                beamSound.playLoop();
                                //カブトムシを2°回す
                                turn(3);
                                //この時向いた角度が90°だったら
                                if (getRotation() == 90) {
                                    //周回をカウント
                                    countRotation++;
                                    //3周回ってたら
                                    if (countRotation == 3) {
                                        //フレームカウントをリセット
                                        flameCount = 0;
                                        //周回カウントをリセット
                                        countRotation = 0;
                                        //予告フラグをリセット
                                        readyBeam = true;
                                        //音停止
                                        beamSound.stop();
                                        //ビームを消失
                                        getWorld().removeObject(beams);
                                        //体力減る
                                        hitpoint -= 100;
                                        isDamage = true;
                                        //モード周回ビームを終わる
                                        mode = -1;
                                    } //2周目じゃなければ
                                    else {
                                        //ビームの座標を向く角度に合わせて調整
                                        beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                        //カブトムシと同じ角度を向く
                                        beams.setRotation(getRotation());
                                    }
                                } //角度が90°じゃなければ
                                else {
                                    //ビームの座標を向く角度に合わせて調整
                                    beams.setLocation(getX() + (int) (beams.img.getWidth() / 2 * Math.cos(Math.toRadians(getRotation()))), getY() + (int) (beams.img.getWidth() / 2 * Math.sin(Math.toRadians(getRotation()))));
                                    //カブトムシと同じ角度を向く
                                    beams.setRotation(getRotation());
                                }
                            }
                        }
                        break;
                    case 3:
                        flameCount++;
                        if (flameCount % 110 == 0) {
                            int random;
                            do {
                                random = Greenfoot.getRandomNumber(6);
                            } while (random == oldHanuke);
                            for (int i = 0; i < stones.length; i++) {
                                stones[i] = new EnemyBullet(0, -2, stone);
                                if (i != random) {
                                    getWorld().addObject(stones[i], 50 + stone.getHeight() * i, 1);
                                    stones[i].moveFlag = true;
                                }
                            }
                            oldHanuke = random;
                        }
                        if (flameCount == 1540) {
                            //体力減る
                            hitpoint -= 100;
                            isDamage = true;
                            flameCount = 0;
                            mode = -1;
                        }
                        break;
                    default:
                        flameCount++;

                        if (flameCount == 120) {
                            mode = modePattern[countModeChange];
                            flameCount = 0;
                            countModeChange++;
                        }
                        break;

                }

                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);

                    //消滅
                    getWorld().addObject(new ClearStageEffect("Stage5", ((Stage) (getWorld())).timer.millisElapsed()), Stage.WIDTH / 2, Stage.HEIGHT / 2);

                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;

                }
            } //戦闘が始まっていなければ
            else {
                if (caution.finishCaution) {
                    getWorld().addObject(new DisplayHitpoint("Beatle"), 700, Stage.HEIGHT / 2);
                    img.setTransparency(255);
                    startBattle = true;
                }
            }
        }
    }
}
