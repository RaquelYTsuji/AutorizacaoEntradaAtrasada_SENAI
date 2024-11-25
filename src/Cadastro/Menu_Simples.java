package Cadastro;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Menu_Simples {
    private static String[][] alunos = new String[0][6]; // Inicialização vazia, pois não há alunos no início.
    private static String caminhoArquivo = "src\\Cadastro\\arquivo.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        String nomeAluno;
        String matriculaAluno;
        String aqv;
        String coordenador;
        String matriculaFuncionario;

        File arquivo = new File(caminhoArquivo); // arquivo de salvamento de dados
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("\t 1- Criar Cadastro");
            System.out.println("\t 2- Lista de todos os alunos cadastrados");
            System.out.println("\t 3- Buscar aluno cadastrado");
            System.out.println("\t 4- Sair");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nDigite o nome do aluno: ");
                    nomeAluno = scanner.nextLine();
                    System.out.println("Digite o número da matrícula: ");
                    matriculaAluno = scanner.nextLine();
                    System.out.println("Digite o nome da AQV: ");
                    aqv = scanner.nextLine();
                    System.out.println("Digite o nome do coordenador: ");
                    coordenador = scanner.nextLine();
                    System.out.println("Digite o número da matrícula do funcionário: ");
                    matriculaFuncionario = scanner.nextLine();

                    adicionarCadastro(nomeAluno, matriculaAluno, aqv, coordenador, matriculaFuncionario);

                    System.out.println("\nCadastro realizado com sucesso!");
                    break;

                case 2:
                    System.out.println("\nLista de Alunos Cadastrados: ");
                    exibirListaAlunos();
                    break;

                case 3:
                    System.out.println("Digite o número da matrícula que deseja buscar: ");
                    int matriculaBusca = scanner.nextInt();
                    buscarAlunoPorMatricula(matriculaBusca);
                    break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida! Tente novamente.");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }

    static void adicionarCadastro(String nomeAluno, String matriculaAluno, String aqv, String coordenador, String matriculaFuncionario) {
        String[][] novoCadastro = new String[alunos.length + 1][6];

        // Copiar os dados antigos para o novo array
        for (int i = 0; i < alunos.length; i++) {
            for (int j = 0; j < alunos[i].length; j++) {
                novoCadastro[i][j] = alunos[i][j];
            }
        }

        novoCadastro[novoCadastro.length - 1][0] = nomeAluno;
        novoCadastro[novoCadastro.length - 1][1] = matriculaAluno;
        novoCadastro[novoCadastro.length - 1][2] = aqv;
        novoCadastro[novoCadastro.length - 1][3] = coordenador;
        novoCadastro[novoCadastro.length - 1][4] = matriculaFuncionario;

        alunos = novoCadastro;

        salvarNoArquivo();
    }

    static void salvarNoArquivo() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            for (int i = 0; i < alunos.length; i++) {
                writer.write("Nome do Aluno: " + alunos[i][0] + ", ");
                writer.write("Matrícula: " + alunos[i][1] + ", ");
                writer.write("AQV: " + alunos[i][2] + ", ");
                writer.write("Coordenador: " + alunos[i][3] + ", ");
                writer.write("Matrícula Funcionário: " + alunos[i][4]);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void exibirListaAlunos() {
        if (alunos.length == 0) {
            System.out.println("Nenhum aluno cadastrado.");
            return;
        }

        for (int i = 0; i < alunos.length; i++) {
            System.out.println("\nAluno " + (i + 1) + ": ");
            System.out.println("Nome: " + alunos[i][0]);
            System.out.println("Matrícula: " + alunos[i][1]);
            System.out.println("AQV: " + alunos[i][2]);
            System.out.println("Coordenador: " + alunos[i][3]);
            System.out.println("Matrícula do Funcionário: " + alunos[i][4]);
            System.out.println("-----------------------------");
        }
    }

    static void buscarAlunoPorMatricula(int matriculaBusca) {
        boolean encontrado = false;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i][1].equals(String.valueOf(matriculaBusca))) {
                System.out.println("\nAluno Encontrado: ");
                System.out.println("Nome: " + alunos[i][0]);
                System.out.println("Matrícula: " + alunos[i][1]);
                System.out.println("AQV: " + alunos[i][2]);
                System.out.println("Coordenador: " + alunos[i][3]);
                System.out.println("Matrícula do Funcionário: " + alunos[i][4]);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Aluno não encontrado.");
        }
    }
}

