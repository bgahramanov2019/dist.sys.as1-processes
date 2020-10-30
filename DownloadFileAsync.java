
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class DownloadFileAsync implements Runnable{

	private String fileName;
	private String link;
	
	public DownloadFileAsync(String fileName, String link)
	{
		this.fileName = fileName;
		this.link = link;
	}
	
	public void run()
	{
		try {
			InputStream in = new URL(link).openStream();
			Files.copy(in, Paths.get(fileName), StandardCopyOption.REPLACE_EXISTING);
			System.out.print(fileName + " -> done,");
		} catch (Exception e) {
			System.out.println("Error Downloading file : " + fileName);
		}
	}
}
