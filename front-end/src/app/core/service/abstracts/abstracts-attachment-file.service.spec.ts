import { TestBed } from '@angular/core/testing';

import { AbstractsAttachmentFileService } from './abstracts-attachment-file.service';

describe('AbstractsAttachmentFileService', () => {
  let service: AbstractsAttachmentFileService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AbstractsAttachmentFileService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
