package com.watergrid.dst.client.manager;


import java.util.ArrayList;

import com.google.gwt.xml.client.Document;
import com.google.gwt.xml.client.XMLParser;
import com.google.gwt.xml.client.Element;
import com.google.gwt.xml.client.Node;
import com.google.gwt.xml.client.NodeList;
import com.watergrid.dst.client.model.Boundary;
import com.watergrid.dst.client.model.Coordinate;
import com.watergrid.dst.client.model.DMAmodel;
import com.watergrid.dst.client.model.Junction;
import com.watergrid.dst.client.model.Piping;
import com.watergrid.dst.client.model.Reservoir;
import com.watergrid.dst.client.model.Valve;


//this class converts XML files into the corresponding objects
public class ModelFactoryHelperXML {
	
	/////////////
	//BOUNDARY
	/////////////
	//this method creates the boundary Object using th XML string and assigns it to the received model. Throws exception if parsing fails
	static public void createBoundaryFromXML(String XMLstring, DMAmodel model) throws Exception{
		
		String point1_X;	//coordinates in the XML. The xml only includes 2 points. TopLeft(1), and BottomRight(2)
		String point1_Y;
		String point2_X;
		String point2_Y;
		
		Double point1_Lat;	//coordinates of the points converted to lat lng (Double)
		Double point1_Lng;
		Double point2_Lat;
		Double point2_Lng;
		
		String[] temp;
		Coordinate tempCoordinate;
		
		try{
			
			//get the information from the XML
			Document doc = XMLParser.parse(XMLstring);
			NodeList points = doc.getElementsByTagName("coord");
			Node point1 = points.item(0);
			Node point2 = points.item(1);
			point1_X = ((Element)point1).getAttribute("x");
			point1_Y = ((Element)point1).getAttribute("y");
			point2_X = ((Element)point2).getAttribute("x");
			point2_Y = ((Element)point2).getAttribute("y");
			//convert the coordinates of the points to Lat Lng using the coordinateConverter
			temp=CoordinateConverter.convertToLatLng(point1_X,point1_Y);
			point1_Lat=Double.parseDouble(temp[0]);
			point1_Lng=Double.parseDouble(temp[1]);
			temp=CoordinateConverter.convertToLatLng(point2_X,point2_Y);
			point2_Lat=Double.parseDouble(temp[0]);
			point2_Lng=Double.parseDouble(temp[1]);
			//create the boundary object
			Boundary boundary = new Boundary();
			//create the 4 points of the boundary using the 2 points (create a square) and assign to boundary object
			tempCoordinate = new Coordinate(point1_Lat,point1_Lng);
			boundary.setPointBttmLeft(tempCoordinate);
			tempCoordinate = new Coordinate(point1_Lat,point2_Lng);
			boundary.setPointBttmRight(tempCoordinate);
			tempCoordinate = new Coordinate(point2_Lat,point2_Lng);
			boundary.setPointTopRight(tempCoordinate);
			tempCoordinate = new Coordinate(point2_Lat,point1_Lng);
			boundary.setPointTopLeft(tempCoordinate);
			//assign the created boundary object to the received DMAmodel
			model.setBounds(boundary);

		}catch (Exception e){
			throw new Exception ("parsing failed");
		}
	}
	
	
	/////////////
	//JUNCTIONS
	/////////////
	//this method creates the Junctions Objects using the XML string and assigns it to the received model. Throws exception if parsing fails
	static public void createJunctionsFromXML(String XMLstring, DMAmodel model) throws Exception{
		
		try{
			//get the information from the XML
			Document doc = XMLParser.parse(XMLstring);
			NodeList junctionsList = doc.getElementsByTagName("junction");

			//iterate over each junction
			for(int i=0;i<junctionsList.getLength();i++){
				
				//extract data of each Junction in the XML (from each node in the NodeList)
				Node junctionNode = junctionsList.item(i);
				Element junctionElement=(Element)junctionNode;
				
				String id = junctionElement.getAttribute("id");
				float elevation = Float.parseFloat(junctionElement.getAttribute("elevation"));
				float demand = Float.parseFloat(junctionElement.getAttribute("demand"));
				
				Element coordinateElement = (Element) junctionElement.getElementsByTagName("coord").item(0);
				String point_x = coordinateElement.getAttribute("x");
				String point_y = coordinateElement.getAttribute("y");
				
				//convert the coordinates of the point to LatLng and create the Coordinate object
				String[] temp;
				Coordinate coordinate;
				Double pointLat,pointLng;
				temp=CoordinateConverter.convertToLatLng(point_x,point_y);
				pointLat=Double.parseDouble(temp[0]);
				pointLng=Double.parseDouble(temp[1]);
				coordinate = new Coordinate(pointLat, pointLng);
				
				//create the junction object and add it to the model
				Junction junction = new Junction(i,id,elevation,demand,coordinate);
				model.addJunction(junction);	
			}
			
		}catch (Exception e){
			throw new Exception ("parsing failed");
		}
		
	}
	
	
	/////////////
	//PIPES
	/////////////
	static public void createPipesFromXML(String XMLstring, DMAmodel model) throws Exception{
		
		try{
			//get the information from the XML
			Document doc = XMLParser.parse(XMLstring);
			NodeList pipeList = doc.getElementsByTagName("pipe");
			
			//iterate over each Pipe
			for(int i=0;i<pipeList.getLength();i++){
				
				//extract data of each Pipe in the XML (from each node in the NodeList)
				Node pipeNode = pipeList.item(i);
				Element pipeElement=(Element)pipeNode;
				
				String id = pipeElement.getAttribute("id");
				float length = Float.parseFloat(pipeElement.getAttribute("length"));
				float diameter = Float.parseFloat(pipeElement.getAttribute("diameter"));
				float roughness = Float.parseFloat(pipeElement.getAttribute("roughness"));
				float minorLoss = Float.parseFloat(pipeElement.getAttribute("minorLoss"));
				String status = pipeElement.getAttribute("status");
				String startNodeId = pipeElement.getAttribute("startnode");
				String endNodeId = pipeElement.getAttribute("endnode");
				
				
				//extract the vertices of the pipe. The number of vertices can vary, that is why we use a loop
				NodeList verticesList = pipeElement.getElementsByTagName("coord");
				ArrayList<Coordinate> vertices = new ArrayList<Coordinate>();
				for(int j=0;j<verticesList.getLength();j++){
					Node vertixNode = verticesList.item(j);
					Element vertixElement=(Element)vertixNode;
					String point_x = vertixElement.getAttribute("x");
					String point_y = vertixElement.getAttribute("y");
					//convert point to Lat Lng
					String[] temp;
					Coordinate coordinate;
					Double pointLat,pointLng;
					temp=CoordinateConverter.convertToLatLng(point_x,point_y);
					pointLat=Double.parseDouble(temp[0]);
					pointLng=Double.parseDouble(temp[1]);
					coordinate = new Coordinate(pointLat, pointLng);
					vertices.add(coordinate);
				}
				
				//create the Pipe object and add it to the model
				Piping pipe = new Piping(i,id,length,diameter,roughness,minorLoss,status,vertices,startNodeId,endNodeId);
				model.addPipe(pipe);
			}

			
		}catch (Exception e){
			throw new Exception ("parsing failed");
		}
		
	}
	
	/////////////
	//RESERVOIRS
	/////////////
	//this method creates the Reservoirs Objects using the XML string and assigns it to the received model. Throws exception if parsing fails
	static public void createReservoirsFromXML(String XMLstring, DMAmodel model) throws Exception{
		
		try{
			//get the information from the XML
			Document doc = XMLParser.parse(XMLstring);
			NodeList reservoirList = doc.getElementsByTagName("reservoir");

			//iterate over each reservoir
			for(int i=0;i<reservoirList.getLength();i++){
				
				//extract data of each reservoir in the XML (from each node in the NodeList)
				Node reservoirNode = reservoirList.item(i);
				Element reservoirElement=(Element)reservoirNode;
				
				String id = reservoirElement.getAttribute("id");
				float head = Float.parseFloat(reservoirElement.getAttribute("head"));

				
				Element coordinateElement = (Element) reservoirElement.getElementsByTagName("coord").item(0);
				String point_x = coordinateElement.getAttribute("x");
				String point_y = coordinateElement.getAttribute("y");
				
				//convert the coordinates of the point to LatLng and create the Coordinate object
				String[] temp;
				Coordinate coordinate;
				Double pointLat,pointLng;
				temp=CoordinateConverter.convertToLatLng(point_x,point_y);
				pointLat=Double.parseDouble(temp[0]);
				pointLng=Double.parseDouble(temp[1]);
				coordinate = new Coordinate(pointLat, pointLng);
				
				//create the Reservoir object and add it to the model
				Reservoir reservoir = new Reservoir(i,id,coordinate,head);
				model.addReservoir(reservoir);	
				
			}
			
		}catch (Exception e){
			throw new Exception ("parsing failed");
		}
		
	}
	
	/////////////
	//VALVES
	/////////////
	// WARNING< THIS MEHTOD HAS YET TO BE TESTED WITH SOME SAMPLE XML ON SERVER
	static public void createValvesFromXML(String XMLstring, DMAmodel model) throws Exception{
		
		try{
			//get the information from the XML
			Document doc = XMLParser.parse(XMLstring);
			NodeList valvesList = doc.getElementsByTagName("valve");

			//iterate over each valve
			for(int i=0;i<valvesList.getLength();i++){
				
				//extract data of each valve in the XML (from each node in the NodeList)
				Node valveNode = valvesList.item(i);
				Element valveElement=(Element)valveNode;
				
				String id = valveElement.getAttribute("id");

				
				Element coordinateElement = (Element) valveElement.getElementsByTagName("coord").item(0);
				String point_x = coordinateElement.getAttribute("x");
				String point_y = coordinateElement.getAttribute("y");
				
				//convert the coordinates of the point to LatLng and create the Coordinate object
				String[] temp;
				Coordinate coordinate;
				Double pointLat,pointLng;
				temp=CoordinateConverter.convertToLatLng(point_x,point_y);
				pointLat=Double.parseDouble(temp[0]);
				pointLng=Double.parseDouble(temp[1]);
				coordinate = new Coordinate(pointLat, pointLng);
				
				//create the Valve object and add it to the model
				Valve valve = new Valve(i,id,coordinate);
				model.addValve(valve);	
				
				
				
				
			}
			
		}catch (Exception e){
			throw new Exception ("parsing failed");
		}
		
	}
	

}
