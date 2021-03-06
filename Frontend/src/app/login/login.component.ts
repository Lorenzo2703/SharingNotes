import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  form: any;
  //variabile per il caricamento
  openSpinner = false;
  constructor(private route: ActivatedRoute, private router: Router, private ajax: AjaxService) { }

  initForm() {
    this.form = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.email]),
      password: new FormControl('', [Validators.required])
    });
  }

  login() {
    //apre il segno di caricamento
    this.openSpinner = true;
    //prendo le info dal form
    this.ajax.login(this.form.get("email").value, this.form.get("password").value).subscribe((res) => {
      //salvo le informazioni dell'user e le salvo nel session storage per poterle riutilizzare
      let userName = res["name"];
      let userID = res["_id"];
      let userRating = res["rating"]
      sessionStorage.setItem('UserName', userName);
      sessionStorage.setItem('UserID', userID);
      sessionStorage.setItem('UserRating', userRating);
      //ti rimanda direttamente alla home
      this.router.navigateByUrl("/home");
    }, (error) => {
      //Error callback
      Swal.fire({
        title: "Wrong email or password, please try again", icon: 'error', position: "center"
      });
      this.openSpinner = false;
    }
    )
  }

  skip() {
    sessionStorage.setItem('UserName', "");
    sessionStorage.setItem('UserID', "");
    sessionStorage.setItem('UserRating', "");
    this.router.navigateByUrl("/home");
  }


  register() {
    //ti rimanda alla pagina per registrarsi
    this.router.navigateByUrl("/register");
  }

  ngOnInit(): void {
    this.initForm();

  }

}
