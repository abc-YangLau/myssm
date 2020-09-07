package myssm;

public class KMPTest {
    public static void main(String[] args) {
        KMP kmp = new KMP("ABABC");
        System.out.print(kmp.search("ABABABABBABABC"));
    }
}
