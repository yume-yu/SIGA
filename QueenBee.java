
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.World;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author C0116246
 */
public class QueenBee extends Boss {

    
    //以下女王蟻のコピペ
    //状態判断用変数
    int mode = 0;
    //体力の上限記憶変数
    final int HITPOINT = 100;
    //状態切替時の位置記憶変数
    int oldX = 0, oldY = 0;
    //撃つかどうかのフラグ
    boolean shoot = false;

    //フレームカウンター
    int flameCount = 0;
    int flameCountDango = 0;
    int flameCountBee = 0;
    int flameCountSpiderChild = 0;
    int flameCountFly = 0;
    int flameCountKanabun = 0;
    int flameCountBeeWall = 0;
    //sting用フレームカウンタ
    int flameCountForSting = 0;

    //自機位置保存(2段階目に使用)
    int heroX;

    //フラフラ動くための向きのフラク
    int directionX = 1;
    int directionY = 1;
    //召喚敵の宣言
    Ant[] bossant = new Ant[10];
    Bee[] bossbee = new Bee[10];
    Dango[] bossdango = new Dango[10];
    SpiderChild[] bossspiderchild = new SpiderChild[10];
    Fly[] bossfly = new Fly[10];
    Kanabun[] bosskanabun = new Kanabun[1];
    Bee[] beewall = new Bee[30];

    //蜂の数
    int beeNumber = 0;
    //ダンゴムシを召喚した回数
    int dangoNumber = 0;
    int flyNumber = 0;
    int KanabunNumber = 0;

    public QueenBee() {
        hitpoint = HITPOINT;
        giveScore = 1;
        range = 15;
        int[] items = {0, 0, 0};
        this.items = items;

        img = new GreenfootImage("./images/queenBee.png");
        setImage(img);
    }

    
    
    //初期位置の記憶
    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
        oldX = getX();
        oldY = getY();
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            if (startBattle) {
                //位置情報の更新
                statusUpdate();
                switch (mode) {
                    case 0://登場直後
                        //行動開始点についたら状態切替
                        if (x >= MyWorld.WIDTH / 2 && y >= img.getHeight() + 150) {
                            //角度を直す
                            setRotation(0);
                            //自機一取得
                            heroX = Stage.heroX;
                            //状態更新
                            mode = 1;
                        } else {
                            //行動開始点へ移動
                            setLocation(x + (MyWorld.WIDTH / 2 - oldX) / 50, y + (MyWorld.HEIGHT / 2 + 150 - oldY) / 50);
                        }
                        break;
                    case 1:
                        queenBeeMove(1);
                        EnemyShot(1);
                        //体力が2/3を切ったら
                        if (hitpoint <= HITPOINT * 2 / 3) {
                            mode = 3;
                        } else {
                            //過去位置記憶の更新
                            oldX = x;
                            oldY = y;
                        }
                        break;
                    case 2:
                        //第二形態から第三携形態になるときに敵が少なくなるのは意図的なものではないですが仕様にしておきます
                        queenBeeMove(2);
                        EnemyShot(2);
                        BeeWallShot(2);
                        break;
                    case 3:
                        queenBeeMove(1);
                        EnemyShot(1);
                        BeeWallShot(1);
                        //体力が1/3を切ったら
                        if (hitpoint <= HITPOINT / 3) {
                            mode = 2;
                        } else {
                            //過去位置記憶の更新
                            oldX = x;
                            oldY = y;
                        }
                        break;
                }

                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                    //消滅
                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;
                }
            } //戦闘が始まっていなければ
            else {
                if (caution.finishCaution) {
                    img.setTransparency(255);
                    startBattle = true;
                }
            }
        }
    }
//壁蜂以外の敵を召喚するメソッド

    public void EnemyShot(int amount) {
        //フレームを数えて
        flameCount++;
        flameCountDango++;
        flameCountBee++;
        flameCountSpiderChild++;
        flameCountFly++;
        flameCountKanabun++;
        //ここからあり召喚
        //仕様書通りアリは3体並んできます
        if (flameCount == 200) {
            //敵召喚
            AntShot(1, amount);
        }
        if (flameCount == 220) {
            //敵召喚
            AntShot(1, amount);
        }
        if (flameCount == 240) {
            //敵召喚
            AntShot(1, amount);
        }
        if (flameCount == 440) {
            //敵召喚
            AntShot(2, amount);
        }
        if (flameCount == 460) {
            //敵召喚
            AntShot(2, amount);
        }
        if (flameCount == 480) {
            //敵召喚
            AntShot(2, amount);
        }
        if (flameCount == 680) {
            //敵召喚
            AntShot(3, amount);
        }
        if (flameCount == 700) {
            //敵召喚
            AntShot(3, amount);
        }
        if (flameCount == 720) {
            //敵召喚
            AntShot(3, amount);
            flameCount = 0;
        }
        //ここから蜂召喚
        if (amount == 1) {
            if (flameCountBee == 40 * (beeNumber + 1)) {
                //敵召喚
                BeeShot((beeNumber) % 2);
            }
        } else {
            if (flameCountBee == 20 * (beeNumber + 1)) {
                //敵召喚
                BeeShot((beeNumber) % 2);
            }
        }
        if (beeNumber == bossbee.length || flameCountBee >= 680) {
            beeNumber = 0;
            flameCountBee = 0;
        }
        //ここからダンゴムシ召喚
        if (flameCountDango == (200 * dangoNumber / amount) + 100) {
            //敵召喚
            DangoShot(dangoNumber % 2);
            dangoNumber++;
        }
        if (flameCountDango == 800) {
            dangoNumber = 0;
            flameCountDango = 0;
        }
        //ここから子供蜘蛛
        if (flameCountSpiderChild == 1000) {
            SpiderChildShot(0, amount);
        }
        if (flameCountSpiderChild == 2000) {
            SpiderChildShot(1, amount);
            flameCountSpiderChild = 0;
        }
        //ここからハエ
        if (flameCountFly == 100 / amount) {
            heroX = Stage.heroX;
            FlyShot();
            flyNumber++;
            flameCountFly = 0;
        }
        if (flyNumber == bossfly.length) {
            flyNumber = 0;
        }
        if (flameCountFly == 100 && amount == 2) {
            flameCountFly = 0;
        }
        //カナブン
        if (flameCountKanabun == 500 / amount) {
            heroX = Stage.heroX;
            KanabunShot();
            KanabunNumber++;
            flameCountKanabun = 0;
        }
        if (KanabunNumber == bosskanabun.length) {
            KanabunNumber = 0;
        }
        if (flameCountKanabun == 500 && amount == 2) {
            flameCountKanabun = 0;
        }
    }
//壁蜂召喚

    public void BeeWallShot(int amount) {
        flameCountBeeWall++;
        if (flameCountBeeWall == 500) {
            if (amount == 1) {
                for (int i = 1; i < 15 * amount; i++) {
                    beewall[i - 1] = new Bee();
                    getWorld().addObject(beewall[i - 1], x - 75 + i * 10, 1);
                }
            } else {
                for (int i = 1; i < 30; i++) {
                    beewall[i - 1] = new Bee();
                    getWorld().addObject(beewall[i - 1], x - 145 + i * 10, 1);
                }
            }
            flameCountBeeWall = 0;
        }
    }
//modeは蟻の行動パターン

    public void AntShot(int mode, int amount) {
        for (int i = 0; i < bossant.length; i++) {
            bossant[i] = new Ant(mode - 1, true);
        }
        switch (mode) {
            case 1:
                for (int i = 1; i <= 10 * amount; i++) {
                    getWorld().addObject(bossant[i - 1], (60 / amount * i) - 23, 1);
                }
                break;
            case 2:
                for (int i = 1; i <= 10 * amount; i++) {
                    getWorld().addObject(bossant[i - 1], 599, (30 / amount * i) - 15);
                }
                break;
            case 3:
                for (int i = 1; i <= 10 * amount; i++) {
                    getWorld().addObject(bossant[i - 1], 1, (30 / amount * i) - 15);
                }
                break;
            default:
                break;
        }
    }

    //startは出現位置、0なら左側、1なら右側
    //蜂は時間差で出現するようにするので3段階目の量2倍設定はenemyshotメソッドのほうで行う
    public void BeeShot(int start) {
        bossbee[beeNumber] = new Bee();
        switch (start) {
            case 0:
                getWorld().addObject(bossbee[beeNumber], 20, 1);
                break;
            case 1:
                getWorld().addObject(bossbee[beeNumber], 580, 1);
                break;
            default:
                break;
        }
        beeNumber++;
    }

    public void DangoShot(int start) {
        for (int i = 0; i < bossdango.length; i++) {
            bossdango[i] = new Dango();
        }
        switch (start) {
            case 0:
                for (int i = 1; i <= 10; i++) {
                    getWorld().addObject(bossdango[i - 1], (30 * i) - 20, 1);
                }
                break;
            case 1:
                for (int i = 1; i <= 10; i++) {
                    getWorld().addObject(bossdango[i - 1], (30 * i) + 280, 1);
                }
                break;
            default:
                break;
        }
    }

    public void SpiderChildShot(int start, int amount) {
        for (int i = 0; i < bossspiderchild.length; i++) {
            bossspiderchild[i] = new SpiderChild();
        }
        switch (start) {
            case 0:
                for (int i = 1; i <= 10 * amount; i++) {
                    getWorld().addObject(bossspiderchild[i - 1], 1, (30 / amount * i) - 15);
                }
                break;
            case 1:
                for (int i = 1; i <= 10 * amount; i++) {
                    getWorld().addObject(bossspiderchild[i - 1], 597, (30 / amount * i) - 15);
                }
                break;
            default:
                break;
        }
    }

    public void FlyShot() {
        bossfly[flyNumber] = new Fly();
        getWorld().addObject(bossfly[flyNumber], heroX, 1);
    }

    public void KanabunShot() {
        bosskanabun[0] = new Kanabun();
        getWorld().addObject(bosskanabun[0], heroX, 1);
    }
//aは第一第二段階は1,第三は2

    public void queenBeeMove(int a) {
        //常時する行動
        //ここから移動スクリプト
        //乱数を降って偶数なら、
        if (Greenfoot.getRandomNumber(10) % 2 == 0) {
            //横移動は右
            directionX = 1;
        } else {//奇数なら
            //横移動は左
            directionX = -1;
        }
        //右画面に近づいているときは必ず左に移動、左画面は右
        if (x >= 580) {
            directionX = -1;
        } else if (x <= 20) {
            directionX = 1;
        }
        //横の移動距離は*向き
        x = x + directionX * 3 * a;
        //乱数を振って偶数なら、
        if (Greenfoot.getRandomNumber(10) % 2 == 0) {
            //横移動は下
            directionY = 1;
        } else {//奇数なら
            //横移動は上
            directionY = -1;
        }
        //下画面に近づいているときは必ず上に移動、上画面はした
        if (y >= 380) {
            directionY = -1;
        } else if (y <= 20) {
            directionY = 1;
        }
        //縦の移動距離は*向き
        y = y + directionY * 3 * a;
        //位置の反映
        setLocation(x, y);
        //-------------------------//
    }
}
