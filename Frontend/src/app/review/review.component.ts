import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, private ajaxService: AjaxService) { }

  ngDoCheck(): void {
    if (this.review._id === "") {
      this.getParam();
    }
  }

  userReview

  review = {
    idNotaRecensita: "",
    testo: "",
    idUserRecensito: "",
    id_Recensore: "",
    score: 0,
    title: "",
    _id: ""
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['_id'];
      this.data.listReviews.forEach(element => {
        if (element._id == id) {
          this.review = element;
          this.ajaxService.getUserByID(this.review["id_Recensore"]).subscribe(user => {
            this.userReview = user;
          })
        }
      });
    });
  }


  ngOnInit(): void {
    this.getParam();
  }


}
