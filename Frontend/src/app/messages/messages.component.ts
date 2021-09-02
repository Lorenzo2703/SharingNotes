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
  listGroupMessage: any = [];
  message: any = {
    "_id": "",
    "id_User1": "",
    "id_User2": "",
    "messaggio": [],
    "name": ""
  };

  messageGroup: any = {
    "_id": "",
    "id": [],
    "messaggio": [],
    "name": ""
  };

  openDialog() {
    //apre il new-chat component
    const dialogRef = this.dialog.open(NewChatComponent);
  }

  openGroupDialog() {
    //apre il new-group-chat component
    const dialogRef = this.dialog.open(NewGroupChatComponent);
  }


  getChat() {
    //prendo tutte le chat singole in cui c'è l'utente
    this.ajax.getAllChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.message = res[x];
        this.listMessage.push(this.message);
      }
      this.getChatName()
    });
  }

  getGroupChat() {
    //prendo tutte le chat di gruppo in cui c'è l'utente
    this.ajax.getGroupChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.messageGroup = res[x];
        this.listGroupMessage.push(this.messageGroup);
      }
    });

  }

  getChatName(){

      this.listMessage.forEach(chat => {
        this.dataservice.listUsers.forEach(user=>{
          //se l'user1 della chat sono il allora il name sarà dato dall'altro user
          if (chat.id_User1 == sessionStorage.getItem("UserID")) {
            if(user._id == chat.id_User2){
              chat.name = user.name
            }
          //se non sono io il creatore comunque non leggo il mio nome sulla chat
          } else { 
            if(user._id == chat.id_User1){
              chat.name = user.name
            }
          }
        })
      })
  }


  ngOnInit(): void {
    this.getChat();
    this.getGroupChat();
  }

}
