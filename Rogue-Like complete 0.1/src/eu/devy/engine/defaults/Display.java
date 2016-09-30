package eu.devy.engine.defaults;

import java.awt.Canvas;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import eu.devy.engine.utils.Utils;

public class Display extends Canvas
{
	private static final BufferedImage DEFAULT_CURSOR_ICON = new BufferedImage(1, 2, 3);
	private static final BufferedImage DEFAULT_WINDOW_ICON = Utils.Images.load("/textures/icon.png");
	
	private static final long serialVersionUID = 1L;
	
	private static Display instance;
	private JFrame jFrame;
	
	public Display(String title, int w, int h)
	{
		defaults(title, new Dimension(w, h));
		
		instance = this;
	}

	private void defaults(String title, Dimension dimension)
	{	
		setPreferredSize(dimension);
		setMinimumSize(dimension);
		setMaximumSize(dimension);

		jFrame = new JFrame();
		
		jFrame.setTitle(title);
		jFrame.setSize(dimension);
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setResizable(false);
		jFrame.setLocationRelativeTo(null);
		jFrame.setIconImage(DEFAULT_WINDOW_ICON);
		jFrame.setIgnoreRepaint(true);

		jFrame.setVisible(true);
		
		jFrame.add(this);
		jFrame.pack();
		
		requestFocus();
		
		setCursor(DEFAULT_CURSOR_ICON);
	}
	
	public void setCursor(BufferedImage image)
	{
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		
		Cursor c = toolkit.createCustomCursor(image , new Point(0, 0), "img");
		
		jFrame.setCursor(c);
	}
	
	public void setTitle(String title)
	{
		jFrame.setTitle(title);
	}
	
	public Canvas getCanvas()
	{
		return this;
	}
	
	public JFrame getJFrame()
	{
		return jFrame;
	}
	
	public static Display get()
	{
		return instance;
	}
}