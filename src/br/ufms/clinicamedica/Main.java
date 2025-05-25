package br.ufms.clinicamedica;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        try {
            // Criando um secretário
            Secretario secretario = new Secretario(
                    "Kleber Kruger",
                    "12345678909",
                    null,
                    "67991234567",
                    LocalDate.of(1988, 12, 8),
                    LocalDate.now(),
                    30000
            );

            // Criando uma clínica
            ClinicaMedica clinica = new ClinicaMedica("Clínica Universitária", "52.223.432/0001-65");

            // Adicionando o secretário à clínica
            clinica.adicionarSecretario(secretario);

            // Criando endereço do médico 1
            Endereco enderecoMedico1 = new Endereco();
            enderecoMedico1.setLogradouro("Avenida Central");
            enderecoMedico1.setNumero(456);
            enderecoMedico1.setComplemento("Sala 203");
            enderecoMedico1.setBairro("Jardim América");
            enderecoMedico1.setCep("79400-000");
            enderecoMedico1.setCidade("Coxim");
            enderecoMedico1.setEstado("MS");

            // Criando médico 1
            Medico medico1 = new Medico(
                    "Dra Nathalie Cavalcante",
                    "03281723160",
                    enderecoMedico1,
                    "67999095242",
                    LocalDate.of(1988, 11, 1),
                    "123456-MS"
            );

            // Criando endereço do médico 2
            Endereco enderecoMedico2 = new Endereco();
            enderecoMedico2.setLogradouro("Avenida Mato Grosso");
            enderecoMedico2.setNumero(789);
            enderecoMedico2.setComplemento("Casa de esquina");
            enderecoMedico2.setBairro("Vila Bela");
            enderecoMedico2.setCep("79400-000");
            enderecoMedico2.setCidade("Coxim");
            enderecoMedico2.setEstado("MS");

            // Criando médico 2
            Medico medico2 = new Medico(
                    "Dr Pimpolho",
                    "46835645059",
                    enderecoMedico2,
                    "67999586352",
                    LocalDate.of(1990, 10, 15),
                    "20514-MT"
            );

            // Adicionando médicos à clínica
            clinica.adicionarMedico(medico1);
            clinica.adicionarMedico(medico2);

            // Criando endereço do paciente
            Endereco enderecoPaciente = new Endereco();
            enderecoPaciente.setLogradouro("Rua dos Peixes Fritos");
            enderecoPaciente.setNumero(258);
            enderecoPaciente.setComplemento("Sala 171");
            enderecoPaciente.setBairro("Jardim Empamonado");
            enderecoPaciente.setCep("79400-000");
            enderecoPaciente.setCidade("Coxim");
            enderecoPaciente.setEstado("MS");

            // Criando paciente
            Paciente paciente = new Paciente(
                    "Bruno Cavalcante",
                    "94372047134",
                    enderecoPaciente,
                    "67999095498",
                    LocalDate.of(1979, 9, 14)
            );

            // Adicionando paciente à clínica
            clinica.adicionarPaciente(paciente);

            // Criando consulta com médico 1
            LocalDateTime horario1 = LocalDateTime.of(2025, 6, 17, 10, 30);
            Consulta consulta1 = clinica.criarConsulta(medico1, paciente, "Dor no ombro direito", horario1, 450.00);
            consulta1.gerarReceita("Paracetamol 500mg - Tomar 1 comprimido a cada 8 horas por 5 dias.");
            consulta1.solicitarExame("Raio-X do ombro direito");
            consulta1.solicitarExame("Exame de sangue completo");
            System.out.println("Consulta agendada: " + consulta1);

            // Criando consulta com médico 2
            LocalDateTime horario2 = LocalDateTime.of(2025, 6, 18, 10, 30);
            Consulta consulta2 = clinica.criarConsulta(medico2, paciente, "Dor no ombro esquerdo", horario2, 410.00);
            consulta2.gerarReceita("Ibuprofeno 600mg - Tomar 1 comprimido a cada 12 horas por 7 dias.");
            consulta2.solicitarExame("Ultrassonografia do ombro esquerdo");
            System.out.println("Consulta agendada: " + consulta2);

            // Testando agenda dos médicos
            System.out.println("\nAgenda do(a) " + medico1.getNome() + ":");
            medico1.getAgenda().forEach(consulta -> System.out.println("- " + consulta));
            System.out.println("\nAgenda do(a) " + medico2.getNome() + ":");
            medico2.getAgenda().forEach(consulta -> System.out.println("- " + consulta));

            // Testando conflito de horário (médico 1 já tem consulta no mesmo horário)
            try {
                LocalDateTime horarioConflito = LocalDateTime.of(2025, 6, 17, 10, 30);
                Consulta consultaConflito = clinica.criarConsulta(medico1, paciente, "Dor nas costas", horarioConflito, 400.00);
                System.out.println("Consulta agendada: " + consultaConflito);
            } catch (IllegalArgumentException e) {
                System.out.println("Erro ao agendar consulta: " + e.getMessage());
            }

            // Aqui é um teste que imprime tudo que foi salvo no console, para confirmar que deu certo a criação dos objetos e seus atributos.
            System.out.println(medico1);
            System.out.println(medico2);
            System.out.println(paciente);
            System.out.println(secretario);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
