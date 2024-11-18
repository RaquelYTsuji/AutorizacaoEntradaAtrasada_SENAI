package Cadastro;

import java.util.Scanner;

public class Menu_Simples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[][] alunos = new String[][]{{"Nome do Aluno", "Matrícula do Aluno", "AQV", "Coordenador", "Matrícula do Funcionário", "Código do Usuário"}};

        int opcao;
        String nomeAluno;
        String matriculaAluno;
        String aqv;
        String coordenador;
        String matriculaFuncionario;

        do {
            System.out.println("Escolha uma opção: ");
            System.out.println("\t 1- Criar Cadastro ");
            System.out.println("\t 2- Lista de todos os alunos cadastrados ");
            System.out.println("\t 3- Buscar aluno cadastrado  ");
            System.out.println("\t 4- Sair  ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Digite o nome do aluno: ");
                    nomeAluno = scanner.nextLine();
                    System.out.println("Digite o número da matrícula: ");
                    matriculaAluno = scanner.nextLine();
                    System.out.println("Digite o nome da AVQ: ");
                    aqv = scanner.nextLine();
                    System.out.println("Digite o nome do coordenador: ");
                    coordenador = scanner.nextLine();
                    System.out.println("Digite o número da matrícula do funcionário: ");
                    matriculaFuncionario = scanner.nextLine();

                    String[][] cadastro = adicionarCadastro(alunos, nomeAluno, matriculaAluno, aqv, coordenador, matriculaFuncionario);

                    alunos = new String[cadastro.length][cadastro[0].length];

                    for (int i = 0; i < cadastro.length; i++) {
                        for (int j = 0; j < cadastro[0].length; j++) {
                            alunos[i][j] = cadastro[i][j];
                        }
                    }

                    System.out.println("Cadastro realizado com sucesso!");
                    System.out.println("Nome do Aluno: " + nomeAluno + ", Matrícula: " + matriculaAluno +
                            ", AQV: " + aqv + ", Coordenador: " + coordenador +
                            ", Matrícula do Funcionário: " + matriculaFuncionario);
                    break;

                case 2:
                    for (int i = 0; i < alunos.length; i++) {
                        for (int j = 0; j < alunos[0].length; j++) {
                            System.out.print(alunos[i][j] + "\t");
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    System.out.println("Digite o número da matrícula que deseja buscar: ");
                    int matriculaBusca = scanner.nextInt();
                    System.out.println("O número da sua matrícula é: " + matriculaBusca);
                   break;

                case 4:
                    System.out.println("Saindo...");
                    opcao = 4;
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }

    static String[][] adicionarCadastro(String[][] alunos, String nomeAluno, String matriculaALuno, String avq,
                                        String coordenador, String matriculaFuncionario){
        String[][] cadastro = new String[alunos.length+1][alunos[0].length];

        for (int i = 0; i < alunos.length; i++) {
            for (int j = 0; j < alunos[i].length; j++) {
                cadastro[i][j] = alunos[i][j];
            }
        }

        cadastro[cadastro.length-1][0] = nomeAluno;
        cadastro[cadastro.length-1][1] = matriculaALuno;
        cadastro[cadastro.length-1][2] = avq;
        cadastro[cadastro.length-1][3] = coordenador;
        cadastro[cadastro.length-1][4] = matriculaFuncionario;

        return cadastro;
    }
}

