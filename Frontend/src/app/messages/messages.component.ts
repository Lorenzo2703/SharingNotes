import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { MatDialog } from '@angular/material/dialog';
import { NewChatComponent } from '../new-chat/new-chat.component';
import { NewGroupChatComponent } from '../new-group-chat/new-group-chat.component';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {

  constructor(private ajax: AjaxService, public dialog: MatDialog, private dataservice: DataService) { }

  listMessage: any = [];
  message: any = {
    "_id": "",
    "id_User1": "",
    "id_User2": "",
    "messaggio": [],
    "name": ""
  };

  openDialog() {
    const dialogRef = this.dialog.open(NewChatComponent);
  }

  openGroupDialog() {
    const dialogRef = this.dialog.open(NewGroupChatComponent);
  }


  getChat() {
    this.ajax.getAllChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.message = res[x];
        this.listMessage.push(this.message);
      }
      this.getChatName()
    });
  }

  getChatName(){

      this.listMessage.forEach(chat => {
        this.dataservice.listUsers.forEach(user=>{
          if (chat.id_User1 == sessionStorage.getItem("UserID")) {
            if(user._id == chat.id_User2){
              chat.name = user.name
            }
          } else { //se non sono io il creatore comunque non leggo il mio nome sulla chat
            if(user._id == chat.id_User1){
              chat.name = user.name
            }
          }
        })
      })
  }

  ngOnInit(): void {
    this.getChat();
  }

}
