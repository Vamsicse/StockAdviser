package com.galaxy.StockAdviser.util;

import com.galaxy.StockAdviser.constants.MessageConstants;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Vamsi Krishna Myalapalli
 * @version 1.0
 * @since 2019-12-27
 */
public class IOUtil {

    public static String getFileContent(String filePath)
    {
        String content="";
        try {
            content = new String(Files.readAllBytes(Paths.get(filePath)));
        }
        catch (IOException e) {
            System.out.println(MessageConstants.ERROR + "Exception while reading file: " + filePath);
            e.printStackTrace();
        }
        return content;
    }

}
