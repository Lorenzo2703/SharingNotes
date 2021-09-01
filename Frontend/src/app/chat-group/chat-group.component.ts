import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-chat-group',
  templateUrl: './chat-group.component.html',
  styleUrls: ['./chat-group.component.scss']
})
export class ChatGroupComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, private ajaxService: AjaxService) { }

  ngOnInit(): void {

    this.getParam();
    this.initForm();

  }
  user1;
  user1ID;
  form;
  id;

  ngDoCheck(): void {
    if (this.chat._id === "") {
      this.getParam();
    }
  }

  chat: any = {
    "_id": "",
    "id": [],
    "messaggi": [],
    "name": ""
  };

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      this.id = params['_id'];
      this.data.listGroupChat.forEach(element => {
        if (element._id == this.id) {
          this.chat = element;
          this.chat.name = element.name;
          for (let i = 0; i < 10; i++) {
            if (sessionStorage.getItem("UserID") == element["id_" + i]) {
              this.user1ID = element["id_" + i]
            }

          }
        }
      })
    });
    this.newChat()
    this.ajaxService.getUserByID(sessionStorage.getItem("UserID")).subscribe(res =>{
      this.user1= res
    })
  }

  initForm() {
    this.form = new FormGroup({
      id: new FormControl('', [Validators.required]),
      id_user: new FormControl('', [Validators.required]),
      messaggio: new FormControl('', [Validators.required]),
    });
  }

  newChat() {
    for (let x in this.chat.messaggi) {
      for (let y in this.data.listUsers) {
        if(this.data.listUsers[y]._id == Object.keys(this.chat.messaggi[x])){
          delete Object.assign(this.chat.messaggi[x], {[this.data.listUsers[y].name]: this.chat.messaggi[x][this.data.listUsers[y]._id] })[this.data.listUsers[y]._id];
        }
      }
    }
  }

  sendMessage(event) {
    event.preventDefault();
    const formData = new FormData();
    formData.append("id", this.id);
    formData.append('id_user', this.user1ID);
    formData.append("messaggio", this.form.get("messaggio").value);
    this.ajaxService.sendGroupMessage(formData).subscribe((res) => {
    });
    window.location.reload();
  }
}
