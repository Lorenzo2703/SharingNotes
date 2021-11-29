import { Component, DoCheck, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';
import { MatDialog } from '@angular/material/dialog';
import { NewNoteComponent } from '../new-note/new-note.component';
import { Router } from '@angular/router';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, DoCheck {

  constructor(private ajaxService: AjaxService, private router: Router, private dataservice: DataService, public dialog: MatDialog) { }

  ngDoCheck(): void {
    this.dataservice.searchText = this.searchText;

  
  }

  listNotes = []; //lista delle note 
  listReviews = []; //lista delle recensioni
  listChats = []; //lista delle chat
  listUsers = []; //lista degli utenti
  listGroupChat = []; //lista delle chat di gruppo
  searchText;
  userID = sessionStorage.getItem("UserID")

  i = 0;

  ngOnInit(): void {
    //riempio le liste iniazializzate sopra richiamando i metodi dall'ajax
    this.getNotes();
    this.getReview();
    this.getChat();
    this.getUser();
    this.getGroupChat();

  }




  openDialog() {
    //apro il componente new-note
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      const dialogRef = this.dialog.open(NewNoteComponent);
    }
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

  getGroupChat() {

    this.ajaxService.getGroupChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.listGroupChat.push(res[x]);

      }
      this.dataservice.listGroupChat = this.listGroupChat;
    })
  }

  getUser() {
    this.ajaxService.getUser().subscribe((res) => {
      for (let x in res) {
        this.listUsers.push(res[x]);

      }
      this.dataservice.listUsers = this.listUsers;
    })
  }


  logOut() {
    Swal.fire({ title: "Good Bye 👋", icon: 'success', position: "center" });
    sessionStorage.setItem('UserName', "");
    sessionStorage.setItem('UserID', "");
    sessionStorage.setItem('UserRating', "");
  }

}
