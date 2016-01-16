package application;

import java.util.*;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.control.*;

public class Main extends Application{
	Stage window,window2;
	GridPane theGrid;
	Scene scene1,scene2,scene3,scene4;
	BorderPane layout1,open_layout,save_layout,delete_layout;
	ListView<String> listView1,listView2,listView3;
	ArrayList<String> projectNames = new ArrayList<String>();
	Components components=new Components();
	Db db = new Db();

	public static void main(String[] args){
		launch(args);
	}

	public void start(Stage primaryStage) throws Exception{
		window = primaryStage;
		window.setTitle("Electrical Application");
		window.setOnCloseRequest(e -> closeProgram());

		layout1 = new BorderPane();

		scene1 = new Scene(layout1,870,650);

		setProjectNames("Tiberiu's project");
		setProjectNames("Vlad's project");
		setProjectNames("Graham's project");

		BorderPane border4 = new BorderPane();
		GridPane grid = addGridPane();
	    border4.setTop(grid);
		layout1.setCenter(border4);
		theGrid = grid;

		/*
		FlowPane flow = addSmallFlowPane();
		layout1.setCenter(flow);
		theFlow = flow;
		*/

		FlowPane fPane = addFlowPane();
		BorderPane border = new BorderPane();
		border.setCenter(fPane);
		layout1.setLeft(border);

		HBox hbox = addSmallHbox();
		layout1.setBottom(hbox);
		hbox.setAlignment(Pos.BOTTOM_CENTER);

		//Open menu item
		BorderPane border1 = new BorderPane();
		listView1 = createListView();
		HBox hbox1 = addHBox("Open");
		border1.setBottom(hbox1);
		hbox1.setAlignment(Pos.BOTTOM_CENTER);
        border1.setLeft(listView1);
		open_layout = new BorderPane();
		scene2 = new Scene(open_layout,600,400);
		open_layout.setBottom(border1);

		//Save menu item
		BorderPane border2 = new BorderPane();
		listView2 = createListView();
		HBox hbox2 = addHBox("Save");
		border2.setBottom(hbox2);
		hbox2.setAlignment(Pos.BOTTOM_CENTER);
		border2.setLeft(listView2);
		save_layout = new BorderPane();
		scene3 = new Scene(save_layout,600,400);
		save_layout.setBottom(border2);

		//Delete menu item
		BorderPane border3 = new BorderPane();
		listView3 = createListView();
		HBox hbox3 = addHBox("Delete");
		border3.setBottom(hbox3);
		hbox3.setAlignment(Pos.BOTTOM_CENTER);
		border3.setLeft(listView3);
		delete_layout = new BorderPane();
		scene4 = new Scene(delete_layout,600,400);
		delete_layout.setBottom(border3);

		//MenuBar menus
		Menu fileMenu1 = new Menu("_File");
		//Menu fileMenu2 = new Menu("Edit");

		 //File Menu items
		 fileMenu1.getItems().add(new MenuItem("New project..."));
		 //Open Project
		 Menu Open = new Menu("Open project...");
		 Open.setOnAction(e -> window.setScene(scene2));
		 fileMenu1.getItems().add(Open);
		//Separator
		 fileMenu1.getItems().add(new SeparatorMenuItem());
		 Menu Save = new Menu("Save project...");
		 Save.setOnAction(e -> window.setScene(scene3));
		 fileMenu1.getItems().add(Save);
		 //Delete
		 Menu Delete = new Menu("Delete project...");
		 Delete.setOnAction(e -> window.setScene(scene4));
		 fileMenu1.getItems().add(Delete);
		 //Separator
		 fileMenu1.getItems().add(new SeparatorMenuItem());
		 //Exit
		 Menu Exit = new Menu("Exit");
		 Exit.setOnAction(e -> closeProgram());
		 fileMenu1.getItems().add(Exit);
		/*
		//Edit Menu items
		 //Save
		 Menu Save = new Menu("Save project...");
		 Save.setOnAction(e -> window.setScene(scene3));
		 fileMenu1.getItems().add(Save);
		 //Delete
		 Menu Delete = new Menu("Delete project...");
		 Delete.setOnAction(e -> window.setScene(scene4));
		 fileMenu1.getItems().add(Delete);
		*/
		//Menu bar
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(fileMenu1);
		layout1.setTop(menuBar);

		window.setScene(scene1);
		window.show();
	}

	public HBox addSmallHbox(){
		HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699;");
	    hbox.setPrefSize(900, 130);

	    Button button = new Button("Test");
	    button.setPrefSize(100, 20);
	    Button button2 = new Button("Create wires");
	    button2.setPrefSize(100, 20);
	    TextField textField = new TextField();
	    textField.setPrefSize(350, 100);
	    textField.setEditable(false);
	    button.setOnAction(e -> textField.setText("This is the input."));
	    button2.setOnAction(e -> wire());

	    hbox.getChildren().addAll(textField, button,button2);

	    return hbox;
	}
	public void wire()
	{
        ArrayList<Line> lines=components.createWires();
        for(Line line:lines)
        	layout1.getChildren().add(line);
	}

	public HBox addHBox(String name) {
	    HBox hbox = new HBox();
	    hbox.setPadding(new Insets(15, 12, 15, 12));
	    hbox.setSpacing(10);
	    hbox.setStyle("-fx-background-color: #336699");

	    Button button = new Button(name);
	    button.setPrefSize(100, 20);
	    Button button2 = new Button("Back");
	    button2.setPrefSize(100, 20);
	    button2.setOnAction(e -> window.setScene(scene1));
	    hbox.getChildren().add(button2);
        if(name == "Save"){
		   Label label = new Label("Save As:");
		   TextField textField = new TextField();
		   hbox.getChildren().addAll(label, textField);
	       button.setOnAction(e -> save_buttonClicked(textField.getText()));
        }
	    else if(name == "Open")
	    	button.setOnAction(e -> open_buttonClicked());
	    else
	    	button.setOnAction(e -> delete_buttonClicked());
	    hbox.getChildren().add(button);
	    return hbox;
	}

	public FlowPane addSmallFlowPane() {
	    FlowPane flow = new FlowPane();
	    flow.setPadding(new Insets(15, 15, 15, 15));
	    flow.setVgap(80);
	    flow.setHgap(50);
	    //flow.setPrefWrapLength(170); // preferred width allows for two columns
	    flow.setStyle("-fx-background-color: #B0D8E0");
	    flow.setAlignment(Pos.CENTER);

	    for(int i=0; i<6; i++){
	    	for(int j=0; j<6;j++){
	    		Text text = new Text("+");
	    	    flow.getChildren().add(text);
	    	}
	    }
	    return flow;
	}

	public GridPane addGridPane() {
	    GridPane grid = new GridPane();
	    grid.setHgap(100);
	    grid.setVgap(100);
	    grid.setPadding(new Insets(0,0, 0, 0));
	    grid.setStyle("-fx-background-color: #B0D8E0");
	    grid.setMinSize(700, 500);
	    grid.setMaxSize(700, 500);
	    Label label = new Label("Paneeee");

	    grid.getChildren().add(label);
        GridPane.setConstraints(label, 7, 5);
	    grid.setGridLinesVisible(false);



	    return grid;
	}

	public FlowPane addFlowPane() {
	    FlowPane flow = new FlowPane();
	    flow.setPadding(new Insets(10, 10, 10, 10));
	    flow.setVgap(4);
	    flow.setHgap(4);
	    flow.setPrefWrapLength(150); // preferred width allows for two columns
	    flow.setStyle("-fx-background-color: DAE6F3;");
	    Label label = new Label("Circuit components:");
	    flow.getChildren().add(label);
	    flow.setAlignment(Pos.CENTER_LEFT);
		MovingClass mv = new MovingClass(layout1, theGrid, scene1);

	    ImageView pages[] = new ImageView[9];
	    for (int i=1; i<9; i++) {
	        pages[i] = new ImageView(new Image("File:src/icons/"+i+".png"));
	        //pages[i].setFitWidth(100);
	        pages[i].setOnMouseClicked(e -> System.out.println("Image clicked"));
	        if(i!=3 && i!=5)
	           flow.getChildren().add(pages[i]);
	        mv.scene=scene1;
			   mv.moveOb(pages[i],i);

	    }
	    return flow;
	}

	public void setProjectNames(String name){
		projectNames.add(name);
	}

	public void getProjectNames(){
		System.out.println(projectNames);
	}

	public ListView<String> createListView(){
		ListView<String> listView;
		listView = new ListView<>();
		listView.setPrefSize(600,350);
		listView.getItems().addAll(db.getTables());
		listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); //MULTIPLE
		return listView;
	}

	private void save_buttonClicked(String text){
		System.out.println(text);
		listView1.getItems().add(text);
		listView2.getItems().add(text);
		listView3.getItems().add(text);
		db.saveToDb(text);
	}
	private void open_buttonClicked(){
		String message = "";
		ObservableList<String> names;
		names = listView1.getSelectionModel().getSelectedItems();
		for(String m : names)
		    message += m + "\n";
		System.out.println(message);
		db.loadFromDb(message,layout1);
	}

	private void delete_buttonClicked(){
		String message = "";
		ObservableList<String> names;
		names = listView3.getSelectionModel().getSelectedItems();
		for(String m : names)
		    message += m + "\n";
		System.out.println(message);
		db.deleteFromDb(message);
	}

	private void closeProgram(){
		  System.out.println("Program closed!");
		  window.close();
	}
}