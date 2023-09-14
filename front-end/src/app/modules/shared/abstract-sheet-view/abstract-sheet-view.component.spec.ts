import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractSheetViewComponent } from './abstract-sheet-view.component';

describe('AbstractSheetViewComponent', () => {
  let component: AbstractSheetViewComponent;
  let fixture: ComponentFixture<AbstractSheetViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractSheetViewComponent]
    });
    fixture = TestBed.createComponent(AbstractSheetViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
