package cn.fulong.tra.action.websocket;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;

import cn.fulong.common.web.action.BaseAction;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.service.MainService;
import cn.fulong.tra.util.GetUUID32;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**头部各个字段方法计算
 * @author Z
 *
 */
/**
 * @author 42084
 *
 */
/**
 * @author 42084
 *
 */
@Controller
@RequestMapping("/count")
public class CountAction extends BaseAction{
	@Autowired
	private MainService mainService;
	@Autowired
	private Setdata sd;
	@Autowired
	private JedisPool jedisPool;
	
	
	
	
	public String getdtdName(String type){
		String dtdName=null;
		if("1".equals(type)){
			dtdName="VISU_DI_CONTROL_STOP_SIGNAL";
		}else if("2".equals(type)){
			dtdName="INIT_MAIN_LOOP_BLOCK_STARTING_MODE";
		}else if("3".equals(type)){
			dtdName="B_ManualBatteryTest";
		}else if("4".equals(type)){
			dtdName="INIT_MAIN_LOOP_BLOCK_STARTING_MODE";
		}else if("5".equals(type)){
			dtdName="VISU_DI_CONTROL_START_SIGNAL";
		}
		return dtdName;
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/cleanstatus")
	public String cleanstatus()  {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.del("teststatus");
			jedis.del("teststatus2");
			
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
			
		}
		return null;
	}
	@ResponseBody
	@RequestMapping(value = "/changestatus2")
	public String changestatus2()  {
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set("teststatus2", "0");
			
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
			
		}
		return null;
	}
	/**
	 * 测试界面控制按钮
	 * @param name
	 * @param type
	 * @return
	 * @throws InterruptedException
	 */
	@ResponseBody
	@RequestMapping(value = "/checkpw")
	public String checkpw(HttpServletRequest request,String name,String type,String status) throws InterruptedException {
		ScadaNode sn=mainService.getsnbyname(name);
		HttpSession session = request.getSession();
		String dtdName=getdtdName(type);
		//如果信号为2 为禁止启动   
			String xx=(String) session.getAttribute("teststatus");
//					System.out.println("接收信号前session"+xx);
			if(("1").equals(type)||("5").equals(type)){
				ScadaDtd sdtd=mainService.getdtdbyname(dtdName).get(0);
				String a=mainService.write(sdtd,sn,type,true);
			}else if(("3").equals(type)){
				Jedis jedis = jedisPool.getResource();
				try {
					jedis.set("teststatus2", "1");
					
				} finally {
					if (jedis != null) {
						jedisPool.returnResource(jedis);
					}
					
				}
				ScadaDtd sdtd=mainService.getdtdbyname(dtdName).get(0);
				String a=mainService.write(sdtd,sn,type,true);
			}
			else if(("2").equals(type)){
				Jedis jedis = jedisPool.getResource();
				try {
					jedis.set("teststatus", "1");
				} finally {
					if (jedis != null) {
						jedisPool.returnResource(jedis);
					}
					
				}
				ScadaDtd sdtd=mainService.getdtdbyname(dtdName).get(0);
				String a=mainService.write(sdtd,sn,type,true);
				newtre(request,name,sn);
			}else if(("4").equals(type)){
				
				Jedis jedis = jedisPool.getResource();
				try {
					jedis.set("teststatus", "0");
				} finally {
					if (jedis != null) {
						jedisPool.returnResource(jedis);
					}
					
				}
				mainService.savetestresult();
				ScadaDtd sdtd=mainService.getdtdbyname(dtdName).get(0);
				String a=mainService.write(sdtd,sn,type,false);
			}
			
			
//			//如果信号为2 为禁止启动   获取session里面的teststatus  默认应为空  如果为空或者为0时时正常工作   接到信号置为 1 发送信号true给这个信号点 
//			if("0".equals(xx)||"".equals(xx)||StringUtils.isBlank(xx) || StringUtils.isBlank(xx)){
//				
//			}
//			else if("1".equals(xx)){
//				
//			}
//					System.out.println("接收信号后session"+(String) session.getAttribute("teststatus")+"####"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));

		return null;
	}
	
	public void newtre(HttpServletRequest request,String name, ScadaNode sn) throws InterruptedException{
		Jedis jedis2 = jedisPool.getResource();
		HttpSession session = request.getSession();
		Map<Object, Object> fanmap=new  HashMap<>();
		try {
			jedis2.set("test_no", GetUUID32.getUUID32());
			jedis2.set("port", sn.getPort()+"");
			jedis2.set("test_fan", name);
			jedis2.set("ip", sn.getIp()+"");
			jedis2.set("slaveId", sn.getSlaveId()+"");
			
		} finally {
			if (jedis2 != null) {
				jedisPool.returnResource(jedis2);
			}
			
		}
		long stopTime = System.currentTimeMillis() +  1000 * 1000;
		while((System.currentTimeMillis()<stopTime)) {
				Jedis jedis = jedisPool.getResource();
			
				String xxx=null;
				try{
					xxx=jedis.get("teststatus");
				} finally {
					
					if (jedis != null) {
						jedisPool.returnResource(jedis);
					}
				}
//				System.out.println("循环中session值"+xxx+"####"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));
				if(("1").equals(xxx)){
//					System.out.println("zzzz执行中"+"####"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()));            //1 是禁止启动  0是取消禁止启动
					try {
						Thread.sleep(100); //设置暂停的时间 5 秒
						Thread thread = new Thread(sd);
				        thread.start();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}  
				}else{
					
					break;
					
					
				}
				
		}while((System.currentTimeMillis()==stopTime)) {
			Jedis jedis = jedisPool.getResource();
			try {
				jedis.set("teststatus", "0");
			} finally {
				if (jedis != null) {
					jedisPool.returnResource(jedis);
				}
				
			}
			mainService.savetestresult();
			break;
		}
	}
	
}
