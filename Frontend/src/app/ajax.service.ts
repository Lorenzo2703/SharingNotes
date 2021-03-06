import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class AjaxService {

  constructor(private httpClient: HttpClient) { }

  baseUrl = "http://localhost:8080/";
  loginUrl = this.baseUrl + "login/authenticate";
  registerUrl = this.baseUrl + "register/register";
  getNotesUrl = this.baseUrl + "file/getFiles";
  getReviewsUrl = this.baseUrl + "file/getFiles";
  getUserUrl = this.baseUrl + "file/getFiles";
  getRequestUrl = this.baseUrl + "file/getFiles";
  getUserbyIDUrl = this.baseUrl + "user/getUserByID";
  uploadUrl = this.baseUrl + "file/fileUpload";
  uploadReview = this.baseUrl + "recensione/insertRecensione";
  getChat = this.baseUrl + "chat/getAllChat";
  sendMessageUrl = this.baseUrl + "chat/sendMessage";
  createChatUrl = this.baseUrl + "chat/createChat";
  createGroupChatUrl = this.baseUrl + "chat/createGroupChat";
  getGroupChatUrl = this.baseUrl + "chat/getGroupChat";
  sendGroupMessageUrl = this.baseUrl + "chat/sendGroupMessage";
  updateScoreUrl = this.baseUrl + "score/updateScore"
  insertRichiestaUrl = this.baseUrl + "request/insertRichiesta";
  insertIdVotatiUrl = this.baseUrl + "score/insertIdVotati";
  completeRequestUrl = this.baseUrl + 'request/completeRequest';
  deleteUrl = this.baseUrl + 'file/delete';

  completeRequest(id) {
    let params = new HttpParams().set('bool', "true").set('id', id).set("collection", "richieste");
    return this.httpClient.post(this.completeRequestUrl, {}, { params: params })
  }

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

  getRequest() {
    return this.httpClient.get(this.getRequestUrl, { params: { collection: "richieste" } });
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

  updateUserScore(score, id) {
    let params = new HttpParams().set('score', score).set('id', id).set("collection", "utenti");
    return this.httpClient.post(this.updateScoreUrl, {}, { params: params })
  }

  insertRequest(formData) {
    return this.httpClient.post(this.insertRichiestaUrl, formData)
  }

  delete(id, collection) {
    return this.httpClient.delete(this.deleteUrl, { params: { _id: id, collection: collection } });
  }

  insertIdVotati(id, id_votato) {
    let params = new HttpParams().set("id_votato", id_votato).set('id', id);
    return this.httpClient.post(this.insertIdVotatiUrl, {}, { params: params })
  }

}
