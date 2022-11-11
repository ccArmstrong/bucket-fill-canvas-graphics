package com.canvas.enums;

public enum CommandEnum {
	C("C", "create"),
	Q("Q", "quit")
	;
	
	private String code;
	
	private String desc;
	
	CommandEnum (String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	/**
	 * check whether the input command valid
	 * @param cmd input command
	 * @return boolean valid return true; else return false
	 */
	public static boolean isValidCommand(String cmd) {
		CommandEnum[] cmdEnums = CommandEnum.values();
		for (CommandEnum en : cmdEnums) {
			if (en.getCode().equals(cmd)) {
				return true;
			}
		}
		return false;
	}
	
	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
	
	
}
