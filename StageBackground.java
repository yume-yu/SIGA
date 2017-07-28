import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class stage1_1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StageBackground extends SystemObject
{
    /**
     * Act - do whatever the stage1_1 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public StageBackground(String image){
        GreenfootImage img = new GreenfootImage(image);
        //img.rotate(-90);
        setImage(img);
        //turn(90);
    }
    public void act() 
    {
        if(Stage.inPause){
            
        }else{
            turnTowards(Stage.WIDTH/2-1, Stage.HEIGHT);
            move(2);
            if(getY() >= 799){
                    setLocation(Stage.WIDTH/2-1,0);
                    //getWorld().removeObject(this);
            }
        }
    }
}
