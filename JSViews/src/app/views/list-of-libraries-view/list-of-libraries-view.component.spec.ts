import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfLibrariesViewComponent } from './list-of-libraries-view.component';

describe('ListOfLibrariesViewComponent', () => {
  let component: ListOfLibrariesViewComponent;
  let fixture: ComponentFixture<ListOfLibrariesViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ListOfLibrariesViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ListOfLibrariesViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
