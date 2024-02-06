package enstabretagne.helloworld.Etudiant;

import enstabretagne.engine.InitData;

//extension du mod�le d'initiation d'une entit� pour en faire un mod�le d'initialisation d'un �tudiant
public class InitEtudiant extends InitData {
    public final Genre genre;
    public final Film filmPrefere;

    public InitEtudiant(String name, Genre genre, Film filmPrefere) {
        super(name);
        this.genre = genre;
        this.filmPrefere = filmPrefere;
    }


}
