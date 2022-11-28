import java.awt.*;
import java.util.HashMap;
import java.util.LinkedList;
/**
 * @author Gruppe 29
 * @version 3.1 (4. Dezember 2022)
 *
 * Die Klasse Produktions_Manager arbeitet neu eintreffende Bestellung ab und leitet diese den Robotern zur Produktion weiter.
 * Sie wird als Thread implementiert, damit sie immer wieder neu eintreffende Bestellungen abarbeiten und den Robotern zum Produzieren geben kann.
 *
 */

public class Produktions_Manager extends Thread {
   /**
     * Instanzvariablen:
     * - holzRoboter
     * - montageRoboter
     * - lackierRoboter
     * - verpackungsRoboter
     * - meineFabrik
     * - meinLager
     * - zuVerarbeitendeBestellungen
     * - bestellungenInProduktion
     */
    
    private Holzbearbeitungs_Roboter holzRoboter;
    private Montage_Roboter montageRoboter;
    private Lackier_Roboter lackierRoboter;
    private Verpackungs_Roboter verpackungsRoboter;

    private Fabrik meineFabrik;
    private Lager meinLager;

    private LinkedList <Bestellung> zuVerarbeitendeBestellungen;
    private LinkedList <Bestellung> bestellungenInProduktion;

    /**
     * Konstruktor für die Klasse Produktionsmanager
     * Hier sind alle Roboter als Threads instanziert und werden gestartet.
     * Zwei LinkedLists wurden implementiert, um die zu verarbeitende Bestellungen und die Bestellungen in Produktion zu verwalten.
     */
    
    public Produktions_Manager(){
        zuVerarbeitendeBestellungen = new LinkedList<Bestellung>();
        bestellungenInProduktion = new LinkedList<Bestellung>();
                
        this.meineFabrik = meineFabrik;
        this.meinLager = meinLager;
        
        holzRoboter = new Holzbearbeitungs_Roboter();
        montageRoboter = new Montage_Roboter();
        lackierRoboter = new Lackier_Roboter();
        verpackungsRoboter = new Verpackungs_Roboter();
    
        holzRoboter.start();
        montageRoboter.start();
        lackierRoboter.start();
        verpackungsRoboter.start();
    }
    
    /**
     * Die sleep Methode lässt den Thread um die Zeit zeit schlafen
     * @param zeit, welche der Thread schlafen soll
     */
    public static void sleep(int zeit) {
        try {
            Thread.sleep(zeit);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * ANM Cha: umschreiben
     * Mit der Synchronisierungsmethode syncedPrintIn wird sichergestellt, 
     * dass nur ein Thread zu einem bestimmten Zeitpunkt auf die Ressource zugreifen kann.
     */
    public static void syncedPrintln(String message) {
        synchronized (System.out) {
            System.out.println(message);
        }
    }

    /**
     * Hier werden Bestellungen der Liste zuVerarbeitendeBestellungen hinzugefügt
     */
    public void fuegeZuVerarbeitendeBestellungenHinzu(Bestellung bestellung){
        this.zuVerarbeitendeBestellungen.add(bestellung);
    }

    /**
     * Die run Methode des Threats prüft immer wieder, ob eine neue Bestellung eingetroffen ist.
     * Bestellungen werden dann aus der Liste der zu verarbeitenden Bestellungen herausgenommen
     * und in die Liste der zu produzierenden Bestellungen (bestellungenInProduktion) gespeichert.
     * Wenn im Lager genügend Material vorhanden ist, wird somit die Produktion gestartet.
     * 
     * Zudem wird bei der Produktion geprüft, ob eine Bestellung abgeschlossen ist.
     * Wenn ja, wird die Bestellung von der zu produzierenden Bestellungen gelöscht.
     * Gleichzeitig wird  in der Klasse Bestellung festgehalten, dass die Produkte produziert und bereit auszuliefern sind.
     * 
     * Schliesslich soll der Thread um die Zeit zeit schlafen
     * @param zeit die der Thread schlafen soll.
     */

    public void run(){
        syncedPrintln("Produktionsmanager gestartet");
        while(true){
            Bestellung naechsteBestellung = zuVerarbeitendeBestellungen.poll();
            if(naechsteBestellung != null){
                bestellungenInProduktion.add(naechsteBestellung);
                starteProduktion(naechsteBestellung);
            }  
            for (Bestellung bestellung : bestellungenInProduktion){
                boolean alleProdukteProduziert = true;
                for (Produkt produkt : bestellung.liefereBestellteProdukte()){
                    if(produkt.aktuellerZustand()!=3){
                        alleProdukteProduziert = false;
                        break;
                    }
                }
                if(alleProdukteProduziert){
                    bestellungenInProduktion.remove(bestellung);
                    bestellung.setzeAlleProdukteProduziert();
                    syncedPrintln("Produktionsmanager beendet");
                    }
                }
            }
            // ANM Timo: Die Frage stellt sich jedoch weshalb der Produktionsmanager sleepen soll...
            // ANM Cha: ist im Diagramm vorgegeben. Ich glaube sonst läuft der Thread non stop, was wir nicht wollen
            sleep(1000);
    }
    }

    /**
     * Die folgenden beiden Methoden schienen mir nicht korrekt bennant bzw. auch nicht in Miro vorhanden
     * den Inhalt von "starte Produktion" habe ich von Übung 7 übernommen und übersetzt, denke aber, dieser sollte zu
     * einer neuen Methode "setzeProduktionsAblauf" benannt werden.
     * 
     * ANM Cha: ich finde es gut, die zwei Methoden zu kombinieren - wir hatten ja gesagt, die Übung 7 dient als Beispiel, deshalb super :)
     * in Miro steht im Text, dass es eine "starteProduktion" Methode braucht. Aber nicht explizit im Diagramm.
     * Ich würde sie nicht setzteProduktionsAblauf bennen, weil wir ja in Produkt schon eine solch benannte Methode haben?
     */

    /**
     * In der Methode starteProduktion wird jedem Produkt der Bestellung
     * die entsprechenden Roboter zugewiesen und die Produktion dadurch gestartet.
     */

   private void starteProduktion(Bestellung bestellung) {

       LinkedList<Roboter> produktionsAblauf = new LinkedList<>();
       HashMap<Roboter, Integer> produktionsZeit = new HashMap<>();

       for (Produkt produkt: bestellung) {
           if (produkt instanceof Stuhl) {
               produktionsAblauf.add(holzRoboter);
               produktionsZeit.put(holzRoboter, Stuhl.HOLZARBEIT_ZEIT);
               produktionsAblauf.add(montageRoboter);
               produktionsZeit.put(montageRoboter, Stuhl.MONTAGE_ZEIT);
               produktionsAblauf.add(lackierRoboter);
               produktionsZeit.put(lackierRoboter, Stuhl.LACKIER_ZEIT);
               produktionsAblauf.add(verpackungsRoboter);
               produktionsZeit.put(verpackungsRoboter, Stuhl.VERPACKUNG_ZEIT);
           }
           else if (produkt instanceof Sofa) {
               produktionsAblauf.add(holzRoboter);
               produktionsZeit.put(holzRoboter, Sofa.HOLZARBEIT_ZEIT);
               produktionsAblauf.add(lackierRoboter);
               produktionsZeit.put(lackierRoboter, Sofa.LACKIER_ZEIT);
               produktionsAblauf.add(montageRoboter);
               produktionsZeit.put(montageRoboter, Sofa.MONTAGE_ZEIT);
               produktionsAblauf.add(verpackungsRoboter);
               produktionsZeit.put(verpackungsRoboter, Sofa.VERPACKUNG_ZEIT);
           }
           produkt.setzeProduktionsAblauf(produktionsAblauf);
           produkt.setzeProduktionsZeit(produktionsZeit);
       }

   }
    
    /**
     * In der Methode alloziereRoboter werden für jedes Produkt die entsprechenden Roboter in der richtigen Reihenfolge festgelegt.
     * Die Information zur Reihenfolge wird im Produkt selbst gespeichert.
     */
   private void alloziereRoboter(Produkt produkt){
        LinkedList<Roboter> bearbeitungsReihenfolge = new LinkedList<Roboter>();
        // Cha: hier muss die Reihenfolge der Roboter festgelegt werden
        // if(){
            // if das Produkt Stuhl ist dann --> Reihenfolge der Roboter für Stuhl auflisten
        // }
        // else if(){
            // else if Produkt Sofa --> Reihenfolge der Roboter für Sofa auflisten
        //}
    }
}
