
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBulletForBut here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyBulletForBut extends Bullet {

    public EnemyBulletForBut(int speedX, int speedY, GreenfootImage img) {
        bullet_speedX = speedX;
        bullet_speedY = speedY;
        this.img = img;
        setImage(this.img);
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            setImage(img);
            //setLocation(getX() - bullet_speedX, getY() - bullet_speedY);
            if (isTouching(Enemies.me.getClass()) && Cater.notInvincible) {
                Cater.life--;
                Cater.updateLife = true;
                Cater.notInvincible = false;
            }
        }
    }
}
