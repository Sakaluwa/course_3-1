import java.util.Random;
import java.util.Scanner;

/**
 * Created by 94839 on 2016/11/7.
 */
public class test {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        scanner.useDelimiter("\n");
        while(scanner.hasNext())
        {
            String string=scanner.next();
            String[] arr=string.split(" ");
            String string1=arr[0]+"-02-12";
            System.out.println(string1);
        }





    }

    public static void  scan_split()
    {
        Scanner scanner=new Scanner(System.in);
        scanner.useDelimiter("\n");
        while(scanner.hasNext())
        {
            String string=scanner.next();
            String[] arr=string.split(" ");
            for (int i=0;i<arr.length;i++)
            {
                System.out.println(arr[i]);
            }

        }


    }


    public static String getRandomString(int length)
    {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();//构造函数
        StringBuffer sb = new StringBuffer();//StringBuffer对象改变的是自身，在进行字符串处理时不生成新的对象，所以在内存使用上要优先String
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());//生成[0,base.length)区间的整数
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
}
