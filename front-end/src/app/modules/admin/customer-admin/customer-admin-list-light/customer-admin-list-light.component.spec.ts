import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerAdminListLightComponent } from './customer-admin-list-light.component';

describe('CustomerAdminListLightComponent', () => {
  let component: CustomerAdminListLightComponent;
  let fixture: ComponentFixture<CustomerAdminListLightComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [CustomerAdminListLightComponent]
    });
    fixture = TestBed.createComponent(CustomerAdminListLightComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
