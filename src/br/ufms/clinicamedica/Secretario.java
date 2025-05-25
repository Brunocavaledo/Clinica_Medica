package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Secretario extends Usuario{
    private LocalDate dataIngresso;
    private double salario;
    /**
     * Constrói um objeto Secretario informando apenas os atributos obrigatórios.
     *
     * @param nome    nome
     * @param cpf     cpf
     * @param salario salário
     */
    public Secretario(String nome, String cpf, double salario) {
        // este construtor chama o construtor abaixo para concentrar as inicializações em um mesmo lugar
        this(nome, cpf, null, null, null, null, salario);
    }

    public Secretario(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento, LocalDate dataIngresso, double salario) {
        // inicializa os atributos chamando os métodos setters que validam os valores
        super(nome, cpf, endereco, telefone, dataNascimento);
        setDataIngresso(dataIngresso);
        setSalario(salario);
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        if (dataIngresso.isBefore(getDataNascimento().plusYears(18)) || dataIngresso.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Data de ingresso inválida.");
        }
        this.dataIngresso = dataIngresso;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) { // Validador de Salário
        if (salario < 0) {
            throw new IllegalArgumentException("O salário não pode ser negativo");
        }
        this.salario = salario;
    }

    @Override
    public boolean equals(Object obj) { //Método de comparação moldado(casting) para ver se já existe alguém com esses dados
        if (obj instanceof Secretario outro) {
            return this.getCpf().equals(outro.getCpf());
        }
        return false;
    }
}
