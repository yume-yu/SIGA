import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GiveSkill extends Items
{
    public GiveSkill(){
        img = new GreenfootImage("./images/power.png");
        setImage(img);
    }
    
    public void act() {
        if(Stage.inPause){
            
        }else{
            fall();
            giveSkill();
        }
    }    
}
