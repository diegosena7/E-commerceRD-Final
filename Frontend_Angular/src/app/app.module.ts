import { AuthGuardService } from './auth/auth-grarg.service';
import { PesquisaProdutoComponent } from './pesquisa-produto/pesquisa-produto.component';
import { BrowserModule } from '@angular/platform-browser';
import { NgModule, LOCALE_ID } from '@angular/core';

import { NgxMaskModule, IConfig } from 'ngx-mask';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HttpClient } from '@angular/common/http';
import { StorageServiceModule } from 'ngx-webstorage-service';
import { FormRulesModule } from 'ng-form-rules';
import { registerLocaleData, DatePipe } from '@angular/common';
import localePt from '@angular/common/locales/pt';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderMainComponent } from './header-main/header-main.component';
import { CarouselComponent } from './carousel/carousel.component';
import { CarouselItemsComponent } from './carousel-items/carousel-items.component';
import { FooterMainComponent } from './footer-main/footer-main.component';
import { HeaderSecondaryComponent } from './header-secondary/header-secondary.component';
import { FooterSecondaryComponent } from './footer-secondary/footer-secondary.component';
import { MeusPedidosComponent } from './meus-pedidos/meus-pedidos.component';
import { CadastroComponent } from './cadastro/cadastro.component';
import { LoginComponent } from './login/login.component';
import { LostPasswordComponent } from './login/lost-password/lost-password.component';
import { MinhaContaComponent } from './minha-conta/minha-conta.component';
import { CategoriasComponent } from './categorias/categorias.component';
import { ProdutoDetalhesComponent } from './produto-detalhes/produto-detalhes.component';
import { CestaComponent } from './cesta/cesta.component';
import { EntregaComponent } from './entrega/entrega.component';
import { PagamentoComponent } from './pagamento/pagamento.component';
import { MenuFinalCompraComponent } from './menu-final-compra/menu-final-compra.component';
import { ExclusivoComponent } from './exclusivo/exclusivo.component';
import { AlterarSenhaComponent } from './minha-conta/alterar-senha/alterar-senha.component';
import { MeusCartoesComponent } from './minha-conta/meus-cartoes/meus-cartoes.component';
import { MinhasInformacoesComponent } from './minha-conta/minhas-informacoes/minhas-informacoes.component';
import { MeusEnderecosComponent } from './minha-conta/meus-enderecos/meus-enderecos.component';
import { MenuMinhaContaComponent } from './minha-conta/menu-minha-conta/menu-minha-conta.component';
import { ResumoCompraComponent } from './pagamento/resumo-compra/resumo-compra.component';
import { CadastradoLojaComponent } from './login/cadastrado-loja/cadastrado-loja.component';
registerLocaleData(localePt, 'pt-BR');


@NgModule({
  declarations: [
    AppComponent,
    HeaderMainComponent,
    CarouselComponent,
    CarouselItemsComponent,
    FooterMainComponent,
    HeaderSecondaryComponent,
    FooterSecondaryComponent,
    MeusPedidosComponent,
    CadastroComponent,
    LoginComponent,
    LostPasswordComponent,
    MinhaContaComponent,
    CategoriasComponent,
    ProdutoDetalhesComponent,
    CestaComponent,
    EntregaComponent,
    PagamentoComponent,
    MenuFinalCompraComponent,
    ExclusivoComponent,
    AlterarSenhaComponent,
    MeusCartoesComponent,
    MinhasInformacoesComponent,
    MeusEnderecosComponent,
    MenuMinhaContaComponent,
    PesquisaProdutoComponent,
    ResumoCompraComponent,
    CadastradoLojaComponent
  ],
  imports: [
    NgxMaskModule.forRoot(),
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    FormRulesModule,
    StorageServiceModule,
  ],
  providers: [HttpClient,
    AuthGuardService, DatePipe, {
    provide: LOCALE_ID,
    useValue: "pt-BR"
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
