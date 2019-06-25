package cn.fulong.tra.util;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @desc 第三种开启线程的方式
 * @author xiaostudy
 *
 */
public class Demo3_Thread  {

    public static void main(String[] args) throws InterruptedException {
    	long stopTime = System.currentTimeMillis() +  1000 * 1000;
    	
    	String aaa="0";
    	for (int a=0;a<100;a++){
    		Thread.sleep(100);
    		a=a+1;
    		if(a==10){
    			 aaa="1";
    		}
    	}
    	
		while((System.currentTimeMillis()<stopTime)) {
			
				System.out.println("循环中session值"+aaa+"####"+System.currentTimeMillis());
				if(("1").equals(aaa)){
					System.out.println("zzzz执行中"+"####"+System.currentTimeMillis());            //1 是禁止启动  0是取消禁止启动
					Thread.sleep(100);
//					try {
//						Thread.sleep(100); //设置暂停的时间 5 秒
//						Thread thread = new Thread(sd);
//				        thread.start();
//					} catch (InterruptedException e) {
//						e.printStackTrace();
//					}  
				}else{
					
					System.out.println("xxxx====0了！！！！！！！！！！！"+System.currentTimeMillis());
					System.out.println("break了！！！！！！！！！！！"+System.currentTimeMillis());
					break;
					
					
				}
    }

    
    }}
