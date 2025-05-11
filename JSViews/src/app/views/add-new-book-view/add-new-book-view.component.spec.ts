import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewBookViewComponent } from './add-new-book-view.component';

describe('AddNewBookViewComponent', () => {
  let component: AddNewBookViewComponent;
  let fixture: ComponentFixture<AddNewBookViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddNewBookViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewBookViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
