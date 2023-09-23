import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractStatisticComponent } from './abstract-statistic.component';

describe('AbstractStatisticComponent', () => {
  let component: AbstractStatisticComponent;
  let fixture: ComponentFixture<AbstractStatisticComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractStatisticComponent]
    });
    fixture = TestBed.createComponent(AbstractStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
