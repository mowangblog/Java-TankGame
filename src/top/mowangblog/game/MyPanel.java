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
@SuppressWarnings("all")
public class MyPanel extends JPanel implements KeyListener, Runnable {
    //我方坦克
    public static HeroTank heroTank = null;
    //敌方坦克
    public static Vector<EnemyTank> enemyTanks = new Vector<>();

    int enemyNum = 5;
    //爆炸图片
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    //爆炸
    public static Vector<Bomb> bombs = new Vector<>();

    public MyPanel() {
        //初始化我方坦克
        this.heroTank = new HeroTank(500, 400, 3, 5);
        //初始化敌方坦克
        for (int i = 0; i < enemyNum; i++) {
            EnemyTank enemyTank = new EnemyTank((200 + (i * 160)), 100, 2, 5);
            enemyTanks.add(enemyTank);
            Thread thread = new Thread(enemyTank);
            thread.start();
        }
        //初始化爆炸图片
        image1 = Toolkit.getDefaultToolkit().createImage(Panel.class.getResource("/bomb_1.gif"));
        image2 = Toolkit.getDefaultToolkit().createImage(Panel.class.getResource("/bomb_2.gif"));
        image3 = Toolkit.getDefaultToolkit().createImage(Panel.class.getResource("/bomb_3.gif"));
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(0, 0, 1980, 1080);
        //我方坦克存活则继续游戏，被击毁则游戏结束
        if (heroTank != null && heroTank.isLive()) {
            drawTank(heroTank.getX(), heroTank.getY(), g, heroTank.getDirect(), heroTank.getType());
        } else {
            heroTank.setX(-100);
            heroTank.setY(-100);
//            System.out.println("游戏结束");
        }
        //遍历我方炮弹
        for (int i = 0; i < heroTank.bullets.size(); i++) {
            Bullet bullet = heroTank.bullets.get(i);
            if (bullet.isLive()) {
                //设置我方炮弹颜色
                g.setColor(Color.YELLOW);
                drawBullet(bullet.getX(), bullet.getY(), g);
                for (int j = 0; j < enemyTanks.size(); j++) {
                    EnemyTank enemyTank = enemyTanks.get(j);
                    //遍历敌方坦克，判断我方子弹是否击中敌方坦克
                    hitTank(bullet, enemyTank);
                }
            } else {
                heroTank.bullets.remove(bullet);
            }
        }
        //画出敌方坦克和敌方坦克打出的炮弹
        for (int i = 0; i < enemyTanks.size(); ++i) {
            EnemyTank enemyTank = enemyTanks.get(i);
            if (enemyTank.isLive()) {
                drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), enemyTank.getType());
            } else {
                enemyTanks.remove(enemyTank);
            }
            //遍历敌方坦克炮弹
            for (int j = 0; j < EnemyTank.bullets.size(); j++) {
                Bullet bullet = EnemyTank.bullets.get(j);
                if (bullet.isLive()) {
                    drawBullet(bullet.getX(), bullet.getY(), g);
                    //判断炮弹有没有击中我方坦克
                    hitTank(bullet, heroTank);
                } else {
                    EnemyTank.bullets.remove(bullet);
                }
            }
        }

        //画出爆炸效果
        for (int i = 0; i < bombs.size(); i++) {
            Bomb bomb = bombs.get(i);
            if (bomb.isLive()) {
                if (bomb.getLife() > 20) {
                    g.drawImage(image1, bomb.getX(), bomb.getY(), 50, 50, this);
                } else if (bomb.getLife() > 10) {
                    g.drawImage(image2, bomb.getX(), bomb.getY(), 50, 50, this);
                } else {
                    g.drawImage(image3, bomb.getX(), bomb.getY(), 50, 50, this);
                }
                bomb.lifeDown();
            } else {
                bombs.remove(bomb);
            }
        }

    }

    public void hitTank(Bullet bullet, Tank tank) {
        switch (tank.getDirect()) {
            case 0:
            case 2:
                if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 40
                        && bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 60) {
                    //击中坦克根据坦克类型进行销毁
                    if (tank instanceof EnemyTank) {
                        ((EnemyTank) tank).setLive(false);
                        bullet.setLive(false);
                    } else if (tank instanceof HeroTank) {
                        ((HeroTank) tank).setLive(false);
                        bullet.setLive(false);
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                    this.repaint();
                }
                break;
            case 1:
            case 3:
                if (bullet.getX() > tank.getX() && bullet.getX() < tank.getX() + 60
                        && bullet.getY() > tank.getY() && bullet.getY() < tank.getY() + 40) {
                    //击中坦克根据坦克类型进行销毁
                    if (tank instanceof EnemyTank) {
                        ((EnemyTank) tank).setLive(false);
                        bullet.setLive(false);
                    } else if (tank instanceof HeroTank) {
                        ((HeroTank) tank).setLive(false);
                        bullet.setLive(false);
                    }
                    Bomb bomb = new Bomb(tank.getX(), tank.getY());
                    bombs.add(bomb);
                    this.repaint();
                }
                break;
            default:
                break;
        }

    }

    public void drawBullet(int x, int y, Graphics g) {
        g.draw3DRect(x, y, 3, 3, false);
    }

    /**
     * 画坦克方法
     *
     * @param x      x轴
     * @param y      y轴
     * @param g      画笔
     * @param direct 方向 0上 1右 2下 3左
     * @param type   类型 0我方,1敌方
     */
    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //绘制不同类型的坦克
        //根据类型决定坦克颜色 类型 0我方,1敌方
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
        //绘制不同方向的坦克
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
        //按下J键
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //坦克射击
            heroTank.shot();
        }
        //按下w键
        if (e.getKeyCode() == KeyEvent.VK_W) {
            //设置坦克移动方向为上
            heroTank.move(Tank.DIRECT_UP);
        }
        //按下D键
        if (e.getKeyCode() == KeyEvent.VK_D) {
            //设置坦克移动方向为右
            heroTank.move(Tank.DIRECT_RIGHT);
        }
        //按下S键
        if (e.getKeyCode() == KeyEvent.VK_S) {
            //设置坦克移动方向为下
            heroTank.move(Tank.DIRECT_DOWN);
        }
        //按下A键
        if (e.getKeyCode() == KeyEvent.VK_A) {
            //设置坦克移动方向为左
            heroTank.move(Tank.DIRECT_LEFT);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }

    }
}
