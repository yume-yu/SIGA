
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import static java.lang.Math.abs;

/**
 * Write a description of class EnemyBulletForMantis1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class EnemyBulletForMantis3 extends Bullet {

    /**
     * Act - do whatever the EnemyBulletForMantis1 wants to do. This method is
     * called whenever the 'Act' or 'Run' button gets pressed in the
     * environment.
     */

    int x, y;
    boolean flag = true;

    public EnemyBulletForMantis3(int x, int y,String img) {
        GreenfootImage a = new GreenfootImage(img);
        a.rotate(180);
        setImage(a);
        this.x = x;
        this.y = y;
        int cnt = 0;
        turnTowards(this.x, this.y); 
    }

    @Override
    protected void addedToWorld(World world) {     	
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            
            double distance = Math.sqrt(abs((getX() - x)) * (getX() - x) + (getY() - y) * (getY() - y));
            if (distance > 20 && flag) {
                turnTowards(x, y);
            }
         else {
               flag = false;
           }
            move(3);
            System.out.println(Math.sqrt(abs((getX() - x)) * (getX() - x) + (getY() - y) * (getY() - y)));
        }

        if(isTouching(Enemies.me.getClass()) && Cater.notInvincible){
              Cater.life--;
              Cater.updateLife = true;
              Cater.notInvincible = false;
            }else if((isAtEdge() || getY() <= 1)){
                getWorld().removeObject(this);
                moveFlag = false;
            }
    }

}
