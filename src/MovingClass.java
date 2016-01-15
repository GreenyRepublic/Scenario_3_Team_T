package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.*;

import java.io.File;
import java.util.ArrayList;

public class MovingClass {

	BorderPane layout1;
	GridPane grid;
	ImageView theImage, theImg, theCompImg;
	Scene scene;
	Components components = new Components();

	public MovingClass(BorderPane layout1, GridPane grid){
		this.layout1 = layout1;
		this.grid = grid;
	}

	public void moveOb(ImageView hold, int index){
		hold.setOnDragDetected(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
            	System.out.println("in set on drag Detected");
                Dragboard db = hold.startDragAndDrop(TransferMode.ANY);

                // Put a string on a dragboard

                theImage = hold;

                //theImg = new ImageView(theImage.getImage());
                //theImages.add(theImg);
                //currentListIndex = theImages.indexOf(theImg);
                ClipboardContent content = new ClipboardContent();
                content.putImage(hold.getImage());
                db.setContent(content);

                event.consume();
            }
        });

		layout1.setOnDragOver(new EventHandler<DragEvent>() {
            public void handle(DragEvent event) {

            	System.out.println("in set on drag OVER");
               // if (event.getGestureSource() != layout1 &&
                      //  event.getDragboard().hasString()) {
                    // allow for both copying and moving, whatever user chooses
                    event.acceptTransferModes(TransferMode.COPY);
               // }

                event.consume();
            }
        });

		 layout1.setOnDragEntered(new EventHandler<DragEvent>() {
	            public void handle(DragEvent event) {
	            // the drag-and-drop gesture entered the target //
	            // show to the user that it is an actual gesture target //
	            	System.out.println("in set on drag Entered");
	            	if (event.getGestureSource() != layout1 &&
	                        event.getDragboard().hasString()) {
	                    System.out.println("You are over text!!!!!!!!!");
	                }


	                 event.consume();
	            }
	        });

		 layout1.setOnDragExited(new EventHandler<DragEvent>() {
	            public void handle(DragEvent event) {
	                // mouse moved away, remove the graphical cues
	            	System.out.println("in set on drag Exited");

	                event.consume();
	            }
	        });

		  grid.setOnDragDropped(new EventHandler<DragEvent>() {
	            public void handle(DragEvent event) {
	                // data dropped
	                // if there is a string data on dragboard, read it and use it
	            	System.out.println("in set on drag DROPPED");
	            	double x=0,y=0;
	                Dragboard db = event.getDragboard();
	                boolean success = false;
	                if (event.getSource() == grid) {

	                   System.out.println("in set on drag DROPPED in GRID");
	                   theImg = new ImageView(theImage.getImage());
	                   x=event.getSceneX();
            	       y=event.getSceneY();
	                   for(int i=220;i<=820;i+=100)
	                   {
	                	  if((x-i<=50 && x-i>=0)||(x-i>=-50 && x-i<0))
	                	  theImg.setX(i-theImage.getImage().getWidth()/2);
	                   }

	                   for(int i=75;i<=475;i+=100)
	                   {
	                	  if((y-i<=50 && y-i>=0)||(y-i>=-50 && y-i<0))
	                	  theImg.setY(i-theImage.getImage().getHeight()/2);
	                   }
	                   for(double x1:components.xPos)
	                	   for(double y1:components.yPos)
	                		   if(theImg.getX()==x1 && theImg.getY()==y1)
	                			   theImg.setImage(null);
	                   components.list.add(index+".png");
	                   components.xPos.add(theImg.getX());
	                   components.yPos.add(theImg.getY());
	                   layout1.getChildren().add(theImg);
	                   moveCurrentImg2(theImg);


	                   success = true;





	               }
	                // let the source know whether the string was successfully
	                  //transferred and used
	                event.setDropCompleted(success);

	                event.consume();


	             }
	        });

		  hold.setOnDragDone(new EventHandler<DragEvent>() {
	            public void handle(DragEvent event) {
	                // the drag and drop gesture ended
	                // if the data was successfully moved, clear it
	            	System.out.println("in set on drag Done");
	                if (event.getTransferMode() == TransferMode.COPY) {

	                }
	                event.consume();
	            }
	        });
	}


	 public void moveCurrentImg2(ImageView button1){
		final Delta dragDelta = new Delta();

		button1.setOnMousePressed(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {
				  System.out.println("mouse pressed");
			    dragDelta.x = button1.getLayoutX() - mouseEvent.getSceneX();
			    dragDelta.y = button1.getLayoutY() - mouseEvent.getSceneY();
			    button1.setCursor(Cursor.MOVE);
			    System.out.println("pressing");
			  }
			});
		button1.setOnMouseReleased(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {

			    button1.setCursor(Cursor.HAND);
			    System.out.println("releasing");
			  }
			});


		button1.setOnMouseDragged(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {
				  System.out.println("mouse DRAGGED");
			    button1.setLayoutX(mouseEvent.getSceneX() + dragDelta.x);
			    button1.setLayoutY(mouseEvent.getSceneY() + dragDelta.y);
			    System.out.println("dragging");
			  }
			});
		button1.setOnMouseEntered(new EventHandler<MouseEvent>() {
			  @Override public void handle(MouseEvent mouseEvent) {

			    button1.setCursor(Cursor.HAND);
			    System.out.println("Entering");
			 }
			});
	}
}
class Delta { double x, y; }