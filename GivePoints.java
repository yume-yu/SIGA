import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class GivePoints here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GivePoints extends Items
{
    int  point = 1;
    public GivePoints(int givePoints){
        point = givePoints;
        setImage(img);
    }
    
    public void act(){
        if(Stage.inPause){
            
        }else{
            fall();
            givePoint(point);
        }
    }    
}
