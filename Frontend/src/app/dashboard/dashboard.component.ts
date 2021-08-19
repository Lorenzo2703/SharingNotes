
import { ChangeDetectorRef } from '@angular/core';
import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {

  constructor(private ajaxService: AjaxService, private cdRef: ChangeDetectorRef) { }

  listColor = [{ color: "border-left-dark" }, { color: "border-left-success" }, { color: "border-left-secondary" }, { color: "border-left-primary" }, { color: "border-left-danger" }, { color: "border-left-warning" }, { color: "border-left-info" }];
  listNotes = [];
  i = 0;

  getNotes() {
    this.ajaxService.getNotes().subscribe(res => {
      for (let x in res) {
        this.listNotes.push(res[x]);
      }
      console.log(this.listNotes);
    })
  }

  returnColor() {
    if (this.i == this.listColor.length - 1) {
      this.i = 0;
    } else {
      this.i++;
    }
    return this?.listColor[this.i]?.color;
  }

  ngOnInit(): void {
    this.getNotes();
  }

}
