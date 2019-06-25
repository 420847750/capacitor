package cn.fulong.tra.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


import com.serotonin.io.serial.SerialParameters;
import com.serotonin.modbus4j.BasicProcessImage;
import com.serotonin.modbus4j.BatchRead;
import com.serotonin.modbus4j.BatchResults;
import com.serotonin.modbus4j.ModbusFactory;
import com.serotonin.modbus4j.ModbusMaster;
import com.serotonin.modbus4j.ModbusSlaveSet;
import com.serotonin.modbus4j.ProcessImage;
import com.serotonin.modbus4j.ProcessImageListener;
import com.serotonin.modbus4j.base.ReadFunctionGroup;
import com.serotonin.modbus4j.code.DataType;
import com.serotonin.modbus4j.code.RegisterRange;
import com.serotonin.modbus4j.exception.ErrorResponseException;
import com.serotonin.modbus4j.exception.IllegalDataAddressException;
import com.serotonin.modbus4j.exception.ModbusInitException;
import com.serotonin.modbus4j.exception.ModbusTransportException;
import com.serotonin.modbus4j.ip.IpParameters;
import com.serotonin.modbus4j.locator.BaseLocator;
import com.serotonin.modbus4j.locator.StringLocator;
import com.serotonin.modbus4j.msg.ModbusResponse;
import com.serotonin.modbus4j.msg.ReadCoilsRequest;
import com.serotonin.modbus4j.msg.ReadCoilsResponse;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsRequest;
import com.serotonin.modbus4j.msg.ReadDiscreteInputsResponse;
import com.serotonin.modbus4j.msg.ReadExceptionStatusRequest;
import com.serotonin.modbus4j.msg.ReadExceptionStatusResponse;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersRequest;
import com.serotonin.modbus4j.msg.ReadHoldingRegistersResponse;
import com.serotonin.modbus4j.msg.ReadInputRegistersRequest;
import com.serotonin.modbus4j.msg.ReadInputRegistersResponse;
import com.serotonin.modbus4j.msg.ReportSlaveIdRequest;
import com.serotonin.modbus4j.msg.ReportSlaveIdResponse;
import com.serotonin.modbus4j.msg.WriteCoilRequest;
import com.serotonin.modbus4j.msg.WriteCoilResponse;
import com.serotonin.modbus4j.msg.WriteCoilsRequest;
import com.serotonin.modbus4j.msg.WriteCoilsResponse;
import com.serotonin.modbus4j.msg.WriteMaskRegisterRequest;
import com.serotonin.modbus4j.msg.WriteMaskRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegisterRequest;
import com.serotonin.modbus4j.msg.WriteRegisterResponse;
import com.serotonin.modbus4j.msg.WriteRegistersRequest;
import com.serotonin.modbus4j.msg.WriteRegistersResponse;

public class MasterTest {

	/**
	 * @Description: 创建TcpMaster
	 * @return 
	 */
	public static ModbusMaster getTcpMaster() {
		IpParameters ipParameters = new IpParameters();
		//设置IP
		ipParameters.setHost("192.168.0.104");
		//设置端口 默认为502
		ipParameters.setPort(503);
		// 创建ModbusMaster工厂实例   
		return new ModbusFactory().createTcpMaster(ipParameters, true);
	}
	
    public static void main(String[] args) throws Exception {
		Boolean result = false;
		int count = 0;
//				Thread.sleep(100); //设置暂停的时间 5 秒
				count ++ ;
				 long start = System.currentTimeMillis();
//				  String sop=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
//				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())+"message--------->"+sop+"开始"+ "--循环执行第" + count + "次");
//				System.out.println(sdf.format(new Date()) + "--循环执行第" + count + "次");
//				if (count == 3) {
//					result = true;
//					break ;
//				}
				//TCP传输方式
	    		ModbusMaster master = getTcpMaster();
	    		//RTU传输方式
//	        	ModbusMaster master = getRtuMaster();
	        	//ASCII传输模式
//	        	ModbusMaster master = getAsiiMaster();
//	       	ModbusMaster master = modbusFactory.createUdpMaster(ipParameters);

	        try {
	        	//初始化
	        	//设置超时时间
	        	master.setTimeout(500);
	        	//设置重连次数
	        	master.setRetries(1);
	            master.init();
	            //设置从站ID
	            int slaveId = 1;
	            
	            //测试校验
//	        	Timer timer = new Timer();
//	    		timer.schedule(new TimerTask() {
//	    			
//	    			@Override
//	    			public void run() {
//	    				//RTU
//	    				ModbusMaster master = getRtuMaster();
//	    				//ASCII
////	    				ModbusMaster master = getAsiiMaster();
//	    				try {
//	    					master.init();
//	    					readCoilTest(master, 1, 0, 10);
//	    				} catch (ModbusInitException e) {
//	    					e.printStackTrace();
//	    				} finally {
//	    					master.destroy();
//	    				}
//	    			}
//	    		}, 100, 1000);
	            
	            //------------------读---------------
	            //读取开关量状态   01
	            //            readCoilTest(master, slaveId, 0, 5);
	            //读取外部设备输入开关量状态  02
//	            readDiscreteInputTest(master, slaveId, 0, 10);
	            //读取保持寄存器数据 03
//	            readHoldingRegistersTest(master, 1, 1, 2);
	            //读取外围设备输入的数据 04
//	            readInputRegistersTest(master, slaveId, 0, 10);
	            //------------------写---------------
	            //开关量写单个数据 05
//	            writeCoilTest(master, slaveId, 9, true);
	            //开关量写入多个数据 15
//	            writeCoilsTest(master, slaveId, 0, new boolean[] {true, true, true, true, true});
	            //写入单个模拟量数据 06
//	            writeRegisterTest(master, slaveId, 0, 100);
	            //写入多个模拟量数据 16
//		        writeRegistersTest(master, slaveId, 0, new short[] {1, 10, 100, 1000, 10000, (short) 65534});

	            //批量读取
 		     String xx=  bathRead(master, slaveId);
 		     System.out.println("zzzzzz"+xx);
	            //根据类型写
//			       writeValue(master);
			    //异常
//			       readExceptionStatusTest(master, slaveId);
//			       String sop2=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date());
//			       System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())+"message--------->"+sop2+"结束");
 		       		long end = System.currentTimeMillis();
			       System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(new Date())+"--------->用时: " + (end - start) + "ms");
//					if (xx.equals("3") ) {
//						result = true;
//						break ;
//					}
	        }
	        finally {
	            master.destroy();
	        }
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }



	/**
	 * @Description: 根据类型写数据
	 * @param master
	 * @throws ModbusTransportException
	 * @throws ErrorResponseException 
	 */
	public static void writeValue(ModbusMaster master) throws ModbusTransportException, ErrorResponseException {

//		BaseLocator<?> createLocator = StringLocator.createLocator(1, 1, DataType.TWO_BYTE_INT_UNSIGNED, 1, 2);
		
//		BaseLocator<Number> hr1 = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_UNSIGNED);
//		BaseLocator<Number> hr1 = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_UNSIGNED_SWAPPED);
//		BaseLocator<Number> hr1 = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_UNSIGNED);
//		BaseLocator<Number> hr1 = BaseLocator.holdingRegister(1, 0, DataType.FOUR_BYTE_INT_UNSIGNED);
		//测试高低位0x12345678
//		BaseLocator<Number> hr1 = BaseLocator.holdingRegister(1, 0, DataType.FOUR_BYTE_INT_UNSIGNED);
//		BaseLocator<Number> hr1 = BaseLocator.holdingRegister(1, 0, DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED);
		BaseLocator<Number> hr1 = BaseLocator.inputRegister(1, 0, DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED);
//		BaseLocator<Number> hr2 = BaseLocator.holdingRegister(1, 0, DataType.TWO_BYTE_INT_UNSIGNED);
		//测试浮点数
//		BaseLocator<Number> hr2 = BaseLocator.holdingRegister(1, 0, DataType.FOUR_BYTE_INT_UNSIGNED);
//		master.setValue(hr2, 0x3F800000);
		
		 master.setValue(hr1, 0x12345678);
		 
		
	}

       

	/**
	 * @Description: 批量读取    可以批量读取不同寄存器中数据
	 * @param master
	 * @param slaveId
	 * @throws ModbusTransportException
	 * @throws ErrorResponseException 
	 */
	public static String bathRead(ModbusMaster master, int slaveId)
			throws ModbusTransportException, ErrorResponseException {
		
		
		    BatchRead<Number> batch = new BatchRead<Number>();
		    
		    //****************批量读取不同寄存器中的单个数据********************
		    //读取线圈状态开关量
//		   	batch.addLocator(1, BaseLocator.coilStatus(slaveId, 0));
		   	//读取输入状态 开关量
//		    batch.addLocator(0, BaseLocator.readHoldingRegistersTest(slaveId, 509));
//		    batch.addLocator(0, BaseLocator.inputStatus(slaveId, 1));
//		    batch.addLocator(2, BaseLocator.inputStatus(slaveId, 168));
//		    batch.addLocator(3, BaseLocator.inputStatus(slaveId, 169));
//		    batch.addLocator(4, BaseLocator.inputStatus(slaveId, 170));
//		    batch.addLocator(5, BaseLocator.inputStatus(slaveId, 171));
//		    batch.addLocator(6, BaseLocator.inputStatus(slaveId, 172));
//		    batch.addLocator(7, BaseLocator.inputStatus(slaveId, 173));
			   
		   	//读取保持寄存器数据
		   	//根据设置的数据类型读取
//		   	batch.addLocator(3, BaseLocator.holdingRegister(slaveId, 0, DataType.TWO_BYTE_INT_UNSIGNED));
		   	//读取整形中16位中某一位的布尔值
//		   	batch.addLocator(3.1, BaseLocator.holdingRegisterBit(slaveId, 0, 0));
		   	
		   	//读取输入寄存器数据
		   	//根据设置的数据类型读取
//		   	batch.addLocator(4, BaseLocator.inputRegister(slaveId, 0, DataType.TWO_BYTE_INT_UNSIGNED));
		 	//读取整形中16位中某一位的布尔值
//		   	batch.addLocator(4.1, BaseLocator.inputRegisterBit(slaveId, 0, 0));
		   	
		   	//高低字节颠倒
//		   	batch.addLocator(1, BaseLocator.inputRegister(slaveId, 227, DataType.FOUR_BYTE_FLOAT));
		   	//高低字节不颠倒
//		   	batch.addLocator(1, BaseLocator.holdingRegister(slaveId, 0, DataType.FOUR_BYTE_INT_UNSIGNED));
		   	//读取浮点数
//		   	batch.addLocator(0, BaseLocator.holdingRegister(509, 0, DataType.FOUR_BYTE_FLOAT));
//					   	batch.addLocator(0, BaseLocator.inputRegister(0, 1,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(1, BaseLocator.inputRegister(1,267,DataType.FOUR_BYTE_INT_UNSIGNED_SWAPPED));
		    batch.addLocator(2, BaseLocator.inputRegister(1,269,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(3, BaseLocator.inputRegister(1,271,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(5, BaseLocator.inputRegister(1,90,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(6, BaseLocator.inputRegister(1,91,DataType.FOUR_BYTE_FLOAT));
		    batch.addLocator(7, BaseLocator.inputRegister(1,92,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(8, BaseLocator.inputRegister(1,133,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(9, BaseLocator.inputRegister(1,134,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(10,BaseLocator.inputRegister(1,135,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(11,BaseLocator.inputRegister(1,130,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(12,BaseLocator.inputRegister(1,131,DataType.FOUR_BYTE_FLOAT_SWAPPED));
		    batch.addLocator(13,BaseLocator.inputRegister(1,132,DataType.FOUR_BYTE_FLOAT_SWAPPED));
//		    batch.addLocator(14,BaseLocator.coilStatus(1, 1));
//		    batch.addLocator(15,BaseLocator.coilStatus(1, 2));
//		    batch.addLocator(16,BaseLocator.coilStatus(1, 3));
//		    batch.addLocator(17,BaseLocator.coilStatus(1, 4));
//		    batch.addLocator(19,BaseLocator.inputStatus(1, 1));
//		    batch.addLocator(20,BaseLocator.inputStatus(1, 2));
//		    batch.addLocator(22,BaseLocator.inputStatus(1, 3));
//		    batch.addLocator(23,BaseLocator.inputStatus(1, 4));
//		    batch.addLocator(24,BaseLocator.inputStatus(1, 5));
//		    batch.addLocator(25,BaseLocator.inputStatus(1, 6));
			 BatchResults<Number> results = master.send(batch);
			 System.out.println("批量读取1:--" + results.getValue(1));
			 System.out.println("批量读取2:--" + results.getValue(2));
			 System.out.println("批量读取3:--" + results.getValue(3));
//			 System.out.println("批量读取3.1:--" + results.getValue(3.1));
//			 System.out.println("批量读取3.2:--" + results.getValue(3.2));
//			 System.out.println("批量读取4:--" + results.getValue(4));
//			 System.out.println("批量读取4.1:--" + results.getValue(4.1));
			 //高低字节颠倒
//			 System.out.println(results.getValue(0));
//			 System.out.println(results.getValue(1));
//			 System.out.println(results.getValue(2));
//			 System.out.println(results.getValue(3));
//			 System.out.println(results.getValue(4));
			 
//			 String xx=results.getValue(0)+"";
			 
			 int a=(int)(1+Math.random()*(10-1+1));
			 String xx=a+"";
//			 System.out.println(results.getValue(1));
//			 System.out.println(results.getValue(2));
//			 System.out.println(results.getValue(3));
//			 System.out.println(results.getValue(4));
//			 System.out.println(results.getValue(5));
//			 System.out.println(results.getValue(6));
//			 System.out.println(results.getValue(7));
			 return xx;
	}


    
    /**
     * @Description: 读取开关量
     * @param master 主站实例 
     * @param slaveId 从站ID
     * @param start 起始位
     * @param len 读取的长度
     */
    public static void readCoilTest(ModbusMaster master, int slaveId, int start, int len) {
        try {
            ReadCoilsRequest request = new ReadCoilsRequest(slaveId, start, len);
            ReadCoilsResponse response = (ReadCoilsResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:1--" + Arrays.toString(response.getBooleanData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 读取外围设备输入的开关量
     * @param master 主站实例
     * @param slaveId 从站ID
     * @param start 起始位
     * @param len 长度
     */
    public static void readDiscreteInputTest(ModbusMaster master, int slaveId, int start, int len) {
        try {
            ReadDiscreteInputsRequest request = new ReadDiscreteInputsRequest(slaveId, start, len);
            ReadDiscreteInputsResponse response = (ReadDiscreteInputsResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:2--" + Arrays.toString(response.getBooleanData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 读取保持寄存器数据
     * @param master 主站实例
     * @param slaveId 从站ID
     * @param start 起始位
     * @param len 长度
     */
    public static void readHoldingRegistersTest(ModbusMaster master, int slaveId, int start, int len) {
        try {
            ReadHoldingRegistersRequest request = new ReadHoldingRegistersRequest(slaveId, start, len);
            ReadHoldingRegistersResponse response = (ReadHoldingRegistersResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:3--" + Arrays.toString(response.getShortData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 读取外围设备输入的数据
     * @param master 主站实例
     * @param slaveId 从站ID
     * @param start 起始位
     * @param len 长度
     */
    public static void readInputRegistersTest(ModbusMaster master, int slaveId, int start, int len) {
        try {
            ReadInputRegistersRequest request = new ReadInputRegistersRequest(slaveId, start, len);
            ReadInputRegistersResponse response = (ReadInputRegistersResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:4--" + Arrays.toString(response.getShortData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 写开关量数据
     * @param master 
     * @param slaveId 从站ID
     * @param offset 偏移量
     * @param value 写入的值
     */
    public static void writeCoilTest(ModbusMaster master, int slaveId, int offset, boolean value) {
        try {
            WriteCoilRequest request = new WriteCoilRequest(slaveId, offset, value);
            WriteCoilResponse response = (WriteCoilResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:1,写入单个数据成功!");
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 保持寄存器，写入单个数据
     * @param master
     * @param slaveId 从站ID
     * @param offset 偏移量
     * @param value 
     */
    public static void writeRegisterTest(ModbusMaster master, int slaveId, int offset, int value) {
        try {
            WriteRegisterRequest request = new WriteRegisterRequest(slaveId, offset, value);
            WriteRegisterResponse response = (WriteRegisterResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:3,写入单个模拟量数据成功!");
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 读取异常状态
     * @param master
     * @param slaveId 
     */
    public static void readExceptionStatusTest(ModbusMaster master, int slaveId) {
        try {
            ReadExceptionStatusRequest request = new ReadExceptionStatusRequest(slaveId);
            ReadExceptionStatusResponse response = (ReadExceptionStatusResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println(response.getExceptionStatus());
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    public static void reportSlaveIdTest(ModbusMaster master, int slaveId) {
        try {
            ReportSlaveIdRequest request = new ReportSlaveIdRequest(slaveId);
            ReportSlaveIdResponse response = (ReportSlaveIdResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println(Arrays.toString(response.getData()));
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 保持寄存器写多个数据
     * @param master
     * @param slaveId 从站ID
     * @param start 起始位置
     * @param values 数值
     */
    public static void writeCoilsTest(ModbusMaster master, int slaveId, int start, boolean[] values) {
        try {
            WriteCoilsRequest request = new WriteCoilsRequest(slaveId, start, values);
            WriteCoilsResponse response = (WriteCoilsResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:1,写入多个数据成功!");
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description: 保持寄存器写入多个模拟量数据
     * @param master
     * @param slaveId 从站ID
     * @param start modbus起始位置
     * @param values 数据
     */
    public static void writeRegistersTest(ModbusMaster master, int slaveId, int start, short[] values) {
        try {
            WriteRegistersRequest request = new WriteRegistersRequest(slaveId, start, values);
            WriteRegistersResponse response = (WriteRegistersResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("功能码:3,写入多个模拟量数据成功!");
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }

    public static void writeMaskRegisterTest(ModbusMaster master, int slaveId, int offset, int and, int or) {
        try {
            WriteMaskRegisterRequest request = new WriteMaskRegisterRequest(slaveId, offset, and, or);
            WriteMaskRegisterResponse response = (WriteMaskRegisterResponse) master.send(request);

            if (response.isException())
                System.out.println("Exception response: message=" + response.getExceptionMessage());
            else
                System.out.println("Success");
        }
        catch (ModbusTransportException e) {
            e.printStackTrace();
        }
    }
    
	
	
}
