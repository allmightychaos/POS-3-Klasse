import java.sql.SQLOutput;

public class Main {
    public static void main(String[] args) {
        Fahrzeug f = new Auto();
        f.fahren();

        Object o = f;
        System.out.println(o.toString());

        if (o.getClass().getName().equals("Auto")) {
            Fahrzeug g = (Fahrzeug) o;
            g.fahren();
        }

        if (o instanceof Fahrzeug) {
            Fahrzeug g = (Fahrzeug) o;
            g.fahren();
        }

        double x = 15.17;

        Double d = x;
        Object i = d;
        System.out.println(i);
    }
}