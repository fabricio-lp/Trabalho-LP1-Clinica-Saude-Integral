package classes;

import classes.pessoas.Paciente;
import classes.profissionais.Dentista;
import classes.profissionais.Fisioterapeuta;
import classes.profissionais.Medico;
import classes.profissionais.ProfissionalSaude;

public class Clinica {

    private String nome;

    private Especialidade[] especialidades = new Especialidade[100];
    private ProfissionalSaude[] profissionais = new ProfissionalSaude[100];
    private Paciente[] pacientes = new Paciente[100];
    private Consulta[] consultas = new Consulta[100];

    private int totalEspecialidades = 0;
    private int totalProfissionais = 0;
    private int totalPacientes = 0;
    private int totalConsultas = 0;
    private int contadorCodigoConsulta = 0;
    private int contadorCodigoEspecialidade = 0;

    public Clinica(String nome) {
        this.nome = nome;
    }

    public void cadastrarEspecialidade(String nome, String descricao) {
        contadorCodigoEspecialidade++;
        Especialidade esp = new Especialidade(contadorCodigoEspecialidade, nome, descricao);
        especialidades[totalEspecialidades] = esp;
        totalEspecialidades++;
        System.out.println("Especialidade cadastrada: " + esp.toString());
    }

    public void cadastrarProfissional(int tipo, String registro, String nome, String telefone, int codigoEspecialidade) {
        Especialidade esp = null;
        ProfissionalSaude profissional;
        for (int i = 0; i < totalEspecialidades; i++) {
            if (especialidades[i].getCodigo() == codigoEspecialidade) {
                esp = especialidades[i];
            }
        }

        if (esp == null) {
            System.out.println("ERRO: Especialidade nao encontrada. O profissional deve ter especialidade.");
            return;
        }
        switch (tipo) {
            case 1:
                profissional = new Medico(registro, nome, telefone, esp);
                break;

            case 2:
                profissional = new Fisioterapeuta(registro, nome, telefone, esp);
                break;

            case 3:
                profissional = new Dentista(registro, nome, telefone, esp);
                break;
        
            default:
                System.out.println("Tipo inválido!");
                return;
        }
        profissionais[totalProfissionais] = profissional;
        totalProfissionais++;
        System.out.println("Profissional cadastrado: " + profissional.toString());
    }

    public void cadastrarPaciente(String cpf, String nome, String telefone, String dataNascimento) {
        Paciente pac = new Paciente(cpf, nome, telefone, dataNascimento);
        pacientes[totalPacientes] = pac;
        totalPacientes++;
        System.out.println("Paciente cadastrado: " + pac.toString());
    }

    public void agendarConsulta(String cpfPaciente, int tipo, String registroProfissional, String dataHora) {
        Paciente paciente = null;
        for (int i = 0; i < totalPacientes; i++) {
            String cpfAtual = pacientes[i].getCpf();
            if (cpfAtual.equals(cpfPaciente)) {
                paciente = pacientes[i];
            }
        }

        if (paciente == null) {
            System.out.println("ERRO: Paciente nao encontrado. Nao e permitido agendar para paciente inexistente.");
            return;
        }

        ProfissionalSaude profissional = null;
        for (int i = 0; i < totalProfissionais; i++) {
            String registroAtual = profissionais[i].getRegistroProfissional();
            if (registroAtual.equals(registroProfissional)) {
                profissional = profissionais[i];
            }
        }

        if (profissional == null) {
            System.out.println("ERRO: Profissional nao encontrado.");
            return;
        }

        for (int i = 0; i < totalConsultas; i++) {
            ProfissionalSaude profDaConsulta = consultas[i].getProfissional();
            String registroDaConsulta = profDaConsulta.getRegistroProfissional();
            String horaDaConsulta = consultas[i].getDataHora();
            boolean estaCancelada = consultas[i].isCancelada();

            boolean mesmoRegistro = registroDaConsulta.equals(registroProfissional);
            boolean mesmoHorario = horaDaConsulta.equals(dataHora);

            if (mesmoRegistro && mesmoHorario && !estaCancelada) {
                System.out.println("ERRO: Profissional ja tem consulta nesse horario.");
                return;
            }
        }

        contadorCodigoConsulta++;
        Consulta consulta = new Consulta(contadorCodigoConsulta, paciente, profissional, dataHora);
        consultas[totalConsultas] = consulta;
        totalConsultas++;
        System.out.println("Consulta agendada: " + consulta.toString());
    }

    public void concluirConsulta(int codigoConsulta, String observacao) {
        Consulta consulta = buscarConsultaRecursivo(codigoConsulta, 0);
        if (consulta == null) {
            System.out.println("ERRO: Consulta nao encontrada.");
            return;
        }
        consulta.concluir(observacao);
    }

    public void cancelarConsulta(int codigoConsulta) {
        Consulta consulta = buscarConsultaRecursivo(codigoConsulta, 0);
        if (consulta == null) {
            System.out.println("ERRO: Consulta nao encontrada.");
            return;
        }
        consulta.cancelar();
    }

    public void listarEspecialidades() {
        System.out.println("\n--- Especialidades ---");
        for (int i = 0; i < totalEspecialidades; i++) {
            System.out.println("  " + especialidades[i].toString());
        }
    }

    public void listarProfissionais() {
        String[] registros = {"CRM", "CRO", "CREFITO"};
        String[] categorias = {"Medico", "Dentista", "Fisioterapeuta"};
        for (int j = 0; j < categorias.length; j++) {
        System.out.println("\n--- " + categorias[j] + " ---");
        for (int i = 0; i < totalProfissionais; i++) {
            if (registros[i].equals(profissionais[i].getTipoRegistro()))
            System.out.println("  " + profissionais[i].toString());
            }
        }
    }

    public void listarPacientes() {
        System.out.println("\n--- Pacientes ---");
        for (int i = 0; i < totalPacientes; i++) {
            System.out.println("  " + pacientes[i].toString());
        }
    }

    public void listarConsultasPorPaciente(String cpf) {
        System.out.println("\n--- Consultas do Paciente ---");
        for (int i = 0; i < totalConsultas; i++) {
            Paciente pacDaConsulta = consultas[i].getPaciente();
            String cpfDaConsulta = pacDaConsulta.getCpf();
            if (cpfDaConsulta.equals(cpf)) {
                System.out.println("  " + consultas[i].toString());
            }
        }
    }

    public void listarConsultasPorProfissional(String crm) {
        System.out.println("\n--- Consultas do Profissional ---");
        for (int i = 0; i < totalConsultas; i++) {
            ProfissionalSaude profDaConsulta = consultas[i].getProfissional();
            String crmDaConsulta = profDaConsulta.getCrm();
            if (crmDaConsulta.equals(crm)) {
                System.out.println("  " + consultas[i].toString());
            }
        }
    }

    public void listarConsultasAgendadas() {
        if (totalConsultas !=0 ){
        System.out.println("\n--- Consultas Agendadas ---");
        for (int i = 0; i < totalConsultas; i++) {
            if (!consultas[i].isRealizada() && !consultas[i].isCancelada()) {
                System.out.println("  " + consultas[i].toString());

            }
        }
        } else {
            System.out.println("\nNenhuma consulta agendada. ");
        }
    }

    public void listarTodasConsultas() {
        System.out.println("\n--- Todas as Consultas ---");
        for (int i = 0; i < totalConsultas; i++) {
            System.out.println("  " + consultas[i].toString());
        }
    }

    public void exibirRelatorio() {
        System.out.println("\n===================================================");
        System.out.println("         RELATORIO GERAL - " + nome);
        System.out.println("===================================================");
        System.out.println("  Especialidades: " + totalEspecialidades);
        System.out.println("  Profissionais:  " + totalProfissionais);
        System.out.println("  Pacientes:      " + totalPacientes);
        System.out.println("  Total consultas:" + totalConsultas);
        System.out.println("  Realizadas:     " + contarConsultasRealizadasRecursivo(0));
        System.out.println("  Canceladas:     " + contarConsultasCanceladasRecursivo(0));
        System.out.println("===================================================");
    }



    public Consulta buscarConsultaRecursivo(int codigo, int indice) {
        if (indice >= totalConsultas) {
            return null;
        }
        if (consultas[indice].getCodigo() == codigo) {
            return consultas[indice];
        }
        return buscarConsultaRecursivo(codigo, indice + 1);
    }

    public int contarConsultasRealizadasRecursivo(int indice) {
        if (indice >= totalConsultas) {
            return 0;
        }
        int contagem = 0;
        if (consultas[indice].isRealizada()) {
            contagem = 1;
        }
        return contagem + contarConsultasRealizadasRecursivo(indice + 1);
    }

    public int contarConsultasCanceladasRecursivo(int indice) {
        if (indice >= totalConsultas) {
            return 0;
        }
        int contagem = 0;
        if (consultas[indice].isCancelada()) {
            contagem = 1;
        }
        return contagem + contarConsultasCanceladasRecursivo(indice + 1);
    }

    public String getNome() {
        return nome;
    }
}
