package com.mysample.springwas.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.UUID;

public class SpringWasIdGenerator {

    private static final char[] HEX = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};


    private static final Logger logger = LoggerFactory.getLogger(SpringWasIdGenerator.class);

    private static class Singleton {
        private static final SpringWasIdGenerator instance = new SpringWasIdGenerator();
    }

    public static SpringWasIdGenerator getInstance() {
        return Singleton.instance;
    }

    private SpringWasIdGenerator() {

    }

    /**
     * Generate 64char Random Unique ID with UUIDv4 and SHA-256
     *
     * @return
     */
    public String getRandomUniqueId() {
        String uuid = null;
        MessageDigest salt = null;
        try {
            salt = MessageDigest.getInstance("SHA-256");
            salt.update(generateType4UUID().toString().getBytes(StandardCharsets.UTF_8));
            uuid = String.copyValueOf(byteToHex(salt.digest()));
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA-256 not supported", nsae);
        }
        return uuid;
    }

    /**
     * Generate 30char Unique ID with UUIDv5 and
     *
     * @return
     */
    public String getNamespaceUniqueId(UUID_NAMESPACE namespace, String name) {
        String uuid = null;
        MessageDigest salt = null;
        try {
            salt = MessageDigest.getInstance("SHA-256");
            salt.update(generateType5UUID(namespace.getUuidWithOutHyphen(), name).toString().getBytes(StandardCharsets.UTF_8));
            uuid = String.copyValueOf(byteToHex(salt.digest()));
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA-256 not supported", nsae);
        }
        return uuid;
    }

    /**
     * Type 4 UUID Generation
     */
    public UUID generateType4UUID() {
        return UUID.randomUUID();
    }

    /**
     * Type 5 UUID Generation
     */
    public UUID generateType5UUID(String namespace, String name) {

        byte[] nameSpaceBytes = hexToByte(namespace);
        byte[] nameBytes = name.getBytes(StandardCharsets.UTF_8);
        byte[] combined = new byte[nameSpaceBytes.length + nameBytes.length];

        System.arraycopy(nameSpaceBytes, 0, combined, 0, nameSpaceBytes.length);
        System.arraycopy(nameBytes, 0, combined, nameSpaceBytes.length, nameBytes.length);

        return type5UUIDFromBytes(combined);
    }

    public UUID type5UUIDFromBytes(byte[] name) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException nsae) {
            throw new InternalError("SHA-1 not supported", nsae);
        }
        byte[] bytes = Arrays.copyOfRange(md.digest(name), 0, 16);
        bytes[6] &= 0x0f; /* clear version        */
        bytes[6] |= 0x50; /* set to version 5     */
        bytes[8] &= 0x3f; /* clear variant        */
        bytes[8] |= 0x80; /* set to IETF variant  */
        return constructType5UUID(bytes);
    }

    private UUID constructType5UUID(byte[] data) {
        long msb = 0;
        long lsb = 0;
        assert data.length == 16 : "data must be 16 bytes in length";

        for (int i = 0; i < 8; i++)
            msb = (msb << 8) | (data[i] & 0xff);

        for (int i = 8; i < 16; i++)
            lsb = (lsb << 8) | (data[i] & 0xff);
        return new UUID(msb, lsb);
    }


    /**
     * byte array to hex string
     *
     * @param bytes byte array
     * @return hex string
     */
    private char[] byteToHex(byte[] bytes) {
        final int nBytes = bytes.length;
        char[] result = new char[2 * nBytes];         //  1 hex contains two chars
        //  hex = [0-f][0-f], e.g 0f or ff
        int j = 0;
        for (byte aByte : bytes) {                    // loop byte by byte
            // 0xF0 = FFFF 0000
            result[j++] = HEX[(0xF0 & aByte) >>> 4];    // get the top 4 bits, first half hex char
            // 0x0F = 0000 FFFF
            result[j++] = HEX[(0x0F & aByte)];          // get the bottom 4 bits, second half hex char
            // combine first and second half, we get a complete hex
        }
        return result;
    }

    /**
     * hex string to byte array
     *
     * @param hex hex string
     * @return byte array
     */
    public byte[] hexToByte(CharSequence hex) {
        int nChars = hex.length();

        if (nChars % 2 != 0) {
            throw new IllegalArgumentException(
                    "Hex-encoded string must have an even number of characters");
        }

        byte[] result = new byte[nChars / 2];                                  // 1 hex = 2 char

        for (int i = 0; i < nChars; i += 2) {                                  // step 2, 1 hex = 2 char
            int msb = Character.digit(hex.charAt(i), 16);                         // char -> hex, base16
            int lsb = Character.digit(hex.charAt(i + 1), 16);

            if (msb < 0 || lsb < 0) {
                throw new IllegalArgumentException(
                        "Detected a Non-hex character at " + (i + 1) + " or " + (i + 2) + " position");
            }
            result[i / 2] = (byte) ((msb << 4) | lsb);
        }
        return result;
    }
}
