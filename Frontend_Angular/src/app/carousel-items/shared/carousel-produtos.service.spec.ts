import { TestBed } from '@angular/core/testing';

import { CarouselProdutosService } from './carousel-produtos.service';

describe('CarouselProdutosService', () => {
  let service: CarouselProdutosService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CarouselProdutosService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
