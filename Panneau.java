import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Panneau extends JPanel {
    public void paintComponent(Graphics g){
        try {
            Image img = ImageIO.read(new File("godzilla.jpg"));
            g.drawImage(img, 0, 0, this);
            //Pour une image de fond
            //g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
