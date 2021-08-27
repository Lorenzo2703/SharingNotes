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
  getUserbyIDUrl = this.baseUrl + "getUserByID";
  uploadUrl = this.baseUrl + "fileUpload";
  uploadReview = this.baseUrl + "insertRecensione";
  getChat = this.baseUrl + "getAllChat";
  sendMessageUrl = this.baseUrl + "sendMessage";

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

  getAllChat(id) {
    return this.httpClient.get(this.getChat, { params: { id_user1: id } });
  }

  getUserByID(id) {
    return this.httpClient.get(this.getUserbyIDUrl, { params: { _id: id } });
  }

  getReview() {
    return this.httpClient.get(this.getReviewsUrl, { params: { collection: "recensioni" } });
  }
  
  /**
  sendMessage(id1, id2, sender, message ){
    let params = new HttpParams().set('id_user1', id1).set('id_user2', id2).set("sender", sender).set("messaggio", message);
    return this.httpClient.post(this.sendMessageUrl,  {}, { params: params })
  }**/

  sendMessage(formData) {
    return this.httpClient.post(this.sendMessageUrl, formData);
  }

}
