package com.canvas;

import java.util.Scanner;

import com.canvas.config.RegisterGraphic;
import com.canvas.consts.Consts;
import com.canvas.consts.MsgConst;
import com.canvas.enums.ColorEnum;
import com.canvas.enums.CommandEnum;
import com.canvas.graphics.Graphic;

/**
 * application run by java version of jdk1.8 or later
 */
public class Main {

	static Canvas canvas = null;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws Exception {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.print("enter command:");
			while (sc.hasNext()) {
				String inputLine = sc.nextLine();
				if (inputLine != null) {
					String[] inputStr = inputLine.split(Consts.FILL_BY_SPACE);
					
					// get command and parameters
					String cmd = inputStr[0];
					String[] parmStrings = new String[inputStr.length - 1];
					System.arraycopy(inputStr, 1, parmStrings, 0, inputStr.length - 1);
					
					try {
						// execute command
						if (CommandEnum.C.getCode().equals(cmd)) {
							canvas = new Canvas(parmStrings);
							canvas.init();
						} else if (CommandEnum.Q.getCode().equals(cmd)) {
							System.exit(1);
						} else if (RegisterGraphic.isGraphicCmd(cmd)) {
							// the command is create graphic, then reflect the specialize graphic
							if (!isCanvasInit()) {
								prompt(MsgConst.tipsCanvasInit);
							} else {
								String clsName = RegisterGraphic.getGraphicClassName(cmd);
								Class<Graphic> clazz = (Class<Graphic>) Class.forName(clsName);
								Graphic graphic = clazz.newInstance();
								graphic.setArgs(parmStrings);
								canvas.paint(graphic);
							}				
						} else {
							prompt(MsgConst.tipsUnknowCmd);
						}
					} catch (Exception e) {
						prompt(e.getLocalizedMessage());
					} 
				} 
				continueInput();
			}
		}
	}
	
	private static void prompt(String tips) {
		System.out.println(String.format("\033[%dm%s\033[0m", ColorEnum.RED.getCode(), tips));
	}
	
	private static void continueInput() {
		System.out.println();
		System.out.print("enter command:");
	}
	
	private static boolean isCanvasInit() {
		if (canvas == null) {
			return false;
		}
		return true;
	}
}
