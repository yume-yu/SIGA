
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;


public class Stage7 extends Stage {

    Random rand = new Random();

    public Stage7() {
        super(7);
        if (MyWorld.haveItem) {
            addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth() - 10, 695 - 6 * 15);
        }
        bgm = new GreenfootSound("./sounds/stage7.mp3");
        //ボス用bgm
        bgm.setVolume(80);
        bossBgm = new GreenfootSound("./sounds/stage7_boss.mp3");
        bossBgm.setVolume(50);
        //bossにはその面のボスを
        boss = new StagBeetle();
        ant = new Ant[40];
        bee = new Bee[10];
        dango = new Dango[30];
        kana = new Kanabun[20];
    }

    public void kanabunRain() {
        kana[0] = new Kanabun();
        addObject(kana[0], Greenfoot.getRandomNumber(WIDTH - 1) + 1, 1);
    }

    public void act() {

        if (isFirst) {
            MyWorld.usingStory = new Story8();
            isFirst = false;
            bgm.playLoop();
        }

        //テスト用クリアフラグ
        testKeys();

        //表示順位の更新
        resetPaintOrder();

        //スコアの更新
        updateScore();

        //ゲームオーバーの確認
        gameOver();
        //出現スクリプト
        //ロゴが消えていて、ポーズ中じゃなければ
        if (Stage.hideStageLogo && !Stage.inPause) {
            //周回が規定の数になったらボス出現(一周の長さによって周回数を変える)
            if (roundCount == 3) {
                //一度bgmを止める。Cautionの中にbgm切り替えのスクリプトがあるので、ここで再開させなくていい
                bgm.stop();
                //ボス出現
                addObject(boss, WIDTH / 2, 1);
                //周回数を増やす
                roundCount++;
            } //ボス出現中は出現を止めるので空にする
            else if (roundCount == 4) {
            } else {
                //出現はフレーム数で制御する
                flameCount++;
                //縦に並べたいときはこんな感じ。必ずSwitchの外でやる
                /*
                出現させるフレーム数の幅を決める
                if (flameCount >= 410 && flameCount <= 510) {
                    毎フレーム出ると多すぎるので、適当なタイミングを決める
                    例えばこの場合は、410フレームから510フレームの100フレームの間に10回呼び出すから、現れるのは10体
                    if (flameCount % 10 == 0) {
                        ant[0] = new Ant(0);
                        x軸をずらすと斜めに、ずらさないと縦一列に呼べる
                        addObject(ant[0], Greenfoot.getRandomNumber(WIDTH - 2) + 1, 1);
                    }
                }
                
                 */

                if (flameCount >= 0 && flameCount % 30 == 0 && flameCount < 400) {
                    int rnd = rand.nextInt(WIDTH);
                    ant[flameCount / 30] = new Ant(0);
                    addObject(ant[flameCount / 30], rnd, 1);
                }
                if (flameCount >= 510 && flameCount <= 610 && flameCount % 5 == 0) {
                    kanabunRain();
                }
                switch (flameCount) {
                    //初回出現は10フレーム目くらいがちょうどいいかも
                    case 10:
                        sdango1(1);
                        break;
                    case 30:
                        sdango1(2);
                        break;
                    case 50:
                        sdango1(3);
                        break;
                    case 70:
                        sdango1(4);
                        break;
                    case 90:
                        sdango1(5);
                        break;
                    case 110:
                        sdango1(6);
                        break;
                    case 130:
                        sdango1(7);
                        break;
                    case 150:
                        sdango1(8);
                        break;
                    case 170:
                        sdango1(9);
                        break;
                    case 220:
                        sdango2(10);
                        break;
                    case 240:
                        sdango2(11);
                        break;
                    case 260:
                        sdango2(12);
                        break;
                    case 280:
                        sdango2(13);
                        break;
                    case 300:
                        sdango2(14);
                        break;
                    case 320:
                        sdango2(15);
                        break;
                    case 340:
                        sdango2(16);
                        break;
                    case 360:
                        sdango2(17);
                        break;
                    case 380:
                        sdango2(18);
                        break;
                    case 400:
                        sdango2(19);
                        break;
                    case 500:
                        for (int i = 1; i < ant.length; i++) {
                            ant[i] = new Ant(0);
                            addObject(ant[i], WIDTH / 20 * i, 1);
                        }
                        break;
                    case 550:
                        for (int i = 1; i < bee.length; i++) {
                            bee[i] = new Bee(450 - Greenfoot.getRandomNumber(100));
                            addObject(bee[i], WIDTH / 10 * i, 1);
                        }
                        break;
                    case 750:
                        for (int i = 1; i < bee.length; i++) {
                            bee[i] = new Bee(450 - Greenfoot.getRandomNumber(100));
                            addObject(bee[i], WIDTH / 10 * i, 1);
                        }
                        break;
                    //頃合いを見て
                    case 810:
                        //周回数を1増やす
                        roundCount++;
                        //フレームをリセット
                        flameCount = 0;
                        break;
                }
            }
        }
        //クリアフラグのチェックとクリア時の処理
        checkClear(MyWorld.usingStory);

    }

    public void sdango1(int i) {
//配列長のループを回す

        dango[i] = new Dango();
        //x座標をずらしてaddすると横1列に並べられる。座標を0にして呼ぶとエラーを吐くんで注意
        //   addObject(dango[i], WIDTH  - WIDTH/ 10 * i, 1);
        addObject(dango[i], WIDTH / 10 * i, 1);
    }

    public void sdango2(int i) {
//配列長のループを回す
        dango[i] = new Dango();
        //x座標をずらしてaddすると横1列に並べられる。座標を0にして呼ぶとエラーを吐くんで注意
        addObject(dango[i], WIDTH - WIDTH / 10 * (i - 9), 1);
        //addObject(dango[i], WIDTH / 10 * i, 1);
    }

}
