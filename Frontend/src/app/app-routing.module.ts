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

const routes: Routes = [
  { path: "", component: LoginComponent },
  { path: "register", component: RegisterComponent },
  {
    path: "home", component: HomeComponent, children: [
      { path: "", component: DashboardComponent },
      { path: "messages", component: MessagesComponent },
      { path: "profile/notes", component: ProfileComponent },
      { path: "profile/reviews", component: ProfileReviewComponent },
      { path: 'note/:_id', component: NoteComponent },
      { path: 'chat/:_id', component: ChatComponent },
      { path: 'review/:_id', component: ReviewComponent }],
  },
  { path: '**', component: LoginComponent }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
