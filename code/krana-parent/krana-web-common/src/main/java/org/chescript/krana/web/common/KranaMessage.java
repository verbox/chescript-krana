package org.chescript.krana.web.common;

public class KranaMessage {
	private String type;
	private String msg;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	@Override
	public String toString() {
		return "KranaMessage [type=" + type + ", msg=" + msg + "]";
	}
	public KranaMessage(String type, String msg) {
		super();
		this.type = type;
		this.msg = msg;
	}
	public KranaMessage() {
		super();
	}
}
