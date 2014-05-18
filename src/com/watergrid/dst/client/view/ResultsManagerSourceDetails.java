package com.watergrid.dst.client.view;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.ColumnSortEvent.ListHandler;
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
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.visualization.client.AbstractDataTable.ColumnType;
import com.google.gwt.visualization.client.DataTable;
import com.google.gwt.visualization.client.VisualizationUtils;
import com.google.gwt.visualization.client.visualizations.corechart.AxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.CoreChart;
import com.google.gwt.visualization.client.visualizations.corechart.HorizontalAxisOptions;
import com.google.gwt.visualization.client.visualizations.corechart.LineChart;
import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.watergrid.dst.client.dataobjects.NodeAgeResult;
import com.watergrid.dst.client.dataobjects.NodeSourceTraceResult;

public class ResultsManagerSourceDetails extends Composite{
	
	ResultsManager parentView;
	VerticalPanel verticalPanelGlobal;
	HorizontalPanel optionButtonsPanel;
	
	//for nodes data grid
	Label lblNodeTitle;
	DataGrid<NodeSourceTraceResult> nodeDataGrid;
	SimplePager nodePager;
	ListDataProvider<NodeSourceTraceResult> nodeDataProvider;
	SingleSelectionModel<NodeSourceTraceResult> nodeSelectionModel;
	List<NodeSourceTraceResult> nodeList;
	
	//for graph
	VerticalPanel graphPanel;
	LineChart graph;
	ListBox cmbVariable;
	
	ImageResources images = GWT.create(ImageResources.class);
	
	public ResultsManagerSourceDetails(ResultsManager parentView){
		
		this.parentView=parentView;
		initializeComponents();
		activateGridView();
	}
	
	
	// ////////////////////
	// VIEW COMPONENTS
	// ////////////////////

	private void initializeComponents() {

		verticalPanelGlobal = new VerticalPanel();
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		verticalPanelGlobal.setSpacing(3);
		verticalPanelGlobal.setWidth("100%");
		verticalPanelGlobal.setHeight("100%");
		initWidget(verticalPanelGlobal);

		// Nodes Data Grid

		lblNodeTitle = new Label("NODE - Source Trace Percentage (%) ");
		verticalPanelGlobal.add(lblNodeTitle);

		createNodesDataGrid();
		
		//Buttons

		optionButtonsPanel = new HorizontalPanel();
		verticalPanelGlobal.add(optionButtonsPanel);
		verticalPanelGlobal.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);

		// Back button
		Button btnBack = new Button("");
		btnBack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				parentView.activateSourceTraceSummaryMode();
			}
		});
		Image img2 = new Image(images.backSmall());
		VerticalPanelButton buttonPanel2 = new VerticalPanelButton(img2,"Back to summary"); // this is a custom panel that includes an image and a title
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
		VerticalPanelButton buttonPanel3 = new VerticalPanelButton(img3,"View Component on map");
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
		
		///////////////////////
		//  GRAPH VIEW
		///////////////////////
		
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
		cmbVariable.addItem("--select variable--");
		cmbVariable.addItem("sourceTrace");
		
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

	private void createNodesDataGrid() {

		nodeDataGrid = new DataGrid<NodeSourceTraceResult>();
		nodeDataGrid.setWidth("100%");
		nodeDataGrid.setHeight("24em");
		verticalPanelGlobal.add(nodeDataGrid);
		// options
		nodeDataGrid.setAutoHeaderRefreshDisabled(true);
		SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
		nodePager = new SimplePager(TextLocation.CENTER, pagerResources, false,0, true);
		//nodePager.setDisplay(nodeDataGrid);
		//verticalPanelGlobal.add(nodePager);
		nodeDataGrid.setPageSize(10000);

		// Create Name column.
		TextColumn<NodeSourceTraceResult> idColumn = new TextColumn<NodeSourceTraceResult>() {
			@Override
			public String getValue(NodeSourceTraceResult nodeResult) {
				return nodeResult.getNodeId();
			}
		};
		// Create MinTracePct column.
		TextColumn<NodeSourceTraceResult> minTracePct = new TextColumn<NodeSourceTraceResult>() {
			@Override
			public String getValue(NodeSourceTraceResult nodeResult) {
				return NumberFormat.getFormat("0.00").format(nodeResult.getMinSourceTrace());
			}
		};
		// Create MaxTracePct column.
		TextColumn<NodeSourceTraceResult> maxTracePct = new TextColumn<NodeSourceTraceResult>() {
			@Override
			public String getValue(NodeSourceTraceResult nodeResult) {
				return NumberFormat.getFormat("0.00").format(nodeResult.getMaxSourceTrace());
			}
		};
		// Create AvgTracePct column.
		TextColumn<NodeSourceTraceResult> avgTracePct = new TextColumn<NodeSourceTraceResult>() {
			@Override
			public String getValue(NodeSourceTraceResult nodeResult) {
				return NumberFormat.getFormat("0.00").format(nodeResult.getAvgSourceTrace());
			}
		};
		

		// Add the columns.
		nodeDataGrid.addColumn(idColumn, "ID");
		nodeDataGrid.addColumn(minTracePct, "min");
		nodeDataGrid.addColumn(maxTracePct, "max");
		nodeDataGrid.addColumn(avgTracePct, "avg");

		// Create a data provider.
		nodeDataProvider = new ListDataProvider<NodeSourceTraceResult>();
		

		// Connect the list to the data provider.
		nodeDataProvider.addDataDisplay(nodeDataGrid);
		nodeList=nodeDataProvider.getList();

		// selection Model
		nodeSelectionModel = new SingleSelectionModel<NodeSourceTraceResult>();
		nodeDataGrid.setSelectionModel(nodeSelectionModel);
		
		//Sorters for the dataGrid
		
		idColumn.setSortable(true);
		minTracePct.setSortable(true);
		maxTracePct.setSortable(true);
		avgTracePct.setSortable(true);
		
		ListHandler<NodeSourceTraceResult> columnSortHandler = new ListHandler<NodeSourceTraceResult>(nodeDataProvider.getList());
		nodeDataGrid.addColumnSortHandler(columnSortHandler);
		
		columnSortHandler.setComparator(idColumn, ComparatorFactory.getComp_NodeSource_Id());
		columnSortHandler.setComparator(minTracePct, ComparatorFactory.getComp_NodeSource_MinSourcePct());
		columnSortHandler.setComparator(maxTracePct, ComparatorFactory.getComp_NodeSource_MaxSourcePct());
		columnSortHandler.setComparator(avgTracePct, ComparatorFactory.getComp_NodeSource_AvgSourcePct());

	}
	
	///////////////
	// METHODS related to events of this view
	////////////////
	
	
	private void viewOnMapButtonPressed(){
		
		//get the selected ID and call the parent view
		NodeSourceTraceResult selectedNode = nodeSelectionModel.getSelectedObject();
		if(selectedNode != null){
			parentView.highlightComponent(selectedNode.getNodeId());
		}else{
			Window.alert("Please select a component!");
		}
	}
	
	private void viewGraphButtonPressed(){
		
		NodeSourceTraceResult selectedNode = nodeSelectionModel.getSelectedObject();
		if(selectedNode != null){
			activateGraphView();
		}else{
			Window.alert("Please select a component!");
		}
	}
	
	private void selectedVariableChanged(){
		
		if(cmbVariable.getSelectedIndex()==0){
			graph.draw(createNullData(),createOptions("no selected variable","",""));
		}else{
			String variable=cmbVariable.getItemText(cmbVariable.getSelectedIndex());
			String componentId=nodeSelectionModel.getSelectedObject().getNodeId();
			loadGraph(parentView.getComponentCompleteData(componentId,variable),variable,componentId);
		}
		
	}
	
	private void activateGridView(){
		lblNodeTitle.setVisible(true);
		nodeDataGrid.setVisible(true);
		optionButtonsPanel.setVisible(true);
		graphPanel.setVisible(false);
	}
	
	private void activateGraphView(){
		lblNodeTitle.setVisible(false);
		nodeDataGrid.setVisible(false);
		optionButtonsPanel.setVisible(false);
		graphPanel.setVisible(true);
		cmbVariable.setSelectedIndex(0);
		graph.draw(createNullData(), createOptions("no selected variable","",""));
	}
	
	
	
	///////////////
	// METHODSto load the dataGrids
	////////////////
	
	//for updating the DataGrid
	public void updateNodesData(List<NodeSourceTraceResult> data){
		
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

	
	public void redrawDataGrids(){
		nodeDataGrid.redraw();
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
		if(variable.equals("sourceTrace")){
			vTitle="Source Trace Pct (%)";
		}
		graph.draw(dataTable, createOptions(componentId+"'s behaviour: ",hTitle,vTitle));
	
	}

}

