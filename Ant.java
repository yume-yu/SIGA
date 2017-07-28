
import greenfoot.GreenfootImage;

public class Ant extends Enemies {

    //移動パターン
    int mode = 0;
    //ボスが呼び出したかどうか
    boolean queen = false;

    //自機狙いの時に使用
    int heroX;
    int heroY;
    //最初のときのみtrue
    boolean first = true;

    public Ant() {
        //ほぼダンゴムシのコピペ
        hitpoint = 1;
        giveScore = 1;
        range = 50;
        int[] items = {0};
        this.items = items;
        this.mode = 1;
        img = new GreenfootImage("./images/ant.png");
        setImage(img);

    }

    //ボスが呼び出した場合
    public Ant(int mode, boolean queen) {
        hitpoint = 1;
        //スコアを得られないように
        giveScore = 0;
        range = 50;
        int[] items = {0};
        this.items = items;
        img = new GreenfootImage("./images/ant.png");
        setImage(img);
        this.mode = mode;
        this.queen = queen;
        heroX = Stage.heroX;
        heroY = Stage.heroY;
    }

    //移動パターン変更用
    public Ant(int mode) {
        hitpoint = 1;
        giveScore = 1;
        range = 50;
        int[] items = {0};
        this.items = items;
        img = new GreenfootImage("./images/ant.png");
        setImage(img);
        this.mode = mode;
        heroX = Stage.heroX;
        heroY = Stage.heroY;
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            statusUpdate();
            switch (mode) {
                case 0:
                    //真下に向かって移動　　　　　　　　　このへんから
                    turnTowards(x, y + 3);
                    setLocation(x, y + 3);
                    break;
                case 1:
                    //左斜め下に向かって移動
                    turnTowards(x - 3, y + 3);
                    setLocation(x - 3, y + 3);
                    break;
                case 2:
                    //右斜め下に向かって移動
                    turnTowards(x + 3, y + 3);
                    setLocation(x + 3, y + 3);
                    break;
                case 3:
                    //初めに自機を向き
                    if (first) {
                        turnTowards(heroX, heroY);
                        first = !first;
                    }
                    //その方向に移動
                    move(2);
                    break;//              この辺まで変更
                default:
                    break;
            }
            //あたり判定
            hitStatusCheck(img);

            //死亡判定
            if (hitpoint <= 0) {
                //queen
                if (!queen) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                }
                if (!bye.isPlaying()) {
                    bye.play();
                }
                //消滅
                getWorld().removeObject(this);
                //その後に現在一の判定
            } else if (isAtEdge()) {
                //消滅
                getWorld().removeObject(this);
            }
        }
    }
}
