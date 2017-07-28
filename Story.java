
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Story extends World {

    //すべてのストーリー文章
    static final String STORY[][] = {{"辺境の島にある広大な森 クルエル森林。",
        "ここは多種多様な虫が、互いの村や国と",
        "いったテリトリーを守りながら生きてい",
        "る、平和な森▼",
        "しかし、スズメバチ王国の新たな女王蜂",
        "が決まった日を境に、王国が率いる軍団",
        "が他種の虫の棲むテリトリーの侵攻を始",
        "めた▼",
        "王国は他種の強力な虫を勧誘することで",
        "戦力をより強大なものにしていき、クル",
        "エル森林全体の支配も目前となった▼",
        "森林の外れにあるケムシ村もまた、王国",
        "の脅威に晒されていた。クモが指揮する",
        "スズメバチ軍団により村は半壊、多くの",
        "ケムシやガが傷ついてしまった▼",
        "そんな中、ケムシの子供のオルガは母親",
        "をクモに連れ去られてしまう▼",
        "彼女がクモに連れ去られてしまった...",
        "悔しいが、今は助けに行けるものが誰も",
        "おらぬ…。▼",
        "なら僕がお母さんを助けに行くよ▼",
        "おまえひとりだけで大丈夫なのか?▼",
        "大丈夫。",
        "僕はケムシの中じゃお父さん譲りで1番",
        "強いんだから。▼",
        "...わかった。",
        "儂も戦い方のサポートはするがくれぐ",
        "れも気をつけるんじゃぞ。▼"

    },
    {
        "待ちなさい。▼" ,//0
        "チョウ！▼" ,//1
        "なんでも、ひとりでクモの棲む大樹に行",//2
        "くそうですね。▼" ,
        "そうです。僕はお母さんを助けに…▼" ,//4
        "なりません。今すぐ村に帰りなさい。▼" ,//5
        "なぜですか。あなたが僕を止める理由は",//6
        "無いはずだ。▼" ,
        "あなたの為に言っているのです。まだケ",//8
        "ムシのあなたが行っても、あなたの母親",
        "を助けるどころか、返り討ちにあうだけ",
        "です。▼" ,
        "僕はお父さん譲りで強いし、長老からも",//12
        "許しは得てます。▼" ,
        "長老が許しても、私は許しません。あな",//14
        "たはスズメバチ軍団の真の恐ろしさを知",
        "らないのですか。▼" ,
        "スズメバチは単体でも十分強いのに、群",//17
        "れをなして攻撃してくる。▼" ,
        "加えて、他の種の虫まで率いています。",//19
        "私たちの町もその実力の前に壊されてし",
        "まいました。▼" ,
        "近しい種の子供が、みすみす痛めつけら",//22
        "れに行く様を見過ごすわけにはいかない",
        "のです。▼" ,
        "たしかに、あの時僕はガ達に守られるだ",//25
        "けで戦えなかったから、スズメバチ軍団",
        "の実力はまだ知らない。それでも、僕は",
        "行きます。▼" ,
        "ならば、私と私の仲間たちがあなたを止",//29
        "めます。▼" ,
        "(チョウやみんなと戦うなんて...▼" ,//31
        "でも、ケムシ村は誰も戦えないほど攻撃",
        "されたのに、なんでチョウは僕を止める",
        "ほどの余力があるんだ。)▼"
    },
    {
        "そこのケムシ。▼",
        "え、何。▼",
        "ここは女王アリ様のテリトリーだぞ。こ",
        "こで何をしている。▼",
        "僕はケムシ村のオルガです。僕は今、",
        "クモの棲む大樹に向かっています。▼",
        "そこまでの近道になるここを選んで進ん",
        "できましたが、アリのテリトリーとは知",
        "りませんでした。▼",
        "無断に入ったことは謝ります。望まれる",
        "なら回り道を…▼",
        "待て、クモの大樹で何をするつもりだ▼",
        "なぜそんなことまで？▼",
        "我らが女王アリ様とクモは、スズメバチ",
        "王国との同盟を結んでいる。つまり、ク",
        "モは我々と共に戦う同志である。▼",
        "よって、我々はお前の目的を聞かずに見",
        "逃すわけにはいかないのだ。▼",
        "え……え？▼",
        "どうした、何を狼狽えている。▼",
        "いや……なんでもないですヨ？▼",
        "(そうだったんだ……。",
        "でも、今までアリがスズメバチと一緒に",
        "戦ったなんて話、聞いたことないぞ）▼",
        "無駄口を叩かず、さっさと言え！",
        "大樹で何をするつもりだ！▼",
        "それは……▼",
        "それは？▼",
        "僕は……▼",
        "僕は？▼",
        "……▼",
        "……▼",
        "……▼",
        "……よし、ひっ捕らえ▼",
        " ▼",
        "失礼しまーす！▼",
        "曲者、曲者だ！▼",
        "出合え！出合え！▼"

    },
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"},
    {"hoge▼"}};

    //getkey()の格納先
    static String str;

    //そのクラスで使うストーリーの格納先
    String readStory[];
    //表示している文章の画像
    GreenfootImage[] storyLine;
    //いま表示している文章or表示し終わった文章の格納先
    String showingStory[];
    //話してる時になる音
    GreenfootSound talk = new GreenfootSound("./sounds/talk_hi.mp3");

    //表示速度調整のフレームカウント
    int flameCount;
    //改行フレーム数
    int returnFlame;
    //読み込んでいる文字数カウント
    int charaCount;
    //読み込んでいる行数カウント
    int lineCount;
    //表示している行数カウント
    int displayLineCount;
    //入力待ちかどうかのフラグ
    boolean stopLine;
    //ステージ終了フラグ
    boolean exitThis;
    //背景
    GreenfootImage background;
    //テキストを表示するオブジェクト
    DisplayImage texts;
    //人物名を表示するオブジェクト
    DisplayImage nameDisplay;
    //文字の大きさ
    static final int TEXT_SIZE = 45;
    static final int FLASHTEXT_SIZE = 45;
    //テキスト表示オブジェクトに渡す画像
    GreenfootImage passImg;
    //メッセージウィンドウ
    MessageWindow mes;
    //人物名ウィンドウ
    MessageWindow name;
    //Press Enter文字列
    static final String PRESS_ENTER = "Press Enter";
    //pressEnterを表示するオブジェクト
    FlashingText flashPressEnter;

    //次の世界
    World nextWorld;

    //｢初めて動いた｣ときかどうかを判断するフラグ
    boolean isFirst;

    /* 使う色の設定 */
    static final Color transport = new Color(0, 0, 0, 0);
    static final Color white = new Color(255, 255, 255, 255);
    static final Color rightGray = new Color(125, 125, 125, 255);

    public Story(int stageNum, GreenfootImage img) {
        super(MyWorld.WINDOW_SIZE, MyWorld.WINDOW_SIZE, 1);
        MyWorld.resetCurtain();
        //背景の設定
        background = img;
        setBackground(background);

        //使用する関数の初期化
        readStory = STORY[stageNum];
        showingStory = new String[readStory.length];
        storyLine = new GreenfootImage[readStory.length];
        flameCount = 0;
        returnFlame = 5;
        charaCount = 0;
        lineCount = 0;
        displayLineCount = 0;
        stopLine = false;
        exitThis = false;
        isFirst = true;
        flashPressEnter = new FlashingText(PRESS_ENTER, FLASHTEXT_SIZE);
        mes = new MessageWindow(MyWorld.WINDOW_SIZE - 100, 2 * MyWorld.WINDOW_SIZE / 7);
        passImg = new GreenfootImage(MyWorld.WINDOW_SIZE - 100, 2 * MyWorld.WINDOW_SIZE / 7);
        texts = new DisplayImage(passImg, 0);
        name = new MessageWindow(passImg.getWidth() / 4, passImg.getHeight() / 5);

        for (int i = 0; i < showingStory.length; i++) {
            //showingStory[i] = STORY[stageNum][i].substring(0,1);
        }

        addObject(mes, MyWorld.WINDOW_SIZE / 2, MyWorld.WINDOW_SIZE - mes.box.getHeight() / 2 - 50);
        addObject(texts, MyWorld.WINDOW_SIZE / 2, MyWorld.WINDOW_SIZE - mes.box.getHeight() / 2 - 50);
        //明転オブジェクトの配置
        addObject(MyWorld.removeBlack, MyWorld.WINDOW_SIZE / 2, MyWorld.WINDOW_SIZE / 2);
        setPaintOrder(RemoveBlack.class, Blackout.class,DisplayImage.class,MessageWindow.class,DisplayCharacter.class);
    }

    public void progress() {
        if (MyWorld.finishRemoveBlack) {
            if (stopLine) {
                talk.stop();
            }
            try {

                if (stopLine && str.equals("enter")) {
                    removeObject(flashPressEnter);
                    returnFlame = 5;
                    flameCount = 0;
                    passImg = new GreenfootImage(MyWorld.WINDOW_SIZE - 100, 2 * MyWorld.WINDOW_SIZE / 7);
                    removeObject(texts);
                    texts = new DisplayImage(passImg, 0);
                    addObject(texts, MyWorld.WINDOW_SIZE / 2, MyWorld.WINDOW_SIZE - mes.box.getHeight() / 2 - 50);
                    displayLineCount = 0;

                    if (lineCount == readStory.length) {
                        exitThis = true;
                    } else {
                        stopLine = false;
                    }
                } else if (!stopLine) {
                    flameCount++;
                    talk.playLoop();
                    if (flameCount >= returnFlame) {
                        try {
                            if (showingStory[lineCount].equals("")) {
                            } else {
                                showingStory[lineCount] += readStory[lineCount].charAt(charaCount);
                            }
                        } catch (Exception e) {
                            showingStory[lineCount] = readStory[lineCount].substring(0, 1);
                        }
                        if (readStory[lineCount].charAt(charaCount) == '▼') {
                            stopLine = true;
                            flashPressEnter = new FlashingText(PRESS_ENTER, FLASHTEXT_SIZE);
                            addObject(flashPressEnter, MyWorld.WINDOW_SIZE - flashPressEnter.flashText.getWidth() / 2 - 50, MyWorld.WINDOW_SIZE - flashPressEnter.flashText.getHeight() / 2 - 10);
                            Greenfoot.setSpeed(50);
                        }
                        storyLine[lineCount] = new GreenfootImage(showingStory[lineCount], TEXT_SIZE, white, transport, rightGray);
                        passImg.drawImage(storyLine[lineCount], 0, TEXT_SIZE * displayLineCount);
                        texts.img = passImg;
                        flameCount = 0;
                        if (charaCount == readStory[lineCount].length() - 1) {
                            lineCount++;
                            displayLineCount++;
                            charaCount = 0;
                        } else {

                            charaCount++;
                        }
                    }
                }
            } catch (Exception e) {
                //System.out.println(e.getCause());
                //throw e;
            }
        }
    }

    public void checkInput(World world) {
        str = Greenfoot.getKey();
        try {
            if (str.equals("right")) {
                exitThis = true;
                stopLine = true;
                Greenfoot.setSpeed(50);
                exitThis(world);
            }

            if (str.equals("enter") && !stopLine) {
                returnFlame = 0;
                Greenfoot.setSpeed(100);
            }
        } catch (Exception e) {
            //throw e;
        }
    }

    public void setName(GreenfootImage img) {
        try {
            removeObject(nameDisplay);
            removeObject(name);
        } catch (Exception e) {
        }
        nameDisplay = new DisplayImage(img, 0);
        addObject(name, mes.getX() - mes.box.getWidth() / 2 + name.box.getWidth() / 2, mes.getY() - mes.box.getHeight() / 2 - name.box.getHeight() / 2);
        addObject(nameDisplay, name.getX(), name.getY());
    }

    public void exitThis(World world) {
        if (exitThis) {
            talk.stop();
            addObject(MyWorld.blackout, MyWorld.WINDOW_SIZE / 2, MyWorld.WINDOW_SIZE / 2);
        }
        if (MyWorld.waitStartStage) {

            //ステージ終了時処理フラグをfalseに
            MyWorld.waitStartStage = false;
            //ステージ終了時処理フラグをfalseに
            MyWorld.finishRemoveBlack = false;
            MyWorld.resetBullets();
            //次ステージの呼び出し
            Greenfoot.setWorld(world);
        }
    }

    @Override
    public void act() {
        checkInput(new Stage1());
        progress();
        exitThis(new Stage1());
    }

}
