import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story6 extends Story
{

    /**
     * Constructor for objects of class Story6.
     * 
     */
    public Story6(){
        super(6, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage6();
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
