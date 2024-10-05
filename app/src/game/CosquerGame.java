package game;

public class CosquerGame {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        System.out.println(new CosquerGame().getGreeting());
    }
}
