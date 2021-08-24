import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { MatDialog } from '@angular/material/dialog';
import { NewReviewComponent } from '../new-review/new-review.component';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss']
})
export class NoteComponent implements OnInit, DoCheck {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, public dialog: MatDialog, private ajaxService: AjaxService) { }

  listReviews = [];
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
    const dialogRef = this.dialog.open(NewReviewComponent);
  }



  getParam() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['_id'];
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
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Nota_Recensita"] == this.note._id) {
          this.listReviews.push(res[x]);
        }
      }
    })
  }

  ngOnInit(): void {
    this.getParam();
    this.getNotesReview();
  }

}
