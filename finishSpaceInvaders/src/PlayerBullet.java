import java.awt.*;

public class PlayerBullet extends Sprite2D {

    private int bulletSpeed = 10;
    Image bulletImage;
    public boolean isAlive;

    // constructor for PlayerBullet
    public PlayerBullet(Image bulletImage, Image i1, Image i2, double x, double y){
        super(i1, i2);
        setPosition(x, y);
        this.bulletImage = bulletImage;
        currentImage = bulletImage;
        isAlive = true;
    }
    // moves bullet upwards by decreasing its y value
    public void moveBullet(){
        y -= bulletSpeed;
        if (y < 0) { // If bullet goes out of bounds
            isAlive = false; // Set bullet as dead
        }
    }

    public double getY() {
        return y;
    }

    public boolean isAlive(){
        return isAlive;
    }


    public void setAlive(boolean alive){
        isAlive = alive;
    }
    // paints the bullet so it comes out of the middle of the spaceship
    public void paint(Graphics g) {
        g.drawImage(bulletImage, (int)x+25, (int)y+25, null);
    }
}


