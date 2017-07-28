
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage5 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Stage5 extends Stage {

    /**
     * Constructor for objects of class Stage5.
     *
     */
    public Stage5() {
        super(5);
        if(MyWorld.haveItem){
            addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth()-10, 695 - 6 * 15);   
        }
        boss = new Beatle();
        bgm = new GreenfootSound("./sounds/stage5.mp3");
        bossBgm = new GreenfootSound("./sounds/stage5_boss.mp3");
        bgm.playLoop();
        bgm.pause();
        dango = new Dango[30];
        spi = new SpiderChild[2];
        kana = new Kanabun[3];

    }

    @Override
    public void resetPaintOrder() {
        setPaintOrder(RemoveBlack.class,
                Blackout.class,
                ClearStageEffect.class,
                PauseText.class,
                GameOverText.class,
                MessageWindow.class,
                Display.class,
                DisplayImage.class,
                DisplayHitpoint.class,
                SideStatus.class,
                Caution.class,
                Cater.class,
                CaterOuter.class,
                Beatle.class,
                EnemyBulletForStag.class,
                EnemyBullet.class,
                BulletEnhanced.class,
                Spider.class);
    }

    public void act() {
        if (isFirst) {
            MyWorld.usingStory = new Story6();
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
                if (flameCount % 600 == 0 && flameCount != 0) {
                    for (int i = 0; i < spi.length; i++) {
                        spi[i] = new SpiderChild();
                        if (i % 2 == 0) {
                            addObject(spi[i], 2, 5 + spi[i].img.getWidth());
                        } else {
                            addObject(spi[i], WIDTH - 3, 5 + spi[i].img.getWidth());
                        }
                    }
                }
                if (flameCount % 200 == 0 && flameCount != 0) {
                    int random = Greenfoot.getRandomNumber(10) + 10;
                    for (int i = 0; i < kana.length; i++) {
                        kana[i] = new Kanabun();
                        switch (i) {
                            case 0:
                                addObject(kana[i], Stage.heroX, 1);
                                break;
                            case 1:
                                System.out.println("ここ");
                                addObject(kana[i], Stage.heroX + random * 5, 1);
                                break;
                            case 2:
                                addObject(kana[i], Stage.heroX - random * 5, 1);
                                break;
                        }
                    }
                }
                switch (flameCount) {
                    case 10:
                    case 210:
                    case 410:
                    case 610:
                    case 810:
                    case 1010:

                        for (int i = 0; i < dango.length; i++) {
                            dango[i] = new Dango();
                            dango[i].hitpoint = 3;
                            addObject(dango[i], dango[i].img.getWidth() / 2 + WIDTH / (dango.length) * i, 1);
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
