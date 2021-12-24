import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.scss']
})
export class ProfileComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private ajaxService: AjaxService, private data: DataService) { }

  listNotes = []; //lista delle note pubblicate dall'utente
  listReviews = []; //lista delle recensioni pubblicate dall'utente
  listRequest = []; //lista delle richieste pubblicate dall'utente
  noRev = false;
  noNotes = false; //boolean per verificare che ci siano note pubblicate dall'user
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

  ngDoCheck(): void {
    if (this.user._id === "") {
      this.getParam();
    }

  }

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getParam();
      this.getUserNotes();
      this.getUserReview();
      this.getRequest();
    }
  }

  getUserNotes() {
    //salvo tutte le note pubblicate dall'utente
    this.ajaxService.getNotes().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_User"] == this.user._id) {
          this.listNotes.push(res[x]);
        }
      }
      if (this.listNotes.length == 0) {
        this.noNotes = true;
      }
    })
  }

  getRequest() {
    this.ajaxService.getRequest().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Richiedente"] == this.user._id) {
          this.listRequest.push(res[x]);
        }
      }

    })
  }

  counter(i: number) {
    return new Array(i);
  }

  getUserReview() {
    //salvo tutte le recensioni scritte dall'utente
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Recensore"] == this.user._id && res[x]["title"] != "R1chiesta") {
          this.listReviews.push(res[x]);
        }
      }
      if (this.listReviews.length == 0) {
        this.noRev = true;
      }
    })
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //prendo l'id della nota dall'url
      let id = params['_id'];
      //scorro la lista dei documenti e salvo quello che ho selezionato
      this.data.listUsers.forEach(element => {

        //posso usare sia l'id che il name dell'utente
        if (element._id == id || element.name == id) {
          this.user = element;
        }
      });
    });
  }
}
