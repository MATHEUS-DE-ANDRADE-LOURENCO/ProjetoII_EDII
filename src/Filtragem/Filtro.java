package Filtragem;

public interface Filtro<T extends Filtravel> {
  boolean aceita(T item);
}
