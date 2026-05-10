import java.util.Base64;

public class Test {
    public static void main(String[] args) {
        String a="Danie";
        String b="43210";
        String c="100";

        System.out.println(Base64.getEncoder().encodeToString(a.getBytes()));
        System.out.println(Base64.getEncoder().encodeToString(b.getBytes()));
        System.out.println(Base64.getEncoder().encodeToString(c.getBytes()));

    }
}

