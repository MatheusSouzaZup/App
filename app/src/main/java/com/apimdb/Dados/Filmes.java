package com.apimdb.Dados;

import android.graphics.Bitmap;

/**
 * Created by Matheus on 16/03/2017.
 */

public class Filmes {
    private String Title;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Plot;
    private String Language;
    private String imdbID;
    private String imdbRating;
    private String Writer;
    private String Poster;
    private Bitmap imagem;



    public Filmes(String title , String plot, String year, String director, String actors, String genre, String runtime, String rated, String released, String imdbid, String imdbrating, String language, Bitmap imagem) {
        this.Title = title;
        this.Plot = plot;
        this.Year = year;
        this.Director = director;
        this.Actors = actors;
        this.Genre = genre;
        this.Runtime = runtime;
        this.Rated = rated;
        this.Released = released;
        this.imdbID = imdbid;
        this.imdbRating = imdbrating;
        this.Language = language;
        this.imagem = imagem;
    }

    public Filmes()
    {

    }

    public String getWriter() {return Writer;}

    public String getImdbRating(){return imdbRating;}

    public String getImdbID() {

        return imdbID;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public String getPoster() {
        return Poster;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getActors() {
        return Actors;
    }

    public String getDirector() {
        return Director;
    }

    public String getGenre() {
        return Genre;
    }

    public String getLanguage() {
        return Language;
    }

    public String getPlot() {
        return Plot;
    }

    public String getRated() {
        return Rated;
    }

    public String getReleased() {
        return Released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }
}
