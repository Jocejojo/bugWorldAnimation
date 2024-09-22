package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Obstacle extends WorldObject {

	private Image obstacle = new Image(getClass().getResourceAsStream("x.png"));
	
	public Obstacle(int x, int y) {
		super(x, y);	
		this.view = new ImageView(obstacle);
		this.setPosition(x, y);
	}



	@Override
	public boolean canTraverse() {
		return false;
	}


    public void setPosition(int x, int y) {
		view.setFitHeight(20);
		view.setFitWidth(20);
    	this.view.setTranslateX(x * 20);
        this.view.setTranslateY(y * 20);
    }

	@Override
	public String toString() {
		return "Obstacle " + this.getSymbol() + " at: (" + this.x + "," + this.y + ")";
	}
	
    public double getCentreX() {
    	return this.view.getTranslateX()+ this.view.getFitWidth()/2;
    }
    
    public double getCentreY() {
    	return this.view.getTranslateY()+ this.view.getFitHeight()/2;
    }


	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 0;
	}



	@Override
	public int scent() {
		// TODO Auto-generated method stub
		return 0;
	}
}
