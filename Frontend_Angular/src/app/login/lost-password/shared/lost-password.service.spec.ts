import { TestBed } from '@angular/core/testing';

import { LostPasswordService } from './lost-password.service';

describe('LostPasswordService', () => {
  let service: LostPasswordService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LostPasswordService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
