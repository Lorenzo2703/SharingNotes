import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-richiesta',
  templateUrl: './richiesta.component.html',
  styleUrls: ['./richiesta.component.scss']
})
export class RichiestaComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, private ajaxService: AjaxService) { }

  richiesta = {
    testo: "",
    title: "",
    id_Richiedente: "",
    _id: "",
    completed: false,
  };
  autore;

  ngOnInit(): void {
    this.getParam();
  }

  ngDoCheck(): void {
    if (this.richiesta._id === "") {
      this.getParam();
    }
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //prendo l'id della nota dall'url
      let id = params['_id'];
      //scorro la lista dei documenti e salvo quello che ho selezionato
      this.ajaxService.getRequest().subscribe(res =>{
        for(let x in res){
          if(res[x]._id == id){
            this.richiesta = res[x];
            //scrivo il nome dell'autore della richiesta
            this.ajaxService.getUserByID(res[x]["id_Richiedente"]).subscribe(res =>{
              this.autore = res["name"];
            })
          }
        }
      })
    });
  }

}
