public class Fahrzeug extends Object {
    // public       = Alle
    // privat       = Nur ich selbst (Klasse)
    // protected    = Meine Nachfahren und ich

    protected String wasBinIch = "Fahrzeug";

    public void fahren() {
        System.out.println(wasBinIch +  " fährt!");
    }

    @Override
    public String toString() {
        return wasBinIch +  " fährt!";
    }
}
