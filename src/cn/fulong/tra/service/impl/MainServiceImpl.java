package cn.fulong.tra.service.impl;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;

import cn.fulong.common.service.impl.BaseServiceImpl;
import cn.fulong.tra.action.websocket.Commonutil;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.TestResult;
import cn.fulong.tra.entity.ViewDtd;
import cn.fulong.tra.entity.ViewFan;
import cn.fulong.tra.entity.fanDate;
import cn.fulong.tra.service.MainService;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class MainServiceImpl extends BaseServiceImpl implements MainService{
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	@Autowired
	private JedisPool jedisPool;

	@Override
	public List<TestResult> getresult() {
		
		List<TestResult> resultlist = baseTransaction
				.find("from TestResult ");
		return resultlist;
	}
	@Override
	public List<TestResult> getresultby(String start, String end, String deviceName) {
		
		List<TestResult> resultlist = baseTransaction
				.find("from TestResult where fanName='" + deviceName + "' "
						+ "and testTime  between '" + start + "' and '" + end + "' ");
		return resultlist;
	}
	@Override
	public List<ScadaDtd> getdtdbyname(String dtdName) {
			List<ScadaDtd> dtdlist = baseTransaction
					.find("from ScadaDtd where  englishName='" + dtdName + "' ");
			return dtdlist;
	}
	@Override
	public List<ScadaDtd> getdtd() {
			List<ScadaDtd> dtdlist = baseTransaction
					.find("from ScadaDtd  ");
			return dtdlist;
	}
	@Override
	public ScadaNode getsnbyname(String name) {
		List<ScadaNode> snList = 
				baseTransaction.find("from ScadaNode where name='"+name+"'");
		return snList.get(0);
	}
	@Override
	public List<ViewFan> getdata() {
		List<ViewFan> dataList = 
				baseTransaction.find("from ViewFan ");
		return dataList;
	}

	@Override
	public void saveDate(String fanName, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//yyyy-mm-dd, 会出现时间不对, 因为小写的mm是代表: 秒
	    Date utilDate=null;
		try {
			utilDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();  
        c.setTime(utilDate);  
        c.add(Calendar.MONTH, 6);   
		Date nextdate=c.getTime();
		List<fanDate> dataList = 
				baseTransaction.find("from fanDate where fanName='"+fanName+"'");
		if(dataList.size()>0){
			dataList.get(0).setCreateDate(utilDate);
			dataList.get(0).setTestPeriod(6);
			dataList.get(0).setNextDate(nextdate);	
			baseTransaction.update(dataList.get(0));
		}else{
			fanDate fd=new fanDate();
			fd.setFanName(fanName);
			fd.setCreateDate(utilDate);
			fd.setTestPeriod(6);
			fd.setNextDate(nextdate);
			baseTransaction.save(fd);
		}
		
	}

	@Override
	public List<ScadaNode> getnode() {
		List<ScadaNode> dataList = 
				baseTransaction.find("from ScadaNode  order by orderNo");
		List<ScadaDtd> sdList = 
				baseTransaction.find("from ScadaDtd where type  ='0'  order  by busPoints");
		
		Jedis jedis = jedisPool.getResource();
		try {
			jedis.set("caplist", JSON.toJSONString(sdList));
		} finally {
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
			
		}
		return dataList;
	}
	@Override
	public String write(ScadaDtd sdtd,ScadaNode sn ,String type,boolean boo){
	Boolean a=false;
	Map<Object, Object> map=new  HashMap<>();
	ModbusMaster master=null;
	master =Commonutil.getMaster(sn.getIp(),sn.getPort(),"write");
		a=writeCoilTest(master, sn.getSlaveId(), (sdtd.getBusAddr()-1), boo);
	 Map<Object, Object> remap=new  HashMap<>();
	 remap.put(sn.getName(), a);
	return null;
}
/**
 * @Description: 写开关量数据
 * @param master
 * @param slaveId 从站ID
 * @param value 写入的值
 */
public  Boolean writeCoilTest(ModbusMaster master, int slaveId, int offset, boolean value) {
    Boolean result=false;
	try {
		 System.out.println("zzzzzzzzzzzzzzzzz"+value+"zzzzzzzzzzzzzzzzz"+slaveId+"aa"+offset);
        WriteCoilRequest request = new WriteCoilRequest(slaveId, offset, value);
        WriteCoilResponse response = (WriteCoilResponse) master.send(request);
        Timestamp time = new Timestamp(System.currentTimeMillis()); 
        if (response.isException()){   
        	System.out.println("Exception response: message=" + response.getExceptionMessage());
        }	
        else{
            System.out.println("功能码:1,写入单个数据成功!");
            result=true;
        }
    }
    catch (ModbusTransportException e) {
        e.printStackTrace();
    }
	return result;
}
@Override
public void savetestresult() {
	String testNo=null;
	String testfan =null;
	Jedis jedis = jedisPool.getResource();
	try{
		testNo=jedis.get("test_no");
		testfan=jedis.get("test_fan");
	} finally {
		
		if (jedis != null) {
			jedisPool.returnResource(jedis);
		}
	}
//	System.out.println("test_no---------"+testNo);
	String sql="select max(dianya1) as dianya1max,max(dianya2) as dianya2max,max(dianya3) as dianya3max,"
			+ "min(dianya1) as dianya1min,min(dianya2) as dianya2min,min(dianya3) as dianya3min  from test_data where test_no='"+testNo+"'";
	
	List rows = jdbcTemplate.queryForList(sql);
	double dianya1max = 0;
	double dianya2max = 0;
	double dianya3max = 0;
	double dianya1min = 0;
	double dianya2min = 0;
	double dianya3min = 0;
	for(int a=0;a<rows.size();a++){
		Map userMap=(Map) rows.get(a);
		 dianya1max = Double.parseDouble( userMap.get("dianya1max").toString());
		 dianya2max = Double.parseDouble( userMap.get("dianya2max").toString());
		 dianya3max = Double.parseDouble( userMap.get("dianya3max").toString());
		 dianya1min = Double.parseDouble( userMap.get("dianya1min").toString());
		 dianya2min = Double.parseDouble( userMap.get("dianya2min").toString());
		 dianya3min = Double.parseDouble( userMap.get("dianya3min").toString());
	}
	
	TestResult tr=new TestResult();
	tr.setFanName(testfan);
	tr.setTestTime(new Timestamp(System.currentTimeMillis()));
	tr.setYajiang1(dianya1max-dianya1min);
	tr.setYajiang2(dianya2max-dianya2min);
	tr.setYajiang3(dianya3max-dianya3min);
	tr.setTestNo(testNo);
    if((dianya1max-dianya1min)>15){
    	tr.setStatus1("1");
	}else{
		tr.setStatus1("0");
	}
	if((dianya2max-dianya2min)>15){
		tr.setStatus2("1");
	}else{
		tr.setStatus2("0");
	}
	if((dianya3max-dianya3min)>15){
		tr.setStatus3("1");
	}else{
		tr.setStatus3("0");
	}
	baseTransaction.save(tr);
}
@Override
public List<ScadaNode> getnodelist() {
	List<ScadaNode> dataList = 
			baseTransaction.find("from ScadaNode  order by orderNo");
	return dataList;
}

	
}
