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
      id: new FormControl( [] , [Validators.required]),
    });
  }

  submit() {
    const formData = new FormData();

    if(this.form.get("id").value.length != 0){
      this.okUsers = true
    }
    this.form.get("id").value.push(sessionStorage.getItem("UserID"))
    formData.append("id", this.form.get("id").value )

    if(this.okUsers == false){
      this.dialogRef.close();
      window.alert("Inserire almeno un user");
    }
    console.log(formData)
    this.ajax.createGroupChat(formData).subscribe((res) => {
      this.dialogRef.close();
    });
  }

  ngOnInit(): void {
    this.getOtherUser();
    this.initForm();
    
  }


}
