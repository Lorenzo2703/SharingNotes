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
  getReviewsUrl = this.baseUrl + "getFiles";
  uploadUrl = this.baseUrl + "fileUpload";
  uploadReview = this.baseUrl + "insertRecensione";
  downloadUrl = this.baseUrl + "download";

  login(email: string, password: string) {
    const data = { "email": email, "password": password };
    return this.httpClient.post(this.loginUrl, {}, { params: data });
  }

  register(name: string, email: string, password: string) {
    let params = new HttpParams().set('name', name).set('email', email).set("password", password);
    return this.httpClient.post(this.registerUrl, {}, { params: params });
  }

  submitFile(formData) {
    return this.httpClient.post(this.uploadUrl, formData);
  }

  submitRecensione(formData) {
    return this.httpClient.post(this.uploadReview, formData);
  }

  getNotes() {
    return this.httpClient.get(this.getNotesUrl, { params: { collection: "notes" } });
  }

  download(url: string) {
    return this.httpClient.get(this.downloadUrl, { params: { url: url } });
  }

  getReview() {
    return this.httpClient.get(this.getReviewsUrl, { params: { collection: "recensioni" } });
  }
}
