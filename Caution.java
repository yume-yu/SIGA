import greenfoot.*;
import java.awt.GraphicsEnvironment;
//import java.awt.Font;


/**
 * Write a description of class Caution here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Caution extends SystemObject{
    //背景の画像。ここに画像を配置する
    GreenfootImage base;
    //上下のリボンの画像
    GreenfootImage rebon;
    //真ん中の"WORNING"の文字の画像
    GreenfootImage warning;
    //音
    GreenfootSound alarm = new GreenfootSound("./sounds/alarm.mp3");
    
    //完全な透明の色
    static final Color TRANS = new Color(0, 0, 0, 0);
    //なんとなく定義した黒
    static final Color BLACK = new Color(0, 0, 0);
    //リボンや文字で使う赤の色
    static final Color RED_FOR_CAUTION = new Color(200,50,50);
    
    //すべての画像の透明度。初期値は185
    int transportLevel;
    
    //透明度増減のフラグ.trueなら減らす
    boolean upDown = true;
    
    boolean bfAf = true;
    boolean removeFlag = false;
    boolean finishCaution = false;
    int xPoints[] = new int[11];
    int flameCount = 0;
    //greenfoot.Font font;
    
    //コンストラクタ
    public Caution(){
        transportLevel = 185;
        getImage();
        //各画像の初期化
        base = new GreenfootImage(MyWorld.WIDTH, MyWorld.HEIGHT/2);
        rebon = new GreenfootImage(MyWorld.WIDTH, base.getHeight()/5);
        warning = new GreenfootImage("./images/warning.png");
        //ベース画像の背景を透明に
        base.setColor(TRANS);
        base.fill();
        //しましまを書くためのx座標をセット
        for(int i = 0;i < xPoints.length;i++){
            xPoints[i] = (base.getWidth()/10)*i;
        }
        
        //フォントを設定
        //font = font.deriveFont(font.getSize());
        
        
        
        //使用できるフォントの確認
        java.awt.Font[] fonts=GraphicsEnvironment.getLocalGraphicsEnvironment().getAllFonts();
        for(int i = 0;i < fonts.length;i++){
            //System.out.println(fonts[i].getName());
        }
        //諦めた名残
        //font = new greenfoot.Font(/*"SansSerif" fonts[10].getName()*/java.awt.Font.MONOSPACED,true,false,120);
        //System.out.println(font.getName());
        //配置する画像の描画
        startUp();
    }
    
    //配置する雅号を描画するメソッド
    public void startUp(){
        //上下のリボン
        rebon.setColor(RED_FOR_CAUTION);
        rebon.fill();
    }

    @Override
    protected void addedToWorld(World world) {
        getWorld().setPaintOrder(null);
        getWorld().setPaintOrder(this.getClass(),Stage.scoreDisplay.getClass(),Cater.LIFE_IMG[0].getClass(),Stage.status.getClass());
        alarm.playLoop();
    }    
    
    public void updateImage(){
        if(upDown){
            transportLevel--;
            if(transportLevel == 100 && !removeFlag){
                upDown = false;
            }else if(removeFlag && transportLevel == 0){
                finishCaution = true;
                try{
                    MyWorld.usingStage.bgm = MyWorld.usingStage.bossBgm;
                    MyWorld.usingStage.bgm.playLoop();
                }catch(Exception e){
                }
                getWorld().removeObject(this);
            }else if(removeFlag){
                transportLevel--;
            }
        }else{
            transportLevel++;
            if(transportLevel == 185){
                
                upDown = true;
            }
        }
        startUp();
        rebon.setColor(BLACK);
        if(bfAf){
            for(int i = 1;i < xPoints.length - 1;i+=2){
                int x[] = {xPoints[i],xPoints[i+1],xPoints[i],xPoints[i-1]};
                int y[] = {0,0,rebon.getHeight(),rebon.getHeight()};
                rebon.fillPolygon(x, y, 4);
            }
        }else{
            int leftx[] = {xPoints[0],xPoints[1],xPoints[0]};
            int lefty[] = {0,0,rebon.getHeight()};
            rebon.fillPolygon(leftx, lefty, 3);
            int rightx[] = {xPoints[xPoints.length-1],xPoints[xPoints.length-1],xPoints[xPoints.length - 2]};
            int righty[] = {0,rebon.getHeight(),rebon.getHeight()};
            rebon.fillPolygon(rightx, righty, 3);
            for(int i = 2;i < xPoints.length - 1;i+=2){
                int x[] = {xPoints[i],xPoints[i+1],xPoints[i],xPoints[i-1]};
                int y[] = {0,0,rebon.getHeight(),rebon.getHeight()};
                rebon.fillPolygon(x, y, 4);
            }
        }
        warning.setTransparency(transportLevel+70);
        rebon.setTransparency(transportLevel);
    }
    
    public void makeImage(){
        base = new GreenfootImage(MyWorld.WIDTH, MyWorld.HEIGHT/2);
        base.drawImage(rebon, 0, 0);
        base.drawImage(warning, base.getWidth()/2 - warning.getWidth()/2, (base.getHeight()-warning.getHeight())/2);
        base.drawImage(rebon, 0, (9 * base.getHeight()/10 -rebon.getHeight()/2));
        setImage(base);
    }
    
    public void act(){
        if(Stage.inPause){
        }
        else{
            updateImage();
            makeImage();
            flameCount++;
            if(flameCount % 50 == 0){
                bfAf = !bfAf;
                if(flameCount == 250){
                    alarm.stop();
                    removeFlag = true;
                }
            }
        }
    }    
}
