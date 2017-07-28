import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Logo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Logo extends SystemObject
{
    
    static boolean finishHideLogo = false;
    
    int textSize;
    int transportLevel = 0;
    String titleName;// = "けむしきんぐ";
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,0);
    Color rightGray = new Color(125,125,125,0);
    //public GreenfootImage title = new GreenfootImage("./images/Logo.png");
    Font titleFont = new Font(30);
    GreenfootImage titleLogo ;
    
    public Logo(String text,int size){
        finishHideLogo = false;
        titleName = text;
        textSize = size;
        titleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
        setImage(titleLogo);
    }
    
    public void act() 
    {
        //System.out.println("diaplay title");
        if(transportLevel < 255){
            transportLevel+=5;
        }else{
            transportLevel =255;
        }
        white = new Color(255,255,255,transportLevel);
        rightGray = new Color(125,125,125,transportLevel);
        titleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
        setImage(titleLogo);
        if(getY() <= getWorld().getCellSize()*getWorld().getHeight()/2 -100){
            setLocation(getX(), getY()+4);
        }else{
            MyWorld.startStage = true;
        }
    }
}
