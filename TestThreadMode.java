import java.time.Duration;
import java.time.Instant;

public class TestThreadMode {
	public static void main(String[] args) throws InterruptedException {
		
		String fileName1 = "file1";
		String fileName2 = "file2";
		String fileName3 = "file3";
		String fileName4 = "file4";
		
		String link1 = "http://www.ubicomp.org/ubicomp2003/adjunct_proceedings/proceedings.pdf";
		String link2 = "https://www.hq.nasa.gov/alsj/a17/A17_FlightPlan.pdf";
		String link3 = "https://ars.els-cdn.com/content/image/1-s2.0-S0140673617321293-mmc1.pdf";
		String link4 = "http://www.visitgreece.gr/deployedFiles/StaticFiles/maps/Peloponnese_map.pdf";
		
		Instant start = Instant.now();
		//Run in sequence
		if(args[0].equals("0"))
		{
			DownloadFileSync d1 = new DownloadFileSync(fileName1, link1);
			DownloadFileSync d2 = new DownloadFileSync(fileName2, link2);
			DownloadFileSync d3 = new DownloadFileSync(fileName3, link3);
			DownloadFileSync d4 = new DownloadFileSync(fileName4, link4);
			
			d1.run();
			d2.run();
			d3.run();
			d4.run();
		}
		
		//Run in parallel
		else if(args[0].equals("1"))
		{
			DownloadFileAsync d1 = new DownloadFileAsync(fileName1, link1);
			DownloadFileAsync d2 = new DownloadFileAsync(fileName2, link2);
			DownloadFileAsync d3 = new DownloadFileAsync(fileName3, link3);
			DownloadFileAsync d4 = new DownloadFileAsync(fileName4, link4);
			
			Thread t1 = new Thread(d1);
			Thread t2 = new Thread(d2);
			Thread t3 = new Thread(d3);
			Thread t4 = new Thread(d4);
			
			t1.start();
			t2.start();
			t3.start();
			t4.start();
			
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		}
		
		System.out.println();
		System.out.println("Time:"+ ((double)Duration.between(start, Instant.now()).toMillis())/1000 + "s");
		
	}
}	
