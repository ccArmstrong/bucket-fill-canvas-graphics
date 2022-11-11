package com.canvas;

import java.util.LinkedHashMap;
import java.util.Map;

import com.canvas.bean.Unit;
import com.canvas.consts.Consts;
import com.canvas.consts.MsgConst;
import com.canvas.enums.ColorEnum;
import com.canvas.graphics.Graphic;
import com.canvas.utils.CacheUtil;
import com.canvas.utils.DataUtils;

public class Canvas {

	/**
	 * width of canvas
	 */
	private Integer width;
	
	/**
	 * height of canvas
	 */
	private Integer height;

	/**
	 * contains canvas cell content
	 */
	private LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> linkedData = new LinkedHashMap<>();
	
	/**
	 * create Canvas with height and width
	 * @param width
	 * @param height
	 */
	public Canvas(String[] args) {
		if (args.length < 2) {
			throw new RuntimeException(MsgConst.tipsCanvasWH);
		} else if (!DataUtils.isDigital(args[0]) 
				|| !DataUtils.isDigital(args[1])) {
			throw new RuntimeException(MsgConst.tipsCanvasWHDigital);
		}
		
		this.width = Integer.parseInt(args[0]);
		this.height = Integer.parseInt(args[1]);
		
		for (int i = 0; i < this.height + 2; i++) {
			LinkedHashMap<Integer, Unit> colMap = new LinkedHashMap<>();
			for (int j = 0; j < this.width + 2; j++) {
				if (i == 0 || i == (this.height + 2 - 1)) {
					colMap.put(j, new Unit(Consts.FILL_BY_HLINE, ColorEnum.WHITE.getCode(), true));
				} else if (j ==0 || j == this.width + 2 - 1) {
					colMap.put(j, new Unit(Consts.FILL_BY_VLINE, ColorEnum.WHITE.getCode(), true));
				} else {
					colMap.put(j, new Unit(Consts.FILL_BY_SPACE, null, true));
				}
			}
			
			linkedData.put(i, colMap);
		}
	}
	
	/**
	 * initialize canvas
	 */
	public void init() {
		if (linkedData.size() == 0) {
			return;
		}
		
		for (Map.Entry<Integer, LinkedHashMap<Integer, Unit>> rowEntry : linkedData.entrySet()) {
			LinkedHashMap<Integer, Unit> colMap = rowEntry.getValue();
			for (Map.Entry<Integer, Unit> colEntry : colMap.entrySet()) {
				System.out.print(getColorOutputString(colEntry.getValue().getCell(), colEntry.getValue().getColor()));
			}
			System.out.println();
		}
	}
	
	/**
	 * print the graphic and canvas
	 * @param graphic line or rectangle
	 */
	public void paint(Graphic graphic) {
		if (graphic != null) {
			// store graphic info into cache
			CacheUtil.setGraphic(graphic);
			
			// create graphic in canvas
			graphic.createGraphicV0(linkedData);
		}
		init();
	}
	
	private static String getColorOutputString(String content, int color) {
		return String.format("\033[%dm%s\033[0m", color, content);
	}
	
	
	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}
}
