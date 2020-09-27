import { ResponseFabricantes } from './shared/fabricante.model';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { ProdutoService } from './shared/produto.service';
import { ResponseProdutos } from './shared/produto.model';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-categorias',
  templateUrl: './categorias.component.html',
  styleUrls: ['./categorias.component.css']
})
export class CategoriasComponent implements OnInit {

  @ViewChild('preco')alteraPreco:ElementRef;
  filtro: any ={
    preco: 'preco teste',
    nomeFabricante: ''
  };
  id: string;
  idPreco: string;
  nomeFabricante: string ='';
  query: any;
  idFabricante: string;
  filtroPreco1: string = 'Filtro de Preço';
  erro: boolean = false;
  aparecerErro: boolean = false;

  responseProdutos: ResponseProdutos[];
  responseFabricantes: ResponseFabricantes[];

  constructor(
    private produtoService: ProdutoService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    //Método criado para listar produtos no campo de pesquisa
    this.filtroPreco1;
    this.listarProdutosPorID();
    this.listarFabricantesPorSubCategoria();
    
    // this.listarProdutosPorNome();
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
    this.idFabricante = this.route.snapshot.paramMap.get('id');
    this.produtoService.getFabricantesPorSubCategoria(this.idFabricante).subscribe(response => this.responseFabricantes = response);
  }

  clicked(cd: number) {
    this.router.navigateByUrl('categorias/produto', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/produto/${cd}`]);
    });
  }
  
  click(idPreco: string) {
    this.router.navigateByUrl('categorias', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/${this.id}/${idPreco}/${this.nomeFabricante}`]);
    });
    // console.log(idPreco)
    // switch(idPreco){
    //   case '1':
    //   this.alteraPreco.nativeElement.value='preco1'
    //   console.log(this.alteraPreco.nativeElement.value)
    //   break;
    // }
    // console.log('click')
   }
   

  click2(nomeFabricante: string) {
    this.router.navigateByUrl('categorias', { skipLocationChange: true }).then(() => {
      this.router.navigate([`categorias/${this.id}/${this.idPreco}/${nomeFabricante}`]);
    });
  }

  

}
