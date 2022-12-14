import java.util.ArrayList;

/**
 * @author Gruppe 29
 * @version 3.1 (4. Dezember 2022)
 * Die Klasse Bestellung verwaltet die Array-Liste, in der alle über die Fabrik bestellten
 * Produkte gespeichert werden. Dies können Sofas oder Stühle sein.
 */

public class Bestellung {
    /**
     * Instanzvariablen:
     *
     * - Liste bestellteProdukte: Liste aller Produkte, die bestellt wurden.
     * - bestellBestaetigung: Indikator, ob eine Bestellung erfolgreich bestätigt wurde oder nicht (boolean).
     * - beschaffungsZeit: Beschaffungszeit (in Tagen) für die Produkte (int).
     * - lieferZeit: Lieferzeit (in Tagen) für die jeweiligen Bestellungen (float).
     * - bestellNummer: Nummer einer Bestellung bei Empfang (int).
     * - anzahlStuehle: Anzahl Stühle, die in einer Bestellung nachgefragt wurden (int).
     * - anzahlSofas: Anzahl Sofas, die in einer Bestellung nachgefragt wurden (int).
     * - alleProdukteProduziert: Indikator, ob alle Produktei einer Bestellung produziert worden sind (boolean).
     */

    //Intanzvariablen:
    private ArrayList<Produkt> bestellteProdukte;
    private boolean bestellBestaetigung;
    private int beschaffungsZeit;
    private float lieferZeit;
    private int bestellungsNr;
    private int anzahlStuehle;
    private int anzahlSofas;
    private boolean alleProdukteProduziert;

    /**
     * Konstruktor der Klasse Bestellung: initialisiert alle Instanzvariabeln der Klasse Bestellung.
     * Bei der Initialisierung der Klasse Bestellung wird auch gleichzeitig die ArrayList "bestellteProdukte" mit der Anzahl an bestellten Stühlen und Sofas aufgefüllt.
     *
     * @param anzahlSofas: Anzahl bestellter Sofas einer Bestellung.
     * @param anzahlStuehle: Anzahl bestellter Stühle einer Bestellung.
     */
    public Bestellung(int bestellungsNr, int anzahlSofas, int anzahlStuehle) {
        bestellteProdukte = new ArrayList<>();

        for (int i = 0; i < anzahlStuehle; i++) {
            bestellteProdukte.add(new Stuhl(bestellungsNr));
        }

        for (int i = 0; i < anzahlSofas; i++) {
            bestellteProdukte.add(new Sofa(bestellungsNr));
        }

        bestellBestaetigung = false;
        beschaffungsZeit = 0;
        this.bestellungsNr = bestellungsNr;
        this.anzahlStuehle = anzahlStuehle;
        this.anzahlSofas = anzahlSofas;
    }

    /**
     * Bestätigt die Bestellung (true).
     *
     * Anmerkung: Falls noch nicht bestätigt, bleibt diese Variabel 'false'.
     */
    public void bestellungBestaetigen() {
        bestellBestaetigung = true;
    }

    /**
     * Gib die bestellBestaetigung wieder.
     *
     * @return Bestellbestätigung
     */
    public boolean gibBestellBestaetigung() {
        return bestellBestaetigung;
    }

    /**
     * Setze die jeweilige Beschaffungszeit in Abstimmung mit den Lieferanten.
     *
     * @param beschaffungsZeit: Neu gesetzte Zahl für die Beschaffungszeit.
     */
    public void setzBeschaffungsZeit(int beschaffungsZeit) {
        this.beschaffungsZeit = beschaffungsZeit;
    }

    /**
     * Gib die Beschaffungszeit wieder.
     *
     * @return die aktuell gesetzte Beschaffungszeit
     */
    public int gibBeschaffungszeit() {
        return beschaffungsZeit;
    }

    /**
     * Gib die Bestellnummer wieder.
     *
     * @return Nummer einer Bestellung
     */
    public int gibBestellNummer() {
        return bestellungsNr;
    }

    /**
     * Gib die Liste der bestellten Produkte wieder.
     *
     * @return Liste der Produkte in der Bestellung
     */
    public ArrayList<Produkt> liefereBestellteProdukte() {
        return bestellteProdukte;
    }

    /**
     * Gib die Anzahl Stuehle wieder.
     *
     * @return die Anzahl Stühle in einer Bestellung
     */
    public int gibAnzahlStuehle() {
        return anzahlStuehle;
    }

    /**
     * Gib die Anzahl Sofas wieder.
     *
     * @return die Anzahl Sofas in einer Bestellung
     */
    public int gibAnzahlSofas() {
        return anzahlSofas;
    }

    /**
     * Wandelt unterschiedliche Typen in den Typ String um.
     *
     * @return die Bestellnummer, die Anzahl Stühle, die Anzahl Sofas sowie die zugehörige Lieferzeit in der Form des nachfolgend definierten Strings
     *
     * Anmerkung: Wandelt die Konsolenausgabe der Methode bestellungenAusgeben in die Form String um,
     * damit diese im Unit-Test auf Übereinstimmung getestet werden kann.
     */
    public String toString() {
        return "<html><br>Bestellnummer: <html>" + bestellungsNr +
                "<html><br>Sofas bestellt: <html>" + anzahlSofas +
                "<html><br>Stühle bestellt: <html>" + anzahlStuehle +
                "<html><br>Die Lieferzeit beträgt: <html>" + gibFormatierteLieferzeit();
    }

    /**
     * Setze die aktuelle Lieferzeit einer Bestellung.
     *
     * @param lieferZeit: Neu gesetzte Zahl für die Lieferzeit.
     */
    public void setzLieferZeit(float lieferZeit) {
        this.lieferZeit = lieferZeit;
    }

    /**
     * Gib die Lieferzeit einer Bestellung.
     *
     * @return Lieferzeit einer Bestellung
     */
    public float gibLieferZeit() {
        return lieferZeit;
    }

    /**
     * Berechnet die Lieferzeit einer Bestellung in Tagen, Stunden und Minuten.
     *
     * @return die berechnete Lieferzeit einer Bestellung in Tagen, Stunden und Minuten
     *
     * Anmerkung: Stunden werden jeweils aufgerundet, um dem Kunden keine zu kurze Lieferzeit zu versprechen.
     */
    public String gibFormatierteLieferzeit() {
        int tage = (int) lieferZeit;
        int stunden = (int) (lieferZeit % 1 * 24);
        int minuten = Math.round((lieferZeit % 1 * 24) % 1 * 60);
        return tage + " Tag(e) " + stunden + " Stunde(n) " + minuten + " Minute(n)";
    }

    /**
     * Methode, um zu bestätigen, dass alle Produkte einer Bestellung produziert worden sind
     */
    public void setzeAlleProdukteProduziert() {
        alleProdukteProduziert = true;
    }
}
