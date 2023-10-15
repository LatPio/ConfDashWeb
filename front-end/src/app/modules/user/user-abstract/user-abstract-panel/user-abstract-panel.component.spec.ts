import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserAbstractPanelComponent } from './user-abstract-panel.component';

describe('UserAbstractPanelComponent', () => {
  let component: UserAbstractPanelComponent;
  let fixture: ComponentFixture<UserAbstractPanelComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserAbstractPanelComponent]
    });
    fixture = TestBed.createComponent(UserAbstractPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
