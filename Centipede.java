/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yume_yu
 */
public class Centipede extends Boss {

    
    public void act() {
        if (Stage.inPause) {
        } else {
            if (startBattle) {

                //位置情報の更新
                statusUpdate();

                
                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                    //getWorld().removeObject();
                    //消滅
                    getWorld().addObject(new ClearStageEffect("Stage3", ((Stage) (getWorld())).timer.millisElapsed()), Stage.WIDTH / 2, Stage.HEIGHT / 2);

                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;
                }
            } else if (caution.finishCaution) {
                turnTowards(getX(), getY() + 100);
                getWorld().addObject(new DisplayHitpoint("Mantis"),700, Stage.HEIGHT/2);    
                img.setTransparency(255);
                startBattle = true;
            }
        }
    }
 
        
    
}
