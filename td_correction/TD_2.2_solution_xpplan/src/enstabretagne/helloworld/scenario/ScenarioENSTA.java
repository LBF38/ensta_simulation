package enstabretagne.helloworld.scenario;

import enstabretagne.base.logger.Logger;
import enstabretagne.base.time.LogicalDateTime;
import enstabretagne.base.time.LogicalDuration;
import enstabretagne.engine.EntiteSimulee;
import enstabretagne.engine.Scenario;
import enstabretagne.engine.SimEvent;
import enstabretagne.engine.SimuEngine;
import enstabretagne.helloworld.Etudiant.EtudiantSimple;
import enstabretagne.helloworld.Etudiant.Film;
import enstabretagne.helloworld.Etudiant.Genre;
import enstabretagne.helloworld.Etudiant.InitEtudiant;

import java.util.List;

//sp�cialisation du concept de sc�nario
public class ScenarioENSTA extends Scenario {

    public ScenarioENSTA(SimuEngine engine, ScenarioEnstaInitData init) {
        super(engine, init);
        nbEtudiants = init.getEtudiants();
        Logger.Information(this, "constructeur", "nbetudiants=" + getEtudiants());
    }

    int totalEntities;

    @Override
    public void creerEntitesSimulees() {
        Logger.Information(this, "creerentite", "nbetudiants=" + getEtudiants());
        for (int i = 0; i < getEtudiants(); i++) {
            creerRandomEtudiantSimple(this, i);
            totalEntities++;
        }

    }

    //exemple de cr�ation d'un �tudiant al�atoire
    protected static EtudiantSimple creerRandomEtudiantSimple(Scenario s, int i) {
        //g�n�ration du genre de mani�re al�atoire
        Genre randomGenre;
        double d = s.getRandomGenerator().nextDouble();
        //on sait qu'il n'y a pas plus de 3 genres
        //on a suppos� qu'il �tait �quiprobable d'�tre d'un genre ou un autre
        if (d < 1 / 3) {
            randomGenre = Genre.Homme;
        } else if (d > 1 / 3 && d < 2 / 3) {
            randomGenre = Genre.Femme;
        } else {
            randomGenre = Genre.Autre;
        }

        //g�n�ration du film pr�f�r�
        //cette fois on part du principe que la liste des films est extensible
        //on g�n�re donc un nombre entier al�atoire entre 0 et le nombre de films list�s
        int randomFilmIndex = s.getRandomGenerator().nextInt(0, Film.values().length - 1);
        //je choisi le film � partir de l'index g�n�r� al�atoirement
        Film randomFilm = Film.values()[randomFilmIndex];

        return new EtudiantSimple(s.getEngine(), new InitEtudiant("Et" + i, randomGenre, randomFilm));

    }

    int nbEtudiants;

    public int getEtudiants() {
        return nbEtudiants;
    }

    @Override
    public void init() {
        super.init();
        //on initialise les entit�s
        //on peut avoir une strat�gie d'initialisation initiale complexe
        //ici elle est simple on initialise tous les �tudiants sans distinction
        //mais on pourrait initialiser selon le genre d'abord les hommes, puis les femmes puis les autres

        //on fait une requ�te au moteur
        List<EntiteSimulee> l = recherche(e -> {
            return e instanceof EtudiantSimple;
        });

        //on demande l'initialisation de ces entit�s
        for (EntiteSimulee e : l) {
            e.requestInit();
        }

        //on d�cide de faire une cr�ation diff�r�e des �tudiants
        //ceci est � but p�dagogique: on n'est pas oblig� de cr�er desuite les entit�s
        Post(new CreerEtudiant(getEngine().SimulationDate().add(LogicalDuration.ofMinutes(8)), "Olivier"));
    }

    public class CreerEtudiant extends SimEvent {

        String nom;

        public CreerEtudiant(LogicalDateTime d, String nom) {
            super(d);
            this.nom = nom;
        }

        //Exemple typique:
        // en tant qu'entit�, le sc�nario cr�e des entit�s filles
        //c'est au sc�nario de faire le requestInit()
        @Override
        public void process() {
            EtudiantSimple es = creerRandomEtudiantSimple(ScenarioENSTA.this, ++totalEntities);
            es.requestInit();
        }

    }

    @Override
    public void terminate() {
        super.terminate();
        totalEntities = 0;
        nbEtudiants = 0;
    }

}
