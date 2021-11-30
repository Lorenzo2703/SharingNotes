import { Component, DoCheck, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, DoCheck {


  constructor(private activatedRoute: ActivatedRoute, private ajaxService: AjaxService, private dataService: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  categories = this.dataService.listCategorie;
  form;
  searchText;
  i = 0;

  listNotes = [];

  ngDoCheck(): void {
    this.searchText = this.dataService?.searchText;
    if (this.listNotes.length === 0) {
      this.getParam();
    }

    if (this.dataService?.load) {
      this.dataService.load = false;
      setTimeout(() => { this.getNotes(); }, 3000);
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


  getNotes() {
    this.ajaxService.getNotes().subscribe(res => {
      this.listNotes = [];
      this.dataService.listNotes = this.listNotes;
      for (let x in res) {
        this.listNotes.push(res[x]);

        if (this.i == this.dataService?.listColor?.length - 1) {
          this.i = 0;
        } else {
          this.i++;
        }

        this.listNotes[x].color = this.dataService.listColor[this.i].color;
      }
      this.dataService.listNotes = this.listNotes;
    })
  }


  initForm() {
    this.form = new FormGroup({
      category: new FormControl(""),
    });
  }



  ngOnInit(): void {
    this.initForm();
    this.getParam();

  }

}
