
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class EnemyBulletForCycron extends Bullet {

    double radian = 0;
    int x = 0, y = 0;

    public EnemyBulletForCycron(GreenfootImage img) {
        this.img = img;
        setImage(this.img);
    }

    @Override
    protected void addedToWorld(World world) {
        x = getX();
        y = getY();
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            setLocation((int) (x + 50 * Math.sin(radian)), getY() + 2);
            radian += Math.toRadians(1);
            if (radian == Math.toRadians(Math.PI)) {
                radian = Math.toRadians(0);
            }

            if (isTouching(Enemies.me.getClass()) && Cater.notInvincible) {
                Cater.life--;
                Cater.updateLife = true;
                Cater.notInvincible = false;
                getWorld().removeObject(this);
            } else if ((isAtEdge() || getX() >= 599)) {
                getWorld().removeObject(this);
            }
        }
    }
}
