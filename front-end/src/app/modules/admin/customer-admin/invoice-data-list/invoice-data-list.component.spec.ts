import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceDataListComponent } from './invoice-data-list.component';

describe('DepartmentListComponent', () => {
  let component: InvoiceDataListComponent;
  let fixture: ComponentFixture<InvoiceDataListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InvoiceDataListComponent]
    });
    fixture = TestBed.createComponent(InvoiceDataListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
