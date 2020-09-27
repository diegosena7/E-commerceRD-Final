export interface ResumoCompra {
    titulo: string,
    nrCpf: string,
    idNF: number,
    dsEmail: string,
    nrPedido: number,
    formaPagamento: string,
    nmNomeTitular: string,
    nrNumeroCartao: string,
    endereco: {
        idEndereco: number,
        dsEndereco: string,
        nrEndereco: number,
        nrCep: string,
        dsBairro: string,
        dsCidade: string,
        sgEstado: string,
        nmCompleto: string,
    },
    valorTotalNota: number,
    itensDocumento: [{
        nrItemDocumento: number,
        qtItem: number,
        nmProduto: string,
        vlItemUnitario: number,
        vlTotalItem: number,
        pcIcms: number,
        vlIcms: number,
    }],
    qtItens: number;
}

export interface ResponseResumo {
    resumoCompra: ResumoCompra[];
}