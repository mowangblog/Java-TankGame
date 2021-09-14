package top.mowangblog.game;

/**
 * Java-TankGame
 * 敌方坦克
 *
 * @author : Xuan Li
 * @date : 2021-09-14 21:00
 **/
public class EnemyTank extends Tank{
    int type = 1;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public EnemyTank(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }
}
