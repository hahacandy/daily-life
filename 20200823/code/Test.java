import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Test {
	
	public static void main(String[] args) {	
       
		//사용자 지정
		String converterPath = "D:\\게임\\다크에덴\\Toolss\\TreasureList Converter v1.0 - OpenDarkEden\\v2\\TreasureList Converter.exe";
		
		String binPath = "C:\\Users\\Desk\\Desktop\\bin원본\\";
		

		//changeJsDropRate(converterPath, binPath);
		//binToJs(converterPath, binPath);
		jsTobin(converterPath, binPath);
		


		
        

	}
	
	static void jsTobin(String converterPath, String binPath) {
		String fileName = null;
		String race = null;
		String cmd = null;
		
		for (int j = 1; j <= 3; j++) {

			if (j == 1) {
				race = "vampire";
			} else if (j == 2) {
				race = "ousters";
			} else if (j == 3) {
				race = "slayer";
			}
			
			for (int i = 1; i <= 14; i++) {

				fileName = binPath + "Class" + i + "." + race + ".bin.js";
				
				cmd = "\"" + converterPath + "\" \"" + fileName + "\"";
				
				ThubanCmd.exec(cmd);
			}
		}
	}
	
	static void changeJsDropRate(String converterPath, String binPath) {

		String fileName = null;
		String race = null;

		boolean isOption = false;

		int ratio = 0;

		int multiple = 0;

		FileReader rw = null;
		BufferedReader br = null;

		List<String> change = null;

		String readLine = null;

		File deleteFile = null;

		FileWriter fw = null;
		BufferedWriter bw = null;

		for (int j = 1; j <= 3; j++) {

			if (j == 1) {
				race = "vampire";
			} else if (j == 2) {
				race = "ousters";
			} else if (j == 3) {
				race = "slayer";
			}

			for (int i = 1; i <= 14; i++) {

				fileName = binPath + "Class" + i + "." + race + ".bin.js";

				System.out.println("start" + i);

				try {

					rw = new FileReader(fileName);
					br = new BufferedReader(rw);

					change = new ArrayList<String>();

					while ((readLine = br.readLine()) != null) {

						if (readLine.contains("\"OptionType\"")) {
							isOption = true;
						} else if (isOption) {

							if (readLine.contains("\"Ratio\"")) {
								ratio = Integer.valueOf(readLine.replaceAll("[^0-9]", ""));

								if (ratio <= 10) {
									multiple = 10;
								} else if (ratio <= 100) {
									multiple = 5;
								} else if (ratio <= 500) {
									multiple = 3;
								} else if (ratio <= 1000) {
									multiple = 2;
								} else {
									multiple = 1;
								}

								readLine = "\"Ratio\": " + ratio * multiple;

								//System.out.println(ratio + " -> " + ratio * multiple);
							}

							isOption = false;
						}

						change.add(readLine);

					}
					
					rw.close();
					br.close();

					
					deleteFile = new File(fileName);
					if(deleteFile.exists()) {
						if(deleteFile.delete()) {
							System.out.println(fileName + "파일 삭제");
						}else {
							System.out.println(fileName + "파일 삭제 실패 1");
						}
					}else {
						System.out.println(fileName + "파일 삭제 실패 2");
					}
					

					fw = new FileWriter(fileName, true);
					bw = new BufferedWriter(fw);

					for (String temp : change) {

						bw.write(temp);
						bw.newLine();
						bw.flush();

					}

					System.out.println("end" + i);

				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("error" + i);
				}

			}

		}

	}
	
	static void binToJs(String converterPath, String binPath) {

		String binName = null;
		String race = null;
		String cmd = null;

		for(int j=1;  j<=3; j++) {
			
			if(j==1) {
				race = "vampire";
			}else if(j==2) {
				race = "ousters";
			}else if(j==3) {
				race = "slayer";
			}
			
			for (int i = 1; i <= 14; i++) {

				binName = binPath + "Class" + i + "." + race + ".bin";

				try {
					cmd = "\"" + converterPath + "\" \"" + binName + "\"";

					ThubanCmd.exec(cmd);

				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			
		}
		
	}
	
}
