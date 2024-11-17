package Filtros;

import Filtragem.Filtro;
import Modelo.Registro;

public class FiltroPorAno implements Filtro<Registro> {
  private final int ano;

  public FiltroPorAno(int ano) {
    this.ano = ano;
  }

  @Override
  public boolean aceita(Registro item) {
    return item.getAnoSinistro() == ano;
  }
}
