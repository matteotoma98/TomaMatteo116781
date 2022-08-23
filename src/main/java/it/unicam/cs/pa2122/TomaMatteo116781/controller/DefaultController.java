package it.unicam.cs.pa2122.TomaMatteo116781.controller;

import it.unicam.cs.pa2122.TomaMatteo116781.model.DefaultPlane;
import it.unicam.cs.pa2122.TomaMatteo116781.model.SyntaxInstructionErrorException;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Instruction;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Line;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Plane;
import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Point;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Formatter;
import java.util.logging.*;

/**
 * Implementazione del controller dell' interfaccia it.unicam.cs.2122.TomaMatteo116781.controller.Controller
 * I parametri sono point per rappresentare il punto e double per aumentare la precisione.
 * Implementa i metodi dell'interfaccia.
 */
public class DefaultController implements Controller<Point<Double>> {

    private static final Logger logger = Logger.getLogger("it.cs.unicam.pa2122.TomaMatteo116781.DefaultController");
    private static final Formatter formatter = new SimpleFormatter();
    private static final ConsoleHandler consoleHandler = new ConsoleHandler();
    private Plane<Point<Double>> currentPlane;
    private Deque<Plane<Point<Double>>> previousPlane;
    private Deque<Plane<Point<Double>>> nextPlane;
    private Deque<String> configurationInstructions;
    private List<String> allInstructions;

    @Override
    public void newPlane(double length, double height) {
        this.currentPlane = new DefaultPlane(length, height);
        this.previousPlane = new LinkedList<>();
        this.nextPlane = new LinkedList<>();
        this.configurationInstructions = new LinkedList<>();
        this.allInstructions = new ArrayList<>();
        logger.setLevel(Level.INFO);
        consoleHandler.setFormatter(formatter);
        logger.addHandler(consoleHandler);
    }

    @Override
    public synchronized Plane<Point<Double>> getPlane() {
        return this.currentPlane;
    }

    @Override
    public synchronized boolean hasPrevious() {
        return !this.previousPlane.isEmpty();
    }

    @Override
    public synchronized boolean hasNext() {
        return !this.nextPlane.isEmpty();
    }

    @Override
    public synchronized Plane<Point<Double>> previous() {
        if (this.hasPrevious()) {
            this.nextPlane.addFirst(this.currentPlane);
            this.currentPlane = this.previousPlane.removeFirst();
            this.configurationInstructions.removeLast();
            return this.currentPlane;
        }
        logger.info("Nessuna configurazione precedente da mostrare");
        return null;
    }

    @Override
    public synchronized Plane<Point<Double>> next() {
        if (this.hasNext()) {
            this.previousPlane.addFirst(this.currentPlane);
            this.currentPlane = this.nextPlane.removeFirst();
            this.configurationInstructions.addLast(this.allInstructions.get(configurationInstructions.size()));
            return this.currentPlane;
        }
        logger.info("Nessuna configurazione successiva da mostrare");
        return null;
    }

    @Override
    public synchronized void loadInstructions(String filePath) throws IOException {
        if (!Files.exists(Path.of(filePath)))
            logger.severe("Path non esistente");
        else {
            List<String> lst = Files.lines(Path.of(filePath))
                    .map(l -> l.split(" "))
                    .flatMap(Arrays::stream).toList();
            if (lst.isEmpty()) {
                logger.severe("Il file è vuoto");
                return;
            }
            this.allInstructions.add(lst.get(0));
            if (isNumeric(lst.get(0))) {
                logger.log(Level.SEVERE, "Eccezione: " + new SyntaxInstructionErrorException("Un numero non è un'istruzione valida"));
            }
            int j = 0;
            boolean repeat = false;
            /*
            SCRITTURA DEL FILE
             */
            for (int i = 1; i < lst.size(); i++) {
                if (isNumeric(lst.get(i)))
                    this.allInstructions.set(j, this.allInstructions.get(j).concat(" " + lst.get(i)));
                else {
                    if (lst.get(i).charAt(0) == '[') {
                        repeat = true;
                        this.allInstructions.set(j, this.allInstructions.get(j).concat(" " + lst.get(i).substring(1)));
                    } else if (lst.get(i).charAt(lst.get(i).length() - 1) == ']') {
                        this.allInstructions.set(j, this.allInstructions.get(j).concat(" " + lst.get(i).substring(0, lst.get(i).length() - 1)));
                        repeat = false;
                    } else if (repeat)
                        this.allInstructions.set(j, this.allInstructions.get(j).concat(" " + lst.get(i)));
                    else {
                        this.allInstructions.add(lst.get(i));
                        j++;
                    }
                }
            }
        }
    }


    @Override
    public List<String> getAllInstructions() {
        return this.allInstructions;
    }

    @Override
    public Deque<String> getConfigurationInstructions() {
        return this.configurationInstructions;
    }

    @Override
    public boolean createLOGOFile(String filepath) throws IOException {

        StringBuilder outputFile = new StringBuilder();
        for (int i = 0; i < filepath.length(); i++) {
            outputFile.append(filepath.charAt(i));
        }
        File file = new File(outputFile.toString());
        boolean result = file.createNewFile();
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write("SIZE " + getPlane().getLength() + " " + getPlane().getHeight() + " "
                + this.currentPlane.getCursor().getAreaColor() + "\n");

        fileWriter.write("POLYGON " + this.getPlane().getNumLines() + " " + this.currentPlane.getCursor().getAreaColor() + "\n");
        for (Line<Point<Double>> l : this.getPlane().getLines()) {
            if (!this.currentPlane.getClosedAreas().contains((l))) {
                fileWriter.write(l + "");
                fileWriter.write(l.getSize() + "\n");
            }
        }
        fileWriter.close();
        if (result)
            logger.info("File creato con successo");
        else if (file.exists() && !file.isDirectory()) {
            logger.severe("Il file esiste già. Cambiare nome");
        } else
            logger.severe("Errore nelle creazione del file.");
        return result;
    }

    private boolean isNumeric(String s) {
        if (s == null) {
            return false;
        }
        try {
            Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    @Override
    public synchronized void execute(String instruction) {
        Plane<Point<Double>> generatedPlane = new DefaultPlane(this.currentPlane);
        Instruction<Point<Double>> instr;
        String[] nameCmd = instruction.split(" ");
        StringBuilder log = new StringBuilder();
        boolean defaultCase = false;
        switch (nameCmd[0]) {
            case "FORWARD" -> {
                checkLOGOSyntax(nameCmd[0], 2, nameCmd.length);
                instr = Instruction::forward;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1]);
            }
            case "BACKWARD" -> {
                checkLOGOSyntax(nameCmd[0], 2, nameCmd.length);
                instr = Instruction::backward;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1]);
            }
            case "LEFT" -> {
                checkLOGOSyntax(nameCmd[0], 2, nameCmd.length);
                instr = Instruction::left;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1]);
            }
            case "RIGHT" -> {
                checkLOGOSyntax(nameCmd[0], 2, nameCmd.length);
                instr = Instruction::right;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1]);
            }
            case "CLEARSCREEN" -> {
                instr = Instruction::clearScreen;
                generatedPlane = instr.execute(this.currentPlane);
            }
            case "HOME" -> {
                instr = Instruction::home;
                generatedPlane = instr.execute(this.currentPlane);
            }
            case "PENUP" -> {
                instr = Instruction::penUp;
                generatedPlane = instr.execute(this.currentPlane);
            }
            case "PENDOWN" -> {
                instr = Instruction::penDown;
                generatedPlane = instr.execute(this.currentPlane);
            }
            case "SETPENCOLOR" -> {
                checkLOGOSyntax(nameCmd[0], 4, nameCmd.length);
                instr = Instruction::setPenColor;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1], nameCmd[2], nameCmd[3]);
            }
            case "SETFILLCOLOR" -> {
                checkLOGOSyntax(nameCmd[0], 4, nameCmd.length);
                instr = Instruction::setFillColor;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1], nameCmd[2], nameCmd[3]);
            }
            case "SETSCREENCOLOR" -> {
                checkLOGOSyntax(nameCmd[0], 4, nameCmd.length);
                instr = Instruction::setScreenColor;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1], nameCmd[2], nameCmd[3]);
            }
            case "SETPENSIZE" -> {
                instr = Instruction::setPenSize;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1]);
            }
            case "REPEAT" -> {
                List<String> lstCmd = new ArrayList<>(Arrays.asList(nameCmd).subList(2, nameCmd.length));
                instr = Instruction::repeat;
                generatedPlane = instr.execute(this.currentPlane, nameCmd[1], lstCmd);
            }
            default -> {
                defaultCase = true;
                if (!isNumeric(nameCmd[0])) {
                    logger.severe(" Istruzione all'interno del file non esistente: " + instruction);
                } else {
                    logger.severe("Un numero non è un'istruzione " + instruction);
                }
                logger.severe("Istruzione non eseguita: " + instruction + " assicurarsi che ci sia un'istruzione " +
                        "per linea");
            }
        }
        if (!defaultCase) {
            for (String s : nameCmd)
                log.append(s).append(" ");
            logger.setUseParentHandlers(false);
            logger.info("Istruzione eseguita: " + log);
            System.out.print("\t\n");
        }
        this.previousPlane.addFirst(this.currentPlane);
        this.currentPlane = generatedPlane;
        this.configurationInstructions.addLast(instruction);
    }


    @Override
    public void clear() {
        this.currentPlane = new DefaultPlane(this.currentPlane.getLength(), this.currentPlane.getHeight());
        this.configurationInstructions = new LinkedList<>();
        this.previousPlane = new LinkedList<>();
        this.nextPlane = new LinkedList<>();
        this.allInstructions = new LinkedList<>();
        logger.info("Tutte le configurazioni sono state cancellate");
    }

    @Override
    public void clearNext() {
        this.nextPlane.clear();
    }

    private void checkLOGOSyntax(String instruction, int expectedSize, int size) {
        if (expectedSize != size)
            logger.log(Level.SEVERE, "Eccezione lanciata: " + new SyntaxInstructionErrorException("Errore di sintassi per l'istruzione: " + instruction));
    }

}
