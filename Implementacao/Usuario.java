

public abstract class Usuario {
    protected String email;
    protected String senha;
    protected String nome;

    protected void gerarEmail(){
        String semEspaco = this.nome.replace(" ","");
        this.email = semEspaco+"@faculdade.com";
    }

    public boolean logar(String senha, String email){
        if(this.senha == senha && this.email == email)
            return true;
        else
            return false;
    }

    public String getEmail() {
        return email;
    }
}
