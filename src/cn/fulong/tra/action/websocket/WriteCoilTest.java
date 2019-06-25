package cn.fulong.tra.action.websocket;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;


import org.springframework.beans.factory.annotation.Autowired;

import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;

import cn.fulong.tra.entity.SysUser;
import cn.fulong.tra.entity.ViewDtd;

/**
 * 
 *
 * 根据不同的类型获取风机所需要采集的modbus结点
 *
 */
public class WriteCoilTest implements Callable {
	

	
	private List<ViewDtd> vdlist;
	private String fanname;
	private String type;
	public WriteCoilTest(List<ViewDtd> vdlist,String fanname,String type) {
		this.vdlist = vdlist;
		this.fanname = fanname;
		this.type = type;
	}
	public Map<Object, Object>  call()  {
		Boolean a=false;
		Map<Object, Object> map=new  HashMap<>();
		ModbusMaster master=null;
		master =Commonutil.getMaster(vdlist.get(0).getIp(),vdlist.get(0).getPort(),"write");
		if("5".equals(type)){
			a=writeCoilTest(master, vdlist.get(0).getSlaveId(), (vdlist.get(0).getBusAddr()-1), false);
			
		}else{
			a=writeCoilTest(master, vdlist.get(0).getSlaveId(), (vdlist.get(0).getBusAddr()-1), true);
			
		}
		 Map<Object, Object> remap=new  HashMap<>();
		 remap.put(vdlist.get(0).getDeviceName(), a);
		return remap;
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
	
}