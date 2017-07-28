
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class PauseText extends SystemObject {

    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255, 255, 255, 255);
    Color rightGray = new Color(125, 125, 125, 255);

    static GreenfootImage main = new GreenfootImage(Stage.WIDTH, Stage.HEIGHT);
    GreenfootImage pose = new GreenfootImage("- PAUSE -", 100, white, transport, rightGray);
    GreenfootImage resume = new GreenfootImage("Resume", 50, white, transport, rightGray);
    GreenfootImage backTitle = new GreenfootImage("Back Title", 50, white, transport, rightGray);
    GreenfootImage arrow = new GreenfootImage(resume.getHeight() / 2, resume.getHeight() / 2);

    boolean selectedResume = true;

    static DisplayImage here;

    public PauseText() {
        arrow.setColor(white);
        int x[] = {0, 0, arrow.getWidth()};
        int y[] = {0, arrow.getHeight(), arrow.getHeight() / 2};
        arrow.fillPolygon(x, y, 3);
        here = new DisplayImage(arrow, 0);
        main.drawImage(pose, (main.getWidth() - pose.getWidth()) / 2, (main.getHeight() - pose.getHeight()) / 2);
        main.drawImage(resume, (main.getWidth() - resume.getWidth()) / 2, (main.getHeight() - pose.getHeight()) / 2 + resume.getHeight() / 2 + 150);
        main.drawImage(backTitle, (main.getWidth() - backTitle.getWidth()) / 2, (main.getHeight() - pose.getHeight()) / 2 + resume.getHeight() + backTitle.getHeight() / 2 + 150);
        setImage(main);
    }

    @Override
    protected void addedToWorld(World world) {
        selectResume();
        selectedResume = true;
        MyWorld.usingStage.bgm.pause();
    }

    public void selectResume() {
        getWorld().addObject(here, (main.getWidth() - backTitle.getWidth()) / 2 - arrow.getWidth() - 10, (main.getHeight() - pose.getHeight()) / 2 + resume.getHeight() + 150);
    }

    public void selectBack() {
        getWorld().addObject(here, (main.getWidth() - backTitle.getWidth()) / 2 - arrow.getWidth() - 10, (main.getHeight() - pose.getHeight()) / 2 + resume.getHeight() + backTitle.getHeight() + 150);
    }

    public void act() {
        try {
            if ((Stage.str.equals("down") || Stage.str.equals("up")) && selectedResume) {
                getWorld().removeObject(here);
                selectBack();
                selectedResume = false;
            } else if ((Stage.str.equals("down") || Stage.str.equals("up")) && !selectedResume) {
                getWorld().removeObject(here);
                selectResume();
                selectedResume = true;
            } else if (Stage.str.equals("enter")) {
                MyWorld.button.play();
                if (selectedResume) {
                    Stage.inPause = false;
                    MyWorld.usingStage.bgm.playLoop();
                    getWorld().removeObject(here);
                    getWorld().removeObject(Stage.pose);
                    getWorld().removeObject(this);


                } else {
                    Stage.inPause = false;
                    MyWorld.usingStage.bgm.stop();
                    getWorld().removeObject(here);
                    getWorld().removeObject(Stage.pose);
                    getWorld().removeObject(this);
                    Greenfoot.setWorld(new MyWorld());
                }
            }
        } catch (Exception e) {

        }
    }

}
