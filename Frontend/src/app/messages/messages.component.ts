import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {

  constructor(private ajax: AjaxService) { }

  listMessage: any = [];
  message: any = {
    "_id": "",
    "id_User1": "",
    "id_User2": "",
    "messaggio": [],
    "name": ""
  };


  getChat() {
    this.ajax.getAllChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.message = res[x];
        this.ajax.getUserByID(res[x]["id_User2"]).subscribe(response => {
          if (response["_id"] === sessionStorage.getItem("UserID")) {
            this.ajax.getUserByID(res[x]["id_User1"]).subscribe(respo => {
              this.message.name = respo["name"];
              this.listMessage.push(this.message);
            })
          } else {
            this.message.name = response["name"];
            this.listMessage.push(this.message);
          }


        })
      }
    });
  }

  ngOnInit(): void {
    this.getChat();
  }

}
