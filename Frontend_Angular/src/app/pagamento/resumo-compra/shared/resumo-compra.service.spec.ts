import { TestBed } from '@angular/core/testing';

import { ResumoCompraService } from './resumo-compra.service';

describe('ResumoCompraService', () => {
  let service: ResumoCompraService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ResumoCompraService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
