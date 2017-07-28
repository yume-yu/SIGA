import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Story0 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Story0 extends Story
{

    GreenfootImage head = new GreenfootImage("長老", TEXT_SIZE, white, transport, rightGray);
    GreenfootImage oruga = new GreenfootImage("オルガ", TEXT_SIZE, white, transport, rightGray);
    DisplayCharacter orugaImg = new DisplayCharacter(new GreenfootImage("./images/inStory/cater.png"));
    DisplayCharacter headImg = new DisplayCharacter(new GreenfootImage("./images/inStory/choro.png"));
    public Story0(){
        super(0, MyWorld.title);
    }

    @Override
    public void act() {
        if(isFirst){
            isFirst = false;
            MyWorld.usingStory = new Story1();
        }
        
        checkInput(MyWorld.usingStory);
        progress();
        exitThis(MyWorld.usingStory);
        if(flameCount == 1 && !stopLine){
            switch(lineCount){
                case 17:
                    addObject(headImg, 4 * MyWorld.WINDOW_SIZE/5- 50, MyWorld.WINDOW_SIZE/2);
                    setName(head);
                    break;
                case 20:
                    headImg.img.setTransparency(125);
                    addObject(orugaImg, MyWorld.WINDOW_SIZE/5, MyWorld.WINDOW_SIZE/2);
                    setName(oruga);
                    break;
                case 21:
                    headImg.img.setTransparency(255);
                    orugaImg.img.setTransparency(125);
                    setName(head);
                    break;
                case 22:
                    headImg.img.setTransparency(125);
                    orugaImg.img.setTransparency(255);
                    setName(oruga);
                    break;
                case 25:
                    headImg.img.setTransparency(255);
                    orugaImg.img.setTransparency(125);
                    setName(head);
                    break;
            }
        }
        
    }
    
   
}
