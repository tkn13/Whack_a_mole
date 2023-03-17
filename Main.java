public class Main {
    public static void main(String[] args) {
        CoreGUI a = new CoreGUI();
        a.initialize();
        while (true/*a.stopBtnBoolean*/) {
            a.playingDelayVersion();
        }
    }
}