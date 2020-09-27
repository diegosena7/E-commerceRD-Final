import { Cliente } from './cliente.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Endereco, ResponseEnderecos } from './endereco.model';
import { ResponseCliente } from 'src/app/login/shared/responseCliente.model';

@Injectable({
  providedIn: 'root'
})
export class CadastroService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/novoCadastro/';

  private readonly APIatualizacadastro = 'api/atualizarCadastro';

  private readonly APIatualizaEndereco = 'api/atualizaEndereco';

  private readonly APINovoEndereco = 'api/novoEndereco';

  private readonly APIBuscaEndereco = 'api/listarPorId';

  private readonly APIDeleteEndereco = 'api/deletarEndereco';

  
  updateDadosCadastrais(request: Cliente): Observable<Cliente> {
    // const URL = `${this.APIatualizacadastro}/${id}`;
    return this.http.put<Cliente>(this.APIatualizacadastro, request);
  }

  postCadastro(request: Cliente): Observable<ResponseCliente> {
    return this.http.post<ResponseCliente>(this.API, request);
  }

  postEndereco(id: number, request: Endereco): Observable<Endereco> {
    const URL = `${this.APINovoEndereco}/${id}`;
    return this.http.post<Endereco>(URL, request);
  }

  //Método para atualizar o endereço
  updateEnderecos(id: number, request: Endereco): Observable<Endereco> {
    const URL = `${this.APIatualizaEndereco}/${id}`;
    return this.http.put<Endereco>(URL, request);
  }

  //Método para buscar endereços
  getEndereco(id: number): Observable<ResponseEnderecos> {
    const URL = `${this.APIBuscaEndereco}/${id}`;
    return this.http.get<ResponseEnderecos>(URL);
  }

  //Método de excluir endereços
  deleteEndereco(idCliente: number, idEndereco: number): Observable<Endereco> {
    const URL = `${this.APIDeleteEndereco}/${idCliente}/${idEndereco}`;
    return this.http.delete<Endereco>(URL);
  }
}
