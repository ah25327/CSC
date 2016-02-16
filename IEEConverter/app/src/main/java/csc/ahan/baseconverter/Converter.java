package csc.ahan.baseconverter;

//Author: ah25327
//Class: CSC 205 (003N) with Professor Tanes

import java.util.HashMap;

public class Converter {
	private static String exponentKey = "exponent";
	private static String mantissaKey = "mantissa";
	public Converter(){
	}

	//Main method to convert a decimal number to IEEE754 number
	public static String decimalTo7554(String decimalNumber, boolean singlePrecision) {
		float value = Float.parseFloat(decimalNumber);
		String result = "";
		String sign = "";
		if(value >= 0){
			sign = "0";
		}
		else{
			sign = "1";
		}
		int exp = getExponent(value);
		String exponent = convertExponent(exp, singlePrecision);
		String mantissa = getMantissa(value, exp, singlePrecision);

		result = result.concat(sign).concat(exponent).concat(mantissa);

		return result;
	}
	//Method to get the exponent for IEE754
	public static int getExponent(float number){
		int exponent = 0;
		number = Math.abs(number);
		boolean isInside = false;
		while(!isInside){

			if(number < 2){
				isInside = true;
			}
			else{
				number = number/2;
				exponent++;
			}
		}
		return exponent;
	}
	//Method to get the mantissa of the number
	public static String getMantissa(float number, int exponent, boolean singlePrecision){
		float dividend = Math.abs(number)/(float)(Math.pow(2.0, (double) exponent))-1;
		String result = "";
		int upperBound = 0;
		if(singlePrecision){
			upperBound = 24;
		}
		else{
			upperBound = 53;
		}
		for(int i = 1; i<upperBound; i++){
			if(dividend >= Math.pow(2.0, -(double)i)){
				dividend -= Math.pow(2.0, -(double) i);
				result = result.concat("1");
			}
			else{
				result = result.concat("0");
			}
		}


		return result;
	}
	//Method to format the exponent to the right format based on single or double precision
	public static String convertExponent(int exponent, boolean singlePrecision){
		String result = "";
		if(singlePrecision){
			result = String.format("%8s", Integer.toBinaryString(exponent+127)).replace(' ', '0');
		}
		else{
			result = String.format("%11s", Integer.toBinaryString(exponent+1023)).replace(' ', '0');
		}
		return result;
	}

	//Main method to convert binary number back to decimal
	public static String parseValueToDecimal(String binaryNumber){
		String result = "";
		String signValue = binaryNumber.substring(0,1);
		HashMap<String, String> numberParts = parseBinaryNumber(binaryNumber);
		double exponent = parseExponentToDecimal(numberParts.get(exponentKey));
		double mantissa = parseMantissaToDecimal(numberParts.get(mantissaKey));
		double product = Math.pow(2.0, exponent)*mantissa;
		result = Double.toString(product);
		if(signValue.equals("1")){
			result = "-" + result;
		}
		return result;
	}

	//Method that breaks a IEEE754 floating point number into its components
	public static HashMap<String, String> parseBinaryNumber(String binaryNumber){
		HashMap<String, String> results = new HashMap<String, String>();
		int length = binaryNumber.length();
		String exponentValue = "";
		String mantissaValue = "";
		if(length == 32){
			exponentValue = binaryNumber.substring(1,9);
			mantissaValue = binaryNumber.substring(9);
		}
		else{
			exponentValue = binaryNumber.substring(1, 12);
			mantissaValue = binaryNumber.substring(12);
		}
		results.put(exponentKey, exponentValue);
		results.put(mantissaKey, mantissaValue);
		return results;
	}

	//Converts mantissa back into decimal form number
	public static double parseMantissaToDecimal(String mantissa){
		double result = 1.0;
		char[] values = mantissa.toCharArray();
		double base = 0;
		for(int i = 1; i <= values.length; i++){
			base = Double.parseDouble(Character.toString(values[i-1]));
			result += Math.pow(2, -i)*base;
		}
		return result;
	}
	//Converts exponent back to decimal form number
	public static double parseExponentToDecimal(String exponent){
		int length = exponent.length();
		int result = 0;
		if(length == 8){
			result = Integer.parseInt(exponent,2) - 127;
		}
		else{
			result = Integer.parseInt(exponent,2) - 1023;
		}
		return (double) result;
	}


}
