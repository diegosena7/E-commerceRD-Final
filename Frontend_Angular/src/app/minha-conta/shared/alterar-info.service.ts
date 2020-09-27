import { CartaoCliente } from './../../cadastro/shared/cartao.model';
import { AlterarSenha } from './../alterar-senha/alterar-senha.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Cliente } from '../../cadastro/shared/cliente.model';
import { Cartao } from 'src/app/cadastro/shared/cartao.model';



@Injectable({
  providedIn: 'root'
})
export class AlterarInfoService {
  
  constructor(private http: HttpClient) { }
  
  private readonly API = 'api/novoCartao';

  private readonly APIaltSenha = 'api/atualizarSenha';

  private readonly APIDEL = 'api/deletarCartaoCredito';
  
  private readonly APIBuscaCartao = 'api/listarPorId';

  putSenha(request: AlterarSenha): Observable<AlterarSenha> {
    return this.http.put<AlterarSenha>(this.APIaltSenha, request)
  }
    //Método para inserir cartoes
    postCartao(idCliente: number, request: Cartao): Observable<Cartao> {
      const URL = `${this.API}/${idCliente}`;
      return this.http.post<Cartao>(URL, request);
    }

  
    //Método para deletar cartoes
    deleteCartao(idCartaoCredito: any): Observable<Cartao> {
      const URL = `${this.APIDEL}/${idCartaoCredito}`;
      return this.http.delete<Cartao>(URL);
    }
  
    //Método para buscar cartpes
    getCartao(idCartaoCredito: number): Observable<CartaoCliente> {
      const URL = `${this.APIBuscaCartao}/${idCartaoCredito}`;
      return this.http.get<CartaoCliente>(URL);
    }

}
