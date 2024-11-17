import java.util.Map;

public class Registro implements Comparable<Registro> {
    private long chave;
    private int anoSinistro;
    private int mesSinistro;
    private int diaSinistro;
    private int horaSinistro;
    private String diaSemana;
    private String turno;
    private String regiaoAdministrativa;
    private String tipoVia;
    private String tipoLocal;
    private int tipoVeiculoEnvolvido;
    private int tipoSinistro;
    private int quantidadeVitimasFatais;

    public Registro(Map<String, String> dados) {
        this.chave = gerarChaveNumerica(dados);
        this.anoSinistro = Integer.parseInt(dados.get("Ano do Sinistro"));
        this.mesSinistro = Integer.parseInt(dados.get("Mês do Sinistro"));
        this.diaSinistro = Integer.parseInt(dados.get("Data do Sinistro").split("/")[0]);
        this.horaSinistro = Integer.parseInt(dados.get("Hora do Sinistro"));
        this.diaSemana = dados.get("Dia da Semana");
        this.turno = dados.get("Turno");
        this.regiaoAdministrativa = dados.get("Região Administrativa");
        this.tipoVia = dados.get("Tipo de via");
        this.tipoLocal = dados.get("Tipo de local do sinistro");
        this.tipoVeiculoEnvolvido = determinarTipoVeiculo(dados);
        this.tipoSinistro = determinarTipoSinistro(dados);
        this.quantidadeVitimasFatais = Integer.parseInt(dados.get("Quantidade de vítimas fatais"));
    }

    private long gerarChaveNumerica(Map<String, String> dados) {
        int ano = Integer.parseInt(dados.get("Ano do Sinistro"));
        int mes = Integer.parseInt(dados.get("Mês do Sinistro"));
        int dia = Integer.parseInt(dados.get("Data do Sinistro").split("/")[0]);
        int hora = Integer.parseInt(dados.get("Hora do Sinistro"));

        int tipoVeiculo = determinarTipoVeiculo(dados);
        int tipoSinistro = determinarTipoSinistro(dados);

        return (long) (ano * Math.pow(10, 10) + mes * Math.pow(10, 8) + dia * Math.pow(10, 6) +
                hora * Math.pow(10, 4) + tipoVeiculo * Math.pow(10, 2) + tipoSinistro);
    }

    private int determinarTipoVeiculo(Map<String, String> dados) {
        String[] tipos = { "Pedestre", "Automóvel", "Bicicleta", "Caminhão", "Motocicleta", "Ônibus" };
        for (int i = 0; i < tipos.length; i++) {
            if ("1".equals(dados.get(tipos[i] + " envolvido"))) {
                return i;
            }
        }
        return 6; // Outro
    }

    private int determinarTipoSinistro(Map<String, String> dados) {
        String[] tipos = { "Atropelamento", "Capotamento", "Choque", "Colisão frontal",
                "Colisão lateral", "Colisão transversal", "Colisão traseira",
                "Engavetamento", "Tombamento" };
        for (int i = 0; i < tipos.length; i++) {
            if ("1".equals(dados.get(tipos[i]))) {
                return i;
            }
        }
        return 9; // Outro
    }

    @Override
    public int compareTo(Registro outro) {
        return Long.compare(this.chave, outro.chave);
    }

    // Getters and Setters
    public long getChave() {
        return chave;
    }

    public void setChave(long chave) {
        this.chave = chave;
    }

    public int getAnoSinistro() {
        return anoSinistro;
    }

    public void setAnoSinistro(int anoSinistro) {
        this.anoSinistro = anoSinistro;
    }

    public int getMesSinistro() {
        return mesSinistro;
    }

    public void setMesSinistro(int mesSinistro) {
        this.mesSinistro = mesSinistro;
    }

    public int getDiaSinistro() {
        return diaSinistro;
    }

    public void setDiaSinistro(int diaSinistro) {
        this.diaSinistro = diaSinistro;
    }

    public int getHoraSinistro() {
        return horaSinistro;
    }

    public void setHoraSinistro(int horaSinistro) {
        this.horaSinistro = horaSinistro;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public String getRegiaoAdministrativa() {
        return regiaoAdministrativa;
    }

    public void setRegiaoAdministrativa(String regiaoAdministrativa) {
        this.regiaoAdministrativa = regiaoAdministrativa;
    }

    public String getTipoVia() {
        return tipoVia;
    }

    public void setTipoVia(String tipoVia) {
        this.tipoVia = tipoVia;
    }

    public String getTipoLocal() {
        return tipoLocal;
    }

    public void setTipoLocal(String tipoLocal) {
        this.tipoLocal = tipoLocal;
    }

    public int getTipoVeiculoEnvolvido() {
        return tipoVeiculoEnvolvido;
    }

    public void setTipoVeiculoEnvolvido(int tipoVeiculoEnvolvido) {
        this.tipoVeiculoEnvolvido = tipoVeiculoEnvolvido;
    }

    public int getTipoSinistro() {
        return tipoSinistro;
    }

    public void setTipoSinistro(int tipoSinistro) {
        this.tipoSinistro = tipoSinistro;
    }

    public int getQuantidadeVitimasFatais() {
        return quantidadeVitimasFatais;
    }

    public void setQuantidadeVitimasFatais(int quantidadeVitimasFatais) {
        this.quantidadeVitimasFatais = quantidadeVitimasFatais;
    }
}
