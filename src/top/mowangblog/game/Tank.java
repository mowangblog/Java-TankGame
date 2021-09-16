package top.mowangblog.game;

import java.awt.*;

/**
 * Java-TankGame
 * tank
 *
 * @author : Xuan Li
 * @date : 2021-09-14 20:22
 **/
@SuppressWarnings("all")
public class Tank {
    private int x;
    private int y;
    private int direct;
    private int speed;
    private int type;
    public static final int DIRECT_UP = 0;
    public static final int DIRECT_RIGHT = 1;
    public static final int DIRECT_DOWN = 2;
    public static final int DIRECT_LEFT = 3;
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }


    public void move(int direct){
        switch (direct) {
            case 0:
                if (!(y > 0)) {
                    break;
                }
                this.setDirect(direct);
                y-=speed;
                break;
            case 1:
                if (!(x + 60 < 980)) {
                    break;
                }
                this.setDirect(direct);
                x+=speed;
                break;
            case 2:
                if (!(y + 60 < 780)) {
                    break;
                }
                this.setDirect(direct);
                y+=speed;
                break;
            case 3:
                if (!(x >= 0)) {
                    break;
                }
                this.setDirect(direct);
                x-=speed;
                break;
            default:
                break;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Tank(int x, int y, int direct, int speed) {
        this.x = x;
        this.y = y;
        this.direct = direct;
        this.speed = speed;
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

    public Tank(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
