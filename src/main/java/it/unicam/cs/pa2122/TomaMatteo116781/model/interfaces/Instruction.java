package it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces;

import it.unicam.cs.pa2122.TomaMatteo116781.model.DefaultPlane;
import it.unicam.cs.pa2122.TomaMatteo116781.model.RGBColor;
import it.unicam.cs.pa2122.TomaMatteo116781.model.GenericLine;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

/**
 * Permette di rappresentare ed eseguire un' istruzione in linguaggio LOGO nel piano di lavoro.
 * Contiene i metodi per interpretare i diversi comandi:
 * &#9679; LEFT<angolo> ruota il cursore in senso antiorario dei gradi descritti dal parametro, nel range [0, 360]).
 * &#9679; RIGHT<angolo> ruota il cursore in senso antiorario dei gradi descritti dal parametro, nel range [0, 360]).
 * &#9679; CLEARSCREEN cancella cio che &egrave; disegnato.
 * &#9679; HOME muove il cursore nella posizione di default (centro le foglio).
 * &#9679; PENUP stacca la penna dal foglio.
 * &#9679; PENDOWN attacca la penna al foglio.
 * &#9679; SETPENCOLOR <byte> <byte> <byte> imposta il colore della penna al colore,
 * rappresentato dal colore RGB rappresentato dai tre byte dati, nel range che va da 0 a 255.
 * &#9679; SETFILLCOLOR <byte> <byte> <byte> imposta il colore del riempimento di un’area chiusa.
 * &#9679; SETSCREENCOLOR <byte> <byte> <byte> imposta il colore di background dell’area di disegno.
 * &#9679; SETPENSIZE indica la grandezza del tratto della penna, <size> &egrave; un intero di grandezza >= 1;
 * &#9679; RIPETI<num> [ <cmds> ] ripete num volte la sequenza di comandi presenti nella lista dei comandi <cmds>.,
 *
 * @param <C> il tipo parametrico per le coordinate del punto nel piano.
 */
@FunctionalInterface
public interface Instruction<C> {

    /**
     * Metodo statico per impostare la direzione in base all'angolo.
     *
     * @param direction '-' per impostare la direzione in senso orario (right) , '+' per il senso antiorario. (left)
     * @param plane     il piano di disegno in cui direzionare il cursore.
     * @param angle     l' angolo per cui spostarsi nella direzione specificata.
     * @return un piano contenente le modifiche dopo aver eseguito l' istruzione 'LEFT' o 'RIGHT'.
     * @throws IllegalArgumentException se l'angolo specificato &egrave; fuori dal range predefinito (0-360).
     */
    static Plane<Point<Double>> direct(char direction, Plane<Point<Double>> plane, Object angle) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        int degrees = Integer.parseInt("" + angle);
        if (degrees < 0 || degrees > 360) throw new IllegalArgumentException("Angle out of range");
        int newDirection;
        if (direction == '-') {
            newDirection = p.getCursor().getDirection().getDirectionWay() - degrees;
            newDirection = newDirection < 0 ? (newDirection + 360) : newDirection;
        } else {
            newDirection = p.getCursor().getDirection().getDirectionWay() + degrees;
            newDirection = newDirection >= 360 ? (newDirection - 360) : newDirection;
        }
        p.getCursor().setDirection(Direction.genericDirection(newDirection));
        return p;
    }

    /**
     * Muove il cursore in avanti o indietro rispetto la sua posizione e direzione.
     *
     * @param operator '+' per andare avanti, '-' per andare indietro.
     * @param plane    il piano dove spostare il cursore.
     * @param distance la distanza da cui spostarsi dalla posizione attuale del cursore.
     * @return il piano aggiornato con le modifiche dopo aver eseguito l' istruzione 'FORWARD' o 'BACKWARD'.
     * @throws IllegalArgumentException se la distanza specificata &egrave; minore di 0.
     */
    static Plane<Point<Double>> move(char operator, Plane<Point<Double>> plane, Object distance) {
        int dist = Integer.parseInt("" + distance);
        if (dist < 0) throw new IllegalArgumentException("Hai inserito una distanza negativa. Inseriscine una > di 0 ");
        Plane<Point<Double>> p = new DefaultPlane(plane);
        Point<Double> oldPosition = Point.cartesianPoint(p.getCursorPosition().getX(), p.getCursorPosition().getY());
        int degrees = p.getCursor().getDirection().getDirectionWay();
        double coseno = Math.cos(Math.toRadians(degrees));
        double seno = Math.sin(Math.toRadians(degrees));
        double angleCos = operator == '-' ? -(dist * coseno) : (dist * coseno);
        double angleSin = operator == '-' ? -(dist * seno) : (dist * seno);
        double newX = Math.round((p.getCursorPosition().getX() + angleCos) * 100.0) / 100.0;
        double newY = Math.round((p.getCursorPosition().getY() + angleSin) * 100.0) / 100.0;
        Point<Double> newPosition = Point.cartesianPoint(newX, newY);

        checkCursorAtBorder(p, newPosition, oldPosition);

        Line<Point<Double>> l = new GenericLine<>(oldPosition, p.getCursorPosition(), plane.getCursor().getLineColor(), plane.getCursor().getPenSize());
        if (p.getCursor().isPen()) {
            p.addLine(l);
            Logger.getGlobal().info("Linea creata: " + l);
            p.getCursor().setPlot(true);
            p.getPlaneUpdateSupport().GeneratedLine(l);
        } else {
            p.getCursor().setPlot(false);
            Logger.getGlobal().info("Cursore mosso dalla posizione vecchia" + oldPosition + " a quella nuova " + newPosition);
            p.getPlaneUpdateSupport().MovedCursor(newPosition);
        }
        return p;
    }

    /**
     * Metodo statico per posizionare il cursore al bordo del piano se supera i limiti di esso.
     *
     * @param plane             il piano scelto.
     * @param newCursorPosition la nuova posizione del cursore da dover controllare.
     */
    static void checkCursorAtBorder(Plane<Point<Double>> plane, Point<Double> newCursorPosition, Point<Double> oldCursorPosition) {
        GenericLine<Point<Double>> xDownAxis = new GenericLine<>(plane.getDownLeftPoint(), plane.getDownRightPoint(), plane.getCursor().getLineColor(), plane.getCursor().getPenSize()),
                xUpAxis = new GenericLine<>(plane.getUpLeftPoint(), plane.getUpRightPoint(), plane.getCursor().getLineColor(), plane.getCursor().getPenSize()),
                yLeftAxis = new GenericLine<>(plane.getUpLeftPoint(), plane.getDownLeftPoint(), plane.getCursor().getLineColor(), plane.getCursor().getPenSize()),
                yRightAxis = new GenericLine<>(plane.getUpRightPoint(), plane.getDownRightPoint(), plane.getCursor().getLineColor(), plane.getCursor().getPenSize());

        Line<Point<Double>> line = new GenericLine<>(oldCursorPosition, newCursorPosition, plane.getCursor().getLineColor(), plane.getCursor().getPenSize());

        Optional<Point<Double>> intersectionPoint = Optional.empty();
        if (newCursorPosition.getY() >= plane.getHeight()) {
            intersectionPoint = Plane.intersect(line, xUpAxis);
            Logger.getGlobal().info("Punto d'intersezione:  " + intersectionPoint);
        }
        if (newCursorPosition.getY() < 0) {
            intersectionPoint = Plane.intersect(line, xDownAxis);
            Logger.getGlobal().info("Punto d'intersezione:  " + intersectionPoint);
        }
        if (newCursorPosition.getX() >= plane.getLength()) {
            intersectionPoint = Plane.intersect(line, yRightAxis);
            Logger.getGlobal().info("Punto d'intersezione:  " + intersectionPoint);
        }
        if (newCursorPosition.getX() < 0) {
            intersectionPoint = Plane.intersect(line, yLeftAxis);
            Logger.getGlobal().info("Punto d'intersezione:  " + intersectionPoint);
        }
        plane.getCursor().setPosition(intersectionPoint.orElse(newCursorPosition));
        Logger.getGlobal().info("Posizione attuale del cursore " + plane.getCursorPosition());
    }

    /**
     * Metodo che implementa l' istruzione FORWARD.
     * Sposta il cursore in avanti dalla posizione attuale, di una certa distanza passata in args[0].
     * Se il cursore supera l' altezza del piano per andare in avanti, si ferma al bordo del piano.
     *
     * @param plane il piano di disegno contenente il cursore da spostare.
     * @param args  la distanza di cui spostarsi dalla sua posizione.
     * @return il piano aggiornato dopo aver eseguito l'istruzione.
     * @throws IllegalArgumentException se la distanza impostata &egrave; minore di 0.
     */
    static Plane<Point<Double>> forward(Plane<Point<Double>> plane, Object... args) {
        return move('+', plane, args[0]);
    }

    /**
     * Metodo statico che implementa l' istruzione BACKWARD.
     * Sposta il cursore indietro verso la sua direzione di una certa distanza passata in args[0].
     * Se il cursore supera l' altezza del piano all' indietro, si ferma al bordo.
     *
     * @param plane il piano contenente il cursore da spostare.
     * @param args  in particolare args[0], la distanza di cui spostarsi dalla sua posizione.
     * @return un piano contenente le modifiche dopo aver eseguito l' istruzione BACKWARD.
     * @throws IllegalArgumentException se la distanza specificata &egrave; minore di 0.
     */
    static Plane<Point<Double>> backward(Plane<Point<Double>> plane, Object... args) {
        return move('-', plane, args[0]);
    }

    /**
     * Metodo che implementa l' istruzione 'LEFT'.
     * Ruota il cursore in senso antiorario rispetto ai gradi specificati.
     *
     * @param plane il piano dove risiede il cursore da ruotare.
     * @param args  in particolare args[0], i gradi di cui ruotare in senso antiorario rispetto alla direzione attuale.
     * @return un piano aggiornato alle modifiche, dopo aver eseguito l' istruzione 'LEFT'.
     * @throws IllegalArgumentException se l' angolo da impostare &egrave; fuori dal range predefinito
     */
    static Plane<Point<Double>> left(Plane<Point<Double>> plane, Object... args) {
        return direct('+', plane, args[0]);
    }

    /**
     * Metodo statico che implementa l' istruzione 'RIGHT'.
     * Ruota il cursore in senso orario rispetto ai gradi specificati.
     *
     * @param plane il piano dove risiede il cursore da ruotare.
     * @param args  in particolare args[0], i gradi di cui ruotare in senso orario rispetto alla direzione attuale.
     * @return un piano contenente le modifiche dopo aver eseguito l' istruzione RIGHT.
     * @throws IllegalArgumentException se l' angolo da impostare &egrave; fuori dal range predefinito
     */
    static Plane<Point<Double>> right(Plane<Point<Double>> plane, Object... args) {
        return direct('-', plane, args[0]);
    }

    /**
     * Metodo che implementa l' istruzione 'CLEARSCREEN'.
     * Cancella il disegno, liberando il piano di lavoro.
     *
     * @param plane il piano in cui cancellare il disegno
     * @param args  in questo caso nessun argomento (quindi un array vuoto)
     * @return un piano contenente le modifiche dopo aver eseguito l' istruzione 'CLEARSCREEN'.
     */
    static Plane<Point<Double>> clearScreen(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        p.getPlaneUpdateSupport().ScreenCleaned();
        p.getLines().clear();
        p.getClosedAreas().clear();
        p.getPoints().clear();
        p.getGraph().clear();
        return p;
    }

    /**
     * Metodo che implementa l' istruzione 'HOME'.
     * Sposta il cursore in posizione di default nel piano, ossia quelle di coordinate (base/2,altezza/2).
     *
     * @param plane il piano in cui riposizionare il cursore.
     * @param args  nessun argomento poich&egrave; il cursore non deve ruotare.
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'HOME'.
     */
    static Plane<Point<Double>> home(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        p.getCursor().setPosition(p.getHome());
        p.getPlaneUpdateSupport().MovedCursor(p.getCursorPosition());
        return p;
    }

    /**
     * Metodo che implementa l' istruzione 'PENUP'.
     * Stacca la penna dal foglio.
     * Non verr&agrave; generata nessuna linea anche se il cursore si sposta
     *
     * @param plane il piano da cui staccare la penna.
     * @param args  in questo caso nessun argomento poich&egrave; il cursore non &egrave; soggetto a modifiche di posizione/direzione
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'PENUP'.
     */
    static Plane<Point<Double>> penUp(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        p.getCursor().penUp();
        return p;
    }

    /**
     * Metodo che implementa l' istruzione 'PENDOWN'.
     * Attacca la penna al foglio, quindi se il cursore si sposta verr&agrave; generata una linea.
     *
     * @param plane il piano a cui attaccare la penna.
     * @param args  in questo caso nessun argomento poich&egrave; il cursore non &egrave; soggetto a modifiche di posizione/direzione
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'PENDOWN'.
     */
    static Plane<Point<Double>> penDown(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        p.getCursor().penDown();
        return p;
    }

    /**
     * Metodo statico che implementa l' istruzione 'SETPENCOLOR'.
     * Imposta il colore della penna con colori RGB, da usare in una combinazione specifica per impostare il colore.
     *
     * @param plane il piano in cui impostare il colore della penna.
     * @param args  in particolare args[0] sar&agrave; R che sta per rosso, args[1] = G (verde) , args[2] = B (blu)
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'SETPENCOLOR'.
     * @throws IllegalArgumentException se il colore/i colori R, G, B &egrave; fuori dal range, quindi minore di 0 o maggiore di 255.
     */
    static Plane<Point<Double>> setPenColor(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        int r = Integer.parseInt("" + args[0]), g = Integer.parseInt("" + args[1]), b = Integer.parseInt("" + args[2]);
        p.getCursor().setLineColor(new RGBColor(r, g, b));
        return p;
    }

    /**
     * Metodo statico che implementa l' istruzione 'SETFILLCOLOR'.
     * Imposta il colore RGB per riempire l' area chiusa dalle linee, in base ai parametri specificati.
     *
     * @param plane il piano in cui impostare il colore per riempire l' area chiusa.
     * @param args  anche qui, args[0] per indicare R, args[1] per G, args[2] per B.
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'SETFILLCOLOR'.
     * @throws IllegalArgumentException se il colore/i colori R, G, B &egrave; fuori dal range, quindi minore di 0 o maggiore di 255.
     */
    static Plane<Point<Double>> setFillColor(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        int r = Integer.parseInt("" + args[0]), g = Integer.parseInt("" + args[1]), b = Integer.parseInt("" + args[2]);
        p.getCursor().setAreaColor(new RGBColor(r, g, b));
        return p;
    }

    /**
     * Metodo che implementa l' istruzione 'SETSCREENCOLOR'.
     * Imposta il colore RGB di background del piano in base ai colori specificati.
     *
     * @param plane il piano dn cui impostare il colore RGB.
     * @param args  anche qui, args[0] per i parametri R, args[1] per G, args[2] per B.
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'SETSCREENCOLOR'.
     * @throws IllegalArgumentException se il colore/i colori R, G, B &egrave; fuori dal range, quindi minore di 0 o maggiore di 255.
     */
    static Plane<Point<Double>> setScreenColor(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        int r = Integer.parseInt("" + args[0]), g = Integer.parseInt("" + args[1]), b = Integer.parseInt("" + args[2]);
        p.setBackgroundColor(new RGBColor(r, g, b));
        p.getPlaneUpdateSupport().ScreenColorChanged(p.getBackgroundColor());
        return p;
    }

    /**
     * Metodo che implementa l' istruzione 'SETPENSIZE'.
     * Imposta lo spessore della penna in base alla dimensione specificata.
     *
     * @param plane il piano di cui impostare lo spessore della penna.
     * @param args  args[0], rappresenta lo spessore della penna.
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'SETPENSIZE'.
     * @throws IllegalArgumentException se lo spessore della penna impostata &egrave; minore di 1.
     */
    static Plane<Point<Double>> setPenSize(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        int size = Integer.parseInt("" + args[0]);
        p.getCursor().setPenSize(size);
        return p;
    }

    /**
     * Metodo che implementa l' istruzione 'REPEAT'.
     * Ripete la sequenza di comandi [cmds] per N volte.
     *
     * @param plane il piano in cui eseguire la sequenza di comandi per N volte.
     * @param args  in particolare args[0] indica il numero delle volte in cui ripetere le istruzioni,
     *              args[1] la lista di comandi da ripetere.
     * @return il piano aggiornato alle modifiche dopo aver eseguito l' istruzione 'REPEAT'.
     */
    static Plane<Point<Double>> repeat(Plane<Point<Double>> plane, Object... args) {
        Plane<Point<Double>> p = new DefaultPlane(plane);
        Instruction<Point<Double>> instr;

        @SuppressWarnings("unchecked")
        List<String> cmds = (List<String>) args[1];

        int n = Integer.parseInt("" + args[0]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < cmds.size(); j++)
                switch (cmds.get(j)) {
                    case "FORWARD" -> {
                        instr = Instruction::forward;
                        p = instr.execute(p, cmds.get(j + 1));
                    }
                    case "BACKWARD" -> {
                        instr = Instruction::backward;
                        p = instr.execute(p, cmds.get(j + 1));
                    }
                    case "LEFT" -> {
                        instr = Instruction::left;
                        p = instr.execute(p, cmds.get(j + 1));
                    }
                    case "RIGHT" -> {
                        instr = Instruction::right;
                        p = instr.execute(p, cmds.get(j + 1));
                    }
                    case "CLEARSCREEN" -> {
                        instr = Instruction::clearScreen;
                        p = instr.execute(p);
                    }
                    case "HOME" -> {
                        instr = Instruction::home;
                        p = instr.execute(p);
                    }
                    case "PENUP" -> {
                        instr = Instruction::penUp;
                        p = instr.execute(p);
                    }
                    case "PENDOWN" -> {
                        instr = Instruction::penDown;
                        p = instr.execute(p);
                    }
                    case "SETPENCOLOR" -> {
                        instr = Instruction::setPenColor;
                        p = instr.execute(p, cmds.get(j + 1), cmds.get(j + 2), cmds.get(j + 3));
                    }
                    case "SETFILLCOLOR" -> {
                        instr = Instruction::setFillColor;
                        p = instr.execute(p, cmds.get(j + 1), cmds.get(j + 2), cmds.get(j + 3));
                    }
                    case "SETSCREENCOLOR" -> {
                        instr = Instruction::setScreenColor;
                        p = instr.execute(p, cmds.get(j + 1), cmds.get(j + 2), cmds.get(j + 3));
                    }
                    case "SETPENSIZE" -> {
                        instr = Instruction::setPenSize;
                        p = instr.execute(p, cmds.get(j + 1));
                    }
                    case "REPEAT" -> {
                        instr = Instruction::repeat;
                        p = instr.execute(p, cmds.get(j));
                    }
                }
        }
        return p;
    }

    /**
     * Esegue una delle istruzioni LOGO nel piano di disegno.
     *
     * @param plane il piano in cui eseguire l' istruzione.
     * @param args  la lista degli argomenti di quella specifica istruzione.
     * @return un piano contenente le modifiche dopo aver eseguito l' istruzione.
     */
    Plane<C> execute(Plane<C> plane, Object... args);

}
