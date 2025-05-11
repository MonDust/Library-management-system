import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailsOfBookViewComponent } from './details-of-book-view.component';

describe('DetailsOfBookViewComponent', () => {
  let component: DetailsOfBookViewComponent;
  let fixture: ComponentFixture<DetailsOfBookViewComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DetailsOfBookViewComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DetailsOfBookViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
