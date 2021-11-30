package com.sharingnotes.Model;

import java.util.UUID;

public class Recensione {

    private UUID _id;
    private int score=0;
    private String idRecensore;
    private String idUserRecensito;
    private String idNotaRecensita;
    private String title;
    private String testo;

    /***
     * Costruttore del Recensione Model
     * @param _id
     * @param idRecensore
     * @param idUserRecensito
     * @param idNotaRecensita
     * @param title
     * @param testo
     */
    public Recensione(UUID _id, String idRecensore, String idUserRecensito, String idNotaRecensita, String title, String testo) {
        this._id = _id;
        this.score=0;
        this.idRecensore = idRecensore;
        this.idUserRecensito = idUserRecensito;
        this.idNotaRecensita = idNotaRecensita;
        this.title = title;
        this.testo = testo;
    }
    
    public UUID getId() {
        return _id;
    }

    public void setId(UUID id) {
        this._id = id;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getIdRecensore() {
        return idRecensore;
    }

    public void setIdRecensore(String idRecensore) {
        this.idRecensore = idRecensore;
    }

    public String getIdUserRecensito() {
        return idUserRecensito;
    }

    public void setIdUserRecensito(String idUserRecensito) {
        this.idUserRecensito = idUserRecensito;
    }

    public String getIdNotaRecensita() {
        return idNotaRecensita;
    }

    public void setIdNotaRecensita(String idNotaRecensita) {
        this.idNotaRecensita = idNotaRecensita;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTesto() {
        return testo;
    }

    public void setTesto(String testo) {
        this.testo = testo;
    }
}
