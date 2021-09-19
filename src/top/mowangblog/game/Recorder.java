package top.mowangblog.game;

import java.io.*;

/**
 * Java-TankGame
 * 记录玩家成绩
 *
 * @author : Xuan Li
 * @date : 2021-09-19 09:34
 **/
public class Recorder {
    private static int cont = 0;
    private static int dieCont = 0;

    private static String recordPath = "src/recorder/CountData.txt";
    private static String recordPath2 = "src/recorder/TankData.dat";
    private static BufferedWriter br = null;
    private static BufferedReader bufferedReader = null;
    private static ObjectOutputStream outputStream = null;
    private static ObjectInputStream inputStream = null;

    public static void keepRecorder() {
        try {
            br = new BufferedWriter(new FileWriter(recordPath));
            br.write(cont + "");
            br.newLine();
            br.write(dieCont + "");

            outputStream = new ObjectOutputStream(new FileOutputStream(recordPath2));
            outputStream.writeInt(MyPanel.enemyTanks.size());
            for (EnemyTank enemyTank : MyPanel.enemyTanks) {
                outputStream.writeObject(enemyTank);
            }
            System.out.println("存盘成功");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }

    public static void readRecorder() {
        try {
            File file = new File(recordPath);
            File file2 = new File(recordPath2);
            if (!file2.exists() || !file.exists()){
                System.out.println("没有记录开始新游戏");
                TankGame.isNew = true;
                return;
            }
            bufferedReader = new BufferedReader(new FileReader(recordPath));
            String line = "";
            int[] arr = new int[2];
            int i = 0;
            while ((line = bufferedReader.readLine()) != null) {
                arr[i++] = Integer.parseInt(line);
            }
            cont = arr[0];
            dieCont = arr[1];
            inputStream = new ObjectInputStream(new FileInputStream(recordPath2));

            int len = inputStream.readInt();
            if (len > 0){
                for (EnemyTank enemyTank : MyPanel.enemyTanks) {
                    enemyTank.setLive(false);
                }
                for (Bullet bullet : EnemyTank.bullets) {
                    bullet.setLive(false);
                }
                MyPanel.enemyTanks.clear();
            }
            for (int j = 0; j < len; j++) {
                MyPanel.enemyTanks.add(((EnemyTank) inputStream.readObject()));
            }
            for (EnemyTank enemyTank : MyPanel.enemyTanks) {
                Thread thread = new Thread(enemyTank);
                thread.start();
            }
            System.out.println("读盘成功");
        } catch (Exception e) {
            System.out.println("读盘失败");
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public int getDieCont() {
        return dieCont;
    }

    public Recorder(int cont, int dieCont) {
        Recorder.cont = cont;
        Recorder.dieCont = dieCont;
    }

    public void setDieCont(int dieCont) {
        Recorder.dieCont = dieCont;
    }


    public void addCont() {
        cont++;
    }

    public void addDieCont() {
        dieCont++;
    }

    @Override
    public String toString() {
        return "Recorder{" +
                "cont=" + cont +
                '}';
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        Recorder.cont = cont;
    }

    public Recorder(int cont) {
        Recorder.cont = cont;
    }
}
