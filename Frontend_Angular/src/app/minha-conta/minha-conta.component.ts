import { ReactiveFormsRuleService } from 'ng-form-rules';
import { Cartao } from './../cadastro/shared/cartao.model';
import { Endereco, ResponseEnderecos } from './../cadastro/shared/endereco.model';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Cliente } from '../cadastro/shared/cliente.model';
import { FormBuilder } from '@angular/forms';
import { CadastroService } from '../cadastro/shared/cadastro.service';
import { AlterarInfoService } from '../minha-conta/shared/alterar-info.service'
import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-minha-conta',
  templateUrl: './minha-conta.component.html',
  styleUrls: ['./minha-conta.component.css']
})
export class MinhaContaComponent implements OnInit {

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
  }


}
