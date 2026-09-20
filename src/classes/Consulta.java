package classes;

public class Consulta {

    private int codigo;
    private Paciente paciente;
    private Profissional profissional;
    private String dataHora;
    private boolean realizada = false;
    private boolean cancelada = false;
    private String observacao = "";

    public Consulta(int codigo, Paciente paciente, Profissional profissional, String dataHora) {
        this.codigo = codigo;
        this.paciente = paciente;
        this.profissional = profissional;
        this.dataHora = dataHora;
    }

    public boolean concluir(String observacao) {
        if (this.realizada) {
            System.out.println("ERRO: Esta consulta ja foi realizada.");
            return false;
        }
        if (this.cancelada) {
            System.out.println("ERRO: Esta consulta foi cancelada e nao pode ser concluida.");
            return false;
        }
        this.realizada = true;
        this.observacao = observacao;
        System.out.println("Consulta #" + codigo + " concluida com sucesso.");
        return true;
    }

    public boolean cancelar() {
        if (this.realizada) {
            System.out.println("ERRO: Nao e possivel cancelar uma consulta que ja foi realizada.");
            return false;
        }
        if (this.cancelada) {
            System.out.println("ERRO: Esta consulta ja esta cancelada.");
            return false;
        }
        this.cancelada = true;
        System.out.println("Consulta #" + codigo + " cancelada com sucesso.");
        return true;
    }

    public String getStatus() {
        if (realizada) return "Realizada";
        if (cancelada) return "Cancelada";
        return "Agendada";
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public boolean isRealizada() {
        return realizada;
    }

    public boolean isCancelada() {
        return cancelada;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String toString() {
        String texto = "Consulta [Codigo= " + codigo
                + ", Paciente= " + paciente.getNome()
                + ", Profissional= " + profissional.getNome()
                + " (" + profissional.getEspecialidade().getNome() + ")"
                + ", Data/Hora= " + dataHora
                + ", Status= " + getStatus();

        if (realizada) {
            texto = texto + ", Obs=" + observacao;
        }

        texto = texto + "]";
        return texto;
    }
}
