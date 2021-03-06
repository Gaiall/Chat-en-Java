import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

public class Chat{
    public static void main(String[] args){
        /*
        String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

        for (int i = 0; i < fonts.length; i++) {
            System.out.println(fonts[i]);
        }
        */

        int longueur = 500;
        int largeur = 600;
        Fenetre fenetre = new Fenetre("Squaïp", longueur, largeur);
        Box boiteDeNom = Box.createHorizontalBox();
        Box boiteDeIP = Box.createHorizontalBox();
        Box boiteDePort = Box.createHorizontalBox();
        Box boiteDeAdresse = Box.createHorizontalBox();
        Box boiteDeBoutonConnecter = Box.createHorizontalBox();
        Box boiteInfoConnexion = Box.createVerticalBox();
        Box boiteLeChat = Box.createVerticalBox();
        Box boiteLesBoites = Box.createVerticalBox();

        SocketContainer socketServeurContainer = null;

        JTextField zoneDeNom = new JTextField(" nom");
        JTextField zoneDeIP = new JTextField(" adresse");
        JTextField zoneDePort = new JTextField(" port");
        JLabel labelDeNom = new JLabel("Nom :");
        JLabel labelDeIP = new JLabel("Adresse IP :");
        JLabel labelDePort = new JLabel("Port :");
        JButton bouttonConnecter = new JButton("Connexion");
        Font roboto = new Font("Roboto", Font.PLAIN, 24);

        JTextArea listeDesConnectes = new JTextArea();
        listeDesConnectes.setText("");
        JTextArea leChat = new JTextArea();
        //on fill la textArea
        for(int i = 0; i < 22; i++){
            leChat.append("\n");
        }
        JTextField leMessage = new JTextField("");
        JButton boutonEnvoyer = new JButton("Envoyer");

        JPanel zoneDeChat = new JPanel(new GridBagLayout());
        GridBagConstraints contrainte = new GridBagConstraints();

        //Tout ce qui est mise au point de la zone de chat
        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.weightx = 1;
        contrainte.weighty = 1;
        contrainte.gridx = 0;
        contrainte.gridy = 0;
        contrainte.gridheight = 5;
        contrainte.gridwidth = 1;
        listeDesConnectes.setPreferredSize(new Dimension(100, 400));
        zoneDeChat.add(listeDesConnectes, contrainte);

        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.gridx = 1;
        contrainte.gridy = 0;
        contrainte.gridwidth = 2;
        contrainte.gridheight = 3;
        leChat.setPreferredSize(new Dimension(200, 350));
        zoneDeChat.add(leChat, contrainte);

        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.gridx = 1;
        contrainte.gridy = 3;
        contrainte.gridwidth = 2;
        contrainte.gridheight = 1;
        leMessage.setPreferredSize(new Dimension(200, 25));
        zoneDeChat.add(leMessage, contrainte);

        contrainte.fill = GridBagConstraints.HORIZONTAL;
        contrainte.gridx = 1;
        contrainte.gridy = 4;
        contrainte.gridwidth = 2;
        contrainte.gridheight = 1;
        boutonEnvoyer.setPreferredSize(new Dimension(200, 25));
        zoneDeChat.add(boutonEnvoyer, contrainte);

        //Les comportement des boutons et zone de chat
        //Lui il connecte
        bouttonConnecter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(bouttonConnecter.getText() == "Connexion"){
                    String adresseIP = zoneDeIP.getText();
                    String port = zoneDePort.getText();
                    Boolean addresseOK = adresseIP.matches("\\s*\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}[.]\\d{1,3}\\s*");
                    Boolean portOK = port.matches("\\s*\\d{1,4}\\s*");
                    adresseIP = adresseIP.trim();
                    port = port.trim();
                    if(addresseOK && portOK){
                        System.out.println(adresseIP + ":" + port);
                        //La c'est l'endroit ou on connecte
                        try{
                            socketServeurContainer.setSocket(new Socket(InetAddress.getByName(adresseIP), Integer.parseInt(port)));
                        } catch (UnknownHostException e){
                            System.out.println("Le serveur n'a pas été trouvé.");
                            JOptionPane.showMessageDialog(fenetre, "Le serveur n'a pas été trouvé.");
                        } catch (IOException e){
                            JOptionPane.showMessageDialog(fenetre, "I/O Exception.");
                        }

                        //une fois connecté on empeche les infos de connection d'etre modifiées
                        zoneDeNom.setEditable(false);
                        zoneDeIP.setEditable(false);
                        zoneDePort.setEditable(false);
                        //On permet l'utilisation du chat
                        boutonEnvoyer.setEnabled(true);
                        leMessage.setEditable(true);
                        //On en fait un bouton de deconnexion
                        bouttonConnecter.setText("Deconnexion");
                    }
                } else {
                    //on deconnecte donc on autorise de nouveau de tout changer
                    zoneDeNom.setEditable(true);
                    zoneDeIP.setEditable(true);
                    zoneDePort.setEditable(true);
                    boutonEnvoyer.setEnabled(false);
                    leMessage.setEditable(false);
                    //On en fait un bouton de deconnexion
                    bouttonConnecter.setText("Connexion");
                }
            }
        });
        //Les 3 la c'est de l'esthetique
        zoneDeNom.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                if(zoneDeNom.getText().equals(" nom")){
                    zoneDeNom.setText("");
                }
            }

            public void focusLost(FocusEvent e){
                if(zoneDeNom.getText().equals("")){
                    zoneDeNom.setText(" nom");
                }
            }
        });
        zoneDeIP.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                if(zoneDeIP.getText().equals(" adresse")){
                    zoneDeIP.setText("");
                }
            }

            public void focusLost(FocusEvent e){
                if(zoneDeIP.getText().equals("")){
                    zoneDeIP.setText(" adresse");
                }
            }
        });
        zoneDePort.addFocusListener(new FocusListener(){
            public void focusGained(FocusEvent e){
                if(zoneDePort.getText().equals(" port")){
                    zoneDePort.setText("");
                }
            }

            public void focusLost(FocusEvent e){
                if(zoneDePort.getText().equals("")){
                    zoneDePort.setText(" port");
                }
            }
        });
        //Eux ca envoie les messages
        leMessage.addKeyListener(new KeyAdapter(){
            public void keyPressed(KeyEvent e){
                if(e.getKeyCode()==KeyEvent.VK_ENTER){
                    String message = leMessage.getText();
                    if(!(message.trim().equals(""))){
                        leMessage.setText("");
                        int finDeLigne = 1;
                        try{
                            finDeLigne = leChat.getLineEndOffset(0);
                            leChat.replaceRange("", 0, finDeLigne);
                        } catch(Exception exception){}
                        leChat.append("\n " + message);
                    }
                }
            }
        });
        boutonEnvoyer.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                String message = leMessage.getText();
                if(!(message.trim().equals(""))){
                    leMessage.setText("");
                    int finDeLigne = 1;
                    try{
                        finDeLigne = leChat.getLineEndOffset(0);
                        leChat.replaceRange("", 0, finDeLigne);
                    } catch(Exception exception){

                    }
                    leChat.append("\n " + message);
                }
            }
        });

        //On empeche d'utiliser le chat tant qu'on est pas connecté
        boutonEnvoyer.setEnabled(false);
        leMessage.setEditable(false);

        zoneDeNom.setMaximumSize(new Dimension(100, 20));
        zoneDeIP.setPreferredSize(new Dimension(200, 20));
        zoneDePort.setPreferredSize(new Dimension(70, 20));

        listeDesConnectes.setEditable(false);
        listeDesConnectes.setBorder(new LineBorder(Color.black, 1));
        leChat.setEditable(false);
        leChat.setBorder(new LineBorder(Color.black, 1));

        //Tout ce qui est ajout dans les boites
        boiteDeNom.add(labelDeNom);
        boiteDeNom.add(Box.createRigidArea(new Dimension(10, 0)));
        boiteDeNom.add(zoneDeNom);

        boiteDeIP.add(labelDeIP);
        boiteDeIP.add(Box.createRigidArea(new Dimension(10, 0)));
        boiteDeIP.add(zoneDeIP);

        boiteDePort.add(labelDePort);
        boiteDePort.add(Box.createRigidArea(new Dimension(10, 0)));
        boiteDePort.add(zoneDePort);

        boiteDeAdresse.add(boiteDeIP);
        boiteDeAdresse.add(Box.createRigidArea(new Dimension(10, 0)));
        boiteDeAdresse.add(boiteDePort);

        boiteDeBoutonConnecter.add(bouttonConnecter);

        boiteInfoConnexion.add(boiteDeNom);
        boiteInfoConnexion.add(Box.createRigidArea(new Dimension(0, 10)));
        boiteInfoConnexion.add(boiteDeAdresse);
        boiteInfoConnexion.add(Box.createRigidArea(new Dimension(0, 10)));
        boiteInfoConnexion.add(boiteDeBoutonConnecter);

        boiteLeChat.add(zoneDeChat);

        boiteLesBoites.add(boiteInfoConnexion);
        boiteLesBoites.add(Box.createRigidArea(new Dimension(0, 10)));
        boiteLesBoites.add(boiteLeChat);

        fenetre.getContentPane().add(boiteLesBoites);
        fenetre.setResizable(false);
        fenetre.setVisible(true);
    }
}
