package classes.profissionais;

import classes.Especialidade;
import classes.pessoas.Pessoa;
import classes.interfaces.Atendivel;

public abstract class ProfissionalSaude extends Pessoa implements Atendivel{

    private String registroProfissional;
    private Especialidade especialidade;

    public ProfissionalSaude(String nome, String telefone, String registroProfissional, Especialidade especialidade) {
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

    public String getRegistroFormatado() {
        return getTipoRegistro() + "= " + registroProfissional;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String toString() {
        return getNome()
                + "\n    " + getTipoRegistro() + ":" + registroProfissional
                + "\n    Especialidade: " + especialidade.getNome()
                + "\n    Telefone: " + getTelefone();
    }
    @Override
    public String getDescricao() {
        return getTipoRegistro() + " " + registroProfissional + " - " + getNome()
                + " (" + especialidade.getNome() + ")";
    }
}
