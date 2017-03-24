package com.apimdb.domain;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Matheus on 19/03/2017.
 */

public class Filme {

    private String Title;
    private String Plot;
    private String Year;
    private String Rated;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Actors;
    private String Language;
    private String imdbID;
    private String imdbRating;
    private String Writer;
    private String Poster;
    private Bitmap imagem;

    public Filme(String title , String plot, String year, String director, String actors, String genre, String runtime, String rated, String released, String imdbid, String imdbrating, String language, Bitmap imagem) {
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
    public Filme(){}

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        this.Plot = plot;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public Bitmap getImagem() {
        return imagem;
    }

    public void setImagem(Bitmap imagem) {
        this.imagem = imagem;
    }

    public ArrayList<Filme> getLista(){
        ArrayList<Filme> lista = new ArrayList<Filme>();
        Filme novo;

        novo = new Filme();
        novo.setTitle("Harry Potter");
        novo.setPlot("Harry...");
        lista.add(novo);

        novo = new Filme();
        novo.setTitle("Fast and Furious");
        novo.setPlot("Brian O'Conner...");
        lista.add(novo);

        novo = new Filme();
        novo.setTitle("The Interview");
        novo.setPlot("Na Coreia do Sul...");
        novo.setWriter("James Franco");
        novo.setActors("James Franco, Seth Rogan");

        lista.add(novo);
        return lista;
    }
}
