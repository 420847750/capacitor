package cn.fulong.tra.action.websocket;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.validation.constraints.Null;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSONArray;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;

import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.ViewDtd;
import cn.fulong.tra.util.GetUUID32;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
@Repository
class Setdata implements Runnable {
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	@Autowired
	private JedisPool jedisPool;
	
	@Override
    public void run() {
		String threadTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
//		 System.out.println("当前线程：" + Thread.currentThread().getName() + "---"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date()) );
		 Jedis jedis = jedisPool.getResource();
		 List<ScadaDtd> sdlist = new ArrayList<>();
		 int port =0;
		 int slaveId =0;
		 String testNo=null;
		 String testfan=null;
		 String ip=null;
		try{
			testNo=jedis.get("test_no");
			testfan=jedis.get("test_fan");
			port=Integer.parseInt(jedis.get("port"));;
			slaveId=Integer.parseInt(jedis.get("slaveId"));;
			 ip=jedis.get("ip");
		 sdlist = jsonToList(jedis.get("caplist"), ScadaDtd.class);
		} finally {
		
						if (jedis != null) {
							jedisPool.returnResource(jedis);
						}
					}
		 BatchRead<Integer> batchRead = new BatchRead<Integer>();
			ModbusMaster master=null;
			
			master =Commonutil.getMaster(ip,port,"000");
			batchRead.setContiguousRequests(false);
			BatchResults<Integer> batchResults=null;
			for (int a =0; a < sdlist.size(); a++) {
				if("1".equals(sdlist.get(a).getFileNo())){
					batchRead.addLocator(a, BaseLocator.inputStatus(slaveId, sdlist.get(a).getBusAddr()-1));
				}
				else if("2".equals(sdlist.get(a).getFileNo())){
					batchRead.addLocator(a, BaseLocator.inputStatus(slaveId, sdlist.get(a).getBusAddr()-1));
				}
				else if("4".equals(sdlist.get(a).getFileNo())){
					batchRead.addLocator(a, BaseLocator.inputRegister(slaveId, sdlist.get(a).getBusAddr()-1,DataType.FOUR_BYTE_FLOAT_SWAPPED));
				}
			}
			try {
				batchResults = master.send(batchRead);
			} catch (ModbusTransportException e) {
//				modtype="0";
			} catch (ErrorResponseException e) {
				System.out.println(e);
			}
			
//			System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+flotodou( batchResults.getValue(7)));
			
    	String sss= GetUUID32.getUUID32();
		  String sql = "insert into test_data(id,test_no,fan_name,test_time,jiangjujiao1,jiangjujiao2,jiangjujiao3,sudu1,sudu2,sudu3,"
		  		+ "dianya1,dianya2,dianya3,wendu1,wendu2,wendu3) "+
		  	"values(?,?,?,str_to_date('"+threadTime+"','%Y-%m-%d %H:%i:%s:%f'),?,?,?,?,?,?,?,?,?,?,?,?)";
			Object args[] = {sss,testNo,testfan,flotodou( batchResults.getValue(0)),
					flotodou( batchResults.getValue(1)),
					flotodou( batchResults.getValue(2)),
					flotodou( batchResults.getValue(3)),
					flotodou( batchResults.getValue(4)),
					flotodou( batchResults.getValue(5)),
					flotodou(batchResults.getValue(6)),
					flotodou( batchResults.getValue(7)),
					flotodou( batchResults.getValue(8)),
					flotodou( batchResults.getValue(9)),
					flotodou( batchResults.getValue(10)),
					flotodou( batchResults.getValue(11))};
			int temp = jdbcTemplate.update(sql, args);

              }
	
	public double flotodou(Object object){
		BigDecimal b = new BigDecimal(String.valueOf(object));  
		double x = b.doubleValue();  
		return x;
	}
	public ScadaNode jsonToclass(String jsonString, Class<ScadaNode> clazz) {
		 ScadaNode sn =  (ScadaNode ) JSONArray.parseArray(jsonString, clazz);
		return sn;
	}
	public List<ScadaDtd> jsonToList(String jsonString, Class<ScadaDtd> clazz) {
		List<ScadaDtd> ts = (List<ScadaDtd>) JSONArray.parseArray(jsonString, clazz);
		return ts;
	}
}