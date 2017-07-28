
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class StagBeetle extends Boss {

    //状態判断用変数
    int mode = 0;
    //方向反転判断
    int direction = 1;
    //フレームカウント
    int flameCount = 0;
    int beamFlameCount = 0;

    //横移動の基準距離(振幅)
    int distanceBaseX = MyWorld.WIDTH / 2 - 100;
    //角度用変数
    double angle = 0;
    int dangle = 1;

    boolean beaming = false;

    //ビームパターン判別
    int beamPattern = 1;

    //ビームとして渡す画像
    GreenfootImage beamImg = new GreenfootImage(800, 20);
    GreenfootImage SubbeamImg = new GreenfootImage(400, 20);
    //ビーム用オブジェクト
    EnemyBulletForStag beams = new EnemyBulletForStag();
    EnemyBulletForStag dualBeam[] = {new EnemyBulletForStag(), new EnemyBulletForStag()};
    //ビーム音
    GreenfootSound beamSound = new GreenfootSound("./sounds/beam.mp3");
    GreenfootSound beamWaitSound = new GreenfootSound("./sounds/beamwait.mp3");
    //風の音
    GreenfootSound wind = new GreenfootSound("./sounds/wind.mp3");
    //竜巻
    GreenfootImage cycronImg = new GreenfootImage("./images/cycron.png");
    EnemyBulletForCycron cycron[] = new EnemyBulletForCycron[20];

//new GreenfootImage[20];
    public StagBeetle() {
        HITPOINT = 200;
        hitpoint = HITPOINT;
        giveScore = 1;
        range = 15;
        int[] items = {0, 0, 0};
        this.items = items;
        direction = 1;
        dangle = 1;
        img = new GreenfootImage("./images/stag.png");
        setImage(img);
        beaming = false;
        beamImg.setColor(new Color(92, 244, 66));
        beamImg.fill();
        beamImg.setTransparency(45);
        SubbeamImg.setColor(new Color(92, 244, 66));
        SubbeamImg.fill();
        SubbeamImg.setTransparency(45);
        for (int i = 0; i < cycron.length; i++) {
            cycron[i] = new EnemyBulletForCycron(cycronImg);
        }
    }

    public void beam() {
        if (beamFlameCount == 0) {
            beamImg.setTransparency(45);
            beams.img = beamImg;
            getWorld().addObject(beams, x, y + beams.img.getWidth() / 2 + img.getHeight() / 2);
            beamWaitSound.play();
            beaming = true;
            //getWorld().setPaintOrder(this.getClass(),beams.getClass());
        } else if (beamFlameCount == 100) {
            beamImg.setTransparency(255);
            beamWaitSound.stop();
            beamSound.playLoop();
            beams.isActive = true;
            beams.img = beamImg;
        } else if (beamFlameCount == 270) {
            beams.isActive = false;
            beamSound.stop();
            beamImg.setTransparency(45);
            beams.img = beamImg;
        }
        beamFlameCount++;
        if (beamFlameCount > 300) {
            getWorld().removeObject(beams);
            beaming = false;
            //beamPattern = 0;
            flameCount = 0;
            beamFlameCount = 0;
        }

    }

    public void dualBeam() {

        if (beamFlameCount == 0) {
            beamImg.setTransparency(45);
            for (int i = 0; i < dualBeam.length; i++) {
                dualBeam[i].img = beamImg;
                //distanceBaseX = MyWorld.WIDTH / 2 - 200;
            }
            if (x <= 100) {
                dualBeam[0].img = SubbeamImg;
            }
            beaming = true;
            getWorld().addObject(dualBeam[0], x + (int) (dualBeam[0].img.getWidth() / 2 * Math.cos(Math.toRadians(60))), y + 50 + (int) (dualBeam[0].img.getWidth() * Math.sin(Math.toRadians(60)) / 2));
            dualBeam[0].setRotation(120);
            getWorld().addObject(dualBeam[1], x + (int) (dualBeam[1].img.getWidth() / 2 * Math.cos(Math.toRadians(120))), y + 50 + (int) (dualBeam[1].img.getWidth() * Math.sin(Math.toRadians(120)) / 2));
            dualBeam[1].setRotation(60);
            //getWorld().setPaintOrder(this.getClass(),beams.getClass());
            beamWaitSound.play();
        } else if (beamFlameCount == 100) {
            SubbeamImg.setTransparency(255);
            beamImg.setTransparency(255);
            beamWaitSound.stop();
            beamSound.playLoop();
            for (int i = 0; i < dualBeam.length; i++) {
                dualBeam[i].isActive = true;
                dualBeam[i].img = beamImg;
            }
            if (x <= 100) {
                dualBeam[0].img = SubbeamImg;
            }
        } else if (beamFlameCount == 270) {
            SubbeamImg.setTransparency(45);
            beamImg.setTransparency(45);
            beamSound.stop();
            for (int i = 0; i < dualBeam.length; i++) {
                dualBeam[i].isActive = false;
                dualBeam[i].img = beamImg;

            }
            if (x <= 100) {
                dualBeam[0].img = SubbeamImg;
            }
        }

        beamFlameCount++;
        if (beamFlameCount > 300) {
            beaming = false;
            getWorld().removeObject(dualBeam[0]);
            getWorld().removeObject(dualBeam[1]);
            //distanceBaseX = MyWorld.WIDTH / 2 - 100;
            beamPattern = 0;
            flameCount = 0;
            beamFlameCount = 0;
        }

    }

    @Override
    public void shot() {
        for (int i = 0; i < cycron.length; i++) {
            try {
                int check = cycron[i].getX();
            } catch (Exception e) {
                cycron[i] = new EnemyBulletForCycron(cycronImg);
                wind.play();
                getWorld().addObject(cycron[i], x, y);
                break;
            }
        }
    }

    public void act() {
        if (Stage.inPause) {
            //ポーズ中なので空
        } else {

            if (startBattle) {

                //位置情報の更新
                statusUpdate();

                switch (mode) {
                    case 0:
                        setLocation(x, y + 2);
                        if (getY() > 100) {
                            mode = 1;
                        }
                        break;
                    case 2:
                    case 3:
                        if (flameCount % 65 == 0) {
                            shot();
                        }
                    case 1:
                        flameCount++;
                        move(MyWorld.WIDTH / 2 + (int) (distanceBaseX * Math.sin(angle)) - x);
                        //角度を元に位置を更新
                        //setLocation(MyWorld.WIDTH / 2 + (int) (distanceBaseX * Math.sin(angle)), y);
                        try {
                            beams.setLocation(x, y + beams.img.getWidth() / 2 + img.getHeight() / 2);
                        } catch (Exception e) {

                        }
                        try {

                            if (x <= 200) {
                                //getWorld().removeObject(dualBeam[0]);
                                dualBeam[0].img = SubbeamImg;
                                dualBeam[0].setLocation(x + (int) (dualBeam[0].img.getWidth() / 2 * Math.cos(Math.toRadians(60))), y + 50 + (int) (dualBeam[0].img.getWidth() * Math.sin(Math.toRadians(60)) / 2));

                            } else {
                                dualBeam[0].img = beamImg;
                                dualBeam[0].setLocation(x + (int) (dualBeam[0].img.getWidth() / 2 * Math.cos(Math.toRadians(60))), y + 50 + (int) (dualBeam[0].img.getWidth() * Math.sin(Math.toRadians(60)) / 2));

                            }

                            dualBeam[0].setRotation(0);
                            dualBeam[1].setRotation(0);
                            dualBeam[0].move(x - (int) (dualBeam[0].img.getWidth() / 2 * Math.cos(Math.toRadians(60))) - dualBeam[0].getX());
                            dualBeam[1].move(x - (int) (dualBeam[1].img.getWidth() / 2 * Math.cos(Math.toRadians(120))) - dualBeam[1].getX());
                            dualBeam[0].setRotation(120);
                            dualBeam[1].setRotation(60);
                        } catch (Exception e) {
                        }
                        //角度を10°足す
                        angle += Math.toRadians(dangle);
                        //angleが360°になっていたら
                        if (angle >= 2 * Math.PI) {
                            //0に直す
                            angle = 0;
                        }
                        if (flameCount == 50) {
                            beamPattern = Greenfoot.getRandomNumber(2);
                        } else if (flameCount > 50) {

                            switch (beamPattern) {
                                case 0:
                                    beam();
                                    break;
                                case 1:
                                    dualBeam();
                                    break;
                                case 2:
                                    beam();
                                    dualBeam();
                                    break;
                            }
                        }
                        break;
                }
                
                //体力を見てモードを切り替える
                switch(mode){
                    case 1:
                        if(hitpoint <= 2 * HITPOINT/3){
                            mode = 2;
                        }
                        break;
                    case 2:
                        if(hitpoint <= HITPOINT/3){
                            mode = 3;
                            dangle = 2;
                        }
                    case 3:
                        break;
                }
                hitStatusCheck(img);

                //消失判定(ボスなので、画面縁で消えないようにする)
                if (hitpoint <= 0) {
                    beamSound.stop();
                    beamWaitSound.stop();
                    //ドロップアイテムの出現
                    putItem(items, giveScore);
                    try {
                        getWorld().removeObject(beams);
                    } catch (Exception e) {
                    }
                    try {
                        getWorld().removeObject(dualBeam[0]);
                        getWorld().removeObject(dualBeam[1]);
                    } catch (Exception e) {
                    }
                    //消滅
                    getWorld().removeObject(this);
                    //ステージのクリアフラグを立てる
                    MyWorld.clearStage = true;
                }

            } else {
                if (caution.finishCaution) {
                    getWorld().addObject(new DisplayHitpoint("StagBeatle"),700, Stage.HEIGHT/2);
                    img.setTransparency(255);
                    startBattle = true;
                }
            }
        }
    }
}
