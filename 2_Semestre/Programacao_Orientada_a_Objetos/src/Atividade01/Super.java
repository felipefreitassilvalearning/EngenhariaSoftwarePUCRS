package Atividade01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Super {
    private GaragemLocomotivas garagemLocomotivas;
    private ArrayList<Locomotiva> locomotivas;
    private GaragemVagoes garagemVagoes;
    private ArrayList<Vagao> vagoes;
    private PatioDeManobras patioDeManobras;

    public Super() {
        garagemLocomotivas = new GaragemLocomotivas();
        locomotivas = new ArrayList<>();
        garagemVagoes = new GaragemVagoes();
        vagoes = new ArrayList<>();
        patioDeManobras = new PatioDeManobras();
    }

    public boolean initialize(String locomotivasFilePath, String vagoesFilePath) {
        try {
            Scanner lReader = new Scanner(new File(locomotivasFilePath));
            Scanner vReader = new Scanner(new File(vagoesFilePath));
            String[] line;
            int identificador;

            // Skip header
            lReader.nextLine();
            while (lReader.hasNextLine()) {
                line = lReader.nextLine().split(",");
                identificador = Integer.parseInt(line[0].trim());
                double pesoMaximo = Double.parseDouble(line[1].trim());
                int qtdadeVagoes = Integer.parseInt(line[2].trim());
                Locomotiva novaLocomotiva = new Locomotiva(identificador, pesoMaximo, qtdadeVagoes);
                locomotivas.add(novaLocomotiva);
                garagemLocomotivas.adicionaGaragem(novaLocomotiva);
            }

            // Skip header
            vReader.nextLine();
            while (vReader.hasNextLine()) {
                line = vReader.nextLine().split(",");
                identificador = Integer.parseInt(line[0].trim());
                double capacidadeCarga = Double.parseDouble(line[1].trim());
                Vagao novoVagao = new Vagao(identificador, capacidadeCarga);
                vagoes.add(novoVagao);
                garagemVagoes.adicionaGaragem(novoVagao);
            }
            return true;
        } catch (FileNotFoundException fnfe) {
            System.out.println("Arquivo(s) não encontrado(s).");
            return false;
        }

    }

    public GaragemLocomotivas getGaragemLocomotivas() {
        return garagemLocomotivas;
    }

    public ArrayList<Locomotiva> getLocomotivas() {
        return locomotivas;
    }

    public GaragemVagoes getGaragemVagoes() {
        return garagemVagoes;
    }

    public ArrayList<Vagao> getVagoes() {
        return vagoes;
    }

    public PatioDeManobras getPatioDeManobras() {
        return patioDeManobras;
    }

    public void criarTrem() {
        System.out.println("(1) Criando trem");
        int idNovoTrem = pedirIdentificador("trem");
        Trem novoTrem = new Trem(idNovoTrem);
        System.out.println("Locomotivas: ");
        for (int i = 0; i < garagemLocomotivas.qtdade(); i++) {
            System.out.println(garagemLocomotivas.getPorPosicao(i));
        }
        int idNovaLocomotiva = pedirIdentificador("locomotiva");
        Locomotiva novaLocomotiva = garagemLocomotivas.getPorId(idNovaLocomotiva);
        if (novaLocomotiva != null) {
            novoTrem.engataLocomotiva(novaLocomotiva);
            novaLocomotiva.setTrem(novoTrem);
            patioDeManobras.adicionaPatio(novoTrem);
            System.out.println("Trem criado com sucesso e inserido no pátio!");
        }
    }

    public Trem editarTrem() {
        System.out.println("(2) Editar trem");

        System.out.println("Trens no pátio: ");
        listarTrens();
        int idTrem = pedirIdentificador("trem");
        return patioDeManobras.getPorId(idTrem);
    }

    public void editarTremInserirLocomotiva(Trem tremPorEditar) {
        System.out.println("(2.1) Inserir locomotiva");
        if (tremPorEditar.getQtdadeVagoes() == 0) {
            System.out.println("Locomotivas: ");
            for (int i = 0; i < garagemLocomotivas.qtdade(); i++) {
                System.out.println(garagemLocomotivas.getPorPosicao(i));
            }
            int idLocomotivaPorAdicionar = pedirIdentificador("locomotiva");
            Locomotiva locomotivaPorAdicionar = garagemLocomotivas.getPorId(idLocomotivaPorAdicionar);
            if (locomotivaPorAdicionar != null) {
                if (locomotivaPorAdicionar.livre()) {
                    tremPorEditar.engataLocomotiva(locomotivaPorAdicionar);
                    locomotivaPorAdicionar.setTrem(tremPorEditar);
                    System.out.println("Locomotiva adicionada com sucesso.");
                } else
                    System.out.println("Locomotiva indisponível!");
            } else
                System.out.println("Locomotiva inexistente, favor tentar novamente.");
        } else
            System.out.println(
                    "Não é possível adicionar locomotivas após vagões.\nFavor remover vagões antes de tentar novamente.");
    }

    public void editarTremInserirVagao(Trem tremPorEditar) {
        System.out.println("(2.2) Inserir vagao");
        System.out.println("Vagões: ");
        for (int i = 0; i < garagemVagoes.qtdade(); i++) {
            System.out.println(garagemVagoes.getPorPosicao(i));
        }
        int idVagaoPorAdicionar = pedirIdentificador("vagão");
        Vagao vagaoPorAdicionar = garagemVagoes.getPorId(idVagaoPorAdicionar);
        if (vagaoPorAdicionar == null)
            System.out.println("Vagão inexistente, favor tentar novamente.");
        else {
            if (vagaoPorAdicionar.livre()) {
                tremPorEditar.engataVagao(vagaoPorAdicionar);
                vagaoPorAdicionar.setTrem(tremPorEditar);
                System.out.println("Vagao adicionado com sucesso.");
            } else
                System.out.println("Esse vagão já está sendo utilizado.");
        }
    }

    public void editarTremRemoverUltimo(Trem tremPorEditar) {
        if (tremPorEditar.getQtdadeVagoes() > 0) {
            if (tremPorEditar.desengataVagao())
                System.out.println("Vagão desengatado com sucesso!");
            else
                System.out.println("Não há vagões para desengatar.");
        } else {
            if (tremPorEditar.desengataLocomotiva())
                System.out.println("Locomotiva desengatada com sucesso!");
            else
                System.out.println("Não foi possível remover a locomotiva.");
        }
    }

    public void editarTremListarLocomotivasLivres(Trem tremPorEditar) {
        for (Locomotiva locomotiva : locomotivas) {
            if (locomotiva.livre())
                System.out.println(locomotiva);
        }
    }

    public void editarTremListarVagoesLivres(Trem tremPorEditar) {
        for (Vagao vagao : vagoes) {
            if (vagao.livre())
                System.out.println(vagao);
        }
    }

    public void desfazerTrem(Trem tremPorDesfazer) {
        for (int i = 0; i < tremPorDesfazer.getQtdadeVagoes(); i++) {
            tremPorDesfazer.desengataVagao();
        }
        for (int i = 0; i < tremPorDesfazer.getQtdadeLocomotivas(); i++) {
            locomotivas.get(i).setTrem(null);
            tremPorDesfazer.desengataLocomotiva();
        }
        patioDeManobras.removePatio(tremPorDesfazer.getIdentificador());
    }

    public boolean listarTrens() {
        if (patioDeManobras.qtdade() > 0) {
            for (int i = 0; i < patioDeManobras.qtdade(); i++) {
                System.out.println(patioDeManobras.getPorPosicao(i));
            }
            return true;
        }
        return false;
    }

    public int pedirIdentificador(String para) {
        System.out.printf("%nDigite o identificador do %s: ", para);
        return Scanners.inputInt();
    }

}
