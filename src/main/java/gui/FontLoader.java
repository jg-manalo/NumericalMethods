package gui;

import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;

public class FontLoader {
	private static boolean registered = false;
	
    public static void register(String resourcePath) {
    	if (registered) {
    		return;
    	}
    	
        try (InputStream is = FontLoader.class.getResourceAsStream(resourcePath)) {
            if (is == null) {
                throw new IOException("Font not found: " + resourcePath);
            }
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }
}
