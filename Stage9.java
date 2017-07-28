    import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Stage9 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Stage9 extends Stage
{

    /**
     * Constructor for objects of class Stage9.
     * 
     */
    public Stage9(){
        super(9);
    }
    
    public void act(){
        if(isFirst){
            MyWorld.usingStory = new Story10();
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
