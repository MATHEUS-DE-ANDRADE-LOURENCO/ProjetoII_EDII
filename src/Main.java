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

    String nomeArquivo = "/workspaces/ProjetoII_EDII/src/sinistros_fatais.csv";
    
    try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {

    String titulos[] = br.readLine().split(";");
    for(int i = 0; i < titulos.length; i++) {
      System.out.println(i + ":" + titulos[i]);
    }

    String registroString[];
    while((registroString = br.readLine().split(";")) != null) {
      Map<String, String> mapaRegistro = new HashMap<>();

      mapaRegistro.put(titulos[7], registroString[7]);
      mapaRegistro.put(titulos[6], registroString[6]);
      mapaRegistro.put(titulos[5], registroString[5]);
      mapaRegistro.put(titulos[10], registroString[10]);
      mapaRegistro.put(titulos[9], registroString[9]);
      mapaRegistro.put(titulos[11], registroString[11]);
      mapaRegistro.put(titulos[12], registroString[12]);
      mapaRegistro.put(titulos[13], registroString[13]);
      mapaRegistro.put(titulos[22], registroString[22]);
      mapaRegistro.put(titulos[19], registroString[19]);
      mapaRegistro.put(titulos[24], registroString[24]);
      mapaRegistro.put(titulos[25], registroString[25]);
      mapaRegistro.put(titulos[26], registroString[26]);
      mapaRegistro.put(titulos[27], registroString[27]);
      mapaRegistro.put(titulos[28], registroString[28]);
      mapaRegistro.put(titulos[32], registroString[32]);
      mapaRegistro.put(titulos[33], registroString[33]);
      mapaRegistro.put(titulos[34], registroString[34]);
      mapaRegistro.put(titulos[35], registroString[35]);
      mapaRegistro.put(titulos[36], registroString[36]);
      mapaRegistro.put(titulos[37], registroString[37]);
      mapaRegistro.put(titulos[38], registroString[38]);
      mapaRegistro.put(titulos[39], registroString[39]);
      mapaRegistro.put(titulos[40], registroString[40]);
      mapaRegistro.put(titulos[43], registroString[43]);

      Registro registroTemp = new Registro(mapaRegistro);
      arvore.insert(registroTemp);
    }
    // Registro 1
    
    
      
    
  /* 
    // Distribuição temporal
    Map<Integer, Long> distribuicaoTemporal = arvore.analisar(new FiltroPorAno(2023),
        new DistribuicaoTemporalAnalisador());

    // Análise geográfica
    Map<String, Long> analiseGeografica = arvore.analisar(new FiltroPorAno(2023), new AnaliseGeograficaAnalisador());

    // Tipos de veículos
    Map<Integer, Long> tiposVeiculos = arvore.analisar(new FiltroPorAno(2023), new TiposVeiculosAnalisador());

    // Gravidade dos sinistros
    Map<String, Long> gravidadeSinistros = arvore.analisar(new FiltroPorAno(2023), new GravidadeSinistrosAnalisador());

    // Análise de tipos de vias
    Map<String, Long> analiseTiposVias = arvore.analisar(new FiltroPorAno(2023), new AnaliseTiposViasAnalisador());

    // Análise temporal comparativa
    Map<Integer, Long> analiseTemporalComparativa = arvore.analisar(new FiltroPorAno(2023),
        new AnaliseTemporalComparativaAnalisador());

    // Hotspots
    Map<String, Long> hotspots = arvore.analisar(new FiltroPorAno(2023), new HotspotsAnalisador());
  */
    } catch (IOException e) {
      System.err.println("Erro ao ler o arquivo: " + e.getMessage());
    }
  }
}

