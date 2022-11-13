export interface UserModel {
    idUsuario: number;
    cpf: string;
    senha: string;
    nome: string;
    saldo: number;
    perfil: PerfilUsuario;
}

export enum PerfilUsuario {
    EMPRESA = "Empresa",
    ALUNO = "Aluno",
    PROFESSOR = "Professor"
}