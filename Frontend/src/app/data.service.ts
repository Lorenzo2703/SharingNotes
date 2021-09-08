import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  searchText = "";
  listColor = [{ color: "border-left-dark" }, { color: "border-left-success" }, { color: "border-left-secondary" }, { color: "border-left-primary" }, { color: "border-left-danger" }, { color: "border-left-warning" }, { color: "border-left-info" }];
  listNotes = [];
  listReviews = [];
  listChats = [];
  listUsers= [];
  listGroupChat = [];
  listCategorie = ["Matematica", "Fisica", "Beni Culturali e Storia dell'arte", "Informatica", "Ingegneria", "Chimica, Biologia, Biotecnologie", "Agraria, Geologia, Scienze dei materiali", "Scienze della Nutrizione, Metaboliche e Neuroscienze", "Lettere", "Giurisprudenza", "Storia e Filosofia", "Medicina e Farmacia", "Economia", "Architettura", "Lingue Straniere", "Psicologia", "Scienze Umane, Politiche e Sociali", "Scienze motorie" ]
}
