import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  form: any;

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
    //registrazione
    this.ajax.register(this.form.get("name").value, this.form.get("email").value, this.form.get("password").value).subscribe((res) => {
      console.log(res);
      this.router.navigateByUrl("/home");
    });
  }
  
  ngOnInit(): void {
    this.initForm();
  }

}
