import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewGroupChatComponent } from './new-group-chat.component';

describe('NewGroupChatComponent', () => {
  let component: NewGroupChatComponent;
  let fixture: ComponentFixture<NewGroupChatComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewGroupChatComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewGroupChatComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
