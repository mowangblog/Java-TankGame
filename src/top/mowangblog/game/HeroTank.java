package top.mowangblog.game;

import java.util.Vector;

/**
 * Java-TankGame
 * 英雄
 *
 * @author : Xuan Li
 * @date : 2021-09-14 20:23
 **/
@SuppressWarnings("all")
public class HeroTank extends Tank{
    private int type = 0;
    Bullet bullet = null;
    Vector<Bullet> bullets = new Vector<>();

    public HeroTank(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }

    public HeroTank(int x, int y) {
        super(x, y);
    }

    public void shot() {
        if(bullets.size() >= 5){
            return;
        }
        //根据坦克方向绘制子弹
        switch (getDirect()) {
            case 0:
                bullet = new Bullet(getX() + 17, getY() - 10, 5, getDirect());
                break;
            case 1:
                bullet = new Bullet(getX() + 65, getY() + 18, 5, getDirect());
                break;
            case 2:
                bullet = new Bullet(getX() + 18, getY() + 65, 5, getDirect());
                break;
            case 3:
                bullet = new Bullet(getX() - 10, getY() + 18, 5, getDirect());
                break;
            default:
                break;
        }
        bullets.add(bullet);
        Thread thread = new Thread(bullet);
        thread.start();
    }
    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

}
