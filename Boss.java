
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Boss here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Boss extends Enemies {

    boolean startBattle = false;
    boolean isDamage = false;
    int HITPOINT;
    Caution caution = new Caution();

    public void completeLine() {

    }

    ;
    
    @Override
    protected void addedToWorld(World world) {
        getWorld().addObject(caution, Stage.WIDTH / 2, Stage.HEIGHT / 2);
        img.setTransparency(0);
    }

    @Override
    //原理的に画像ファイルの縦横があれば最適解が弾けるので、画像を引数にした版
    public void hitStatusCheck(GreenfootImage img) {
        //int range = 0;
        if (img.getWidth() - img.getHeight() >= 0) {
            range = img.getWidth() / 2;
        } else {
            range = img.getHeight() / 2;
        }

        list = getObjectsInRange(range, Stage.bullets[0].getClass());

        //listの中身の数分forをまわして、それぞれのクラスが｢自機の弾｣｣かを判断する
        for (int i = 0; i < list.size(); i++) {
            //取得したクラスが｢自分に当たっているクラス｣かを判断
            if (isTouching(list.get(i).getClass())) {
                //もし当たっているクラスが｢Bulletクラス = 自機の弾｣なら
                if (list.get(i).toString().matches("Bullet@.*")) {
                    //体力から1減らす
                    hitpoint--;
                    isDamage = true;
                    //当たった弾の判別のため、bulletsの要素数でforを回す
                    for (int j = 0; j < Stage.bullets.length; j++) {
                        //当たったオブジェクトの名前が一致する弾を探す
                        if (list.get(i).toString().equals(Stage.bulletsClassName[j])) {
                            //見つけたら該当の弾の使用中フラグを更新
                            Stage.bullets[j].moveFlag = false;
                            //該当の弾を除去
                            getWorld().removeObject(Stage.bullets[j]);
                            break;
                        }
                    }
                }
            }
        }

        if (Cater.isBeaming) {
            for (int i = 0; i < Cater.bulletEnhanced.length; i++) {
                try {
                    if (Cater.bulletEnhanced[i].checkT(x, y)) {
                        
                        if (beamFlameCount == 0) {
                            hitpoint--;
                            isDamage = true;
                        }
                        beamFlameCount++;
                        if (beamFlameCount == 15) {
                            beamFlameCount = 0;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }

        list = getObjectsInRange(range, me.getClass());

        //listの中身の数分forをまわして、｢自機本体｣かを判断する
        for (int i = 0; i < list.size(); i++) {
            //取得したクラスが｢自分に当たっているクラス｣かを判断
            if (isTouching(list.get(i).getClass())) {

                //もし当たっているクラスが｢Caterクラス = 自機｣なら
                if (list.get(i).toString().matches("Cater@.*") && Cater.notInvincible) {
                    //自機の体力or残機を減らす処理がここに来る
                    Cater.life--;
                    Cater.updateLife = true;
                    Cater.notInvincible = false;
                }
            }
        }
    }

    @Override
    public void hitStatusCheck(int range) {

        list = getObjectsInRange(range, Stage.bullets[0].getClass());

        //listの中身の数分forをまわして、それぞれのクラスが｢自機の弾｣｣かを判断する
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).toString());
            //取得したクラスが｢自分に当たっているクラス｣かを判断
            if (isTouching(list.get(i).getClass())) {
                //もし当たっているクラスが｢Bulletクラス = 自機の弾｣なら
                if (list.get(i).toString().matches("Bullet@.*")) {
                    //体力から1減らす
                    hitpoint--;
                    isDamage = true;
                    //当たった弾の判別のため、bulletsの要素数でforを回す
                    for (int j = 0; j < Stage.bullets.length; j++) {
                        //当たったオブジェクトの名前が一致する弾を探す
                        if (list.get(i).toString().equals(Stage.bulletsClassName[j])) {
                            //見つけたら該当の弾の使用中フラグを更新
                            Stage.bullets[j].moveFlag = false;
                            //該当の弾を除去
                            getWorld().removeObject(Stage.bullets[j]);
                            break;
                        }
                    }
                }
                //else if(list.get(i).toString().matches("BulletEnhanced@.*")){
//                    //体力から1減らす
//                    hitpoint--;
//                }
            }
        }

        if (Cater.isBeaming) {
            for (int i = 0; i < Cater.bulletEnhanced.length; i++) {
                try {
                    if (Cater.bulletEnhanced[i].checkT(x, y)) {
                        
                        if (beamFlameCount == 0) {
                            hitpoint--;
                            isDamage = true;
                        }
                        beamFlameCount++;
                        if (beamFlameCount == 15) {
                            beamFlameCount = 0;
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
        
        list = getObjectsInRange(range, me.getClass());

        //listの中身の数分forをまわして、｢自機本体｣かを判断する
        for (int i = 0; i < list.size(); i++) {
            //取得したクラスが｢自分に当たっているクラス｣かを判断
            if (isTouching(list.get(i).getClass())) {

                //もし当たっているクラスが｢Caterクラス = 自機｣なら
                if (list.get(i).toString().matches("Cater@.*") && Cater.notInvincible) {
                    //自機の体力or残機を減らす処理がここに来る
                    Cater.life--;
                    Cater.updateLife = true;
                    Cater.notInvincible = false;
                }
            }
        }
    }

    public void act() {

    }
}
