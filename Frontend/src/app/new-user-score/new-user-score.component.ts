import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import { FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-new-user-score',
  templateUrl: './new-user-score.component.html',
  styleUrls: ['./new-user-score.component.scss']
})
export class NewUserScoreComponent implements OnInit {

  rating: number = 0;
  starCount: number = 5;
  color: string = 'accent';
  user: any = {
    _id: "",
    name: "",
    email: "",
    password: "",
    rating: 0,
    nvoti: 0,
    sommaVoti: 0,
    id_votati: [],
  };

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, private ajax: AjaxService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;
  ratingArr = [];

  initForm() {
    this.form = new FormGroup({});
  }

  submit() {
    if (this.user.id_votati.includes(sessionStorage.getItem("idUserScore"))) {
      Swal.fire({ title: "User already voted!", icon: 'error', position: "center" });
    } else {
      this.ajax.insertIdVotati(sessionStorage.getItem("UserID"), sessionStorage.getItem("idUserScore")).subscribe((res) => { })
      this.ajax.updateUserScore(this.rating, sessionStorage.getItem("idUserScore")).subscribe((res) => {
        this.dialogRef.close();
        window.location.reload();
      })
    }
  }

  ngOnInit(): void {
    this.initForm();
    this.getParam();
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

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //prendo l'id della nota dall'url
      let id = params['_id'];
      //scorro la lista dei documenti e salvo quello che ho selezionato
      this.data.listUsers.forEach(element => {
        //posso usare sia l'id che il name dell'utente
        if (element._id == sessionStorage.getItem("UserID") || element.name == id) {
          this.user = element;
        }
      });
    });
  }
}
