
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletForStag here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyBulletForStag extends Bullet {

    boolean isActive;

    public EnemyBulletForStag() {
        isActive = false;
        setImage(this.img);
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            setImage(img);
            if (isTouching(Enemies.me.getClass()) && Cater.notInvincible && isActive) {
                Cater.life--;
                Cater.updateLife = true;
                Cater.notInvincible = false;
            }
        }
    }
}
