package application;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;




public class World {

	private static int height = 30;
	private static int width = 30;
	
	private List<Bug> bugs;
	private List<WorldObject> objs;
	
	private BorderPane pane; 
	private Pane worldPane;// The pane that holds all the bugs and objects
	
	private Timeline tl;
	
	private Image worldpane = new Image(getClass().getResourceAsStream("pane.png"));
	private ImageView worldView;
	
	public World() {
        this.pane = new BorderPane();
		this.bugs = new ArrayList<Bug>();
		this.objs = new ArrayList<WorldObject>();
		this.worldPane = new Pane();
		this.setPane();//Set the default pane
		this.setDefault();//Set default world with bugs and objects	
	}

	public void setPane() {
		worldPane.setPrefSize(this.width * 20, this.height * 20);
		this.worldView = new ImageView(worldpane);
		this.worldView.setFitWidth(worldPane.getPrefWidth());	
		this.worldView.setFitHeight(worldPane.getPrefHeight());
		worldPane.getChildren().add(worldView);
	}
	
	public void setDefault() {
		
		Collections.addAll(bugs, 
				new Bee(14, 4),
				new Ant(26, 20),
				new Bee(5, 5)
				
				);
	
		Collections.addAll(objs,  
				new Obstacle (10,8),
				new Obstacle (10,12),
				new Obstacle (12,12),
				new Obstacle (25,15),
				new Obstacle (26,15),
				new Obstacle (27,15),
								
				new Plant (8,8),
				new Plant (5,8),
				new Plant (15,15),
				new Plant (15,16)
				);
		
		for(Bug b : bugs) {
			worldPane.getChildren().add(b.getImageView());
		}

		for(WorldObject obj : objs) {
			worldPane.getChildren().add(obj.getImageView());
		}
		pane.setCenter(worldPane);
		tl = new Timeline();
		this.setButton();
		
	}
		
	public void setButton() {
		HBox box = new HBox();
		box.setAlignment(Pos.TOP_LEFT);
		box.setSpacing(10);
		
		//Set find food button
		Button play = new Button("Find food");
		play.setOnAction(e -> {
			if(tl.getStatus() == Timeline.Status.RUNNING) {
				tl.pause();
			}
			this.updateWorld();
		});
		
		//Set pause button
		Button pause = new Button("Pause");
		pause.setOnAction(e -> tl.pause());
		
		Button random = new Button("Random Move");
		random.setOnAction(e -> {
			if(tl.getStatus() == Timeline.Status.RUNNING) {			
				tl.pause();
			}
			this.updateWorldRandomly();
		});
		
		//Set generate new world button
		Button generate = new Button("Generate");
		generate.setOnAction(e -> {
			if(tl != null) {
				tl.stop();
			}
			this.generateRandomWorld();
		});
		
		//Set quit button
		Button stop = new Button("Stop");
		stop.setOnAction(e ->{
			if(tl != null) {
				tl.stop();
				
				worldPane.getChildren().clear();
				Text text = new Text("Bye Bye Bug World! :)");
				text.setFont(new Font(20));
				text.setFill(Color.BLUEVIOLET);
				
				double textWidth = text.getLayoutBounds().getWidth();
				double textHeight = text.getLayoutBounds().getHeight();
			
				double paneWidth = worldPane.getWidth();
				double paneHeight = worldPane.getHeight();
				double x = (paneWidth - textWidth) / 2;
				double y = (paneHeight + textHeight) / 2;
				
				text.setX(x);
				text.setY(y);
				worldPane.getChildren().add(text);
			}
		});
		
		//Set the buttons on the top of the pane
		box.getChildren().addAll(play,pause, random, generate, stop);
		pane.setTop(box);
		
	}
	
	//Move towards food and update the world
	public void updateWorld() {
		if(tl != null) {
			tl.stop();
		}
		tl =new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(16), e -> {
			for(Bug b : bugs) {
				b.move(this);
			}
		});
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.getKeyFrames().add(frame);
		tl.play();
	
	}
	
	//Randomly move the bugs
	public void updateWorldRandomly() {
		if(tl != null) {
			tl.stop();
		}
		tl = new Timeline();
		KeyFrame frame = new KeyFrame(Duration.millis(16), e -> {
			for(Bug b : bugs) {
				b.randomMove(this);
			}
		});
		tl.setCycleCount(Timeline.INDEFINITE);
		tl.getKeyFrames().add(frame);
		tl.play();
	}
	
	//Generate a new random world
	public void generateRandomWorld() {
		Random r = new Random();
		objs.clear();
		bugs.clear();
		worldPane.getChildren().clear();
		this.setPane();
		
		//Randomly generate bugs
		int bugNum = r.nextInt(5) + 5;
		for(int i = 0; i < bugNum; i++) {
	        int x = r.nextInt(World.width);
	        int y = r.nextInt(World.height);
	        
	        int bugSpecies = r.nextInt(3);	        
	        Bug b;
	        
	        switch (bugSpecies){
	        	case 0 : b = new Ant(x,y); break;
	        	case 1 : b = new Bee(x,y); break;
	        	case 2 : b = new Butterfly(x,y); break;
	        	default: b = new Ant(x,y); break;
	        }
	        this.bugs.add(b);
	        worldPane.getChildren().add(b.getImageView());
		}
		
		// Randomly generate obstacles
		int obNum = r.nextInt(8) + 8;
	    for (int i = 0; i < obNum; i++) {
	        int x = r.nextInt(World.width);
	        int y = r.nextInt(World.height);
	        Obstacle obstacle = new Obstacle(x, y);
	        this.objs.add(obstacle);
	        worldPane.getChildren().add(obstacle.getImageView());
	    }

	    // Randomly generate plants
		int plantNum = r.nextInt(6) + 10;
	    for (int i = 0; i < plantNum; i++) {
	        int x = r.nextInt(World.width);
	        int y = r.nextInt(World.height);
	        Plant plant = new Plant(x, y);
	        this.objs.add(plant);
	        worldPane.getChildren().add(plant.getImageView());
	    }
		
	}
	
	public BorderPane getPane() {
		return pane;
	}
	
	public Pane getWorldPane() {
		return this.worldPane;
	}
	

	//Check if there is an obstacle
	public boolean checkTraversable(double x, double y) {

		for (WorldObject o: this.objs) {
			if ((o.distanceTo(x, y) < 1) && !o.canTraverse()) {
				return false;
			}
		}
		return true;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	//Return the nearest food location of a bug
	public Location findFoodLocation(double x, double y, int strength) {

		WorldObject closestFood = null;
		double minDistance = Double.MAX_VALUE;
		
		
		for (WorldObject o: this.objs) {
			if(o.isEatable()) {
				double distance = o.distanceTo(x, y);
				if (distance <= strength + o.scent() && distance < minDistance) {
					minDistance = distance;
					closestFood = o;
									
				}
			}			
		}
		if (closestFood != null) {
			return closestFood.getLocation();
		} else {
			return null;
		}
	}
	
	public Location getLocation(WorldObject o) {
		return o.getLocation();
	}
	

	
}
