package classes.profissionais;

import classes.Especialidade;

public class Fisioterapeuta extends ProfissionalSaude {
    
    public Fisioterapeuta(String nome, String telefone, String registroProfissional, Especialidade especialidade) {
        super(nome, telefone, registroProfissional, especialidade);
    }

    @Override
    public String getTipoRegistro(){
    return "CREFITO"; 
    }

    @Override
    public String realizarAtendimento() {
        return getNome() + " realiza consulta clinica, avalia sintomas e prescreve medicamentos.";
    }

}
