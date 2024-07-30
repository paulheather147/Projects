import java.awt.Image;

public class Spaceship extends Sprite2D {
    private double xSpeed=0;

    public Spaceship(Image i1, Image i2) {
        super(i1, i2); // invoke constructor on superclass Sprite2D
    }

    public void setXSpeed(double dx) {
        xSpeed=dx;
    }

    public void move() {
        // apply current movement
        x+=xSpeed;

        // stop movement at screen edge?
        if (x<=0) {
            x=0;
            xSpeed=0;
        }
        else if (x>=winWidth-myImage1.getWidth(null)) {
            x=winWidth-myImage1.getWidth(null);
            xSpeed=0;
        }
    }

    // Implement getX() method to return the x-coordinate
    public double getX() {
        return x;
    }

    // Implement getY() method to return the y-coordinate
    public double getY() {
        return y;
    }
}


