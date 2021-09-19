package top.mowangblog.game;

import java.io.Serializable;

/**
 * Java-TankGame
 * 子弹
 *
 * @author : Xuan Li
 * @date : 2021-09-15 23:43
 **/
@SuppressWarnings("all")
public class Bullet implements Runnable, Serializable {
    private int x;//子弹x
    private int y;//子弹y
    private int speed;//子弹速度
    private int direct;//子弹方向
    private boolean isLive = true;

    public Bullet(int x, int y, int speed, int direct, MyPanel mp) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direct = direct;
    }


    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    public void BulletMove() {
        while (true) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    x += speed;
                    break;
                case 2:
                    y += speed;
                    break;
                case 3:
                    x -= speed;
                    break;
                default:
                    break;
            }
            if (!(x >= 0 && y >= 0 && x <= 980 && y <= 780)) {
                isLive = false;
                return;
            }
        }

    }

    public int getDirect() {
        return direct;
    }

    public void setDirect(int direct) {
        this.direct = direct;
    }

    public Bullet(int x, int y, int speed, int direct) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.direct = direct;
    }

    public Bullet(int x, int y, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public Bullet(int x, int y) {
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }


    @Override
    public void run() {
        BulletMove();
    }
}
