package Aula26;

import aula26_grafos_dijkstra.FilaPrioridadeMinima;
import aula26_grafos_dijkstra.GrafoDirigidoValorado;

public class Dijkstra {
    public int[] antecessor;
    public int[] distancia;
    public boolean[] percorrido;
    private GrafoDirigidoValorado grafo;
    
    public Dijkstra(GrafoDirigidoValorado g, int origem) {
        this.grafo = g;
        antecessor = new int[grafo.getNumeroVertices()];
        distancia = new int[grafo.getNumeroVertices()];
        percorrido = new boolean[grafo.getNumeroVertices()];
        for (int i = 0; i < grafo.getNumeroVertices(); i++) {
            antecessor[i] = -1;
            distancia[i] = Integer.MAX_VALUE;
            percorrido[i] = false;
        }

        FilaPrioridadeMinima filaMin = new FilaPrioridadeMinima();
        filaMin.enfileirar(origem, 0);
        distancia[origem] = 0;

        while (!filaMin.estaVazia()) {
            int vertice = filaMin.desenfileirar();
            percorrido[vertice] = true;
            for (GrafoDirigidoValorado.ArestaDirigida aresta : g.getListaAdjacencia(vertice)) {
                int destino = aresta.destino;
                int distanciaDestino = distancia[vertice] + aresta.peso;
                if (distanciaDestino < distancia[destino]) {
                    antecessor[destino] = vertice;
                    distancia[destino] = distanciaDestino;
                    if (!filaMin.existe(destino)) filaMin.enfileirar(destino, distanciaDestino);
                    else filaMin.atualizarDistanca(destino, distanciaDestino);
                }
            }
        }
        imprimirResultado(g);
    }

    public void imprimirResultado(GrafoDirigidoValorado g) {
        System.out.println("vertice,antecessor,distancia,percorrido");
        for (int i = 0; i < g.getNumeroVertices(); i++)
            System.out.println(i + "," + antecessor[i] + "," + distancia[i] + "," + percorrido[i]);
    }
}
