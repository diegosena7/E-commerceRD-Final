import { CarouselProdutosService } from './shared/carousel-produtos.service';
import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';
import { ResponseProdutos } from '../categorias/shared/produto.model';
import { Router } from '@angular/router';

@Component({
  selector: 'app-carousel-items',
  templateUrl: './carousel-items.component.html',
  styleUrls: ['./carousel-items.component.css']
})
export class CarouselItemsComponent implements OnInit {

  carousel: boolean;

  responseProdutos: ResponseProdutos[];
  // responseProdutosMVendido: ResponseProdutos[];
  // responseProdutosPromo: ResponseProdutos[];

  constructor(
    private carouselProdutosService: CarouselProdutosService,
    private router: Router
  ) { }

  ngOnInit(): void {
    // this.carousel = true;
    this.listarProdutosCarousel();
  }

  listarProdutosCarousel() {
    this.carouselProdutosService.getProdutosDestaque().subscribe(response => {
      this.responseProdutos = response;
      
    });
    
  }

  clicked(cd: number) {
    this.router.navigateByUrl('categorias/produto', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/produto/${cd}`]);
    });
  }

}
