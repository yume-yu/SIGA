
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import static java.lang.Math.abs;

/**
 * Write a description of class EnemyBulletForBee here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyBulletForBee extends Bullet {

    int x, y;               //heroの位置
    boolean flag = true;    //軌道修正

    public EnemyBulletForBee(int x, int y, GreenfootImage img) {
        setImage(img);
        this.x = x;     //heroの位置
        this.y = y;     //
    }

    @Override
    protected void addedToWorld(World world) {
        turnTowards(x, y);
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            double distance = Math.sqrt(abs((x - getX())) * (x - getX()) + (y - getY()) * (y - getY()));
            if (distance > 30 && flag) {
                turnTowards(x, y);
            } else {
                flag = false;
            }
            move(5);
            if (isTouching(Enemies.me.getClass()) && Cater.notInvincible) {
                
                Cater.life--;
                Cater.updateLife = true;
                Cater.notInvincible = false;
                getWorld().removeObject(this);
                moveFlag = false;
            } else if ((isAtEdge() || getX() >= 599)) {
                getWorld().removeObject(this);
                moveFlag = false;
            }
        }
    }
}
