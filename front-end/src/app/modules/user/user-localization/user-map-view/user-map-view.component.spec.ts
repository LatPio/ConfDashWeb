import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserMapViewComponent } from './user-map-view.component';

describe('UserMapViewComponent', () => {
  let component: UserMapViewComponent;
  let fixture: ComponentFixture<UserMapViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserMapViewComponent]
    });
    fixture = TestBed.createComponent(UserMapViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
