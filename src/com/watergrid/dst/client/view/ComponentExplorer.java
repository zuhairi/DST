package com.watergrid.dst.client.view;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.DecoratorPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.watergrid.dst.client.presenter.ComponentExplorerPresenter;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ChangeEvent;

public class ComponentExplorer extends Composite{
	
	ComponentExplorerPresenter presenter;				//reference to the presenter to call it in case of events
	
	ListBox cmbJunctions;								//listboxes with components
	ListBox cmbPipes;
	ListBox cmbReservoirs;
	ListBox cmbValves;
	
	
	public ComponentExplorer(){
		initializeComponents();
	}
	
	//this method sets the presenter who will be called by events
	public void registerPresenter(ComponentExplorerPresenter presenter){
		this.presenter=presenter;
	}
	
	private void initializeComponents(){
		
		DecoratorPanel decoratorPanelListBoxes = new DecoratorPanel();
		decoratorPanelListBoxes.setWidth("100%");
		initWidget(decoratorPanelListBoxes);
		
		VerticalPanel verticalPanelTOP = new VerticalPanel();
		decoratorPanelListBoxes.setWidget(verticalPanelTOP);
		verticalPanelTOP.setWidth("100%");
		
		Label lblComponents = new Label("COMPONENTS");
		lblComponents.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelTOP.add(lblComponents);
		cmbJunctions = new ListBox();
		verticalPanelTOP.add(cmbJunctions);
		cmbJunctions.setWidth("100%");
		cmbPipes = new ListBox();
		verticalPanelTOP.add(cmbPipes);
		cmbPipes.setWidth("100%");
		cmbReservoirs = new ListBox();
		verticalPanelTOP.add(cmbReservoirs);
		cmbReservoirs.setWidth("100%");
		cmbValves = new ListBox();
		verticalPanelTOP.add(cmbValves);
		cmbValves.setWidth("100%");
		
		cmbJunctions.addItem("--JUNCTIONS--");
		cmbPipes.addItem("--PIPES--");
		cmbReservoirs.addItem("--RESERVOIRS--");
		cmbValves.addItem("--VALVES--");
		

		
		//listeners
		//they inform the presenter that the value has changed
		cmbJunctions.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if(cmbJunctions.getSelectedIndex()>0){						//Don't do anything if the selected one is the "--Select--" value
					//inform presenter
					presenter.informComponentSelected("junction", cmbJunctions.getSelectedIndex()-1, cmbJunctions.getItemText(cmbJunctions.getSelectedIndex())); //Subtract 1 so that it matches the index value of the components list (this is because of the "--select--" option)
				}
			}
		});
		
		cmbPipes.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if(cmbPipes.getSelectedIndex()>0){	
					presenter.informComponentSelected("pipe", cmbPipes.getSelectedIndex()-1, cmbPipes.getItemText(cmbPipes.getSelectedIndex())); //Subtract 1 so that it matches the index value of the components list (this is because of the "--select--" option)
				}
			}
		});
		
		cmbReservoirs.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if(cmbReservoirs.getSelectedIndex()>0){		
					presenter.informComponentSelected("reservoir", cmbReservoirs.getSelectedIndex()-1, cmbReservoirs.getItemText(cmbReservoirs.getSelectedIndex())); //Subtract 1 so that it matches the index value of the components list (this is because of the "--select--" option)
				}
			}
		});
		
		cmbValves.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				if(cmbValves.getSelectedIndex()>0){
					presenter.informComponentSelected("valve", cmbValves.getSelectedIndex()-1, cmbValves.getItemText(cmbValves.getSelectedIndex())); //Subtract 1 so that it matches the index value of the components list (this is because of the "--select--" option)
				}
			}
		});
	}//initializeComponents
	
	
	/////////////////////////////////
	// ADD ELEMENTS TO LISTS
	/////////////////////////////////
	
	// to add elements to the listBoxes (call be presenter by iterating over component list)
	public void addJunction(String id){
		cmbJunctions.addItem(id);
	}
	public void addPipe(String id){
		cmbPipes.addItem(id);
	}
	public void addReservoir(String id){
		cmbReservoirs.addItem(id);
	}
	public void addValve(String id){
		cmbValves.addItem(id);
	}
	
	
	/////////////////////////////////
	// HIGHLIGHT  A COMPONENT
	/////////////////////////////////
	
	public void highlightComponent(String type,int index,String id){
	
		if(type.equals("junction")){
			highlightJunction(index);
		}
		else if(type.equals("pipe")){
			highlightPipe(index);
		}
		else if(type.equals("reservoir")){
			highlightReservoir(index);
			
		}else if(type.equals("valve")){
			highlightValve(index);
		}
	}
	
	//this method highlights the component and resets the other list boxes of the other type of components
	private void highlightJunction(int index){
		cmbJunctions.setSelectedIndex(index+1);			//we add 1 because of the "--select--" option 
		cmbPipes.setSelectedIndex(0);
		cmbReservoirs.setSelectedIndex(0);
		cmbValves.setSelectedIndex(0);
	}
	private void highlightPipe(int index){
		cmbPipes.setSelectedIndex(index+1);				//we add 1 because of the "--select--" option
		cmbJunctions.setSelectedIndex(0);
		cmbReservoirs.setSelectedIndex(0);
		cmbValves.setSelectedIndex(0);
	}
	private void highlightReservoir(int index){
		cmbReservoirs.setSelectedIndex(index+1);		//we add 1 because of the "--select--" option
		cmbJunctions.setSelectedIndex(0);
		cmbPipes.setSelectedIndex(0);
		cmbValves.setSelectedIndex(0);
	}
	private void highlightValve(int index){
		cmbValves.setSelectedIndex(index+1);			//we add 1 because of the "--select--" option
		cmbJunctions.setSelectedIndex(0);
		cmbPipes.setSelectedIndex(0);
		cmbReservoirs.setSelectedIndex(0);
	}
	
}
