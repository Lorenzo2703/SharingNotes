import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';


@Component({
  selector: 'app-review',
  templateUrl: './review.component.html',
  styleUrls: ['./review.component.scss']
})
export class ReviewComponent implements OnInit {
  
  constructor(private activatedRoute: ActivatedRoute, private data: DataService) { }

  ngDoCheck(): void {
    if (this.review._id === "") {
      this.getParam();
    }
  }

  review = {
    idNotaRecensita: "",
    testo: "",
    idUserRecensito: "",
    idRecensore: "",
    score: 0,
    title: "",
    _id: ""
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['_id'];
      this.data.listReviews.forEach(element => {
        console.log(element._id)
        if (element._id == id) {
          this.review = element;
        }
      });
    });
  }

  ngOnInit(): void {
    this.getParam();
  }


}
