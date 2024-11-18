package Modelo;

import java.text.Normalizer;
import java.util.Map;

import Filtragem.Filtravel;

public class Registro implements Filtravel, Comparable<Registro> {
  private long chave;
  private int idDelegacia;
  private int numeroBo;
  private int anoSinistro;
  private int mesSinistro;
  private int diaSinistro;
  private int horaSinistro;
  private String diaSemana;
  private String turno;
  private String municipio;
  private String regiaoAdministrativa;
  private String tipoVia;
  private String tipoLocal;
  private int tipoVeiculoEnvolvido;
  private int tipoSinistro;
  private int quantidadeVitimasFatais;

  public Registro(Map<String, String> dados) {
    this.idDelegacia = Integer.parseInt(dados.get("Id da Delegacia"));
    this.numeroBo = Integer.parseInt(dados.get("Número do Bo"));
    this.anoSinistro = Integer.parseInt(dados.get("Ano do Sinistro"));
    this.mesSinistro = Integer.parseInt(dados.get("Mês do Sinistro"));
    this.diaSinistro = Integer.parseInt(dados.get("Data do Sinistro").split("/")[0]);
    this.horaSinistro = Integer.parseInt(dados.get("Hora do Sinistro"));
    this.diaSemana = limparTexto(dados.get(limparTexto("Dia da Semana")));
    this.turno = limparTexto(dados.get(limparTexto("Turno")));
    this.regiaoAdministrativa = limparTexto(dados.get(limparTexto("Região Administrativa")));
    this.tipoVia = limparTexto(dados.get(limparTexto("Tipo de via")));
    this.tipoLocal = limparTexto(dados.get(limparTexto("Tipo de local do sinistro")));
    this.tipoVeiculoEnvolvido = determinarTipoVeiculo(dados);
    this.tipoSinistro = determinarTipoSinistro(dados);
    this.municipio = limparTexto(dados.get(limparTexto("Município")));
    this.quantidadeVitimasFatais = Integer.parseInt(dados.get("Quantidade de vítimas fatais"));

    // Gerar a chave numérica no construtor
    this.chave = gerarChaveNumerica();
  }

  private long gerarChaveNumerica() {
    // Parte 1: Data e hora (mantém a ordenação principal)
    long chave = (long) anoSinistro * 10000L
        + (long) mesSinistro * 1000L
        + (long) diaSinistro * 10L
        + (long) horaSinistro;

    // Parte 2: Tipo de veículo e tipo de sinistro
    chave = chave * 10 + tipoVeiculoEnvolvido;
    chave = chave * 10 + tipoSinistro;

    // Parte 4: ID da Delegacia
    chave = chave * 100000 + (idDelegacia % 100000);

    // Parte 5: Número do BO (últimos 4 dígitos)
    chave = chave * 100000 + (numeroBo % 100000);

    return chave;
  }

  private String limparTexto(String texto) {
    if (texto == null) {
      return "";
    }
    // Remove acentos
    String semAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD)
        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
    // Converte para minúsculas e remove espaços extras
    return semAcentos.toLowerCase().trim();
  }

  private int determinarTipoVeiculo(Map<String, String> dados) {
    String[] tipos = { "pedestre", "automovel", "bicicleta", "caminhao", "motocicleta", "onibus" };
    for (int i = 0; i < tipos.length; i++) {
      String chave = limparTexto(tipos[i] + " envolvido");
      String chaveAlternativa = limparTexto(tipos[i] + " envolvida");
      if ("1".equals(dados.get(chave)) || "1".equals(dados.get(chaveAlternativa))) {
        return i;
      }
    }
    return 6; // Outro
  }

  private int determinarTipoSinistro(Map<String, String> dados) {
    String[] tipos = { "atropelamento", "capotamento", "choque", "colisao frontal",
        "colisao lateral", "colisao transversal", "colisao traseira",
        "engavetamento", "tombamento" };
    for (int i = 0; i < tipos.length; i++) {
      String chave = limparTexto(tipos[i]);
      if ("1".equals(dados.get(chave))) {
        return i;
      }
    }
    return 9; // Outro
  }

  @Override
  public int compareTo(Registro outro) {
    return Long.compare(this.chave, outro.chave);
  }

  public long getChave() {
    return chave;
  }

  public void setChave(long chave) {
    this.chave = chave;
  }

  public int getAnoSinistro() {
    return anoSinistro;
  }

  public void setAnoSinistro(int anoSinistro) {
    this.anoSinistro = anoSinistro;
  }

  public int getMesSinistro() {
    return mesSinistro;
  }

  public void setMesSinistro(int mesSinistro) {
    this.mesSinistro = mesSinistro;
  }

  public int getDiaSinistro() {
    return diaSinistro;
  }

  public void setDiaSinistro(int diaSinistro) {
    this.diaSinistro = diaSinistro;
  }

  public int getHoraSinistro() {
    return horaSinistro;
  }

  public void setHoraSinistro(int horaSinistro) {
    this.horaSinistro = horaSinistro;
  }

  public String getDiaSemana() {
    return diaSemana;
  }

  public void setDiaSemana(String diaSemana) {
    this.diaSemana = diaSemana;
  }

  public String getTurno() {
    return turno;
  }

  public void setTurno(String turno) {
    this.turno = turno;
  }

  public String getRegiaoAdministrativa() {
    return regiaoAdministrativa;
  }

  public void setRegiaoAdministrativa(String regiaoAdministrativa) {
    this.regiaoAdministrativa = regiaoAdministrativa;
  }

  public String getTipoVia() {
    return tipoVia;
  }

  public void setTipoVia(String tipoVia) {
    this.tipoVia = tipoVia;
  }

  public String getTipoLocal() {
    return tipoLocal;
  }

  public void setTipoLocal(String tipoLocal) {
    this.tipoLocal = tipoLocal;
  }

  public int getTipoVeiculoEnvolvido() {
    return tipoVeiculoEnvolvido;
  }

  public void setTipoVeiculoEnvolvido(int tipoVeiculoEnvolvido) {
    this.tipoVeiculoEnvolvido = tipoVeiculoEnvolvido;
  }

  public int getTipoSinistro() {
    return tipoSinistro;
  }

  public void setTipoSinistro(int tipoSinistro) {
    this.tipoSinistro = tipoSinistro;
  }

  public int getQuantidadeVitimasFatais() {
    return quantidadeVitimasFatais;
  }

  public void setQuantidadeVitimasFatais(int quantidadeVitimasFatais) {
    this.quantidadeVitimasFatais = quantidadeVitimasFatais;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }
}
