import { TestBed } from '@angular/core/testing';

import { AbstractsService } from './abstracts.service';

describe('AbstractsService', () => {
  let service: AbstractsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AbstractsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
