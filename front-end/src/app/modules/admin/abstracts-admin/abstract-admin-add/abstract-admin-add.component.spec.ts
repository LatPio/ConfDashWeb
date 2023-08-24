import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminAddComponent } from './abstract-admin-add.component';

describe('AbstractAdminAddComponent', () => {
  let component: AbstractAdminAddComponent;
  let fixture: ComponentFixture<AbstractAdminAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminAddComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
