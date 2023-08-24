import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminUpdateComponent } from './abstract-admin-update.component';

describe('AbstractAdminUpdateComponent', () => {
  let component: AbstractAdminUpdateComponent;
  let fixture: ComponentFixture<AbstractAdminUpdateComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminUpdateComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
