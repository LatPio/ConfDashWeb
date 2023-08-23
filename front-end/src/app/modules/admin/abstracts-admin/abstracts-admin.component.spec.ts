import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractsAdminComponent } from './abstracts-admin.component';

describe('AbstractsAdminComponent', () => {
  let component: AbstractsAdminComponent;
  let fixture: ComponentFixture<AbstractsAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractsAdminComponent]
    });
    fixture = TestBed.createComponent(AbstractsAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
