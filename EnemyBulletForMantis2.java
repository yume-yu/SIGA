
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import static java.lang.Math.abs;

/**
 * Write a description of class EnemyBulletForMantis1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyBulletForMantis2 extends Bullet {

    /**
     * Act - do whatever the EnemyBulletForMantis1 wants to do. This method is
     * called whenever the 'Act' or 'Run' button gets pressed in the
     * environment.
     */

    int i, x, y;

    public EnemyBulletForMantis2(String img, int i) {
        setImage(img);
        turn(90);
        this.i =  i;

        
    }

    @Override
    protected void addedToWorld(World world) {     	
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            move(4*i);
        }

        if(isTouching(Enemies.me.getClass()) && Cater.notInvincible){
              Cater.life--;
              Cater.updateLife = true;
              Cater.notInvincible = false;
            }else if((isAtEdge() || getX() <= 1)){
                getWorld().removeObject(this);
                moveFlag = false;
            }
    }

}
