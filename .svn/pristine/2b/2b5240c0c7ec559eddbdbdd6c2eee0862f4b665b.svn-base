package com.watergrid.dst.client.view;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.maps.gwt.client.Animation;
import com.google.maps.gwt.client.ControlPosition;
import com.google.maps.gwt.client.GoogleMap;
import com.google.maps.gwt.client.LatLng;
import com.google.maps.gwt.client.LatLngBounds;
import com.google.maps.gwt.client.MVCArray;
import com.google.maps.gwt.client.MapOptions;
import com.google.maps.gwt.client.MapTypeId;
import com.google.maps.gwt.client.Marker;
import com.google.maps.gwt.client.MarkerImage;
import com.google.maps.gwt.client.MarkerOptions;
import com.google.maps.gwt.client.MouseEvent;
import com.google.maps.gwt.client.Polygon;
import com.google.maps.gwt.client.PolygonOptions;
import com.google.maps.gwt.client.Polyline;
import com.google.maps.gwt.client.PolylineOptions;
import com.google.maps.gwt.client.GoogleMap.DragEndHandler;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.watergrid.dst.client.dataobjects.ComponentLevel;
import com.watergrid.dst.client.presenter.NetworkMapPresenter;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ToggleButton;

public class NetworkMap extends Composite{
	
	NetworkMapPresenter presenter;	//reference to the presenter to call it in case of events
	HorizontalPanel buttonsPanel;
	
	ToggleButton btnBoundaryVisibility,btnJunctionsVisibility,btnPipesVisibility,btnReservoirsVisibility,btnValvesVisibility, btnMyLocation,btnCenterMap;
	
	
	GoogleMap map;						//google maps object
	MapOptions mapOptions;				//map options
	LatLng centerLatLng;				//map center
	LatLngBounds latLngBounds;			//represents a rectangle

	
	Polygon boundsPolygon;				//polygon of the the bounds
	ArrayList<Marker> junctionMarkers;	//list of  markers of each type of component
	ArrayList<Marker> reservoirMarkers;
	ArrayList<Marker> valveMarkers;
	ArrayList<Polyline> pipePolylines;
	
	Marker highlightMarker;				//marker to highlight component
	Polyline highlightPolyline;
	
	Marker myLocationMarker;			//my location marker
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public NetworkMap() {
		initializeComponents();
		junctionMarkers= new ArrayList<Marker>();
		reservoirMarkers= new ArrayList<Marker>();
		valveMarkers= new ArrayList<Marker>();
		pipePolylines= new ArrayList<Polyline>();
		highlightMarker=Marker.create();
		highlightPolyline=Polyline.create();
		myLocationMarker = Marker.create();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(NetworkMapPresenter presenter){
		this.presenter=presenter;
	}
	
	/////////////////////
	// VIEW SETUP
	/////////////////////
	
	private void initializeComponents(){
		
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSpacing(1);
		verticalPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		verticalPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanel.setSize("100%", "100%");
		
		HTMLPanel panel = new HTMLPanel("<div id='mapContainer' style='height: 95%; width: 100%'>MAP container</div><div id='menuContainer' style='height: 5%; width: 100%'></div>");
		verticalPanel.add(panel);
		
		//for all browsers
		//panel.setSize("100%", "100%");
		
		//for explorer and firefox
		int mapWidth=(int) ((Window.getClientWidth())*0.63);
		int mapHeight=(int) ((Window.getClientHeight())*0.5);
		panel.setPixelSize(mapWidth, mapHeight);
		
		
		
		
		buttonsPanel = new HorizontalPanel();
		//buttonsPanel.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		buttonsPanel.setSize("100%", "100%");
		panel.add(buttonsPanel, "menuContainer");	//add panel in the div element of the HTML panel
		
		initWidget(verticalPanel);
		addButtons();
	}
	
	private void addButtons(){
		
		//create buttons and add listeners
		//the visibility buttons check for the state of the button and according to that set visibility ON or OFF of the markers or polylines on the ArrayLists
	
	    //boundary
	    btnBoundaryVisibility = new ToggleButton("BOUNDARY", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	btnBoundaryVisibility.setFocus(false);
	        	if(btnBoundaryVisibility.isDown()){
	        		boundsPolygon.setVisible(true);
	        	}
	        	else{
	        		boundsPolygon.setVisible(false);
	        	}
	        }
	      });
	    btnBoundaryVisibility.setSize("100%", "100%");
	    buttonsPanel.add(btnBoundaryVisibility);

	    //Junctions
	    btnJunctionsVisibility = new ToggleButton("JUNCTIONS", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	btnJunctionsVisibility.setFocus(false);
	        	for(int i =0;i<junctionMarkers.size();i++){
	        		if(btnJunctionsVisibility.isDown()){
	        			junctionMarkers.get(i).setVisible(true);
	        		}
	        		else{
	        			junctionMarkers.get(i).setVisible(false);
	        		}
	        	}
	        }
	      });
	    btnJunctionsVisibility.setSize("100%", "100%");
	    buttonsPanel.add(btnJunctionsVisibility);
	    
	    //Pipes
	    btnPipesVisibility = new ToggleButton("PIPES", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	btnPipesVisibility.setFocus(false);
	        	for(int i =0;i<pipePolylines.size();i++){
	        		if(btnPipesVisibility.isDown()){
	        			pipePolylines.get(i).setVisible(true);
	        		}
	        		else{
	        			pipePolylines.get(i).setVisible(false);
	        		}
	        	}
	        }
	      });
	    btnPipesVisibility.setSize("100%", "100%");
	    buttonsPanel.add(btnPipesVisibility);
	    
	    //Reservoirs
	    btnReservoirsVisibility = new ToggleButton("RESERVOIRS", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	btnReservoirsVisibility.setFocus(false);
	        	for(int i =0;i<reservoirMarkers.size();i++){
	        		if(btnReservoirsVisibility.isDown()){
	        			reservoirMarkers.get(i).setVisible(true);
	        		}
	        		else{
	        			reservoirMarkers.get(i).setVisible(false);
	        		}
	        	}
	        }
	      });
	    btnReservoirsVisibility.setSize("100%", "100%");
	    buttonsPanel.add(btnReservoirsVisibility);
	    
	    //Valves
	    btnValvesVisibility = new ToggleButton("VALVES", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	btnValvesVisibility.setFocus(false);
	        	for(int i =0;i<valveMarkers.size();i++){
	        		if(btnValvesVisibility.isDown()){
	        			valveMarkers.get(i).setVisible(true);
	        		}
	        		else{
	        			valveMarkers.get(i).setVisible(false);
	        		}
	        	}
	        }
	      });
	    btnValvesVisibility.setSize("100%", "100%");
	    buttonsPanel.add(btnValvesVisibility);
	    
	    //Show My Location
	    btnMyLocation = new ToggleButton("MY LOCATION", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	btnMyLocation.setFocus(false);
	        	
	        	if(btnMyLocation.isDown()){
        			presenter.requestMyLocation();
        		}
        		else{
        			myLocationMarker.setVisible(false);
        		}
	        }
	      });
	    btnMyLocation.setSize("100%", "100%");
	    buttonsPanel.add(btnMyLocation);

	    //Center Map
	    btnCenterMap = new ToggleButton("RESET MAP", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	map.triggerResize();
	        	btnCenterMap.setFocus(false);
	    		centerLatLng=latLngBounds.getCenter();
	    		mapOptions.setCenter(centerLatLng);
	    		map.fitBounds(latLngBounds);
	    		btnCenterMap.setDown(false);
	    		highlightMarker.setVisible(false);
	    		highlightPolyline.setVisible(false);
	    		myLocationMarker.setVisible(false);
	    		btnMyLocation.setDown(false);				//release my location bttn
	        }
	      });
	    btnCenterMap.setSize("93%", "100%");
	    buttonsPanel.add(btnCenterMap);   
	 }
	

	////////////////////////
	// MAP INITIALIZATION
	///////////////////////
	
	//Called by presenter. this method initializes the map, setting the center and Zoom by using the Bounds of the model. It receives the coordinates of the BottomLeft and TopRight corner of the bounds
	public void InitializeMap(Double Lat_bttmLeft,Double Lng_bttmLeft,Double Lat_topRight,Double Lng_topRight){
		
		//create bounds and center
		LatLng bttmLeft = LatLng.create(Lat_bttmLeft, Lng_bttmLeft);
		LatLng topRight = LatLng.create(Lat_topRight, Lng_topRight);
		latLngBounds=LatLngBounds.create();
		latLngBounds.extend(bttmLeft);
		latLngBounds.extend(topRight);
		centerLatLng=latLngBounds.getCenter();
		
		// create Maps and adds it to the DIV container
		mapOptions = MapOptions.create();
		mapOptions.setCenter(centerLatLng);
		mapOptions.setMapTypeId(MapTypeId.ROADMAP);
		map = GoogleMap.create(Document.get().getElementById("mapContainer"),mapOptions);
		map.fitBounds(latLngBounds);
		
		//this event fires refreshes the map after the user finished dragging the map. This is to solve the tablet problem that sometimes only one part of the map is visible, so the idea with this is that as soon as the user drags the map, it will refresh and be shown corectly
		map.addDragEndListener(new DragEndHandler() {
			@Override
			public void handle() {
				refreshMap();
			}
		});
		
		//add Refresh button (Inside MAP)
	    Button btnRefresh = new Button("", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	refreshMap();
	        }
	      });
	    RootPanel.get().add(btnRefresh);// Add button to the root panel. (register it on the GWT side)
	    map.getControls().get(new Double(ControlPosition.RIGHT_BOTTOM.getValue()).intValue()).push(btnRefresh.getElement());// Add button as a map control.
	    
	    Image imgRefresh = new Image(images.refreshSmall());
	    btnRefresh.getElement().appendChild(imgRefresh.getElement());
	}
	
	//this method is called to kind of "Refresh" the map. Sometimes google maps has an error that only loads partially and the rest is left gray. This helps solving that issue
	//the idea is to force the map to refresh
	public void refreshMap(){
		map.triggerResize();
	}
	

	////////////////////////////////////////////////////
	// ADDING COMPONENTS TO THE MAP (called by presenter)
	//////////////////////////////////////////////////
	
	//BOUNDS
	
	public void setBoundaryPolygon(Double Lat_bttmLeft,Double Lng_bttmLeft,Double Lat_bttmRight,Double Lng_bttmRight,Double Lat_topRight,Double Lng_topRight,Double Lat_topLeft,Double Lng_topLeft){
		//create the four points
		LatLng bttmLeft = LatLng.create(Lat_bttmLeft, Lng_bttmLeft);
		LatLng bttmRight = LatLng.create(Lat_bttmRight, Lng_bttmRight);
		LatLng topRight = LatLng.create(Lat_topRight, Lng_topRight);
		LatLng topLeft = LatLng.create(Lat_topLeft, Lng_topLeft);
	    //create the path
	    MVCArray<LatLng> pathPolygon = MVCArray.create();
	    pathPolygon.push(topLeft);
	    pathPolygon.push(topRight);
	    pathPolygon.push(bttmRight);
	    pathPolygon.push(bttmLeft);
	    pathPolygon.push(topLeft);
	    //create Array of Array (This is because the setPath method of the Polygon object receives and Array of paths, and each path is an array)
	    MVCArray<MVCArray<LatLng>> pathPolygon2 = MVCArray.create();
	    pathPolygon2.push(pathPolygon);
	    //create options and polygon
	    PolygonOptions polygOpts = PolygonOptions.create();
	    polygOpts.setPaths(pathPolygon2);
	    polygOpts.setStrokeColor("#FA58F4");
	    polygOpts.setStrokeOpacity(0.6);
	    polygOpts.setStrokeWeight(2);
	    polygOpts.setFillColor("#58D3F7");
	    polygOpts.setFillOpacity(0.2);
	    boundsPolygon = Polygon.create(polygOpts);
	    boundsPolygon.setMap(map);
	    
	    boundsPolygon.setVisible(true);
	    btnBoundaryVisibility.setDown(true);
	    
	    //maybe replace polygon with polyline, because polygon interferes a lot with ease of clicking pipes and components on map
	}
	
	//JUNCTIONS
	
	public void addJunctionComponent(final int index,final String id,final Double lat,final Double lng){
		//create the marker
		LatLng markerLocation = LatLng.create(lat, lng);
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(markerLocation);
		markerOpts.setMap(map);		
		markerOpts.setIcon("images/markers/green.png");
		markerOpts.setTitle("Junction");
		Marker junctionMarker = Marker.create(markerOpts);
		//add the marker to the List
		junctionMarkers.add(junctionMarker);
		//add event
		junctionMarker.addClickListener(new Marker.ClickHandler() {
			@Override
			public void handle(MouseEvent event) {
				presenter.informComponentSelected("junction", index, id);		//inform presenter
			}
		});
		
		btnJunctionsVisibility.setDown(false);
		junctionMarker.setVisible(false);
	}
	
	//PIPES
	
	public void addPipeComponent(final int index,final String id, final ArrayList<Double> latsVertex,final ArrayList<Double> lngsVertex){
		
		//create array for polyline
	    MVCArray<LatLng> path = MVCArray.create();
	    //for each vertex create point and add it to path
	    for(int i=0;i<latsVertex.size();i++){
	    	LatLng vertex = LatLng.create(latsVertex.get(i), lngsVertex.get(i));
	    	path.push(vertex);
	    }
	    //create options and polyline
	    PolylineOptions polyOpts=PolylineOptions.create();
	    polyOpts.setStrokeColor("#642EFE");
	    polyOpts.setStrokeOpacity(1.0);
	    polyOpts.setStrokeWeight(2);
	    polyOpts.setPath(path);	
	    polyOpts.setMap(map);
	    Polyline pipePolyline = Polyline.create(polyOpts);
	    //add polyline to the list
	    pipePolylines.add(pipePolyline);
	    //add event	    
	    pipePolyline.addClickListener(new Polyline.ClickHandler() {
			@Override
			public void handle(MouseEvent event) {
				presenter.informComponentSelected("pipe", index, id);		//inform presenter
			}
		});
		btnPipesVisibility.setDown(true);
		pipePolyline.setVisible(true);
	    
	}
	
	//RESERVOIRS
	
	public void addReservoirComponent(final int index,final String id,final Double lat,final Double lng){
		//create the marker
		LatLng markerLocation = LatLng.create(lat, lng);
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(markerLocation);
		markerOpts.setMap(map);
		markerOpts.setIcon("images/markers/yellow.png");
		markerOpts.setTitle("Reservoir");
		Marker reservoirMarker = Marker.create(markerOpts);
		//add the marker to the List
		reservoirMarkers.add(reservoirMarker);
		//add event
		reservoirMarker.addClickListener(new Marker.ClickHandler() {
			@Override
			public void handle(MouseEvent event) {
				presenter.informComponentSelected("reservoir", index, id);		//inform presenter
			}
		});
		
		btnJunctionsVisibility.setDown(false);
		reservoirMarker.setVisible(false);
	}
	
	//VALVES
	
	public void addValveComponent(final int index,final String id,final Double lat,final Double lng){//this code has not yet been tested because there are no valves
		//create the marker
		LatLng markerLocation = LatLng.create(lat, lng);
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(markerLocation);
		markerOpts.setMap(map);
		markerOpts.setIcon("images/markers/black.png");
		markerOpts.setTitle("Valve");
		Marker valveMarker = Marker.create(markerOpts);
		//add the marker to the List
		valveMarkers.add(valveMarker);
		//add event
		valveMarker.addClickListener(new Marker.ClickHandler() {
			@Override
			public void handle(MouseEvent event) {
				presenter.informComponentSelected("valve", index, id);		//inform presenter
			}
		});
		
		btnValvesVisibility.setDown(false);
		valveMarker.setVisible(false);
	}
	
	
	/////////////////////////////////
	// HIGHLIGHT COMPONENT (called by presenter)
	/////////////////////////////////
	public void highlightComponent(String type,int index,String id){

		
		//if component is a Pipe we draw a polyline, else, we draw a marker. We get the position of marker or path of polyline using the ArrayLists and the index
		
		if(!type.equals("pipe")){
			
			highlightPolyline.setVisible(false);
			
			MarkerOptions markerOpts= MarkerOptions.create();
			//obtain the position of the highlight marker by copying it from the selected component, using the arrays of markers we have for each type of component
			if(type.equals("junction")){
				markerOpts.setPosition(junctionMarkers.get(index).getPosition());
			}
			else if(type.equals("reservoir")){
				markerOpts.setPosition(reservoirMarkers.get(index).getPosition());
			}
			else if(type.equals("valve")){
				markerOpts.setPosition(valveMarkers.get(index).getPosition());
			}
			markerOpts.setMap(map);
			//MarkerImage image = MarkerImage.create("images/markers/highlight.png", Size.create(15, 15), Point.create(0, 0), Point.create(7.5, 11));
			//markerOpts.setIcon(image);
			markerOpts.setIcon("images/markers/pinkFlag64.png");
			markerOpts.setAnimation(Animation.DROP);
			highlightMarker.setOptions(markerOpts);
			highlightMarker.setVisible(true);
			
		}
		else{
			
			highlightMarker.setVisible(false);
			MVCArray<LatLng> path = pipePolylines.get(index).getPath();
		    PolylineOptions polyOpts=PolylineOptions.create();
		    polyOpts.setStrokeColor("#FF4000");
		    polyOpts.setStrokeOpacity(1.0);
		    polyOpts.setStrokeWeight(5);
		    polyOpts.setZindex(10000000);	//so it is always on top
		    polyOpts.setPath(path);	
		    polyOpts.setMap(map);
		    highlightPolyline.setOptions(polyOpts);
		    highlightPolyline.setVisible(true);
		    
		    //also add the flag in the start point (position 0 of the path)
		    MarkerOptions markerOpts= MarkerOptions.create();
		    markerOpts.setPosition(pipePolylines.get(index).getPath().getAt(0));
		    markerOpts.setMap(map);
			markerOpts.setIcon("images/markers/pinkFlag64.png");
			markerOpts.setAnimation(Animation.DROP);
			highlightMarker.setOptions(markerOpts);
			highlightMarker.setVisible(true);
		}
		
		
		//if map doesn't contain the highlighted point, center it on the point
		if(!map.getBounds().contains(highlightMarker.getPosition())){
			map.setCenter(highlightMarker.getPosition());		//center map on marker
		}
	}
	
	/////////////////////////////////
	// SHOW RESULTS ON MAP
	/////////////////////////////////

	public void showResultsOnMap(ArrayList<ComponentLevel> results ){
		
		
		//iterate over each component in the list
		for(int i=0;i<results.size();i++){
			
			ComponentLevel component = results.get(i);
			String componentType=component.getComponentType();
			int componentIndex = component.getComponentIndex();
			int level = component.getLevel();
			
			if(componentType.equals("junction")){
				modifyJunction(componentIndex,level);
			}else if(componentType.equals("pipe")){
				modifyPipe(componentIndex,level);
			}else if(componentType.equals("reservoir")){
				modifyReservoir(componentIndex,level);
			}else if(componentType.equals("valve")){
				modifyValve(componentIndex,level);
			}
		}
		
	}
	
	//junction
	private void modifyJunction(int index,int level){
		
		Marker junction = junctionMarkers.get(index);
		
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(junction.getPosition());
		markerOpts.setMap(map);
		markerOpts.setTitle("Junction");
		
        switch (level) {
        case 1:  markerOpts.setIcon("images/markers/blue.png");
                 break;
        case 2:  markerOpts.setIcon("images/markers/cyan.png");
                 break;
        case 3:  markerOpts.setIcon("images/markers/green.png");
                 break;
        case 4:  markerOpts.setIcon("images/markers/yellow.png");
                 break;
        case 5:  markerOpts.setIcon("images/markers/red.png");
                 break;
        case 0:  markerOpts.setIcon("images/markers/green.png");			//no level - default color of junction
                 break;
        default: markerOpts.setIcon("images/markers/green.png");			//no level - default color of junction
                 break;
        }
        junction.setOptions(markerOpts);
		
	}
	
	//pipe
	private void modifyPipe(int index,int level){
		
		Polyline pipe= pipePolylines.get(index);
		
		PolylineOptions polyOpts=PolylineOptions.create();
		polyOpts.setStrokeOpacity(1.0);
		polyOpts.setStrokeWeight(2);
		polyOpts.setMap(map);
		polyOpts.setPath(pipe.getPath());	//copy the same path
		
        switch (level) {
            case 1:  polyOpts.setStrokeColor("#0000FF");
                     break;
            case 2:  polyOpts.setStrokeColor("#9CFEFF");
                     break;
            case 3:  polyOpts.setStrokeColor("#9FFF00");
                     break;
            case 4:  polyOpts.setStrokeColor("#FFFF00");
                     break;
            case 5:  polyOpts.setStrokeColor("#D31F00");
                     break;
            case 0:  polyOpts.setStrokeColor("#642EFE");			//no level - default color of pipe
                     break;
            default: polyOpts.setStrokeColor("#642EFE");			//no level - default color of pipe
                     break;
        }
	    pipe.setOptions(polyOpts);
		
	}
	
	//reservoir
	private void modifyReservoir(int index,int level){
		
		Marker reservoir = reservoirMarkers.get(index);
		
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(reservoir.getPosition());
		markerOpts.setMap(map);
		markerOpts.setTitle("Reservoir");
		
        switch (level) {
        case 1:  markerOpts.setIcon("images/markers/blue.png");
                 break;
        case 2:  markerOpts.setIcon("images/markers/cyan.png");
                 break;
        case 3:  markerOpts.setIcon("images/markers/green.png");
                 break;
        case 4:  markerOpts.setIcon("images/markers/yellow.png");
                 break;
        case 5:  markerOpts.setIcon("images/markers/red.png");
                 break;
        case 0:  markerOpts.setIcon("images/markers/yellow.png");			//no level - default color of reservoir
                 break;
        default: markerOpts.setIcon("images/markers/yellow.png");			//no level - default color of reservoir
                 break;
        }
        reservoir.setOptions(markerOpts);
		
	}
	
	
	//valve
	private void modifyValve(int index,int level){
		
		Marker valve = valveMarkers.get(index);
		
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(valve.getPosition());
		markerOpts.setMap(map);
		markerOpts.setTitle("Reservoir");
		
        switch (level) {
        case 1:  markerOpts.setIcon("images/markers/blue.png");
                 break;
        case 2:  markerOpts.setIcon("images/markers/cyan.png");
                 break;
        case 3:  markerOpts.setIcon("images/markers/green.png");
                 break;
        case 4:  markerOpts.setIcon("images/markers/yellow.png");
                 break;
        case 5:  markerOpts.setIcon("images/markers/red.png");
                 break;
        case 0:  markerOpts.setIcon("images/markers/black.png");			//no level - default color of valve
                 break;
        default: markerOpts.setIcon("images/markers/black.png");			//no level - default color of valve
                 break;
        }
        valve.setOptions(markerOpts);
		
	}
	
	//////////////////////////////////
	//   My Location functionality
	//////////////////////////////////
	
	public void showMyLocation(Double lat,Double lng){

		//create the marker
		LatLng markerLocation = LatLng.create(lat, lng);
		MarkerOptions markerOpts= MarkerOptions.create();
		markerOpts.setPosition(markerLocation);
		markerOpts.setMap(map);
		markerOpts.setIcon("images/markers/blueDrop64.png");
		markerOpts.setTitle("Junction");
		myLocationMarker = Marker.create(markerOpts);

		map.setCenter(markerLocation);
		
		//add event
		myLocationMarker.addClickListener(new Marker.ClickHandler() {
			@Override
			public void handle(MouseEvent event) {
				
			}
		});
		
	}
	
	
}
