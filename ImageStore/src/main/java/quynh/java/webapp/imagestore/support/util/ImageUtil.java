package quynh.java.webapp.imagestore.support.util;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtil {
	public static ByteArrayOutputStream resizeImage(InputStream inputStream , int IMG_WIDTH,
	        int IMG_HEIGHT, String imageFormatName) throws Exception {
	    BufferedImage originalImage = ImageIO.read(inputStream);
	    int type = originalImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB
	            : originalImage.getType();
	    BufferedImage resizedImage = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, type);
	    {
	        Graphics2D g = resizedImage.createGraphics();
	        g.drawImage(originalImage, 0, 0, IMG_WIDTH, IMG_HEIGHT, null);
	        g.dispose();
	        g.setComposite(AlphaComposite.Src);

	        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
	                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
	        g.setRenderingHint(RenderingHints.KEY_RENDERING,
	                RenderingHints.VALUE_RENDER_QUALITY);
	        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
	                RenderingHints.VALUE_ANTIALIAS_ON);
	    }
	    ByteArrayOutputStream bos = new ByteArrayOutputStream();
	    ImageIO.write(resizedImage, imageFormatName, bos);
	    return bos;
	} 
}
