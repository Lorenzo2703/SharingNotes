import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';

@Component({
  selector: 'app-profile-note',
  templateUrl: './profile-note.component.html',
  styleUrls: ['./profile-note.component.scss']
})
export class ProfileNoteComponent implements OnInit {
  
  constructor(private ajaxService: AjaxService, private data: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  listNotes = []; //lista delle note pubblicate dall'utente
  noNotes = false; //boolean per verificare che ci siano note pubblicate dall'user

  ngOnInit(): void {
    this.getUserNotes();
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
