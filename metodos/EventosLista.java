package Metodos;

import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

public class EventosLista extends AbstractTableModel {
    private final String[] colunas = {"ID", "Nome", "Data", "Extras", "Classificação", "Acesso", "Atração", "Ingresso/Convite", "Tipo Evento"};
    private final ArrayList<CriarEventos> eventos;

    public EventosLista(ArrayList<CriarEventos> eventos) {
        this.eventos = eventos;
    }
    public void setEventos(ArrayList<CriarEventos> eventos) {
    this.eventos.clear(); // Limpa a lista atual de eventos
    this.eventos.addAll(eventos); // Adiciona os eventos filtrados à lista
    fireTableDataChanged(); // Notifica a tabela de que os dados foram alterados
}


    @Override
    public int getRowCount() {
        return eventos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CriarEventos evento = eventos.get(rowIndex);

        switch (columnIndex) {
            case 0:
                return evento.getId();
            case 1:
                return evento.getNome_evento();
            case 2:
                return evento.getData();
            case 3:
                return evento.getExtras();
            case 4:
                return evento.getClassificacao();
            case 5:
                return evento.getAcesso();
            case 6:
                return evento.getAtracao();
            case 7:
                return evento.getIngressoOuConvite();
            case 8:
                return evento.getTipo_evento();
            default:
                return null;
        }
    }
}
