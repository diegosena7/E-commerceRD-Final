import { ResponseProdutos } from './../categorias/shared/produto.model';
import { CestaService } from './../cesta/shared/cesta.service';
import { Cliente } from './../cadastro/shared/cliente.model';
import { PagamentoService } from './shared/pagamento.service';
import { Component, OnInit, ViewChildren, ElementRef } from '@angular/core';
import { DocumentoFiscal, ItemProduto } from './shared/pagamento.model';
import { ActivatedRoute, Router } from '@angular/router';
import { Produtos } from '../categorias/shared/produto.model';
import { Cartao, CartaoCliente } from '../cadastro/shared/cartao.model';
import { AlterarInfoService } from '../minha-conta/shared/alterar-info.service';
import { ResumoCompra } from './resumo-compra/shared/resumo-compra.model';
import { Endereco } from '../cadastro/shared/endereco.model'

@Component({
  selector: 'app-pagamento',
  templateUrl: './pagamento.component.html',
  styleUrls: ['./pagamento.component.css'],

})
export class PagamentoComponent implements OnInit {

  state: string = 'small';

  enderecoId: number;

  endereco: any;

  itensNf: Array<any> = [];

  nmNomeTitular: string;
  nrNumeroCartao: string;

  cartaoSelecionado: number;
  idPagamento: number;
  opcaoPagamentoCredito: string;
  novoMes: string;
  novoAno: string;

  requestProdutos: ItemProduto[] = [{
    cdProduto: null,
    qtProduto: null,
  }];

  request: DocumentoFiscal = {
    idCliente: null,
    idEndereco: null,
    idFormaPagamento: null,
    itensDTOPost: this.itensNf,
    nmNomeTitular: null,
    nrNumeroCartao: null,
  }

  index: number;
  selectedPagamento: boolean;
  cliente: Cliente;
  clienteId: number;
  produtos: Produtos[];

  resumoCompra: ResumoCompra = {
    titulo: '',
    nrCpf: '',
    idNF: null,
    dsEmail: '',
    nrPedido: null,
    formaPagamento: '',
    nmNomeTitular: null,
    nrNumeroCartao: null,
    endereco: {
      idEndereco: null,
      dsEndereco: '',
      nrEndereco: null,
      nrCep: '',
      dsBairro: '',
      dsCidade: '',
      sgEstado: '',
      nmCompleto: '',
    },
    valorTotalNota: null,
    itensDocumento: [{
      nrItemDocumento: null,
      qtItem: null,
      nmProduto: '',
      vlItemUnitario: null,
      vlTotalItem: null,
      pcIcms: null,
      vlIcms: null,
    }],
    qtItens: null,
  }


  constructor(

    private alterarInfoService: AlterarInfoService,
    private pagamentoService: PagamentoService,
    private cestaService: CestaService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  itens = [];

  ngOnInit(): void {
    this.selectedPagamento = false;
    this.cliente = (JSON.parse(localStorage['cliente']));
    this.produtos = (JSON.parse(localStorage['produtos']));
    this.endereco = (JSON.parse(localStorage['enderecoCompra']));
    console.log(this.endereco)
    this.buscarCartaoCliente();
    console.log(this.produtos);
  }

  calculaCesta() {
    return this.cestaService.calculaCesta();
  }


  pagamentoEValido(): boolean {
    switch (this.idPagamento) {
      case 1:
        if (this.opcaoPagamentoCredito) {
          if (this.opcaoPagamentoCredito == "1") {
            return (this.verificaData(this.novoMes, this.novoAno) &&
              this.nmNomeTitular.length > 0 &&
              this.nrNumeroCartao.length == 16
            );
          } else if (this.opcaoPagamentoCredito.indexOf("cartaoSalvo") != -1) {
            const index = this.opcaoPagamentoCredito.slice(this.opcaoPagamentoCredito.length - 1)
            return this.verificaData(this.cartoes[index].mes, this.cartoes[index].ano);
          }
        }
        else {
          return false;
        }
      case 2:
        return true;

      case 3:
        return true;

      default:
        return false;
    }
  }


  verificaData(mes: string, ano: string): boolean {
    const hoje = new Date();
    return (
      (hoje.getMonth() < (parseInt(mes, 10)) && hoje.getFullYear() == parseInt(ano, 10)) ||
      (hoje.getFullYear() < parseInt(ano, 10))
    );
  }

  clicked(): void {
    if (!this.pagamentoEValido()) {
      alert("Dados de pagamento invalidos");
      return;
    }

    this.request.idFormaPagamento = this.idPagamento;
    if (this.idPagamento == 1 && this.opcaoPagamentoCredito) {
      if (this.opcaoPagamentoCredito == "1") {
        this.request.nrNumeroCartao = this.nrNumeroCartao;
        this.request.nmNomeTitular = this.nmNomeTitular;

      } else {
        const index = this.opcaoPagamentoCredito.slice(this.opcaoPagamentoCredito.length - 1);
        this.request.nrNumeroCartao = this.cartoes[index].nrNumeroCartao;
        this.request.nmNomeTitular = this.cartoes[index].nmNomeTitular;
      }

    }
    var produtos: Array<any> = [];

    produtos = JSON.parse(localStorage['produtos']);
    this.cliente = (JSON.parse(localStorage['cliente']));
    this.itens = (JSON.parse(localStorage.getItem('produtos')));
    this.request.idCliente = this.cliente.idCliente;
    this.request.idEndereco = this.endereco;

    for (var contador = 0; contador < produtos.length; contador++) {
      let itensNf = new ItensNf;
      itensNf.cdProduto = this.itens[contador].cdProduto;
      itensNf.qtProduto = this.itens[contador].qtProduto;
      this.itensNf.push(itensNf);
    }
    console.log(this.request);

    this.pagamentoService.postNota(this.request).subscribe(
      success => console.log('Pedido realizado'),
      error => console.log('pedido não realizado'),
      () => console.log('request completo')
      // request => {
      //   this.resumoCompra = request;
      // }
    );
    this.router.navigate(['cesta/pagamento/resumo-compra']);
  }

  requestC: Cartao = {
    idCartaoCredito: null,
    nrNumeroCartao: '',
    nmNomeTitular: '',
    idCliente: null
  }

  primeiroCartao: any;
  cartoes: CartaoPagamento[];
  //Método para buscar cartão
  buscarCartaoCliente() {
    this.cartoes = [];
    this.cliente = JSON.parse(localStorage['cliente']);
    this.alterarInfoService.getCartao(this.cliente.idCliente).subscribe(response => {
      response.cartoesCreditoDTO.forEach(cartao => {
        this.cartoes.push(new CartaoPagamento(cartao));
      })
      this.primeiroCartao = this.cartoes[0];//Retorna o objeto cartão no array
      console.log(this.primeiroCartao);
      console.log(this.requestC.idCartaoCredito);

    })
  }

  inserirCartao() {
    console.log(this.cliente.cartoesCreditoDTO)
    this.cliente = JSON.parse(localStorage['cliente']);
    this.alterarInfoService.postCartao(this.cliente.idCliente, this.requestC).subscribe();
    alert("Cartão Inserido com Sucesso");
    window.location.reload();
    console.log(this.requestC);
  }

}

export class ItensNf {
  public qtProduto: number;
  public cdProduto: number;
}


class CartaoPagamento {
  idCartaoCredito: number;
  nrNumeroCartao: string;
  nmNomeTitular: string;
  idCliente: number;
  mes = '';
  ano = '';
  cvc = '';


  constructor(dados) {
    this.idCartaoCredito = dados.idCartaoCredito;
    this.nrNumeroCartao = dados.nrNumeroCartao;
    this.nmNomeTitular = dados.nmNomeTitular;
    this.idCliente = dados.idCliente;
  }

}