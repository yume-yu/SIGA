import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class clickStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FlashingText extends SystemObject{
    int textSize = 60;
    int transportLevel = 0;
    int dx = 5;
    String titleName;
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,0);
    Color rightGray = new Color(125,125,125,0);
    GreenfootImage flashText; //= new GreenfootImage(titleName,textSize,white,transport,rightGray);
    
    public FlashingText(String titleName,int textSize){
        this.titleName = titleName;
        this.textSize = textSize;
        flashText = new GreenfootImage(this.titleName,textSize,white,transport,rightGray);
        //ロゴ画像(背景とうめい)
        setImage(flashText);
    }
    
//    public FlashingText(){
//        flashText = new GreenfootImage(this.titleName,textSize,white,transport,rightGray);
//        //ロゴ画像(背景とうめい)
//        setImage(flashText);
//    }
    public void act() 
    {
       if(transportLevel <= 0){
            dx=5;
        }else if(transportLevel >= 255){
            dx=-5;
        }
        transportLevel+=dx;
        if(transportLevel > -1 && transportLevel < 256){
            white = new Color(255,255,255,transportLevel);
            rightGray = new Color(125,125,125,transportLevel);
        }
        
        flashText = new GreenfootImage(titleName,textSize,white,transport,rightGray);
        setImage(flashText);
       
       
    }    
}
