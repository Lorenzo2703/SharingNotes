import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(private ajaxService: AjaxService, private data: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  listNotes = [];
  noNotes = false;

  ngOnInit(): void {
    this.getUserNotes();
  }

  getUserNotes() {
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
