import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-new-review',
  templateUrl: './new-review.component.html',
  styleUrls: ['./new-review.component.scss']
})
export class NewReviewComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private ajax: AjaxService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;
  idUserNote;
  idNota;

  initForm() {
    this.form = new FormGroup({
      title: new FormControl('', [Validators.required]),
      testo: new FormControl('', [Validators.required])
    });
  }

  submit() {
    const formData = new FormData();
    formData.append("idRecensore", sessionStorage.getItem("UserID"));
    formData.append("idUserRecensito", sessionStorage.getItem("IDUserNota"));
    formData.append("idNotaRecensita", sessionStorage.getItem("IDNota"));
    formData.append('title', this.form.get("title").value);
    formData.append("testo", this.form.get("testo").value);
    this.ajax.submitRecensione(formData).subscribe((res) => {
      this.dialogRef.close();
    });
  }

  ngOnInit(): void {
    this.initForm();
  }


}
