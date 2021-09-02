import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-new-score',
  templateUrl: './new-score.component.html',
  styleUrls: ['./new-score.component.scss']
})
export class NewScoreComponent implements OnInit {

  rating: number = 0;
  starCount: number = 5;
  color: string = 'accent';

  constructor(private data: DataService, private ajax: AjaxService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;
  ratingArr = [];
  nota;
  initForm() {
    this.form = new FormGroup({});
  }

  submit() {
    //recupero la mia nota da votare
    this.data.listNotes.forEach(nota => {
      if (nota._id == sessionStorage.getItem("IDNota")) {
        this.nota= nota.toString()
      }
    })
    this.ajax.updateScore(this.rating, this.nota).subscribe((res) => {
      this.dialogRef.close();
    });
  }

  ngOnInit(): void {
    this.initForm();
    //quante stelle utilizzare
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }
  }

  onClick(rating: number) {
    //aggiorno il rating
    this.rating = rating
    return false;
  }

  showIcon(index: number) {
    //per saper se far vedere la stella piena o vuota in base al rating
    if (this.rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }

  }

}
