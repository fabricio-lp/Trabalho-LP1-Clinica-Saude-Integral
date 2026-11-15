package classes;

import classes.interfaces.Atendivel;
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
                profissional = new Medico(nome, telefone, registro, esp);
                break;

            case 2:
                profissional = new Fisioterapeuta(nome, telefone, registro, esp);
                break;

            case 3:
                profissional = new Dentista(nome, telefone, registro, esp);
                break;

            default:
                System.out.println("Tipo invalido!");
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
        System.out.println("\n===================================================");
        System.out.println("                 ESPECIALIDADES");
        System.out.println("===================================================");
        for (int i = 0; i < totalEspecialidades; i++) {
            System.out.println("  " + especialidades[i].toString() + "\n");
        }
    }

    public void listarPacientes() {
        System.out.println("\n===================================================");
        System.out.println("                    PACIENTES");
        System.out.println("===================================================");
        for (int i = 0; i < totalPacientes; i++) {
            System.out.println("  " + pacientes[i].toString() + "\n");
        }
    }

    public void listarProfissionais() {
        String[] registros = {"CRM", "CRO", "CREFITO"};
        String[] categorias = {"MEDICOS", "DENTISTAS", "FISIOTERAPEUTAS"};
        System.out.println("\n===================================================");
        System.out.println("                  PROFISSIONAIS");
        System.out.println("===================================================");
        for (int j = 0; j < categorias.length; j++) {
            System.out.println("\n  --- " + categorias[j] + " ---\n");
            for (int i = 0; i < totalProfissionais; i++) {
                if (registros[j].equals(profissionais[i].getTipoRegistro())) {
                    System.out.println("  " + profissionais[i].toString() + "\n");
                }
            }
        }
    }


    public void listarConsultasPorPaciente(String cpf) {
        System.out.println("\n===================================================");
        System.out.println("        HISTÓRICO DE CONSULTAS DO PACIENTE");
        System.out.println("===================================================");
        int encontradas = listarConsultasPorPacienteRecursivo(cpf, 0);

        System.out.println("---------------------------------------------------");
        if (encontradas == 0) {
            System.out.println("  Nenhuma consulta encontrada para este paciente.");
        } else {
            System.out.println("  Total:      " + encontradas);
            System.out.println("  Realizadas: " + contarConsultasPacientePorStatusRecursivo(cpf, "Realizada", 0));
            System.out.println("  Canceladas: " + contarConsultasPacientePorStatusRecursivo(cpf, "Cancelada", 0));
            System.out.println("  Agendadas:  " + contarConsultasPacientePorStatusRecursivo(cpf, "Agendada", 0));
        }
        System.out.println("===================================================");
    }


    public void listarConsultasPorProfissional(String registro) {
        System.out.println("\n===================================================");
        System.out.println("            CONSULTAS DO PROFISSIONAL");
        System.out.println("===================================================");
        for (int i = 0; i < totalConsultas; i++) {
            if (consultas[i].getProfissional().getRegistroProfissional().equals(registro)) {
                System.out.println("  " + consultas[i].toString() + "\n");
            }
        }
    }

    public void listarConsultasAgendadas() {
        System.out.println("\n===================================================");
        System.out.println("                CONSULTAS AGENDADAS");
        System.out.println("===================================================");
        boolean achou = false;
        for (int i = 0; i < totalConsultas; i++) {
            if (!consultas[i].isRealizada() && !consultas[i].isCancelada()) {
                System.out.println("  " + consultas[i].toString() + "\n");
                achou = true;
            }
        }
        if (!achou) {
            System.out.println("  Nenhuma consulta agendada.\n");
        }
    }

    public void listarTodasConsultas() {
        System.out.println("\n===================================================");
        System.out.println("                TODAS AS CONSULTAS");
        System.out.println("===================================================");
        for (int i = 0; i < totalConsultas; i++) {
            System.out.println("  " + consultas[i].toString() + "\n");
        }
    }

    
    public void listarAtendimentos() {
        System.out.println("\n--- Demonstracao de polimorfismo ---");
        if (totalProfissionais == 0) {
            System.out.println("  Nenhum profissional cadastrado.");
            return;
        }
        for (int i = 0; i < totalProfissionais; i++) {
            Atendivel a = profissionais[i];                  
            System.out.println("  " + a.getDescricao());
            System.out.println("    -> " + a.realizarAtendimento());
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

    public int listarConsultasPorPacienteRecursivo(String cpf, int indice) {
        if (indice >= totalConsultas) {
            return 0;
        }
        int contagem = 0;
        if (consultas[indice].getPaciente().getCpf().equals(cpf)) {
            System.out.println("  " + consultas[indice].toString() + "\n");
            contagem = 1;
        }
        return contagem + listarConsultasPorPacienteRecursivo(cpf, indice + 1);
    }

    public int contarConsultasPacientePorStatusRecursivo(String cpf, String status, int indice) {
        if (indice >= totalConsultas) {
            return 0;
        }
        int contagem = 0;
        boolean mesmoPaciente = consultas[indice].getPaciente().getCpf().equals(cpf);
        boolean mesmoStatus = consultas[indice].getStatus().equals(status);
        if (mesmoPaciente && mesmoStatus) {
            contagem = 1;
        }
        return contagem + contarConsultasPacientePorStatusRecursivo(cpf, status, indice + 1);
    }

    public void carregarDadosTeste() {
        System.out.println("\n>>> Carregando dados de teste...");

        cadastrarEspecialidade("Cardiologia", "Doencas do coracao");
        cadastrarEspecialidade("Clinica Geral", "Atendimento geral e preventivo");
        cadastrarEspecialidade("Ortodontia", "Correcao de dentes e arcada");
        cadastrarEspecialidade("Fisioterapia Esportiva", "Reabilitacao de lesoes");
        cadastrarEspecialidade("Dermatologia", "Doencas da pele, cabelos e unhas");
        cadastrarEspecialidade("Pediatria", "Saude de criancas e adolescentes");
        cadastrarEspecialidade("Endodontia", "Tratamento de canal e polpa dentaria");

        cadastrarProfissional(1, "12345", "Cleber Mendonça", "(77) 99911-1111", 1);
        cadastrarProfissional(1, "67890", "Katarine Pereira Silvestre", "(77) 99922-2222", 2);
        cadastrarProfissional(2, "111", "Neymar Junior Silva", "(77) 99933-3333", 4);
        cadastrarProfissional(3, "222", "João Vitor Medeiros", "(77) 99944-4444", 3);
        cadastrarProfissional(1, "54321", "Lázaro Ramos", "(77) 99955-5555", 5);
        cadastrarProfissional(1, "98765", "Fátima Bernardes", "(77) 99966-6666", 6);
        cadastrarProfissional(3, "333", "Marcos Manuel Santos", "(77) 99977-7777", 7);

        cadastrarPaciente("111.111.111-11", "Ana Pereira", "(77) 98811-1111", "15/03/1990");
        cadastrarPaciente("222.222.222-22", "Bruno Costa", "(77) 98822-2222", "22/07/1985");
        cadastrarPaciente("333.333.333-33", "Carla Mendes", "(77) 98833-3333", "05/12/2001");
        cadastrarPaciente("444.444.444-44", "Diego Santos", "(77) 98844-4444", "30/09/1978");
        cadastrarPaciente("555.555.555-55", "Silvio Souza Palheiros", "(77) 98855-5555", "12/04/1969");
        cadastrarPaciente("666.666.666-66", "Marcele Aguiar Bittencourt", "(77) 98866-6666", "28/08/1993");
        cadastrarPaciente("777.777.777-77", "Joana Kubischeck", "(77) 98877-7777", "03/01/1982");

        agendarConsulta("111.111.111-11", 1, "12345", "10/11/2026 09:00"); // 1 - Ana + Cleber
        agendarConsulta("222.222.222-22", 1, "67890", "10/11/2026 10:00"); // 2 - Bruno + Katarine
        agendarConsulta("111.111.111-11", 3, "222", "12/11/2026 14:00");   // 3 - Ana + Joao Vitor
        agendarConsulta("111.111.111-11", 2, "111", "20/11/2026 08:30");   // 4 - Ana + Neymar
        agendarConsulta("333.333.333-33", 1, "12345", "21/11/2026 09:00"); // 5 - Carla + Cleber
        agendarConsulta("444.444.444-44", 3, "222", "22/11/2026 15:00");   // 6 - Diego + Joao Vitor
        agendarConsulta("555.555.555-55", 1, "54321", "23/11/2026 10:00"); // 7 - Silvio + Lazaro
        agendarConsulta("666.666.666-66", 1, "98765", "24/11/2026 11:00"); // 8 - Marcele + Fatima
        agendarConsulta("777.777.777-77", 3, "333", "25/11/2026 16:00");   // 9 - Joana + Marcos

        concluirConsulta(1, "Paciente com pressao normal. Retorno em 6 meses.");
        concluirConsulta(2, "Check-up completo, exames solicitados.");
        cancelarConsulta(3);

        System.out.println(">>> Dados de teste carregados!\n");
    }

    public String getNome() {
        return nome;
    }

}