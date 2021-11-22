import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import Swal from 'sweetalert2';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';
import { HomeComponent } from '../home/home.component';

@Component({
  selector: 'app-new-note',
  templateUrl: './new-note.component.html',
  styleUrls: ['./new-note.component.scss']
})
export class NewNoteComponent implements OnInit {

  constructor(private ajax: AjaxService, private data: DataService, public dialogRef: MatDialogRef<HomeComponent>) { }
  form;
  file;

  listCategorie = this.data.listCategorie;
  openSpinner = false;

  requiredFileType(expectedFormats: string[]) {
    return function (control: FormControl) {
      const files = control.value;

      if (files) {

        const splitParts = files.split('.');

        const extension = splitParts[splitParts.length - 1].toLowerCase();

        if ('pdf' != extension.toLowerCase()) {
          return { valid: false };
        } else {
          return null;
        }
      }
    };
  };


  initForm() {
    this.form = new FormGroup({
      title: new FormControl('', [Validators.required]),
      description: new FormControl('', [Validators.required]),
      categoria: new FormControl('', [Validators.required]),
      file: new FormControl('', [Validators.required, this.requiredFileType(["pdf"])])
    });
  }

  submit() {
    this.openSpinner = true;
    //inizializzo il form
    const formData = new FormData();
    //aggiungo i parametri
    formData.append("file", this.file);
    formData.append('title', this.form.get("title").value);
    formData.append("description", this.form.get("description").value);
    formData.append("userId", sessionStorage.getItem("UserID"));
    formData.append("categoria", this.form.get("categoria").value)
    this.ajax.submitFile(formData).subscribe((res) => {
      this.openSpinner = false;
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
