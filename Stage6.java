import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage6 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage6 extends Stage
{

    public Stage6(){
        super(6);
    }
    
    public void act(){
        
        if(isFirst){
            MyWorld.usingStory = new Story7();
            isFirst = false;
        }
                   
        //テスト用クリアフラグ
        testKeys();
        
        //スコアの更新
        updateScore();
        
        //クリアフラグのチェックとクリア時の処理
        checkClear(MyWorld.usingStory);
    }
}
