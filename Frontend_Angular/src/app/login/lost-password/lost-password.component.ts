import { LoginForPassword } from './shared/lost-password.model';
import { LostPasswordService } from './shared/lost-password.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-lost-password',
  templateUrl: './lost-password.component.html',
  styleUrls: ['./lost-password.component.css']
})
export class LostPasswordComponent implements OnInit {

  request: LoginForPassword = {
    login: ''
  }

  constructor(
    private lostPasswordService: LostPasswordService,
    private router: Router
  ) { }

  ngOnInit(): void {
  }

  alterarSenha() {
    this.lostPasswordService.putSenha(this.request).subscribe(
    );
    alert('Senha encaminhada no email!');
    this.router.navigate(['/login']);
  }

}
