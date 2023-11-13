import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BasketAdminListComponent } from './basket-admin-list.component';

describe('BasketAdminListComponent', () => {
  let component: BasketAdminListComponent;
  let fixture: ComponentFixture<BasketAdminListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BasketAdminListComponent]
    });
    fixture = TestBed.createComponent(BasketAdminListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
