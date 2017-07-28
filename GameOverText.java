import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class GameOverText extends SystemObject
{
    Color transport = new Color(0, 0, 0, 0);
    Color white = new Color(255,255,255,255);
    Color rightGray = new Color(125,125,125,255);
    
    static GreenfootImage main = new GreenfootImage(Stage.WIDTH, Stage.HEIGHT);
    GreenfootImage gameOver = new GreenfootImage("GAME OVER", 100, white, transport, rightGray);
    GreenfootImage c0ntinue = new GreenfootImage("Continue", 50, white, transport, rightGray); 
    GreenfootImage backTitle = new GreenfootImage("Back Title", 50, white, transport, rightGray); 
    GreenfootImage arrow = new GreenfootImage(c0ntinue.getHeight()/2, c0ntinue.getHeight()/2);
    
    boolean selectedContiue = true;
    boolean inGameOver = false;
    int completeMove = 0;
    int trans = 0;
            
    static DisplayImage here;
    
    public GameOverText(){
        trans = 0;
        completeMove = 0;
        arrow.setColor(white);
        int x[] = {0,0,arrow.getWidth()};
        int y[] = {0,arrow.getHeight(),arrow.getHeight()/2};
        arrow.fillPolygon(x, y, 3);
        here = new DisplayImage(arrow, 0);
        main = new GreenfootImage(Stage.WIDTH, Stage.HEIGHT);
        main.drawImage(gameOver, (main.getWidth() - gameOver.getWidth())/2, (main.getHeight() - gameOver.getHeight())/2);
        main.setTransparency(trans);
        setImage(main);
        }

    @Override
    protected void addedToWorld(World world) {       
        selectedContiue = true;
        inGameOver = true;
    }
    
    
    public void selectContinue(){
        getWorld().addObject(here, (main.getWidth() - backTitle.getWidth())/2 - arrow.getWidth() -10, (main.getHeight() - gameOver.getHeight() )/2 + c0ntinue.getHeight() + 150);
    }
    
    public void selectBack(){
        getWorld().addObject(here, (main.getWidth() - backTitle.getWidth())/2 - arrow.getWidth() -10, (main.getHeight() - gameOver.getHeight() )/2 + c0ntinue.getHeight() + backTitle.getHeight()+ 150);
    }
    
    public void act(){
        switch(completeMove){
            case 0:
                setLocation(getX(), getY() + 4);
                trans+=2;
                main.setTransparency(trans);
                if(getY() >= Stage.HEIGHT/2){
                    main.setTransparency(255);
                    completeMove++;
                }
                try{
                    if(Stage.str.equals("enter")){
                        setLocation(getX(), Stage.HEIGHT/2);
                    }
                }catch(Exception e){
                    
                }
                
            break;
            case 1:
                main.drawImage(c0ntinue, (main.getWidth() - c0ntinue.getWidth())/2, (main.getHeight() - gameOver.getHeight() )/2 + c0ntinue.getHeight()/2 + 150);
                main.drawImage(backTitle, (main.getWidth() - backTitle.getWidth())/2, (main.getHeight() - gameOver.getHeight() )/2 + c0ntinue.getHeight() + backTitle.getHeight()/2+ 150);
                setImage(main);
                selectContinue();
                completeMove++;
                break;
            case 2:
                try{
                    if((Stage.str.equals("down") || Stage.str.equals("up")) && selectedContiue){
                        MyWorld.button.play();
                        getWorld().removeObject(here);
                        selectBack();
                        selectedContiue = false;
                    }else if((Stage.str.equals("down") || Stage.str.equals("up")) && !selectedContiue){
                        MyWorld.button.play();
                        getWorld().removeObject(here);
                        selectContinue();
                        selectedContiue = true;
                    }else if(Stage.str.equals("enter")){
                        MyWorld.button.play();
                        if(selectedContiue){
                            Stage.inPause = false;
                            Cater.life = 6;
                            Cater.score /= 2;
                            Cater.updateLife = true;
                            Stage.scoreUpdate = true;
                            getWorld().removeObject(here);
                            getWorld().removeObject(Stage.pose);
                            getWorld().removeObject(this);
                        }else{
                            Stage.inPause = false;
                            MyWorld.usingStage.bgm.stop();
                            getWorld().removeObject(here);
                            getWorld().removeObject(Stage.pose);
                            getWorld().removeObject(this);
                            Greenfoot.setWorld(new MyWorld());
                        }
                        inGameOver = false;
                    }
                }catch(Exception e){

                }
                break;
        }
    }    
}
