import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditBookViewComponent } from './edit-book-view.component';

describe('EditBookViewComponent', () => {
  let component: EditBookViewComponent;
  let fixture: ComponentFixture<EditBookViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditBookViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditBookViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
