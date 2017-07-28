import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story4 extends Story
{

    /**
     * Constructor for objects of class Story4.
     * 
     */
    public Story4(){
        super(4, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage4();
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
