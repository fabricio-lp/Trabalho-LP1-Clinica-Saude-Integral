package classes;

public class Paciente {

    private String cpf;
    private String nome;
    private String telefone;
    private String dataNascimento;

    public Paciente(String cpf, String nome, String telefone, String dataNascimento) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String toString() {
        return "Paciente [CPF= " + cpf + ", Nome= " + nome + ", Telefone= " + telefone
                + ", Nascimento= " + dataNascimento + "]";
    }
}
