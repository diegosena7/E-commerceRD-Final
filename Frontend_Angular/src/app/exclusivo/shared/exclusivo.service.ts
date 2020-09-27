import { Cliente } from './../../cadastro/shared/cliente.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseProdutos } from '../../categorias/shared/produto.model';




@Injectable({
  providedIn: 'root'
})
export class ExclusivoService {

  cestaService: any;

  constructor(private http: HttpClient) { }

  private readonly API = 'api/cupomDesconto';

  getProdutoExclusivo(id: number): Observable<ResponseProdutos[]> {
    const URL = `${this.API}/${id}`;
    return this.http.get<ResponseProdutos[]>(URL)
  }

}