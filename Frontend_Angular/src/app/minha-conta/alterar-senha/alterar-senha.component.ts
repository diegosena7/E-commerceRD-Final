import { AlterarInfoService } from './../shared/alterar-info.service';
import { Component, OnInit } from '@angular/core';
import { Cliente } from '../../cadastro/shared/cliente.model';
import { AlterarSenha } from './alterar-senha.model';

@Component({
  selector: 'app-alterar-senha',
  templateUrl: './alterar-senha.component.html',
  styleUrls: ['./alterar-senha.component.css']
})
export class AlterarSenhaComponent implements OnInit {

  cliente: Cliente;

  request: any = {
    idCliente: null,
    senhaAtual: '',
    novaSenha: '',
    confirmarSenha: ''
  }

  constructor(private alterarInfo: AlterarInfoService) { }

  ngOnInit(): void {
  }

  clienteNew: any;

  alterarSenha() {

    this.cliente = JSON.parse(localStorage['cliente']);

    //Descriptografia da senha (atob + npm install --save angular-base64)
    let senha: string = atob(this.cliente.pwCliente);

    //Criptografa a senha (btoa)
    this.clienteNew = btoa(this.cliente.pwCliente);

    if (this.request.novaSenha != this.request.confirmarSenha) {
      alert('Nova senha e confirmar nova senha são diferefentes, favor verificar!');
    } else if (senha != this.request.senhaAtual) {
      alert('A senha atual não é a mesma cadastrada em nosso sistema, favor verificar!');
    } else if(senha == this.request.senhaAtual){
      this.request.idCliente = this.cliente.idCliente;
      this.cliente.pwCliente = btoa(this.request.senhaAtual);
      //Reload no loalstorage após as alterações
      localStorage.setItem('cliente', JSON.stringify(this.cliente));
      alert("Senha alterada com sucesso!");

      this.alterarInfo.putSenha(this.request).subscribe(response => {
        this.request = response;

        window.location.reload();
      })    
    }
  }

}
