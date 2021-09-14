package top.mowangblog.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

/**
 * Java-TankGame
 * 游戏区
 *
 * @author : Xuan Li
 * @date : 2021-09-14 20:23
 **/
public class MyPanel extends JPanel implements KeyListener {
    HeroTank heroTank = null;
    Vector<EnemyTank> enemyTanks = new Vector<>();
    int enemyNum = 10;

    public MyPanel() {
        this.heroTank = new HeroTank(500, 400, 0, 5);
        for (int i = 0; i < enemyNum; i++) {
            enemyTanks.add(new EnemyTank((200+(i*60)), 100, 2, 5));
        }

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1980, 1080);
        drawTank(heroTank.getX(), heroTank.getY(), g, heroTank.getDirect(), heroTank.getType());
        for (EnemyTank enemyTank : enemyTanks) {
            drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), enemyTank.getType());
        }
    }

    /**
     * 画坦克方法
     *
     * @param x      x轴
     * @param y      y轴
     * @param g      画笔
     * @param direct 方向 0上 1右 2下 3左
     * @param type   类型 0我方,1敌法
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        switch (type) {
            case 0:
                g.setColor(Color.YELLOW);
                break;
            case 1:
                g.setColor(Color.cyan);
                break;
            default:
                g.setColor(Color.MAGENTA);
                break;
        }
        switch (direct) {
            case 0:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y, x + 20, y + 30);
                break;
            case 1:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x + 60, y + 20);
                break;
            case 2:
                g.fill3DRect(x, y, 10, 60, false);
                g.fill3DRect(x + 30, y, 10, 60, false);
                g.fill3DRect(x + 10, y + 10, 20, 40, false);
                g.fillOval(x + 10, y + 20, 20, 20);
                g.drawLine(x + 20, y + 60, x + 20, y + 30);
                break;
            case 3:
                g.fill3DRect(x, y, 60, 10, false);
                g.fill3DRect(x, y + 30, 60, 10, false);
                g.fill3DRect(x + 10, y + 10, 40, 20, false);
                g.fillOval(x + 20, y + 10, 20, 20);
                g.drawLine(x + 30, y + 20, x, y + 20);
                break;
            default:
                break;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            heroTank.setDirect(Tank.DIRECT_UP);
            heroTank.move(Tank.DIRECT_UP);
        }

        if (e.getKeyCode() == KeyEvent.VK_D) {
            heroTank.setDirect(Tank.DIRECT_RIGHT);
            heroTank.move(Tank.DIRECT_RIGHT);
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {
            heroTank.setDirect(Tank.DIRECT_DOWN);
            heroTank.move(Tank.DIRECT_DOWN);
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {
            heroTank.setDirect(Tank.DIRECT_LEFT);
            heroTank.move(Tank.DIRECT_LEFT);
        }
        repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
