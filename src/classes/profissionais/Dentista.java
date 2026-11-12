package classes.profissionais;

import classes.Especialidade;

public class Dentista extends ProfissionalSaude {
    
    public Dentista(String nome, String telefone, String registroProfissional, Especialidade especialidade) {
        super(nome, telefone, registroProfissional, especialidade);
    }

    @Override 
    public String getTipoRegistro(){
    return "CRO"; 
    }

}
