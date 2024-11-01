package Cadastro;

import java.util.Scanner;

public class Menu_Simples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        int matricula = 2400;
        String nomeAluno;
        String avq;
        String coordenador;
        int numeroMatriculaFuncionario;
        String dadosMocados = "";

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
                    matricula = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Digite o nome da AVQ: ");
                    avq = scanner.nextLine();
                    System.out.println("Digite o nome do coordenador: ");
                    coordenador = scanner.nextLine();
                    System.out.println("Digite o número da matrícula do funcionário: ");
                    numeroMatriculaFuncionario = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Cadastro realizado com sucesso!");
                    System.out.println("Nome do Aluno: " + nomeAluno + ", Matrícula: " + matricula +
                            ", AVQ: " + avq + ", Coordenador: " + coordenador +
                            ", Matrícula do Funcionário: " + numeroMatriculaFuncionario);

                     dadosMocados= ("O número da matricula é :" + matricula + "o nome do Aluno é "+ nomeAluno);
                    break;

                case 2:
                   System.out.println(dadosMocados);
                    break;

                case 3:
                    System.out.println("Digite o número da matrícula que deseja buscar: ");
                    int matriculaBusca = scanner.nextInt();
                    System.out.println("O número da sua matrícula é: " + matriculaBusca);
                   break;

                case 4:
                    System.out.println("Saindo...");
                    break;

                default:
                    System.out.println("Opção inválida!");
                    break;
            }
        } while (opcao != 4);

        scanner.close();
    }
}

