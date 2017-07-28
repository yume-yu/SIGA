
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage3 extends Stage {

    /**
     * Constructor for objects of class Stage3.
     *
     */
    public Stage3() {
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(3);
        if(MyWorld.haveItem){
            addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth()-10, 695 - 6 * 15);   
        }
        bgm = new GreenfootSound("./sounds/stage3.mp3");
        bossBgm = new GreenfootSound("./sounds/stage3_boss.mp3");
        bgm.playLoop();
        bgm.pause();
        boss = new Mantis();
        ant = new Ant[20];
        bee = new Bee[15];
        dango = new Dango[30];
        spi = new SpiderChild[10];

    }

    public void act() {
        if (isFirst) {
            MyWorld.usingStory = new Story4();
            isFirst = false;
            //タイマースタート
            timer.mark();
            bgm.playLoop();
        }

        //表示順位の更新
        resetPaintOrder();

        //テスト用クリアフラグ
        testKeys();

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
                flameCount++;
                if (flameCount >= 410 && flameCount <= 510) {
                    if (flameCount % 10 == 0) {
                        ant[0] = new Ant(0);
                        addObject(ant[0], Greenfoot.getRandomNumber(WIDTH - 2) + 1, 1);
                    }
                }
                if (flameCount >= 760 && flameCount <= 860) {
                    if (flameCount % 10 == 0) {
                        spi[0] = new SpiderChild();
                        if ((flameCount / 10) % 2 == 0) {
                            addObject(spi[0], 1, Greenfoot.getRandomNumber(HEIGHT/2-1) + 1);
                        } else {
                            addObject(spi[0], WIDTH - 3, Greenfoot.getRandomNumber(HEIGHT/2-1) + 1);
                        }
                    }
                }
                switch (flameCount) {
                    case 10:
                        for (int i = 0; i < dango.length; i++) {
                            dango[i] = new Dango();
                            dango[i].hitpoint = 3;
                            if (i % 2 == 0) {
                                addObject(dango[i], WIDTH / 30 * i + 1, 1);
                            } else {
                                addObject(dango[i], WIDTH / 30 * i + 1, dango[0].img.getHeight() + 50);
                            }
                        }
                        break;
                    case 410:
                        for (int i = 1; i < ant.length; i++) {
                            ant[i] = new Ant(0);
                            addObject(ant[i], WIDTH / 20 * i, 1);
                        }
                        break;
                    case 560:
                        for (int i = 0; i < bee.length; i++) {
                            bee[i] = new Bee(Greenfoot.getRandomNumber(450) + 101);
                            addObject(bee[i], WIDTH / bee.length * i + 1, 1);
                        }
                        break;
                    case 760:
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
