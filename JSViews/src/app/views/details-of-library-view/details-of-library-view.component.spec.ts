import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsOfLibraryViewComponent } from './details-of-library-view.component';

describe('DetailsOfLibraryViewComponent', () => {
  let component: DetailsOfLibraryViewComponent;
  let fixture: ComponentFixture<DetailsOfLibraryViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsOfLibraryViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsOfLibraryViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
