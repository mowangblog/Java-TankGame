package top.mowangblog.game;

/**
 * Java-TankGame
 * 爆炸效果
 *
 * @author : Xuan Li
 * @date : 2021-09-16 14:32
 **/
public class Bomb {
    private int x;
    private int y;

    private boolean isLive = true;
    private int life = 30;

    public Bomb(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public void lifeDown(){
        if(life > 0){
            life--;
        }else {
            isLive = false;
        }
    }
}
