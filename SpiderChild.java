
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class SpiderChild extends Enemies {

    boolean startUp = true;
    boolean shoot = false;
    int dx = 1;
    int shotAtX = 0;
    //List list = new ArrayList(); 
    final int speed = 3;
    int webSpeed = 3;
    GreenfootImage bulletImg = new GreenfootImage("./images/web.png");
    Bullet web;// = new Bullet(speed, speed, img)

    public SpiderChild() {
        img = new GreenfootImage("./images/spider.png");
        setImage(img);
        hitpoint = 5;
        giveScore = 0;
        range = 20;
        int[] items = {0, 0, 0};
        this.items = items;
        shotAtX = Stage.heroX;
    }

    public SpiderChild(int x) {
        img = new GreenfootImage("./images/spider.png");
        setImage(img);
        hitpoint = 100;
        giveScore = 0;
        int[] items = {0, 0, 0};
        this.items = items;
        shotAtX = x;
    }

    //壁際から出現が前提
    public void act() {
        if (Stage.inPause) {
        } else {
            statusUpdate();
            if (startUp) {
                if (x <= MyWorld.WIDTH / 2) {

                } else if (x >= MyWorld.WIDTH / 2) {
                    dx *= -1;
                }
                startUp = false;
            }

            if (x == shotAtX) {
                shoot = true;
                shot();
            }
            
//            if (x == Stage.heroX) {
//                shoot = true;
//                shot();
//            }
            
            
            if (!shoot) {
                setLocation(x + dx, y);
            }

            hitStatusCheck(img);

            //ここ当たり判定
            if (hitpoint <= 0) {
                putItem(items, giveScore);
                if (!bye.isPlaying()) {
                    bye.play();
                }
                getWorld().removeObject(this);
            } else if (isAtEdge() || getX() >= 598) {
                getWorld().removeObject(this);
            }
        }
    }

    @Override
    public void shot() {
        if (shoot) {
            if (getY() < Stage.heroY) {
                webSpeed = -3;
            } else {
                webSpeed = 3;
            }
            web = new EnemyBulletForSpi(0, webSpeed, bulletImg);
            getWorld().addObject(web, x, y);
            web.moveFlag = true;
            shoot = false;
        }
    }
}
