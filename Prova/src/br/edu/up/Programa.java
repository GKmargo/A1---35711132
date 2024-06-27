package br.edu.up;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import br.edu.up.modelo.Aluno;
import br.edu.up.modelo.Resumo;

public class Programa {
    private static final String ARQUIVO_ALUNOS = "C:\\Users\\User\\OneDrive\\Documentos\\A1\\Prova\\src\\br\\edu\\up\\dao\\alunos.csv";
    private static final String ARQUIVO_RESUMO = "C:\\Users\\User\\OneDrive\\Documentos\\A1\\Prova\\src\\br\\edu\\up\\dao\\resumo.csv";
    private static final String HEADER_RESUMO = "Quantidade de Alunos;Aprovados;Reprovados;Menor Nota;Maior Nota;Média Geral";

    public static void main(String[] args) {
        List<Aluno> listaDeAlunos = lerAlunos();

        if (listaDeAlunos != null) {
            Resumo resumo = processarDados(listaDeAlunos);
            gravarResumo(resumo);
        }
    }

    public static List<Aluno> lerAlunos() {
        List<Aluno> listaDeAlunos = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO_ALUNOS, StandardCharsets.UTF_8))) {
            String linha;
            boolean isFirstLine = true;
            while ((linha = br.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue; // pula a primeira linha (cabeçalho)
                }
                String[] dados = linha.split(";");
                String matricula = dados[0];
                String nome = dados[1].trim(); // Remove espaços extras
                double nota = Double.parseDouble(dados[2].replace(",", ".")); // substitui vírgula por ponto
                listaDeAlunos.add(new Aluno(matricula, nome, nota));
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("Erro ao ler o arquivo de alunos: " + e.getMessage());
            return null;
        }

        return listaDeAlunos;
    }

    public static Resumo processarDados(List<Aluno> listaDeAlunos) {
        int quantidadeAlunos = listaDeAlunos.size();
        int aprovados = 0;
        int reprovados = 0;
        double menorNota = Double.MAX_VALUE;
        double maiorNota = Double.MIN_VALUE;
        double somaNotas = 0.0;

        for (Aluno aluno : listaDeAlunos) {
            double nota = aluno.getNota();
            somaNotas += nota;

            if (nota >= 6.0) {
                aprovados++;
            } else {
                reprovados++;
            }

            if (nota < menorNota) {
                menorNota = nota;
            }

            if (nota > maiorNota) {
                maiorNota = nota;
            }
        }

        double mediaGeral = somaNotas / quantidadeAlunos;
        return new Resumo(quantidadeAlunos, aprovados, reprovados, menorNota, maiorNota, mediaGeral);
    }

    public static void gravarResumo(Resumo resumo) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO_RESUMO, StandardCharsets.UTF_8))) {
            bw.write(HEADER_RESUMO);
            bw.newLine();
            bw.write(resumo.toCSV());
        } catch (IOException e) {
            System.out.println("Erro ao gravar o arquivo de resumo: " + e.getMessage());
        }
    }
}
