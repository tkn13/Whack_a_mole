public class Main {
    public static void main(String[] args) {
        CoreGUI a = new CoreGUI();
        // txtRW t = new txtRW();
        // System.out.println(t.read());
        a.initialize();
        while(true){
            while (a.getGameRunable()) {
                a.playingDelayVersion();
            }
        }
    }
        }
        