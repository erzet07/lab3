import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.*;
import java.util.ArrayList;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{

    // Just a single image, TODO: Generalize

    BufferedImage volvoImage;
    BufferedImage scaniaImage;
    BufferedImage saabImage;




    // To keep track of a single cars position

    Point volvoPoint = new Point();
    Point saabPoint= new Point();
    Point scaniaPoint = new Point();


    // TODO: Make this general for all cars
     void  moveCar(int x, int y,Car car){


         if (car instanceof Saab95) {
             saabPoint.y = y;
             saabPoint.x = x;
         }

         else if (car instanceof Volvo240) {
             volvoPoint.y = y;
             volvoPoint.x = x;
         }

         else if (car instanceof Scania) {
             scaniaPoint.x = x;
             scaniaPoint.y = y;
         }







    }



    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            volvoImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            scaniaImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            saabImage = ImageIO.read(Objects.requireNonNull(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(volvoImage, volvoPoint.x, volvoPoint.y, null); // see javadoc for more info on the parameters

        g.drawImage(saabImage, saabPoint.x, saabPoint.y+100, null);
        g.drawImage(scaniaImage, scaniaPoint.x, scaniaPoint.y+200, null);
    }
}
