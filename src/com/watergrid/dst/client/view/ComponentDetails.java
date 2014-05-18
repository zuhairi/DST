//this View class is an addition to the Component Explorer

package com.watergrid.dst.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.watergrid.dst.client.presenter.ComponentDetailsPresenter;

public class ComponentDetails extends Composite{
	
	ComponentDetailsPresenter presenter;		//reference to the presenter to call it in case of events
			
	VerticalPanel verticalPanelDetails;			// where we add the different versions of the details view
	Button btnAddAction;
	
	ImageResources images = GWT.create(ImageResources.class);

	
	public ComponentDetails() {
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(ComponentDetailsPresenter presenter){
		this.presenter=presenter;
	}
	
	private void initializeComponents(){
		
		DecoratorPanel decoratorPanelDetails = new DecoratorPanel();
		decoratorPanelDetails = new DecoratorPanel();
		decoratorPanelDetails.setWidth("100%");
		initWidget(decoratorPanelDetails);
		
		verticalPanelDetails = new VerticalPanel();
		verticalPanelDetails.setSpacing(3);
		decoratorPanelDetails.setWidget(verticalPanelDetails);
		verticalPanelDetails.setWidth("100%");
		
		this.setVisible(false);		//starts hiding
		
		//Create the button all views will use (but dont add it yet)
		btnAddAction = new Button("", new ClickHandler() {
	        public void onClick(ClickEvent event) {
	        	presenter.showAddActionView();
	        }
	      });
		btnAddAction.setWidth("95%");
		Image img = new Image(images.jobSmall());
		Composite buttonPanel = new HorizontalPanelButton(img, "Add Action");		//this is a custom panel that includes an image and a title
		btnAddAction.getElement().appendChild(buttonPanel.getElement());


	}
	
	
	/////////////////////////////
	// Methods for showing DETAIL view depending on type of component
	/////////////////////////////
	
	
	//junction details
	public void showJunctionDetails(int index,String id,Double lat,Double lng,float elevation){
	
		verticalPanelDetails.clear();
		
		Label lblComponentData = new Label("JUNCTION DETAILS");
		lblComponentData.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelDetails.add(lblComponentData);
		verticalPanelDetails.add(new DataLabel("ID", id));
		verticalPanelDetails.add(new DataLabel("Latitude", NumberFormat.getFormat("0.0000").format(lat)));
		verticalPanelDetails.add(new DataLabel("Longitude", NumberFormat.getFormat("0.0000").format(lng)));
		verticalPanelDetails.add(new DataLabel("Elevation", String.valueOf(elevation)));
		verticalPanelDetails.add(btnAddAction);
		
		
	}
	
	//pipe details
	public void showPipeDetails(int index,String id,Double length,Double diameter,String status,String startNodeId,String endNodeId){
		
		verticalPanelDetails.clear();
		
		Label lblComponentData = new Label("PIPE DETAILS");
		lblComponentData.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelDetails.add(lblComponentData);
		
		verticalPanelDetails.add(new DataLabel("ID", id));
		verticalPanelDetails.add(new DataLabel("Length", NumberFormat.getFormat("0.0000").format(length)));
		verticalPanelDetails.add(new DataLabel("Diameter", NumberFormat.getFormat("0.0000").format(diameter)));
		verticalPanelDetails.add(new DataLabel("Status", status));
		verticalPanelDetails.add(new DataLabel("StartNode", startNodeId));
		verticalPanelDetails.add(new DataLabel("EndNode", endNodeId));
		
		verticalPanelDetails.add(btnAddAction);

	}
	
	//reservoir details
	public void showReservoirDetails(int index,String id,Double lat,Double lng,float head){
		
		verticalPanelDetails.clear();
		
		Label lblComponentData = new Label("RESERVOIR DETAILS");
		lblComponentData.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelDetails.add(lblComponentData);
		
		verticalPanelDetails.add(new DataLabel("ID", id));
		verticalPanelDetails.add(new DataLabel("Latitude", NumberFormat.getFormat("0.0000").format(lat)));
		verticalPanelDetails.add(new DataLabel("Longitude", NumberFormat.getFormat("0.0000").format(lng)));
		verticalPanelDetails.add(new DataLabel("Head", String.valueOf(head)));

		verticalPanelDetails.add(btnAddAction);

	}
	
	//valve details
	public void showValveDetails(int index,String id,Double lat,Double lng){
		
		verticalPanelDetails.clear();
		
		Label lblComponentData = new Label("VALVE DETAILS");
		lblComponentData.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelDetails.add(lblComponentData);
		
		verticalPanelDetails.add(new DataLabel("ID", id));
		verticalPanelDetails.add(new DataLabel("Latitude", NumberFormat.getFormat("0.0000").format(lat)));
		verticalPanelDetails.add(new DataLabel("Longitude", NumberFormat.getFormat("0.0000").format(lng)));

		verticalPanelDetails.add(btnAddAction);

	}

}
