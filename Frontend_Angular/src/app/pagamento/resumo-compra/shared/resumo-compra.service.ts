import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResumoCompra, ResponseResumo } from './resumo-compra.model';

@Injectable({
  providedIn: 'root'
})
export class ResumoCompraService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/ultimaNotaCliente/';

  public getUltimoDF(id: number): Observable<ResumoCompra> {
    const URL= `${this.API}/${id}`;

    return this.http.get<ResumoCompra>(URL);
  }
}
