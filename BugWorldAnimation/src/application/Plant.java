package application;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Plant extends WorldObject{

	private Image plant = new Image(getClass().getResourceAsStream("tulip.png"));
	
	public Plant(int x, int y) {
		super(x, y);
		this.view = new ImageView(plant);
		this.setPosition(x, y);
	}



	@Override
	public boolean canTraverse() {
		return true;
	}

	@Override
	public int scent() {
		return 3;
	}

	
    public void setPosition(int x, int y) {
    	view.setFitHeight(20);
		view.setFitWidth(20);
    	this.view.setTranslateX(x * 20);
        this.view.setTranslateY(y * 20);
    }

    public double getCentreX() {
    	return this.view.getTranslateX()+ this.view.getFitWidth()/2;
    }
    
    public double getCentreY() {
    	return this.view.getTranslateY()+ this.view.getFitHeight()/2;
    }
    
	@Override
	public boolean isEatable() {
		return true;
	}



	@Override
	public char getSymbol() {
		// TODO Auto-generated method stub
		return 0;
	}

}
