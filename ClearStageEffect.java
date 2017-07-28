
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

/**
 * Write a description of class ClearStageEffect here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class ClearStageEffect extends SystemObject {

    List list = new ArrayList();

    GreenfootImage base;
    GreenfootImage title;
    GreenfootImage stageName;
    GreenfootImage clearTime;

    Color white = Display.white;
    Color transport = Display.transport;
    Color rightGray = Display.rightGray;

    int flameCount;

    public ClearStageEffect(String StageName, int clearTime) {
        flameCount = 0;
        base = new GreenfootImage(600, 800);
        title = new GreenfootImage("Stage Clear!", 100, white, transport, rightGray);
        base.drawImage(title, (base.getWidth() - title.getWidth()) / 2, base.getHeight() / 3 - title.getHeight());
        base.setTransparency(0);
        this.stageName = new GreenfootImage(StageName, 80, white, transport, rightGray);
        String time;
        if (Integer.toString(clearTime / 60000).length() == 1) {
            time = "0" + Integer.toString(clearTime / 60000);
        } else {
            time = Integer.toString(clearTime / 60000);
        }
        clearTime = clearTime % 60000;
        if (Integer.toString(clearTime / 1000).length() == 1) {
            time += ":0" + Integer.toString(clearTime / 1000);
        } else {
            time += ":" + Integer.toString(clearTime / 1000);
        }
        clearTime = clearTime % 1000;
        if (Integer.toString(clearTime / 10).length() == 1) {
            time += ".0" + Integer.toString(clearTime / 10);
        } else {
            time += "." + Integer.toString(clearTime / 10);
        }
        this.clearTime = new GreenfootImage("Time : " + time, 80, white, transport, rightGray);
        setImage(base);
    }

    @Override
    protected void addedToWorld(World world) {
        /* EnemyBulletたちの除去 */
        list = getWorld().getObjects(EnemyBullet.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForBee.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForSpi.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForStag.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForBut.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForCycron.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForMantis1.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForMantis2.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(EnemyBulletForMantis3.class);
        removeSpecifyObject(list);
        list = getWorld().getObjects(DisplayHitpoint.class);
        removeSpecifyObject(list);

        /* ザコの体力を強制的に0にする*/
        list = getWorld().getObjects(Ant.class);
        removeSpecifyEnemies(list);
        list = getWorld().getObjects(Dango.class);
        removeSpecifyEnemies(list);
        list = getWorld().getObjects(Bee.class);
        removeSpecifyEnemies(list);
        list = getWorld().getObjects(Fly.class);
        removeSpecifyEnemies(list);
        list = getWorld().getObjects(Kanabun.class);
        removeSpecifyEnemies(list);
        list = getWorld().getObjects(SpiderChild.class);
        removeSpecifyEnemies(list);

        ((Stage) (getWorld())).inClearView = true;
    }

    public void removeSpecifyObject(List list) {
        if (!list.isEmpty()) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    getWorld().removeObject((Actor) (list.get(i)));
                }
            } catch (Exception e) {
            }
        }
    }

    public void removeSpecifyEnemies(List list) {
        if (!list.isEmpty()) {
            try {
                for (int i = 0; i < list.size(); i++) {
                    ((Enemies) (list.get(i))).hitpoint = 0;
                }
            } catch (Exception e) {
            }
        }
    }

    public void act() {
        if (base.getTransparency() != 255) {
            flameCount++;
            if (flameCount >= 200 && flameCount % 10 == 1) {
                base.setTransparency(base.getTransparency() + 51);
            }
        } else {
            if (flameCount == 241) {
                flameCount++;
                base.drawImage(stageName, (base.getWidth() - stageName.getWidth()) / 2, base.getHeight() / 2 + stageName.getHeight());
                base.drawImage(clearTime, (base.getWidth() - clearTime.getWidth()) / 2, base.getHeight() / 2 + stageName.getHeight() + clearTime.getHeight());
                MyWorld.usingStory.flashPressEnter = new FlashingText(Story.PRESS_ENTER, Story.FLASHTEXT_SIZE);
                getWorld().addObject(MyWorld.usingStory.flashPressEnter, Stage.WIDTH - MyWorld.usingStory.flashPressEnter.flashText.getWidth() / 2, Stage.HEIGHT - 2 * MyWorld.usingStory.flashPressEnter.flashText.getHeight() / 3);
            }
            if (Greenfoot.isKeyDown("enter")) {
                MyWorld.clearStage = true;
            }
        }

    }
}
