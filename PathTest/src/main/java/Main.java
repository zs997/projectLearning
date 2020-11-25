import java.io.*;
import java.net.URL;
import java.util.Date;

public class Main {
    public static void main(String args[])   {


        System.out.println(System.getProperty("user.dir"));//D:\works\projectLearning
    //   在idea运行中，如果是子工程，是父根工程所在的文件夹
        //   在class类运行或者jar包运行时 是class文件或者jar包文件所在路径（运行时路径）

        File file = new File("a.txt");
        System.out.println(file.getAbsolutePath());//D:\works\projectLearning\a.txt
        //项目所在路径（运行时所在路径）下的a.txt

        File file1 = new File(System.getProperty("user.dir") + "\\a.txt");
        System.out.println(file1.getAbsolutePath());//D:\works\projectLearning\a.txt
        //

        File file2 = new File("\\b\\a.txt");
        System.out.println(file2.getAbsolutePath());//D:\b\a.txt
        //相对于盘符路径

        File file3 = new File(System.getProperty("user.dir") + "\\b\\a.txt");
        System.out.println(file3.getAbsolutePath());
        //D:\works\projectLearning\b\a.txt

        //是工作路径下的文件 相当于 user.dir+//a.txt   创建的文件跟随 user.dir变化而变化
   // 所以 user.dir+文件名 可以作为程序的数据源，在idea中是工程目录；单独运行时，是程序所在目录。文件可以读写。

        /*
            一般通过maven编译，打包，都不会将jre之外的依赖导入目标文件夹，只是将pom文件导入了目标。
            maven打包的程序，只适合作为仓库用。(如果在pom文件中安装插件，也可以将依赖包一并打包，使用maven package)
            如果程序中没有jre之外的依赖，可以通过命令行运行class文件（多个class，有包名，都可以
            java cn.zs.test.Main）或者jar文件（java -cp reserch.jar cn.zs.Main）
            如果有额外依赖，想让程序单独运行，需要通过file->project structure ->Artifacts build
        */
        /*
         用maven编译的时候，java源码文件夹里面的java文件会编译到target/classes文件夹，称之为类文件夹classpath。
         标记为resources的文件夹内的所有文件默认放到target/classes文件夹内（pom配置可以修改，也可以修改其他目录作为资源），
         既resource也会搬运到classpath根目录。
         Main.class.getClassLoader().getResourceAsStream("a");
         指的就是目标目录下a资源。
         在编译后，不打包，如果通过java命令运行程序，可以执行，并对资源文件进行读写（因为没有打包，打包相当于压缩了）
         如果通过maven打包了，打jar包就是class文件夹压缩包，作为程序一部分，此时资源文件也被打包进去了，所以打包时资源变成了只读。
         所以资源目录一般用来初始化配置使用
         使用maven编译或者打包成为jar 本质是一样的，都不会将依赖一块打包，所以此时jar包只能被别的项目依赖，或者没有jre之外的依赖时，可以单独运行。

        */


        //String path = Main.class.getClassLoader().getResource("a").getPath();
        //得到资源路径，可以用文件File读写
        //字节流读取文件,也可以使用字符流包装下
        //InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("a");
    }
    /**
     * @description: 将输入流转换成字符串
     * @author:
     * @date: 2019年12月15日
     * @param inputStream 输入流
     * @return
     */
    private static String inputStreamToString(InputStream inputStream) {
        StringBuffer buffer = new StringBuffer();
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }

    public void readWriteTest(){
        //String path = this.getClass().getClassLoader().getResource("./prop/file.txt").getPath();//"./prop/file.txt"效果一样
        String path = this.getClass().getClassLoader().getResource("a").getPath();
        System.out.println(path);
        System.out.println("---------------------------------------------------------------");
        path = path.substring(1);
        System.out.println(path);
        System.out.println("--------------------------------------------------------------");
        path = path.replaceAll("/", "\\\\");
        System.out.println(path);
        System.out.println("--------------------------------------------------------------");
        path = path.replaceAll("\\\\", "\\\\\\\\");
        System.out.println(path);

        System.out.println("先读取数据");
        InputStream a = this.getClass().getClassLoader().getResourceAsStream("a");
        String s = inputStreamToString(a);
        System.out.println(s);

        Date date = new Date();
        String key = date.toString();

        //创建文件将原有文件覆盖删除
//        try {
//            FileOutputStream fos = new FileOutputStream(new File(path));
//            fos.write(key.getBytes());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter(path, true);
            writer.write(key);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
