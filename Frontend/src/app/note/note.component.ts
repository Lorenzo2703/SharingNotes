import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { MatDialog } from '@angular/material/dialog';
import { NewReviewComponent } from '../new-review/new-review.component';
import { NewScoreComponent } from '../new-score/new-score.component';
import { AjaxService } from '../ajax.service';
import Swal from 'sweetalert2';
//import { filesaver } from "file-saver";

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss']
})
export class NoteComponent implements OnInit, DoCheck {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private data: DataService, public dialog: MatDialog, private ajaxService: AjaxService) { }

  listReviews = []; //lista recensioni relative alla nota
  note = {
    color: "",
    description: "",
    fileUrl: "",
    id_User: "",
    rating: 0,
    title: "",
    _id: ""
  }
  name;
  idUser = sessionStorage.getItem("UserID");

  ngDoCheck(): void {
    if (this.note._id === "") {
      this.getParam();
    }
  }

  openDialog() {
    //apre new-review component
    if (sessionStorage.getItem('UserID') == ""||sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      const dialogRef = this.dialog.open(NewReviewComponent);
    }
  }

  openScoreDialog() {
    //apre new-score component
    if (sessionStorage.getItem('UserID') == ""||sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      const dialogRef = this.dialog.open(NewScoreComponent);
    }
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //prendo l'id della nota dall'url
      let id = params['_id'];
      //scorro la lista dei documenti e salvo quello che ho selezionato
      this.data.listNotes.forEach(element => {
        if (element._id == id) {
          this.note = element;
        }
      });
    });
    sessionStorage.setItem("IDUserNota", this.note.id_User);
    sessionStorage.setItem("IDNota", this.note._id);
    this.ajaxService.getUserByID(this.note.id_User).subscribe(user => {
      this.name = user["name"];
    })
  }

  getNotesReview() {
    //salvo tutte le recensioni relative alle note
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Nota_Recensita"] == this.note._id) {
          
          /* salvo i nomi al posto dell'id del recendore */
          this.ajaxService.getUserByID(res[x]["id_Recensore"]).subscribe(user => {
            res[x]["id_Recensore"] =  user["name"]
          })
          this.listReviews.push(res[x]);
        }
      }
    })
  }

  download() {
    //funzione di download dei file con api di mega
    /* this.ajaxService.download(this?.note?.fileUrl).subscribe(res => {
       console.log(res);
 
       this.downloadURI(res, "document.pdf");
     });
     */

  }

  downloadURI(uri, name) {
    var link = document.createElement("a");
    link.download = name;
    link.href = "data:application/pdf;base64," + uri;
    link.click();
    link.remove();
  }

  ngOnInit(): void {
    this.getParam();
    this.getNotesReview();
  }

}
