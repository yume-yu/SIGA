
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Kanabun here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Kanabun extends Enemies {

    
    int speed = 5;

    public Kanabun() {
        hitpoint = 3;
        giveScore = 10;
        int[] items = {0, 1};
        this.items = items;
        img = new GreenfootImage("./images/kanabun.png");
        setImage(img);
        turn(90);
    }

    public void act() {
        if(Stage.inPause){
        }
        else{
            super.act();
            move(speed);
        }
    }    
}
