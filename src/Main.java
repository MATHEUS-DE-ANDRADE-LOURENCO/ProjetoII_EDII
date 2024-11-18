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
    AVL<Registro> arvoreAVL = new AVL<Registro>();
    BST<Registro> arvoreBST = new BST<Registro>();

    String nomeArquivo = "./src/sinistros_fatais.csv";
    long cont = 0;

    long tempoTotalInsercaoAVL = 0;
long tempoTotalInsercaoBST = 0;

try (BufferedReader br = new BufferedReader(new FileReader(nomeArquivo))) {
  String linha = br.readLine();

  while ((linha = br.readLine()) != null) {
    String[] registroString = linha.split(";");
    Map<String, String> mapaRegistro = new HashMap<>();

    preencherMapaRegistro(mapaRegistro, registroString);

    Registro registroTemp = new Registro(mapaRegistro);

    long inicioInsercaoAVL = System.currentTimeMillis();
    try {
      arvoreAVL.insert(registroTemp);
    } catch (RuntimeException e) {
      System.err.println("Erro ao inserir registro #" + cont + " na AVL: " + e.getMessage());
    }
    tempoTotalInsercaoAVL += System.currentTimeMillis() - inicioInsercaoAVL;

    long inicioInsercaoBST = System.currentTimeMillis();
    try {
      arvoreBST.insert(registroTemp);
    } catch (RuntimeException e) {
      System.err.println("Erro ao inserir registro #" + cont + " na BST: " + e.getMessage());
    }
      tempoTotalInsercaoBST += System.currentTimeMillis() - inicioInsercaoBST;

      cont++;
    }

    System.out.println("Tempo total de inserção AVL: " + tempoTotalInsercaoAVL + " ms");
    System.out.println("Tempo total de inserção BST: " + tempoTotalInsercaoBST + " ms");

    System.out.println("Altura da AVL: " + arvoreAVL.getHeight());
    System.out.println("Altura da BST: " + arvoreBST.getHeight());

    // Realizar análises em ambas as árvores
    realizarAnalises(arvoreAVL, "AVL");
    realizarAnalises(arvoreBST, "BST");

  } catch (IOException e) {
    System.err.println("Erro ao ler o arquivo: " + e.getMessage());
  }

  }

  private static void preencherMapaRegistro(Map<String, String> mapaRegistro, String[] registroString) {
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
  }

  private static void realizarAnalises(BST<Registro> arvore, String tipo) {
    long tempoInicio = System.currentTimeMillis();

    // Distribuição temporal
    Map<Integer, Long> distribuicaoTemporal = arvore.analisar(new FiltroPorAno(2023),
        new DistribuicaoTemporalAnalisador());
    System.out.println(tipo + " - Distribuição temporal: " + distribuicaoTemporal);

    // Análise geográfica
    Map<String, Long> analiseGeografica = arvore.analisar(new FiltroPorAno(2023), new AnaliseGeograficaAnalisador());
    System.out.println(tipo + " - Análise geográfica: " + analiseGeografica);

    // Tipos de veículos
    Map<Integer, Long> tiposVeiculos = arvore.analisar(new FiltroPorAno(2023), new TiposVeiculosAnalisador());
    System.out.println(tipo + " - Tipos de veículos: " + tiposVeiculos);

    // Gravidade dos sinistros
    Map<String, Long> gravidadeSinistros = arvore.analisar(new FiltroPorAno(2023),
        new GravidadeSinistrosAnalisador());
    System.out.println(tipo + " - Gravidade dos sinistros: " + gravidadeSinistros);

    // Análise de tipos de vias
    Map<String, Long> analiseTiposVias = arvore.analisar(new FiltroPorAno(2023), new AnaliseTiposViasAnalisador());
    System.out.println(tipo + " - Análise de tipos de vias: " + analiseTiposVias);

    // Análise temporal comparativa
    Map<Integer, Long> analiseTemporalComparativa = arvore.analisar(new FiltroPorAno(2023),
        new AnaliseTemporalComparativaAnalisador());
    System.out.println(tipo + " - Análise temporal comparativa: " + analiseTemporalComparativa);

    // Hotspots
    Map<String, Long> hotspots = arvore.analisar(new FiltroPorAno(2023), new HotspotsAnalisador());
    System.out.println(tipo + " - Hotspots: " + hotspots);

    long tempoFinal = System.currentTimeMillis();
    System.out.println("Tempo total de análises " + tipo + ": " + (tempoFinal - tempoInicio) + " ms");
  }
}
