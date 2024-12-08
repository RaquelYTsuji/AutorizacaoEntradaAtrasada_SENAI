package Cadastro;

import java.io.*;
import java.util.Scanner;

public class Menu_Simples {
    static String[][] alunos = new String[0][6];
    static String caminhoArquivo = "src\\Cadastro\\arquivo.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        String nomeAluno;
        int matriculaAluno;
        String aqv;
        String coordenador;
        int matriculaFuncionario;

        arquivo();

        do {
            System.out.println("\nEscolha uma opção:");
            System.out.println("\t 1- Criar Cadastro");
            System.out.println("\t 2- Lista de todos os alunos cadastrados");
            System.out.println("\t 3- Buscar aluno cadastrado");
            System.out.println("\t 4- Sair");
            System.out.print("Opção escolhida: ");

            opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("\nDigite o nome do aluno: ");
                    nomeAluno = scanner.nextLine();
                    System.out.println("Digite o número da matrícula: ");
                    matriculaAluno = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome da AQV: ");
                    aqv = scanner.nextLine();
                    System.out.println("Digite o nome do coordenador: ");
                    coordenador = scanner.nextLine();
                    System.out.println("Digite o número da matrícula do funcionário: ");
                    matriculaFuncionario = scanner.nextInt();
                    scanner.nextLine();

                    adicionarCadastro(nomeAluno, matriculaAluno, aqv, coordenador, matriculaFuncionario);

                    System.out.println("\nCadastro realizado com sucesso!");
                    break;

                case 2:
                    System.out.println("\nLista de alunos cadastrados: ");
                    exibirListaAlunos();
                    break;

                case 3:
                    System.out.print("Digite o número da matrícula que deseja buscar: ");
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

    static void arquivo() {
        File arquivo = new File(caminhoArquivo);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));

                int linhas = 0;
                while (bufferedReader.readLine() != null) {
                    linhas++;
                }
                String[][] novoCadastro = new String[linhas][6];
                bufferedReader.close();

                bufferedReader = new BufferedReader(new FileReader(arquivo));
                String linha;
                int i = 0;
                while ((linha = bufferedReader.readLine()) != null){
                    String[] informacoes = linha.split(", " );
                    int j = 0;
                    for (String informacao : informacoes) {
                        String[] dados = informacao.split(": ");
                        novoCadastro[i][j] = dados[1];
                        j++;
                    }
                    i++;
                }
                alunos = novoCadastro;
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void adicionarCadastro(String nomeAluno, int matriculaAluno, String aqv, String coordenador, int matriculaFuncionario) {
        String[][] novoCadastro = new String[alunos.length + 1][6];

        for (int i = 0; i < alunos.length; i++) {
            for (int j = 0; j < alunos[i].length; j++) {
                novoCadastro[i][j] = alunos[i][j];
            }
        }

        novoCadastro[novoCadastro.length - 1][0] = nomeAluno;
        novoCadastro[novoCadastro.length - 1][1] = String.valueOf(matriculaAluno);
        novoCadastro[novoCadastro.length - 1][2] = aqv;
        novoCadastro[novoCadastro.length - 1][3] = coordenador;
        novoCadastro[novoCadastro.length - 1][4] = String.valueOf(matriculaFuncionario);
        novoCadastro[novoCadastro.length - 1][5] = String.valueOf(novoCadastro.length);

        alunos = novoCadastro;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivo, true))) {
            writer.write("Nome do Aluno: " + alunos[alunos.length - 1][0] + ", ");
            writer.write("Matrícula: " + alunos[alunos.length - 1][1] + ", ");
            writer.write("AQV: " + alunos[alunos.length - 1][2] + ", ");
            writer.write("Coordenador: " + alunos[alunos.length - 1][3] + ", ");
            writer.write("Matrícula do Funcionário: " + alunos[alunos.length - 1][4] + ", ");
            writer.write("Código do Usuário: " + alunos[alunos.length - 1][5]);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void exibirListaAlunos() {
        if (alunos.length == 0) {
            System.out.println("Nenhum aluno cadastrado.");
        } else {
            for (int i = 0; i < alunos.length; i++) {
                System.out.println("\nAluno " + (i + 1));
                System.out.println("Nome: " + alunos[i][0]);
                System.out.println("Matrícula: " + alunos[i][1]);
                System.out.println("AQV: " + alunos[i][2]);
                System.out.println("Coordenador: " + alunos[i][3]);
                System.out.println("Matrícula do Funcionário: " + alunos[i][4]);
                System.out.println("Código do Usuário: " + alunos[i][5]);
                System.out.println("-----------------------------");
            }
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
                System.out.println("Código do Usuário: " + alunos[i][5]);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Aluno não encontrado.");
        }
    }
}

