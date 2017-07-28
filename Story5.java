import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story5 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story5 extends Story
{

    /**
     * Constructor for objects of class Story5.
     * 
     */
    public Story5(){
        super(5, MyWorld.title);

    }
    
    @Override
    public void act() {
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage5();
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
