import { ResponsePedidos } from './pedido.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ResponseProdutos } from 'src/app/categorias/shared/produto.model';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/pedidos/cliente';
  private readonly APIprodutosID = 'api/pedido';

  getPedido(id: number): Observable<ResponsePedidos[]> {
    const URL = `${this.API}/${id}`;

    return this.http.get<ResponsePedidos[]>(URL);
  }

  getRecompra(id: number): Observable<ResponsePedidos[]> {
    const URL = `${this.API}/${id}`;
    return this.http.get<ResponsePedidos[]>(URL);
  }

  getBuscaIdPedido(id: number): Observable<ResponseProdutos[]> {
    const URL = `${this.APIprodutosID}/${id}`;
    return this.http.get<ResponseProdutos[]>(URL);
  }
}
