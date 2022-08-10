package it.unicam.cs.pa2122.TomaMatteo116781.model;

/**
 * Viene lanciata per indicare che un metodo ha cercato di eseguire un' istruzione LOGO con sintassi
 * non rispettata.
 */
public class SyntaxInstructionErrorException extends Exception {

    public SyntaxInstructionErrorException(String error) {
        super(error);

    }
}
