import { Component, OnInit } from '@angular/core';
import { AjaxService } from '../ajax.service';
import { DataService } from '../data.service';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile-note',
  templateUrl: './profile-note.component.html',
  styleUrls: ['./profile-note.component.scss']
})
export class ProfileNoteComponent implements OnInit {

  constructor(private ajaxService: AjaxService, private router: Router, private data: DataService) { }

  nameUser = sessionStorage.getItem("UserName");
  idUser = sessionStorage.getItem("UserID");
  ratingUser = sessionStorage.getItem("UserRating");
  listNotes = []; //lista delle note pubblicate dall'utente
  noNotes = false; //boolean per verificare che ci siano note pubblicate dall'user

  ngOnInit(): void {
    if (sessionStorage.getItem('UserID') == "" || sessionStorage.getItem('UserID') == null) {
      Swal.fire({ title: "Try to Log in 😅", icon: 'info', position: "center" });
      this.router.navigateByUrl("/login");
    } else {
      this.getUserNotes();
    }
  }

  convert(s: number) {
    var th_val = ['', 'thousand', 'million', 'billion', 'trillion'];
    var dg_val = ['zero', 'one', 'two', 'three', 'four', 'five', 'six', 'seven', 'eight', 'nine'];
    var tn_val = ['ten', 'eleven', 'twelve', 'thirteen', 'fourteen', 'fifteen', 'sixteen', 'seventeen', 'eighteen', 'nineteen'];
    var tw_val = ['twenty', 'thirty', 'forty', 'fifty', 'sixty', 'seventy', 'eighty', 'ninety'];
    function toWordsconver(s) {
      s = s.toString();
      s = s.replace(/[\, ]/g, '');
      if (s != parseFloat(s))
        return 'not a number ';
      var x_val = s.indexOf('.');
      if (x_val == -1)
        x_val = s.length;
      if (x_val > 15)
        return 'too big';
      var n_val = s.split('');
      var str_val = '';
      var sk_val = 0;
      for (let i = 0; i < x_val; i++) {
        if ((x_val - i) % 3 == 2) {
          if (n_val[i] == '1') {
            str_val += tn_val[Number(n_val[i + 1])] + ' ';
            i++;
            sk_val = 1;
          } else if (n_val[i] != 0) {
            str_val += tw_val[n_val[i] - 2] + ' ';
            sk_val = 1;
          }
        } else if (n_val[i] != 0) {
          str_val += dg_val[n_val[i]] + ' ';
          if ((x_val - i) % 3 == 0)
            str_val += 'hundred ';
          sk_val = 1;
        }
        if ((x_val - i) % 3 == 1) {
          if (sk_val)
            str_val += th_val[(x_val - i - 1) / 3] + ' ';
          sk_val = 0;
        }
      }
      if (x_val != s.length) {
        var y_val = s.length;
        str_val += 'point ';
        for (let i = x_val + 1; i < y_val; i++)
          str_val += dg_val[n_val[i]] + ' ';
      }
      return str_val.replace(/\s+/g, ' ');
    }
    return (toWordsconver(s));

  }


  getUserNotes() {
    //salvo tutte le note pubblicate dall'utente
    this.ajaxService.getNotes().subscribe(res => {
      for (let x in res) {
        if (res[x]["id_User"] == this.idUser) {
          this.listNotes.push(res[x]);
        }
      }
      if (this.listNotes.length == 0) {
        this.noNotes = true;
      }
    })
  }

}
