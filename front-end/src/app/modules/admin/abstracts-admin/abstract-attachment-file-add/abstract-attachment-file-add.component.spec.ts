import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AbstractAttachmentFileAddComponent } from './abstract-attachment-file-add.component';

describe('AbstractAttachmentFileAddComponent', () => {
  let component: AbstractAttachmentFileAddComponent;
  let fixture: ComponentFixture<AbstractAttachmentFileAddComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AbstractAttachmentFileAddComponent]
    });
    fixture = TestBed.createComponent(AbstractAttachmentFileAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
