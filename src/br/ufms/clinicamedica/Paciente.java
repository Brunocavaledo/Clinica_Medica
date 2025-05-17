package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Paciente {

    private String nome;
    private String cpf;
    private Endereco endereco;
    private String telefone;
    private LocalDate dataNascimento;

    //construtor criado para instanciar os pacientes
    public Paciente(String nome, String cpf, String telefone, LocalDate dataNascimento) {
        // este construtor chama o construtor abaixo para concentrar as inicializações em um mesmo lugar
        this(nome, cpf, null, telefone, dataNascimento);
    }

    public Paciente(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento) {
        // inicializa os atributos chamando os métodos setters que validam os valores
        setNome(nome);
        setEndereco(endereco);
        setTelefone(telefone);
        setDataNascimento(dataNascimento);

        this.cpf = validarCPF(cpf);
    }

    public String getNome() {
        return nome;
    }

        // Validador de nome
    public void setNome(String nome) {
        if (nome == null) { // Não pode ser nulo
            throw new IllegalArgumentException("O nome não pode ser nulo");
        }
        nome = nome.trim(); // Elimina espaços adicionais no início ou final da string
        if (nome.isBlank()) { // Não pode estar vazio
            throw new IllegalArgumentException("O nome pode ser em branco");
        } else if (!nome.matches("^[A-Za-zÀ-ÖØ-öø-ÿ'\\-\\s]+$")) { // Passa por um regex
            throw new IllegalArgumentException("O nome possui caracteres inválidos: " + nome);
        } else if (nome.split(" ").length < 2) { // Quando verifica o nome com split ele deve ter pelo menos duas palavras, nome + sobrenome, no mínimo
            throw new IllegalArgumentException("O nome está incompleto. Informe o sobrenome");
        } else if (nome.length() < 3 || nome.length() > 60) { // Nomes não podem ser curtos demais, menores que 3 caracteres não servem
            throw new IllegalArgumentException("O nome deve ter de 3 a 60 caracteres");
        }
        this.nome = nome;
    }

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

    //Validador de telefone
    public void setTelefone(String telefone) {
        telefone = telefone != null ? telefone.trim() : null;
        if (telefone != null && !telefone.matches("^(\\d{2}9\\d{8}|\\d{2}[1-8]\\d{7})$")) {
            throw new IllegalArgumentException("Telefone inválido. Informe somente dígitos (com DDD)");
        }
        this.telefone = telefone;
    }

    //Validador de data de nascimento
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        if (dataNascimento != null) { // Nao pode ser nula
            if (dataNascimento.isBefore(LocalDate.now().minusYears(150)) ||
                    dataNascimento.isAfter(LocalDate.now())) { // Nao pode ser maior que 150, nem ser data futura.
                throw new IllegalArgumentException("Data de nascimento inválida");
            } else if (dataNascimento.isAfter(LocalDate.now().minusYears(18))) { // Tem que ser maior de 18 anos
                throw new IllegalArgumentException("O secretário deve ter pelo menos 18 anos");
            }
        }
        this.dataNascimento = dataNascimento;
    }

    //Método de Validador de CPF's:
    private String validarCPF(String cpf) {
        if (cpf == null) { //Nao pode ser nulo
            throw new IllegalArgumentException("CPF nulo");
        }
        cpf = cpf.trim();
        if (!cpf.matches("\\d{11}") || cpf.chars().distinct().count() == 1) { // Tem que ter os 11 caracteres
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
        return "Paciente{" +
                "nome='" + nome + '\'' +
                ", cpf='" + cpf + '\'' +
                ", endereco=" + endereco +
                ", telefone='" + telefone + '\'' +
                ", dataNascimento=" + dataNascimento +
                '}';
    }
}
