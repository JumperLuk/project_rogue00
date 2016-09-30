package eu.devy.engine.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.awt.image.ImageProducer;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class Utils 
{
	public static class Images
	{
		private static GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		private static GraphicsDevice device = environment.getDefaultScreenDevice();
		private static GraphicsConfiguration config = device.getDefaultConfiguration();
		
		public static BufferedImage load(String path)
		{
			try 
			{
				return adjust(ImageIO.read(Images.class.getResource(path)));
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			return null;
		}
		
		public static BufferedImage filter(BufferedImage image, final Color color)
		{
			ImageFilter imageFilter = new RGBImageFilter()
			{
				public int markerRGB = color.getRGB();
				
				@Override
				public final int filterRGB(int x, int y, int rgb)
				{
					if((rgb | 0xFF000000) == markerRGB)
					{
						return 0x00FFFFFF & rgb;
					}
					else
					{
						return rgb;
					}
				}
			};
			
			ImageProducer imageProducer = new FilteredImageSource(image.getSource(), imageFilter);
			Image temp = Toolkit.getDefaultToolkit().createImage(imageProducer);
			
			BufferedImage bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			
			Graphics2D graphics2d = bufferedImage.createGraphics();
			graphics2d.drawImage(temp, 0, 0, null);
			graphics2d.dispose();
			
			return bufferedImage;
		}
		
		public static BufferedImage adjust(BufferedImage image)
		{
			if(image.getColorModel().equals(config.getColorModel()))
			{
				return image;
			}
			else
			{
				BufferedImage new_image = config.createCompatibleImage(image.getWidth(), image.getHeight(), image.getTransparency());
				Graphics2D g2d = (Graphics2D)new_image.getGraphics();
				g2d.drawImage(image, 0, 0, null);
				g2d.dispose();
				
				return new_image;
			}
		}
	}
	
	public static class Fonts
	{
		public static Font load(String path)
		{
			Font font = new Font("arial", Font.TRUETYPE_FONT, 14);
			
			try 
			{
				font = Font.createFont(Font.TRUETYPE_FONT, new File(path));
			} 
			catch (FontFormatException | IOException e) 
			{
				e.printStackTrace();
			}
			return font;
		}
	}
	
	public static class Audio
	{
		public static Clip load(String path)
		{
			try 
			{
				AudioInputStream input = AudioSystem.getAudioInputStream(Audio.class.getResource(path));
				AudioFormat format = input.getFormat();
				AudioFormat decode = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(), 16, format.getChannels(), format.getChannels() * 2, format.getSampleRate(), false);
			
				AudioInputStream decode_input = AudioSystem.getAudioInputStream(decode, input);
				
				Clip clip = AudioSystem.getClip();
				clip.open(decode_input);
				return clip;
			}
			catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) 
			{
				e.printStackTrace();
			}
			return null;
		}
	}
}