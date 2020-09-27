import { CadastradoLoja } from './cadastrado-loja.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CadastradoLojaService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/clienteLoja';

  putEmail(request: CadastradoLoja): Observable<CadastradoLoja> {
    return this.http.put<CadastradoLoja>(this.API, request);
  }
}
