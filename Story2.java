import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)


public class Story2 extends Story{
    
    GreenfootImage ari1 = new GreenfootImage("働きアリ1", TEXT_SIZE - 5, white, transport, rightGray);
    GreenfootImage ari2 = new GreenfootImage("働きアリ2", TEXT_SIZE - 5, white, transport, rightGray);
    GreenfootImage oruga = new GreenfootImage("オルガ", TEXT_SIZE, white, transport, rightGray);
    

    public Story2(){
        super(2, MyWorld.title);
    }
    
    @Override
    public void act() {
        
        if(isFirst){
            isFirst = false;
            MyWorld.usingStage = new Stage2();
        }
        
        checkInput(MyWorld.usingStage);
        progress();
        exitThis(MyWorld.usingStage);
        if(flameCount == 1 && !stopLine){
            switch(lineCount){
                case 0:
                    setName(ari1);
                    break;
                case 1:
                    setName(oruga);
                    break;
                case 2:
                    setName(ari2);
                    break;
                case 4:
                    setName(oruga);
                    break;
                case 11:
                    setName(ari1);
                    break;
                case 12:
                    setName(oruga);
                    break;
                case 13:
                    setName(ari1);
                    break;
                case 18:
                    setName(oruga);
                    break;
                case 19:
                    setName(ari2);
                    break;
                case 20:
                    setName(oruga);
                    break;
                case 24:
                    setName(ari1);
                    break;
                case 26:
                    setName(oruga);
                    break;
                case 27:
                    setName(ari1);
                    break;
                case 28:
                    setName(oruga);
                    break;
                case 29:
                    setName(ari2); //僕は？
                    break;
                case 30:
                    setName(oruga);
                    break;
                case 31:
                    setName(ari1);
                    break;
                case 32:
                    setName(ari2);
                    break;
                case 33:
                    setName(ari1);
                    break;
                case 34:
                    removeObject(nameDisplay);
                    removeObject(name);
                    break;
                case 35:
                    setName(oruga);
                    break;
                case 36:
                    setName(ari1);
                    break;
                case 37:
                    setName(ari2);
                    break;
            }
        }
    }
}
