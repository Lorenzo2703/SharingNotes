import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';
import {MatSelectModule} from '@angular/material/select';

@Component({
  selector: 'app-new-group-chat',
  templateUrl: './new-group-chat.component.html',
  styleUrls: ['./new-group-chat.component.scss']
})
export class NewGroupChatComponent implements OnInit {

  constructor(private ajax: AjaxService,private dataservice: DataService, public dialogRef: MatDialogRef<HomeComponent>) { }

  form;
  listUser = [];
  idUser
  
  getOtherUser(){
    this.dataservice.listUsers.forEach(element =>{
      if(element._id != sessionStorage.getItem("UserID")){
        this.listUser.push(element)
      }
    });
  }
  okUsers = false;


  initForm() {
    this.form = new FormGroup({
      name: new FormControl( "" , [Validators.required]),
      id: new FormControl( [] , [Validators.required]),
    });
  }

  submit() {
    const formData = new FormData();
    this.form.get("id").value = [sessionStorage.getItem("UserID")].concat(this.form.get("id").value)

    formData.append("name", this.form.get("name").value )
    if(this.form.get("id").value.length > 2){
      this.okUsers = true
    }
    formData.append("id", this.form.get("id").value )

    if(this.okUsers == false){
      this.dialogRef.close();
      window.alert("Inserire almeno due users");
    }else{
      this.ajax.createGroupChat(formData).subscribe((res) => {
      });
      this.dialogRef.close();
    }
    window.location.reload();
  }

  ngOnInit(): void {
    this.getOtherUser();
    this.initForm();
    
  }


}
