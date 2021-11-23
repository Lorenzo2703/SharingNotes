import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-request',
  templateUrl: './profile-request.component.html',
  styleUrls: ['./profile-request.component.scss']
})
export class ProfileRequestComponent implements OnInit {

  constructor(private data: DataService, private router: Router, private ajaxService: AjaxService) { }

  listRequest = [];

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getRequest();
    }

    console.log(this.listRequest);

  }

  getRequest() {
    this.ajaxService.getRequest().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_Richiedente"] == sessionStorage.getItem("UserID")) {
          this.listRequest.push(res[x]);
        }
      }

    })
  }


}
