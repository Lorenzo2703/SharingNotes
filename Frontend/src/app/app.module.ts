import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { HomeComponent } from './home/home.component';
import { RegisterComponent } from './register/register.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { Ng2SearchPipeModule } from 'ng2-search-filter';
import { NewNoteComponent } from './new-note/new-note.component';
import { MatDialogModule } from '@angular/material/dialog';
import { NoteComponent } from './note/note.component';
import { ReviewComponent } from './review/review.component';
import { ProfileReviewComponent } from './profile-review/profile-review.component';
import { NewReviewComponent } from './new-review/new-review.component';
import { MessagesComponent } from './messages/messages.component';
import { ChatComponent } from './chat/chat.component';
import { NewChatComponent } from './new-chat/new-chat.component';
import { NewGroupChatComponent } from './new-group-chat/new-group-chat.component';
import { MatSelectModule } from '@angular/material/select';
import { ChatGroupComponent } from './chat-group/chat-group.component';
import { NewScoreComponent } from './new-score/new-score.component';
import { MatIconModule } from '@angular/material/icon';
import { RichiestaComponent } from './richiesta/richiesta.component';
import { NewRichiestaComponent } from './new-richiesta/new-richiesta.component';
import { RequestComponent } from './request/request.component';
import { ProfileRequestComponent } from './profile-request/profile-request.component';
import { NewUserScoreComponent } from './new-user-score/new-user-score.component';
import { RankingComponent } from './ranking/ranking.component';
import { ProfileNoteComponent } from './profile-note/profile-note.component';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {MatAutocompleteModule} from '@angular/material/autocomplete';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    RegisterComponent,
    DashboardComponent,
    ProfileComponent,
    NewNoteComponent,
    NoteComponent,
    ReviewComponent,
    ProfileReviewComponent,
    NewReviewComponent,
    MessagesComponent,
    ChatComponent,
    NewChatComponent,
    NewGroupChatComponent,
    ChatGroupComponent,
    NewScoreComponent,
    RichiestaComponent,
    NewRichiestaComponent,
    RequestComponent,
    ProfileRequestComponent,
    NewUserScoreComponent,
    RankingComponent,
    ProfileNoteComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
    Ng2SearchPipeModule,
    FormsModule,
    HttpClientModule,
    MatFormFieldModule,
    MatInputModule,
    MatIconModule,
    MatDialogModule,
    MatButtonModule,
    MatSelectModule,
    MatProgressSpinnerModule,
    MatAutocompleteModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
