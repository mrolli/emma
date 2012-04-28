package ch.rollis.emma;

public class EmmaRunner {
    public static void main(String[] args) {
        HttpServer emma;

        try {
            emma = new HttpServer(8080);
            emma.start();
        } catch (HttpServerException e) {
            e.printStackTrace();
        }
    }
}
