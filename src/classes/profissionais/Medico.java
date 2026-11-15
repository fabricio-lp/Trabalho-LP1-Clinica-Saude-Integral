package classes.profissionais;

import classes.Especialidade;

public class Medico extends ProfissionalSaude {
    
    public Medico(String nome, String telefone, String registroProfissional, Especialidade especialidade) {
        super(nome, telefone, registroProfissional, especialidade);
    }

    @Override
    public String getTipoRegistro(){
    return "CRM"; 
    }
    
    @Override
    public String realizarAtendimento() {
        return getNome() + " realiza consulta clinica, avalia sintomas e prescreve medicamentos.";
    }
    
}
