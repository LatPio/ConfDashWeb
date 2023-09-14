import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAttachmentFileAddButtonComponent } from './abstract-attachment-file-add-button.component';

describe('AbstractAttachmentFileAddButtonComponent', () => {
  let component: AbstractAttachmentFileAddButtonComponent;
  let fixture: ComponentFixture<AbstractAttachmentFileAddButtonComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAttachmentFileAddButtonComponent]
    });
    fixture = TestBed.createComponent(AbstractAttachmentFileAddButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
