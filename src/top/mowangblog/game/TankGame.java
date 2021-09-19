package top.mowangblog.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.Scanner;

/**
 * Java-TankGame
 * 坦克大战
 *
 * @author : Xuan Li
 * @date : 2021-09-14 20:25
 **/
@SuppressWarnings("all")
public class TankGame extends JFrame {
    MyPanel myPanel = null;
    public static boolean isNew = true;
    public static void main(String[] args) {
        TankGame tankGame = new TankGame();
    }
    public TankGame() {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.println("=====坦克大战=====");
            System.out.println("1,开始新游戏");
            System.out.println("2,继续游戏");
            System.out.println("3,退出游戏");
            String next = scanner.next();
            if(next.toLowerCase().equals("1")){
                isNew = true;
                break;
            }
            if(next.toLowerCase().equals("2")){
                isNew = false;
                break;
            }
            if(next.toLowerCase().equals("3")){
                System.exit(0);
            }
        }

        this.myPanel = new MyPanel();
        //启动画布线程，实现自动刷新
        Thread thread = new Thread(this.myPanel);
        thread.start();
        //添加画板
        this.add(myPanel);
        //设置尺寸
        this.setSize(1280, 780);
        //点击x退出程序
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //添加key监听器
        this.addKeyListener(myPanel);
        //设置可见
        this.setVisible(true);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Recorder.keepRecorder();
            }

            @Override
            public void windowOpened(WindowEvent e) {
                super.windowOpened(e);
                if (!isNew) {
                    Recorder.readRecorder();
                }
            }
        });

    }


}
