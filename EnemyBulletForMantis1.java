
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import static java.lang.Math.abs;

/**
 * Write a description of class EnemyBulletForMantis1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyBulletForMantis1 extends Bullet {

    /**
     * Act - do whatever the EnemyBulletForMantis1 wants to do. This method is
     * called whenever the 'Act' or 'Run' button gets pressed in the
     * environment.
     */

    int x, y;

    public EnemyBulletForMantis1(String img) {
        setImage(img);
        this.x = x;     //heroの位置
        this.y = y;     //
        
    }

    @Override
    protected void addedToWorld(World world) {
        //turn(90);
         	
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            move(-2);
        }

        if(isTouching(Enemies.me.getClass()) && Cater.notInvincible){
              Cater.life--;
              Cater.updateLife = true;
              Cater.notInvincible = false;
            }else if((isAtEdge() || getY() >= 799)){
                getWorld().removeObject(this);
                moveFlag = false;
            }
    }
    
}
