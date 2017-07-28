import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class SideStatus here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class SideStatus extends SystemObject
{
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,255);
    Color rightGray = new Color(125,125,125,255);
    
    GreenfootImage img = new GreenfootImage(200,800);
    
    
    
    public SideStatus(String stageNum,int size1){
        img = new GreenfootImage(200,800);
        img.setColor(Color.BLACK);
        img.fill();
        GreenfootImage stage = new GreenfootImage(stageNum,size1,white,transport,rightGray);
        img.drawImage(stage, 10, stage.getHeight()/2);
        GreenfootImage bomb = new GreenfootImage("Bomb : ", 30, white, transport, rightGray);
        img.drawImage(bomb, 10, 700 - 7 * bomb.getHeight()/2);
        GreenfootImage life = new GreenfootImage("Life : ", 30, white, transport, rightGray);
        img.drawImage(life, 10, 700 - 2 * life.getHeight());
        //img.drawImage(SPACE, 10, img.getHeight() - SPACE.getHeight() - 10);
        setImage(img);
        
    }
    
    public void setGaming(){
        img = new GreenfootImage(200,800);
        img.setColor(transport);
        img.fill();
        
        setImage(img);
    }
    
    public void act() 
    {
        // Add your action code here.
    }    
}
