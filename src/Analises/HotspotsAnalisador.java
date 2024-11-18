package Analises;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import Analisador.AnalisadorEstatistico;
import Modelo.Registro;

public class HotspotsAnalisador implements AnalisadorEstatistico<Registro, Map<String, Long>> {
  private static final int LIMITE_HOTSPOT = 5;

  @Override
  public Map<String, Long> calcular(List<Registro> dados) {
    Map<String, Long> contagemPorLocal = dados.stream()
        .collect(Collectors.groupingBy(
            this::obterLocalizacao,
            Collectors.counting()));

    return contagemPorLocal.entrySet().stream()
        .filter(entry -> entry.getValue() > LIMITE_HOTSPOT)
        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
  }

  private String obterLocalizacao(Registro registro) {
    String municipio = registro.getMunicipio();
    String regiaoAdministrativa = registro.getRegiaoAdministrativa();
    return (municipio != null && !municipio.isEmpty()) ? municipio : regiaoAdministrativa;
  }
}