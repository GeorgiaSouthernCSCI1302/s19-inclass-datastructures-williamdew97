import java.nio.file.DirectoryIteratorException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.Stack;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MyDeckAsASet extends Application {

	Set<ImageView>deck = new HashSet<ImageView>();
	Iterator<ImageView> itr;
	public static void main(String[] args) {
		launch();

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//Setup UI **********
		Button next = new Button("Next Card");
		HBox disp = new HBox(20);
		disp.getChildren().add(next);
		next.setOnMouseClicked((new EventHandler<MouseEvent>() {
	          public void handle(MouseEvent arg0) {
	              disp.getChildren().add(itr.next());
	          }}));
		Scene window = new Scene(disp, 900, 600);
		primaryStage.setScene(window);
		//******************
		
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("src/cards"), "*.png")) {//get everything in the directory
	           for (Path entry: stream) {// for each entry in the directory do 
	        	   System.out.println("file:"+entry);
	        	   ImageView iv =new ImageView("file:"+entry) ;
	        	   iv.setFitHeight(200);
	        	   iv.setFitWidth(120);
	              deck.add(iv); //add to Stack
	           }
	       } catch (DirectoryIteratorException ex) {
	           // I/O error encounted during the iteration, the cause is an IOException
	           throw ex.getCause();
       }
		itr = deck.iterator();
	   primaryStage.show();

  }
	
	
}
