import { Router } from '@angular/router';
import { ProdutoService } from './../categorias/shared/produto.service';
import { ExclusivoService } from './shared/exclusivo.service';
import { CestaService } from './../cesta/shared/cesta.service';
import { Produtos, ResponseProdutos } from './../categorias/shared/produto.model';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cadastro/shared/cliente.model';


@Component({
  selector: 'app-exclusivo',
  templateUrl: './exclusivo.component.html',
  styleUrls: ['./exclusivo.component.css']
})

export class ExclusivoComponent implements OnInit {

  produtos = [];
  responseProdutos: ResponseProdutos[];

  cliente: Cliente;
  id: any;

  constructor(
    private cestaService: CestaService,
    private produtoService: ProdutoService,
    private exclusivoService: ExclusivoService,
    private router: Router) { }

  ngOnInit(): void {
    // this.produtos=this.cestaService.getCesta();
    this.getExclusivo();
  }


  getExclusivo() { //tentando buscar produtos @.@
    this.cliente = (JSON.parse(localStorage['cliente']));
    console.log(this.cliente);
    this.id = this.cliente.idCliente;
    console.log(this.id);
    this.exclusivoService.getProdutoExclusivo(this.id).subscribe(response => {
      this.responseProdutos = response;

      console.log(this.responseProdutos)

    });
  }

  addCestaExclusivo(produto) {
    console.log(produto)
    this.cestaService.addCesta(produto);
    window.alert('O produto foi adicionado a cesta!!');
 
  }
}

