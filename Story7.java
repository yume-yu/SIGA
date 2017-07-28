import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story7 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story7 extends Story
{

    /**
     * Constructor for objects of class Story7.
     * 
     */
    public Story7(){
        super(7, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage7();
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
