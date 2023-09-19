import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractCardViewComponent } from './abstract-card-view.component';

describe('AbstrctCardViewComponent', () => {
  let component: AbstractCardViewComponent;
  let fixture: ComponentFixture<AbstractCardViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractCardViewComponent]
    });
    fixture = TestBed.createComponent(AbstractCardViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
