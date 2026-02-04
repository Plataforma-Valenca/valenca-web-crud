package org.example.projetodiogo.model;

public class Permissao {
    private Boolean alterarNota;
    private Boolean visualizarNota;
    private Boolean visualizarObs;
    private Boolean visualizarBoletim;


    // Método Construtor
    public Permissao(Boolean alterarNota, Boolean visualizarNota, Boolean visualizarObs, Boolean visualizarBoletim) {
        this.alterarNota = alterarNota;
        this.visualizarNota = visualizarNota;
        this.visualizarObs = visualizarObs;
        this.visualizarBoletim = visualizarBoletim;
    }

    // Métodos getters
    public Boolean getAlterarNota() {
        return alterarNota;
    }

    public Boolean getVisualizarNota() {
        return visualizarNota;
    }

    public Boolean getVisualizarObs() {
        return visualizarObs;
    }

    public Boolean getVisualizarBoletim() {
        return visualizarBoletim;
    }

    // Métodos setters
    public void setAlterarNota(Boolean alterarNota) {
        this.alterarNota = alterarNota;
    }

    public void setVisualizarNota(Boolean visualizarNota) {
        this.visualizarNota = visualizarNota;
    }

    public void setVisualizarObs(Boolean visualizarObs) {
        this.visualizarObs = visualizarObs;
    }

    public void setVisualizarBoletim(Boolean visualizarBoletim) {
        this.visualizarBoletim = visualizarBoletim;
    }

    // Método toString

    @Override
    public String toString() {
        return "Permissao{" +
                "alterarNota = " + alterarNota +
                ", visualizarNota = " + visualizarNota +
                ", visualizarObs = " + visualizarObs +
                ", visualizarBoletim = " + visualizarBoletim +
                '}';
    }
}
