package cool.rtz.util;

import java.io.File;
import java.io.PrintWriter;

public class FileUtils {

    private static String serverPath = System.getProperty("user.dir");
	private static final String dirpath = serverPath + File.separator + "config" + File.separator + "worldcommand";

    public static boolean writeJsonToFile(String jsonString, String fileName) {

        File dir = new File(dirpath);
		dir.mkdirs();
        
        try {
			PrintWriter writer = new PrintWriter(dirpath + File.separator + fileName + ".json", "UTF-8");
			writer.println(jsonString);
			writer.close();
		} catch (Exception e) {
			return false;
		}
		return true;
    }
}
