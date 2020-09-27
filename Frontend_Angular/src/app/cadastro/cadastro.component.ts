import { Cadastro } from './shared/cadastro.model';
import { Cliente } from './shared/cliente.model';
import { CadastroService } from './shared/cadastro.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm, FormGroup, Validators, FormBuilder, FormControl, FormArray } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AbstractModelSettings, ReactiveFormsRuleService, AdhocModelSettings, ResultsPassRequirement } from 'ng-form-rules';
import { map } from 'rxjs/operators';
import { isValid } from 'cpf';
import { HttpClient } from '@angular/common/http';
import { ResponseCliente } from '../login/shared/responseCliente.model';


@Component({
  selector: 'app-cadastro',
  templateUrl: './cadastro.component.html',
  styleUrls: ['./cadastro.component.css']
})
export class CadastroComponent implements OnInit {

  @ViewChild('it', {static: true}) it: NgForm;
 
  valida: boolean;
  CPF: number;
  cliente: Cliente;
  validaForm: FormGroup;
  modelSettings: AbstractModelSettings<Cadastro>;

  request: Cliente = {
    idCliente: null,
    nmCliente: '',
    nrCpf: '',
    dsEmail: '',
    dtNasc: '',
    dsGenero: '',
    nrTelefone1: '',
    nrTelefone2: '',
    pwCliente: '',
    confirmarSenha: '',
    enderecos: [{
      idEndereco: null,
      dsEndereco: '',
      nrEndereco: '',
      nrCep: '',
      dsBairro: '',
      dsCidade: '',
      sgEstado: '',
      nmCompleto: ''
    }],
    cartoesCreditoDTO: null
  }

  responseCliente: ResponseCliente = {
    status: null,
    mensagem: null,
    retorno: this.request,
    dtTimestampErro: null,
  }

  onSubmit() { }

  constructor(
    private validaCad: FormBuilder,
    private cadastroService: CadastroService,
    private route: ActivatedRoute,
    private router: Router,
    private svc: ReactiveFormsRuleService,
    private http: HttpClient

  ) {

  }

  ngOnInit(): void {
    this.cadForm();
  }

  registrar() {
    this.cadastroService.postCadastro(this.request).subscribe(
      response => {
        this.responseCliente = response;
        let cliente = localStorage['cliente'] = JSON.stringify(this.responseCliente.retorno);
        alert("Cadastro realizado, você está logado!");
        this.router.navigate(['']);
      }
    )
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
 
  validar() {
    let valida = isValid(this.request.nrCpf);
    return valida;
  }

  // consultaCEP(cep, form) {
  //   cep = cep.replace(/\D/g, '');

  //   if (cep != "") {
  //     let validacep = /^[0-9]{8}$/;

  //     if(validacep.test(cep)) {
  //       this.http.get(`//viacep.com.br/ws/${cep}/json`)
  //       .pipe(map(dados => dados))
  //       .subscribe(dados => this.populaDadosForm(dados, form));
  //       // .subscribe(dados=> console.log(dados));
  //     }
  //   }
  // }

  // populaDadosForm(dados, form) {
  //   form.setValue({
  //     nome: form.value.nome,
  //     cpf: form.value.cpf,
  //     dataNasc: form.value.dataNasc, 
  //     genero: form.value.genero,
  //     email: form.value.email, 
  //     tel1: form.value.tel1,
  //     tel2: form.value.tel2, 
  //     senha: form.value.senha, 
  //     confsenha: form.value.confsenha,
  //     dsEndereco: dados.logradouro,
  //     numero: '',
  //     cep: dados.cep,
  //     bairro: dados.bairro,
  //     cidade: dados.localidade,
  //     estado: dados.uf,
  //     complemento: ''
  //   });
  // }

}
