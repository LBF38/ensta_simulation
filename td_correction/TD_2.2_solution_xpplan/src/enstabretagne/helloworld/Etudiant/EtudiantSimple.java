package enstabretagne.helloworld.Etudiant;

import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.SimuEngine;

public class EtudiantSimple extends EntiteSimulee {


    public EtudiantSimple(SimuEngine engine, InitEtudiant ini) {
        super(engine, ini);

    }

    //on surcharge la m�thode init pour pouvoir faire l'effet "boule de neige" de l'�v�nementiel
    @Override
    public void init() {
        super.init();//ne pas oublier d'appeler l'init du dessus pour bien mettre l'�tat � jour
        Post(new Bonjour(Now().add(LogicalDuration.ofMinutes(15)), getInit().getName()));
    }

    @Override
    public String toString() {
        return getInit().getName();
    }

    //on surcharge la m�thode terminate()
    //elle reste vide car il n'y a pas de variables � remettre � zero
    //sur une entit� plus complexe, il faudrait:
    // vider les listes, mettre les r�f�rences � d'autres objets � null
    @Override
    public void terminate() {

    }
}
