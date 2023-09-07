import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminToProcessComponent } from './abstract-admin-to-process.component';

describe('AbstractsAdminToProcessComponent', () => {
  let component: AbstractAdminToProcessComponent;
  let fixture: ComponentFixture<AbstractAdminToProcessComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminToProcessComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminToProcessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
