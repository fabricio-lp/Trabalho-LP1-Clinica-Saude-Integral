package classes.profissionais;

import classes.Especialidade;

public class Medico extends ProfissionalSaude {
    
    public Medico(String registroProfissional, String nome, String telefone, Especialidade especialidade) {
        super(nome, telefone, registroProfissional, especialidade);
    }

    @Override
    public String getTipoRegistro(){
    return "CRM"; 
    }
    
}
