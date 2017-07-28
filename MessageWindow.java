import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MessageWindow here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MessageWindow extends SystemObject
{
    /**
     * Act - do whatever the MessageWindow wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage box;
    public MessageWindow(int width,int height){
        box = new GreenfootImage(width, height);
        box.fillRect(0, 0, width, height);
        box.setTransparency(125);
        setImage(box);
    }

    public MessageWindow(int width,int height,int trans){
        box = new GreenfootImage(width, height);
        box.fillRect(0, 0, width, height);
        box.setTransparency(trans);
        setImage(box);
    }      

    public void act() {
        // Add your action code here.
    }
}
