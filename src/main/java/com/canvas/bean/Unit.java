package com.canvas.bean;

import com.canvas.consts.Consts;
import com.canvas.enums.ColorEnum;

public class Unit {

	private String cell;
	
	private Integer color;
	
	private boolean canBeFill;

	public Unit(String cell, Integer color, boolean canBeFill) {
		this.cell = cell != null ? cell : Consts.FILL_BY_SPACE;
		this.color = color != null ? color : ColorEnum.BLACK.getCode();
		this.canBeFill = canBeFill;
	}
	
	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public Integer getColor() {
		return color;
	}

	public void setColor(Integer color) {
		this.color = color;
	}

	public boolean isCanBeFill() {
		return canBeFill;
	}

	public void setCanBeFill(boolean canBeFill) {
		this.canBeFill = canBeFill;
	}
	
}
