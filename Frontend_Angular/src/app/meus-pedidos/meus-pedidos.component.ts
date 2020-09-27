import { Router } from '@angular/router';
import { PedidoService } from './shared/pedido.service';
import { ResponseProdutos } from './../categorias/shared/produto.model';
import { Pedido, ResponsePedidos } from './shared/pedido.model';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cadastro/shared/cliente.model';
import { CestaService } from '../cesta/shared/cesta.service';
import { ProdutoService } from '../categorias/shared/produto.service';

@Component({
  selector: 'app-meus-pedidos',
  templateUrl: './meus-pedidos.component.html',
  styleUrls: ['./meus-pedidos.component.css']
})
export class MeusPedidosComponent implements OnInit {

  pedido: Pedido = {
    tipoVenda: null,
    aberto: 'small',
    idPedido: null,
    dtCompra: '',
    vlTotalPedido: null,
    qtItensPedido: null,
    idCliente: null,
    dsStatusPedido: null,
    items: [],
  };

  cliente: Cliente;
  id: number;
  idRecompra: number;
  responsePedidos: ResponsePedidos[];
  responseProdutos: ResponseProdutos[];
  cdProduto: string;
  pedidos: any;

  constructor(
    private cestaService: CestaService,
    private pedidoService: PedidoService,
    private produtoService: ProdutoService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.buscarPedidos();
    this.recompra(this.pedido.idPedido);
  }

  buscarPedidos() {//Método usado para exibir os pedidos e seus itens na tela de histórico de pedidos
    this.cliente = (JSON.parse(localStorage['cliente']));
    this.id = this.cliente.idCliente;
    this.pedidoService.getPedido(this.id).subscribe(response => {
      this.responsePedidos = response;
      console.log(this.responsePedidos);

      this.responsePedidos.forEach(pedidos => {
        if (pedidos.pedidos) {
          pedidos.pedidos.forEach(pedido => {
            pedido.aberto = 'small';
          })
        }
      });
    });
  }

  recompra(idPedido: number) {
    //Fazer rota encaminhado os produtos do pedido para a tela de cesta
    //localStorage.setItem('produtos', JSON.stringify(this.itens));
    this.idRecompra = this.pedido.idPedido;
    this.pedidoService.getBuscaIdPedido(this.idRecompra).subscribe(response => {
      /* this.responsePedidos = response; */
      console.log(this.idRecompra);
    });

    //Passando pedidos para a rota de cesta
    this.router.navigate(['/cesta', idPedido]);
    window.alert('Pedido adicionado na cesta para compra');

    //Setando pedidos no localStorage
    localStorage.setItem('idPedido', JSON.stringify(this.pedido.idPedido));
    console.log(this.idRecompra);

    //Recuperando informações do pedido do localStorage
    let pedidosCesta: string;
    pedidosCesta = this.pedido.idPedido = JSON.parse(localStorage.getItem('idPedido'))
  }

  addCestaCompras(idPedido: number) {
    // this.pedidoService.getBuscaIdPedido(idPedido)

    this.pedidoService.getBuscaIdPedido(Number(idPedido)).subscribe(response => { this.responseProdutos = response; console.log(this.pedido.idPedido); console.log(this.responseProdutos); 
      let produtos = localStorage['produtos'] = JSON.stringify(this.responseProdutos);

      this.cestaService.histAddCesta(this.responseProdutos);
    });
  
    window.alert('O produto foi adicionado a cesta!!');
  }

  calculaCesta() {
    return this.cestaService.calculaCesta();
  }
  contaCesta(){
    return this.cestaService.contaCesta();
  }

}
