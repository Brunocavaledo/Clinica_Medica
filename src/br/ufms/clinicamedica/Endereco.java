package br.ufms.clinicamedica;

public class Endereco {

    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private Estado estado;

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        if (logradouro == null || logradouro.isBlank()) { // Validador de Logradouro
            throw new IllegalArgumentException("Logradouro nulo ou vazio");
        }
        this.logradouro = logradouro;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        if (numero != null && numero < 0) { // Validador de Número
            throw new IllegalArgumentException("Numero inválido");
        }
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        if (complemento != null && complemento.length() > 50) { //Validador de Complemento
            throw new IllegalArgumentException("O complemento deve ter no máximo 50 caracteres");
        }
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        if (bairro == null || bairro.isBlank() || bairro.length() > 30) { //Validador de Bairro
            throw new IllegalArgumentException("Bairro nulo ou vazio");
        }
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        if (cep == null || cep.isBlank()) { //Validador para o CEP
            throw new IllegalArgumentException("Cep nulo ou vazio");
        }

        if (!cep.matches("\\d{5}-\\d{3}")) {
            throw new IllegalArgumentException("Formato de CEP inválido. Use o padrão #####-###.");
        }

        this.cep = cep;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        if (cidade == null || cidade.isBlank()) { // Validador de Cidade
            throw new IllegalArgumentException("Cidade nula ou vazio");
        }
        this.cidade = cidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(String sigla) {
        this.estado = Estado.fromSigla(sigla);  // Valida a sigla antes de definir o estado, esse validador está no Enum Estado
    }
    @Override
    public String toString() {// esse método que descobri é bom pra eu conseguir ver os dados dentro de um objeto, pra fins de teste mesmo.
        return "Endereco{" +
                "logradouro='" + logradouro + '\'' +
                ", numero=" + numero +
                ", complemento='" + complemento + '\'' +
                ", bairro='" + bairro + '\'' +
                ", cep='" + cep + '\'' +
                ", cidade='" + cidade + '\'' +
                ", estado=" + estado +
                '}';
    }
}
