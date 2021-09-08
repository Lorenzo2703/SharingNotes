import { Component, DoCheck, OnInit } from '@angular/core';
import { DataService } from '../data.service';

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

  ngDoCheck(): void {
    this.searchText = this.dataService?.searchText;
    this.listNotes = this.dataService?.listNotes;
  }

  searchText;
  listNotes = [];

  ngOnInit(): void {

  }

}
