package top.mowangblog.game;

import javax.swing.*;
import java.awt.*;

/**
 * Java-TankGame
 * 坦克大战
 *
 * @author : Xuan Li
 * @date : 2021-09-14 20:25
 **/
public class TankGame extends JFrame {
    MyPanel myPanel = null;
    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
    public TankGame() {
        this.myPanel = new MyPanel();
        Thread thread = new Thread(this.myPanel);
        thread.start();
        //添加画板
        this.add(myPanel);
        //设置尺寸
        this.setSize(980,780);
        //点击x退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加key监听器
        this.addKeyListener(myPanel);
        //设置可见
        this.setVisible(true);

    }


}
