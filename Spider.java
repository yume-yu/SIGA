
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Spider extends Boss {

    //行動パターンのフラグ
    int mode;
    //糸の画像1
    GreenfootImage bulletImg = new GreenfootImage(1, 2);

    int WIDTHF = 300;
    int HEIGHTF = 200;
    //経過フレームカウンタ
    int flameCount = 0;
    //糸を吐く
    int shotFlameCounter = 0;
    int dPosition[][] = {{67, -67}, {117, -117}, {192, -192}};
    int destination[] = new int[2];
    int shootLineCount = 0;
    //makeLine()でつかう、発射時の自機の位置記憶変数。
    int toPoint[] = new int[2];
    //makeLine()でつかう、糸のオブジェクト生成
    EnemyBulletForSpi line;
    //自分から相手までの相対角度記憶変数
    double rotate = 0;
    //makeLine()でつかう、糸の長さを記憶する変数
    int longer = 1;
    //移動フラグ
    boolean move = true;
    boolean isFirst = true;

    //makeWeb()でつかう、糸のオブジェクト生成
    EnemyBulletForSpi lines[] = {new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true),
        new EnemyBulletForSpi(0, 0, bulletImg, true)};
    //makeWeb()でつかう、それぞれの糸の角度
    final int rotates[] = {0, 45, 90, 135, 180, 225, 270, 315, 0, 90, 180, 270, 0, 90, 180, 270};
    //makeWeb()でつかう、糸設定完了のフラグ
    boolean setComp[] = {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false};

    public Spider() {
        img = new GreenfootImage("./images/bigSpider.png");
        img.rotate(-90);
        bulletImg.setColor(Color.WHITE);
        bulletImg.fill();
        setImage(img);
        HITPOINT = 150;
        hitpoint = HITPOINT;
        giveScore = 0;
        int items[] = {0, 0, 0};
        mode = 0;
        this.items = items;
        destination[0] = WIDTHF;
        destination[1] = HEIGHTF;
    }

    @Override
    protected void addedToWorld(World world) {
        super.addedToWorld(world);
    }

    public void act() {
        if (Stage.inPause) {
        } else {
            if (startBattle) {
                //位置情報の更新
                statusUpdate();
                //System.out.println(flameCount);
                //移動パターンをmodeフラグを元に切り替え
                switch (mode) {
                    case 0://0はデフォルト。自機をめがけて追ってくる
                        turnTowards(Stage.WIDTH / 2, Stage.HEIGHT / 4);
                        move(1);
                        if (getX() == Stage.WIDTH / 2 && getY() == Stage.HEIGHT / 4) {
                            mode = 1;
                        }
                        break;
                    case 2:
                        if(isFirst){
                            makeWeb();
                        }else{
                        if (getX() == destination[0] && getY() == destination[1]) {
                            int first = Greenfoot.getRandomNumber(3);
                            destination[0] = WIDTHF + dPosition[first][Greenfoot.getRandomNumber(2)];
                            destination[1] = HEIGHTF + dPosition[first][Greenfoot.getRandomNumber(2)];
                        }
                        
                        shotFlameCounter++;
                            if (shotFlameCounter >= 500) {
                                turnTowards(Stage.heroX, Stage.heroY);
                                makeLine(false, Stage.heroX, Stage.heroY);
                            }else{
                                turnTowards(destination[0], destination[1]);
                                move(1);
                            }
                        }
                        break;
                    case 1://1はパターン1 自機めがけて糸を発射
                        if (isFirst) {
                            makeWeb();
                        } else {
                            //turnTowards(Stage.WIDTH / 2, Stage.HEIGHT);
                            shotFlameCounter++;
                            if (shotFlameCounter >= 200) {
                                makeLine(true, Stage.WIDTH / 4 * shootLineCount + 1, Stage.HEIGHT);
                            }
                            if (shotFlameCounter == 200) {
                                getWorld().addObject(new Bee(450), 10, 1);
                                getWorld().addObject(new Bee(450), Stage.WIDTH - 10, 1);
                            }
                        }
                        if (hitpoint <= HITPOINT / 3) {
                            mode = 2;
                            isFirst = true;
                        }
                        break;
                    //case 2://2はパターン2 蜘蛛の巣を展開
                    //makeWeb();
                    //break;
                    case 3://3はパターン3 巣に合わせて移動？
                        move = !move;
                        shotFlameCounter++;
                        turnTowards(Stage.heroX, Stage.heroY);
                        if (shotFlameCounter >= 200) {
                            makeLine(true, 0, 0);

                        } else if (move) {
                            move(1);
                        }
                        break;
                    default:
                        hitpoint = 0;
                        makeLine(false, 0, 0);
                        break;
                }

                //テスト用
                if (Greenfoot.isKeyDown("1")) {
                    mode = 1;
                }
                if (Greenfoot.isKeyDown("2")) {
                    mode = 2;
                }

                //当たり判定
                hitStatusCheck(20);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                    //消滅
                    getWorld().addObject(new ClearStageEffect("Stage4", ((Stage) (getWorld())).timer.millisElapsed()), Stage.WIDTH / 2, Stage.HEIGHT / 2);

                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    //MyWorld.clearStage = true;
                }
            } else {
                if (caution.finishCaution) {
                    getWorld().addObject(new DisplayHitpoint("MotherSpider"), 700, Stage.HEIGHT / 2);
                    img.setTransparency(255);
                    startBattle = true;
                }
            }
        }
    }

    //糸を吐く動きを再現するメソッド。時間制御はflameCountを使ってフレーム数を数えることで再現
    public void makeLine(boolean is, int xx, int yy) {
        //行動開始時の処理
        if (flameCount == 0) {
            //まず最初に発動直後の自機の位置を記憶
            if (is) {
                toPoint[0] = Stage.heroX;
                toPoint[1] = Stage.heroY;
            } else {
                toPoint[0] = xx;
                toPoint[1] = yy;
            }
            //長さの初期化
            longer = 1;
            //糸オブジェクトの初期化
            line = new EnemyBulletForSpi(0, 0, bulletImg, true);
            line.isWebMaterial = !is;
            //とりあえずオブジェクトを追加
            getWorld().addObject(line, x, y);
            //オブジェクトの向きを自機に向ける
            line.turnTowards(toPoint[0], toPoint[1]);
            //自機に向けたオブジェクトの角度を記憶
            rotate = line.getRotation();
        }

        //先に糸の先端が端に届いていないかを確かめる(x軸しか見てない)
        //端に届いていたら
        //箸に届いてる1 = 中心がほぼ上端
        if (x + (int) (longer / 2 * Math.sin(Math.toRadians(90 - rotate))) < 10) {
            completeLine();
        } //端に届いてる2 = 先端xが0以下or600以上 
        else if ((x + (int) (longer * Math.sin(Math.toRadians(90 - rotate))) <= 0
                || x + (int) (longer * Math.sin(Math.toRadians(90 - rotate))) >= 600) /*&& (rotate >90 && rotate < 270)*/) {
            completeLine();
        } //端に届いてる2 = 先端yが0以下or800以上 
        else if ((y + (int) (longer * Math.cos(Math.toRadians(90 - rotate))) >= 800
                || y + (int) (longer * Math.cos(Math.toRadians(90 - rotate))) <= 0) /*&& rotate < 180 */) {
            completeLine();
        } //端に届いていなかったら
        else {
            //一度オブジェクトを除去する
            getWorld().removeObject(line);
            //糸の長さを+10する
            longer += 10;
            //糸の画像の大きさを算出した長さにする
            bulletImg = new GreenfootImage(longer, 2);
            //糸を塗りつぶす色を決める
            bulletImg.setColor(Color.WHITE);
            //糸の画像を塗りつぶす
            bulletImg.fill();
            //糸の画像を再設定する
            line.img = bulletImg;
            //長くなった糸を、伸びた分調整した位置に再度追加する
            getWorld().addObject(line, x + (int) (longer / 2 * Math.sin(Math.toRadians(90 - rotate))), y + (int) (longer / 2 * Math.cos(Math.toRadians(90 - rotate))));
            //記録していた角度に回転させる
            line.setRotation((int) rotate);
            //最後にフレーム数を数える
            flameCount++;
        }

    }

    @Override
    public void completeLine() {
        //糸の消失カウントダウン開始
        line.startCount = true;
        //次に備えて糸画像の初期化
        bulletImg = new GreenfootImage(1, 2);
        //フレームカウントを初期化
        flameCount = 0;
        //モードを通常に戻す
        //mode = 0;
        shotFlameCounter = 0;
        shootLineCount++;
        if (shootLineCount == 5) {
            shootLineCount = 0;
        } else if (shootLineCount == 2 && mode == 1) {
            isFirst = true;
        }
    }

    public void makeWeb() {
        //行動開始時の処理
        if (flameCount == 0) {
            //長さの初期化
            longer = 1;
            //糸オブジェクトとフラグの初期化
            resetForMakeWeb();
            //とりあえずオブジェクト追加
            for (int i = 0; i < lines.length; i++) {
                getWorld().addObject(lines[i], x, y);
            }
            //順位の設定
            //getWorld().setPaintOrder(MyWorld.scoreDisplay.getClass(),MyWorld.stageDisplay.getClass(),MyWorld.statusBack.getClass());

        }
        if (setComp[0] && setComp[1] && setComp[2] && setComp[3] && setComp[4] && setComp[5] && setComp[6] && setComp[7]) {
            if (mode != 2) {
                for (int i = 0; i < lines.length; i++) {
                    lines[i].startCount = true;
                }
            }
            //次に備えて糸画像の初期化
            bulletImg = new GreenfootImage(1, 2);
            //フレームカウントを初期化
            flameCount = 0;
            //モードを通常に戻す
            //mode = 0;
            isFirst = false;
        } else {
            //糸の長さを+10する
            longer += 10;
            //糸の画像の大きさを算出した長さにする
            bulletImg = new GreenfootImage(longer, 2);
            //糸を塗りつぶす色を決める
            bulletImg.setColor(Color.WHITE);
            //糸の画像を塗りつぶす
            bulletImg.fill();
            for (int i = 0; i < lines.length; i++) {
                if (!setComp[i]) {
                    if (i % 2 == 1 && i < 8) {
                        if (x + (int) (longer / 2 * Math.sin(Math.toRadians(rotates[i]))) < 10) {
                            setComp[i] = true;
                            System.out.println(i + " : 1 /" + lines[i].getX() + " : " + lines[i].getY());
                        } //端に届いてる2 = 先端xが0以下or590以上 
                        else if ((x + (int) (longer * Math.sin(Math.toRadians(rotates[i]))) <= 0
                                || x + (int) (longer * Math.sin(Math.toRadians(rotates[i]))) >= 600)) {
                            setComp[i] = true;
                            System.out.println(i + " : 2 /" + lines[i].getX() + " : " + lines[i].getY());
                        } //端に届いてる2 = 先端yが0以下or800以上 
                        else if ((y + (int) (longer * Math.cos(Math.toRadians(rotates[i]))) >= 800
                                || y + (int) (longer * Math.cos(Math.toRadians(rotates[i]))) <= 0)) {
                            setComp[i] = true;
                            System.out.println(i + " : 3 /" + lines[i].getX() + " : " + lines[i].getY());
                        } else {
                            updateLines(i);
                        }
                    } else {
                        switch (i) {
                            //内側
                            case 0://0°の時
                                if ((int) (longer * Math.cos(Math.toRadians(rotates[i]))) >= 135) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 4://180°の時
                                if ((int) (longer * Math.cos(Math.toRadians(rotates[i]))) <= -135) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 2://90°の時
                                if ((int) (longer * Math.sin(Math.toRadians(rotates[i]))) >= 135) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 6://270°のとき
                                if ((int) (longer * Math.sin(Math.toRadians(rotates[i]))) <= -135) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            //真ん中
                            case 8://0°の時
                                if ((int) (longer * Math.cos(Math.toRadians(rotates[i]))) >= 235) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 10://180°の時
                                if ((int) (longer * Math.cos(Math.toRadians(rotates[i]))) <= -235) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 9://90°の時
                                if ((int) (longer * Math.sin(Math.toRadians(rotates[i]))) >= 235) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 11://270°のとき
                                if ((int) (longer * Math.sin(Math.toRadians(rotates[i]))) <= -235) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            //外側
                            case 12://0°の時
                                if ((int) (longer * Math.cos(Math.toRadians(rotates[i]))) >= 385) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 14://180°の時
                                if ((int) (longer * Math.cos(Math.toRadians(rotates[i]))) <= -385) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 13://90°の時
                                if ((int) (longer * Math.sin(Math.toRadians(rotates[i]))) >= 385) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                            case 15://270°のとき
                                if ((int) (longer * Math.sin(Math.toRadians(rotates[i]))) <= -385) {
                                    setComp[i] = true;
                                } else {
                                    updateLines(i);
                                }
                                break;
                        }
                    }
                }
            }
            //最後にフレーム数を数える
            flameCount++;
        }
    }

    public void resetForMakeWeb() {
        for (int i = 0; i < lines.length; i++) {
            lines[i] = new EnemyBulletForSpi(0, 0, bulletImg, true);
            setComp[i] = false;
        }
    }

    public void updateLines(int i) {
        //一度オブジェクトを除去する
        getWorld().removeObject(lines[i]);
        //糸の画像を再設定する
        lines[i].img = bulletImg;
        //長くなった糸を、伸びた分調整した位置に再度追加する
        getWorld().addObject(lines[i], x + (int) (longer / 2 * Math.sin(Math.toRadians(rotates[i]))), y + (int) (longer / 2 * Math.cos(Math.toRadians(rotates[i]))));
        //記録していた角度に回転させる
        lines[i].setRotation(rotates[i]);
    }
}
