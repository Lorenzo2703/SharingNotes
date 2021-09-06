import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';
import { MatDialog } from '@angular/material/dialog';
import { NewRichiestaComponent } from '../new-richiesta/new-richiesta.component'

@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.scss']
})
export class RequestComponent implements OnInit {

  constructor(private activatedRoute: ActivatedRoute, private data: DataService, public dialog: MatDialog, private ajaxService: AjaxService) { }

  listRequest = [];

  ngOnInit(): void {
    this.getRequest();
  }

  getRequest(){
    this.ajaxService.getRequest().subscribe(res =>{
      for(let x in res){
        this.listRequest.push(res[x]);
      }

    })
  }

  openDialog() {
    //apre il new-chat component
    const dialogRef = this.dialog.open(NewRichiestaComponent);
  }

}
