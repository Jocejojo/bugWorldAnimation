package application;

import javafx.scene.image.ImageView;

public abstract class WorldObject {
	protected int x, y;
	protected ImageView view;
	protected Location location;
		
	public WorldObject(int x, int y) {
		this.x = x; 
		this.y = y;
		this.setLoaction();		
	}
		
	public void setLoaction() {
		this.location = new Location(x,y);
	}

	public Location getLocation() {
		return location;
	}
	public abstract char getSymbol();
	
	public abstract boolean canTraverse();

	public abstract int scent();
	

	// Get the straight line distance
	public double distanceTo(double otherX, double otherY) {
		return Math.sqrt(Math.pow(this.x - otherX,2) + Math.pow(this.y - otherY,2));
	}


	
	public boolean isEatable() {
		return false;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public ImageView getImageView() {
		return view;		
	}
}
