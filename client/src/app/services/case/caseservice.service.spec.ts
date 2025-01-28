import { TestBed } from '@angular/core/testing';

import { CaseserviceService } from './caseservice.service';

describe('CaseserviceService', () => {
  let service: CaseserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CaseserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
