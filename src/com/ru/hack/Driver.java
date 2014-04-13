package com.ru.hack;

import utility.Utility;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(Utility.HexToRGB(0xAAAAAA)[0] +" " +Utility.HexToRGB(0xAAAAAA)[1] +" "+Utility.HexToRGB(0xAAAAAA)[2]);
		System.out.println(Integer.toHexString(Utility.RGBtoHex(0xAA, 0xBB, 0xCC)));
	}

}
