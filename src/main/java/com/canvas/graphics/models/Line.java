package com.canvas.graphics.models;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.canvas.bean.Coordinate;
import com.canvas.bean.Unit;
import com.canvas.consts.Consts;
import com.canvas.consts.MsgConst;
import com.canvas.enums.ColorEnum;
import com.canvas.graphics.Graphic;
import com.canvas.utils.CacheUtil;
import com.canvas.utils.DataUtils;

public class Line extends Graphic {

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
		// convert arguments to coordinate
		this.convertArgsToCoordinate();
			
		// the coordinate set of current line
		List<Coordinate> corList = new LinkedList<>();
		String fillChar = Consts.FILL_BY_X;
		if (this.x1 == x2) {
			for (int i = y1; i <= y2; i++) {
				LinkedHashMap<Integer, Unit> colMap = canvasMap.get(i);
				colMap.put(this.x1, new Unit(fillChar, ColorEnum.WHITE.getCode(), true));
				corList.add(new Coordinate(this.x1, i));
			}
		} else if (this.y1 == this.y2) {
			LinkedHashMap<Integer, Unit> colMap = canvasMap.get(this.y1);
			for (int i = this.x1; i <= x2; i++) {
				colMap.put(i, new Unit(fillChar, ColorEnum.WHITE.getCode(), true));
				corList.add(new Coordinate(i, this.y1));
			}
		} else {
			// for slashes, temporary exclude
		}
		
		// calculate inaccessible area with current line
		this.calculateInaccessibleArea(corList, canvasMap);
	}
	
	@Override
	public boolean check() {
		if (this.getArgs().length < 4) {
			throw new RuntimeException(MsgConst.tipsLineLength);
		} else if (!DataUtils.isDigital(this.getArgs()[0]) 
				|| !DataUtils.isDigital(this.getArgs()[1]) 
				|| !DataUtils.isDigital(this.getArgs()[2]) 
				|| !DataUtils.isDigital(this.getArgs()[3])) {
			throw new RuntimeException(MsgConst.tipsLineCoordinates);
		}
		return true;
	}
	
	private void calculateInaccessibleArea(List<Coordinate> corList, LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> canvasMap) {
		// calculate current line coordinate set
		Map<Graphic, List<Coordinate>> lineGraphicMap = CacheUtil.getLineCoorMap();
		if (lineGraphicMap.size() == 0) {
			return;
		}
		
		for (Map.Entry<Graphic, List<Coordinate>> lineGraphicEntry : lineGraphicMap.entrySet()) {
			// ignore the same line with equals and hash code
			Line mapLine = (Line)lineGraphicEntry.getKey();
			if (mapLine.equals(this)) {
				continue;
			}
			
			List<Coordinate> mapLineCoordinates = lineGraphicEntry.getValue();
			for (Coordinate orgLineCor : mapLineCoordinates) {
				Coordinate leftCoordinate = new Coordinate(orgLineCor.getX() - 1, orgLineCor.getY());
				Coordinate upCoordinate = new Coordinate(orgLineCor.getX(), orgLineCor.getY() - 1);
				Coordinate rightCoordinate = new Coordinate(orgLineCor.getX() + 1, orgLineCor.getY());
				Coordinate downCoordinate = new Coordinate(orgLineCor.getX(), orgLineCor.getY() + 1);
				
				// the coordinate of new line coincidence with exist line coordinate
				if (corList.contains(orgLineCor)
						|| corList.contains(leftCoordinate)
						|| corList.contains(upCoordinate)
						|| corList.contains(rightCoordinate)
						|| corList.contains(downCoordinate)) {
					Integer maxY = Math.max(Math.max(mapLine.getY2(), orgLineCor.getY()), this.y2);
					if (orgLineCor.getY() < maxY) {
						this.calculateInaccessibleArea(mapLine, orgLineCor, canvasMap);
					}
				}
			}
			
		}
	}
	
	private void calculateInaccessibleArea(Line orgLine, Coordinate coinCor, LinkedHashMap<Integer, LinkedHashMap<Integer, Unit>> canvasMap) {
		Integer stX = Math.min(Math.min(orgLine.x1, coinCor.getX()), this.x1);
		Integer stY = Math.min(Math.min(orgLine.y1, coinCor.getY()), this.y1);
		Integer etX = Math.max(Math.max(orgLine.x2, coinCor.getX()), this.x2); 
		Integer etY = Math.max(Math.max(orgLine.y2, coinCor.getY()), this.y2);

		// construct rectangle 
		if (stX < etX && stY < etY) {
			for (int i = stY; i <= etY; i++) {
				for (int j = stX; j <= etX; j++) {
					Unit cellUnit = canvasMap.get(i).get(j);
					if (!cellUnit.isCanBeFill()) {
						continue;
					} else if (Consts.FILL_BY_SPACE.equals(cellUnit.getCell())) {
						cellUnit.setCanBeFill(false);
					} 
					canvasMap.get(i).put(j, cellUnit);
				}
			}
		}
		
	}
	
	public void convertArgsToCoordinate() {
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

	@Override
	public int hashCode() {
		return Objects.hash(x1, x2, y1, y2);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Line other = (Line) obj;
		return Objects.equals(x1, other.x1) && Objects.equals(x2, other.x2) && Objects.equals(y1, other.y1)
				&& Objects.equals(y2, other.y2);
	}
}
