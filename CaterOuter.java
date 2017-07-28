import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class CaterOuter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaterOuter extends Actor
{
    /**
     * Act - do whatever the CaterOuter wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    static int speed;
    static int x = 0;
    static int y = 0;
    static Cater nakami;
    public CaterOuter(){
        setImage("./images/hero.png");
        nakami = new Cater(this);
    }

    protected void addedToWorld(World world) {
        x = getX();
        y = getY();
        nakami = new Cater(this);
        getWorld().addObject(this, getX(), getY());
        getWorld().addObject(nakami, getX(), getY());
    }
    
    public void act() 
    {
        
    }    
}
