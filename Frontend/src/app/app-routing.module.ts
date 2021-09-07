import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { NoteComponent } from './note/note.component';
import { ProfileComponent } from './profile/profile.component';
import { RegisterComponent } from './register/register.component';
import { ReviewComponent } from './review/review.component';
import { ProfileReviewComponent } from './profile-review/profile-review.component';
import { MessagesComponent } from './messages/messages.component';
import { ChatComponent } from './chat/chat.component';
import { ChatGroupComponent } from './chat-group/chat-group.component';
import { RequestComponent } from './request/request.component';
import { RichiestaComponent } from './richiesta/richiesta.component';
import { ProfileRequestComponent } from './profile-request/profile-request.component';
import { RankingComponent } from './ranking/ranking.component';

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  {
    path: "home", component: HomeComponent, children: [
      { path: "", component: DashboardComponent },
      { path: "messages", component: MessagesComponent },
      { path: "profile/notes", component: ProfileComponent },
      { path: "profile/reviews", component: ProfileReviewComponent },
      { path: "profile/request", component: ProfileRequestComponent },
      { path: 'note/:_id', component: NoteComponent },
      { path: 'chat/:_id', component: ChatComponent },
      { path: 'review/:_id', component: ReviewComponent },
      { path: 'chat-group/:_id', component: ChatGroupComponent },
      { path: 'request', component: RequestComponent},
      { path: 'ranking', component: RankingComponent},
      { path: 'richiesta/:_id', component: RichiestaComponent}],
  },
  { path: '**', component: LoginComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
