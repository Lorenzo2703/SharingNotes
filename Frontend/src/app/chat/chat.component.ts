import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, private ajaxService: AjaxService) { }

  ngOnInit(): void {
    
    this.getParam();
    
  }

  user1;
  user2;
  user1ID;
  user2ID;

  ngDoCheck(): void {
    if (this.chat._id === "") {
      this.getParam();
    }
  }

  chat: any = {
    "_id": "",
    "id_User1": "",
    "id_User2": "",
    "messaggi": [],
    "name": ""
  };

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['_id'];
      this.data.listChats.forEach(element => {
        if (element._id == id) {
          this.chat = element;
          if (this.chat.id_User1 == sessionStorage.getItem("UserID")) {
            this.ajaxService.getUserByID(this.chat.id_User2).subscribe(response => {
              this.chat.name = response["name"];
              this.user2 = response;
              this.user2ID = this.user2._id
            })
            this.ajaxService.getUserByID(this.chat.id_User1).subscribe(response => {
              this.user1 = response;
              this.user1ID = this.user1._id
            })
          } else {
            this.ajaxService.getUserByID(this.chat.id_User1).subscribe(response => {
              this.chat.name = response["name"];
              this.user1 = response;
              this.user1ID = this.user1._id
            })
            this.ajaxService.getUserByID(this.chat.id_User2).subscribe(response => {
              this.user2 = response;
              this.user2ID = this.user2._id
            })
          }
        }
      })
    });
    console.log(this.chat);
  }

}
