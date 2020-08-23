import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ThubanCmd {
	
	public static void exec(String command) {

		System.out.println("command = " + command);
	    try {
	        Process p = Runtime.getRuntime().exec(command);

	        final InputStreamReader isr
	                = new InputStreamReader(p.getErrorStream());
	        Thread th = new Thread() {
	            public void run() {
	                try {
	                    BufferedReader br = new BufferedReader(isr);
	                    String line = null;
	                    while ((line = br.readLine()) != null) {
	                        System.out.println(line);
	                    }
	                } catch (Exception ex) {
	                }
	            }
	        };
	        th.start();
	        //p.waitFor(); //이걸 써줘야 cmd명령어가 끝나야 넘어감
	    } catch (IOException ex) {
	        System.out.println("Error 1 : " + ex.getMessage());
	    } catch (Exception ex) {
	        System.out.println("Error 2 : " + ex.getMessage());
	    }
	    
	}
	
}