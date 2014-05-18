//this class deals with the conversion of coordinates.
//it uses JSNI to access a JavaScript Library. Included in the HTML

package com.watergrid.dst.client.manager;

public class CoordinateConverter {
	
	//this method receives a string of X and Y coordinate, and converts it to Lat Long
	//and returns an array with the Lat and Lng as Strings
	public static String[] convertToLatLng(String x, String y){
		
		String conversion =convertUsingJS(x,y);
		
		String[] latLng = conversion.split("&");
		
		return latLng;
	}
	
	//JSNI to interact with the JS library that converts the coordinates
	//the JavaScript receives the X and Y coordinates as String and returns the Lat and Lng coordinates as a String separeted by "&" character
	private static native String convertUsingJS(String x,String y)/*-{
	
		var conversion;
		conversion=$wnd.convertPoint(x,y);
		return conversion;

	}-*/;
	
	
	
	

	

}
