import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Display here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Display extends SystemObject
{
    int textSize = 30;
    static boolean ifScore = false;
    String titleName;
    static Color transport = new Color(0, 0, 0, 0);
    static Color white = new Color(255,255,255,255);
    static Color rightGray = new Color(125,125,125,255);
    Font titleFont = new Font(30);
    GreenfootImage titleLogo ;//= new GreenfootImage(titleName,textSize,white,transport,rightGray);
    
    public Display(String text,int size){
        titleName = text;
        textSize = size;
        titleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
        setImage(titleLogo);
    }
    
    public Display(){
        ifScore = true;
        titleName = "Score : " + Stage.scoreBlank + Stage.scoreString;
        titleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
        setImage(titleLogo);
    }
    
    public void act(){
        if(ifScore){
            titleName = "Score : " + Stage.scoreBlank + Stage.scoreString;
            titleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
            setImage(titleLogo);
            ifScore = false;
        }
        
    }    
}
