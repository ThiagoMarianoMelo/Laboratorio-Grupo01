public abstract class Usuario {
    protected String email;
    protected String senha;
    protected String nome;

    protected void gerarEmail(){
        String semEspaco = this.nome.replace(" ","");
        this.email = semEspaco+"@faculdade.com";
    }

    public boolean logar(String senha, String email){
        return this.senha == senha && this.email == email;
    }

    public String getEmail() {
        return email;
    }
}
