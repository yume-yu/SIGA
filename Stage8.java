import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage8 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage8 extends Stage
{

    public Stage8(){
        super(8);
        if(MyWorld.haveItem){
            addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth()-10, 695 - 6 * 15);
        }
        boss = new QueenBee();
        bgm = new GreenfootSound("./sounds/stage8.mp3");
        //ボス用bgm
        bgm.setVolume(80);
        bossBgm = new GreenfootSound("./sounds/stage8_boss.mp3");
        bossBgm.setVolume(50);
    }
    
    public void act(){
        
        if(isFirst){
            MyWorld.usingStory = new Story9();
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
                addObject(boss, WIDTH / 2 + 100, 1);
                roundCount++;
            } else if (roundCount == 4) {
            } else {
                flameCount++;
                switch (flameCount) {
//                    case 500:
//                        for (int i = 0; i < bee.length; i++) {
//                            bee[i] = new Bee(350);
//                            addObject(bee[i], WIDTH - (WIDTH/2 / bee.length - 1) * i, 1);
//                        }
//                        break;
//                    case 800:
//                        for (int i = 0; i < bee.length; i++) {
//                            bee[i] = new Bee(350);
//                            addObject(bee[i], (WIDTH/ 2 / bee.length - 1) * i, 1);
//                        }
//                        break;
                    case 100:
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
