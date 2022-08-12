package it.unicam.cs.pa2122.TomaMatteo116781.model;

import java.util.Objects;

/**
 * Classe usata per rappresentare i colori rgb per creare le combinazioni dei colori.
 */
public record RGBColor(int r, int g, int b) {
    /**
     * Crea un colore RGB con i colori rosso, verde e blu con range tra 0 e 255.
     *
     * @param r colore rosso.
     * @param g colore verde.
     * @param b colore blu.
     * @throws IllegalArgumentException se uno o pi&ugrave; parametri &egrave; fuori dal range previsto.
     */
    public RGBColor {
        rgbRangeIsCorrect(r, g, b);
    }

    private void rgbRangeIsCorrect(int r, int g, int b) {
        boolean outOfRange = false;
        String rgbValues = "";

        if (r < 0 || r > 255) {
            outOfRange = true;
            rgbValues += " Red";
        }

        if (g < 0 || g > 255) {
            outOfRange = true;
            rgbValues += " Green";
        }

        if (b < 0 || b > 255) {
            outOfRange = true;
            rgbValues += " Blue";
        }

        if (outOfRange)
            throw new IllegalArgumentException("Parameters out of range: " + rgbValues);

    }

    public int getRed() {
        return this.r;
    }

    public int getGreen() {
        return this.g;
    }

    public int getBlue() {
        return this.b;
    }

    /**
     * Verifica che due colori siano uguali.
     *
     * @param o l' oggetto da verificarne l'uguaglianza.
     * @return true se l' oggetto &egrave; uguale all' oggetto passato, false altrimenti.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RGBColor rgbColor = (RGBColor) o;
        return this.r == rgbColor.r && this.g == rgbColor.g && this.b == rgbColor.b;
    }

    /**
     * Ritorna il valore hash code per mappare l’indirizzo dell’area di memoria dove il colore &egrave; allocato con un intero univoco.
     *
     * @return l'hashcode calcolato.
     */
    @Override
    public int hashCode() {
        return Objects.hash(this.r, this.g, this.b);
    }

    /**
     * Restituisce la rappresentazione dei colori sotto forma di stringa.
     *
     * @return rappresentazione dei colori sotto forma di stringa.
     */
    @Override
    public String toString() {
        return "RGBColors{" +
                "r=" + this.r +
                ", g=" + this.g +
                ", b=" + this.b +
                '}';
    }
}
