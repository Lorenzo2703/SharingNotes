import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { MatDialog } from '@angular/material/dialog';
import { NewReviewComponent } from '../new-review/new-review.component';
import { NewScoreComponent } from '../new-score/new-score.component';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss']
})
export class NoteComponent implements OnInit, DoCheck {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, public dialog: MatDialog, private ajaxService: AjaxService) { }

  listReviews = []; //lista recensioni relative alla nota
  nameUserReview = []; //nome di chi ha fatto la recnesione
  note = {
    color: "",
    description: "",
    fileUrl: "",
    id_User: "",
    rating: 0,
    title: "",
    _id: ""
  }

  ngDoCheck(): void {
    if (this.note._id === "") {
      this.getParam();
    }
  }

  openDialog() {
    //apre new-review component
    const dialogRef = this.dialog.open(NewReviewComponent);
  }

  openScoreDialog(){
    //apre new-score component
    const dialogRef = this.dialog.open(NewScoreComponent);
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
  }

  getNotesReview() {
    //salvo tutte le recensioni relative alle note
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Nota_Recensita"] == this.note._id) {
          //salvo le recensioni nella lista
          this.listReviews.push(res[x]);
          /* salvo i nomi dei recensori
          avrò in questo modo una correlazioni tra nome del recensore e recensione grazie alla posizione */
          this.ajaxService.getUserByID(res[x]["id_Recensore"]).subscribe(user => {
            this.nameUserReview.push(user["name"])
          })
        }
      }
    })
  }

  ngOnInit(): void {
    this.getParam();
    this.getNotesReview();
  }

}
