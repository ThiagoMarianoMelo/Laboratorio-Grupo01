export interface TransacaoModel {
    idTransacao: number;
    idBeneficiario: number;
    idUsuario: number;
    dataTransacao: string;
    anotacao: string;
    preco: number;
}