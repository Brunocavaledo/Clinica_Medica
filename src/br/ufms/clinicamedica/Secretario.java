package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Secretario {

    private String nome;
    private final String cpf; // este atributo após inicializado não será alterável
    private Endereco endereco;
    private String telefone;
    private LocalDate dataNascimento;
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

    /**
     * Cria um objeto Secretário especificando todos os campos.
     *
     * @param nome           nome
     * @param cpf            CPF
     * @param endereco       endereço
     * @param telefone       telefone
     * @param dataNascimento data de nascimento
     * @param dataIngresso   data de ingresso na clínica
     * @param salario        salário
     */
    public Secretario(String nome, String cpf, Endereco endereco, String telefone,
                      LocalDate dataNascimento, LocalDate dataIngresso, double salario) {
        // inicializa os atributos chamando os métodos setters que validam os valores
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);
        setDataIngresso(dataIngresso);
        setSalario(salario);

        // Isso garante que o cpf passará pelo validador
        this.cpf = validarCPF(cpf);
    }

    /**
     * @return o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Altera o nome
     *
     * @param nome novo nome
     */
    public void setNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        nome = nome.trim(); // elimina espaços adicionais no início ou final da string
        if (nome.isBlank()) {
            throw new IllegalArgumentException("O nome pode ser em branco");
        } else if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ'\\-\\s]+$")) {
            throw new IllegalArgumentException("O nome possui caracteres inválidos: " + nome);
        } else if (nome.split(" ").length < 2) {
            throw new IllegalArgumentException("O nome está incompleto. Informe o sobrenome");
        } else if (nome.length() < 3 || nome.length() > 60) {
            throw new IllegalArgumentException("O nome deve ter de 3 a 60 caracteres");
        }
        this.nome = nome;
    }

    /**
     * Altera o CPF
     *
     * @return novo CPF
     */
    public String getCpf() {
        return cpf; // Retorna o CPF sem máscara
    }

    public String getCpfFormatado() {
        // Aplica a máscara  de 3 em 3 dígitos e antes do final, de 2 dígitos: XXX.XXX.XXX-XX, e retorna.
        return cpf.substring(0, 3) + "." +
                cpf.substring(3, 6) + "." +
                cpf.substring(6, 9) + "-" +
                cpf.substring(9, 11);
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        telefone = telefone != null ? telefone.trim() : null;// Se não for nulo passa no trim pra tirar eventuais espaços nas pontas
        if (telefone != null && !telefone.matches("^(\\d{2}9\\d{8}|\\d{2}[1-8]\\d{7})$")) { //Passa no regex
            throw new IllegalArgumentException("Telefone inválido. Informe somente dígitos (com DDD)"); //Deu B.O. ? Lança excessão.
        }
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) { // Validador de data de nascimento.
        if (dataNascimento != null) {
            if (dataNascimento.isBefore(LocalDate.now().minusYears(150)) ||
                    dataNascimento.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Data de nascimento inválida");
            } else if (dataNascimento.isAfter(LocalDate.now().minusYears(18))) {
                throw new IllegalArgumentException("O secretário deve ter pelo menos 18 anos");
            }
        }
        this.dataNascimento = dataNascimento;
    }

    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    public void setDataIngresso(LocalDate dataIngresso) {
        if (dataIngresso != null) {
            // não há como o secretário ter ingressado antes de ter feito 18 anos
            LocalDate minDataIngresso = dataNascimento != null ? dataNascimento.plusYears(18) : LocalDate.now();
            if (dataNascimento != null) {
                if (dataIngresso.isBefore(minDataIngresso) || dataIngresso.isAfter(LocalDate.now())) {
                    throw new IllegalArgumentException("Data de ingresso inválida");
                }
            }
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

    //Método de Validador de CPF's:
    private String validarCPF(String cpf) {
        if (cpf == null) {
            throw new IllegalArgumentException("CPF nulo");
        }
        cpf = cpf.trim();
        if (!cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1) {
            throw new IllegalArgumentException("CPF inválido");
        }

        int[] peso1 = {10, 9, 8, 7, 6, 5, 4, 3, 2};
        int[] peso2 = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};

        int soma1 = 0, soma2 = 0;
        for (int i = 0; i < 9; i++) {
            int dig = Character.getNumericValue(cpf.charAt(i));
            soma1 += dig * peso1[i];
            soma2 += dig * peso2[i];
        }
        int dig1 = soma1 % 11 < 2 ? 0 : 11 - (soma1 % 11);
        soma2 += dig1 * peso2[9];
        int dig2 = soma2 % 11 < 2 ? 0 : 11 - (soma2 % 11);
        if (dig1 != Character.getNumericValue(cpf.charAt(9)) || dig2 != Character.getNumericValue(cpf.charAt(10))) {
            throw new IllegalArgumentException("CPF inválido");
        }
        return cpf;
    }
    @Override
    public String toString() {// esse método que descobri é bom pra eu conseguir ver os dados dentro de um objeto, pra fins de teste mesmo.
        return "Secretário{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
