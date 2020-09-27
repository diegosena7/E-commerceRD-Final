import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseProdutos } from '../../categorias/shared/produto.model';


@Injectable({
    providedIn: 'root'
})

export class HeaderService {

    constructor(
        private http: HttpClient,
        private responseProdutos: ResponseProdutos
    ) { }

    private readonly APIbuscaProdutoNome = 'api/produtos/nomeFantasia';


    public getProdutoNome(query: string): Observable<ResponseProdutos[]> {
        const URL = `${this.APIbuscaProdutoNome}/${query}/${'0'}/${'0'}`;
        return this.http.get<ResponseProdutos[]>(URL);
    }

}