import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.image.*;

public class InvadersApplication extends JFrame implements Runnable, KeyListener {

    // member data
    private int Score = 0;
    private int Best = 0;
    public boolean isAlive;
    Image alienImage1;
    Image alienImage2;
    public Image bulletImage;
    public PlayerBullet bullet;
    private static String workingDirectory;
    private static boolean isInitialised = false;
    private static final Dimension WindowSize = new Dimension(800,600);
    private BufferStrategy strategy;
    private Graphics offscreenGraphics;
    private static final int NUMALIENS = 30;
    private Alien[] AliensArray = new Alien[NUMALIENS];
    private Spaceship PlayerShip;

    //enumerator that stores 2 states
    private enum STATE{
        MENU,
        GAME
    };
//automatically set the game state to GAME
    private STATE State = STATE.GAME;
    private Menu menu; //initiallise menu state

    // constructor
    public InvadersApplication() {
        //Display the window, centred on the screen
        Dimension screensize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        int x = screensize.width/2 - WindowSize.width/2;
        int y = screensize.height/2 - WindowSize.height/2;
        setBounds(x, y, WindowSize.width, WindowSize.height);
        setVisible(true);
        this.setTitle("Space Invaders");

        // load images from disk
        ImageIcon icon1 = new ImageIcon(workingDirectory + "\\alien_ship_1.png");
        Image alienImage1 = icon1.getImage();
        ImageIcon icon2 = new ImageIcon(workingDirectory + "\\alien_ship_2.png");
        Image alienImage2 = icon2.getImage();
        ImageIcon iconBullet = new ImageIcon(workingDirectory + "\\bullet.png");
        bulletImage = iconBullet.getImage();

        // create and initialise aliens, passing them each the image we have loaded
        for (int i=0; i<NUMALIENS; i++) {
            AliensArray[i] = new Alien(alienImage1, alienImage2);
            double xx = (i%5)*80 + 70;
            double yy = (i/5)*40 + 50;
            AliensArray[i].setPosition(xx, yy);
        }
        Alien.setFleetXSpeed(2);

        // create and initialise the player's spaceship
        ImageIcon iconShip = new ImageIcon(workingDirectory + "/player_ship.png");
        Image shipImage = iconShip.getImage();
        PlayerShip = new Spaceship(shipImage,null);
        PlayerShip.setPosition(300,530);

        menu = new Menu();

        // tell all sprites the window width
        Sprite2D.setWinWidth(WindowSize.width);

        // create and start our animation thread
        Thread t = new Thread(this);
        t.start();

        // send keyboard events arriving into this JFrame back to its own event handlers
        addKeyListener(this);

        // initialise double-buffering
        createBufferStrategy(2);
        strategy = getBufferStrategy();
        offscreenGraphics = strategy.getDrawGraphics();

        isInitialised = true;
    }

    // thread's entry point
    public void run() {
        while ( 1==1 ) {



            // 1: sleep for 1/50 sec
            try {
                Thread.sleep(20);
            } catch (InterruptedException e) { }
            // only run if the games state is GAME
            if(State == STATE.GAME) {
                // changes the aliens between alien_image_1 and 2
                for (int i = 0; i < NUMALIENS; i++) {
                AliensArray[i].changeImage();
            }
                // method that changes the direction of the aliens if neccessary
                boolean alienDirectionReversalNeeded = false;
                for (int i = 0; i < NUMALIENS; i++) {
                    if (AliensArray[i].move()&& AliensArray[i].isAlive())
                        alienDirectionReversalNeeded = true;
                }
                if (alienDirectionReversalNeeded) {
                    Alien.reverseDirection();
                    for (int i = 0; i < NUMALIENS; i++)
                        AliensArray[i].jumpDownwards();
                }
                // calls method to move the bullet shot if it is created
                if (bullet != null && bullet.isAlive()) {
                    bullet.moveBullet();

                    // checks each alien to see if it collides with bullet shot
                    for (int i = 0; i < NUMALIENS; i++) {
                        if (AliensArray[i] != null && AliensArray[i].isAlive() && bullet != null) {
                            // if an alien and bullet do collide the bullet and alien are set to not alive and score is increased and startNewWave method is called
                            if (checkCollision(bullet, AliensArray[i])) {
                                bullet.setAlive(false);
                                AliensArray[i].setAlive(false);
                                System.out.println("Collision detected between bullet and Alien!");
                                Score += 10;
                                startNewWave();
                            }
                        }
                    }


                }

                // moves player ship
                PlayerShip.move();
                // changes the game to the menu state and sets alien aliens to not alive if an alien and the player ship collide
                if (playerAlienCollision()) {
                    State = STATE.MENU;
                    for (int i = 0; i < NUMALIENS; i++) {
                        AliensArray[i].setAlive(false);
                    }
                    // if current score is better than the high score it gets stored as the new highscore/best
                    if(Score > Best){
                        Best = Score;
                    }
                }

            }



            // force an application repaint
            this.repaint();
        }
    }



    // Three Keyboard Event-Handler functions
    public void keyPressed(KeyEvent e) {
        // check if game is running in GAME state
        if (State == STATE.GAME) {
            // makes the player move 4px to the left
            if (e.getKeyCode() == KeyEvent.VK_LEFT)
                PlayerShip.setXSpeed(-4);
                // makes the player move 4px to the right
            else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                PlayerShip.setXSpeed(4);
            else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                // Create a new bullet at the position of the player ship
                System.out.println("Bullet created");
                bullet = new PlayerBullet(bulletImage, alienImage1, alienImage2, PlayerShip.getX(), PlayerShip.getY());
            }
        }
        // check if game is on menu screen
        else if (State == STATE.MENU){
            // a new game is started if the spacebar is pressed
            if((e.getKeyCode() == KeyEvent.VK_SPACE)){
                State = STATE.GAME;
                startNewGame();
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        // stops the player when they let go of left or right arrow key
        if (e.getKeyCode()==KeyEvent.VK_LEFT || e.getKeyCode()==KeyEvent.VK_RIGHT)
            PlayerShip.setXSpeed(0);
    }

    public void keyTyped(KeyEvent e) {
    }
    //
    //method to check collision between to sprites
    private boolean checkCollision(Sprite2D sprite1, Sprite2D sprite2) {
        if (sprite1 == null || sprite2 == null) {
            return false;
        }

        // creates 2 rectangles of dimensions of 2 sprites and with their position that are passed in and then checks if the 2 rectangles intersect
        Rectangle rect1 = new Rectangle((int)sprite1.getX(), (int)sprite1.getY(), sprite1.currentImage.getWidth(null), sprite1.currentImage.getHeight(null));
        Rectangle rect2 = new Rectangle((int)sprite2.getX(), (int)sprite2.getY(), sprite2.currentImage.getWidth(null), sprite2.currentImage.getHeight(null));
        if(rect1.intersects(rect2)){
            return true;
        }
        return false;
    }

    // method to check if the playership collides with each alien and returns true if one does
    public boolean playerAlienCollision(){
        for (int i = 0; i < NUMALIENS; i++) {
            if (AliensArray[i] != null && AliensArray[i].isAlive() && PlayerShip != null) {
                if (checkCollision(AliensArray[i], PlayerShip)) {
                    System.out.println("Collision detected between player ship and alien!");
                    return true;
                }
            }
        }
        return false;
    }

    // sets new speed of aliens to 2
    private int newSpeed = 2;

    // method to check if all of the aliens have been killed and redraw them at their original position with increased speed if they are all dead
    public void startNewWave(){
        int deadCount = 0;
        for(int i =0; i < AliensArray.length; i++){
            if(!AliensArray[i].isAlive()){
                deadCount++;
            }
        }
        if (deadCount == AliensArray.length){
            for (int i=0; i<NUMALIENS; i++) {
                AliensArray[i].setAlive(true);
                double xx = (i%5)*80 + 70;
                double yy = (i/5)*40 + 50; // integer division!
                AliensArray[i].setPosition(xx, yy);

            }
            newSpeed *= 2;
            Alien.setFleetXSpeed(newSpeed);
            System.out.println("new wave");
        }
    }

    // resets score and redraws all aliens at their original position and sets their speed back to original 2
    public void startNewGame(){
        Score = 0;
        newSpeed = 2;
        int newGameSpeed = 2;
        for (int i=0; i<NUMALIENS; i++) {
            AliensArray[i].setAlive(true);
            double xx = (i%5)*80 + 70;
            double yy = (i/5)*40 + 50; // integer division!
            AliensArray[i].setPosition(xx, yy);

        }
        Alien.setFleetXSpeed(newGameSpeed);
    }

    // application's paint method
    public void paint(Graphics g) {
        if (!isInitialised)
            return;

        g = offscreenGraphics;
            // clear the canvas with a big black rectangle
            g.setColor(Color.BLACK);
            g.fillRect(0, 0, WindowSize.width, WindowSize.height);

            // if game is in GAME state it prints out scores, the aliens, and the player ship
            if (State == STATE.GAME) {
                Font smallFont = new Font("Arial", Font.PLAIN, 20);
                g.setFont(smallFont);
                g.setColor(Color.white);
                g.drawString("Score: "+Score, 20, 50);
                g.drawString("Best: "+Best, 150, 50);
                for (int i = 0; i < NUMALIENS; i++)
                    // only prints alien if alive
                    if (AliensArray[i].isAlive()) {
                        AliensArray[i].paint(g);
                    }
                PlayerShip.paint(g);

                if (bullet != null && bullet.isAlive()) {
                    bullet.paint(g);
                    System.out.println("bullet shot");

                }

        }
            //paints menu screen if in MENU state
        else if(State == STATE.MENU){
            menu.paint(g);
        }



        // flip the buffers offscreen to onscreen
        strategy.show();
    }

    // application entry point
    public static void main(String[] args) {
        workingDirectory = System.getProperty("user.dir");
        System.out.println("Working Directory = " + workingDirectory);
        InvadersApplication w = new InvadersApplication();
    }

}


