import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws Exception {

        // Abre o arquivo para leitura
        String separadorCSV = ";";
        String nomeArquivo = "/home/aluno/projetoEDII/projII/src/sinistros_fatais.csv";
        BufferedReader leitorArquivo = new BufferedReader(new FileReader(nomeArquivo));

        // Lê a primeira linha do arquivo

        String linha = "";
        linha = leitorArquivo.readLine();

        System.out.println(linha.split(separadorCSV));





    }
}
