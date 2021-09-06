import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { DataService } from '../data.service';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-new-richiesta',
  templateUrl: './new-richiesta.component.html',
  styleUrls: ['./new-richiesta.component.scss']
})
export class NewRichiestaComponent implements OnInit {

  constructor(private ajax: AjaxService,private dataservice: DataService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;

  initForm() {
    this.form = new FormGroup({
      title: new FormControl('', [Validators.required]),
      testo: new FormControl('', [Validators.required]),
    });
  }

  submit() {
    //inizializzo il form
    const formData = new FormData();
    //aggiungo i campi al form
    formData.append("idRichiedente", sessionStorage.getItem("UserID"));
    formData.append('title', this.form.get("title").value);
    formData.append("testo", this.form.get("testo").value);  
    //invio il form 
    this.ajax.insertRequest(formData).subscribe((res) => {
    });
    this.dialogRef.close();
    window.location.reload();
  }

  ngOnInit(): void {
    this.initForm();
    
  }

}
