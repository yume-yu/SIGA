
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class DisplayHitpoint here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class DisplayHitpoint extends SystemObject {

    //色
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255, 255, 255, 255);
    Color rightGray = new Color(125, 125, 125, 255);

    GreenfootImage gauge = new GreenfootImage(180, 5);
    GreenfootImage base = new GreenfootImage(200, 195);
    GreenfootImage bossNameCase;
    final GreenfootImage bossName = new GreenfootImage("Boss :", 30, white, transport, rightGray);
    final GreenfootImage hitpoint = new GreenfootImage("HitPoint :", 30, white, transport, rightGray);
    final int LENGTH = 180;
    int length;

    public void drawGauge() {
        base = new GreenfootImage(200, 195);
        length = (int) (((double) MyWorld.usingStage.boss.hitpoint / MyWorld.usingStage.boss.HITPOINT) * LENGTH);
        if (length == 0) {
            getWorld().removeObject(this);
        } else {
            gauge = new GreenfootImage(length, 5);
            if (length >= 2 * LENGTH / 3) {
                gauge.setColor(Color.GREEN);
            } else if (length >= LENGTH / 3) {
                gauge.setColor(Color.YELLOW);
            } else {
                gauge.setColor(Color.RED);
            }
            gauge.fill();
            base.drawImage(this.bossName, 10, 0);
            base.drawImage(this.bossNameCase, base.getWidth() / 2 - bossNameCase.getWidth() / 2, this.bossName.getHeight());
            base.drawImage(this.hitpoint, 10, this.bossName.getHeight() + this.bossNameCase.getHeight() + 15);
            base.drawImage(gauge, 10, this.bossName.getHeight() + this.bossNameCase.getHeight() + this.hitpoint.getHeight() + 20);
            setImage(base);
        }
    }

    public DisplayHitpoint(String bossName) {
        bossNameCase = new GreenfootImage(bossName, 30, white, transport, rightGray);
        drawGauge();

    }

    public void act() {
        if (MyWorld.usingStage.boss.isDamage) {
            drawGauge();
            MyWorld.usingStage.boss.isDamage = false;
        }
    }
}
