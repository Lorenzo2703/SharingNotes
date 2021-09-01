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

  user1ID;
  user2ID;
  form;

  ngDoCheck(): void {
    if (this.chat._id === "") {
      this.getParam();
    }
  }

  chat: any = {
    "_id": "",
    "id":  [],
    "messaggi": [],
    "name": ""
  };

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      let id = params['_id'];
      this.data.listGroupChat.forEach(element => {
        if (element._id == id) {
          this.chat = element;
          this.chat.name = element.name;
          for(let i = 0; i< 10; i++){
            if(sessionStorage.getItem("UserID") == element["id_"+i]){
              this.user1ID = element["id_"+i]
              this.user2ID = element["id_"+i]
            }
          }
        }        
      })
    });
  }

  initForm() {
    this.form = new FormGroup({
      id_user1: new FormControl('', [Validators.required]),
      id_user2: new FormControl('', [Validators.required]),
      sender: new FormControl('', [Validators.required]),
      messaggio: new FormControl('', [Validators.required]),
    });
  }

  sendMessage(event) {
    event.preventDefault();
    const formData = new FormData();
    formData.append("id_user1", this.user1ID);
    formData.append('id_user2', this.user2ID);
    if (this.user1ID == sessionStorage.getItem("UserID")) {
      formData.append("sender", "true");
    } else {
      formData.append("sender", "false");
    }
    formData.append("messaggio", this.form.get("messaggio").value);
    this.ajaxService.sendMessage(formData).subscribe((res) => {
    });
    //window.location.reload();
  }
}
