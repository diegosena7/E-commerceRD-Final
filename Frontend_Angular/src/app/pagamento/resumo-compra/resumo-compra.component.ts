import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cliente } from 'src/app/cadastro/shared/cliente.model';
import { CestaService } from 'src/app/cesta/shared/cesta.service';
import { ResponseResumo, ResumoCompra } from './shared/resumo-compra.model';
import { ResumoCompraService } from './shared/resumo-compra.service';

@Component({
  selector: 'app-resumo-compra',
  templateUrl: './resumo-compra.component.html',
  styleUrls: ['./resumo-compra.component.css']
})
export class ResumoCompraComponent implements OnInit {

  cliente: Cliente;
  idCliente: number;

  resumoCompra: ResumoCompra = {
    titulo: '',
    nrCpf: '',
    idNF: null,
    dsEmail: '',
    nrPedido: null,
    formaPagamento: '',
    nmNomeTitular: '',
    nrNumeroCartao: '',
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

  responseResumos: ResponseResumo;


  constructor(
    private resumoService: ResumoCompraService,
    private cestaService: CestaService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cliente = JSON.parse(localStorage['cliente']);
    this.UltimaCompra();
    console.log(this.resumoCompra);
  }

  UltimaCompra() {
    this.cliente = JSON.parse(localStorage['cliente']);
    this.idCliente = this.cliente.idCliente;
    this.resumoService.getUltimoDF(this.idCliente).subscribe(response =>
      this.resumoCompra = response);
  }

  Concluir() {
    localStorage.removeItem('produtos');
    localStorage.removeItem('enderecoCompra');
    this.cestaService.limpaCesta();
    this.router.navigate(['']);
  }

}
