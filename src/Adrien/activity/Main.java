import java.util.GregorianCalendar;

public class Main {
    public static void main(String[] args) {        // Objets
        Activity options = new Activity ("Choisir mes options", 70);
        Activity ip = new Activity ("Inscription pédagogique", 30);
        PrecedenceConstraint contrainte = new PrecedenceConstraint (options, ip);
        GregorianCalendar date_1 = new GregorianCalendar(2012, 11, 31, 20, 0, 0);
        GregorianCalendar date_2 = new GregorianCalendar(2013, 0, 1, 6, 0, 0);
        GregorianCalendar date_3 = new GregorianCalendar(2013, 0, 1, 7, 0, 0);

// Test avec une programmation censée satisfaire la contrainte
        if ( ! contrainte.isSatisfied(date_1, date_3) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte est satisfaite.");
        } else {
            System.out.println("Mon programme passe le premier test avec succès.");
        }

// Test avec une programmation censée ne pas satisfaire la contrainte
        if ( contrainte.isSatisfied(date_2, date_1) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le deuxième test avec succès.");
        }

// Test avec une programmation censée ne pas satisfaire la contrainte (car la première
// activité finirait après le début de la seconde)
        if ( contrainte.isSatisfied(date_2, date_1 ) ) {
            System.out.println("Mon programme ne marche pas.");
            System.out.println("Il aurait dû trouver que la contrainte n'est pas satisfaite.");
        } else {
            System.out.println("Mon programme passe le troisième test avec succès.");
        }
    }
}
