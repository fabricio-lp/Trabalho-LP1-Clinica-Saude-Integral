import java.util.Scanner;
import classes.Clinica;

/*GRUPO D - Sistema de Clínica Médica
Descrição: Sistema destinado ao gerenciamento de uma clínica médica, permitindo controlar pacientes, profissionais, consultas e especialidades.
O sistema deverá permitir:
Classes:
Relacionamentos:
Regras de negócio (Implementar):
* Não permitir agendamento de consulta para paciente inexistente;
* Um profissional não poderá possuir duas consultas no mesmo horário;
* Uma consulta deverá estar associada a um paciente e a um profissional;
* O profissional deverá possuir uma especialidade cadastrada;
* Não permitir o cancelamento de uma consulta que já tenha sido realizada;
* Ao concluir uma consulta, ela deverá ser marcada como realizada.

javac -d bin src/classes/*.java src/Main.java
java -cp bin Main
*/ 

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Clinica clinica = new Clinica("Clinica Saude Integral");

        int opcao = -1;

        System.out.println("===================================================");
        System.out.println("   BEM-VINDO AO SISTEMA DA CLINICA SAUDE INTEGRAL  ");
        System.out.println("===================================================");

        do {
            System.out.println("\n===================================================");
            System.out.println("              MENU PRINCIPAL");
            System.out.println("===================================================");
            System.out.println("  [1]  Cadastrar Especialidade");
            System.out.println("  [2]  Cadastrar Profissional");
            System.out.println("  [3]  Cadastrar Paciente");
            System.out.println("  [4]  Agendar Consulta");
            System.out.println("  [5]  Concluir Consulta");
            System.out.println("  [6]  Cancelar Consulta");
            System.out.println("  [7]  Listar Especialidades");
            System.out.println("  [8]  Listar Profissionais");
            System.out.println("  [9]  Listar Pacientes");
            System.out.println("  [10] Consultas por Paciente");
            System.out.println("  [11] Consultas por Profissional");
            System.out.println("  [12] Listar Consultas Agendadas");
            System.out.println("  [13] Listar Todas as Consultas");
            System.out.println("  [14] Relatorio Geral");
            System.out.println("  [0]  Sair");
            System.out.println("===================================================");

            System.out.print("Escolha uma opcao: ");
            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\n--- Cadastro de Especialidade ---");
                    System.out.print("Nome da especialidade: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descricao: ");
                    String descricao = scanner.nextLine();
                    clinica.cadastrarEspecialidade(nome, descricao);
                    break;

                case 2:
                    System.out.println("\n--- Cadastro de Profissional ---");
                    System.out.println("[1]  Médico");
                    System.out.println("[2]  Fisioterapeuta");
                    System.out.println("[3]  Dentista");
                    System.out.print("Escolha: ");
                    int tipo = scanner.nextInt();
                    scanner.nextLine();
                    if (tipo == 1)
                        System.out.print("CRM: ");
                    else if (tipo == 2) 
                        System.out.print("CREFITO: ");
                    else if (tipo == 3) 
                        System.out.print("CRO: ");
                    String registro = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomeProf = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefone = scanner.nextLine();
                    System.out.print("\n--- Lista de Especialidades ---");
                    clinica.listarEspecialidades();
                    System.out.print("Codigo da especialidade: ");
                    int codEsp = scanner.nextInt();
                    scanner.nextLine();
                    clinica.cadastrarProfissional(tipo, registro, nomeProf, telefone, codEsp);
                    break;

                case 3:
                    System.out.println("\n--- Cadastro de Paciente ---");
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Nome: ");
                    String nomePaciente = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String telefonePaciente = scanner.nextLine();
                    System.out.print("Data de nascimento (dd/MM/yyyy): ");
                    String dataNasc = scanner.nextLine();
                    clinica.cadastrarPaciente(cpf, nomePaciente, telefonePaciente, dataNasc);
                    break;

                case 4:
                    System.out.println("\n--- Agendamento de Consulta ---");
                    clinica.listarPacientes();
                    clinica.listarProfissionais();
                    System.out.print("CPF do paciente: ");
                    String cpfConsulta = scanner.nextLine();
                    System.out.print("CRM do profissional: ");
                    String crmConsulta = scanner.nextLine();
                    System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
                    String dataHora = scanner.nextLine();
                    clinica.agendarConsulta(cpfConsulta, crmConsulta, dataHora);
                    break;

                case 5:
                    System.out.println("\n--- Concluir Consulta ---");
                    clinica.listarConsultasAgendadas();
                    System.out.print("Codigo da consulta: ");
                    int codigo = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Observacao medica: ");
                    String obs = scanner.nextLine();
                    clinica.concluirConsulta(codigo, obs);
                    break;

                case 6:
                    System.out.println("\n--- Cancelar Consulta ---");
                    clinica.listarConsultasAgendadas();
                    System.out.print("Codigo da consulta: ");
                    int codigoCancelamento = scanner.nextInt();
                    scanner.nextLine();
                    clinica.cancelarConsulta(codigoCancelamento);
                    break;

                case 7:
                    clinica.listarEspecialidades();
                    break;

                case 8:
                    clinica.listarProfissionais();
                    break;

                case 9:
                    clinica.listarPacientes();
                    break;

                case 10:
                    System.out.println("\n--- Consultas por Paciente ---");
                    System.out.print("CPF do paciente: ");
                    String cpfPaciente = scanner.nextLine();
                    clinica.listarConsultasPorPaciente(cpfPaciente);
                    break;

                case 11:
                    System.out.println("\n--- Consultas por Profissional ---");
                    System.out.print("CRM do profissional: ");
                    String crmProfissional = scanner.nextLine();
                    clinica.listarConsultasPorProfissional(crmProfissional);
                    break;

                case 12:
                    clinica.listarConsultasAgendadas();
                    break;

                case 13:
                    clinica.listarTodasConsultas();
                    break;

                case 14:
                    clinica.exibirRelatorio();
                    break;

                case 0:
                    System.out.println("\nEncerrando o sistema. Ate logo!");
                    break;

                default:
                    System.out.println("\nOpcao invalida. Tente novamente.");
                    break;
            }

        } while (opcao != 0);

        scanner.close();
    }
}
