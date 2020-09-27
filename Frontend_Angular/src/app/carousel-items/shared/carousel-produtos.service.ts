import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResponseProdutos } from 'src/app/categorias/shared/produto.model';

@Injectable({
  providedIn: 'root'
})
export class CarouselProdutosService {

  constructor(private http: HttpClient) { }

  private readonly APIDestaque = 'api/produtos/destaqueSemana';

  // private readonly APIMVendidos = 'api/produtos/populares';

  // private readonly APIPromo = 'api/produtos/produtoPromo';

  public getProdutosDestaque() {
    return this.http.get<ResponseProdutos[]>(this.APIDestaque);
  }

  // public getProdutosMVendidos() {
  //   return this.http.get<ResponseProdutos[]>(this.APIMVendidos);
  // }

  // public getProdutosPromo() {
  //   return this.http.get<ResponseProdutos[]>(this.APIPromo);
  // }
}
