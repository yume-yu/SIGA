import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class stageLogo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class StageLogo extends SystemObject
{
    /**
     * Act - do whatever the stageLogo wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    //GreenfootImage stageLogoImage;
    Color textBase;
    int stageTitleSize = 50;
    int transportLevel = 255;
    int hideLogoCount;
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,255);
    Color rightGray = new Color(125,125,125,255);
    String stageTitle;
    
    GreenfootImage stageLogoImage;
    public StageLogo(int stageNumber){
        switch(stageNumber){
            case 1:
                stageTitle = "1st Stage";
                break;
            case 2:
                stageTitle = "2nd Stage";
                break;
            case 3:
                stageTitle = "3rd Stage";
                break;
            case 9:
                stageTitle = "Final Stage";
                break;
            default:
                stageTitle = stageNumber + "th Stage";
                break;
        }
        this.stageLogoImage = new GreenfootImage(stageTitle,stageTitleSize,white,transport,Color.LIGHT_GRAY);
        setImage(stageLogoImage);
    }    

    public void act() {
        transportLevel-= 3;
        if(transportLevel <= 0){
            hideLogoCount++;
            transportLevel = 255;
        }
        if(hideLogoCount == 1){
            if(transportLevel <= 3){
                System.out.println("hide");
                Stage.hideStageLogo = true;
                System.out.println(Stage.hideStageLogo);
                getWorld().removeObject(this);
            }
            white = new Color(255,255,255,transportLevel);
            rightGray = new Color(125,125,125,transportLevel);
        }
        this.stageLogoImage = new GreenfootImage(stageTitle,stageTitleSize,white,transport,rightGray);
        setImage(stageLogoImage);
    }
}
