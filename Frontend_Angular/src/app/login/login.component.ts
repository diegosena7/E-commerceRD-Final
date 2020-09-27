import { Endereco } from './../cadastro/shared/endereco.model';
import { Cliente } from './../cadastro/shared/cliente.model';
import { Login } from './shared/login.model';
import { ResponseCliente } from './shared/responseCliente.model';
import { LoginService } from './shared/login.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Cartao } from '../cadastro/shared/cartao.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  request: Login = {
    login: "",
    senha: ""
  }

  cartao: Cartao = {
    idCartaoCredito: null,
    nrNumeroCartao: null,
    nmNomeTitular: null,
    idCliente: null
  }

  endereco: Endereco = {
    idEndereco: null,
    dsEndereco: null,
    nrEndereco: null,
    nrCep: null,
    dsBairro: null,
    dsCidade: null,
    sgEstado: null,
    nmCompleto: null,
  }

  cliente: Cliente = {
    idCliente: null,
    nmCliente: null,
    nrCpf: null,
    dsEmail: null,
    dtNasc: null,
    dsGenero: null,
    nrTelefone1: null,
    nrTelefone2: null,
    pwCliente: null,
    confirmarSenha: null,
    enderecos: [],
    cartoesCreditoDTO: []
  }

  responseCliente: ResponseCliente = {
    status: null,
    mensagem: null,
    retorno: this.cliente,
    dtTimestampErro: null,
  }

  constructor(
    private loginService: LoginService,
    private router: Router
  ) { }

  ngOnInit(): void {

  }

  erro: any;

  logar(): void {
    if (this.request == null) {
      alert('Os dados de login não estão corretos, favor verificar o E-mail, CPF ou senha e tente novamente!');
    } else {
      this.loginService.postCliente(this.request).subscribe(
        request => {
        this.responseCliente = request;
          localStorage['cliente'] = JSON.stringify(this.responseCliente.retorno)
          this.router.navigate(['']);
          alert("Cliente logado com sucesso!");
        },err => this.erro = err.status
      );
    }
  }
}
