import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story3 extends Story
{

    
    public Story3(){
        super(3, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage3();
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
