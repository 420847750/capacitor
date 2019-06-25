package cn.fulong.tra.action.websocket;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.locator.BaseLocator;

import cn.fulong.tra.entity.ScadaDtd;
import cn.fulong.tra.entity.ScadaNode;

/**
 * 
 *
 * 根据不同的类型获取风机所需要采集的modbus结点
 *
 */
public class GetSum implements Callable {
	private List<ScadaDtd> vdlist;
	private ScadaNode sn;
	public GetSum(List<ScadaDtd> vdlist,ScadaNode sn) {
		this.vdlist = vdlist;
		this.sn = sn;
	}
	public Map<Object, Object>  call()  {
		Map<Object, Object> map=new  HashMap<>();
		String modtype = "1";
		BatchRead<Integer> batchRead = new BatchRead<Integer>();
		for (int a =0; a < vdlist.size(); a++) {
			if("1".equals(vdlist.get(a).getFileNo())){
				batchRead.addLocator(a, BaseLocator.inputStatus(sn.getSlaveId(), vdlist.get(a).getBusAddr()-1));
			}
			else if("2".equals(vdlist.get(a).getFileNo())){
				batchRead.addLocator(a, BaseLocator.inputStatus(sn.getSlaveId(), vdlist.get(a).getBusAddr()-1));
			}
			else if("4".equals(vdlist.get(a).getFileNo())){
					 batchRead.addLocator(a, BaseLocator.inputRegister(sn.getSlaveId(), vdlist.get(a).getBusAddr()-1,DataType.FOUR_BYTE_FLOAT_SWAPPED));
						 
			}
			map.put(vdlist.get(a).getName(),a);
			
		}
		ModbusMaster master=null;
		master =Commonutil.getMaster(sn.getIp(),sn.getPort(),"000");
		batchRead.setContiguousRequests(false);
		BatchResults<Integer> batchResults=null;
		try {
			batchResults = master.send(batchRead);
		} catch (ModbusTransportException e) {
			modtype="0";
		} catch (ErrorResponseException e) {
			System.out.println(e);
		}

		Map<Object, Object> remap=new  HashMap<>();
		remap.put("deviceName",sn.getName());
		if("1".equals(modtype)){    //判断该风机是否有通讯
			for (int a = 0; a <  map.size(); a++) {
				String eng = vdlist.get(a).getEnglishName();
				String englishName = eng.substring(eng.lastIndexOf(".")+1);
				
				
				
				remap.put(englishName,String.valueOf(batchResults.getValue(a)));
				remap.put("status",true);
			}
		}
		else{
			remap.put("deviceName",sn.getName());
			for (int a = 0; a <  map.size(); a++) {
				String eng = vdlist.get(a).getEnglishName();

				String englishName = eng.substring(eng.lastIndexOf(".")+1);

				remap.put(englishName,"--");
			}
			remap.put("status",false);
			}
		
			
		return remap;
		
	}

	
}