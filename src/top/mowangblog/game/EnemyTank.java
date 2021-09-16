package top.mowangblog.game;

import java.util.Vector;

/**
 * Java-TankGame
 * 敌方坦克
 *
 * @author : Xuan Li
 * @date : 2021-09-14 21:00
 **/
@SuppressWarnings("all")
public class EnemyTank extends Tank{
    int type = 1;
    Bullet bullet = null;
    public static Vector<Bullet> bullets = new Vector<>();
    private boolean isLive = true;

    public boolean isLive() {
        return isLive;
    }

    public void setLive(boolean live) {
        isLive = live;
    }

    @Override
    public int getType() {
        return type;
    }

    public void shot() {
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
    public void setType(int type) {
        this.type = type;
    }

    public EnemyTank(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }
}
