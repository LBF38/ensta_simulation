package helloworld;

import engine.InitData;
import enstabretagne.base.logger.ToRecord;

public class EtudiantInitData extends InitData {
    public String movie;
    public MovieGenre genre;

    public EtudiantInitData(String movie, MovieGenre genre) {
        this.movie = movie;
        this.genre = genre;
    }

    @ToRecord(name = "favorite movie")
    public String getMovie() {
        return movie;
    }

    @ToRecord(name = "genre")
    public MovieGenre getGenre() {
        return genre;
    }
}
