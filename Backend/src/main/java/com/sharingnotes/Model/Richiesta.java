package com.sharingnotes.Model;

import java.util.UUID;

public class Richiesta {
    private UUID _id;
    private String idRichiedente;
    private String title;
    private String testo;
    private boolean completed=false;

    /***
     * Costruttore del Richiesta Model
     * @param _id
     * @param idRichiedente
     * @param title
     * @param testo
     */
    public Richiesta(UUID _id, String idRichiedente, String title, String testo) {
        this._id = _id;
        this.idRichiedente = idRichiedente;
        this.title = title;
        this.testo = testo;
        this.completed=false;
    }

    public UUID get_id() {
        return _id;
    }

    public void set_id(UUID _id) {
        this._id = _id;
    }

    public String getIdRichiedente() {
        return idRichiedente;
    }

    public void setIdRichiedente(String idRichiedente) {
        this.idRichiedente = idRichiedente;
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

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
