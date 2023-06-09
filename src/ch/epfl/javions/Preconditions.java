package ch.epfl.javions;

/**
 * Permet de projeter des coordonnées géographiques selon la projection WebMercator
 *
 * @author Ethan Boren (361582)
 * @author Ryad Aouak (315258)
 */
public final class Preconditions {

    /**
     * Constructeur de preconditions qui n'est pas instantiable
     */
    private Preconditions() {}

    /**
     * Lève une exception si l'argument est faux
     *
     * @param shouldBeTrue le boolean qui ressort vrai si son argument est faux
     * @throws IllegalArgumentException si son argument est faux
     */
    public static void checkArgument(boolean shouldBeTrue) {
        if (!shouldBeTrue) throw new IllegalArgumentException();
    }
}