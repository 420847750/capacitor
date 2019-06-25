package cn.fulong.tra.util;

/**
 * @author Administrator
 *封装为respcode类
 */
public enum  RespCode {
		SUCCESS(1, "请求成功"),
	    WARN(0, "网络异常，请稍后重试");

	    private int code;
	    private String msg;

	    RespCode(int code, String msg) {
	        this.msg = msg;
	    }

	    public int getCode() {
	        return code;
	    }
	    public String getMsg() {
	        return msg;
	    }
}
