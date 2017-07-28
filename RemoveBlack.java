import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class removeBlack here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RemoveBlack extends SystemObject
{
    /**
     * Act - do whatever the removeBlack wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    GreenfootImage black;// = new GreenfootImage(600,800);
    int transparency = 255;
    public RemoveBlack(){
        //getWorld().setPaintOrder(this.getClass(),MyWorld.scoreDisplay.getClass(),MyWorld.stageDisplay.getClass(),MyWorld.statusBack.getClass());
        black = new GreenfootImage(600,800);
        black.fillRect(0, 0, 600, 800);
        black.setTransparency(transparency);
        setImage(black);
    }
    
    public RemoveBlack(int x,int y){
        black = new GreenfootImage(x,y);
        black.fillRect(0, 0, x, y);
        black.setTransparency(transparency);
        setImage(black);
    }
    public void act(){ 
//        try{
//            getWorld().setPaintOrder(this.getClass());
//        }catch(NullPointerException e){
//            getWorld().setPaintOrder(this.getClass());
//        }
        if(transparency < 5){
            if(MyWorld.stageNumber == 0){
                MyWorld.finishRemoveBlack = true;
            }
            getWorld().removeObject(this);
        }else if(transparency > 0){
            transparency-=5;
        }
        black.setTransparency(transparency);
        setImage(black);
    }   
}
