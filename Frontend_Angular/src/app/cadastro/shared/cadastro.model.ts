import { Endereco } from './endereco.model';
import { Cartao } from './cartao.model';


export interface Cadastro {
    idCliente: number;
    nmCliente: string;
    nrCpf: string;
    dsEmail: string;
    dtNasc: string;
    dsGenero: string;
    nrTelefone1: string;
    nrTelefone2: string;
    pwCliente: string;
    confirmarSenha: string;
    enderecos: Array<Endereco>;
    cartoesCreditoDTO: Array<Cartao>;
  }