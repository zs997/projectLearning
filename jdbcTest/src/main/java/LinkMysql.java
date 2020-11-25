import utils.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LinkMysql {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        //获取数据库连接
        Connection connection = JdbcUtil.getConnection();
        //需要执行的sql语句
        String sql = "insert into orders(orderNo,brandNo,brandName,num,createTime) values(?,?,?,?,?)";
        //4.获取预处理对象，并依次给参数赋值
        PreparedStatement statement = connection.prepareCall(sql);
        statement.setInt(1,693745); //数据库字段类型是int，就是setInt；1代表第一个参数
        statement.setString(2,"97391B");    //数据库字段类型是String，就是setString；2代表第二个参数
        statement.setString(3,"黑色飞鸟茶灯蜡烛"); //数据库字段类型是int，就是setInt；3代表第三个参数
        statement.setInt(4,16); //数据库字段类型是int，就是setInt；3代表第三个参数
        statement.setString(5,"2011/12/9"); //数据库字段类型是int，就是setInt；3代表第三个参数
        //执行sql语句（插入了几条记录，就返回几）
        int i = statement.executeUpdate();  //executeUpdate：执行并更新
        System.out.println(i);
        //关闭jdbc连接
        statement.close();
        connection.close();
    }
}
