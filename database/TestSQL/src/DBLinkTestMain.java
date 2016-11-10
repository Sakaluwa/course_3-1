//导入java中数据库sql相关的java类
import java.sql.Connection;//
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Created by 94839 on 2016/10/24.
 */

public class DBLinkTestMain {
    public static void main(String args[]) {
        // 连接url:本地服务器地址，端口号，数据库名称，登录名，登录密码
        String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Test;user=sakaluwa;password=241535";//sakaluwa身份连接

        // Declare the JDBC objects.
        Connection con = null;//连接对象
        Statement stmt = null;//SQL指令集（陈述，说明）
        ResultSet rs = null;//查询结果集

        try {
            // 建立连接
            System.out.println("开始连接···");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载JDBC驱动
            con = DriverManager.getConnection(url);//解析url
            System.out.println("连接成功.");

            // 创建并执行一条SQL语句，返回一些数据
            String SQL = "SELECT  * FROM People";
            String SQL2="CREATE TABLE Test (userid int,username nchar(10),phonenumber char(11))";
            stmt = con.createStatement();
            stmt.executeUpdate(SQL2);

//            // 迭代结果集合中的数据，并且显示它
//            while (rs.next()) {
//
//                System.out.println(rs.getString(1) + " " + rs.getString(2)+" " + rs.getString(3)+" " + rs.getString(4));
//            }
             }

        // 处理任何可能出现的错误
        catch (Exception e) {
            e.printStackTrace();
        }
        /*finally块确保close方法总被执行（释放内存资源），而不管try块内是否发出异常*/
        finally {
            if (rs != null)
                try {
                    rs.close();
                } catch (Exception e) {
                }
            if (stmt != null)
                try {
                    stmt.close();
                } catch (Exception e) {
                }
            if (con != null)
                try {
                    con.close();
                } catch (Exception e) {
                }
        }
    }
}
