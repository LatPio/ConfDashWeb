import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractToProcessComponent } from './abstract-to-process.component';

describe('AbstractToProcessComponent', () => {
  let component: AbstractToProcessComponent;
  let fixture: ComponentFixture<AbstractToProcessComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractToProcessComponent]
    });
    fixture = TestBed.createComponent(AbstractToProcessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
