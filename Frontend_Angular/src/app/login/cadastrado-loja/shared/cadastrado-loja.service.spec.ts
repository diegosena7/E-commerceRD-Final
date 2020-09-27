import { TestBed } from '@angular/core/testing';

import { CadastradoLojaService } from './cadastrado-loja.service';

describe('CadastradoLojaService', () => {
  let service: CadastradoLojaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CadastradoLojaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
