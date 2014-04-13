package gui;

public class Driver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(ColorConverter.HexToRGB(0xAAAAAA)[0] +" " +ColorConverter.HexToRGB(0xAAAAAA)[1] +" "+ColorConverter.HexToRGB(0xAAAAAA)[2]);
		System.out.println(Integer.toHexString(ColorConverter.RGBtoHex(0xAA, 0xBB, 0xCC)));
	}

}
