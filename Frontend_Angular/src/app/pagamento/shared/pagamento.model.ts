export interface DocumentoFiscal {
    idCliente: number;
    idEndereco: number,
    idFormaPagamento: number,
    itensDTOPost: ItemProduto[],
    nmNomeTitular: string,
    nrNumeroCartao: string,
    
}

export interface ItemProduto {
    cdProduto: number;
    qtProduto: number;
}