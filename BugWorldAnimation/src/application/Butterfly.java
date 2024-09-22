package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Butterfly extends Bug{

	private int id;
	private static int NEXT_ID = 1;
	private Image butterfly = new Image(getClass().getResourceAsStream("butterfly.png"));
	
	public Butterfly(int x, int y) {
		super(x, y);
		this.energy = 20;
		this.smellStrength = 5;
		
		this.id = NEXT_ID++;
		this.view = new ImageView(butterfly);
		
		this.setPosition(x, y);
	}
	
	public String toString() {
		return "Butterfly" + id;
	}

    public void setPosition(int x, int y) {
		view.setFitHeight(20);
		view.setFitWidth(20);
    	this.view.setTranslateX(x * 20);
        this.view.setTranslateY(y * 20);
    }
}
