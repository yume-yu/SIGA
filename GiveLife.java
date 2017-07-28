import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GiveLofe here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GiveLife extends Items
{
    /**
     * Act - do whatever the GiveLofe wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    
    
    public GiveLife(){
        img = new  GreenfootImage("./images/oneUp.png");
        setImage(img);
    }    

    public void act() {
        if(Stage.inPause){
            
        }else{
        fall();
        giveLife();
        }
    }
}
