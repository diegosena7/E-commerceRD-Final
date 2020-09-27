import { ResponseFabricantes } from './fabricante.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseProdutos, Produtos } from './produto.model';

@Injectable({
  providedIn: 'root'
})
export class ProdutoService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/produtos/subcategoria';

  private readonly APIDetalhes = 'api/produtos/codigo';

  private readonly APIfiltroFabricante = 'api/fabricantes';

  private readonly APIbuscaProdutoNome = 'api/produtos/nomeFantasia';

  public getProdutos() {
    return this.http.get<ResponseProdutos[]>(this.API);
  }
  
  public getProdutoPorCd(cd: string): Observable<ResponseProdutos[]> {
    const URLDetalhes = `${this.APIDetalhes}/${cd}`;

    return this.http.get<ResponseProdutos[]>(URLDetalhes);
  }

  public getProdutosPorId(id: string): Observable<ResponseProdutos[]> {
    const URL = `${this.API}/${id}`;
    return this.http.get<ResponseProdutos[]>(URL);
  }


  public getProdutosPorIdFiltro(id: string, idPreco: string, nomeFabricante: string): Observable<ResponseProdutos[]> {
    const URL = `${this.API}/${id}?idPreco=${idPreco}&nomeFabricante=${nomeFabricante}`;
    return this.http.get<ResponseProdutos[]>(URL);
  }


  public getFabricantesPorSubCategoria(id: string): Observable<ResponseFabricantes[]> {
    const URL = `${this.APIfiltroFabricante}/${id}`;

    return this.http.get<ResponseFabricantes[]>(URL);
  }

  public getFabricantesPorSubCategoriaPesquisa(query: string): Observable<ResponseFabricantes[]> {
    const URL = `${this.APIfiltroFabricante}/${query}`;
    return this.http.get<ResponseFabricantes[]>(URL);
  }

  public getProdutoNome(query: string): Observable<ResponseProdutos[]>{
    const URL = `${this.APIbuscaProdutoNome}/${query}`;
    return this.http.get<ResponseProdutos[]>(URL);
  }
  public getProdutoNomeFiltro(query: string, idPreco: string, nomeFabricante: string): Observable<ResponseProdutos[]>{
    const URL = `${this.APIbuscaProdutoNome}/${query}?idPreco=${idPreco}&nomeFabricante=${nomeFabricante}`;
    return this.http.get<ResponseProdutos[]>(URL);
  }
  
}
