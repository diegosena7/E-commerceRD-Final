import { Cliente } from './../../cadastro/shared/cliente.model';
export interface ResponseCliente{
  status: number;
  mensagem: string;
  retorno: Cliente;
  dtTimestampErro: string;
}