import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../cadastro/shared/cliente.model';
import { Cartao } from '../../cadastro/shared/cartao.model';
import { Endereco, ResponseEnderecos } from '../../cadastro/shared/endereco.model';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ReactiveFormsRuleService } from 'ng-form-rules';
import { CadastroService } from '../../cadastro/shared/cadastro.service';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-meus-enderecos',
  templateUrl: './meus-enderecos.component.html',
  styleUrls: ['./meus-enderecos.component.css']
})
export class MeusEnderecosComponent implements OnInit {

  id: string;
  clienteId: number;
  cartao: Cartao;
  clienteResponse: Cliente;
  cliente: Cliente
  responseEndereco: ResponseEnderecos;
  validaForm: FormGroup;

  request: any = {
    idCliente: '',
    nmCliente: '',
    nrCpf: '',
    dsEmail: '',
    dtNasc: '',
    dsGenero: '',
    nrTelefone1: '',
    nrTelefone2: '',
    pwCliente: '',
    confirmarSenha: '',
    enderecos: [],
  }

  endereco: Endereco = {
    idEndereco: null,
    dsEndereco: '',
    nrEndereco: '',
    nrCep: '',
    dsBairro: '',
    dsCidade: '',
    sgEstado: '',
    nmCompleto: ''
  };

  enderecoUpdate: Endereco = {
    idEndereco: null,
    dsEndereco: '',
    nrEndereco: '',
    nrCep: '',
    dsBairro: '',
    dsCidade: '',
    sgEstado: '',
    nmCompleto: ''
  };

  enderecoInsere: any = {
    idEndereco: null,
    dsEndereco: '',
    nrEndereco: '',
    nrCep: '',
    dsBairro: '',
    dsCidade: '',
    sgEstado: '',
    nmCompleto: ''
  };

  constructor(
  
    private cadastroService: CadastroService,
    private validaCad: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private svc: ReactiveFormsRuleService,
    public datepipe: DatePipe
  ) { }

  ngOnInit(): void {
    this.cadForm();
    this.request = JSON.parse(localStorage['cliente']);
    this.getEnderecoCliente();
  }

  //Método para inclusão de endereços (OK)
  
  postEnderecoCliente() {

    this.cliente = JSON.parse(localStorage['cliente']);
    this.clienteId = this.cliente.idCliente;
    console.log(this.cliente)

    this.cadastroService.postEndereco(this.clienteId, this.enderecoInsere).subscribe(response => {
      this.enderecoInsere = response;
      console.log(this.clienteId);
      console.log(this.endereco);

      alert('Endereço cadastrado com sucesso!');
      window.location.reload();
    });

  }

  //Método para atualização de endereços (ToDo)
  putEnderecoCliente(idEndereco: number, index: number) {
    console.log("esse é o id do cliente: " + idEndereco);
    console.log("Esse é o endereço do cliente: " + this.responseEndereco);
    /* this.endereco = JSON.parse(localStorage['endereco']); console.log(this.endereco); */
    console.log("Esse é o índice do array: " + this.responseEndereco.enderecos[index].dsEndereco);
    this.enderecoUpdate.dsEndereco = this.responseEndereco.enderecos[index].dsEndereco;
    this.enderecoUpdate.dsBairro = this.responseEndereco.enderecos[index].dsBairro;
    this.enderecoUpdate.nrEndereco = this.responseEndereco.enderecos[index].nrEndereco;
    this.enderecoUpdate.nrCep = this.responseEndereco.enderecos[index].nrCep;
    this.enderecoUpdate.dsCidade = this.responseEndereco.enderecos[index].dsCidade;
    this.enderecoUpdate.sgEstado = this.responseEndereco.enderecos[index].sgEstado;
    this.enderecoUpdate.nmCompleto = this.responseEndereco.enderecos[index].nmCompleto;
    this.enderecoUpdate.idEndereco = this.responseEndereco.enderecos[index].idEndereco;
  
    this.cadastroService.updateEnderecos(idEndereco, this.enderecoUpdate).subscribe(response => {
      this.enderecoUpdate = response;

      alert('Endereço alterado com sucesso!!!');
      window.location.reload();
    })
  }


  //Método para buscar endereços (OK)
  primeiroEndereco: any;

  getEnderecoCliente() {

    this.cliente = JSON.parse(localStorage['cliente']);
    this.clienteId = this.cliente.idCliente;
    this.cadastroService.getEndereco(this.clienteId).subscribe(response => {
      this.responseEndereco = response;
    })
  }

  //Método para exclusão de endereço (OK)
  deleteEnderecoCliente(idEndereco: number) {
    if (confirm(`Deseja excluir o endereço selecionado?`)) {

      this.cliente = JSON.parse(localStorage['cliente']);
     
      console.log(this.endereco);

      this.cadastroService.deleteEndereco(this.clienteId, idEndereco).subscribe();
      this.cliente.enderecos.splice(0, 1);//Exclui posição do array
      console.log(idEndereco);

      //Reload no loalstorage após as alterações
      localStorage.setItem('cliente', JSON.stringify(this.cliente));

      alert("Endereço excluido com sucesso!!!");
      window.location.reload();
    }

  }
  cadForm() {
    this.validaForm = this.validaCad.group({
      nmCliente: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(75)]],
      nrCpf: ['', [Validators.required, Validators.minLength(11), Validators.maxLength(11), Validators.pattern(/^[0-9]*$/)]],
      dtNasc: ['', [Validators.required]],
      dsGenero: ['', [Validators.required, Validators.pattern(/^[A-Za-z]*$/)]],
      dsEmail: ['', [Validators.required, Validators.email]],
      nrTelefone1: ['', [Validators.required, Validators.minLength(10), Validators.maxLength(11), Validators.pattern(/^[0-9]*$/)]],
      nrTelefone2: ['', [Validators.minLength(10), Validators.maxLength(11), Validators.pattern(/^[0-9]*$/)]],
      pwCliente: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]],
      confirmarSenha: ['', [Validators.required, Validators.minLength(8), Validators.maxLength(30)]],
      dsEndereco: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(75)]],
      nrEndereco: ['', Validators.required],
      nrCep: ['', [Validators.required, Validators.maxLength(8), Validators.pattern(/^[0-9]*$/)]],
      dsBairro: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(75)]],
      dsCidade: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(75)]],
      sgEstado: ['', [Validators.required, Validators.pattern(/^[A-Za-z]*$/)]],
      nmCompleto: ['', [Validators.minLength(5), Validators.maxLength(75)]]
    });
  }


}
