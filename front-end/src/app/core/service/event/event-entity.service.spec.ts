import { TestBed } from '@angular/core/testing';

import { EventEntityService } from './event-entity.service';

describe('EventEntityService', () => {
  let service: EventEntityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EventEntityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
