import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { AjaxService } from '../ajax.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-new-note',
  templateUrl: './new-note.component.html',
  styleUrls: ['./new-note.component.scss']
})
export class NewNoteComponent implements OnInit {

  constructor(private ajax: AjaxService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;
  file;
  loading;

  initForm() {
    this.form = new FormGroup({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      file: new FormControl('', Validators.required)
    });
  }

  submit() {
    this.loading = true;
    //inizializzo il form
    const formData = new FormData();
    //aggiungo i parametri
    formData.append("file", this.file);
    formData.append('title', this.form.get("title").value);
    formData.append("description", this.form.get("description").value);
    formData.append("userId", sessionStorage.getItem("UserID"));
    this.ajax.submitFile(formData).subscribe((res) => {
      this.loading = false;
      this.dialogRef.close();
    });
  }

  handleFileInput(files: FileList) {
    this.file = files[0];
  }

  ngOnInit(): void {
    this.initForm();
  }

}
