import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.ArrayList;
import java.util.List;

//各ステージのベースになるクラス
public class Stage extends World{
    
    //ステージサイズの記憶
    static final int WIDTH = MyWorld.WIDTH;
    static final int HEIGHT = MyWorld.HEIGHT;
    
    //クリアフラグ。指定の仕方的にはMyWorldを使ったほうがいいのかも...?
    static boolean clearStage = false;
    //ステージのロゴが消えたかどうかのフラグ
    static boolean hideStageLogo;
    
    //スコア更新を行うかどうかのフラグ。基本はfalseでtrueのとき更新作業をしてfalseにする
    static boolean scoreUpdate = false;
    
    //greenfoot.getKey()からの入力を記録する変数。使う時に逐次参照すると値が変わるので、ここで取得したものを外部から参照する
    static String str;
    
    //ポーズ中かどうかのフラグで、基本はfalse。すべてのオブジェクトはこれがtureのときはact()内が読まれないようにしないといけない
    static boolean inPause = false;
    
    //GameOverかどうかのフラグ
    static boolean isGameOver = false;
    
    //以下はよく考えたらMyWorldでは使わないのにMyWorldにあるのはおかしいものたち
    //弾丸のインスタンスの生成
    static Bullet[] bullets = {new Bullet(), 
        new Bullet(), 
        new Bullet(), 
        new Bullet(), 
        new Bullet(), 
        new Bullet(), 
        new Bullet(), 
        new Bullet(), 
        new Bullet()};
    
    //弾丸のクラス名配列の設定
    static String[] bulletsClassName = new String[bullets.length];
    
    //自機クラスの生成
    CaterOuter outer = new CaterOuter();
    
    //ステージ番号ロゴオブジェクトのインスタンス生成
    static StageLogo logo;
    
    //右側ステータスの背景+固定ステータスインスタンスの生成
    static SideStatus status;
    
    //キー操作説明
    static SideStatus buttonStatus;
    
    //スコアディスプレイインスタンスの生成
    static Display scoreDisplay = new Display();
    
    //スコアを文字列に変換したモノの格納先と、位取り用の0の格納先
    static String scoreString = Integer.toString(Cater.score);//String型のスコア
    static String scoreBlank = "00000";//位取りのString変数
    
    static MessageWindow pose = new MessageWindow(MyWorld.WINDOW_SIZE, MyWorld.WINDOW_SIZE, 50);
    static PauseText poseText = new PauseText();
    static GameOverText gameOver = new GameOverText();
    
    List list = new ArrayList();
    
    SimpleTimer timer = new SimpleTimer();
    
    //自機の位置記憶
    static int heroX = 0;
    static int heroY = 0;
    
    String bossName;
    
    //bgm
    GreenfootSound bgm;
    GreenfootSound bossBgm;
    
    //敵オブジェクトの配列
    /*必要な数だけ確保 -> 使う の方式をとるから。
    いちいちnewすると、その分だけメモリを食べる*/
    Enemies ant[];
    Enemies dango[];
    Enemies bee[];
    SpiderChild spi[];
    Enemies kana[];
    Enemies fly[];
    Boss boss;
    
    //フレームカウント
    int flameCount;
    
    //周回カウント
    int roundCount;
    
    //｢初めて動いた｣ときかどうかを判断するフラグ
    boolean isFirst;
    
    //クリア表示中か
    boolean inClearView;
    
    Story nextStory;
    
    public Stage(int stageNum){
        super(MyWorld.WINDOW_SIZE, MyWorld.WINDOW_SIZE, 1); 
        MyWorld.resetCurtain();
        
        //変数初期化準備
        scoreDisplay = new Display();
        //自機クラスの生成
        CaterOuter outer = new CaterOuter();
        //タイマー
        timer = new SimpleTimer();
        buttonStatus = new SideStatus("", 0);
        //スコアアップデート判定
        scoreUpdate = true;
        
        inPause = false;
        isGameOver = false;
        clearStage = false;
        hideStageLogo = false;
        flameCount = 0;
        roundCount = 0;
        isFirst = true;
        inClearView = false;
        
        StageBackground s[] = MyWorld.s[stageNum - 1];
        for(int i = 0;i<s.length;i++){
                addObject(MyWorld.s[stageNum - 1][i],WIDTH/2-1,i*HEIGHT/2);//背景オブジェクトの配置
        }
        
        
        //右側ステータスのベースオブジェクトの初期化と配置
        status = new SideStatus("Stage : " + stageNum,30);
        addObject(status, 700, HEIGHT / 2);
        
        //スコアオブジェクトの配置
        addObject(scoreDisplay,WIDTH + scoreDisplay.titleLogo.getWidth()/2+10, new GreenfootImage("Stage : 0",30,Color.WHITE,Color.WHITE).getHeight() + scoreDisplay.titleLogo.getHeight() + 10);
        
        //ボタン操作オブジェクト
        //addObject(buttonStatus, 700, HEIGHT / 2);
        //buttonStatus.setGaming();
        
        //順位の設定
        //setPaintOrder(scoreDisplay.getClass(),status.getClass());
        resetPaintOrder();

        
        //自機の配置
        //outer = new CaterOuter();
        addObject(outer, Cater.removedX, Cater.removedY);

        //右端ボーダーの設置
        //addObject(new Border(), 601, HEIGHT/2);
        logo = new StageLogo(stageNum);
        
        //ステージロゴ(最初に出るやつ)オブジェクトの配置
        addObject(logo, WIDTH/2, HEIGHT/2);
        //startStage = false; //開始時処理フラグをfalseにする
        //明転オブジェクトの配置
        addObject(MyWorld.removeBlack, MyWorld.WINDOW_SIZE/2, MyWorld.WINDOW_SIZE/2);
    }
   
    public void resetPaintOrder(){
        setPaintOrder(RemoveBlack.class,
                Blackout.class,
                ClearStageEffect.class,
                PauseText.class,
                GameOverText.class,
                FlashingText.class,
                MessageWindow.class,
                Display.class,
                DisplayImage.class,
                DisplayHitpoint.class,
                SideStatus.class,
                Caution.class,
                Cater.class,
                CaterOuter.class,
                BulletEnhanced.class,
                Spider.class);
    }
    
    public void act(){
        
        //スコアの更新
        updateScore();
        
        
        
        //テスト用クリアフラグ
        testKeys();
        
        //クリアフラグのチェックとクリア時の処理
        checkClear(new Stage1());
    }
    
    //クリア時のチェックと処理
    public void checkClear(World world){ 
        if(MyWorld.clearStage){
            //暗転オブジェクトの配置
            addObject(MyWorld.blackout, MyWorld.WINDOW_SIZE/2,MyWorld.WINDOW_SIZE/2);
            MyWorld.clearStage = false;
        }
        //暗転完了の確認
        if(MyWorld.waitStartStage){
            //bgm停止
            try{
            bgm.stop();
        }catch(Exception e){
        }
            Cater.beamSound.stop();
            //自機オブジェクトを削除
            removeObject(null);
            //ステージ終了時処理フラグをfalseに
            MyWorld.waitStartStage = false;
            //ステージ終了時処理フラグをfalseに
            MyWorld.finishRemoveBlack = false;
            //次ステージの呼び出し
            Greenfoot.setWorld(world);
            //自機の発射間隔をリセット
            Cater.cnt = 0;
        }
    }
    
    //テスト用クリアフラグ
    public void testKeys(){
        //全クラスで使う関数の入力処理の更新
        str = Greenfoot.getKey();
        if(Greenfoot.isKeyDown("0") && !gameOver.inGameOver){
            MyWorld.clearStage = true;
        }else if(Greenfoot.isKeyDown("u")){
            Cater.score++;
            scoreUpdate = true;
        }
 
        try{      
            if(str.equals("space") && !gameOver.inGameOver && !inClearView){
                addObject(pose, MyWorld.WINDOW_SIZE/2, MyWorld.WINDOW_SIZE/2);
                addObject(poseText, (WIDTH)/2, (HEIGHT)/2);
                inPause = true;
            }
        }
        catch(Exception e){
//            System.out.println("すとっぷ！できない...");
        }
    }
    
    //スコア更新メソッド
    public void updateScore(){
        if(scoreUpdate){
            //スコアの値をString型に変換
            scoreString = Integer.toString(Cater.score);
            //スコアの桁数によってブランクの数を調整
            switch(scoreString.length()){
                case 1:
                    scoreBlank = "00000";
                    break;
                case 2:
                    scoreBlank = "0000";
                    break;
                case 3:
                    scoreBlank = "000";
                    break;
                case 4:
                    scoreBlank = "00";
                    break;
                case 5:
                    scoreBlank = "0";
                    break;
                case 6:
                    scoreBlank = "";
                    break;   
            }
            scoreUpdate = false;
            Display.ifScore = true;
        }
    }
    
    public void gameOver(){
        if(isGameOver){
            addObject(pose, MyWorld.WINDOW_SIZE/2, MyWorld.WINDOW_SIZE/2);
            gameOver = new GameOverText();
            addObject(gameOver, (WIDTH)/2, 0);
            inPause = true;
            isGameOver = false;
        }
    }

    @Override
    public void stopped() {
        bgm.pause();
    }

    @Override
    public void started() {
        bgm.playLoop();
    }
    
    
    
   
}
