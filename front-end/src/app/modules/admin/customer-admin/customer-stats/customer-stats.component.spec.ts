import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerStatsComponent } from './customer-stats.component';

describe('CustomerStatsComponent', () => {
  let component: CustomerStatsComponent;
  let fixture: ComponentFixture<CustomerStatsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerStatsComponent]
    });
    fixture = TestBed.createComponent(CustomerStatsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
