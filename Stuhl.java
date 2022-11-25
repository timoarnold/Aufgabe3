
/**
 * @author Gruppe 29 
 * @version 3.1 (4. Dezember 2022)
 * 
 * Die Klasse Stuhl bildet eine Erweiterung / Subklasse der Klasse Produkt. 
 * Sie erbt somit die Funktionalitäten sowie die globalen Variabeln der Klasse Produkt.
 * Zudem enthält sie Variabeln, welche für den Produkttyp <Stuhl> einzigartig sind.
 */

public class Stuhl extends Produkt {
    /**
     * Instanzvariabeln: Materialien, die zur Herstellung eines Stuhl benötigt werden
     *
     * - holzeinheiten: Anzahl Holzeiheiten, welche zur Herstellung eines Stuhls benötigt werden (int).
     * - schrauben: Anzahl Schrauben, welche zur Herstellung eines Stuhls benötigt werden (int).
     * - farbeinheiten: Anzahl Farbeinheiten, welche zur Herstellung eines Stuhls benötigt werden (int).
     * - kartoneinheiten: Anzahl Kartoneinheiten, welche zur Herstellung eines Stuhls benötigt werden (int).
     * - produktionsZeit: Produktionszeit in Stunden, die zur Produktion eines Stuhls nötig ist (int).
     */ 
    private static int holzeinheiten = 2;
    private static int schrauben = 10;
    private static int farbeinheiten = 2;
    private static int kartoneinheiten = 1;
    private static int produktionsZeit = 22;

    public static final int HOLZARBEIT_ZEIT = 10;
    public static final int MONTAGE_ZEIT = 5;
    public static final int LACKIER_ZEIT = 2;
    public static final int VERPACKUNG_ZEIT= 5;
    /**
     * Konstruktor für Objekte der Klasse Stuhl.
     * Mit super(1) wird "Zustand" aus der Superklasse Produkt initialisiert
     */
    public Stuhl() {
        super (1);
    }
    
    /**
     * Gib die Anzahl Holzeinheiten wieder.
     * @return die Anzahl benötigter Holzeinheiten für die Produktion eines Stuhls
     */
    public static int getHolzeinheiten() {
        return holzeinheiten;
    }
    
    /**
     * Gib die Anzahl Schrauben wieder.
     * @return die Anzahl benötigter Schrauben für die Produktion eines Stuhls
     */
    public static int getSchrauben() {
        return schrauben;
    }
    
    /**
     * Gib die Anzahl Farbeinheiten wieder.
     * @return die Anzahl benötigter Farbeinheiten für die Produktion eines Stuhls
     */
    public static int getFarbEinheiten() {
        return farbeinheiten;
    }
    
    /**
     * Gib die Anzahl Kartoneinheiten wieder.
     * @return die Anzahl benötigter Kartoneinheiten für die Produktion eines Stuhls
     */
    public static int getKartoneinheiten() {
        return kartoneinheiten;
    }
    
    /**
     * Gib die Produktionszeit wieder.
     * @return die Produktionszeit für einen Stuhl
     */
    public static int getProduktionsZeit() {
        return produktionsZeit;
    }
}
