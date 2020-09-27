import { TestBed } from '@angular/core/testing';

import { AlterarInfoService } from './alterar-info.service';

describe('AlterarInfoService', () => {
  let service: AlterarInfoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AlterarInfoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
