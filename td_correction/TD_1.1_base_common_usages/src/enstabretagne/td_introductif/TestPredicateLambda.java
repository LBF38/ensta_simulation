package enstabretagne.td_introductif;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class TestPredicateLambda {
    public enum Couleur {
        Rouge, Bleu, Vert, Jaune
    }

    Couleur c;
    String nom;

    public TestPredicateLambda(String nom, Couleur c) {
        this.c = c;
        this.nom = nom;
    }

    /*
     * Cr�ation d'une fonction statique g�n�rique qui prend en entr�e:
     * - une liste d'un type quelconque
     * - un pr�dicat c'est � dire une fonction qui prend en param�tre un objet du m�me type et renvoie un boolean en retour
     *
     * la fonction parcourt la liste qui lui est donn�e en entr�e et applique le pr�dicat qui lui permet de savoir si l'objet est � retenir ou pas dans le cadre du filtrage
     */
    public static <T> List<T> extract(List<T> l, Predicate<T> p) {
        List<T> extraction;
        extraction = new ArrayList<>();
        for (T t : l) {
            if (p.test(t)) extraction.add(t);
        }

        return extraction;
    }

    public static void main(String[] args) {
        List<TestPredicateLambda> l;
        l = new ArrayList<>();

        l.add(new TestPredicateLambda("Canard", Couleur.Bleu));
        l.add(new TestPredicateLambda("Lion", Couleur.Rouge));
        l.add(new TestPredicateLambda("Girafe", Couleur.Rouge));
        l.add(new TestPredicateLambda("Serpent", Couleur.Vert));
        l.add(new TestPredicateLambda("Canari", Couleur.Jaune));

        List<TestPredicateLambda> extraction;
        /*
         * utilisation du formalisme Lambda pour exprimer la fonction Predicate<T>.
         * Le compilateur induit T en voyant que le type de retour doit �tre List<TestPredicateLambda> et donc T = TestPredicateLambda
         */
        extraction = extract(l, e -> e.c == Couleur.Rouge);

        /*
         * utilisation d'une autre fonction Lambda cette fois de type Consumer<T> c'est � dire une fonction qui prend un param�tre objet de type T
         * mais qui ne renvoie rien. Elle ne fait que consommer. Ici nous affichons le nom de l'objet.
         * L� encore T est induit en TestPredicateLambda
         */
        extraction.forEach(e -> System.out.println(e.nom));


    }


}
