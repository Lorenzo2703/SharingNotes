import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-profile-review',
  templateUrl: './profile-review.component.html',
  styleUrls: ['./profile-review.component.scss']
})
export class ProfileReviewComponent implements OnInit {



  constructor(private ajaxService: AjaxService, private router: Router) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  listReviews = []; //lista delle recensioni scritte dall'utente
  noRev = false; //boolean per verificare che ci siano recnesioni pubblicate dall'utente

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getUserReview();
    }
  }


  delete(id) {
    this.ajaxService.delete(id, "recensioni").subscribe(res => {
    });
    this.listReviews = [];
    setTimeout(() => { this.getUserReview(); }, 2000);

  }

  getUserReview() {
    //salvo tutte le recensioni scritte dall'utente
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Recensore"] == this.idUser && res[x]["title"] != "R1chiesta") {
          this.listReviews.push(res[x]);
        }
      }
      if (this.listReviews.length == 0) {
        this.noRev = true;
      }
    })
  }
}

