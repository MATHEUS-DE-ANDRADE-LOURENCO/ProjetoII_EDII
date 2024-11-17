package Filtros;

import Filtragem.Filtro;
import Modelo.Registro;

public class FiltroPorAnoEMes implements Filtro<Registro> {
  private final int ano;
  private final int mes;

  public FiltroPorAnoEMes(int ano, int mes) {
    this.ano = ano;
    this.mes = mes;
  }

  @Override
  public boolean aceita(Registro item) {
    return item.getAnoSinistro() == ano && item.getMesSinistro() == mes;
  }
}