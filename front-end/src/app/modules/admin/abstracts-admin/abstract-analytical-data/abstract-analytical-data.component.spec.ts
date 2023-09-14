import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAnalyticalDataComponent } from './abstract-analytical-data.component';

describe('AbstractAnalyticalDataComponent', () => {
  let component: AbstractAnalyticalDataComponent;
  let fixture: ComponentFixture<AbstractAnalyticalDataComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAnalyticalDataComponent]
    });
    fixture = TestBed.createComponent(AbstractAnalyticalDataComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
