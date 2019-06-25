package cn.fulong.tra.util;
import java.text.SimpleDateFormat;
import java.util.Date;
 
public class MainActivity {
 
	public static void main(String[] args) {
 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()) );
		Boolean result = false;
		int count = 0;
		while(!result) {
			try {
				Thread.sleep(1000); //设置暂停的时间 5 秒
				count ++ ;
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()) + "--循环执行第" + count + "次");
//				if (count == 3) {
//					result = true;
//					break ;
//				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
		}
	}
 
}
