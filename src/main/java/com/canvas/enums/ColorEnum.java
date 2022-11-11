package com.canvas.enums;

public enum ColorEnum {
	BLACK(30, "black"),
	RED(31, "red"),
	WHITE(37, "white");
	
	private Integer code;

	private String desc;
	
	private ColorEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
}
