import groovy.sql.Sql;

import java.sql.*;
import java.util.Random;
import java.util.Scanner;
/**
 * Created by 94839 on 2016/11/1.
 */
public class FlightTicket {
    private static Connection con=null;

    public static void main(String[] args) {
        connect_database();//连接数据库
        System.out.println("请输入对机票订票系统的操作：\n 1、flight(航班修改)；\n 2、traveller(旅客修改)；" +
                "\n 3、query(查询航班，旅客等信息)；\n 4、count(统计售票出行)；\n " +
                "5、order(预定机票)；\n 6、refund(退机票)；\n 7、quit(退出系统)；\n");
        Scanner sc=new Scanner(System.in);//System.in代表标准输入，就是键盘输入
        sc.useDelimiter("\n");//Scanner默认以空格作为分隔符，这里以回车作为分隔符
        while(sc.hasNext())
        {
            int flag=0;
            switch (sc.next())
            {
                case "flight":
                    flight();
                    break;
                case "traveller":
                    traveller();
                    break;
                case "query":
                    query();
                    break;
                case "order":
                    order();
                    break;
                case "refund":
                    refund();
                    break;
                case "count":
                    count();
                    break;
                case "quit":
                    System.out.println("成功退出系统！");
                    flag=1;
                    break;
                default:
                    System.out.println("提示：请重新输入正确的主菜单命令！");
            }
            if (flag==1)//flag=1,则退出机票订票系统
                break;
        }
    }
    //连接数据库
    public static void connect_database() {
        String url="jdbc:sqlserver://127.0.0.1:1433;databaseName=机票订票系统;user=sakaluwa;password=241535";

        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//加载JDBC驱动
            con= DriverManager.getConnection(url);//解析url,连接数据库
            if (con!=null)
            {
                System.out.println("连接成功.");
            }
            else
            {
                System.out.println("连接失败！");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    //修改航班信息
    public static void flight() {
        System.out.println("请选择新增/删除（insert/delete）航班信息：");
        Scanner scflight=new Scanner(System.in);
        scflight.useDelimiter("\n");

        while(scflight.hasNext())
        {
            int flag=0;
            switch (scflight.next())
            {
                case "insert":
                    flight_insert();
                    break;
                case "delete":
                    flight_delete();
                    break;
                case "quit":
                    flag=1;
                    break;
                default:
                    System.out.println("提示：请重新输入正确的航班菜单命令！");
            }
            if (flag==1)
                break;
        }
        System.out.println("航班信息修改完毕！");

    }
    //修改旅客信息
    public static void traveller() {
        System.out.println("请选择新增/删除（insert/delete）旅客信息：");
        Scanner sctraveller=new Scanner(System.in);
        sctraveller.useDelimiter("\n");

        while(sctraveller.hasNext())
        {
            int flag=0;
            switch (sctraveller.next())
            {
                case "insert":
                    traveller_insert();
                    break;
                case "delete":
                    traveller_delete();
                    break;
                case "quit":
                    flag=1;
                    break;
                default:
                    System.out.println("提示：请重新输入正确的旅客菜单命令！");
            }
            if (flag==1)
                break;
        }
        System.out.println("旅客信息修改完毕！");

    }
    //查询信息（航班，旅客，机票，订单）
    public static void query() {
        System.out.println("请输入您要查询的信息：\n" +
                "1、iflight(航班信息)；\n2、itraveller(旅客信息)；\n3、iticket(余票信息)；\n4、iorder(订单信息)；\n5、quit(退出查询)；\n");
        Scanner scquery=new Scanner(System.in);
        scquery.useDelimiter("\n");

        while(scquery.hasNext())
        {
            int flag=0;
            switch (scquery.next())
            {
                case "iflight":
                    iflight();
                    break;
                case "itraveller":
                    itraveller();
                    break;
                case "iticket":
                    iticket();
                    break;
                case "iorder":
                    iorder();
                    break;
                case "quit":
                    flag=1;
                    break;
                default:
                    System.out.println("提示：请重新输入正确的查询菜单命令！");
            }
            if (flag==1)
                break;
        }
        System.out.println("信息查询完毕！");
    }
    //预定机票
    public static void order() {
        System.out.println("请输入订票信息(旅客ID，航班编号，出发日期)，例：622826199505241535 XMAIR00001 2016-11-11\n");
        Scanner scorder=new Scanner(System.in);
        scorder.useDelimiter("\n");

        while(scorder.hasNext())
        {
            String order_s = scorder.next();
            String[] arr = order_s.split(" ");
            int flag=0;
            switch (arr[0])
            {
                case "quit":
                    flag=1;
                    break;
                default:
                {
                    try{
                        String order_id=getRandomString(10);
                        String SQL_order="INSERT INTO 订单(order_id,order_traveller,order_flight,order_takeofftime,order_time)" +
                                "VALUES('"+order_id+"','"+arr[0]+"','"+arr[1]+"','"+arr[2]+"','"+arr[2]+"')";
                        sql_exe_manipulate(SQL_order);

                        String SQL_ticket_minus="UPDATE 机票 SET ticket_sum=ticket_sum-1" +
                                "WHERE ticket_flight='"+arr[1]+"' AND ticket_time='"+arr[2]+"'";
                        sql_exe_manipulate(SQL_ticket_minus);
                        System.out.println("预订机票："+order_id+"\n");

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }

            }
            if (flag==1)
            {
                System.out.println("尊敬的顾客，您的机票已预订成功！");
                break;
            }
        }

    }
    //退订机票
    public static void refund() {
        System.out.println("请输入您要退订的机票编号，例：“bp2p9x4gsw”");
        Scanner scorder=new Scanner(System.in);
        scorder.useDelimiter("\n");

        while(scorder.hasNext())
        {
            String order_s = scorder.next();
            String[] arr = order_s.split(" ");
            int flag=0;
            switch (arr[0])
            {
                case "quit":
                    flag=1;
                    break;
                default:
                {
                    try{
                        String SQL_ticket_add="UPDATE 机票 SET ticket_sum=ticket_sum+1" +
                                "WHERE ticket_flight=(SELECT order_flight FROM 订单 WHERE order_id='"+order_s+"')" +
                                "AND ticket_time=(SELECT order_takeofftime FROM 订单 WHERE order_id='"+order_s+"')";
                        sql_exe_manipulate(SQL_ticket_add);
                        System.out.println("退订机票："+order_s+"\n");

                        String SQL_refund="DELETE FROM 订单 WHERE order_id='"+arr[0]+"'";
                        sql_exe_manipulate(SQL_refund);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }
            }
            if (flag==1)
            {
                System.out.println("尊敬的顾客，您的机票已退订成功！");
                break;
            }
        }
    }
    //统计航班售票、旅客出行情况
    public static void count() {
        System.out.println("请输入您要统计的信息：" +
                "\n    航班（月/季度/年）：flight month/season/year;" +
                "\n    旅客（月/季度/年）：traveller month/season/year;\n");
        Scanner scflight_insert=new Scanner(System.in);
        scflight_insert.useDelimiter("\n");

        while(scflight_insert.hasNext())
        {
            String insert_s = scflight_insert.next();
            String[] arr = insert_s.split(" ");
            int flag=0;
            switch (arr[0])
            {
                case "flight":
                    count_flight(arr[1]);
                    break;
                case "traveller":
                    count_traveller(arr[1]);
                    break;
                case "quit":
                    flag=1;
                    break;
                default:
                    System.out.println("请输入正确的统计菜单命令！");
            }
            if (flag==1)
            {
                System.out.println("统计航班/旅客信息完毕.");
                break;
            }
        }
    }
    //统计航班的售票情况
    public static void count_flight(String type) {
        System.out.println("请输入您要查询的航班编号：");
        Scanner scanner=new Scanner(System.in);
        scanner.useDelimiter("\n");
        String flight_id=scanner.next();
        switch (type)
        {
            case "month": {
                System.out.println("请输入您要查询的月份，例：2016年11月份“2016 11“\n");
                Scanner scanner_month = new Scanner(System.in);
                scanner_month.useDelimiter("\n");
                String string = scanner_month.next();
                String[] strings = string.split(" ");
                String time_start = strings[0] + "-" + strings[1] + "-01";
                String time_stop=month_bound(strings);

                try {
                    String SQL_month = "SELECT COUNT(*)" +
                            "FROM 订单" +
                            " WHERE order_flight='" + flight_id + "'" +
                            " AND order_time >='" + time_start + "' AND order_time <='" + time_stop + "'";
                    ResultSet resultSet = sql_exe_query(SQL_month);
                    while (resultSet.next()) {
                        System.out.println(flight_id + "航班" + strings[0] + "年" + strings[1] + "月共计售票： "
                                + resultSet.getString(1) + " 张");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case "season":
                count_flight_season(flight_id);
                break;

            case "year":
                System.out.println("请输入您要查询的年份，例：2016年“2016“\n");
                Scanner scan_count_flight_year=new Scanner(System.in);
                scan_count_flight_year.useDelimiter("\n");
                String year_string=scan_count_flight_year.next();
                String[] year_strings=year_string.split(" ");
                String year_time_start=year_strings[0]+"-01-01";
                String year_time_stop=year_strings[0]+"-12-31";
                try
                {
                    String SQL_year="SELECT COUNT(*)" +
                            "FROM 订单" +
                            " WHERE order_flight='"+flight_id+"'" +
                            " AND order_time >='"+year_time_start+"' AND order_time <='"+year_time_stop+"'";
                    ResultSet year_resultSet=sql_exe_query(SQL_year);
                    while(year_resultSet.next())
                    {
                        System.out.println(flight_id+"航班"+year_strings[0]+"年共计售票： "
                                +year_resultSet.getString(1)+" 张");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
    //按季度统计航班的售票情况
    public static void count_flight_season(String flight_id) {
        System.out.println("请输入你要查询的季度，例：2016年春/夏/秋/冬“2016 spring/summer/autumn/winter”\n");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String season_s = scanner.next();
        String[] arr = season_s.split(" ");
        String time_start = arr[0] + "-01-01";
        String time_stop = arr[0] + "-03-31";
        switch (arr[1])
        {
            case "spring":
                time_start = arr[0] + "-01-01";
                time_stop = arr[0] + "-03-31";
                break;
            case "summer":
                time_start = arr[0] + "-04-01";
                time_stop = arr[0] + "-06-30";
                break;
            case "autumn":
                time_start = arr[0] + "-07-01";
                time_stop = arr[0] + "-09-30";
                break;
            case "winter":
                time_start = arr[0] + "-10-01";
                time_stop = arr[0] + "-12-31";
                break;
        }
        try {
            String SQL_count_flight_season = "SELECT COUNT(*)" +
                    "FROM 订单" +
                    " WHERE order_flight='" + flight_id + "'" +
                    " AND order_time >='" + time_start + "' AND order_time <='" + time_stop + "'";
            ResultSet season_resultSet = sql_exe_query(SQL_count_flight_season);
            while (season_resultSet.next()) {
                System.out.println(flight_id + "航班" + arr[0] + "年" + arr[1] + "季度共计售票： "
                        + season_resultSet.getString(1) + " 张");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //统计旅客出行情况
    public static void count_traveller(String type) {
        System.out.println("请输入您要查询的旅客ID号：");
        Scanner scanner=new Scanner(System.in);
        scanner.useDelimiter("\n");
        String traveller_id=scanner.next();
        switch (type)
        {
            case "month": {
                System.out.println("请输入您要查询的月份，例：2016年11月份“2016 11“\n");
                Scanner scanner_month = new Scanner(System.in);
                scanner_month.useDelimiter("\n");
                String string = scanner_month.next();
                String[] strings = string.split(" ");
                String time_start = strings[0] + "-" + strings[1] + "-1";
                String time_stop = month_bound(strings);
                try {
                    String SQL_count_m = "SELECT COUNT(*)" +
                            "FROM 订单" +
                            " WHERE order_traveller='" + traveller_id + "'" +
                            " AND order_takeofftime >='" + time_start + "' AND order_takeofftime <='" + time_stop + "'";
                    ResultSet resultSet = sql_exe_query(SQL_count_m);
                    while (resultSet.next()) {
                        System.out.println(traveller_id + "旅客" + strings[0] + "年" + strings[1] + "月共计出行： "
                                + resultSet.getString(1) + " 次");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
            }
            case "season":
                count_traveller_season(traveller_id);
                break;

            case "year":
                System.out.println("请输入您要查询的年份，例：2016年“2016“\n");
                Scanner scan_count_flight_year=new Scanner(System.in);
                scan_count_flight_year.useDelimiter("\n");
                String year_string=scan_count_flight_year.next();
                String[] year_strings=year_string.split(" ");
                String year_time_start=year_strings[0]+"-01-01";
                String year_time_stop=year_strings[0]+"-12-31";
                try
                {
                    String SQL_year="SELECT COUNT(*)" +
                            "FROM 订单" +
                            " WHERE order_traveller='"+traveller_id+"'" +
                            " AND order_takeofftime >='"+year_time_start+"' AND order_takeofftime <='"+year_time_stop+"'";
                    ResultSet year_resultSet=sql_exe_query(SQL_year);
                    while(year_resultSet.next())
                    {
                        System.out.println(traveller_id+"旅客"+year_strings[0]+"年共计出行： "
                                +year_resultSet.getString(1)+" 次");
                    }
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
        }
    }
    //按季度统计旅客出行情况
    public static void count_traveller_season(String traveller_id) {
        System.out.println("请输入你要查询的季度，例：2016年春/夏/秋/冬“2016 spring/summer/autumn/winter”\n");
        Scanner scanner = new Scanner(System.in);
        scanner.useDelimiter("\n");
        String season_s = scanner.next();
        String[] arr = season_s.split(" ");
        String time_start = arr[0] + "-01-01";
        String time_stop = arr[0] + "-03-31";
        switch (arr[1])
        {
            case "spring":
                time_start = arr[0] + "-01-01";
                time_stop = arr[0] + "-03-31";
                break;
            case "summer":
                time_start = arr[0] + "-04-01";
                time_stop = arr[0] + "-06-30";
                break;
            case "autumn":
                time_start = arr[0] + "-07-01";
                time_stop = arr[0] + "-09-30";
                break;
            case "winter":
                time_start = arr[0] + "-10-01";
                time_stop = arr[0] + "-12-31";
                break;
        }
        try {
            String SQL_season = "SELECT COUNT(*)" +
                    "FROM 订单" +
                    " WHERE order_traveller='" + traveller_id + "'" +
                    " AND order_takeofftime >='" + time_start + "' AND order_takeofftime <='" +time_stop + "'";
            ResultSet season_resultSet = sql_exe_query(SQL_season);
            while (season_resultSet.next()) {
                System.out.println(traveller_id + "旅客" + arr[0] + "年" + arr[1] + "季度共计出行： "
                        + season_resultSet.getString(1) + " 次");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //新增航班
    public static void flight_insert() {
        System.out.println("请输入航班信息，例：“XMAIR00001 厦门 西安”");
        Scanner scflight_insert=new Scanner(System.in);
        scflight_insert.useDelimiter("\n");

        while(scflight_insert.hasNext())
        {
            String insert_s = scflight_insert.next();
            String[] arr = insert_s.split(" ");
            int flag=0;
            switch (arr[0])
            {
                case "quit":
                    flag=1;
                    break;
                default:
                {
                    try{
                        String SQL_flight_insert="INSERT INTO 航班(flight_id,flight_src,flight_dst)" +
                                "VALUES('"+arr[0]+"','"+arr[1]+"','"+arr[2]+"')";
                        sql_exe_manipulate(SQL_flight_insert);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }
            if (flag==1)
            {
                System.out.println("插入航班信息完毕.");
                break;
            }
        }

    }
    //删除航班
    public static void flight_delete() {

        System.out.println("请输入要删除的航班号，例:'XMAIR00001'");
        Scanner scflight_delete=new Scanner(System.in);
        scflight_delete.useDelimiter("\n");

        while (scflight_delete.hasNext())
        {
            String s=scflight_delete.next();
            int flag=0;
            switch (s)
            {
                case "quit":
                    flag=1;
                    break;
                default:
                {
                    try {
                        String  SQL_flight_delete="DELETE FROM 航班 WHERE flight_id='"+s+"'";
                        sql_exe_manipulate(SQL_flight_delete);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

            }
            if (flag==1) {
                System.out.println("删除航班信息完毕！");
                break;
            }
        }
    }
    //新增旅客
    public static void traveller_insert() {
        System.out.println("请输入旅客信息（18位身份证号），例：“62089819940312344X”");
        Scanner sctraveller_insert=new Scanner(System.in);
        sctraveller_insert.useDelimiter("\n");

        while(sctraveller_insert.hasNext())
        {
            String insert_s = sctraveller_insert.next();
            String[] arr = insert_s.split(" ");
            int flag=0;
            switch (arr[0])
            {
                case "quit":
                    flag=1;
                    break;
                default:
                {
                    try{
                        String SQL_traveller_insert="INSERT INTO 旅客(traveller_id)" +
                                "VALUES('"+arr[0]+"')";
                        sql_exe_manipulate(SQL_traveller_insert);

                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }


                }

            }
            if (flag==1)
            {
                System.out.println("新增旅客信息完毕.");
                break;
            }
        }



    }
    //删除旅客
    public static void traveller_delete() {
        System.out.println("请输入要删除的旅客身份证号，例:'620826199006211535'");
        Scanner sctraveller_delete=new Scanner(System.in);
        sctraveller_delete.useDelimiter("\n");

        while (sctraveller_delete.hasNext())
        {
            String s=sctraveller_delete.next();
            int flag=0;
            switch (s)
            {
                case "quit":
                    flag=1;
                    break;
                default:
                {
                    try {
                        String  SQL_flight_delete="DELETE FROM 旅客 WHERE traveller_id='"+s+"'";
                       sql_exe_manipulate(SQL_flight_delete);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }

                }

            }

            if (flag==1) {
                System.out.println("删除旅客信息完毕！");
                break;
            }
        }
    }
    //航班信息查询
    public static void iflight() {
        try
        {
            String  SQL="SELECT flight_id AS 航班编号,flight_src AS 起飞城市,flight_dst AS 目标城市 FROM 航班";
            ResultSet resultSet=sql_exe_query(SQL);
            System.out.println("  航班编号   起飞城市   目的城市");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "    " + resultSet.getString(2)+"     "
                        + resultSet.getString(3));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


    }
    //机票信息查询
    public static void iticket() {
        try
        {
            String  SQL="SELECT ticket_flight AS 航班机票,ticket_time AS 起飞时间,ticket_sum AS 剩余机票 FROM 机票";
            ResultSet resultSet=sql_exe_query(SQL);
            System.out.println("  航班机票       起飞时间    剩余机票   ");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + "    " + resultSet.getString(2)+"     "
                        + resultSet.getString(3));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //旅客信息查询
    public static void itraveller() {
        try
        {
            String  SQL="SELECT traveller_id AS 旅客ID FROM 旅客";
            ResultSet resultSet=sql_exe_query(SQL);
            System.out.println("   旅 客 ID 号");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //订单信息查询
    public static void iorder() {
        try
        {
            String  SQL="SELECT order_id AS 订单编号,order_traveller AS 订单旅客,order_flight AS 订单航班 ,order_takeofftime " +
                    "AS 起飞时间,order_time AS 订票时间 FROM 订单";
            ResultSet resultSet=sql_exe_query(SQL);
            System.out.println(" 订单编号          订单旅客      订单航班    起飞时间    订票时间   ");
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1) + " " + resultSet.getString(2)+" "
                        + resultSet.getString(3)+" "+resultSet.getString(4)+" "+resultSet.getString(5));
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //执行SQL查询语句，返回代表查询结果的ResultSet对象
    public static ResultSet sql_exe_query(String SQL) {
        ResultSet resultSet=null;
        try {
            Statement statement = con.createStatement();
            resultSet=statement.executeQuery(SQL);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }
            return resultSet;


    }
    //执行SQL数据操作
    public static void sql_exe_manipulate(String SQL) {
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate(SQL);
        }

        catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    //生成指定长度的随机数
    public static String getRandomString(int length) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();//构造函数
        StringBuffer sb = new StringBuffer();//StringBuffer对象改变的是自身，在进行字符串处理时不生成新的对象，所以在内存使用上要优先String
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());//生成[0,base.length)区间的整数
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    //确定不同月份的天数
    public static String month_bound(String[] strings) {
        String time_stop=" ";
        if(strings[1]=="01"||strings[1]=="03"||strings[1]=="05"||strings[1]=="07"||strings[1]=="08"||strings[1]=="10"||strings[1]=="12")
            time_stop = strings[0] + "-" + strings[1] + "-31";
        else if (strings[1]=="04"||strings[1]=="06"||strings[1]=="09"||strings[1]=="11")
            time_stop = strings[0] + "-" + strings[1] + "-30";
        else
        {
            try{
                int year=Integer.parseInt(strings[0]);
                if (((year%4==0)&&(year%100!=0))||(year%400==0))
                    time_stop = strings[0] + "-" + strings[1] + "-29";
                else
                    time_stop = strings[0] + "-" + strings[1] + "-28";
            }catch(NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
        return time_stop;
    }


}
