import { Component, OnInit } from '@angular/core';
import { Produtos, ResponseProdutos } from '../categorias/shared/produto.model';
import { ProdutoService } from '../categorias/shared/produto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { CestaService } from '../cesta/shared/cesta.service';

@Component({
  selector: 'app-produto-detalhes',
  templateUrl: './produto-detalhes.component.html',
  styleUrls: ['./produto-detalhes.component.css']
})
export class ProdutoDetalhesComponent implements OnInit {

  cd: string;

  produtos: Produtos;

  responseProdutos: ResponseProdutos[];

  constructor(
    private cestaService: CestaService,
    private produtoService: ProdutoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.produtoPorCd();
  }

  public produtoPorCd() {
    this.cd = this.route.snapshot.paramMap.get('cd');
    this.produtoService.getProdutoPorCd(this.cd).subscribe(response => {this.responseProdutos = response;
    //localStorage['produtos'] = JSON.stringify(this.responseProdutos);
    console.log(this.responseProdutos);
    } );
  }

  addCesta(responseProdutos){
    this.cestaService.addCesta(responseProdutos[0]);
    window.alert('O produto foi adicionado a cesta!!');
    console.log(responseProdutos);
  }

}
