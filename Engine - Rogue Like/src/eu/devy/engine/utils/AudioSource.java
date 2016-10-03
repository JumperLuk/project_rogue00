package eu.devy.engine.utils;

import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class AudioSource 
{
	public static final String FILE_EXTENSION = ".wav";
	
	private FloatControl floatControl;
	private Clip clip;
	
	public AudioSource(String path)
	{
		defaults(Utils.Audio.load(path));
	}
	
	public AudioSource(Clip clip)
	{
		defaults(clip);
	}
	
	private void defaults(Clip clip)
	{
		floatControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		this.clip = clip;
	}
	
	public void play()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if(clip != null)
				{
					if(!clip.isRunning())
					{
						clip.setFramePosition(0);
						clip.start();
					}
				}
			}
		}).start();
	}
	
	public void loop()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				if(clip != null)
				{
					if(!clip.isRunning())
					{
						clip.setFramePosition(0);
						clip.loop(Clip.LOOP_CONTINUOUSLY);
					}
				}
			}
		}).start();
	}
	
	public void setVolume(float volume)
	{
		clip.getControl(FloatControl.Type.MASTER_GAIN);
		floatControl.setValue(volume);
	}
}
