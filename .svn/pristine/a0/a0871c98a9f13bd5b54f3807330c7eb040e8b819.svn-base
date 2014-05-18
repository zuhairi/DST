package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.SimplePager.TextLocation;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.AreaChart;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.ColumnChart;
import com.google.gwt.visualization.client.visualizations.corechart.ComboChart;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;
import com.google.gwt.visualization.client.visualizations.corechart.HorizontalAxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.watergrid.dst.client.dataobjects.LinkHydraulicResult;
import com.watergrid.dst.client.dataobjects.NodeHydraulicResult;

public class ResultsManagerHydraulicDetails extends Composite{
	
	ResultsManager parentView;
	VerticalPanel verticalPanelGlobal;
	HorizontalPanel optionButtonsPanel;
	
	//for nodes data grid
	Label lblNodeTitle;
	DataGrid<NodeHydraulicResult> nodeDataGrid;
	SimplePager nodePager;
	ListDataProvider<NodeHydraulicResult> nodeDataProvider;
	SingleSelectionModel<NodeHydraulicResult> nodeSelectionModel;
	List<NodeHydraulicResult> nodeList;
	
	//for links dataGrid
	Label lblLinkTitle;
	DataGrid<LinkHydraulicResult> linkDataGrid;
	SimplePager linkPager;
	ListDataProvider<LinkHydraulicResult> linkDataProvider;
	SingleSelectionModel<LinkHydraulicResult> linkSelectionModel;
	List<LinkHydraulicResult> linkList;
	
	//for graph
	VerticalPanel graphPanel;
	LineChart graph;
	ListBox cmbVariable;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ResultsManagerHydraulicDetails(ResultsManager parentView){
		this.parentView=parentView;
		initializeComponents();
		activateGridView();
	}
	
	//////////////////////
	// VIEW COMPONENTS
	//////////////////////
	
	private void initializeComponents(){
	
		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setSpacing(3);
		verticalPanelGlobal.setWidth("100%");
		verticalPanelGlobal.setHeight("100%");
		initWidget(verticalPanelGlobal);
		
		// Nodes Data Grid
		lblNodeTitle = new Label("NODE - Pressure (m) ,  Head (m)");
		verticalPanelGlobal.add(lblNodeTitle);
		createNodesDataGrid();
		
		// Links DataGrid
		lblLinkTitle = new Label("LINK - Flow (litres/s) ,  Velocity (m/s) , HeadLoss (m/Km)");
		verticalPanelGlobal.add(lblLinkTitle);
		createLinksDataGrid();
		
		//BUTTONS

		optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		// Back button
		Button btnBack = new Button("");
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.activateHydraulicSummaryMode();
			}
		});
		Image img2 = new Image(images.backSmall());
		VerticalPanelButton buttonPanel2 = new VerticalPanelButton(img2, "Back to summary");		//this is a custom panel that includes an image and a title
		btnBack.getElement().appendChild(buttonPanel2.getElement());
		optionButtonsPanel.add(btnBack);
		
		// View Component on MAP
		Button btnViewNodeMap = new Button("");
		btnViewNodeMap.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewOnMapButtonPressed();
			}
		});
		Image img3 = new Image(images.mapSmall());
		VerticalPanelButton buttonPanel3 = new VerticalPanelButton(img3, "View Component on map");		//this is a custom panel that includes an image and a title
		btnViewNodeMap.getElement().appendChild(buttonPanel3.getElement());
		optionButtonsPanel.add(btnViewNodeMap);
		
		// View Component's graph
		Button btnViewGraph = new Button("");
		btnViewGraph.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				viewGraphButtonPressed();
			}
		});
		Image img4 = new Image(images.chartSmall());
		VerticalPanelButton buttonPanel4 = new VerticalPanelButton(img4,"View Component's graph");
		btnViewGraph.getElement().appendChild(buttonPanel4.getElement());
		optionButtonsPanel.add(btnViewGraph);
		
		
		//  GRAPH VIEW
		graphPanel = new VerticalPanel();
		graphPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.add(graphPanel);
		
		cmbVariable=new ListBox();
		cmbVariable.addChangeHandler(new ChangeHandler() {
			public void onChange(ChangeEvent event) {
				selectedVariableChanged();
			}
		});
		graphPanel.add(cmbVariable);
		
		
		 Runnable onLoadCallback = new Runnable() {
		      public void run() {
		    	  
			    	graph = new LineChart(createNullData(),createOptions("no selected variable","",""));
			    	graphPanel.add(graph);
			    	
					// Back from graph button
			  		Button btnBackFromGraph = new Button("");
			  		btnBackFromGraph.addClickHandler(new ClickHandler() {
			  			public void onClick(ClickEvent event) {
			  				activateGridView();
			  			}
			  		});
			  		Image img6 = new Image(images.backSmall());
			  		HorizontalPanelButton buttonPanel6 = new HorizontalPanelButton(img6,"Back to details");
			  		btnBackFromGraph.getElement().appendChild(buttonPanel6.getElement());
			  		graphPanel.add(btnBackFromGraph);
		      }
		    };
		 
		 VisualizationUtils.loadVisualizationApi(onLoadCallback, CoreChart.PACKAGE);
		
	}
	
	//////////////////////
	// NODES DATAGRID
	//////////////////////
	
	private void createNodesDataGrid(){
		
		nodeDataGrid = new DataGrid<NodeHydraulicResult>();
	    nodeDataGrid.setWidth("100%");
	    nodeDataGrid.setHeight("12em");
	    verticalPanelGlobal.add(nodeDataGrid);
	    //options
	    nodeDataGrid.setAutoHeaderRefreshDisabled(true);
	    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    nodePager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    //nodePager.setDisplay(nodeDataGrid);
	    //verticalPanelGlobal.add(nodePager);
	    nodeDataGrid.setPageSize(10000);
	    
	    // Create Name column.
	    TextColumn<NodeHydraulicResult> idColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	        return nodeResult.getNodeId();
	      }
	    };
	    // Create MinPressure column.
	    TextColumn<NodeHydraulicResult> minPressureColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	    	return NumberFormat.getFormat("0.00").format(nodeResult.getMinPressure());
	      }
	    };
	    // Create MaxPressure column.
	    TextColumn<NodeHydraulicResult> maxPressureColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	    	return NumberFormat.getFormat("0.00").format(nodeResult.getMaxPressure());
	      }
	    };
	    // Create AvgPressure column.
	    TextColumn<NodeHydraulicResult> avgPressureColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	    	  return NumberFormat.getFormat("0.00").format(nodeResult.getAvgPressure());
	      }
	    };
	    // Create MinHead column.
	    TextColumn<NodeHydraulicResult> minHeadColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	        return NumberFormat.getFormat("0.00").format(nodeResult.getMinHead());
	      }
	    };
	    // Create MaxHead column.
	    TextColumn<NodeHydraulicResult> maxHeadColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	        return NumberFormat.getFormat("0.00").format(nodeResult.getMaxHead());
	      }
	    };
	    // Create AvgHead column.
	    TextColumn<NodeHydraulicResult> avgHeadColumn = new TextColumn<NodeHydraulicResult>() {
	      @Override
	      public String getValue(NodeHydraulicResult nodeResult) {
	        return NumberFormat.getFormat("0.00").format(nodeResult.getAvgHead());
	      }
	    };
	    
	    // Add the columns.
	    nodeDataGrid.addColumn(idColumn, "ID");
	    nodeDataGrid.addColumn(minPressureColumn, "min");
	    nodeDataGrid.addColumn(maxPressureColumn, "max");
	    nodeDataGrid.addColumn(avgPressureColumn, "avg");
	    nodeDataGrid.addColumn(minHeadColumn, "min");
	    nodeDataGrid.addColumn(maxHeadColumn, "max");
	    nodeDataGrid.addColumn(avgHeadColumn, "avg");
	    
	    // Create a data provider.
	    nodeDataProvider = new ListDataProvider<NodeHydraulicResult>();

	    // Connect the list to the data provider.
	    nodeDataProvider.addDataDisplay(nodeDataGrid);
	    nodeList=nodeDataProvider.getList();
	    
		// selection Model
		nodeSelectionModel = new SingleSelectionModel<NodeHydraulicResult>();
		nodeDataGrid.setSelectionModel(nodeSelectionModel);	
		
		// onSelectionChange event
		nodeSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						NodeHydraulicResult selected = nodeSelectionModel.getSelectedObject();
						if (selected != null) {
							linkSelectionModel.clear();		//clear the other grid
						}
					}
				});
	    
		
		//Sorters for the dataGrid
		
		idColumn.setSortable(true);
		minPressureColumn.setSortable(true);
		maxPressureColumn.setSortable(true);
		avgPressureColumn.setSortable(true);
		minHeadColumn.setSortable(true);
		maxHeadColumn.setSortable(true);
		avgHeadColumn.setSortable(true);
		
		ListHandler<NodeHydraulicResult> columnSortHandler = new ListHandler<NodeHydraulicResult>(nodeDataProvider.getList());
		nodeDataGrid.addColumnSortHandler(columnSortHandler);
		
		columnSortHandler.setComparator(idColumn, ComparatorFactory.getComp_NodeHydraulic_Id());
		columnSortHandler.setComparator(minPressureColumn, ComparatorFactory.getComp_NodeHydraulic_MinPressure());
		columnSortHandler.setComparator(maxPressureColumn, ComparatorFactory.getComp_NodeHydraulic_MaxPressure());
		columnSortHandler.setComparator(avgPressureColumn, ComparatorFactory.getComp_NodeHydraulic_AvgPressure());
		columnSortHandler.setComparator(minHeadColumn, ComparatorFactory.getComp_NodeHydraulic_MinHead());
		columnSortHandler.setComparator(maxHeadColumn, ComparatorFactory.getComp_NodeHydraulic_MaxHead());
		columnSortHandler.setComparator(avgHeadColumn, ComparatorFactory.getComp_NodeHydraulic_AvgHead());

		
	}
	
	//////////////////////
	// LINKS DATAGRID
	//////////////////////
	
	private void createLinksDataGrid(){
		
		linkDataGrid = new DataGrid<LinkHydraulicResult>();
	    linkDataGrid.setWidth("100%");
	    linkDataGrid.setHeight("12em");
	    verticalPanelGlobal.add(linkDataGrid);
	    
	    //options
	    linkDataGrid.setAutoHeaderRefreshDisabled(true);
	    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
	    linkPager = new SimplePager(TextLocation.CENTER, pagerResources, false, 0, true);
	    //linkPager.setDisplay(linkDataGrid);
	    //verticalPanelGlobal.add(linkPager);
	    linkDataGrid.setPageSize(10000);
	    
	    // Create Name column.
	    TextColumn<LinkHydraulicResult> idColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return linkResult.getLinkId();
	      }
	    };
	    
	    // Create min Flow column.
	    TextColumn<LinkHydraulicResult> minFlowColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getMinFlow());
	      }
	    };
	    // Create max Flow column.
	    TextColumn<LinkHydraulicResult> maxFlowColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getMaxFlow());
	      }
	    };
	    // Create avg Flow column.
	    TextColumn<LinkHydraulicResult> avgFlowColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getAvgFlow());
	      }
	    };
	    
	    // Create min Vel column.
	    TextColumn<LinkHydraulicResult> minVelColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getMinVelocity());
	      }
	    };
	    // Create max Vel column.
	    TextColumn<LinkHydraulicResult> maxVelColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getMaxVelocity());
	      }
	    };
	    // Create avg Vel column.
	    TextColumn<LinkHydraulicResult> avgVelColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getAvgVelocity());
	      }
	    };
	    
	    // Create min Headloss column.
	    TextColumn<LinkHydraulicResult> minHeadlossColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getMinHeadloss());
	      }
	    };
	    // Create max Headloss column.
	    TextColumn<LinkHydraulicResult> maxHeadlossColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getMaxHeadloss());
	      }
	    };
	    // Create avg Headloss column.
	    TextColumn<LinkHydraulicResult> avgHeadlossColumn = new TextColumn<LinkHydraulicResult>() {
	      @Override
	      public String getValue(LinkHydraulicResult linkResult) {
	        return NumberFormat.getFormat("0.00").format(linkResult.getAvgHeadloss());
	      }
	    };
	    
	    // Add the columns.
	    linkDataGrid.addColumn(idColumn, "ID");
	    linkDataGrid.addColumn(minFlowColumn, "min");
	    linkDataGrid.addColumn(maxFlowColumn, "max");
	    linkDataGrid.addColumn(avgFlowColumn, "avg");
	    linkDataGrid.addColumn(minVelColumn, "min");
	    linkDataGrid.addColumn(maxVelColumn, "max");
	    linkDataGrid.addColumn(avgVelColumn, "avg");
	    linkDataGrid.addColumn(minHeadlossColumn, "min");
	    linkDataGrid.addColumn(maxHeadlossColumn, "max");
	    linkDataGrid.addColumn(avgHeadlossColumn, "avg");
	    
	    // Create a data provider.
	    linkDataProvider = new ListDataProvider<LinkHydraulicResult>();

	    // Connect the list to the data provider.
	    linkDataProvider.addDataDisplay(linkDataGrid);
	    linkList=linkDataProvider.getList();
	    
		// selection Model
		linkSelectionModel = new SingleSelectionModel<LinkHydraulicResult>();
		linkDataGrid.setSelectionModel(linkSelectionModel);	
		
		// onSelectionChange event 
		linkSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
					public void onSelectionChange(SelectionChangeEvent event) {
						LinkHydraulicResult selected = linkSelectionModel.getSelectedObject();
						if (selected != null) {
							nodeSelectionModel.clear();		//clear the other grid
						}
					}
				});
		
		
		//Sorters for the dataGrid
		idColumn.setSortable(true);
		minFlowColumn.setSortable(true);
		maxFlowColumn.setSortable(true);
		avgFlowColumn.setSortable(true);
		minVelColumn.setSortable(true);
		maxVelColumn.setSortable(true);
		avgVelColumn.setSortable(true);
		minHeadlossColumn.setSortable(true);
		maxHeadlossColumn.setSortable(true);
		avgHeadlossColumn.setSortable(true);
		
		ListHandler<LinkHydraulicResult> columnSortHandler = new ListHandler<LinkHydraulicResult>(linkDataProvider.getList());
		linkDataGrid.addColumnSortHandler(columnSortHandler);
		
		columnSortHandler.setComparator(idColumn, ComparatorFactory.getComp_LinkHydraulic_Id());
		columnSortHandler.setComparator(minFlowColumn, ComparatorFactory.getComp_LinkHydraulic_MinFlow());
		columnSortHandler.setComparator(maxFlowColumn, ComparatorFactory.getComp_LinkHydraulic_MaxFlow());
		columnSortHandler.setComparator(avgFlowColumn, ComparatorFactory.getComp_LinkHydraulic_AvgFlow());
		columnSortHandler.setComparator(minVelColumn, ComparatorFactory.getComp_LinkHydraulic_MinVelocity());
		columnSortHandler.setComparator(maxVelColumn, ComparatorFactory.getComp_LinkHydraulic_MaxVelocity());
		columnSortHandler.setComparator(avgVelColumn, ComparatorFactory.getComp_LinkHydraulic_AvgVelocity());
		columnSortHandler.setComparator(minHeadlossColumn, ComparatorFactory.getComp_LinkHydraulic_MinHeadloss());
		columnSortHandler.setComparator(maxHeadlossColumn, ComparatorFactory.getComp_LinkHydraulic_MaxHeadloss());
		columnSortHandler.setComparator(avgHeadlossColumn, ComparatorFactory.getComp_LinkHydraulic_AvgHeadloss());
	}
	
	
	
	///////////////
	// METHODS related to events of this view
	////////////////
	
	
	private void viewOnMapButtonPressed(){
		
		//get the selected ID and call the parent view
		LinkHydraulicResult selectedLink = linkSelectionModel.getSelectedObject();
		NodeHydraulicResult selectedNode = nodeSelectionModel.getSelectedObject();
		if (selectedLink != null) {
			parentView.highlightComponent(selectedLink.getLinkId());
		}else if(selectedNode != null){
			parentView.highlightComponent(selectedNode.getNodeId());
		}else{
			Window.alert("Please select a component!");
		}
	}
	
	private void viewGraphButtonPressed(){
		LinkHydraulicResult selectedLink = linkSelectionModel.getSelectedObject();
		NodeHydraulicResult selectedNode = nodeSelectionModel.getSelectedObject();
		
		if(selectedNode != null){
			activateGraphViewNODE();
		}else if(selectedLink != null){
			activateGraphViewLINK();
		}
		else{
			Window.alert("Please select a component!");
		}
	}
	
	//when user changes the selected variable update the graph or clear it
	private void selectedVariableChanged(){
		
		if(cmbVariable.getSelectedIndex()==0){
			graph.draw(createNullData(),createOptions("no selected variable","",""));
		}else{
			
			LinkHydraulicResult selectedLink = linkSelectionModel.getSelectedObject();
			NodeHydraulicResult selectedNode = nodeSelectionModel.getSelectedObject();
			String componentId="";
			
			if(selectedNode != null){
				componentId=selectedNode.getNodeId();
			}else if(selectedLink != null){
				componentId=selectedLink.getLinkId();
			}
			String variable=cmbVariable.getItemText(cmbVariable.getSelectedIndex());
			loadGraph(parentView.getComponentCompleteData(componentId,variable),variable,componentId);
		}
		
	}
	
	private void activateGridView(){
		lblNodeTitle.setVisible(true);
		lblLinkTitle.setVisible(true);
		nodeDataGrid.setVisible(true);
		linkDataGrid.setVisible(true);
		optionButtonsPanel.setVisible(true);
		graphPanel.setVisible(false);
	}
	
	private void activateGraphViewNODE(){
		lblNodeTitle.setVisible(false);
		lblLinkTitle.setVisible(false);
		nodeDataGrid.setVisible(false);
		linkDataGrid.setVisible(false);
		optionButtonsPanel.setVisible(false);
		graphPanel.setVisible(true);
		cmbVariable.clear();
		cmbVariable.addItem("--select variable--");
		cmbVariable.addItem("pressure");
		cmbVariable.addItem("head");
		cmbVariable.setSelectedIndex(0);
		graph.draw(createNullData(), createOptions("no selected variable","",""));
	}
	
	private void activateGraphViewLINK(){
		lblNodeTitle.setVisible(false);
		lblLinkTitle.setVisible(false);
		nodeDataGrid.setVisible(false);
		linkDataGrid.setVisible(false);
		optionButtonsPanel.setVisible(false);
		graphPanel.setVisible(true);
		cmbVariable.clear();
		cmbVariable.addItem("--select variable--");
		cmbVariable.addItem("flow");
		cmbVariable.addItem("velocity");
		cmbVariable.addItem("headLoss");
		cmbVariable.setSelectedIndex(0);
		graph.draw(createNullData(), createOptions("no selected variable","",""));
	}
	
	
	
				///////////////
				// METHODSto load the dataGrids
				////////////////
	
	//for updating the DataGrid
	public void updateNodesData(List<NodeHydraulicResult> data){
		
		nodeSelectionModel.clear();

		nodeDataProvider.refresh();
		nodeDataProvider.flush();
		nodeDataGrid.redraw();
		
		nodeList.clear();						//done this way,because with the other way we did it (like in scenario explorer) the sorter didn't work
		for (int i =0;i<data.size();i++){
			nodeList.add(data.get(i));
		}
		nodeDataProvider.refresh();
		nodeDataProvider.flush();
		nodeDataGrid.redraw();
		nodeSelectionModel.clear();
		nodeDataGrid.onResize();
	}
	
	//for updating the DataGrid
	public void updateLinksData(List<LinkHydraulicResult> data){
		
		linkSelectionModel.clear();
		
		linkList.clear();
		for (int i =0;i<data.size();i++){
			linkList.add(data.get(i));
		}
		
		linkDataProvider.refresh();
		linkDataProvider.flush();
		linkDataGrid.redraw();
		linkSelectionModel.clear();
		linkDataGrid.onResize();
	}
	
	public void redrawDataGrids(){
		nodeDataGrid.redraw();
		linkDataGrid.redraw();
	}
	
	
			//////////////////////
			//  FOR GRAPH
			//////////////////////
	
	private Options createOptions(String title,String hTitle,String vTitle){
		
		Options options = Options.create();
		
		Double areaWidth=Window.getClientWidth()*0.60;		//the width of this area is 65% of the total browser's width
		int chartWidth=areaWidth.intValue();
		Double areaHeight=Window.getClientHeight()*0.50;
		int chartHeight=areaHeight.intValue();
		
	    options.setHeight(chartHeight);
	    options.setTitle(title);
	    options.setWidth(chartWidth);
	    options.setInterpolateNulls(true);
	    options.setBackgroundColor("#FAFAFA");
	    
	    HorizontalAxisOptions hOptions = HorizontalAxisOptions.create();
	    hOptions.setTitle(hTitle);
	    options.setHAxisOptions(hOptions);
	    
	    AxisOptions vOptions = AxisOptions.create();
	    vOptions.setTitle(vTitle);
	    options.setVAxisOptions(vOptions);
	    
		return options;
	}
	
	private DataTable createNullData(){
		
		DataTable dataTable =  DataTable.create();
		dataTable.addColumn(ColumnType.STRING, "Time Step");
		dataTable.addColumn(ColumnType.NUMBER, "No Variable Selected");
		dataTable.addRows(1);
		dataTable.setValue(0, 0, "0");
		dataTable.setValue(0, 1, 0);
		
		return dataTable;
	}
	
	private void loadGraph(ArrayList<Float> data,String variable,String componentId){
		
		DataTable dataTable =  DataTable.create();
		//dataTable.addColumn(ColumnType.STRING, "Time Step");
		dataTable.addColumn(ColumnType.NUMBER, "Time Step");
		dataTable.addColumn(ColumnType.NUMBER, variable);
		
		dataTable.addRows(data.size());
		
		for(int i=0;i<data.size();i++){
			//dataTable.setValue(i, 0, String.valueOf(i));
			dataTable.setValue(i, 0, (i));
			dataTable.setValue(i, 1, data.get(i));
		}
		
		String hTitle="Time Step";
		String vTitle="";
		if(variable.equals("pressure")){
			vTitle="Pressure (m)";
		}else if(variable.equals("head")){
			vTitle="Head (m)";
		}else if(variable.equals("flow")){
			vTitle="Flow (litres/s)";
		}else if(variable.equals("velocity")){
			vTitle="Velocity (m/s)";
		}else if(variable.equals("headLoss")){
			vTitle="Head Loss (m/Km)";
		}
		
		graph.draw(dataTable, createOptions(componentId+"'s behaviour: ",hTitle,vTitle));
		
	}

}
