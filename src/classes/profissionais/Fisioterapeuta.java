package classes.profissionais;

import classes.Especialidade;

public class Fisioterapeuta extends ProfissionalSaude {
    
    public Fisioterapeuta(String registroProfissional, String nome, String telefone, Especialidade especialidade) {
        super(nome, telefone, registroProfissional, especialidade);
    }

    @Override
    public String getTipoRegistro(){
    return "CREFITO"; 
    }

}
