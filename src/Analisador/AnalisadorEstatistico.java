package Analisador;

import java.util.List;
import Filtragem.Filtravel;

public interface AnalisadorEstatistico<T extends Filtravel, R> {
  R calcular(List<T> dados);
}