import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-new-chat',
  templateUrl: './new-chat.component.html',
  styleUrls: ['./new-chat.component.scss']
})
export class NewChatComponent implements OnInit {

  constructor(private ajax: AjaxService,private dataservice: DataService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;
  listUser = [];
  okUser = false;

  initForm() {
    this.form = new FormGroup({
      id_user1: new FormControl('', [Validators.required]),
      id_user2: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    const formData = new FormData();
    formData.append("id_user1", sessionStorage.getItem("UserID"));
    this.dataservice.listUsers.forEach(element => {
      if(element.name == this.form.get("id_user2").value){
        formData.append('id_user2', element._id);
        this.okUser = true
      }
    });
    if(this.okUser == false){
      this.dialogRef.close();
      window.alert("User errato, inserire il nome corretto!");
    }
    this.ajax.createChat(formData).subscribe((res) => {
      this.dialogRef.close();
    });
  }

  ngOnInit(): void {
    this.initForm();
    
  }

  

}
