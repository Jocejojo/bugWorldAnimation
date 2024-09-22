package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bee extends Bug {
	
	private int id;
	private static int NEXT_ID = 1;
	private Image bee = new Image(getClass().getResourceAsStream("bee.png"));
	

	
	public Bee(int x, int y) {
		super(x, y);
		this.energy = 30;
		this.smellStrength = 8;
		this.id = NEXT_ID++;
		this.view = new ImageView(bee);
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
