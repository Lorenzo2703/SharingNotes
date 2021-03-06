import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { MatDialog } from '@angular/material/dialog';
import { NewChatComponent } from '../new-chat/new-chat.component';
import { NewGroupChatComponent } from '../new-group-chat/new-group-chat.component';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-messages',
  templateUrl: './messages.component.html',
  styleUrls: ['./messages.component.scss']
})
export class MessagesComponent implements OnInit {

  constructor(private ajax: AjaxService, private router: Router, public dialog: MatDialog, private dataservice: DataService) { }

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
    Swal.fire({
      title: 'Do you want to create a chat?',
      showDenyButton: true,
      showCancelButton: true,
      confirmButtonColor: "#4e73df",
      denyButtonColor: "#0f9700",
      confirmButtonText: 'Single chat',
      denyButtonText: `Group chat`,
    }).then((result) => {
      if (result.isConfirmed) {
        const dialogRef = this.dialog.open(NewChatComponent);
      } else if (result.isDenied) {
        const dialogRef = this.dialog.open(NewGroupChatComponent);
      }
    })
  }



  delete(id) {
    this.ajax.delete(id, "chat").subscribe(res => {
    });
    this.listMessage = [];
    setTimeout(() => { this.getChat(); }, 2000);

  }


  getChat() {
    //prendo tutte le chat singole in cui c'รจ l'utente
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in ๐", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    }
    this.ajax.getAllChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.message = res[x];
        this.listMessage.push(this.message);
      }
      this.getChatName()
    });
  }

  getGroupChat() {
    //prendo tutte le chat di gruppo in cui c'รจ l'utente
    this.ajax.getGroupChat(sessionStorage.getItem("UserID")).subscribe(res => {
      for (let x in res) {
        this.messageGroup = res[x];
        this.listGroupMessage.push(this.messageGroup);
      }
    });

  }

  getChatName() {
    this.listMessage.forEach(chat => {
      this.dataservice.listUsers.forEach(user => {
        //se l'user1 della chat sono il allora il name sarร? dato dall'altro user
        if (chat.id_User1 == sessionStorage.getItem("UserID")) {
          if (user._id == chat.id_User2) {
            chat.name = user.name
          }
          //se non sono io il creatore comunque non leggo il mio nome sulla chat
        } else {
          if (user._id == chat.id_User1) {
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
