import { Component, DoCheck, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, DoCheck {


  constructor(private activatedRoute: ActivatedRoute, private dataService: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  categories = this.dataService.listCategorie;
  form;

  ngDoCheck(): void {
    this.searchText = this.dataService?.searchText;
    if (this.listNotes.length === 0) {
      this.getParam();
    }
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //prendo la categoria dall'url
      let categoria = params['category'];
      //svuoto l'array dalle precedenti note in modo che non ci siano ripetizioni
      this.listNotes = [];
      //non ho specificato la categoria allora le vedo tutte di default
      if (categoria == undefined) {    
        this.listNotes = this.dataService?.listNotes;
      } else {
        //altrimenti vedo solo le note di quella determinata categoria
        for (let x in this.dataService.listNotes) {
          if (this.dataService?.listNotes[x]["categoria"] == categoria) {
            this.listNotes.push(this.dataService?.listNotes[x])
          }
        }
      }
    });
  }


  initForm() {
    this.form = new FormGroup({
      category: new FormControl(""),
    });
  }

  searchText;
  listNotes = [];

  ngOnInit(): void {
    console.log(this.dataService.listNotes)
    this.initForm();
    this.getParam();

  }

}
