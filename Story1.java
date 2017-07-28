
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Story1 extends Story {

    //GreenfootImage head = new GreenfootImage("長老", TEXT_SIZE, white, transport, rightGray);
    GreenfootImage oruga = new GreenfootImage("オルガ", TEXT_SIZE, white, transport, rightGray);
    GreenfootImage cho = new GreenfootImage("チョウ", TEXT_SIZE, white, transport, rightGray);

    DisplayCharacter orugaImg = new DisplayCharacter(new GreenfootImage("./images/inStory/cater.png"));
    DisplayCharacter choImg = new DisplayCharacter(new GreenfootImage("./images/inStory/butterfly.png"));
    //DisplayCharacter headImg = new DisplayCharacter(new GreenfootImage("./images/inStory/choro.png"));

    boolean start = false;

    public Story1() {
        super(1, MyWorld.title);
    }

    public void act() {
        if (isFirst) {
            isFirst = false;
            MyWorld.usingStage = new Stage1();
            addObject(orugaImg, 1, MyWorld.WINDOW_SIZE / 2);

        }

        checkInput(MyWorld.usingStage);
        try {
            if (orugaImg.getX() != MyWorld.WINDOW_SIZE / 5 + 1) {
                orugaImg.move(2);
            } else {
                progress();
                if (flameCount == 1 && !stopLine) {
                    switch (lineCount) {
                        case 0:
                            addObject(choImg, 4 * MyWorld.WINDOW_SIZE/5- 50, MyWorld.WINDOW_SIZE/2);
                            setName(cho);
                            break;
                        case 1:
                            choImg.img.setTransparency(125);
                            addObject(orugaImg, MyWorld.WINDOW_SIZE / 5, MyWorld.WINDOW_SIZE / 2);
                            setName(oruga);
                            break;
                        case 2:
                            choImg.img.setTransparency(255);
                            orugaImg.img.setTransparency(125);
                            setName(cho);
                            break;
                        case 4:
                            choImg.img.setTransparency(125);
                            orugaImg.img.setTransparency(255);
                            setName(oruga);
                            break;
                        case 5:
                            choImg.img.setTransparency(255);
                            orugaImg.img.setTransparency(125);
                            setName(cho);
                            break;
                        case 6:
                            choImg.img.setTransparency(125);
                            orugaImg.img.setTransparency(255);
                            setName(oruga);
                            break;
                        case 8:
                            choImg.img.setTransparency(255);
                            orugaImg.img.setTransparency(125);
                            setName(cho);
                            break;
                        case 12:
                            choImg.img.setTransparency(125);
                            orugaImg.img.setTransparency(255);
                            setName(oruga);
                            break;
                        case 14:
                            choImg.img.setTransparency(255);
                            orugaImg.img.setTransparency(125);
                            setName(cho);
                            break;
                        case 25:
                            choImg.img.setTransparency(125);
                            orugaImg.img.setTransparency(255);
                            setName(oruga);
                            break;
                        case 29:
                            choImg.img.setTransparency(255);
                            orugaImg.img.setTransparency(125);
                            setName(cho);
                            break;
                        case 31:
                            choImg.img.setTransparency(125);
                            orugaImg.img.setTransparency(255);
                            setName(oruga);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            //throw e;
        }
        exitThis(MyWorld.usingStage);
    }
}
