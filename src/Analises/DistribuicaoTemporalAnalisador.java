package Analises;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Analisador.AnalisadorEstatistico;
import Modelo.Registro;

public class DistribuicaoTemporalAnalisador implements AnalisadorEstatistico<Registro, Map<Integer, Long>> {
  @Override
  public Map<Integer, Long> calcular(List<Registro> dados) {
    return dados.stream()
        .collect(Collectors.groupingBy(Registro::getHoraSinistro, Collectors.counting()));
  }
}