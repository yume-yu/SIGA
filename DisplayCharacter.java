import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayCharacter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DisplayCharacter extends SystemObject
{
    GreenfootImage img;
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,255);
    Color rightGray = new Color(125,125,125,255);

    public DisplayCharacter(GreenfootImage img) {
        this.img = img;
        setImage(this.img);
    }
       
}
