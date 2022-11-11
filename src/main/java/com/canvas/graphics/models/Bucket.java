package com.canvas.graphics.models;

import java.util.LinkedHashMap;
import java.util.Map;

import com.canvas.bean.Unit;
import com.canvas.consts.Consts;
import com.canvas.consts.MsgConst;
import com.canvas.enums.ColorEnum;
import com.canvas.graphics.Graphic;
import com.canvas.utils.DataUtils;

public class Bucket extends Graphic {

	private Integer x;
	
	private Integer y;
	
	private String ch;
	
	/**
	 * graphics who extends this abstract class implements the createGraphic itself
	 * @param fillChar fill the graphic with char
	 * @return canvasMap canvas map to create graphic
	 */
	@Override
	public void createGraphic(LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> canvasMap) {
		convertArgsToCoordinate();
		
		// 填充点
		String fillChar = ch != null ? ch : Consts.FILL_BY_C;
		for (Map.Entry<Integer, LinkedHashMap<Integer, Unit>> colEntry : canvasMap.entrySet()) {
			for (Map.Entry<Integer, Unit> rowEntry : colEntry.getValue().entrySet()) {
				Unit valUnit = rowEntry.getValue();
				if (valUnit.isCanBeFill() && Consts.FILL_BY_SPACE.equals(valUnit.getCell())) {
					rowEntry.setValue(new Unit(fillChar, ColorEnum.RED.getCode(), true));
				}
			}
		}
	}

	@Override
	public boolean check() {
		if (this.getArgs().length < 3) {
			throw new RuntimeException(MsgConst.tipsBucketLength);
		} else if (!DataUtils.isDigital(this.getArgs()[0]) 
				|| !DataUtils.isDigital(this.getArgs()[1])) {
			throw new RuntimeException(MsgConst.tipsBucketConnectCoordinates);
		}
		return true;
	}
	
	private void convertArgsToCoordinate() {
		String[] args = this.getArgs();
		this.x = Integer.parseInt(args[0]) <= 0 ? 1 : Integer.parseInt(args[0]);
		this.y = Integer.parseInt(args[1]) <= 0 ? 1 : Integer.parseInt(args[1]);
		this.ch = args[2];
	}

	public Integer getX() {
		return x;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}
	
	
}
