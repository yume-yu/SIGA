import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class blackout here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Blackout extends SystemObject {

    GreenfootImage black;
    int transparency = 0;

    public Blackout() {
        //getWorld().setPaintOrder(null);

        black = new GreenfootImage(600, 800);
        black.fillRect(0, 0, 600, 800);
        black.setTransparency(transparency);
        setImage(black);
    }

    public Blackout(int x, int y) {
        //getWorld().setPaintOrder(null);
        //getWorld().setPaintOrder(this.getClass());
        black = new GreenfootImage(x, y);
        black.fillRect(0, 0, x, y);
        black.setTransparency(transparency);
        setImage(black);
    }

    public void act() {
        getWorld().setPaintOrder(this.getClass());
        if (transparency < 254) {
            transparency += 5;
        }else if(transparency ==200){
            
        }else {
            
            //
            try {
                getWorld().removeObject(MyWorld.subti);
                getWorld().removeObject(MyWorld.clicktostart);
            } catch (Exception e) {
            }
            MyWorld.waitStartStage = true;  
            getWorld().setBackground(black);
            getWorld().removeObjects(getWorld().getObjects(null));
            //getWorld().removeObject(this);
            
        }
        black.setTransparency(transparency);
        setImage(black);
    }
}
