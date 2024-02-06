package enstabretagne.helloworld;

import enstabretagne.engine.InitData;

public class InitEtudiant extends InitData {
    public final Genre genre;
    public final Film filmPrefere;

    public InitEtudiant(Genre genre, Film filmPrefere) {
        super();
        this.genre = genre;
        this.filmPrefere = filmPrefere;
    }


}
