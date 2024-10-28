package Cadastro;

import java.util.Scanner;

public class Menu_Simples {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;
        int matrícula = 24247634;
        int nomeAluno;

        System.out.println("Escolha uma opção: ");
        System.out.println("\t 1- Cadastro ");
        System.out.println("\t 2- Consulta ");

        opcao = scanner.nextInt();

        switch (opcao){
            case 1:
                System.out.println("Digite o nome do aluno: ");
                break;
            case 2:
                System.out.println("O número da sua matrícula é: " + matrícula);
                break;
            default:
                System.out.println("Opção inválida!");
                scanner.close();


        }
    }
}

