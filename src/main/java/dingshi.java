import com.alibaba.fastjson.JSONObject;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class dingshi {
    public static void main(String[] args) throws IOException {
        FileWriter fw = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Boolean result = false;
        int count = 0;
        try {
            File f = null;
            if(winlinux.isLinux()) {
                f=new File("/root/test.txt");   //linux路径
            }else {
                f=new File("G:\\test.txt");   //win路径，如果文件存在，则追加内容；如果文件不存在，则创建文件
            }
            fw = new FileWriter(f, true);        //true:追加写入，换行
        } catch (IOException e) {
            e.printStackTrace();
        }
        while(!result) {
            try {
                Thread.sleep(2);   //设置暂停的时间 0.002 秒    :5*1000=5s  休眠
                count ++ ;                    //循环次数控制
                PrintWriter pw = new PrintWriter(fw,true);

                JSONObject object = new JSONObject();            //json格式的
                object.put("起始时间",System.currentTimeMillis());
                pw.println(object);

                //   pw.println("追加内容"+System.currentTimeMillis());            //写入txt
                pw.flush();
                if (count == 500000 ) {           //循环次数为五十万
                    result = true;
                    break ;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            fw.flush();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}