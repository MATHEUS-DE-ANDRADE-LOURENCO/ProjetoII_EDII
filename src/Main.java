import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        AVL<Registro> arvore = new AVL<Registro>();

        // Exemplo de como inserir um registro
        Map<String, String> dadosRegistro = new HashMap<>();
          dadosRegistro.put("Mês do Sinistro", "06");

        dadosRegistro.put("Data do Sinistro", "05/06/2016");

         dadosRegistro.put("Hora do Sinistro", "1");

          dadosRegistro.put("Dia da Semana", "DOMINGO");

           dadosRegistro.put("Turno", "MADRUGADA");

            dadosRegistro.put("Região Administrativa", "METROPOLITANA DE SÃO PAULO");

             dadosRegistro.put("Tipo de via", "RODOVIAS");

              dadosRegistro.put("Tipo de local do sinistro", "PUBLICO");

               dadosRegistro.put("Motocicleta envolvida", "1");

                dadosRegistro.put("Quantidade de vítimas fatais", "2");

        Registro registro = new Registro(dadosRegistro);
        Registro registro1 = new Registro(dadosRegistro);

        arvore.insert(registro);
        arvore.insert(registro1);

        // Exemplo de filtragem
        List<Node<Registro>> registros2016 = arvore.filtrarPorAno(2016);
        for (Node<Registro> node : registros2016) {
            Registro r = node.getData();
            System.out.println("Registro de 2016: " + r.getTipoVeiculoEnvolvido());
        }
    }
}
