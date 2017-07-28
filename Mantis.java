
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

/**
 * Write a description of class Mantis here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Mantis extends Boss {

    /**
     * Act - do whatever the Mantis wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int cnt = 0;
    int dx = 2;     //移動速度
    int mode = 1;   //行動
    Random rand = new Random();
    String img1 = "./images/blade.png";     //カマイタチ（横長)
    String img2 = "./images/blade.png";
    String img22 = "./images/bladeRev.png";    //鎌(縦長)
    String img3 = "./images/cycron.png";     //玉
    GreenfootSound wind = new GreenfootSound("./sounds/wind.mp3");
    GreenfootSound blade1 = new GreenfootSound("./sounds/blade.mp3");
    GreenfootSound blade2 = new GreenfootSound("./sounds/doubleBlade.mp3");



    public Mantis() {
        img = new GreenfootImage("./images/mantis.png");
        img.setTransparency(0);

        setImage(img);
        HITPOINT = 90;
        hitpoint = HITPOINT;
        giveScore = 0;
        int items[] = {0, 0, 0};
        this.items = items;
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            if (startBattle) {

                //位置情報の更新
                statusUpdate();

                if (cnt <= 60) {
                    move(1);
                } else if (cnt <= 80) {
                    //ここまで初期位置への移動
                } else {
                    switch (mode) {
                        case 1:
                            if (cnt % 100 == 0) {
                                blade1.play();
                                shot1();
                            }
                            if (cnt % 400 == 0) {
                                shot2();
                            }
                            if (hitpoint <= 2 * HITPOINT / 3) {
                                mode = 2;
                            }
                            break;
                        case 2:
                            setLocation(getX() + dx, getY());
                            shot1();
                            if (cnt % 400 == 0) {
                                shot2();
                            }
                            if (hitpoint <= HITPOINT / 3) {
                                mode = 3;
                            }
                            break;
                        case 3:
                            if (cnt % 100 == 0) {
                                shot1();
                            }
                            if (dx < 3) {
                                dx = -3;
                            }
                            setLocation(getX() + dx, getY());
                            if (Math.abs(getX() - 300) > 150) {

                                dx *= -1;
                            }
                            if (cnt % 400 == 0) {
                                shot2();
                            }
                            if (cnt % 50 == 0) {
                                wind.play();
                                shot3();
                            }
                            break;
                    }
                }
                cnt++;

                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                    //getWorld().removeObject();
                    //消滅
                    getWorld().addObject(new ClearStageEffect("Stage3", ((Stage) (getWorld())).timer.millisElapsed()), Stage.WIDTH / 2, Stage.HEIGHT / 2);

                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;
                }
            } else if (caution.finishCaution) {
                turnTowards(getX(), getY() + 100);
                getWorld().addObject(new DisplayHitpoint("Mantis"),700, Stage.HEIGHT/2);    
                img.setTransparency(255);
                startBattle = true;
            }
        }
    }

    public void shot1() {
        switch (mode) {
            case 1:
                blade1.play();
                getWorld().addObject(new EnemyBulletForMantis1(img1), 300 + dx * 75, 100);
                dx *= -1;
                break;
            case 2:
                if (Math.abs(getX() - 300) > 150) {
                    int i = 1;
                    if (getX() < 300) {
                        i = -1;
                    }
                    int rnd = rand.nextInt(2);
                    blade1.play();
                    getWorld().addObject(new EnemyBulletForMantis1(img1), 300 + 150 * rnd * i, getY() + 50);
                    dx *= -1;
                }
                break;
            case 3:
                int i = 1;
                if (getX() < 300) {
                    i = -1;
                }
                int rnd = rand.nextInt(2);
                blade1.play();
                getWorld().addObject(new EnemyBulletForMantis1(img1), 300 + 150 * rnd * i, getY() + 50);
                break;
        }
    }

    public void shot2() {
        blade2.play();
        getWorld().addObject(new EnemyBulletForMantis2(img22, 1), getX(), getY() + 30);
        getWorld().addObject(new EnemyBulletForMantis2(img2, -1), getX(), getY() + 30);
    }

    public void shot3() {
        getWorld().addObject(new EnemyBulletForMantis3(Stage.heroX, Stage.heroY, img3), getX(), getY());
    }
}
