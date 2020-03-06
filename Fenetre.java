import javax.swing.*;
import java.awt.*;

public class Fenetre extends JFrame{
    private int longueur, largeur;
    private String titre;

    public Fenetre(){
        longueur = 800;
        largeur = 600;
        titre = "Squaïp";
        this.setTitle(titre);
        this.setSize(longueur, largeur);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quitte le programme quand la fenetre est fermee

        JPanel pan = new JPanel();
        pan.setBackground(Color.WHITE);
        this.setContentPane(pan);
    }

    public Fenetre(int longueur, int largeur){
        titre = "Squaïp";
        this.setTitle(titre);
        this.setSize(longueur, largeur);
        this.longueur = longueur;
        this.largeur = largeur;
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quitte le programme quand la fenetre est fermee

        JPanel pan = new JPanel();
        pan.setBackground(Color.WHITE);
        this.setContentPane(pan);
    }

    public Fenetre(String titre){
        longueur = 800;
        largeur = 600;
        this.setTitle(titre);
        this.titre = titre;
        this.setSize(longueur, largeur);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quitte le programme quand la fenetre est fermee

        JPanel pan = new JPanel();
        pan.setBackground(Color.WHITE);
        this.setContentPane(pan);
    }

    public Fenetre(String titre, int longueur, int largeur){
        this.setTitle(titre);
        this.titre = titre;
        this.setSize(longueur, largeur);
        this.longueur = longueur;
        this.largeur = largeur;
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Quitte le programme quand la fenetre est fermee

        JPanel pan = new JPanel();
        pan.setBackground(Color.WHITE);
        this.setContentPane(pan);
    }
}
