import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-profile-review',
  templateUrl: './profile-review.component.html',
  styleUrls: ['./profile-review.component.scss']
})
export class ProfileReviewComponent implements OnInit {

  

  constructor(private ajaxService: AjaxService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  listReviews = []; //lista delle recensioni scritte dall'utente
  noRev = false; //boolean per verificare che ci siano recnesioni pubblicate dall'utente

  ngOnInit(): void {
    this.getUserReview();

  }

  getUserReview() {
    //salvo tutte le recensioni scritte dall'utente
    this.ajaxService.getReview().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Recensore"] == this.idUser) {
          this.listReviews.push(res[x]);
        }
      }
      console.log(this.listReviews);
      if (this.listReviews.length == 0) {
        this.noRev = true;
      }
    })
  }
}
