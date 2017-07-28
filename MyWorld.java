
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World {

    //タイトル背景の初期化
    static final GreenfootImage title = new GreenfootImage("./images/titleback_L.png");
    static final GreenfootImage STORY_IMAGE = new GreenfootImage("./images/storyback.png");
    static final GreenfootImage item = new GreenfootImage("./images/item.png");

    //BGM
    GreenfootSound bgm = new GreenfootSound("./sounds/title_.mp3");
    //決定音
    static GreenfootSound button = new GreenfootSound("./sounds/decision.mp3");

    //各ステージの背景画像パス
    static final String stageBackImage1 = "./images/stage1st.png";
    static final String stageBackImage2 = "./images/stage2nd.jpg";
    static final String stageBackImage3 = "./images/stage3rd.jpg";
    static final String stageBackImage3_ = "./images/stage3rdRev.jpg";

    //各ステージの背景画像変数設定
    static final StageBackground s[][] = {
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage1
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage2
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage3
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage4
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage5
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage6
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage7
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage8
        {new StageBackground(stageBackImage1), new StageBackground(stageBackImage1)},//stage9
    };

    //ステージのカウンター
    static int stageNumber = -1;
    //-1ならタイトル,0ならプロローグ。1-9はそれぞれのステージ

    //スコアカウンター
    static int score = 0;//スコア本体
    static String scoreString = Integer.toString(score);//String型のスコア
    static String scoreBlank = "00000";//位取りのString変数

    static String str;
    //ウィンドウ自体の大きさ
    static final int WINDOW_SIZE = 800;

    //ゲーム画面の大きさ
    static final int WIDTH = 600, HEIGHT = 800;

    //
    static int heroX = WIDTH / 2;
    static int heroY = 3 * HEIGHT / 4;

    //ステージ開始時かどうかのフラグ。開始時1回だけの動作のために使う。
    static boolean startStage = false;

    //ステージクリアのフラグ。
    static boolean clearStage = false;

    //暗転完了のフラグ trueなら暗転完了
    static boolean waitStartStage = false;

    //明転終了のフラグ trueなら明転完了
    static boolean finishRemoveBlack = false;

    //アイテム所持フラグ
    static boolean haveItem = false;

    //サブタイトルロゴのインスタンスの生成
    static SubLogo subti = new SubLogo();

    //メインタイトルロゴのインスタンスの生成
    static Logo titleLogo;

    //"Click to Start"のインスタンスの生成
    static FlashingText clicktostart = new FlashingText("Press Enter to Start", 60);

    static Stage usingStage;
    static Story usingStory;

    //アイテム所持数表示のインスタンスの生成
    static DisplayImage itemDisplay = new DisplayImage(item, 30);

    //暗転オブジェクトインスタンス
    static Blackout blackout = new Blackout(WINDOW_SIZE, WINDOW_SIZE);
    static RemoveBlack removeBlack = new RemoveBlack(WINDOW_SIZE, WINDOW_SIZE);

    static SimpleTimer timer = new SimpleTimer();

    //コンストラクタ
    public MyWorld() {
        // 画面サイズの設定
        //super();
        super(800, 800, 1);

        resetBullets();
        resetCurtain();
        Greenfoot.setSpeed(50);
        //System.out.println(Greenfoot.getSpeed());
        //背景設定の配置
        setBackground(title);

        Cater.life = 6;
        Cater.score = 0;
        Stage.scoreUpdate = true;

        timer.mark();
        stageNumber = -1;
        //prologue = new Prologue();
        //ステージ開始時かどうかのフラグ。開始時1回だけの動作のために使う。
        startStage = false;

        //ステージクリアのフラグ。
        clearStage = false;

        //暗転完了のフラグ trueなら暗転完了
        waitStartStage = false;

        //明転終了のフラグ trueなら明転完了
        finishRemoveBlack = false;

        usingStory = new Story0();
        usingStage = new Stage1();

        bgm.playLoop();
        bgm.pause();
        //メインタイトルロゴの挿入
        titleLogo = new Logo("けむしきんぐ", 60);
        addObject(titleLogo, WINDOW_SIZE / 2, -titleLogo.titleLogo.getHeight() / 2);
        addObject(removeBlack, WINDOW_SIZE / 2, WINDOW_SIZE / 2);
        setPaintOrder(Blackout.class, RemoveBlack.class);
        haveItem = false;
    }

    //コマンドラインへのコメント
    private void commentToDev() {
        System.out.println("Welcome to Kemushi King\n"
                + "press \"r\" to reset the game\n"
                + "In story,press \"→\" to skip\n"
                + "In stage,press \"_\" to next stage.\n");
    }

    //タイトル画面へ戻るためのメソッド
    public void reset() {
        //全オブジェクト削除
        removeObjects(getObjects(null));
        //オブジェクトの優先順位の初期化
        setPaintOrder(null);

        //必要パラメータの初期化
        startStage = false;
        finishRemoveBlack = false;
        clearStage = false;
        waitStartStage = false;
        stageNumber = -1;
        score = 0;
        Cater.speed = 4;
        scoreString = Integer.toString(score);
        scoreBlank = "00000";

        setBackground(title);
        titleLogo = new Logo("けむしきんぐ", 60);
        titleLogo.transportLevel = 255;
        addObject(titleLogo, WINDOW_SIZE / 2, WINDOW_SIZE / 2 - 100);
        showText("", WINDOW_SIZE / 2, WINDOW_SIZE / 2);
        //prologue = new Prologue();
        //prologue.stopLine = false;
        heroX = WIDTH / 2;
        heroY = 3 * HEIGHT / 4;
        System.out.println("Welcome to Kemushi King\n"
                + "press \"r\" to reset the game\n"
                + "In story,press \"→\" to skip\n"
                + "In stage,press \"_\" to next stage.\n");

    }

    static public void resetBullets() {
        Bullet bullets[] = {new Bullet(), new Bullet(), new Bullet(), new Bullet(), new Bullet(),
            new Bullet(),
            new Bullet(),
            new Bullet(),
            new Bullet()};
        Stage.bullets = bullets;

        for (int i = 0; i < Stage.bullets.length; i++) {
            Stage.bulletsClassName[i] = Stage.bullets[i].toString();
        }
    }

    static public void resetCurtain() {
        blackout = new Blackout(WINDOW_SIZE, WINDOW_SIZE);
        removeBlack = new RemoveBlack(WINDOW_SIZE, WINDOW_SIZE);
    }

    //存在しないステージが指定されたときのエラーメッセージ
    public void errorStage() {
        showText("存在しないステージが指定されています。\n'r'キーを推してリセットしてください。", WINDOW_SIZE / 2, WINDOW_SIZE / 2);
    }

    public void act() {
        str = Greenfoot.getKey();

        try {
            bgm.setVolume((int) (((double) 80 / 255) * (255 - blackout.transparency)));
        } catch (Exception e) {
        }
        //ステージナンバーによってそれぞれの処理をする
        switch (stageNumber) {
            //-1 = タイトル画面のとき
            case -1:
                try {
                    //Enterが押されたら
                    if (str.equals("enter")) {
                        button.play();
                        removeObjects(getObjects(null));
//                        //サブタイトルロゴを除去
//                        removeObject(subti);
//                        //タイトルロゴを除去
//                        removeObject(titleLogo);
//                        //点滅してるやつを除去
//                        removeObject(clicktostart);
                        //暗転オブジェクトを追加
                        System.out.println(timer.millisElapsed()/1000);
                        addObject(blackout, WINDOW_SIZE / 2, WINDOW_SIZE / 2);
                        stageNumber = -2;
                    }
                } catch (Exception e) {

                }
                //タイトルロゴの処理が済んだら
                if (startStage) {
                    //開始処理フラグをfalseに
                    startStage = false;
                    //サブタイトルオブジェクトを配置
                    addObject(subti, WINDOW_SIZE / 2, WINDOW_SIZE / 2 - 50);
                    //点滅してるやつオブジェクトを配置
                    addObject(clicktostart, WINDOW_SIZE / 2, 3 * WINDOW_SIZE / 4);
                    bgm.playLoop();
                }
                break;
            // 0 = ストーリー画面のとき
            case 0:
                //最初のストーリーを呼ぶ
                Greenfoot.setWorld(usingStory);
                break;
            //上のどれでもなければエラーへ
            default:

                break;
        }

        //画面暗転処理完了フラグをみて、暗転完了していたら
        if (waitStartStage) {
            //存在するすべてのオブジェクトを除去
            removeObjects(getObjects(null));
            bgm.stop();
            Greenfoot.setWorld(usingStory);
            //フラグをfalseに
            waitStartStage = !waitStartStage;

            //ステージナンバーを1つ進める
            stageNumber = 0;
            //ステージ開始時処理フラグをtrueに
            //startStage = true;
        }

        //リセットボタン
        if (Greenfoot.isKeyDown("r")) {
            usingStage = new Stage4();
            Greenfoot.setWorld(usingStage);

        }
    }

    @Override
    public void stopped() {
        bgm.pause();
    }

}
