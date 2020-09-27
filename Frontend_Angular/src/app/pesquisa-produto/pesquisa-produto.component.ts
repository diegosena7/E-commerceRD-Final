import { Component, OnInit } from '@angular/core';
import { ResponseProdutos } from '../categorias/shared/produto.model';
import { ResponseFabricantes } from '../categorias/shared/fabricante.model';
import { ProdutoService } from '../categorias/shared/produto.service';
import { ActivatedRoute, Router } from '@angular/router';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-pesquisa-produto',
  templateUrl: './pesquisa-produto.component.html',
  styleUrls: ['./pesquisa-produto.component.css']
})
export class PesquisaProdutoComponent implements OnInit {

  id: string;
  idPreco: string;
  nomeFabricante: string;
  query: any;
  idFabricante: string;
  filtroPreco1: string = 'R$0,00 - R$9,99';
  preco: string;

  responseProdutos: ResponseProdutos[];
  responseFabricantes: ResponseFabricantes[];

  constructor(
    private produtoService: ProdutoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    //Método criado para listar produtos no campo de pesquisa
    // this.listarProdutosPorID();

    this.listarFabricantesPorSubCategoria();
    this.listarProdutosPorNome();
  }

  queryField = new FormControl();

  listarProdutosPorID() {
    this.id = this.route.snapshot.paramMap.get('id');
    this.idPreco = this.route.snapshot.paramMap.get('idPreco');
    this.nomeFabricante = this.route.snapshot.paramMap.get('nomeFabricante');

    if(this.idPreco=='0'){
      this.produtoService.getProdutosPorIdFiltro(this.id,'0',this.nomeFabricante).subscribe(response => this.responseProdutos = response);
    }
    else if(this.nomeFabricante=='0'){
      this.produtoService.getProdutosPorIdFiltro(this.id,this.idPreco,'0').subscribe(response => this.responseProdutos = response);
    }else if(this.nomeFabricante=='0'&&this.idPreco=='0'){
      this.produtoService.getProdutosPorIdFiltro(this.id,'0','0').subscribe(response => this.responseProdutos = response);

    }else{
      this.produtoService.getProdutosPorIdFiltro(this.id,this.idPreco,this.nomeFabricante).subscribe(response => this.responseProdutos = response);

    }
  }

  //Lista os produtos pesquisados no campo de pesquisa
  listarProdutosPorNome() {

    this.query = this.route.snapshot.paramMap.get('query');
    this.idPreco = this.route.snapshot.paramMap.get('idPreco');
    this.nomeFabricante = this.route.snapshot.paramMap.get('nomeFabricante');
    if(this.idPreco=='0'){
      this.produtoService.getProdutoNomeFiltro(this.query,'0',this.nomeFabricante).subscribe(response => this.responseProdutos = response);
    }
    else if(this.nomeFabricante=='0'){
      this.produtoService.getProdutoNomeFiltro(this.query,this.idPreco,'0').subscribe(response => this.responseProdutos = response);
    }else if(this.nomeFabricante=='0'&&this.idPreco=='0'){
      this.produtoService.getProdutoNomeFiltro(this.query,'0','0').subscribe(response => this.responseProdutos = response);

    }else{
      this.produtoService.getProdutoNomeFiltro(this.query,this.idPreco,this.nomeFabricante).subscribe(response => this.responseProdutos = response);
    }
    
  }



  listarTodosProdutos() {
    this.produtoService.getProdutos().subscribe(response =>
      this.responseProdutos = response);
  }

  
  listarFabricantesPorSubCategoria() {
    this.query = this.route.snapshot.paramMap.get('query');
    this.produtoService.getFabricantesPorSubCategoriaPesquisa(this.query).subscribe(response => this.responseFabricantes = response);
  }



  clicked(cd: number) {
    this.router.navigateByUrl('categorias/produto', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/produto/${cd}`]);
    });
  }


  click(idPreco: string) {
    this.router.navigateByUrl('categorias', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/pesquisa/${this.query}/${idPreco}/${this.nomeFabricante}`]);
    });
  }
  click2(nomeFabricante: string) {
    this.router.navigateByUrl('categorias', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/pesquisa/${this.query}/${this.idPreco}/${nomeFabricante}`]);
    });
  }
  

}
