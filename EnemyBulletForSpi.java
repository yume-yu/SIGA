
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBulletForSpi extends Bullet {

    //呼び出したのが親クモクラスかを確かめるフラグ
    boolean isMothers = false;
    //フレームカウント開始フラグ
    boolean startCount = false;
    //巣かどうか
    boolean isWebMaterial = false;
    //フレームカウント
    int flameCount = 0;

    public EnemyBulletForSpi(int speedX, int speedY, GreenfootImage img) {
        bullet_speedX = speedX;
        bullet_speedY = speedY;
        this.img = img;
        setImage(this.img);
    }

    public EnemyBulletForSpi(int speedX, int speedY, GreenfootImage img, boolean flag) {
        bullet_speedX = speedX;
        bullet_speedY = speedY;
        this.img = img;
        setImage(this.img);
        isMothers = flag;
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            if (moveFlag) {
                setLocation(getX() - bullet_speedX, getY() - bullet_speedY);
            }
            if (isTouching(Enemies.me.getClass()) && Cater.notInvincible) {
                if(isWebMaterial){
                    Cater.life--;
                    Cater.updateLife = true;
                    Cater.notInvincible = false;
                    MyWorld.usingStage.boss.completeLine();
                    //getWorld().removeObject(this);
                }
                Cater.slowDown = true;
                Cater.slowCount = 0;
                if (!isMothers) {
                    getWorld().removeObject(this);
                }
                
                
                moveFlag = false;
            } else if ((isAtEdge() || getX() >= 599) && !isMothers) {
                getWorld().removeObject(this);
                MyWorld.score += 10;
                moveFlag = false;
            }

            if (isMothers && !startCount) {
                setImage(img);
            } else if (isMothers && startCount) {
                if (startCount && flameCount % 10 == 0 && flameCount >= 100) {
                    if ((255 - 5 * (flameCount - 100) / 10) > 0) {
                        img.setTransparency(255 - 5 * (flameCount - 100) / 10);
                    } else {
                        getWorld().removeObject(this);
                    }
                    img.fill();
                    setImage(img);
                }
                flameCount++;
            }
        }

    }
}
