
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage2 extends Stage {

    public Stage2() {
        super(2);
        if(MyWorld.haveItem){
            addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth()-10, 695 - 6 * 15);
            
        }
        //タイマースタート
            timer.mark();
        bgm = new GreenfootSound("./sounds/stage2.mp3");
        bossBgm = new GreenfootSound("./sounds/stage2_boss.mp3");
        bgm.setVolume(80);
        bossBgm.setVolume(80);
        bgm.playLoop();
        bgm.pause();
        ant = new Ant[40];
        dango = new Dango[15];
        bee = new Bee[10];
        kana = new Kanabun[10];
        boss = new QueenAnt();
    }

    public void snipAnt() {
        ant[0] = new Ant(3);
        ant[1] = new Ant(3);
        addObject(ant[0], 1 + Greenfoot.getRandomNumber(WIDTH - 1), 1);
        addObject(ant[1], Stage.WIDTH - Greenfoot.getRandomNumber(WIDTH - 1), 1);
    }

    public void kanabunRain() {
        kana[0] = new Kanabun();
        addObject(kana[0], Greenfoot.getRandomNumber(WIDTH - 1) + 1, 1);
    }

    public void act() {
        if (isFirst) {
            MyWorld.usingStory = new Story3();
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

        if (Stage.hideStageLogo && !Stage.inPause) {
            if (roundCount == 3) {
                bgm.stop();
                addObject(boss, WIDTH / 2, 1);
                roundCount++;
            } else if (roundCount == 4) {
            } else {
                if (flameCount % 75 == 0) {
                    snipAnt();
                }
                if (flameCount >= 610 && flameCount <= 710 && flameCount % 5 == 0) {
                    kanabunRain();
                }
                flameCount++;
                switch (flameCount) {
                    case 10:
                        for (int i = 0; i < dango.length; i++) {
                            dango[i] = new Dango();
                            addObject(dango[i], WIDTH / 15 * i, 1);
                        }
                        break;
                    case 410:
                        for (int i = 0; i < ant.length; i++) {
                            ant[i] = new Ant(0);
                            addObject(ant[i], (Stage.WIDTH / 30) * i + 1, 1);
                        }
                        break;
                    case 810:
                        for (int i = 0; i < bee.length; i++) {
                            bee[i] = new Bee(450 - 10 * i);
                            addObject(bee[i], ((WIDTH / 2) / bee.length * i) + 1, 1);
                        }
                        break;

                    case 910:
                        for (int i = 0; i < bee.length; i++) {
                            bee[i] = new Bee(450 - 10 * i);
                            addObject(bee[i], WIDTH - ((WIDTH / 2) / bee.length * i) + 1, 1);
                        }
                        break;
                    case 1200:
                        roundCount++;
                        flameCount = 0;
                        break;
                }
            }
        }

        //クリアフラグのチェックとクリア時の処理
        checkClear(MyWorld.usingStory);
    }
}
