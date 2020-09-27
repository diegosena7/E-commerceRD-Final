export interface Pedido {

  aberto: string;
  idPedido: number;
  dtCompra: string;
  vlTotalPedido: number;
  qtItensPedido: number;
  idCliente: number;
  dsStatusPedido: string;
  tipoVenda: string;
  items: Array<PedidoItens>;
  
}

export interface ResponsePedidos {
aberto: string; 
pedidos: Pedido[];
}

export interface PedidoItens {
  
  qtItensPedido: number;
  cdProduto: number;
  idPedido: number;
  nrItemPedido: number;
  dsProduto: string;
  vlPedidoItem: number;
}

export interface ResponsePedidosItens{
  pedidosItens: PedidoItens[];
}
