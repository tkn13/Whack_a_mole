public class Main {
    public static void main(String[] args) {
        GameCore a = new GameCore();
        a.initialize();
        while(true){
            System.out.println("Stop");
            while (a.getGameRunable()) {
                System.out.println("Play");
                a.playing();
            }
        }
    }
        }
        