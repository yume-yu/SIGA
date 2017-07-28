
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage1 extends Stage {

    public Stage1() {
        //引数はステージ番号
        super(1);

        /* ここで各変数を初期化する 配列の大きさは横一列で召喚するときのマックスにしておくと無駄が少ない*/
        //通常bgm
        bgm = new GreenfootSound("./sounds/stage1.mp3");
        //ボス用bgm
        bossBgm = new GreenfootSound("./sounds/stage1_boss.mp3");
        //bossにはその面のボスを
        boss = new Butterfly();
        ant = new Ant[20];
        bee = new Bee[10];
        dango = new Dango[10];

    }

    public void act() {
        //初回のループだけ
        if (isFirst) {
            //次のステージの初期化
            MyWorld.usingStory = new Story2();
            isFirst = false;
            //タイマースタート
            timer.mark();
            //bgmの開始
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
                switch (flameCount) {
                    //初回出現は10フレーム目くらいがちょうどいいかも
                    case 10:
                        //配列長のループを回す
                        for (int i = 1; i < dango.length; i++) {
                            //使うインスタンスを初期化
                            dango[i] = new Dango();
                            //x座標をずらしてaddすると横1列に並べられる。座標を0にして呼ぶとエラーを吐くんで注意
                            addObject(dango[i], WIDTH / 10 * i, 1);
                            
                        }
                        break;
                    case 410:
                        for (int i = 1; i < ant.length; i++) {
                            ant[i] = new Ant(0);
                            addObject(ant[i], WIDTH / 20 * i, 1);
                        }
                        break;
                    case 610:
                        for (int i = 1; i < bee.length; i++) {
                            bee[i] = new Bee();
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
}
