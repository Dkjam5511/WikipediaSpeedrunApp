
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private FileWriter fileWriterLog;
    private FileWriter fileWriterFinalLinks;

    Logger(){
        try {
            String desktopPath = System.getProperty("user.home") + "/Desktop";

            File log = new File(desktopPath, "Log.txt");
            File finalLinks = new File(desktopPath, "FinalLinks.txt");

            fileWriterLog = new FileWriter(log);
            fileWriterFinalLinks = new FileWriter(finalLinks);
        } catch (IOException ex){
            System.out.println("ERROR Failed to create logger");
        }
    }

    public void log(String message){
        try {
            fileWriterLog.append(message).append("\n");
        } catch (IOException ex){
            System.out.println("ERROR Failed to write to log");
        }
    }

    public void finalLinksLog(String message){
        try {
            fileWriterFinalLinks.append(message).append("\n");
        } catch (IOException ex){
            System.out.println("ERROR Failed to write to log");
        }
    }

    public void close(){
        try{
            fileWriterLog.close();
            fileWriterFinalLinks.close();
        } catch (IOException ex){
            System.out.println("ERROR Failed to close log");
        }
    }


}
