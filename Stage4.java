
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage4 extends Stage {

    /**
     * Constructor for objects of class Stage4.
     *
     */
    public Stage4() {
        super(4);
        if(MyWorld.haveItem){
            addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth()-10, 695 - 6 * 15);
        }
        boss = new Spider();
        bgm = new GreenfootSound("./sounds/stage4.mp3");
        bossBgm = new GreenfootSound("./sounds/stage4_boss.mp3");
        bgm.playLoop();
        bgm.pause();
        spi = new SpiderChild[20];
        fly = new Fly[10];
        bee = new Bee[10];

    }

    public void act() {

        if (isFirst) {
            MyWorld.usingStory = new Story5();
            isFirst = false;
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
                if (flameCount >= 20 && flameCount <= 220 && flameCount % 20 == 0) {
                    spi[0] = new SpiderChild();
                    addObject(spi[0], 1, Greenfoot.getRandomNumber(HEIGHT / 4 - 1) + 1);
                    spi[0].shotAtX = WIDTH - (WIDTH / 200) * (flameCount - 20);
                }

                if (flameCount >= 520 && flameCount <= 720 && flameCount % 20 == 0) {
                    spi[0] = new SpiderChild();
                    addObject(spi[0], WIDTH - 2, Greenfoot.getRandomNumber(HEIGHT / 4 - 1) + 1);
                    spi[0].shotAtX = (WIDTH / 200) * (flameCount - 520);
                }
                if (flameCount >= 130 && flameCount <= 730 && flameCount % 30 == 0) {
                    fly[0] = new Fly();
                    fly[0].hitpoint = 1;
                    addObject(fly[0], Greenfoot.getRandomNumber(WIDTH - 1) + 1, 1);
                    //spi[0].shotAtX = (WIDTH / 400) * (flameCount - 520);
                }
                switch (flameCount) {
                    case 500:
                        for (int i = 0; i < bee.length; i++) {
                            bee[i] = new Bee(350);
                            addObject(bee[i], WIDTH - (WIDTH/2 / bee.length - 1) * i, 1);
                        }
                        break;
                    case 800:
                        for (int i = 0; i < bee.length; i++) {
                            bee[i] = new Bee(350);
                            addObject(bee[i], (WIDTH/ 2 / bee.length - 1) * i, 1);
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
