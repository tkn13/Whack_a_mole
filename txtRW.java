import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.*;

public class txtRW {
    int scor = 0;
    String tx = "";

    public String read(){
        System.out.println("read");
        try {
            FileReader reader = new FileReader("leaderboard.txt");
            BufferedReader bufferedReader = new BufferedReader(reader);
 
            String line;
 
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                return line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
    public void write(String n){
        try {
            FileWriter fwOb = new FileWriter("leaderboard.txt", false); 
            PrintWriter pwOb = new PrintWriter(fwOb, false);
            pwOb.flush();
            pwOb.close();
            fwOb.close();
            FileWriter writer = new FileWriter("leaderboard.txt", true);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);
            writer.write("");
            writer.flush();
            
            bufferedWriter.write(n);
            bufferedWriter.newLine();
            
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
 
    }
}
    

