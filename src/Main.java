import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
  public static void main(String[] args) {
    AVL<Registro> arvore = new AVL<Registro>();

    // Registro 1
    Map<String, String> dadosRegistro1 = new HashMap<>();
    dadosRegistro1.put("Ano do Sinistro", "2016");
    dadosRegistro1.put("Mês do Sinistro", "06");
    dadosRegistro1.put("Data do Sinistro", "05/06/2016");
    dadosRegistro1.put("Hora do Sinistro", "1");
    dadosRegistro1.put("Dia da Semana", "DOMINGO");
    dadosRegistro1.put("Turno", "MADRUGADA");
    dadosRegistro1.put("Região Administrativa", "METROPOLITANA DE SÃO PAULO");
    dadosRegistro1.put("Tipo de via", "RODOVIAS");
    dadosRegistro1.put("Tipo de local do sinistro", "PUBLICO");
    dadosRegistro1.put("Pedestre envolvido", "0");
    dadosRegistro1.put("Automóvel envolvido", "0");
    dadosRegistro1.put("Bicicleta envolvida", "0");
    dadosRegistro1.put("Caminhão envolvido", "0");
    dadosRegistro1.put("Motocicleta envolvida", "1");
    dadosRegistro1.put("Ônibus envolvido", "0");
    dadosRegistro1.put("Atropelamento", "0");
    dadosRegistro1.put("Capotamento", "0");
    dadosRegistro1.put("Choque", "0");
    dadosRegistro1.put("Colisão frontal", "0");
    dadosRegistro1.put("Colisão lateral", "0");
    dadosRegistro1.put("Colisão transversal", "0");
    dadosRegistro1.put("Colisão traseira", "0");
    dadosRegistro1.put("Engavetamento", "0");
    dadosRegistro1.put("Tombamento", "0");
    dadosRegistro1.put("Quantidade de vítimas fatais", "2");
    Registro registro1 = new Registro(dadosRegistro1);
    arvore.insert(registro1);

    // Registro 2
    Map<String, String> dadosRegistro2 = new HashMap<>();
    dadosRegistro2.put("Ano do Sinistro", "2015");
    dadosRegistro2.put("Mês do Sinistro", "10");
    dadosRegistro2.put("Data do Sinistro", "21/10/2015");
    dadosRegistro2.put("Hora do Sinistro", "9");
    dadosRegistro2.put("Dia da Semana", "QUARTA");
    dadosRegistro2.put("Turno", "MANHA");
    dadosRegistro2.put("Região Administrativa", "SÃO JOSÉ DO RIO PRETO");
    dadosRegistro2.put("Tipo de via", "RODOVIAS");
    dadosRegistro2.put("Tipo de local do sinistro", "NAO DISPONIVEL");
    dadosRegistro2.put("Pedestre envolvido", "0");
    dadosRegistro2.put("Automóvel envolvido", "0");
    dadosRegistro2.put("Bicicleta envolvida", "0");
    dadosRegistro2.put("Caminhão envolvido", "1");
    dadosRegistro2.put("Motocicleta envolvida", "0");
    dadosRegistro2.put("Ônibus envolvido", "0");
    dadosRegistro2.put("Atropelamento", "0");
    dadosRegistro2.put("Capotamento", "0");
    dadosRegistro2.put("Choque", "0");
    dadosRegistro2.put("Colisão frontal", "0");
    dadosRegistro2.put("Colisão lateral", "0");
    dadosRegistro2.put("Colisão transversal", "0");
    dadosRegistro2.put("Colisão traseira", "0");
    dadosRegistro2.put("Engavetamento", "0");
    dadosRegistro2.put("Tombamento", "0");
    dadosRegistro2.put("Quantidade de vítimas fatais", "1");
    Registro registro2 = new Registro(dadosRegistro2);
    arvore.insert(registro2);

    // Registro 3
    Map<String, String> dadosRegistro3 = new HashMap<>();
    dadosRegistro3.put("Ano do Sinistro", "2018");
    dadosRegistro3.put("Mês do Sinistro", "06");
    dadosRegistro3.put("Data do Sinistro", "30/06/2018");
    dadosRegistro3.put("Hora do Sinistro", "12");
    dadosRegistro3.put("Dia da Semana", "SÁBADO");
    dadosRegistro3.put("Turno", "TARDE");
    dadosRegistro3.put("Região Administrativa", "BARRETOS");
    dadosRegistro3.put("Tipo de via", "VIAS MUNICIPAIS");
    dadosRegistro3.put("Tipo de local do sinistro", "PUBLICO");
    dadosRegistro3.put("Pedestre envolvido", "0");
    dadosRegistro3.put("Automóvel envolvido", "0");
    dadosRegistro3.put("Bicicleta envolvida", "0");
    dadosRegistro3.put("Caminhão envolvido", "0");
    dadosRegistro3.put("Motocicleta envolvida", "1");
    dadosRegistro3.put("Ônibus envolvido", "0");
    dadosRegistro3.put("Atropelamento", "0");
    dadosRegistro3.put("Capotamento", "0");
    dadosRegistro3.put("Choque", "0");
    dadosRegistro3.put("Colisão frontal", "0");
    dadosRegistro3.put("Colisão lateral", "0");
    dadosRegistro3.put("Colisão transversal", "0");
    dadosRegistro3.put("Colisão traseira", "0");
    dadosRegistro3.put("Engavetamento", "0");
    dadosRegistro3.put("Tombamento", "1");
    dadosRegistro3.put("Quantidade de vítimas fatais", "1");
    Registro registro3 = new Registro(dadosRegistro3);
    arvore.insert(registro3);

    // Exemplo de filtragem
    List<Node<Registro>> registros2016 = arvore.filtrarPorAno(2016);
    for (Node<Registro> node : registros2016) {
      Registro r = node.getData();
      System.out.println("Registro de 2016: " + r.getTipoVeiculoEnvolvido());
    }
  }
}