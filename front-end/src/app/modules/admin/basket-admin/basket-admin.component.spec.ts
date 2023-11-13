import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasketAdminComponent } from './basket-admin.component';

describe('BasketAdminComponent', () => {
  let component: BasketAdminComponent;
  let fixture: ComponentFixture<BasketAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BasketAdminComponent]
    });
    fixture = TestBed.createComponent(BasketAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
