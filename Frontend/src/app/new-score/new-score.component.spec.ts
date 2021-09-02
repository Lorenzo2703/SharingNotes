import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewScoreComponent } from './new-score.component';

describe('NewScoreComponent', () => {
  let component: NewScoreComponent;
  let fixture: ComponentFixture<NewScoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewScoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewScoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
