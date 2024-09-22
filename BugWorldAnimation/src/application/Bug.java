package application;


import javafx.scene.image.ImageView;


public class Bug {

//	protected String name, species;
	double x, y;
	protected int energy,smellStrength;
	protected ImageView view;
	protected int stuck = 0;
	
	private double dx = 1;//X direction
	private double dy = 1;//Y direction
	private double speed = 0.05;//Random moving speed
	
	public Bug( int x, int y) {		

		this.x = x;
		this.y = y;

		this.view = new ImageView(); 

	}
	
    public void setPosition(double x, double y) {
        this.view.setTranslateX(x * 20);
        this.view.setTranslateY(y * 20);
    }
	
	public ImageView getImageView() {
		return view;		
	}
	

	
	public int getSmellStrength() {
		return smellStrength;
	}
	
	
	public void move(World world) {	
		Location location = world.findFoodLocation(this.x, this.y, smellStrength);		
		
		if(location != null && stuck == 0) {
			
			this.moveTowardsFood(location, world);
			if (stuck > 0) {
				stuck--;
			}
		}else {
			this.randomMove(world);
			if (stuck > 0) {
				stuck--;
			}
		}				
		this.setPosition(this.x, this.y);
	}
	
	public void moveTowardsFood(Location location, World world) {
		
		if (Math.abs(this.x - location.getX()) < (0.08) 
				&& Math.abs(this.y - location.getY()) < (0.08) ) {
//			System.out.println("found food");
			this.eatFood();
		}else {
			
			double newX, newY;
					
	        if (this.x < location.getX()) {
	            newX = this.x + speed;  // Move towards right
	        } else {
	            newX = this.x - speed;  // Move towards left
	        }

	        if (this.y < location.getY()) {
	            newY = this.y + speed;  // Move down
	        } else {
	            newY = this.y - speed;  // Move up
	        }
			
			if (world.checkTraversable(newX, newY)) {
				this.x = newX;
				this.y = newY;
				this.setPosition(this.x, this.y);

			}else {
				//if stuck, set the number 20
	            stuck = 20;
				this.generateRandomDir();
				this.x = x + speed * dx;
				this.y = y + speed * dy;
			}		
		}

	}
	
	
	public void randomMove(World world) {
		
		//Check if it is with boundary
		if(this.getImageView().getTranslateX() + this.getImageView().getFitWidth() > world.getWorldPane().getWidth() 
				|| this.getImageView().getTranslateX() < 0) {
			dx = -dx;
		}
		if(this.getImageView().getTranslateY() + this.getImageView().getFitHeight() > world.getWorldPane().getHeight() 
				|| this.getImageView().getTranslateY() < 0) {
			dy = -dy;
		}
		
		double newX = this.x + dx * speed;
		double newY = this.y + dy * speed;
		
		//Check if it encounters obstacle
		if (world.checkTraversable(newX, newY)) {
			this.x = newX;
			this.y = newY;
			this.setPosition(this.x, this.y);
		}else {
			this.generateRandomDir();
		}
	}
	
	public void generateRandomDir() {
	    int[] directions = {-1, 0 , 1};  // -1 means left or down, 1 means up or right, 0 means don't move
	  
	    //The dx and dy cannot be 0 at the same time.
	    do {
		    dx = directions[(int) (Math.random() * 3)] ;  // Generate new dx from -1, 0 ,1
		    dy = directions[(int) (Math.random() * 3)] ;  // Generate new dy from -1, 0 ,1
	    }while( dx == 0 && dy == 0);

	}
	
	public void eatFood() {
		this.energy += 5;
		
	}

	public double getX() {
		return x;
	}



	public double getY() {
		return y;
	}

	public int getEnergy() {
		return energy;
	}

	public void setSmellStrength(int smellStrength) {
		this.smellStrength = smellStrength;
	}
	
}
