package classes;

public class Profissional {

    private String crm;
    private String nome;
    private String telefone;
    private Especialidade especialidade;

    public Profissional(String crm, String nome, String telefone, Especialidade especialidade) {
        this.crm = crm;
        this.nome = nome;
        this.telefone = telefone;
        this.especialidade = especialidade;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
