import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(private httpClient: HttpClient) { }

  baseUrl = "http://localhost:8080/";
  loginUrl = this.baseUrl + "login";
  registerUrl = this.baseUrl + "register";
  getNotesUrl = this.baseUrl + "getFiles";

  login(email: string, password: string) {
    const data = { "email": email, "password": password };
    return this.httpClient.post(this.loginUrl, {}, { params: data });
  }

  register(name: string, email: string, password: string) {
    let params = new HttpParams().set('name', name).set('email', email).set("password", password);
    return this.httpClient.post(this.registerUrl, {}, { params: params });
  }

  getNotes() {
    return this.httpClient.get(this.getNotesUrl, { params: { collection: "notes" } });
  }
}
