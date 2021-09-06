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
  getUserUrl = this.baseUrl + "getFiles";
  getRequestUrl = this.baseUrl + "getFiles";
  getUserbyIDUrl = this.baseUrl + "getUserByID";
  uploadUrl = this.baseUrl + "fileUpload";
  uploadReview = this.baseUrl + "insertRecensione";
  getChat = this.baseUrl + "getAllChat";
  sendMessageUrl = this.baseUrl + "sendMessage";
  createChatUrl = this.baseUrl + "createChat";
  createGroupChatUrl = this.baseUrl + "createGroupChat";
  getGroupChatUrl = this.baseUrl + "getGroupChat";
  sendGroupMessageUrl = this.baseUrl + "sendGroupMessage";
  updateScoreUrl = this.baseUrl + "updateScore"
  downloadUrl = this.baseUrl + "download";
  insertRichiestaUrl = this.baseUrl + "insertRichiesta";

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

  getUser() {
    return this.httpClient.get(this.getUserUrl, { params: { collection: "utenti" } });
  }

  getRequest(){
    return this.httpClient.get( this.getRequestUrl, {params: { collection: "richieste"}});
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

  sendMessage(formData) {
    return this.httpClient.post(this.sendMessageUrl, formData);
  }

  createChat(formData) {
    return this.httpClient.post(this.createChatUrl, formData);
  }

  createGroupChat(formData) {
    return this.httpClient.post(this.createGroupChatUrl, formData)
  }

  getGroupChat(id) {
    return this.httpClient.get(this.getGroupChatUrl, { params: { id: id } })
  }

  sendGroupMessage(formData) {
    return this.httpClient.post(this.sendGroupMessageUrl, formData)
  }

  updateScore(score, id) {
    let params = new HttpParams().set('score', score).set('id', id).set("collection", "notes");
    return this.httpClient.post(this.updateScoreUrl, {}, { params: params })
  }

  download(fileUrl) {
    return this.httpClient.get(this.downloadUrl, { params: { fileUrl: fileUrl } });
  }

  insertRequest(formData){
    return this.httpClient.post( this.insertRichiestaUrl, formData)
  }

}
