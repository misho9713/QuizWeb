package misc;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * The following methods are taken from HW4 - cracker problem of this course.
 */
public class Encryption {
    private static MessageDigest digest;

    static {
        try {
            digest = MessageDigest.getInstance("SHA");
        } catch (NoSuchAlgorithmException ignored) {

        }
    }

    /**
     * Given a byte[] array, produces a hex String,
     * such as "234a6f". with 2 chars for each byte in the array.
     * (provided code)
     */
    public static String hexToString(byte[] bytes) {
        StringBuilder buff = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            int val = bytes[i];
            val = val & 0xff;  // remove higher bits, sign
            if (val < 16) buff.append('0'); // leading 0
            buff.append(Integer.toString(val, 16));
        }
        return buff.toString();
    }

    /**
     * Given a string of hex byte values such as "24a26f", creates
     * a byte[] array of those values, one byte value -128..127
     * for each 2 chars.
     * (provided code)
     */
    public static byte[] hexToArray(String hex) {
        byte[] result = new byte[hex.length() / 2];
        for (int i = 0; i < hex.length(); i += 2) {
            result[i / 2] = (byte) Integer.parseInt(hex.substring(i, i + 2), 16);
        }
        return result;
    }

    /**
     * Encrypts the given string and prints its hex value
     *
     * @param s a string to be encrypted.
     */
    public static String encrypt(String s) {
        byte[] hash = digest.digest(s.getBytes());
        return hexToString(hash);
    }

    /**
     * checks the passed StringBuilder's encrypted hash value and returns whether the given hash
     * is equals to it.
     *
     * @param current a string to be checked
     * @param hash    a byte array of hash values to be compared with
     * @return whether the hash value of the string is equal to the given hash.
     */
    private static boolean equalsEncrypted(String current, byte[] hash) {

        try {
            byte[] bytes = digest.digest(current.getBytes());
            return Arrays.equals(hash, bytes);
        } catch (Exception e) {
            return false;
        }
    }
}
