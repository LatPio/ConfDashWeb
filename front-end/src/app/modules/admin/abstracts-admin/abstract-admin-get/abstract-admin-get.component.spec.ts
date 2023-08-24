import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminGetComponent } from './abstract-admin-get.component';

describe('AbstractAdminGetComponent', () => {
  let component: AbstractAdminGetComponent;
  let fixture: ComponentFixture<AbstractAdminGetComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminGetComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminGetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
