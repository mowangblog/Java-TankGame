package top.mowangblog.game;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Java-TankGame
 * 播放音乐
 *
 * @author : Xuan Li
 * @date : 2021-9-19 12:45:29
 **/
public class AePlayWave extends Thread {
    private String filename;

    public AePlayWave(String wavfile) { //构造器 , 指定文件
        filename = wavfile;

    }

    @Override
    public void run() {
        while (true) {
            File soundFile = new File(filename);

            AudioInputStream audioInputStream = null;
            try {
                audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            } catch (Exception e1) {
                e1.printStackTrace();
                return;
            }

            AudioFormat format = audioInputStream.getFormat();
            SourceDataLine auline = null;
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            try {
                auline = (SourceDataLine) AudioSystem.getLine(info);
                auline.open(format);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            auline.start();
            int nBytesRead = 0;
            //这是缓冲
            byte[] abData = new byte[512];

            try {
                while (nBytesRead != -1) {
                    nBytesRead = audioInputStream.read(abData, 0, abData.length);
                    if (nBytesRead >= 0)
                        auline.write(abData, 0, nBytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
                return;
            } finally {
                auline.drain();
                auline.close();
            }
        }

    }
}
