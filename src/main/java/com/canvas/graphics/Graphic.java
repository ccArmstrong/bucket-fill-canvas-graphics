package com.canvas.graphics;

import java.util.LinkedHashMap;

import com.canvas.bean.Unit;

public abstract class Graphic {

	private String[] args; 
	
	/**
	 * createGraphic 
	 * @param fillChar fill the graphic with char
	 * @param canvasMap canvas map to create graphic
	 * @param args 入参
	 */
	public void createGraphicV0(LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> canvasMap) {
		check();
		createGraphic(canvasMap);
	}
	
	/**
	 * graphics who extends this abstract class implements the createGraphic itself
	 * @param fillChar fill the graphic with char
	 * @param canvasMap canvas map to create graphic
	 */
	public abstract void createGraphic(LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> canvasMap);
	
	/**
	 * check graphic parameters before createGraphic
	 * @param args input parameters
	 * @return boolean 
	 */
	public abstract boolean check();

	public String[] getArgs() {
		return args;
	}

	public void setArgs(String[] args) {
		this.args = args;
	}
}
