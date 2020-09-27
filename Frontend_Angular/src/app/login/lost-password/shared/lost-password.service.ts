import { LoginForPassword } from './lost-password.model';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class LostPasswordService {

  constructor(private http: HttpClient) { }

  private readonly API = 'api/esqueciSenha';

  putSenha(request: LoginForPassword): Observable<LoginForPassword> {
    const URL = `${this.API}/${request.login}`;

    return this.http.put<LoginForPassword>(URL, null);
  }
}
