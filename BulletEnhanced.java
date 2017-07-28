
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BulletEnhanced extends Bullet {

    int length = 0;
    GreenfootImage beamImg = new GreenfootImage(3, 10);
    
    int flameCount = 0;

    public BulletEnhanced() {
        length = 0;
        beamImg.setColor(Color.WHITE);
        beamImg.fill();
        setImage(beamImg);
        flameCount = 0;

    }
    
    public boolean checkT(int x,int y){
        if(Math.abs(x - CaterOuter.nakami.getX()) < 10){
            return true;
        }else if(Math.abs(y - CaterOuter.nakami.getY()) < 10){
            return true;
        }else if(Math.abs(Math.abs(x - CaterOuter.nakami.getX()) - Math.abs(y - CaterOuter.nakami.getY())) < 10){
                
            return true;
        }else{
            return false;
        }
    }
    
    @Override
    protected void addedToWorld(World world) {
    }

    public void act() {
        if (Stage.inPause) {
            if(Cater.beamSound.isPlaying()){
                Cater.beamSound.pause();
            }
            
        } else {
            if(!Cater.beamSound.isPlaying()){
                Cater.beamSound.playLoop();
            }
            flameCount++;
            if (length <= 2 * Stage.HEIGHT) {
                length += 50;
                beamImg = new GreenfootImage(length, 10);
                beamImg.setColor(Color.WHITE);
                beamImg.fill();
                setImage(beamImg);
            }
            
            if (flameCount == 250) {
                Cater.beamSound.stop();
                Cater.isBeaming = false;
                getWorld().removeObject(this);
            }
        }
    }
}
