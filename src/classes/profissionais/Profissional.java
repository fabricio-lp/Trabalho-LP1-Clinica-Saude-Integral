package classes.profissionais;

import classes.Especialidade;
import classes.pessoas.Pessoa;

public class Profissional extends Pessoa{

    private String crm;
    private String nome;
    private String telefone;
    private Especialidade especialidade;

    public Profissional(String crm, String nome, String telefone, Especialidade especialidade) {
        super(nome, telefone);
        this.crm = crm;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String toString() {
        return "Profissional [CRM= " + crm + ", Nome= " + nome + ", Telefone= " + telefone
                + ", Especialidade= " + especialidade.getNome() + "]";
    }
}
