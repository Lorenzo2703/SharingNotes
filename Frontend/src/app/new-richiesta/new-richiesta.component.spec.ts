import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewRichiestaComponent } from './new-richiesta.component';

describe('NewRichiestaComponent', () => {
  let component: NewRichiestaComponent;
  let fixture: ComponentFixture<NewRichiestaComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NewRichiestaComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NewRichiestaComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
