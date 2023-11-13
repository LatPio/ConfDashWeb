import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasketAdminAddComponent } from './basket-admin-add.component';

describe('BasketAdminAddComponent', () => {
  let component: BasketAdminAddComponent;
  let fixture: ComponentFixture<BasketAdminAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BasketAdminAddComponent]
    });
    fixture = TestBed.createComponent(BasketAdminAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
