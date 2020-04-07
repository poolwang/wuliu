package cn.wl.logistic.mo;

//提示信息的类
public class MessageObject {
	
	private Integer code;
	private String msg;
	public MessageObject() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageObject(Integer code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "MessageObject [code=" + code + ", msg=" + msg + "]";
	}
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	

}
