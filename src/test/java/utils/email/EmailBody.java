package utils.email;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
public class EmailBody {
    public EmailBody() {
    }
    public  static  String filePath = "src/test/resources/test_data_files/email_body.README";
    public void enterTextToEmailBody(String text) {
        text = text+"\n";
        try {
            Files.write(Paths.get(filePath), text.getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void clearEmailBody() throws  IOException{
        File file = new File(filePath);
        PrintWriter writer = new PrintWriter(file);
        writer.print("");
        writer.close();
    }
}