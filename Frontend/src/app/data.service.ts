import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor() { }

  searchText = "";
  listColor = [{ color: "border-left-dark" }, { color: "border-left-success" }, { color: "border-left-secondary" }, { color: "border-left-primary" }, { color: "border-left-danger" }, { color: "border-left-warning" }, { color: "border-left-info" }];
  listNotes = [];
}
