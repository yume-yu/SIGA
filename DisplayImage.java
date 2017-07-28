import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayImage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayImage extends SystemObject
{
    int textSize = 30;
    boolean ifScore = false;
    GreenfootImage img = new GreenfootImage(200,800);
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,255);
    Color rightGray = new Color(125,125,125,255);
    Font titleFont = new Font(30);
    GreenfootImage titleLogo ;//= new GreenfootImage(titleName,textSize,white,transport,rightGray);
    
    public DisplayImage(GreenfootImage img,int size){
        this.img = img;
        textSize = size;
        //titleLogo = new GreenfootImage(titleName,textSize,white,transport,rightGray);
        setImage(img);
    }
    public DisplayImage(){
        img.setColor(Color.BLACK);
        img.fill();
        setImage(img);
    }
    
        
}
