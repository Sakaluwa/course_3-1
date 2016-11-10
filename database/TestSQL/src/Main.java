import java.sql.*;//导入sql相关的java基础类

public class Main {

public static void main(String [] args) {
    String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";//驱动器名称
    String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=Test";//数据库url,数据库名称
    String userName = "sakaluwa";
    String userPwd = "241535";
    try{
        Class.forName(driverName);//驱动数据库
        Connection dbConn = DriverManager.getConnection(dbURL, userName, userPwd);//连接
        System.out.println("连接数据库成功");

        String  SQL="SELECT * FROM People";
        Statement stmt=dbConn.createStatement();
        ResultSet rs=stmt.executeQuery(SQL);
        while (rs.next()) {
            System.out.println(rs.getString(1) + " " + rs.getString(2)+" " + rs.getString(3)+" " + rs.getString(4));
        }

    }
    catch (Exception e)
    {
        e.printStackTrace();
        System.out.println("连接失败");
    }

    }

}