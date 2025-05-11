import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddNewLibraryViewComponent } from './add-new-library-view.component';

describe('AddNewLibraryViewComponent', () => {
  let component: AddNewLibraryViewComponent;
  let fixture: ComponentFixture<AddNewLibraryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddNewLibraryViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddNewLibraryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
