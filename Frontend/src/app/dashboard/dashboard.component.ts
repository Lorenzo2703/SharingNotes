import { Component, DoCheck, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit, DoCheck {


  constructor(private dataService: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  categories = this.dataService.listCategorie;
  form;

  ngDoCheck(): void {
    this.searchText = this.dataService?.searchText;
    this.listNotes = this.dataService?.listNotes;
  }

  
  initForm() {
    this.form = new FormGroup({
      category: new FormControl( "" ),
    });
  }

  searchText;
  listNotes = [];

  ngOnInit(): void {
    this.initForm();

  }

}
