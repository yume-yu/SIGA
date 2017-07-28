
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Items here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Items extends Actor {

    //自身の画像の設定
    GreenfootImage img = new GreenfootImage("./images/point.png");
    //移動速度を1/2にするために、毎フレームフラグを切り替えて1フレームに1回位置に+1する。そのためのフラグ
    boolean moveFlag = false;
    //アイテムが吸い込まれるモードかどうかのフラグ
    boolean collectionMode = false;
//    public Items(GreenfootImage img){
//        this.img = img;
//        setImage(this.img);
//    }

    public void act() {
        if (Stage.inPause) {

        } else {
            fall();
        }
    }

    public void fall() {
        if (collectionMode) {
            turnTowards(Stage.heroX, Stage.heroY);
            move(10);
        } else {
            if (Stage.heroY <= Stage.HEIGHT / 5) {
                collectionMode = true;
            } else {
                moveFlag = !moveFlag;
                if (moveFlag) {
                    turnTowards(getX(), Stage.HEIGHT);
                    move(1);
                    //setLocation(getX(), getY()+1);
                }
            }

        }
    }

    public void givePoint(int point) {
        if (isTouching(Enemies.meout.getClass())) {
            Cater.score += point;
            Stage.scoreUpdate = true;
            getWorld().removeObject(this);
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    public void giveLife() {
        if (isTouching(Enemies.meout.getClass()) && Cater.life < 6) {
            Cater.life++;
            Cater.updateLife = true;
            getWorld().removeObject(this);
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    public void giveSkill() {
        if (isTouching(CaterOuter.class)) {
            if (!MyWorld.haveItem) {
                MyWorld.haveItem = true;
                getWorld().addObject(MyWorld.itemDisplay, 800 - MyWorld.itemDisplay.img.getWidth() - 10, 695 - 6 * 15);
            }
            getWorld().removeObject(this);
        } else if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }
}
