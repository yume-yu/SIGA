import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/*旧当たり判定に仕様するインポート*/
//import java.util.ArrayList;
//import java.util.List;

public class Bullet extends Actor {

    /**
     * Act - do whatever the shot wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public boolean moveFlag = false;
    public boolean removeFlag  = false;
    public int bullet_speedX = 0; 
    public int bullet_speedY = 6;
    //  弾のスピード
    
    GreenfootImage img = new GreenfootImage("./images/bullet1.png");
    
    public Bullet() {
        setImage(img);
        turn(-90);
    }
    
    public Bullet(int speedX,int speedY,GreenfootImage img){
        bullet_speedX = speedX;
        bullet_speedY = speedY;
        setImage(img);
    }

    public void act() {
        if(Stage.inPause){
            
        }else{
            move(6);
            if (getY() == 0 || getY() == 799) {
                getWorld().removeObject(this);     
            }
        }
    }
}
