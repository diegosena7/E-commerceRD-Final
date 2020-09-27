export interface Produtos {
    cdProduto: number;
    idStatusProduto: number;
    nomeFantasia: string;
    nomeFabricante: string;
    dsProduto: string;
    valorUnidade: number;
    categoriaProduto: {
        idCategoriaProduto: number;
        dsCategoriaProduto: string;
    };
    subCategoriaProduto: {
        idSubCategoria: number;
        dsSubCategoria: string;
    };
    imagens: [
        {
            cdProduto: number;
            idImagem: number;
            dsUrl: string;
        }
    ];
    estoques: [
        {
            cdFilial: number;
            qtEstoque: number;
            qtEmpenho: number;
        }
    ]
}

export interface ResponseProdutos {
    produtos: Produtos[];
}