
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
/*旧当たり判定に仕様するインポート*/
import java.util.ArrayList;
import java.util.List;

public class Enemies extends Actor {

    //ここからコンストラクタで定義
    //自分の画像
    GreenfootImage img;

    //体力
    int hitpoint;

    //倒したときのスコア
    int giveScore;

    //落とすアイテム
    //配列の大きさ = 落とす数
    //配列の中身 = アイテム
    int items[];

    //
    int beamFlameCount = 0;

    //当たり判定の広さ
    int range = 10;

    GreenfootSound bye = new GreenfootSound("./sounds/bye.mp3");
    //-------------------------//
    //ここからstatusUpdate()で使用。
    //x座標
    int x;

    //y座標
    int y;
    //-------------------------//

    //ここから当たり判定に使用
    //周りのオブジェクトを取得したときの格納先
    List list = new ArrayList();
    //自機外郭
    static CaterOuter meout = new CaterOuter();
    //自機かどうか判断のためのインスタンス
    static Cater me = new Cater(meout);
    static BulletEnhanced beam = new BulletEnhanced();
    //-------------------------//

    //コンストラクタの見本.少なくともここにあるものは全部定義してほしい
    public Enemies() {
        //自分の画像を指定
        img = new GreenfootImage("./images/point.png");

        //画像を設定
        setImage(img);

        //体力を設定
        hitpoint = 10;

        //倒されたときのスコアを設定(使うかわからないからとりあえず0でいい)
        giveScore = 0;

        //落とすアイテムを定義
        //なぜが代入じゃないと許してくれないから、ローカル変数宣言->代入で行く
        int[] items = {0, 1};
        this.items = items;

        bye.setVolume(45);

        //当たり判定の広さを設定
        range = 10;
        //オブジェクトの中心からの距離がrange以下の物を取得するので、やってみての調整になると思う

    }

    //act()の見本
    public void act() {
        //最初に現在位置を更新
        statusUpdate();

        //ここから移動に関するスクリプトを書く
        setLocation(x, y + 1);
        //-------------------------//

        //その後ろに当たり判定を確認
        hitStatusCheck(img);

        //最後に退場のタイミングを書く.下のように一連のifにremoveObjectが入っているのがいいっぽい。
        //先に体力を判定
        if (hitpoint <= 0) {
            //ドロップアイテムの出現
            putItem(items, giveScore);
            //消滅
            if (!bye.isPlaying()) {
                bye.play();
            }
            getWorld().removeObject(this);
            //その後に現在一の判定
        } else if (isAtEdge() || x >= 598) {
            //消滅
            getWorld().removeObject(this);
        }

        //複数で別々のifにremoveObjectがあると、なぜかエラーがでる。
        /*なぜかエラーが出る例
        if(hitpoint <= 0){
            putItem(items);
            getWorld().removeObject(this);
        }
        if(isAtEdge() || getX() >= 598){
            getWorld().removeObject(this);
        }
         */
    }

    //変数xとyに現在位置情報を代入するためのメソッド。
    //ほかから参照するかも知れないので、act()内で最初に呼び出す
    public void statusUpdate() {
        x = getX();
        y = getY();
    }

    //当たり判定のメソッド
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

    //アイテム出現のメソッド。引数としてドロップアイテムの配列をもらう
    public void putItem(int items[], int givePoints) {
        //アイテム出現位置調整用変数
        int[] dx = new int[10];

        //ドロップアイテムの数によってx座標を変えるので、それに応じたx座標の差分を指定
        //とりあえず上限は3だけど、必要に応じて増やす
        switch (items.length) {
            case 0://ここ必要かな...?
                int[] case0 = {};
                dx = case0;
                break;
            case 1://1つの時
                int[] case1 = {0};//呼び出したオブジェクトの真上
                dx = case1;
                break;
            case 2:
                int[] case2 = {-5, +5};//呼び出したオブジェクトの斜め左右上
                dx = case2;
                break;
            case 3:
                int[] case3 = {-8, 0, 8};//呼び出したオブジェクトの真上と斜め左右上
                dx = case3;
                break;

        }
        //item配列の長さ分だけforをまわして、内容に応じたアイテムを出現させる
        for (int i = 0; i < items.length; i++) {
            //内容の判断
            switch (items[i]) {
                case 0://0はスコアをゲットするアイテムに割り当て
                    getWorld().addObject(new GivePoints(giveScore), x + dx[i], y - 5);
                    break;
                case 1://1は必殺技(？)のチャンスをゲットするアイテムに割り当て
                    getWorld().addObject(new GiveSkill(), x + dx[i], y - 5);
                    break;
            }
        }
    }

    //弾を撃つメソッド。使うクラスがメソッド名を統一するために定義。
    public void shot() {
        //だから空っぽ
    }
}
