import { Component, DoCheck, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-chat-group',
  templateUrl: './chat-group.component.html',
  styleUrls: ['./chat-group.component.scss']
})
export class ChatGroupComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, private data: DataService, private ajaxService: AjaxService) { }

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    }
    this.getParam();
    this.initForm();

  }
  user1; //utente loggato
  user1ID;
  form;
  id; //ide della chat selezionata

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

  unsorted() { }

  getParam() {
    this.activatedRoute.params.subscribe(params => {
      //ricavo l'id della chat dall'url
      this.id = params['_id'];
      this.data.listGroupChat.forEach(element => {
        //scorro la lista di tutte le chat e salvo quella che effettivamente ho selezionato confrontando l'id
        if (element._id == this.id) {
          this.chat = element;
          this.chat.name = element.name;
          //scorro gli id degli user della chat finchè non trovo quello dell'utente loggato
          for (let i = 0; i < 10; i++) {
            if (sessionStorage.getItem("UserID") == element["id_" + i]) {
              this.user1ID = element["id_" + i]
            }
          }
        }
      })
    });
    //sostituisco gli id con i nomi
    this.newChat()
    //Salvo l'utente loggato
    this.ajaxService.getUserByID(sessionStorage.getItem("UserID")).subscribe(res => {
      this.user1 = res
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
    //scorro i messaggi
    for (let x in this.chat.messaggi) {
      //scorro gli utenti
      for (let y in this.data.listUsers) {
        //sostituisco la chiave nei messaggi usando al posto dell'id il nome
        if (this.data.listUsers[y]._id == Object.keys(this.chat.messaggi[x])) {
          delete Object.assign(this.chat.messaggi[x], { [this.data.listUsers[y].name]: this.chat.messaggi[x][this.data.listUsers[y]._id] })[this.data.listUsers[y]._id];
        }
      }
    }
  }

  sendMessage(event) {
    event.preventDefault();
    //inizializzo il form
    const formData = new FormData();
    //riempio il form
    formData.append("id", this.id);
    formData.append('id_user', this.user1ID);
    formData.append("messaggio", this.form.get("messaggio").value);
    //invio il messaggio
    this.ajaxService.sendGroupMessage(formData).subscribe((res) => {
    });
    window.location.reload();

  }
}
