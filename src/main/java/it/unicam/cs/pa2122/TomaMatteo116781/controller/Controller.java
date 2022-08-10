package it.unicam.cs.pa2122.TomaMatteo116781.controller;

import it.unicam.cs.pa2122.TomaMatteo116781.model.interfaces.Plane;

import java.io.IOException;
import java.util.Deque;
import java.util.List;

/**
 * Definisce il controller per poter gestire tutti i comandi in linguaggio LOGO.
 *
 * @param <C> il tipo parametrico delle coordinate del punto nel piano.
 */
public interface Controller<C> {

    /**
     * Crea un nuovo piano con le dimensioni specificate.
     *
     * @param length l' altezza del piano di disegno.
     * @param height la lunghezza del piano di disegno.
     */
    void newPlane(double length, double height);

    /**
     * Ritorna la configurazione del piano corrente.
     *
     * @return la configurazione del piano corrente.
     */
    Plane<C> getPlane();

    /**
     * Permette di verificare se &egrave; possibile tornare alla configurazione del piano precedente rispetto a quella attuale.
     *
     * @return true se &egrave; possibile tornare alla configurazione precedente a quella attuale, false altrimenti.
     */
    boolean hasPrevious();

    /**
     * Permette di verificare se &egrave; possibile andare alla configurazione del piano successiva a quella attuale.
     *
     * @return true se &egrave; possibile andare alla configurazione successiva a quella attuale, false altrimenti.
     */
    boolean hasNext();

    /**
     * Restituisce la configurazione del piano precedente a quella attuale dopo aver eseguito l'istruzione letta.
     *
     * @return la configurazione del piano precedente a quella attuale.
     */
    Plane<C> previous();

    /**
     * Restituisce la configurazione del piano successiva a quella attuale dopo aver eseguito l'istruzione letta.
     *
     * @return la configurazione del piano successiva a quella attuale.
     */
    Plane<C> next();

    /**
     * Restituisce la lista delle istruzioni LOGO contenute nel file con il percorso passato come parametro.
     * Viene lanciata una SyntaxInstructionErrorException se viene caricato un numero invece di lettere come istruzione.
     *
     * @param filePath il percorso assoluto/relativo del file da aprire e leggere.
     * @throws IOException se c'&egrave; un errore di input output nell' apertura del file.
     */
    void loadInstructions(String filePath) throws IOException;

    /**
     * Restituisce la lista di tutte le istruzioni presenti nel file caricato.
     *
     * @return la lista delle istruzioni presenti nel file caricato.
     */
    List<String> getAllInstructions();

    /**
     * Restituisce una collezione lineare che supporta l'inserimento e la rimozione degli elementi a entrambe le estremità,
     * di tutte le istruzioni di una determinata configurazione del piano.
     *
     * @return la coda di tutte le istruzioni per una determinata configurazione.
     */
    Deque<String> getConfigurationInstructions();

    /**
     * Apre la schermata per salvare il file inserendo un nome del file a scelta.
     * Nel file vengono memorizzate tutte le linee presenti nel piano e tutte le aree chiuse che formano la figura,
     * ognuna di queste su una riga del file, comprese tutte le loro caratteristiche (posizione, colori).
     * All'inizio del file è definito il SIZE delle aree chiusa formata dalle linee, data da base e altezza.
     * * @param filepath il percorso assoluto o relativo del file utilizzato per le istruzioni.
     * * @return true se il file non esisteva ed &egrave; stato creato con successo, false altrimenti.
     *
     * @throws IOException se ci sono errori di Input/Output con il file.
     */
    boolean createLOGOFile(String filepath) throws IOException;

    /**
     * Esegue un' istruzione in linguaggio LOGO nel piano.
     * Viene lanciata una SyntaxInstructionErrorException se ci sono errori di sintassi nell' istruzione da eseguire.
     *
     * @param instruction l' istruzione da eseguire.
     */
    void execute(String instruction);

    /**
     * Cancella tutte le configurazioni precedenti e successive del piano, con le rispettive
     * istruzioni per quelle configurazioni.
     */
    void clear();

    /**
     * Cancella tutte le configurazioni successive al piano.
     */
    void clearNext();

}
