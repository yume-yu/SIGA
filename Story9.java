import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story9 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story9 extends Story
{

    public Story9(){
        super(9, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage9();
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
