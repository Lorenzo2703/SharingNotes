import { Component, DoCheck, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit, DoCheck {

  constructor(private ajaxService: AjaxService, private dataservice: DataService) { }

  ngDoCheck(): void {
    this.dataservice.searchText = this.searchText;
  }

  listNotes = [];
  searchText;
  i = 0;

  ngOnInit(): void {
    this.getNotes();
  }

  getNotes() {

    this.ajaxService.getNotes().subscribe(res => {
      for (let x in res) {
        this.listNotes.push(res[x]);

        if (this.i == this.dataservice?.listColor?.length - 1) {
          this.i = 0;
        } else {
          this.i++;
        }

        this.listNotes[x].color = this.dataservice.listColor[this.i].color;
      }
      this.dataservice.listNotes = this.listNotes;
      console.log(this.listNotes);
    })
  }

}
