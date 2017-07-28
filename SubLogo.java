import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SubLogo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SubLogo extends SystemObject
{
    /**
     * Act - do whatever the SubLogo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int textSize = 45;
    int transportLevel = 0;
    //int dx = 5;
    String titleName = "~~the Caterpillar~~";
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,255);
    Color rightGray = new Color(125,125,125,255);
    GreenfootImage subTitleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
    

    public SubLogo(){
        setImage(subTitleLogo);
    }
    public void act() 
    {
    }    
}
