public class Main {
    public static void main(String[] args) {
        int p = 11, q = 13;
        int phi = (p - 1) * (q - 1);
        int n = p * q;
        int e = eGenerator(phi);

        int d = privateKey(e, phi);

        String encryptedMessage = encrypt("Hello World".toCharArray(), e, n);
        System.out.println("encrypted: " + encryptedMessage);
        String decryptedMessage = decrypt(encryptedMessage.toCharArray(), d, n);
        System.out.println("decrypted: " + decryptedMessage);
        System.out.println("Private Key(" + d + " " + n + ")");
        System.out.println("Public Key(" + e + " " + n + ")");
    }

    public static String encrypt(char[] message, int e, int n) {
        int encrypted;
        for (int i = 0; i < message.length; i++) {
            encrypted = 1;
            for (int j = 0; j < e; j++) {
                encrypted = (encrypted * message[i]) % n;
            }
            message[i] = (char) encrypted;
        }
        return new String(message);
    }

    public static String decrypt(char[] message, int d, int n) {
        int decrypted;

        for (int i = 0; i < message.length; i++) {
            decrypted = 1;
            for (int j = 0; j < d; j++) {
                decrypted = (decrypted * message[i]) % n;
            }
            message[i] = (char) decrypted;
        }
        return new String(message);
    }

    public static int privateKey(int e, int phi) {
        int d = 0;
        while (true) {
            if ((e * d) % phi == 1) break;
            d++;
        }
        return d;
    }

    public static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    public static int eGenerator(int phi) {
        int e = 2;
        while (e < phi) {
            if (gcd(e, phi) == 1) break;
            e++;
        }
        return e;
    }
}