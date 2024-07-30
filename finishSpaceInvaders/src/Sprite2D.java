import java.awt.*;

public class Sprite2D {

    // member data
    protected double x,y;
    protected Image myImage1;
    protected Image myImage2;
    protected Image currentImage;
    protected int frameCount;
    protected static int winWidth;

    // constructor
    public Sprite2D(Image i1, Image i2) {
        myImage1 = i1;
        myImage2 = i2;
        currentImage = myImage1;
    }

    public void setPosition(double xx, double yy) {
        x=xx;
        y=yy;
    }

    public void paint(Graphics g) {
        g.drawImage(currentImage, (int)x, (int)y, null);
    }

    public static void setWinWidth(int w) {
        winWidth = w;
    }
    // method the changes the current image between alien pic 1 and 2 every 50 frames
    public void changeImage(){
        frameCount++;
        if (frameCount % 50 == 0) {
            if (currentImage == myImage1) {
                currentImage = myImage2;
            } else {
                currentImage = myImage1;
            }
        }
    }

    public double getX() {
        return x;
    }

    // Implement getY() method to return the y-coordinate
    public double getY() {
        return y;
    }

}




