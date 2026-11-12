package classes.pessoas;

public class Paciente extends Pessoa {

    private String cpf;
    private String dataNascimento;

    public Paciente(String cpf, String nome, String telefone, String dataNascimento) {
        super(nome, telefone);
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String toString() {
        return "Paciente [CPF= " + cpf + ", Nome= " + getNome() + ", Telefone= " + getTelefone()
                + ", Nascimento= " + dataNascimento + "]";
    }
}
