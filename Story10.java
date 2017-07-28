import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story10 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story10 extends Story
{

    /**
     * Constructor for objects of class Story10.
     * 
     */
    public Story10(){
        super(2, MyWorld.title);
        
    }
    
    World nextWorld; 
    @Override
    public void act() {
        if(isFirst){
            isFirst = false;
            nextWorld = new MyWorld();
            
        }
        
        checkInput(nextWorld);
        progress();
        exitThis(nextWorld);
        if(flameCount == 1){
            switch(lineCount){
                
            }
        }
    }
}
