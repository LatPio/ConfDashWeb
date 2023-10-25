import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerCardSmallComponent } from './customer-card-small.component';

describe('CustomerCardSmallComponent', () => {
  let component: CustomerCardSmallComponent;
  let fixture: ComponentFixture<CustomerCardSmallComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerCardSmallComponent]
    });
    fixture = TestBed.createComponent(CustomerCardSmallComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
