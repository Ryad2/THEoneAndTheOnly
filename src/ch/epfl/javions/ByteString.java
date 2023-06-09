package ch.epfl.javions;

import java.util.Arrays;
import java.util.HexFormat;
import java.util.Objects;

/**
 * Représente une chaîne d'octets
 *
 * @author Ethan Boren (361582)
 * @author Ryad Aouak (315258)
 */
public final class ByteString {
    private final static HexFormat HEX_FORMAT = HexFormat.of().withUpperCase();
    private final byte[] chain;


    /**
     * Construit une chaîne d'octets dont le contenu est celui du tableau passé en argument
     *
     * @param bytes la chaîne d'octets
     */
    public ByteString(byte[] bytes) {
        this.chain = bytes.clone();
    }

    /**
     * Retourne la chaîne d'octets dont la chaîne passée en argument est la représentation
     * hexadécimale, ou, lève une exception
     * Vérifier que la longueur est paire et tester si tous les caractères sont hexadécimaux
     *
     * @param hexString la chaîne d'octet passée en argument
     * @return retourne la chaîne d'octets dont la chaîne passée en argument est la représentation
     * hexadécimale, ou, lève une exception et transforme une chaîne en byteString
     * @throws NumberFormatException La longueur de la chaine doit être paire
     * @throws NumberFormatException La chaine contient un caractère qui n'est pas hexadécimal
     */
    public static ByteString ofHexadecimalString(String hexString) {
        return new ByteString(HEX_FORMAT.parseHex(hexString));
    }

    /**
     * Calcule la taille de la chaîne
     *
     * @return la taille de la chaîne
     */
    public int size() {
        return chain.length;
    }

    /**
     * Permet de retourner l'octet à l'index donné et on utilise une conversion pour interpréter
     * l'octet comme non signé
     *
     * @param index l'index en question
     * @return l'octet à l'index donné
     * @throws IndexOutOfBoundsException si l'octet retourné est invalide
     */
    public int byteAt(int index) {
        return Byte.toUnsignedInt(chain[index]);
    }

    /**
     * Retourne un octet précis définit par fromIndex et toIndex ou lève des exceptions
     *
     * @param fromIndex l'index de départ
     * @param toIndex   l'index d'arrivée
     * @return les octets compris entre les index fromIndex (inclus) et toIndex (exclu) sous la forme
     * d'une valeur de type long, l'octet d'index toIndex - 1 constituant l'octet de poids faible du
     * résultat, ou lève
     * @throws IllegalArgumentException  si la différence entre toIndex et fromIndex n'est pas
     * strictement inférieure au nombre d'octets contenus dans une valeur de type long.
     * @throws IndexOutOfBoundsException si la plage décrite par fromIndex et toIndex n'est pas
     * totalement comprise entre 0 et la taille de la chaîne
     */
    public long bytesInRange(int fromIndex, int toIndex) {
        Objects.checkFromToIndex(fromIndex, toIndex, chain.length);
        Preconditions.checkArgument((toIndex - fromIndex < Long.BYTES));

        long result = 0;
        for (int i = fromIndex; i < toIndex; i++) {
            result = (result << Long.BYTES) + Byte.toUnsignedInt(chain[i]);
        }
        return result;
    }

    /**
     * Vérifie l'égalité de deux objets
     *
     * @param obj la chaîne d'octets passée en argument
     * @return vrai si et seulement si la valeur qu'on lui passe est aussi une instance de
     * ByteString et que ses octets sont identiques à ceux du récepteur
     */
    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByteString that && Arrays.equals(this.chain, that.chain);
    }


    /**
     * Retourne la valeur retournée par la méthode hashCode de la classe Arrays appliquées au
     * tableau contenant les octets
     *
     * @return la valeur retournée par la méthode hashCode de la classe Arrays appliquées au
     * tableau contenant les octets
     */
    @Override
    public int hashCode() {
        return Arrays.hashCode(this.chain);
    }

    /**
     * Une représentation des octets de la chaîne en hexadécimal
     *
     * @return une représentation des octets de la chaîne en hexadécimal, chaque octet occupant
     * exactement deux caractères
     */
    @Override
    public String toString() {
        return HEX_FORMAT.formatHex(chain);
    }
}