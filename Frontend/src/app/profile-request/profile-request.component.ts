import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DataService } from '../data.service';
import { AjaxService } from '../ajax.service';

@Component({
  selector: 'app-profile-request',
  templateUrl: './profile-request.component.html',
  styleUrls: ['./profile-request.component.scss']
})
export class ProfileRequestComponent implements OnInit {

  constructor(private data: DataService, private ajaxService: AjaxService) { }

  listRequest=[];

  ngOnInit(): void {
    this.getRequest();
  }

  getRequest(){
    this.ajaxService.getRequest().subscribe(res =>{
      for(let x in res){
        if(res[x]["id_Richiedente"] == sessionStorage.getItem("UserID")){
          this.listRequest.push(res[x]);
        }
      }

    })
  }


}
