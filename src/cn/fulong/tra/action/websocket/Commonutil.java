package cn.fulong.tra.action.websocket;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.ip.IpParameters;

/**一些公用方法包括根据获取的各个点状态判断风机状态，创建master
 * @author Administrator
 *
 */
public class Commonutil {
	public static ModbusMaster getMaster(String ip ,int port,String str)  {
		IpParameters params = new IpParameters();
		params.setHost(ip);
		params.setPort(port);
		ModbusMaster modbusMaster = modbusFactory.createTcpMaster(params, false);
		try {
			modbusMaster.init();
		} catch (ModbusInitException e) {
			e.printStackTrace();
		}
		return modbusMaster;
	}

	static ModbusFactory modbusFactory;
	static {
		if (modbusFactory == null) {
			modbusFactory = new ModbusFactory();
		}
	}
}
