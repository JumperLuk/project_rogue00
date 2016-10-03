package eu.devy.ui;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class GraphicsCalculator 
{
	public static int getTextWidth(Graphics graphics, String text)
	{
		FontMetrics metrics = graphics.getFontMetrics();
		return metrics.stringWidth(text);
	}
	
	public static int getTextHeight(Graphics graphics)
	{
		FontMetrics metrics = graphics.getFontMetrics();
		return metrics.getHeight();
	}
}
