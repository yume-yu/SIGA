import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *
 * @author C0116246
 */

public class Dango extends Enemies
{
    
    public Dango() {
        hitpoint = 1;
        giveScore = 1;
        range = 50;
        int[] items = {0};
        this.items = items;
        img = new GreenfootImage("./images/dango.png");
        setImage(img);
    }
    
    public void act() 
    {
        if(Stage.inPause){
        }
        else{
           statusUpdate();

           setLocation(x, y + 1);

           //あたり判定
            hitStatusCheck(img);

           //死亡判定
           if(hitpoint <= 0){
                //ドロップアイテムの出現
                putItem(items,giveScore);
                if (!bye.isPlaying()) {
                   bye.play();
               }
                //消滅
                getWorld().removeObject(this);
            //その後に現在一の判定
            }else if(isAtEdge()){
                //消滅
                getWorld().removeObject(this);
            }
        }
    }
}
