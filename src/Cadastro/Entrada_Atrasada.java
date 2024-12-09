package Cadastro;

import java.io.*;
import java.time.LocalDateTime;

public class Entrada_Atrasada {
    static String[][] atrasados = new String[0][5];
    static String caminhoArquivoAtrasados = "src\\Cadastro\\atrasados.txt";

    static void arquivoAtrasados() {
        File arquivo = new File(caminhoArquivoAtrasados);
        try {
            if (!arquivo.exists()) {
                arquivo.createNewFile();
            } else {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(arquivo));

                int linhas = 0;
                while (bufferedReader.readLine() != null) {
                    linhas++;
                }
                String[][] novoAtrasado = new String[linhas][5];
                bufferedReader.close();

                bufferedReader = new BufferedReader(new FileReader(arquivo));
                String linha;
                int i = 0;
                while ((linha = bufferedReader.readLine()) != null){
                    String[] informacoes = linha.split(", " );
                    int j = 0;
                    for (String informacao : informacoes) {
                        String[] dados = informacao.split(": ");
                        novoAtrasado[i][j] = dados[1];
                        j++;
                    }
                    i++;
                }
                atrasados = novoAtrasado;
                bufferedReader.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static void adicionarAtrasado(String[][] alunos, String nomeAluno, int matriculaAluno, String motivoAtraso) {
        boolean encontrado = false;
        for (int i = 0; i < alunos.length; i++) {
            if (alunos[i][1].equals(String.valueOf(matriculaAluno))) {
                encontrado = true;
                String[][] novoAtraso = new String[atrasados.length + 1][5];

                for (int j = 0; j < atrasados.length; j++) {
                    for (int k = 0; k < atrasados[k].length; k++) {
                        novoAtraso[j][k] = atrasados[j][k];
                    }
                }

                novoAtraso[novoAtraso.length - 1][0] = nomeAluno;
                novoAtraso[novoAtraso.length - 1][1] = String.valueOf(matriculaAluno);
                novoAtraso[novoAtraso.length - 1][2] = motivoAtraso;
                novoAtraso[novoAtraso.length - 1][3] = String.valueOf(false);
                novoAtraso[novoAtraso.length - 1][4] = String.valueOf(LocalDateTime.now());

                atrasados = novoAtraso;

                try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAtrasados, true))) {
                    writer.write("Nome do Aluno: " + atrasados[atrasados.length - 1][0] + ", ");
                    writer.write("Matrícula: " + atrasados[atrasados.length - 1][1] + ", ");
                    writer.write("Motivo do Atraso: " + atrasados[atrasados.length - 1][2] + ", ");
                    writer.write("Entrada Permitida: " + atrasados[atrasados.length - 1][3] + ", ");
                    writer.write("Horário: " + atrasados[atrasados.length - 1][4]);
                    writer.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                System.out.println("\nEntrada atrasada adicionada com sucesso!");
            }
        }
        if (!encontrado) {
            System.out.println("Matrícula Inválida.");
        }
    }

    static void exibirListaAtrasados() {
        if (atrasados.length == 0) {
            System.out.println("Nenhum aluno atrasado.");
        } else {
            for (int i = 0; i < atrasados.length; i++) {
                System.out.println("\nAluno " + (i + 1));
                System.out.println("Nome: " + atrasados[i][0]);
                System.out.println("Matrícula: " + atrasados[i][1]);
                System.out.println("Motivo: " + atrasados[i][2]);
                System.out.println("Entrada Permitida: " + atrasados[i][3]);
                System.out.println("Horário: " + atrasados[i][4]);
            }
        }
    }

    static void permitirAtrasados(int alunoBusca) {
        boolean encontrado = false;
        for (int i = 0; i < atrasados.length; i++) {
            if (atrasados[i][1].equals(String.valueOf(alunoBusca))) {
                atrasados[i][3] = String.valueOf(true);
                encontrado = true;

                try (FileWriter fileWriter = new FileWriter(caminhoArquivoAtrasados);){
                    fileWriter.write("");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                for (int j = 0; j < atrasados.length; j++) {
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter(caminhoArquivoAtrasados, true))){
                        writer.write("Nome do Aluno: " + atrasados[j][0] + ", ");
                        writer.write("Matrícula: " + atrasados[j][1] + ", ");
                        writer.write("Motivo do Atraso: " + atrasados[j][2] + ", ");
                        writer.write("Entrada Permitida: " + atrasados[j][3] + ", ");
                        writer.write("Horário: " + atrasados[j][4]);
                        writer.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        if (!encontrado) {
            System.out.println("Aluno não encontrado.");
        }
    }
}
