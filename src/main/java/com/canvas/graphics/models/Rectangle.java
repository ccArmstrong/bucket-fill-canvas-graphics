package com.canvas.graphics.models;

import java.util.LinkedHashMap;

import com.canvas.bean.Unit;
import com.canvas.consts.Consts;
import com.canvas.consts.MsgConst;
import com.canvas.enums.ColorEnum;
import com.canvas.graphics.Graphic;
import com.canvas.utils.DataUtils;

public class Rectangle extends Graphic {

	private Integer x1;
	
	private Integer y1;
	
	private Integer x2;
	
	private Integer y2;

	/**
	 * graphics who extends this abstract class implements the createGraphic itself
	 * @param fillChar fill the graphic with char
	 * @return canvasMap canvas map to create graphic
	 */
	@Override
	public void createGraphic(LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> canvasMap) {
		convertArgsToCoordinate();
		
		String fillChar = Consts.FILL_BY_X;
		for (int i = this.y1; i <= this.y2; i++) {
			LinkedHashMap<Integer, Unit> colMap = canvasMap.get(i);
			
			// the first and last row fulfill with charSr
			if (i == this.y1 || i == this.y2) {
				for (int j = this.x1; j <= this.x2; j++) {
					colMap.put(j, new Unit(fillChar, ColorEnum.WHITE.getCode(), true));
				}
			} else {
				for (int k = this.x1; k <= this.x2; k++) {
					if (k == this.x1 || k == this.x2) {
						colMap.put(k, new Unit(fillChar, ColorEnum.WHITE.getCode(), true));
					} else {
						colMap.put(k, new Unit(Consts.FILL_BY_SPACE, null, false));
					}
				}
			}
		}
	}
	
	@Override
	public boolean check() {
		if (this.getArgs().length < 4) {
			throw new RuntimeException(MsgConst.tipsRectangleLength);
		} else if (!DataUtils.isDigital(this.getArgs()[0]) 
				|| !DataUtils.isDigital(this.getArgs()[1]) 
				|| !DataUtils.isDigital(this.getArgs()[2]) 
				|| !DataUtils.isDigital(this.getArgs()[3])) {
			throw new RuntimeException(MsgConst.tipsRectangleCoordinates);
		}
		return true;
	}
	
	private void convertArgsToCoordinate() {
		String[] args = this.getArgs();
		this.x1 = Integer.parseInt(args[0]) <= 0 ? 1 : Integer.parseInt(args[0]);
		this.y1 = Integer.parseInt(args[1]) <= 0 ? 1 : Integer.parseInt(args[1]);
		this.x2 = Integer.parseInt(args[2]) <= 0 ? 1 : Integer.parseInt(args[2]);
		this.y2 = Integer.parseInt(args[3]) <= 0 ? 1 : Integer.parseInt(args[3]);
	}

	public Integer getX1() {
		return x1;
	}

	public void setX1(Integer x1) {
		this.x1 = x1;
	}

	public Integer getY1() {
		return y1;
	}

	public void setY1(Integer y1) {
		this.y1 = y1;
	}

	public Integer getX2() {
		return x2;
	}

	public void setX2(Integer x2) {
		this.x2 = x2;
	}

	public Integer getY2() {
		return y2;
	}

	public void setY2(Integer y2) {
		this.y2 = y2;
	}
	
	
}
