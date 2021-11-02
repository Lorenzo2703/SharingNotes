import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { MatDialog } from '@angular/material/dialog';
import { NewRichiestaComponent } from '../new-richiesta/new-richiesta.component'
import Swal from 'sweetalert2';

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss']
})
export class RequestComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private router: Router, public dialog: MatDialog, private ajaxService: AjaxService) { }

  listRequest = [];

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "") {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getRequest();
    }
  }

  getRequest() {
    this.ajaxService.getRequest().subscribe(res => {
      for (let x in res) {
        this.listRequest.push(res[x]);
      }

    })
  }

  openDialog() {
    //apre il new-Richiesta component
    const dialogRef = this.dialog.open(NewRichiestaComponent);
  }
}
