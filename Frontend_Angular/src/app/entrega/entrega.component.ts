import { CadastroService } from 'src/app/cadastro/shared/cadastro.service';
import { Endereco, ResponseEnderecos } from './../cadastro/shared/endereco.model';
import { ReactiveFormsRuleService } from 'ng-form-rules';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../cadastro/shared/cliente.model';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-entrega',
  templateUrl: './entrega.component.html',
  styleUrls: ['./entrega.component.css']
})
export class EntregaComponent implements OnInit {

  cliente: Cliente;
  endereco: Endereco;
  clienteId: number;
  ok: boolean = false;

  enderecoInsere: Endereco = {
    idEndereco: null,
    dsEndereco: '',
    nrEndereco: '',
    nrCep: '',
    dsBairro: '',
    dsCidade: '',
    sgEstado: '',
    nmCompleto: ''
  };

  responseEndereco: ResponseEnderecos;

  constructor(
    private cadastroService: CadastroService,
    private validaCad: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private svc: ReactiveFormsRuleService
  ) { }

  ngOnInit(): void {
    this.getEnderecoCliente();
  }

  getEnderecoCliente() {
    this.cliente = JSON.parse(localStorage['cliente']);
    this.clienteId = this.cliente.idCliente;
    this.cadastroService.getEndereco(this.clienteId).subscribe(response => {
      this.responseEndereco = response;
    })
  }

  postEnderecoCliente() {

    this.cliente = JSON.parse(localStorage['cliente']);
    this.clienteId = this.cliente.idCliente;

    this.cadastroService.postEndereco(this.clienteId, this.enderecoInsere).subscribe(response => {
      this.enderecoInsere = response;
      console.log(this.clienteId);
      console.log(this.endereco);

      alert('Endereço cadastrado com sucesso!');
      window.location.reload();
    });

  }

  verificar(idEndereco: number){
    if(idEndereco === null){
      this.ok = false;
    } else {
      this.ok = true;
    }
    localStorage['enderecoCompra']=JSON.stringify(idEndereco);
  }

  data() {
    let data1 = new Date('08/01/2018');
    let dataAtual = new Date();

    let dataCartao = data1.getTime();
    let dataComparativa = dataAtual.getTime();

    if(dataCartao < dataComparativa) {
      console.log('Cartão vencido');
    } else {
      console.log('compra concluída');
    }
  }

}
