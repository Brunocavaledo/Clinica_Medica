package br.ufms.clinicamedica;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class ClinicaMedica {

    private String nome;
    private final String cnpjFormatado; // CNPJ com máscara

    private final String cnpj; // o CNPJ após inicializado não deve ser modificado
    // as listas também são "final" porque a referência não será modificada, isto é,
    // estas variáveis sempre apontarão para a mesma lista (isto é apenas uma boa prática)
    private final List<Medico> medicos;
    private final List<Secretario> secretarios;
    private final List<Paciente> pacientes;
    private final List<Consulta> consultas;// eu adicionei essa lista para guardar as consultas

    public ClinicaMedica(String nome, String cnpj) {
        this.nome = nome;

        // Validação de CNPJ
        if (cnpj == null || cnpj.isBlank()) { // Se é nulo ou vazio
            throw new IllegalArgumentException("CNPJ inválido");
        }

        cnpj = cnpj.trim(); // Apara as pontas
        if (!cnpj.matches("\\d{2}\\.\\d{3}\\.\\d{3}/\\d{4}-\\d{2}") || cnpj.chars().distinct().count() == 1) { // Tem que ter os 14 caracteres e digitar no formato certo
            throw new IllegalArgumentException("CNPJ inválido. Use o formato XX.XXX.XXX/XXXX-XX");
        }
        this.cnpj = cnpj;
        this.cnpjFormatado = formatarCnpj(cnpj); // Guarda a versão formatada


        this.medicos = new ArrayList<Medico>();
        this.secretarios = new ArrayList<>();
        this.pacientes = new ArrayList<>();
        this.consultas = new ArrayList<>();// faz par com a linha 17
    }

    public String getCnpj() {
        return cnpj; // Retorna o CNPJ sem máscara
    }

    public String getCnpjFormatado() {
        return cnpjFormatado; // Retorna o CNPJ formatado
    }

    private String formatarCnpj(String cnpj) { //  Aplica a máscara padrão de cnpj.
        return cnpj.substring(0, 2) + "." +
                cnpj.substring(2, 5) + "." +
                cnpj.substring(5, 8) + "/" +
                cnpj.substring(8, 12) + "-" +
                cnpj.substring(12, 14);
    }

    // Implemente aqui os métodos que você considerar necessário para que o sistema
    // atenda a todas as funcionalidades descritas...

    // Método para criar e adicionar um médico à clínica
    public Medico criarMedico(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento, String crm) {
        Medico medico = new Medico(nome, cpf, endereco, telefone, dataNascimento, crm);
        medicos.add(medico);
        return medico;
    }

    public void adicionarMedico(Medico medico) {
        medicos.add(medico);
    }

    // Método para criar e adicionar um paciente à clínica
    public Paciente criarPaciente(String nome, String cpf, Endereco endereco, String telefone, LocalDate dataNascimento) {
        Paciente paciente = new Paciente(nome, cpf, endereco, telefone, dataNascimento);
        pacientes.add(paciente);
        return paciente;
    }

    //Método para criar uma consulta
    public Consulta criarConsulta(Medico medico, Paciente paciente, String sintomas, LocalDateTime dataHora, double valor) {
        Consulta consulta = new Consulta(medico, paciente, sintomas, dataHora, valor);
        consultas.add(consulta);
        return consulta;
    }
    public Medico buscarMedicoPorNome(String nome) {
        for (Medico medico : medicos) {
            if (medico.getNome().equalsIgnoreCase(nome)) { // Ignora maiúsculas e minúsculas
                return medico;
            }
        }
        return null; // Retorna null se não encontrar
    }
}
