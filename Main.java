public class Main {
    public static void main(String[] args) {
        CoreGUI a = new CoreGUI();
        a.initialize();
        while (a.stopBtnBoolean) {
            a.playingDelayVersion();

        }
    }
}