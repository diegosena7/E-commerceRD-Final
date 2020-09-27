import { Cartao, CartaoCliente } from './../../cadastro/shared/cartao.model';
import { Endereco } from './../../cadastro/shared/endereco.model';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../cadastro/shared/cliente.model';
import { AlterarInfoService } from '../shared/alterar-info.service';
import { FormBuilder } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ReactiveFormsRuleService } from 'ng-form-rules';


@Component({
  selector: 'app-meus-cartoes',
  templateUrl: './meus-cartoes.component.html',
  styleUrls: ['./meus-cartoes.component.css']
})
export class MeusCartoesComponent implements OnInit {

  cliente: Cliente;
  endereco: Endereco;
  clienteId: number;

  request: Cartao = {
    idCartaoCredito: null,
    nrNumeroCartao: '',
    nmNomeTitular: '',
    idCliente: null
  }

  constructor(
    private alterarInfoService: AlterarInfoService,
    private validaCad: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private svc: ReactiveFormsRuleService
  ) { }

  ngOnInit(): void {
    this.getCliente();
    this.buscarCartaoCliente();
    console.log("oi");
  }

  private getCliente() {
    this.cliente = JSON.parse(localStorage['cliente']);
  }

  inserirCartao() {
    console.log(this.cliente.cartoesCreditoDTO)
    this.cliente = JSON.parse(localStorage['cliente']);
    this.alterarInfoService.postCartao(this.cliente.idCliente, this.request).subscribe();
    alert("Cartão Inserido com Sucesso");
    window.location.reload();
    console.log(this.request);
  }

  apagarCartao(idCartaoCredito: number) {
    console.log(this.cliente.cartoesCreditoDTO);
    this.cliente = JSON.parse(localStorage['cliente']);
    this.alterarInfoService.deleteCartao(idCartaoCredito).subscribe();
    console.log(this.primeiroCartao);

    this.cliente.cartoesCreditoDTO.splice(0, 1);//Exclui posição do array
    localStorage.setItem('cliente', JSON.stringify(this.cliente));
    alert("Cartão excluido com Sucesso");
    window.location.reload();
  }

  primeiroCartao: any;
  cartoes: CartaoCliente;
  //Método para buscar cartão
  buscarCartaoCliente() {
    this.cliente = JSON.parse(localStorage['cliente']);
    this.alterarInfoService.getCartao(this.cliente.idCliente).subscribe(response => {
      this.cartoes = response;
      this.primeiroCartao = this.cartoes.cartoesCreditoDTO[0];//Retorna o objeto cartão no array
      console.log(this.primeiroCartao);
      console.log(this.request.idCartaoCredito);

    })
  }

}
