import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fly extends Enemies
{
    //フラフラ動くための向きのフラク
    int direction = 1;
 
    //コンストラクタ
    public Fly(){
        hitpoint = 1;
        giveScore = 1;
        range = 15;
        int[] items = {0,0,0};
        this.items = items;
        img = new GreenfootImage("./images/fly.png");
        setImage(img);
    }
    
    public void act(){
        if(Stage.inPause){
        }
        else{
            //現在位置の更新
            statusUpdate();

            //ここから移動スクリプト
            //乱数を降って偶数なら、
            if(Greenfoot.getRandomNumber(10) % 2 == 0){
                //横移動は右
                direction = 1;
            }else {//奇数なら
                //横移動は左
                direction = -1;
            }
            //横の移動距離は乱数*向き
            x = x + direction * Greenfoot.getRandomNumber(5);
            //縦の移動距離は1
            y ++;
            //位置の反映
            setLocation(x, y);
            //-------------------------//

            //当たり判定の呼び出し
            hitStatusCheck(range);

            //消滅の判定
            if(hitpoint <= 0){//体力が0なら
                //アイテムをドロップ
                putItem(items, giveScore);
                //消滅
                getWorld().removeObject(this);
            }else if(getY() >= 799){//端っこに来たら
                //消滅
                getWorld().removeObject(this);
            }
        }  
    }
}