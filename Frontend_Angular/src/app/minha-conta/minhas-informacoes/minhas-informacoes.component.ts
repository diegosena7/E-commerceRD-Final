import { Component, OnInit } from '@angular/core';
import { AlterarInfoService } from '../shared/alterar-info.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReactiveFormsRuleService } from 'ng-form-rules';
import { DatePipe } from '@angular/common';
import { Cliente } from '../../cadastro/shared/cliente.model';
import { isValid } from 'cpf';
import { CadastroService } from '../../cadastro/shared/cadastro.service';
import { Cartao } from '../../cadastro/shared/cartao.model';

@Component({
  selector: 'app-minhas-informacoes',
  templateUrl: './minhas-informacoes.component.html',
  styleUrls: ['./minhas-informacoes.component.css']
})
export class MinhasInformacoesComponent implements OnInit {

  cliente: Cliente
  id: string;
  idCliente: number;
  cartao: Cartao;
  clienteResponse: Cliente;
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

  constructor(

  
    private alteraInfoService: AlterarInfoService,
    private cadastroService: CadastroService,
    private validaCad: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private svc: ReactiveFormsRuleService,
    public datepipe: DatePipe
  ) { }

  ngOnInit(): void {
    /* this.getCliente(); */
    this.cadForm();
    this.request = JSON.parse(localStorage['cliente']);
    this.id = this.route.snapshot.paramMap.get('id');
    this.request.dtNasc = this.datepipe.transform(this.request.dtNasc, 'yyyy-MM-dd');
  }

  private getCliente() { }

  //Método para atualização de dados cadastrais (OK)
  updateDadosCliente() {

    this.cliente = JSON.parse(localStorage['cliente']);
    console.log(this.request);
    this.cadastroService.updateDadosCadastrais(this.request).subscribe(response => {

      this.clienteResponse = response;
      console.log(this.clienteResponse);
      console.log('cliente');
      this.cliente.nmCliente = this.request.nmCliente;
      this.cliente.dsEmail = this.request.dsEmail;
      this.cliente.dtNasc = this.request.dtNasc;
      this.cliente.dsGenero = this.request.dsGenero;
      this.cliente.nrTelefone1 = this.request.nrTelefone1;
      this.cliente.nrTelefone2 = this.request.nrTelefone2;
      localStorage.setItem('cliente', JSON.stringify(this.cliente));

      alert('Dados alterados com sucesso!!!');
      window.location.reload();
    });

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

}
