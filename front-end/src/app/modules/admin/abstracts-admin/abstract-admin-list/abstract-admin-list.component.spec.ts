import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAdminListComponent } from './abstract-admin-list.component';

describe('AbstractAdminListComponent', () => {
  let component: AbstractAdminListComponent;
  let fixture: ComponentFixture<AbstractAdminListComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAdminListComponent]
    });
    fixture = TestBed.createComponent(AbstractAdminListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
