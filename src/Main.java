public class Main {
    public static void main(String[] args) {
        int p = 11, q = 17;
        int phi = (p - 1) * (q - 1);
        int n = p * q;
        int e = eGenerator(phi);

        int d = privateKey(e, phi);

        int message = 2; //message muss kleiner als n sein
        int em = encrypt(message, e, n);
        System.out.println("encrypted: " + em);
        int dm = decrypt(em, d, n);
        System.out.println("Decrypted: " + dm);
    }

    public static int decrypt(int encrypted, int d, int n) {
        int decrypted = 1;
        for (int i = 0; i < d; i++) {
            decrypted = (decrypted * encrypted) % n;
        }
        return decrypted;
    }

    public static int encrypt(int message, int e, int n) {
        int encrypted = 1;
        for (int i = 0; i < e; i++) {
            encrypted = (encrypted * message) % n;
        }
        return encrypted;
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