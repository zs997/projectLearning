package cn.zs.data;
import com.opencsv.CSVParser;
import com.opencsv.CSVReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Data {
   //读取txt文件模板
    public void readTxt(String filePath)
    {
      // "d:/works/warehouse-cplus2019/data/input/input-case1.txt";
        try {
            String encoding="GBK";
            File file=new File(filePath);
            if(file.isFile() && file.exists()){ //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file),encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String data ;
                if ((data = bufferedReader.readLine()) != null){
                    System.out.println(data);
                }
                data =data.trim();
                String[] datas = data.split("\\s+");
                for (int i = 0; i < datas.length; i++) {
                    System.out.println("data:"+datas[i]);
                }
                read.close();
            }else{
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }
    //写txt模板
    public  void writeTxt(String txtPath){
        // data.writeTxt("d:/works/test"+System.currentTimeMillis()+".txt");
        //String txtPath = "d:/works/warehouse-cplus2019/data/output/test.txt";
        String content = " aedad阿斯蒂芬123456  dasdas111";
        FileOutputStream fileOutputStream = null;
        File file = new File(txtPath);
        if(!file.exists()){
            //判断文件是否存在，如果不存在就新建一个txt
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 100; i++) {
            try {
                fileOutputStream.write(content.getBytes());
                String huanhang = System.getProperty("line.separator");
                fileOutputStream.write(huanhang.getBytes());
                 fileOutputStream.flush();
                System.out.println(i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //读取csv 模板
    public ArrayList<ArrayList<String>> readCsv(String path) {
        ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();
        try {
            DataInputStream in = new DataInputStream(new FileInputStream(new File(path)));
            CSVReader csvReader = new CSVReader(new InputStreamReader(in, "GBK"), CSVParser.DEFAULT_SEPARATOR,
                    CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER, 0);
            String[] strs;
            while ((strs = csvReader.readNext()) != null) {
                data.add(new ArrayList<String>(Arrays.asList(strs)));
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }
    public static void main(String[] args) {
        Data data = new Data();
        data.writeTxt("d:/works/test"+System.currentTimeMillis()+".txt");
    }




}
