import java.awt.Image;

public class Alien extends Sprite2D {
    private static double xSpeed=50;
    public boolean isAlive;

    public Alien(Image i1, Image i2) {
        super(i1, i2); // invoke constructor on superclass Sprite2D
        isAlive = true;
    }

    public boolean move() {
        x+=xSpeed;

        // direction reversal needed?
        if (x<=0 || x>=winWidth-currentImage.getWidth(null))
            return true;
        else
            return false;
    }
    // returns if sprite id alives
    public boolean isAlive() {
        return isAlive;
    }

    //sets isALive to taken in parameter
    public void setAlive(boolean alive) {
        isAlive = alive;
    }


    public static void setFleetXSpeed(double dx) {
        xSpeed=dx;
    }

    public static void reverseDirection() {
        xSpeed=-xSpeed;
    }

    public void jumpDownwards() {
        y+=20;
    }
}



