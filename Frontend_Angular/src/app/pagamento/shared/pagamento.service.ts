import { Observable } from 'rxjs';
import { DocumentoFiscal } from './pagamento.model';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ResumoCompra } from './../resumo-compra/shared/resumo-compra.model'

@Injectable({
  providedIn: 'root'
})
export class PagamentoService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/adicionaNota';

  postNota(request: DocumentoFiscal): Observable<ResumoCompra> {
    return this.http.post<ResumoCompra>(this.API, request);
  }

}
