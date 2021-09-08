import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProfileNoteComponent } from './profile-note.component';

describe('ProfileNoteComponent', () => {
  let component: ProfileNoteComponent;
  let fixture: ComponentFixture<ProfileNoteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProfileNoteComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ProfileNoteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
