package br.edu.up.modelo;

public class Resumo {
    private int quantidadeAlunos;
    private int aprovados;
    private int reprovados;
    private double menorNota;
    private double maiorNota;
    private double mediaGeral;
   
    public Resumo(int quantidadeAlunos, int aprovados, int reprovados, double menorNota, double maiorNota, double mediaGeral) {
        this.quantidadeAlunos = quantidadeAlunos;
        this.aprovados = aprovados;
        this.reprovados = reprovados;
        this.menorNota = menorNota;
        this.maiorNota = maiorNota;
        this.mediaGeral = mediaGeral;
    }
    
    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }
    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }
    public int getAprovados() {
        return aprovados;
    }
    public void setAprovados(int aprovados) {
        this.aprovados = aprovados;
    }
    public int getReprovados() {
        return reprovados;
    }
    public void setReprovados(int reprovados) {
        this.reprovados = reprovados;
    }
    public double getMenorNota() {
        return menorNota;
    }
    public void setMenorNota(double menorNota) {
        this.menorNota = menorNota;
    }
    public double getMaiorNota() {
        return maiorNota;
    }
    public void setMaiorNota(double maiorNota) {
        this.maiorNota = maiorNota;
    }
    public double getMediaGeral() {
        return mediaGeral;
    }
    public void setMediaGeral(double mediaGeral) {
        this.mediaGeral = mediaGeral;
    }
    
    public String toCSV() {
        return quantidadeAlunos + ";" + aprovados + ";" + reprovados + ";" + menorNota + ";" + maiorNota + ";" + mediaGeral;
    }
}
