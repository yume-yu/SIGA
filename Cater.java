
import greenfoot.*;

public class Cater extends Actor {

    static int speed = 4;
    static boolean slowDown = false;
    //残機
    public static int life = 6;
    //残機の画像
    static GreenfootImage lifeImg = new GreenfootImage("./images/lifeImg.png");
    static final DisplayImage LIFE_IMG[] = {new DisplayImage(lifeImg, 0), new DisplayImage(lifeImg, 0), new DisplayImage(lifeImg, 0), new DisplayImage(lifeImg, 0), new DisplayImage(lifeImg, 0), new DisplayImage(lifeImg, 0)};

    //残機表示更新フラグ
    static boolean updateLife = true;
    //無敵フラグ
    static boolean notInvincible = true;
    //現在地記憶
    static int removedX = MyWorld.WIDTH / 2;
    ;
    static int removedY = 3 * MyWorld.HEIGHT / 4;
    static int score = 0;
    static boolean isBeaming = false;
    static int memoryLife;

    //主人公の画像
    GreenfootImage nomal = new GreenfootImage("./images/hero.png");
    //無敵のときの画像
    GreenfootImage invincible = new GreenfootImage("./images/hero_invin.png");
    //ビーム音
    static GreenfootSound beamSound = new GreenfootSound("./sounds/beam.mp3");
    //射撃音
    static GreenfootSound shotSound[] = new GreenfootSound[Stage.bullets.length];
    
    //自機被弾時の音
    static GreenfootSound damage = new GreenfootSound("./sounds/damage.mp3");
    //必殺技オブジェクト
    static BulletEnhanced bulletEnhanced[] = {new BulletEnhanced(),
        new BulletEnhanced(),
        new BulletEnhanced(),
        new BulletEnhanced()};
    //final int shot_delay = 20;                        //　発射間隔
    final int shot_delay = 15;
    static int cnt = 0;
    static int slowCount = 0;
    int flameCount = 0;

    Actor outer;

    public Cater(Actor outer) {
        this.outer = outer;
        setImage("./images/point.png");
        damage = new GreenfootSound("./sounds/damage.mp3");
        for(int i = 0;i < shotSound.length;i++){
            shotSound[i] = new GreenfootSound("./sounds/shot.mp3");
            shotSound[i].setVolume(50);
        }
        //life = 6;
        updateLife = true;
        notInvincible = true;
        isBeaming = false;
    }

    //残機表示更新メソッド
    public void updateLifeCount() {
        switch (memoryLife - life) {
            case -1:
                damage.play();
                break;
            case 1:
                damage.play();
                break;
            default:
                break;

        }
        if (Cater.life >= 0 && Cater.life <= 6 && updateLife) {
            for (int i = 0; i < LIFE_IMG.length; i++) {
                try {
                    getWorld().removeObject(LIFE_IMG[i]);
                } catch (Exception e) {
                }

            }
            if (life != 0) {
                for (int i = 0; i < Cater.life; i++) {
                    switch (i + 1) {
                        case 1:
                        case 2:
                        case 3:
                            getWorld().addObject(LIFE_IMG[i], 620 + lifeImg.getWidth() / 2 + (lifeImg.getWidth() + 5) * i, 700);
                            break;
                        case 4:
                        case 5:
                        case 6:
                            getWorld().addObject(LIFE_IMG[i], 620 + lifeImg.getWidth() / 2 + (lifeImg.getWidth() + 5) * (i - 3), 700 + lifeImg.getHeight() + 5);
                            break;
                    }
                }
            } else {
                Stage.isGameOver = true;
            }
            updateLife = false;
            memoryLife = life;
        }

    }

    //減速処理メソッド
    public void isSlowDown() {
        if (slowDown && notInvincible) {
            if (slowCount == 0) {
                speed = 1;
                slowCount++;
            }
            if (slowCount == 250) {
                slowCount = 0;
                slowDown = false;
                speed = 4;
            } else if (slowDown) {
                slowCount++;
            }
        } else if (slowDown) {
            speed = 4;
            slowCount = 0;
            slowDown = false;
        }
    }

    //無敵処理のメソッド
    public void isInvincible() {
        if (!notInvincible) {
            if (flameCount == 100) {
                flameCount = 0;
                outer.setImage(nomal);
                notInvincible = true;
            } else if (flameCount == 0) {
                System.out.println("hitPoints : " + life);
                outer.setImage(invincible);
                flameCount++;
            } else {
                flameCount++;
                if (flameCount % 3 == 0) {
                    outer.setImage(nomal);
                } else {
                    outer.setImage(invincible);
                }
            }
        }
    }

    //入力を受けるメソッド
    public void checkInpit() {
        if (Greenfoot.isKeyDown("up")) {
            setLocation(getX(), getY() - speed);
            outer.setLocation(this.getX(), this.getY());
            try {
                for (int i = 0; i < bulletEnhanced.length; i++) {
                    bulletEnhanced[i].setLocation(getX(), getY());
                }
            } catch (Exception e) {
            }
        } else if (Greenfoot.isKeyDown("down")) {
            setLocation(getX(), getY() + speed);
            outer.setLocation(this.getX(), this.getY());
            try {
                for (int i = 0; i < bulletEnhanced.length; i++) {
                    bulletEnhanced[i].setLocation(getX(), getY());
                }
            } catch (Exception e) {
            }
        }
        if (Greenfoot.isKeyDown("left")) {
            setLocation(getX() - speed, getY());
            outer.setLocation(this.getX(), this.getY());
            try {
                for (int i = 0; i < bulletEnhanced.length; i++) {
                    bulletEnhanced[i].setLocation(getX(), getY());
                }
            } catch (Exception e) {
            }
        } else if (Greenfoot.isKeyDown("right") && getX() < 600 - speed) {
            setLocation(getX() + speed, getY());
            outer.setLocation(this.getX(), this.getY());
            try {
                for (int i = 0; i < bulletEnhanced.length; i++) {
                    bulletEnhanced[i].setLocation(getX(), getY());
                }
            } catch (Exception e) {
            }
        }

        if (Greenfoot.isKeyDown("z") && cnt <= 0) {
            //bulletsの要素の数だけforを回す
            for (int i = 0; i < Stage.bullets.length; i++) {
                try {
                    int check = Stage.bullets[i].getX();
                } catch (Exception e) {
                    shotSound[i].play();
                    getWorld().addObject(Stage.bullets[i], outer.getX(), outer.getY() - 10);
                    break;
                }
            }

            //フラグとカウンタを更新
            cnt = shot_delay;
        }
        try {
            if (Stage.str.equals("c") && MyWorld.haveItem && !isBeaming) {
                beamSound = new GreenfootSound("./sounds/beam.mp3");
                isBeaming = true;
                beamSound.playLoop();
                for (int i = 0; i < bulletEnhanced.length; i++) {
                    bulletEnhanced[i] = new BulletEnhanced();
                    getWorld().addObject(bulletEnhanced[i], getX(), getY());
                    bulletEnhanced[i].setRotation(i * 45);
                }
                MyWorld.haveItem = false;
                getWorld().removeObject(MyWorld.itemDisplay);
            }
        } catch (Exception e) {
        }

    }

    public void act() {

        if (Stage.inPause) {

        } else {
            Stage.heroX = getX();
            Stage.heroY = getY();

            if (cnt > 0) {
                cnt--;
            }

            isInvincible();

            isSlowDown();

            checkInpit();

            updateLifeCount();
        }
    }

}
