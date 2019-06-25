package cn.fulong.tra.service.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.alibaba.fastjson.JSON;

import cn.fulong.common.service.impl.BaseServiceImpl;
import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;
import cn.fulong.tra.entity.TestResult;
import cn.fulong.tra.entity.fanDate;
import cn.fulong.tra.service.ExportService;
import cn.fulong.tra.util.GetUUID32;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Repository("exportServiceImpl")
public class ExportServiceImpl extends BaseServiceImpl implements ExportService{
	@Autowired
	private JedisPool jedisPool;
	@Resource(name = "jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public String getteststatus() {
		String teststatus =null;
		Jedis jedis = jedisPool.getResource();
		try{
			teststatus=jedis.get("teststatus");
		} finally {
			
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return teststatus;
	}
	@Override
	public String getteststatus2() {
		String teststatus2 =null;
		Jedis jedis = jedisPool.getResource();
		try{
			teststatus2=jedis.get("teststatus2");
		} finally {
			
			if (jedis != null) {
				jedisPool.returnResource(jedis);
			}
		}
		return teststatus2;
	}

	@Override
	public ScadaNode getfan(String message) {
		String sendMessage = message.split("[|]")[0];
		List<ScadaNode> nodelist = baseTransaction
				.find("from ScadaNode where name='" + sendMessage+"'" );
//		System.out.println("zzzzzzzzzz"+message);
		ScadaNode sd=nodelist.get(0);
		return sd;
		
	}


	@Override
	public String saveexport(final List<ScadaNode> fanlist,final List<ScadaDtd> dtdlist) {
//		 Calendar now = Calendar.getInstance();  
//         String time=now.get(Calendar.YEAR)+"_"+(now.get(Calendar.MONTH) + 1) +"_"+now.get(Calendar.DAY_OF_MONTH)+"_"
//          +now.get(Calendar.HOUR_OF_DAY)+"_"+now.get(Calendar.MINUTE)+"_"+now.get(Calendar.SECOND);
//		  String sqlnodebak="create table scada_node_"+time+" select * from scada_node";
//		  String sqldtdbak="create table scada_dtd_"+time+" select * from scada_node";
//		  jdbcTemplate.execute(sqlnodebak);
//		  jdbcTemplate.execute(sqldtdbak);
		  String sql1 = "delete from  scada_node";
		  jdbcTemplate.execute(sql1);
		  String sql2 = "delete from  scada_dtd";
		  jdbcTemplate.execute(sql2);

		  final String sqlnode = "insert into scada_node(id,name,ip,port,slave_id,"
		  		+ "group_type,line_type,pitch,gearbox,generator,converter,turbine_type,order_no,fan_ip"
		  		+ ",point01File,point02File,point03File,point04File,grid_power) "
		  		+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		  
		  
		        jdbcTemplate.batchUpdate(sqlnode, new BatchPreparedStatementSetter() {
					@Override
					public int getBatchSize() {
						 return fanlist.size();
					}
					@Override
					public void setValues(PreparedStatement arg0, int index) throws SQLException {
						ScadaNode fan = fanlist.get(index);
						 
						 arg0.setString(1,GetUUID32.getUUID32());
						 arg0.setString(2, fan.getName());
						 arg0.setString(3, fan.getIp());
						 arg0.setInt(4, fan.getPort());
						 arg0.setInt(5, fan.getSlaveId());
						 arg0.setString(6, fan.getGroupType());
						 arg0.setString(7, fan.getLineType());
						 arg0.setString(8, fan.getPitch());
						 arg0.setString(9, fan.getGearbox());
						 arg0.setString(10, fan.getGenerator());
						 arg0.setString(11, fan.getConverter());
						 arg0.setString(12, fan.getTurbineType());
						 arg0.setInt(13, fan.getOrderNo());
						 arg0.setString(14, fan.getFanIp());	
						 arg0.setString(15, fan.getPoint01File());
						 arg0.setString(16, fan.getPoint02File());
						 arg0.setString(17, fan.getPoint03File());
						 arg0.setString(18, fan.getPoint04File());	
						 arg0.setDouble(19, fan.getGridPower());
					};
		        	
		        });
		        
		        
		        
		        final String sqldtd = "insert into scada_dtd(id,name,type,"
				  		+ "file_no,bus_addr,bus_points,english_name) "
				  		+ "values(?,?,?,?,?,?,?)";
				  
				  
				        jdbcTemplate.batchUpdate(sqldtd, new BatchPreparedStatementSetter() {
							@Override
							public int getBatchSize() {
								 return dtdlist.size();
							}
							@Override
							public void setValues(PreparedStatement arg0, int index) throws SQLException {
								ScadaDtd dtd = dtdlist.get(index);
								 
								 arg0.setString(1,GetUUID32.getUUID32());
								 arg0.setString(2, dtd.getName());
								 arg0.setString(3, dtd.getType());
								 arg0.setString(4, dtd.getFileNo());
								 arg0.setInt(5, dtd.getBusAddr());
								 arg0.setInt(6, dtd.getBusPoints());
								 arg0.setString(7, dtd.getEnglishName());
												
							};
				        	
				        });  
		return "";
	}

	@Override
	public List<ScadaDtd> getsdlist() {
		List<ScadaDtd> dtdList =  baseTransaction.find("from ScadaDtd");
		return dtdList;
	}

	@Override
	public TestResult gettr(String message) {
		TestResult tr=null;
		String sendMessage = message.split("[|]")[0];
		List<TestResult> trlist = baseTransaction
				.find("from TestResult where fanName='" + sendMessage+"'  order by testTime desc" );
		if(trlist.size()>0){
			tr=trlist.get(0);
		}
		return tr;
	}

	
}
