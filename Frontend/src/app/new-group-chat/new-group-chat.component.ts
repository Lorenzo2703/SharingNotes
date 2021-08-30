import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-new-group-chat',
  templateUrl: './new-group-chat.component.html',
  styleUrls: ['./new-group-chat.component.scss']
})
export class NewGroupChatComponent implements OnInit {

  constructor(private ajax: AjaxService,private dataservice: DataService, public dialogRef: MatDialogRef<HomeComponent>) { }

  toppings = new FormControl();

  toppingList = ['Extra cheese', 'Mushroom', 'Onion', 'Pepperoni', 'Sausage', 'Tomato'];
  selectedToppings;


  form;
  listUser = this.dataservice.listUsers;
  okUser = false;


  initForm() {
    this.form = new FormGroup({
      id: new FormControl( [] , [Validators.required]),
    });
  }

  submit() {
    console.log(this.form.get("id").value)
    /*const formData = new FormData();
    
    formData.append("id", sessionStorage.getItem("UserID"));
    this.dataservice.listUsers.forEach(element => {
      if(element.name == this.form.get("id").value){
        formData.append('id', element._id);
        this.okUser = true
      }
    });
    if(this.okUser == false){
      this.dialogRef.close();
      window.alert("User errato, inserire il nome corretto!");
    }
    this.ajax.createChat(formData).subscribe((res) => {
      this.dialogRef.close();
    });*/
  }

  ngOnInit(): void {
    this.initForm();
    
  }


}
