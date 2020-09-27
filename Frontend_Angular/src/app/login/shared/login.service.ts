import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

import { Login } from './login.model';
import { ResponseCliente } from './responseCliente.model';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  private readonly API= 'api/login';

  postCliente(request: Login): Observable<ResponseCliente> {
    return this.http.post<ResponseCliente>(this.API, request);
  }
}
