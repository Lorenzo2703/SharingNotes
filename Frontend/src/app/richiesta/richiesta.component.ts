import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { NewUserScoreComponent } from '../new-user-score/new-user-score.component';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-richiesta',
  templateUrl: './richiesta.component.html',
  styleUrls: ['./richiesta.component.scss']
})
export class RichiestaComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, public dialog: MatDialog, private ajaxService: AjaxService) { }

  richiesta = {
    testo: "",
    title: "",
    id_Richiedente: "",
    _id: "",
    completed: false,
  };
  autore;
  idAutore;
  form;
  id;
  listReviews = [];
  nameUserReview = [];
  idUser = sessionStorage.getItem("UserID")

  ngOnInit(): void {
    this.initForm();
    this.getComments()
    this.getParam();
  }

  ngDoCheck(): void {
    if (this.richiesta._id === "") {
      this.getParam();
    }
  }

  //utilizzoquesta funzione per potermi salvare l'id dell'user da votare
  getUserID(number) {
    sessionStorage.setItem("idUserScore", this.listReviews[number]["id_Recensore"])
    this.openScoreDialog()
  }
  openScoreDialog() {
    //apre new-score component
    const dialogRef = this.dialog.open(NewUserScoreComponent);
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //prendo l'id della nota dall'url
      this.id = params['_id'];
      //scorro la lista dei documenti e salvo quello che ho selezionato
      this.ajaxService.getRequest().subscribe(res => {
        for (let x in res) {
          if (res[x]._id == this.id) {
            this.richiesta = res[x];
            this.idAutore = res[x]["id_Richiedente"]
            //scrivo il nome dell'autore della richiesta
            this.ajaxService.getUserByID(res[x]["id_Richiedente"]).subscribe(res => {
              this.autore = res["name"];
            })
          }
        }
      })
    });
  }

  getComments() {
    //salvo tutte le recensioni relative alla richiesta
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Nota_Recensita"] == this.id) {
          //salvo le recensioni nella lista
          this.listReviews.push(res[x]);
        }
      }
    /* salvo i nomi dei recensori
    avrò in questo modo una correlazioni tra nome del recensore e recensione grazie alla posizione */
      for(let x in this.listReviews){
        this.ajaxService.getUserByID(this.listReviews[x]["id_Recensore"]).subscribe(user => {
          this.nameUserReview.splice(Number(x), 0, user["name"])
        })
      }
    })
  }

  completeRequest(){
    this.ajaxService.completeRequest(this.id);
  }
  submit() {
    //inizializzo il form
    const formData = new FormData();
    //inserisco i parametri
    formData.append("idRecensore", sessionStorage.getItem("UserID"));
    formData.append("idUserRecensito", this.idAutore);
    formData.append("idNotaRecensita", this.id);
    formData.append('title', "R1chiesta");
    formData.append("testo", this.form.get("testo").value);
    this.ajaxService.submitRecensione(formData).subscribe((res) => {
    });
    window.location.reload();
  }

  initForm() {
    this.form = new FormGroup({
      testo: new FormControl('', [Validators.required])
    });
  }

}
