import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story8 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story8 extends Story
{

    /**
     * Constructor for objects of class Story8.
     * 
     */
    public Story8(){
        super(8, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage8();
        }
        
        checkInput(MyWorld.usingStage);
        progress();
        exitThis(MyWorld.usingStage);
        if(flameCount == 1){
            switch(lineCount){
                
            }
        }
    }
}
