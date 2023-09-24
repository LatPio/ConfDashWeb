import { TestBed } from '@angular/core/testing';

import { InformationlinksService } from './informationlinks.service';

describe('InformationlinksService', () => {
  let service: InformationlinksService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(InformationlinksService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
