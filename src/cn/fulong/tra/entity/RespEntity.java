package cn.fulong.tra.entity;

import cn.fulong.tra.util.RespCode;

public class RespEntity {
	    private int code;
	    private String msg;
	    
	    private Object data;
	    private Object data2;
	    private String status1;
	    private String status2;
	    private String time;


		public RespEntity(RespCode respCode) {
	        this.code = respCode.getCode();
	        this.msg = respCode.getMsg();
	        
	    }

	    public RespEntity(RespCode respCode, Object data,Object data2,String status1,String status2,String time) {
	        this(respCode);
	        this.data = data;
	        this.data2 = data2;
	        this.status1 = status1;
	        this.status2 = status2;
	        this.time = time;
	        
	    } 
	    

	    public int getCode() {
			return code;
		}

		public void setCode(int code) {
			this.code = code;
		}

		public String getMsg() {
			return msg;
		}

		public void setMsg(String msg) {
			this.msg = msg;
		}

		public Object getData() {
			return data;
		}

		public void setData(Object data) {
			this.data = data;
		}


		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public Object getData2() {
			return data2;
		}

		public void setData2(Object data2) {
			this.data2 = data2;
		}

		public String getStatus1() {
			return status1;
		}

		public void setStatus1(String status1) {
			this.status1 = status1;
		}

		public String getStatus2() {
			return status2;
		}

		public void setStatus2(String status2) {
			this.status2 = status2;
		}
		
}
