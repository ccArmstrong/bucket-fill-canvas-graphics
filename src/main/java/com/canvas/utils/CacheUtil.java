package com.canvas.utils;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.canvas.bean.Coordinate;
import com.canvas.graphics.Graphic;
import com.canvas.graphics.models.Line;

public class CacheUtil {

	// record that how many and what kind of graphic have stored in the canvas
	private static List<Graphic> graphicList = new LinkedList<>();

	// coordinate list of line, only for line
	private static Map<Graphic, List<Coordinate>> lineCoorMap = new LinkedHashMap<>();
	
	public static void setGraphic(Graphic graphic) {
		CacheUtil.graphicList.add(graphic);
		
		// line should statistic coordinate
		if (graphic instanceof Line) {
			// check and initialize
			Line l = (Line)graphic;
			l.check();
			l.convertArgsToCoordinate();
			
			// calculate coordinate
			List<Coordinate> corList = new LinkedList<>();
			if (l.getY1() == l.getY2() && l.getX1() != l.getX2()) {
				for (int i = l.getX1(); i <= l.getX2(); i++) {
					Coordinate xCor = new Coordinate(i, l.getY1());
					corList.add(xCor);
				}
			} else if (l.getX1() == l.getX2() && l.getY1() != l.getY2()) {
				for (int j = l.getY1(); j <= l.getY2(); j++) {
					Coordinate yCor = new Coordinate(l.getX1(), j);
					corList.add(yCor);
				}
			} 
			CacheUtil.lineCoorMap.put(l, corList);
		}
	}
	
	public static List<Graphic> getGraphicList() {
		return graphicList;
	}

	public static Map<Graphic, List<Coordinate>> getLineCoorMap() {
		return lineCoorMap;
	}
}
