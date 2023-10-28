import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InvoiceDataAddComponent } from './invoice-data-add.component';

describe('DepartmentAddComponent', () => {
  let component: InvoiceDataAddComponent;
  let fixture: ComponentFixture<InvoiceDataAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [InvoiceDataAddComponent]
    });
    fixture = TestBed.createComponent(InvoiceDataAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
