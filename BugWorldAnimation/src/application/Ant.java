package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Ant extends Bug {
	
	private int id;
	private static int NEXT_ID = 1;
	private Image ant = new Image(getClass().getResourceAsStream("ant.png"));
	


	
	public Ant( int x, int y) {
		super( x, y);
		this.energy = 25;
		this.smellStrength = 6;
		
		this.id = NEXT_ID++;
		this.view = new ImageView(ant);

		this.setPosition(x, y);
	}

	@Override
	public String toString() {
		return "Bee" + id;
	}
	
    public void setPosition(int x, int y) {
		view.setFitHeight(20);
		view.setFitWidth(20);
    	this.view.setTranslateX(x * 20);
        this.view.setTranslateY(y * 20);
    }
}
