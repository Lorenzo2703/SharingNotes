import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: any;
  //variabile per il caricamento
  openSpinner = false;

  constructor(private route: ActivatedRoute, private router: Router, private ajax: AjaxService) { }

  initForm() {
    this.form = new FormGroup({
      name: new FormControl('', [Validators.required]),
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  login() {
    //ritorna alla pagina della login
    this.router.navigateByUrl("");
  }

  register() {
    //apre il segno di caricamento
    this.openSpinner = true;
    //registrazione
    this.ajax.register(this.form.get("name").value, this.form.get("email").value, this.form.get("password").value).subscribe((res) => {
      this.router.navigateByUrl("/login");
      Swal.fire({ title: "Successfully registered!", icon: "success" });
    }, (error) => {
      this.openSpinner = false;                   //Error callback
      Swal.fire({ title: "Name or email already used, use other credentials!", icon: "error" });
    });
  }

  ngOnInit(): void {
    this.initForm();
  }

}
