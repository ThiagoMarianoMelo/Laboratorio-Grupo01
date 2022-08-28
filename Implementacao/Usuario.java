public abstract class Usuario {
    protected String email;
    protected String senha;

    public boolean logar(String senha, String email){
        if(this.senha == senha && this.email == email)
            return true;
        else
            return false;
    }


}
