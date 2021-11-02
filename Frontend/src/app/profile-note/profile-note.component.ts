import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-note',
  templateUrl: './profile-note.component.html',
  styleUrls: ['./profile-note.component.scss']
})
export class ProfileNoteComponent implements OnInit {

  constructor(private ajaxService: AjaxService, private router: Router, private data: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  listNotes = []; //lista delle note pubblicate dall'utente
  noNotes = false; //boolean per verificare che ci siano note pubblicate dall'user

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "") {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getUserNotes();
    }
  }

  getUserNotes() {
    //salvo tutte le note pubblicate dall'utente
    this.ajaxService.getNotes().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_User"] == this.idUser) {
          this.listNotes.push(res[x]);
        }
      }
      if (this.listNotes.length == 0) {
        this.noNotes = true;
      }
    })
  }

}
