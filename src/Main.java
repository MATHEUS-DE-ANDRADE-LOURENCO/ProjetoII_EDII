import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import Analises.*;
import Filtros.FiltroPorAno;
import Modelo.Registro;

public class Main {
  public static void main(String[] args) {
    AVL<Registro> arvore = new AVL<Registro>();

    String nomeArquivo = "./src/sinistros_fatais.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {

      String linha = br.readLine();

      while ((linha = br.readLine()) != null) {
        String[] registroString = linha.split(";");
        Map<String, String> mapaRegistro = new HashMap<>();

        // Preencher o mapaRegistro com todos os campos
        mapaRegistro.put("Id da Delegacia", registroString[1]);
        mapaRegistro.put("Número do Bo", registroString[2]);
        mapaRegistro.put("Data do Sinistro", registroString[4]);
        mapaRegistro.put("Dia do Sinistro", registroString[5]);
        mapaRegistro.put("Mês do Sinistro", registroString[6]);
        mapaRegistro.put("Ano do Sinistro", registroString[7]);
        mapaRegistro.put("Dia da Semana", registroString[9]);
        mapaRegistro.put("Hora do Sinistro", registroString[10]);
        mapaRegistro.put("Turno", registroString[11]);
        mapaRegistro.put("Município", registroString[12]);
        mapaRegistro.put("Região Administrativa", registroString[13]);
        mapaRegistro.put("Tipo de local do sinistro", registroString[19]);
        mapaRegistro.put("Tipo de via", registroString[22]);
        mapaRegistro.put("Pedestre envolvido", registroString[23]);
        mapaRegistro.put("Automóvel envolvido", registroString[24]);
        mapaRegistro.put("Bicicleta envolvida", registroString[25]);
        mapaRegistro.put("Caminhão envolvido", registroString[26]);
        mapaRegistro.put("Motocicleta envolvida", registroString[27]);
        mapaRegistro.put("Ônibus envolvido", registroString[28]);
        mapaRegistro.put("Outros veículos envolvidos", registroString[29]);
        mapaRegistro.put("Veículo envolvido não disponível", registroString[30]);
        mapaRegistro.put("Atropelamento", registroString[32]);
        mapaRegistro.put("Capotamento", registroString[33]);
        mapaRegistro.put("Choque", registroString[34]);
        mapaRegistro.put("Colisão frontal", registroString[35]);
        mapaRegistro.put("Colisão lateral", registroString[36]);
        mapaRegistro.put("Colisão transversal", registroString[37]);
        mapaRegistro.put("Colisão traseira", registroString[38]);
        mapaRegistro.put("Outras colisões", registroString[39]);
        mapaRegistro.put("Engavetamento", registroString[40]);
        mapaRegistro.put("Tombamento", registroString[41]);
        mapaRegistro.put("Outros sinistros", registroString[42]);
        mapaRegistro.put("Quantidade de vítimas fatais", registroString[43]);

        Registro registroTemp = new Registro(mapaRegistro);
        System.out.println(registroTemp.getChave());
        arvore.insert(registroTemp);
      }

      // Distribuição temporal
      Map<Integer, Long> distribuicaoTemporal = arvore.analisar(new FiltroPorAno(2023),
          new DistribuicaoTemporalAnalisador());

      // Análise geográfica
      Map<String, Long> analiseGeografica = arvore.analisar(new FiltroPorAno(2023), new AnaliseGeograficaAnalisador());

      // Tipos de veículos
      Map<Integer, Long> tiposVeiculos = arvore.analisar(new FiltroPorAno(2023), new TiposVeiculosAnalisador());

      // Gravidade dos sinistros
      Map<String, Long> gravidadeSinistros = arvore.analisar(new FiltroPorAno(2023),
          new GravidadeSinistrosAnalisador());

      // Análise de tipos de vias
      Map<String, Long> analiseTiposVias = arvore.analisar(new FiltroPorAno(2023), new AnaliseTiposViasAnalisador());

      // Análise temporal comparativa
      Map<Integer, Long> analiseTemporalComparativa = arvore.analisar(new FiltroPorAno(2023),
          new AnaliseTemporalComparativaAnalisador());

      // Hotspots
      Map<String, Long> hotspots = arvore.analisar(new FiltroPorAno(2023), new HotspotsAnalisador());

    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
  }
}
