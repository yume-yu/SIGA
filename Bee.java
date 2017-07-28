
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bee here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Bee extends Enemies {

    /**
     * Act - do whatever the Bee wants to do. This method is called whenever the
     * 'Act' or 'Run' button gets pressed in the environment.
     */
    int cnt = 600;
    int cntLim = 550;
    int dy = 1;
    GreenfootImage bulletImg = new GreenfootImage("./images/bulletE.png");
    Bullet needleForBee;
    boolean shot_flg = true;
    EnemyBulletForBee bullet;

    public Bee() {
        hitpoint = 8;
        giveScore = 10;
        img = new GreenfootImage("./images/bee.png");
        setImage(img);
        int[] items = {0, 1};
        this.items = items;
        range = 10;
    }

    public Bee(int toShotCount) {
        hitpoint = 8;
        giveScore = 10;
        img = new GreenfootImage("./images/bee.png");
        setImage(img);
        int[] items = {0, 1};
        this.items = items;
        range = 10;
        cntLim = toShotCount;
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            super.act();
            cnt--;
            if (cnt <= cntLim - 100) {
                dy = 3;
                setLocation(x, y + dy);
            } else if (cnt <= cntLim) {
                setLocation(x, y);
                if (cnt <= cntLim - 50 && shot_flg && hitpoint > 0) {
                    shot();
                    shot_flg = false;
                }
            } else {
                setLocation(x, y + dy);
            }
        }
    }

    @Override
    public void shot() {
        bullet = new EnemyBulletForBee(Stage.heroX, Stage.heroY, bulletImg);
        //try {
            getWorld().addObject(bullet, x, y);
//        } catch (Exception e) {
//        }

    }
}
