import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditLibraryViewComponent } from './edit-library-view.component';

describe('EditLibraryViewComponent', () => {
  let component: EditLibraryViewComponent;
  let fixture: ComponentFixture<EditLibraryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EditLibraryViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(EditLibraryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
