package auto;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class main {
    private final static int send_count = 20;


    public static void main(String[] args) throws Exception {
        System.out.println("==========================================");
        System.out.println("请选择模式");
        System.out.println("==========================================");
        System.out.println("0.鸡汤");
        System.out.println("==========================================");
        System.out.println("1.滚刀");
        System.out.println("==========================================");
        System.out.println("2.口吐芬芳模式");
        System.out.println("==========================================");
        System.out.println("3.嘲讽");
        System.out.println("==========================================");
        System.out.println("4.毒鸡汤");
        System.out.println("==========================================");
        System.out.println("5.随机一句");
        System.out.println("==========================================");
        System.out.println();
        Scanner scanner = new Scanner(System.in);
        int i = scanner.nextInt();
        if (i>=0&&i<=5){
            auto(i);
        }else {
            System.out.println("输入有误");
        }

    }
    public static void auto(int i) throws Exception {
        String os = System.getProperty("os.name");
        Integer ctrl;
        if (os.charAt(0)=='W'){
            ctrl=17;
        }else {
            ctrl=157;
        }
        String str = new String();
        Robot robot = new Robot();
        robot.delay(1000);
        Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
        String[] authors = str.split(",");
        for (int j = 1; j < (send_count + 1); j++) {
            str =   api(i);
            str = str.replaceAll("<p>|</p>", "");
            System.out.println(str);
            Transferable text = new StringSelection(str);
            clip.setContents(text, null);
            robot.keyPress(ctrl);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(ctrl);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);

        }

        System.out.println("stop");
    }


    public static String api(int i) throws Exception {
        // 创建URL对象
        String[] URLs = {
                "https://v.api.aa1.cn/api/yiyan/index.php",
                "https://api.pearktrue.cn/api/zuan/gd.php",
                "https://api.pearktrue.cn/api/zuan/ktff.php",
                "https://api.pearktrue.cn/api/zuan/cf.php",
                "https://api.wer.plus/api/djt",
                "https://v1.hitokoto.cn/?encode=text"};
        URL url = new URL(URLs[i]);
        // 打开连接
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        // 设置请求方法（GET、POST等）
        conn.setRequestMethod("GET");

        // 获取响应状态码
        int responseCode = conn.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) {
            // 读取响应内容
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            return "  ";
        }
    }

}