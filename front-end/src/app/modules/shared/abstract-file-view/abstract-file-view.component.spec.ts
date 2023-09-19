import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractFileViewComponent } from './abstract-file-view.component';

describe('AbstractFileViewComponent', () => {
  let component: AbstractFileViewComponent;
  let fixture: ComponentFixture<AbstractFileViewComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractFileViewComponent]
    });
    fixture = TestBed.createComponent(AbstractFileViewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
