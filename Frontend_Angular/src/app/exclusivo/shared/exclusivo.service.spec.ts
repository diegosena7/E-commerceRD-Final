import { TestBed } from '@angular/core/testing';

import { ExclusivoService } from './exclusivo.service';

describe('ExclusivoService', () => {
  let service: ExclusivoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ExclusivoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
