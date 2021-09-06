import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewUserScoreComponent } from './new-user-score.component';

describe('NewUserScoreComponent', () => {
  let component: NewUserScoreComponent;
  let fixture: ComponentFixture<NewUserScoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewUserScoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewUserScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
