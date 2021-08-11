import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(private httpClient: HttpClient) { }

  baseUrl = "http://localhost:8080/";
  loginUrl = this.baseUrl + "login";

  login(email: string, password: string) {

    const data = { "email": email, "password": password };

    return this.httpClient.post(this.loginUrl, {}, { params: data });

  }
}
