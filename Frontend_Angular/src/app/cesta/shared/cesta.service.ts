import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CestaService {

  itens = [];

  totalCompra;

  tamanho;

  constructor(private http: HttpClient) { 
    this.itens = (JSON.parse(localStorage.getItem('produtos')));

    if (this.itens == null) {
      this.itens = [];
    }
    this.itens.forEach(item =>{
      if(!item.qtProduto){
        item.qtProduto = 1;
      }
    });
  }

  addCesta(produto) {
    let temNaLista = false;
    let posicaoLista = null;

    this.itens.forEach((item, index) => {
      if (item.cdProduto == produto.cdProduto) {
        temNaLista = true;
        posicaoLista = index;
      }
    })

    if (!temNaLista) {
      produto.qtProduto = 1;
      this.itens.push(produto);
      localStorage.setItem('produtos', JSON.stringify(this.itens));
    } else {
      this.alteraCesta(posicaoLista, 1);
    }
  }

  histAddCesta(responseProdutos) {
    for (let i = 0; i <= responseProdutos.length; i++) {
      const produto = responseProdutos[i];
      let temNaLista = false;
      let posicaoLista = null;

      this.itens.forEach((item, index) => {
        if (item.cdProduto == produto.cdProduto) {
          temNaLista = true;
          posicaoLista = index;
        }
      })

      if (!temNaLista) {
        produto.qtProduto = 1;
        this.itens.push(produto);
        localStorage.setItem('produtos', JSON.stringify(this.itens));
      } else {
        this.alteraCesta(posicaoLista, 1);
      }
  }
}

  public getCesta() {
    return this.itens;
  }

  public limpaCesta() {
    this.itens = [];
    return this.itens;
  }

  removeCesta(index) {
    this.itens.splice(index, 1);
    localStorage.setItem('produtos', JSON.stringify(this.itens));
    return this.itens;

  }

  calculaCesta() {
    this.totalCompra = this.itens.reduce((totalCompra, soma) => totalCompra + (soma.valorUnidade * soma.qtProduto), 0);
    return this.totalCompra;
   }

   contaCesta(){
     this.tamanho = this.itens.length;
     return this.tamanho;
   }

   alteraCesta(index, alt){
    if (alt > 0) { 
      this.itens[index].qtProduto += 1;
    } else {
      const god = this.itens[index].qtProduto - 1;
      if (god > 0) {
        this.itens[index].qtProduto -= 1;        
      }
    }  
    this.calculaCesta();
    localStorage.setItem('produtos', JSON.stringify(this.itens));
   }
}
