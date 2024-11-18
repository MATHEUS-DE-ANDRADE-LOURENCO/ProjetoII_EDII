package Analises;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Analisador.AnalisadorEstatistico;
import Modelo.Registro;

public class GravidadeSinistrosAnalisador implements AnalisadorEstatistico<Registro, Map<String, Long>> {
  @Override
  public Map<String, Long> calcular(List<Registro> dados) {
    return dados.stream()
        .collect(Collectors.groupingBy(r -> r.getQuantidadeVitimasFatais() > 0 ? "Fatal" : "Não Fatal", Collectors.counting()));
  }
}
