import { Component, DoCheck, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';
import { MatDialog } from '@angular/material/dialog';
import { NewNoteComponent } from '../new-note/new-note.component';
import { Router } from '@angular/router';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, DoCheck {

  constructor(private ajaxService: AjaxService, private dataservice: DataService, public dialog: MatDialog) { }

  ngDoCheck(): void {
    this.dataservice.searchText = this.searchText;
  }

  listNotes = [];
  listReviews = [];
  listChats = [];
  searchText;
  i = 0;

  ngOnInit(): void {
    this.getNotes();
    this.getReview();
    this.getChat();
  }


  openDialog() {
    const dialogRef = this.dialog.open(NewNoteComponent);

  }

  getNotes() {
    this.ajaxService.getNotes().subscribe(res => {
      for (let x in res) {
        this.listNotes.push(res[x]);

        if (this.i == this.dataservice?.listColor?.length - 1) {
          this.i = 0;
        } else {
          this.i++;
        }

        this.listNotes[x].color = this.dataservice.listColor[this.i].color;
      }
      this.dataservice.listNotes = this.listNotes;
    })
  }

  getReview() {

    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        this.listReviews.push(res[x]);   
      
      if (this.i == this.dataservice?.listColor?.length - 1) {
        this.i = 0;
      } else {
        this.i++;
      }

      this.listReviews[x].color = this.dataservice.listColor[this.i].color;
    }
    this.dataservice.listReviews = this.listReviews;
    })
  }

  getChat() {

    this.ajaxService.getAllChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.listChats.push(res[x]);   
      
      if (this.i == this.dataservice?.listColor?.length - 1) {
        this.i = 0;
      } else {
        this.i++;
      }

      this.listChats[x].color = this.dataservice.listColor[this.i].color;
    }
    this.dataservice.listChats = this.listChats;
    })
  }

}
