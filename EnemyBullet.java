import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class EnemyBullet here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class EnemyBullet extends Bullet
{
    int score = 10;
    public EnemyBullet(int speedX,int speedY,GreenfootImage img){
        bullet_speedX = speedX;
        bullet_speedY = speedY;
        setImage(img);
    }
    
    public void act(){
        if(Stage.inPause){
        }
        else{
            if (moveFlag) {
                setLocation(getX() - bullet_speedX, getY() - bullet_speedY);
            }
            if(isTouching(Enemies.me.getClass()) && Cater.notInvincible){
                Cater.life--;
                Cater.updateLife = true;
                Cater.notInvincible = false;
                getWorld().removeObject(this);
                moveFlag = false;
            }else if((isAtEdge() || getX() >= 599)){
                getWorld().removeObject(this);
                moveFlag = false;
            }
        }
    }    
}
