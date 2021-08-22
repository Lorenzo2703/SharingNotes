import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';

@Component({
  selector: 'app-note',
  templateUrl: './note.component.html',
  styleUrls: ['./note.component.scss']
})
export class NoteComponent implements OnInit, DoCheck {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService) { }

  ngDoCheck(): void {
    if (this.note._id === "") {
      this.getParam();
    }
  }

  note = {
    color: "",
    description: "",
    fileUrl: "",
    id_User: "",
    rating: 0,
    title: "",
    _id: ""
  }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['_id'];
      this.data.listNotes.forEach(element => {
        if (element._id == id) {
          this.note = element;
        }
      });
    });
  }

  ngOnInit(): void {
    this.getParam();
  }

}
