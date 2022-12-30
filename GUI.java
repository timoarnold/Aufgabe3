import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.io.File;
import javax.swing.event.MenuListener;
import javax.swing.event.MenuEvent;
import java.awt.Font;
import java.awt.Color;
//import javafx.scene.layout.Border;

import javax.swing.JFrame;  
import javax.swing.JLabel;  
import javax.swing.JTextField;

import javax.imageio.*;

/**
 * Das GUI steht für Graphical User Interface und ist der Visual Experience Builder für alle Anwendungen.
 * Es umfasst grafische Einheiten wie Buttons, Beschriftungen, Fenster usw., über welche die Benutzer mit einer Anwendung
 * in Verbindung treten können.
 *
 * @author Gruppe 29
 * @version 4.1 (31. Dezember 2022)
 */
public class GUI extends JFrame { 
    /**
     * Instanzvariabel:
     * controller: Der zum GUI gehörende Controller
     */
    private final GUIController controller;
    
    /**
     * Konstruktor für Objekte der Klasse Fabrik: Hier wird die Instanzvariabel controller initialisiert.
     */
    public GUI(GUIController controller) {
        this.controller = controller;
        initWindow();
    }
    
    /**
     * Sobald das GUI Fenster vollständig konstruiert ist,
     * kann es mit setVisible im kompletten Zustand angezeigt werden.
     */
    protected void initWindow() {
        this.getContentPane().removeAll();
        this.repaint();
        
        menu();
        JLabel welcomeLabel = new JLabel("Hej Jonathan välkommen till AEKI!");

        Image startseiteBild = null;
            try {
                startseiteBild = ImageIO.read(new File("start.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_startseite = new ImageIcon(startseiteBild);
            JLabel label_bild_startseite = new JLabel(bild_startseite); 
        
        welcomeLabel.setBounds(10, 50, 1000, 25);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        welcomeLabel.setForeground(new Color(120, 90, 40));
        label_bild_startseite.setBounds(10, 10, 1000, 750);
         
        this.add(welcomeLabel);
        this.add(label_bild_startseite);

        setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1650,1650);
        this.setVisible(true);
    }
    
    /**
     * Hier wird die Menuleiste erstellt.
     * Die Menuleiste besteht aus folgenden Elementen: 'Startseite', 'Neue Bestellung', 'Über uns', 'Hilfe' und 'Exit'.
     * 
     * Durch sowohl den MenuListener als auch den ActionListener wird jeweils auf die Menufunktionen verwiesen.
     */
    public void menu() {
        JMenuBar menueBar = new JMenuBar();
        
        // JMenu "Startseite"
        JMenu startseiteMenue = new JMenu("Startseite");
        startseiteMenue.addMenuListener(new MenuListener () {
            @Override
            public void menuSelected(MenuEvent e) {
                startseiteAnzeigen();
        }
        @Override
            public void menuCanceled(MenuEvent e) {
                System.out.println("Cancelled");
        }
        @Override
            public void menuDeselected(MenuEvent e) {
                System.out.println("Deselected");
        }
        } );
        menueBar.add(startseiteMenue);
        
        // JMenu "Bestellung" mit JMenuItems "Neue Bestellung" und "Beenden" erzeugen
        JMenu bestellungMenue = new JMenu("Neue Bestellung");
        menueBar.add(bestellungMenue);
        
        JMenuItem aufgebenEintrag = new JMenuItem("Aufgeben");
        aufgebenEintrag.addActionListener(e -> bestellungAufgeben());
        bestellungMenue.add(aufgebenEintrag);
        
        JMenuItem produkteEintrag = new JMenuItem("Produktübersicht");
        produkteEintrag.addActionListener(e -> produkteFotos());
        bestellungMenue.add(produkteEintrag);
        
        // JMenu "Produktionsmanager" mit JMenuItem "Übersicht"
        JMenu produktionsmanagerMenue = new JMenu("Produktionsmanager");
        menueBar.add(produktionsmanagerMenue);
        
        JMenuItem übersichtEintrag = new JMenuItem("Übersicht");
        übersichtEintrag.addActionListener(e -> bestellungUebersicht());
        produktionsmanagerMenue.add(übersichtEintrag);
        
        // JMenu "Über uns" mit JMenuItems "das Team" und "Geschichte" erzeugen
        JMenu ueberunsMenue = new JMenu("Über uns");
        menueBar.add(ueberunsMenue);
        
        JMenuItem dasteamEintrag = new JMenuItem("das Team");
        dasteamEintrag.addActionListener(e -> dasteam());
        ueberunsMenue.add(dasteamEintrag);
        
        JMenuItem geschichteEintrag = new JMenuItem("Geschichte");
        geschichteEintrag.addActionListener(e -> geschichte());
        ueberunsMenue.add(geschichteEintrag);
        
        //JMenu "Hilfe" mit JMenuItem "Info..." erzeugen
        JMenu hilfeMenue = new JMenu("Hilfe");
        menueBar.add(hilfeMenue);
        
        JMenuItem infoEintrag = new JMenuItem("Info...");
        infoEintrag.addActionListener(e -> zeigeInfo());
        hilfeMenue.add(infoEintrag);
        
        //JMenu "Exit" mit JMenuItem "Anwendung beenden..." erzeugen
        JMenu exitMenue = new JMenu("Exit");
        menueBar.add(exitMenue);
        
        JMenuItem beendenEintrag = new JMenuItem("Anwendung beenden");
        beendenEintrag.addActionListener(e -> anwendungBeenden());
        exitMenue.add(beendenEintrag);

        this.setJMenuBar(menueBar);
        this.setVisible(true);
    }
    
    /**
     * Implementierung der Menü-Funktionen
     * Durch 'removeAll' und 'repaint' werden die Interfaces jeweils erneuert.
     * Durch 'setvisible' werden die Interfaces jeweils sichtbar gemacht.
     */
    
    /**
     * 'startseiteAnzeigen'-Funktion: Zeigt die Startseite an
     */
    private void startseiteAnzeigen() {
        this.getContentPane().removeAll();
        this.repaint();
        
        JLabel welcomeLabel = new JLabel("Hej Jonathan välkommen till AEKI!");
        
        Image startseiteBild = null;
            try {
                startseiteBild = ImageIO.read(new File("start.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_startseite = new ImageIcon(startseiteBild);
            JLabel label_bild_startseite = new JLabel(bild_startseite);
        
        // Positionieren
        welcomeLabel.setBounds(10, 50, 1000, 25);
        welcomeLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        welcomeLabel.setForeground(new Color(120, 90, 40));
        label_bild_startseite.setBounds(10, 10, 1000, 750);
        
        // Zum Panel hinzufügen
        this.add(welcomeLabel);
        this.add(label_bild_startseite);
        
        // Sichtbar machen
        this.setVisible(true);
    }
    
    /**
     * 'bestellungAufgeben'-Funktion: Öffnet einen Auswahldialog zum Bestellen.
     * 
     * Der Action Listener wird jeweils den Buttons hinzugefügt und erstellt dann auf Knopfdruch durch 'contentPanecontroller.onOrder' eine Bestellung.
     * Zudem wird die bestellBestaetigung upgedated.
     */
    private void bestellungAufgeben() {
        this.getContentPane().removeAll();
        this.repaint();
        JLabel welcomeLabel = new JLabel("<html>Hej Jonathan välkommen till AEKI! <br/>Untenstehend kannst Du gerne Deine Bestellung aufgeben. Viel Spass!</html>");
        
        JLabel label_stuehle = new JLabel("Anzahl Stühle");
        JLabel label_sofas = new JLabel("Anzahl Sofas");
        JLabel label_bestellBestaetigung = new JLabel("Bestellbestätigung:");
        JLabel label_status = new JLabel("Anzahl Bestellungen:");
        JLabel label_bestellUebersicht = new JLabel("");

        JTextField textfield_stuehle = new JTextField();
        JTextField textfield_sofas = new JTextField();

        JButton senden_knopf = new JButton("Bestellen");
        JButton button_refreshstatus = new JButton("Bestellinformationen");
        
        senden_knopf.addActionListener((arg) -> {
                int stuehle = Integer.parseInt(textfield_stuehle.getText());
                int sofas = Integer.parseInt(textfield_sofas.getText());
                controller.onOrder(sofas, stuehle);
                String bestellBestaetigung = controller.gibBestellBestaetigung();
                label_bestellBestaetigung.setText("Bestellbestätigung: " + bestellBestaetigung);
                String bestellNummer = controller.gibAnzahlBestellungen();
                label_status.setText("Anzahl Bestellungen: " + bestellNummer);
        });
        
        // Action listener mit Lambda Funktion () -> {}
        button_refreshstatus.addActionListener ((arg) -> {
            String bestellUebersicht = controller.gibBestellInformationen();
            label_bestellUebersicht.setText("<html><br/>" + bestellUebersicht + "</html>");
        });
        
        // Positionieren
        welcomeLabel.setBounds(10, 10, 1000, 35);
        label_stuehle.setBounds(10, 60, 1000, 25);
        textfield_stuehle.setBounds(180, 60, 100, 25);
        label_sofas.setBounds(10,90, 100, 25);
        textfield_sofas.setBounds(180,90,100, 25);
        label_bestellBestaetigung.setBounds(10,120, 1000, 25);
        label_status.setBounds(10,150, 400, 25);
        senden_knopf.setBounds(10,180,150,30);
        button_refreshstatus.setBounds(190,180,150,30);
        label_bestellUebersicht.setBounds(10, 220, 500, 100);

        // Zum Panel hinzufügen
        this.add(welcomeLabel);
        this.add(label_stuehle);
        this.add(label_sofas);
        this.add(label_bestellBestaetigung);
        this.add(label_status);
        this.add(textfield_stuehle);
        this.add(textfield_sofas);
        this.add(senden_knopf);
        this.add(button_refreshstatus);
        this.add(label_bestellUebersicht);
        
        // Sichtbar machen
        this.setVisible(true);
    }
    
    /**
     * 'produkteFotos'-Funktion: Zeigt Bild-Impressionen und Preise der vorhandenen Produkte (Stühle und Sofas) an
     */
    private void produkteFotos() {        
        this.getContentPane().removeAll();
        this.repaint();

        // Fügt ein Bild der im Verkauf vorhandenen Produkte ein
        Image produkteBild = null;
            try {
                produkteBild = ImageIO.read(new File("produkte.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_produkte = new ImageIcon(produkteBild);
            JLabel label_bild_produkte = new JLabel(bild_produkte);
             
        JLabel stuhlLabel = new JLabel("Stuhl - 29.90 CHF pro Stück");
        JLabel sofaLabel = new JLabel("Sofa - 99.90 CHF pro Stück");
        
        // Positionieren
        stuhlLabel.setBounds(50, 475, 1000, 100);
        sofaLabel.setBounds(800, 475, 1000, 100);
        
        label_bild_produkte.setBounds(1, 40, 1650, 450);
        
        stuhlLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        sofaLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        
        // Zum Panel hinzufügen
        this.add(stuhlLabel);
        this.add(sofaLabel);
        this.add(label_bild_produkte);
        
        // Sichtbar machen
        this.setVisible(true);
    }
                
    /**
     * 'bestellungUebersicht'-Funktion: Zeigt die Bestell- und Lagerübersicht aus der Sicht des Produktionsmanagers an
     */
    private void bestellungUebersicht() {
        this.getContentPane().removeAll();
        this.repaint();
        
        JLabel welcomeLabel = new JLabel("<html>Hier wird dem Produktionsmanager die Bestell- und Lagerübersicht angezeigt.</html>");
        JLabel label_bestellUebersicht = new JLabel("");
        JButton button_bestelluebersicht = new JButton("Bestellübersicht anzeigen");
        JButton button_lageruebersicht = new JButton("Lagerübersicht anzeigen");
        JLabel label_lagerUebersicht = new JLabel("");

        button_bestelluebersicht.addActionListener ((arg) -> {
            String bestellUebersicht = controller.gibBestellInformationen();
            label_bestellUebersicht.setText("<html><br/>" + bestellUebersicht + "</html>");
        });

        button_lageruebersicht.addActionListener ((arg) -> {
            String lagerUebersicht = controller.gibLagerInformationen();
            label_lagerUebersicht.setText("<html><br/>" + lagerUebersicht + "</html>");
        });
        
        // Positionieren
        welcomeLabel.setBounds(10, 10, 1000, 25);
        button_bestelluebersicht.setBounds(10, 50, 200, 30);
        label_bestellUebersicht.setBounds(20, 60, 1000, 600);
        button_lageruebersicht.setBounds(450, 50, 200, 30);
        label_lagerUebersicht.setBounds(460, 65, 1000, 200);
        
        // Zum Panel hinzufügen
        this.add(welcomeLabel);
        this.add(button_bestelluebersicht);
        this.add(label_bestellUebersicht);
        this.add(button_lageruebersicht);
        this.add(label_lagerUebersicht);
        
        // Sichtbar machen
        this.setVisible(true);
    }
    
    /**
     * 'Das Team'-Funktion: Zeigt einen Überblick über das Team
     */
    private void dasteam() {        
        this.getContentPane().removeAll();
        this.repaint();
        
        JLabel teamLabel = new JLabel("Das AEKI-Team ist 365 Tage im Jahr für Sie da.");
        teamLabel.setForeground(new Color(120, 90, 40));
        JLabel timoLabel = new JLabel("Timo Arnold - Produktion");
        timoLabel.setForeground(new Color(247, 213, 21));
        timoLabel.setOpaque(true);
        timoLabel.setBackground(new Color(3, 81, 186));
        JLabel florianneLabel = new JLabel("Florianne Walliser - Sales");
        florianneLabel.setForeground(new Color(247, 213, 21));
        florianneLabel.setOpaque(true);
        florianneLabel.setBackground(new Color(3, 81, 186));
        JLabel timLabel = new JLabel("Tim Ilgenstein - IT");
        timLabel.setForeground(new Color(247, 213, 21));
        timLabel.setOpaque(true);
        timLabel.setBackground(new Color(3, 81, 186));
        JLabel jeffLabel = new JLabel("Jeff Mulavarikkal - Lager");
        jeffLabel.setForeground(new Color(247, 213, 21));
        jeffLabel.setOpaque(true);
        jeffLabel.setBackground(new Color(3, 81, 186));
        JLabel charlotteLabel = new JLabel("Charlotte Müller - Marketing");
        charlotteLabel.setForeground(new Color(247, 213, 21));
        charlotteLabel.setOpaque(true);
        charlotteLabel.setBackground(new Color(3, 81, 186));
        
        // Fügt ein Bild von Timo ein
        Image timoBild = null;
            try {
                timoBild = ImageIO.read(new File("timo.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_timo = new ImageIcon(timoBild);
            JLabel label_bild_timo = new JLabel(bild_timo);
        
        // Fügt ein Bild von Florianne ein
        Image florianneBild = null;
            try {
                florianneBild = ImageIO.read(new File("florianne.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_florianne = new ImageIcon(florianneBild);
            JLabel label_bild_florianne = new JLabel(bild_florianne);
        
        // Fügt ein Bild von Tim ein
        Image timBild = null;
            try {
                timBild = ImageIO.read(new File("tim.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_tim = new ImageIcon(timBild);
            JLabel label_bild_tim = new JLabel(bild_tim);
        
        // Fügt ein Bild von Jeff ein
        Image jeffBild = null;
            try {
                jeffBild = ImageIO.read(new File("jeff.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_jeff = new ImageIcon(jeffBild);
            JLabel label_bild_jeff = new JLabel(bild_jeff);
        
        // Fügt ein Bild von Charlotte ein
        Image charlotteBild = null;
            try {
                charlotteBild = ImageIO.read(new File("charlotte.png"));
            }
            catch (Exception f)
            {
                f.printStackTrace();
                System.exit(1);
            }
            ImageIcon bild_charlotte = new ImageIcon(charlotteBild);
            JLabel label_bild_charlotte = new JLabel(bild_charlotte);
        
        // Positionieren
        teamLabel.setBounds(50, 50, 1500, 35);
        timoLabel.setBounds(150, 500, 400, 30);
        florianneLabel.setBounds(650, 500, 400, 30);
        timLabel.setBounds(1150, 500, 400, 30);
        jeffLabel.setBounds(405, 825, 400, 30);
        charlotteLabel.setBounds(905, 825, 400, 30);
        
        label_bild_timo.setBounds(1, 200, 700, 300);
        label_bild_florianne.setBounds(500, 200, 700, 300);   
        label_bild_tim.setBounds(1000, 200, 700, 300);
        label_bild_jeff.setBounds(250, 550, 700, 300);
        label_bild_charlotte.setBounds(750, 550, 700, 300);
        
        teamLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        timoLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        florianneLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        timLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        jeffLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        charlotteLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        
        // Zum Panel hinzufügen
        this.add(teamLabel);
        this.add(timoLabel);
        this.add(florianneLabel);
        this.add(timLabel);
        this.add(jeffLabel);
        this.add(charlotteLabel);
        this.add(label_bild_timo);
        this.add(label_bild_florianne);
        this.add(label_bild_tim);
        this.add(label_bild_jeff);
        this.add(label_bild_charlotte);
        
        // Sichtbar machen
        this.setVisible(true);
    }
    
    /**
     * 'Geschichte'-Funktion: Zeigt einen Überblick über die Geschichte von AEKI
     */
    private void geschichte() {
        this.getContentPane().removeAll();
        this.repaint();
        
        JFrame frame = new JFrame("Geschichte");
        JLabel titelgeschichteLabel = new JLabel("Die Geschichte von AEKI.");
        JLabel geschichte1Label = new JLabel("<html>Wir sind schon weit gekommen, seitdem Jonathan Gruss 1943 gegründet hat. Vom kleinen schwedischen Unternehmen, das seine Waren über einen Versandkatalog verkauft hat, ist AEKI zu einem der bekanntesten Einrichtungsmarken der Welt geworden. Heute gibt es hunderte AEKI Einrichtungshäuser rund um den Erdball und es werden noch mehr. Hier finden Sie mehr über unsere faszinierende Geschichte – von den Anfängen bis hin zu unseren heutigen Tätigkeiten.</html>");
        JLabel untertitelgeschichteLabel = new JLabel("Wohnst du noch oder lebst du schon?");
        JLabel geschichte2Label = new JLabel("<html> Unsere Faszination vom Leben zu Hause zeichnet AEKI aus. Jedes Jahr bitten wir Tausende Menschen, uns ihre Gedanken und Emotionen über den Ort mitzuteilen, an dem sie leben. Wir möchten erfahren, was ihr Leben zu Hause besser macht, damit wir Sie dabei unterstützen können. Es ist ein andauerndes Forschungsprojekt, das wichtiger denn je ist </html>");
        
        // Positionieren
        titelgeschichteLabel.setBounds(50, 25, 1000, 25);
        geschichte1Label.setBounds(50, 50, 1000, 100);
        untertitelgeschichteLabel.setBounds(50, 180, 1000, 25);
        geschichte2Label.setBounds(50, 190, 1000, 100);
        
        titelgeschichteLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        geschichte1Label.setFont(new Font("Serif", Font.PLAIN, 18));
        untertitelgeschichteLabel.setFont(new Font("Serif", Font.PLAIN, 24));
        geschichte2Label.setFont(new Font("Serif", Font.PLAIN, 18));

        titelgeschichteLabel.setForeground(new Color(247, 213, 21));
        titelgeschichteLabel.setOpaque(true);
        titelgeschichteLabel.setBackground(new Color(3, 81, 186));
        untertitelgeschichteLabel.setForeground(new Color(247, 213, 21));
        untertitelgeschichteLabel.setOpaque(true);
        untertitelgeschichteLabel.setBackground(new Color(3, 81, 186));
        frame.setSize(600,300);
        
        // Zum Panel hinzufügen
        frame.add(geschichte1Label);
        this.add(titelgeschichteLabel);
        this.add(geschichte1Label);
        this.add(untertitelgeschichteLabel);
        this.add(geschichte2Label);
        
        // Sichtbar machen
        this.setVisible(true);
    }
    
    /**
     * 'Info'-Funktion: Zeigt Informationen zur Anwendung
     */
    private void zeigeInfo() {
        this.getContentPane().removeAll();
        this.repaint();
        
        JLabel infoLabel = new JLabel("<html>Bitte wenden Sie sich bei Fragen an unseren IT-Kontakt Tim, der unter der folgenden Nummer täglich von 8:00 - 16:00 Uhr erreichbar ist: 0824 67 76</html>");
        
        //hier noch GIF einfügen.
        // Jeff: Kannst du hier evtl helfen? Wenn nicht, machen wir einfach ein Bild draus. :)
        /*URL urlsd;
        try {
            URL urlsd = new URL("https://i.gifer.com/PJxF.gif");
            ImageIcon gif_help = new ImageIcon(urlsd);
            JLabel label_gif_help = new JLabel(gif_help);
        } 
        catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }
        ImageIcon gif_help = new ImageIcon(urlsd);
        JLabel label_gif_help = new JLabel(gif_help);*/
        
        // Positionieren
        infoLabel.setBounds(50, 25, 800, 50);
        //label_gif_help.setBounds(5, 20, 66, 66);
        infoLabel.setFont(new Font("Serif", Font.PLAIN, 18));
        
        // Zum Panel hinzufügen
        this.add(infoLabel);
        //this.add(label_gif_help);
        
        // Sichtbar machen
        this.setVisible(true);
    }
    
    /**
     * 'anwendungBeenden'-Funktion: Durch diese wird die Anwendung jeweils beendet
     */
    private void anwendungBeenden() {
        System.exit(0);
    }
    
    }
