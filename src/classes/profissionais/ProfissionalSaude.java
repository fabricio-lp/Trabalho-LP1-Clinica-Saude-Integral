package classes.profissionais;

import classes.Especialidade;
import classes.pessoas.Pessoa;

public abstract class ProfissionalSaude extends Pessoa{

    private String registroProfissional;
    private Especialidade especialidade;

    public ProfissionalSaude(String registroProfissional, String nome, String telefone, Especialidade especialidade) {
        super(nome, telefone);
        this.registroProfissional = registroProfissional;
        this.especialidade = especialidade;
    }

    public String getRegistroProfissional() {
        return registroProfissional;
    }

    public abstract String getTipoRegistro();

    public void setRegistroProfissional(String registroProfissional) {
        this.registroProfissional = registroProfissional;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String toString() {
        return "Profissional [CRM= " + registroProfissional + ", Nome= " + getNome() + ", Telefone= " + getTelefone()
                + ", Especialidade= " + especialidade.getNome() + "]";
    }
}
