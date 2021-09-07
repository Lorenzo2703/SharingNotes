import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { MatDialog } from '@angular/material/dialog';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-ranking',
  templateUrl: './ranking.component.html',
  styleUrls: ['./ranking.component.scss']
})
export class RankingComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, public dialog: MatDialog, private ajaxService: AjaxService) { }

  listUsers = [];
  listNotes = [];
  rating: number = 0;
  starCount: number = 5;
  color: string = 'accent';
  ratingArr = [];

  ngOnInit(): void {
    this.getNotes();
    this.getUsers();
    //quante stelle utilizzare
    for (let index = 0; index < this.starCount; index++) {
      this.ratingArr.push(index);
    }
  }

  getUsers(){
    this.listUsers = this.data.listUsers;
    //ordino l'array
    this.listUsers.sort(
      function(a, b) {          
         if (a.rating === b.rating) {
            // a parità di rating vince chi ha più voti
            return b.nvoti - a.nvoti;
         }
         return a.rating < b.rating ? 1 : -1;
      });
  }

  getNotes(){
    this.listNotes = this.data.listNotes;
    //ordino l'array
    this.listNotes.sort(
      function(a, b) {          
         if (a.rating === b.rating) {
            // a parità di rating vince chi ha più voti
            return b.nvoti - a.nvoti;
         }
         return a.rating < b.rating ? 1 : -1;
      });
  }

  showIcon(index: number, rating : number) {
    //per saper se far vedere la stella piena o vuota in base al rating
    if (rating >= index + 1) {
      return 'star';
    } else {
      return 'star_border';
    }

  }

}
