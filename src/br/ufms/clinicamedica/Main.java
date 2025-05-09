package br.ufms.clinicamedica;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        try {
            Secretario secretario = new Secretario(
                    "Kleber Kruger",
                    "12345678909",
                    null,
                    "67991234567",
                    LocalDate.of(1988, 12, 8),
                    LocalDate.now(), 30000
            );

            ClinicaMedica clinica = new ClinicaMedica("Clínica Universitária", "52.223.432.0001-65");
            // Implemente seus testes aqui...
            // Experimente cadastrar um secretário, médico, paciente, agendar uma consulta...

            // Criando um endereço pro médico
            Endereco enderecoMedico = new Endereco(); // Criamos o objeto vazio
            enderecoMedico.setLogradouro("Avenida Central");
            enderecoMedico.setNumero(456);
            enderecoMedico.setComplemento("Sala 203");
            enderecoMedico.setBairro("Jardim América");
            enderecoMedico.setCep("79400-000");
            enderecoMedico.setCidade("Coxim");
            enderecoMedico.setEstado(Estado.MS);

            Medico medico = new Medico(
                    "Dra Nathalie Cavalcante",
                    "03281723160",
                    enderecoMedico, // Agora passamos o objeto Endereco completo
                    "67999095242",
                    LocalDate.of(1988, 11, 01),
                    "178254-5"
            );

            // Criando um endereço pro paciente e adicionando um paciente
            Endereco enderecoPaciente = new Endereco();
            enderecoPaciente.setLogradouro("Rua dos Peixes");
            enderecoPaciente.setNumero(258);
            enderecoPaciente.setComplemento("Sala 171");
            enderecoPaciente.setBairro("Jardim Empamonado");
            enderecoPaciente.setCep("79400-000");
            enderecoPaciente.setCidade("Coxim");
            enderecoPaciente.setEstado(Estado.MS);

            Paciente paciente = clinica.criarPaciente(
                    "Bruno Cavalcante",
                    "94372047134",
                    enderecoPaciente,
                    "67999095498",
                    LocalDate.of(1979, 9, 14)
            );
            // Aqui é um teste que imprime tudo que foi salvo no console, de forma a confirmar que deu certo a criação dos objetos e seus atributos.
            System.out.println(medico);
            System.out.println(paciente);
            System.out.println(secretario);

        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }
}
