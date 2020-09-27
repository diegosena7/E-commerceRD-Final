import { ResponseProdutos } from './../categorias/shared/produto.model';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cadastro/shared/cliente.model';
import { CestaService } from './shared/cesta.service';

@Component({
  selector: 'app-cesta',
  templateUrl: './cesta.component.html',
  styleUrls: ['./cesta.component.css']
})
export class CestaComponent implements OnInit {

  cliente: Cliente;
  produtos = [];
  itens = [];
  totalCompra;
  produtoRequest: ResponseProdutos[];
  tamanho;

  constructor(private cestaService: CestaService) { }

  ngOnInit(): void {
    this.produtos=this.cestaService.getCesta();
  }

  removeCesta(index) {
    this.produtos=this.cestaService.removeCesta(index);
    window.alert('Item excluido com sucesso');
  }

  calculaCesta() {
    return this.cestaService.calculaCesta(); 
  }

  contaCesta(){
    return this.cestaService.contaCesta();
  }

  alteraCesta(index, qtd){
    this.cestaService.alteraCesta(index, qtd);
  }

}
