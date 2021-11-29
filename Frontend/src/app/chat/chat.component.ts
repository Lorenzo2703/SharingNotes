import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.scss']
})
export class ChatComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private data: DataService, private ajaxService: AjaxService) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getParam();
      this.initForm();
    }
  }

  user1; //utente loggato
  user2; //altro utente
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
    "id_User1": "",
    "id_User2": "",
    "messaggi": [],
    "name": ""
  };

  unsorted(){
    
  }

  getParam() {

    this.activatedRoute.params.subscribe(params => {
      //ricavo l'id della chat dall'url
      let id = params['_id'];
      this.data.listChats.forEach(element => {
        //scorro la lista di tutte le chat e salvo quella che effettivamente ho selezionato confrontando l'id
        if (element._id == id) {
          this.chat = element;
          //caso in cui l'user loggato è l'id1 della chat
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
            //caso in cui l'user loggato è l'id2 della chat
          } else {
            this.ajaxService.getUserByID(this.chat.id_User1).subscribe(response => {
              this.chat.name = response["name"];
              this.user2 = response;
              this.user2ID = this.user2._id
            })
            this.ajaxService.getUserByID(this.chat.id_User2).subscribe(response => {
              this.user1 = response;
              this.user1ID = this.user1._id
            })
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
    //inizializzo il form
    const formData = new FormData();
    //riempio io campi del form
    formData.append("id_user1", this.chat.id_User1);
    formData.append('id_user2', this.chat.id_User2);
    if (this.chat.id_User1 == sessionStorage.getItem("UserID")) {
      formData.append("sender", "true");
    } else {
      formData.append("sender", "false");
    }
    formData.append("messaggio", this.form.get("messaggio").value);
    //invio il messaggio
    this.ajaxService.sendMessage(formData).subscribe((res) => {
    });
    window.location.reload();

  }

}
