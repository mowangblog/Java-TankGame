package top.mowangblog.game;

/**
 * Java-TankGame
 * 英雄
 *
 * @author : Xuan Li
 * @date : 2021-09-14 20:23
 **/
public class HeroTank extends Tank{
    private int type = 0;

    @Override
    public int getType() {
        return type;
    }

    @Override
    public void setType(int type) {
        this.type = type;
    }

    public HeroTank(int x, int y, int direct, int speed) {
        super(x, y, direct, speed);
    }

    public HeroTank(int x, int y) {
        super(x, y);
    }
}
