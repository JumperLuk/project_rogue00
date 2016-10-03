package eu.devy.engine.defaults;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener
{
	public enum KeyCodeType
	{
		CHARACTER,
		NUMERIC,
		SPECIAL,
		SPECIAL_SELECT
	};
	
	public enum KeyCode
	{
		A(65, "a", KeyCodeType.CHARACTER), B(66, "b", KeyCodeType.CHARACTER), C(67, "c", KeyCodeType.CHARACTER), D(68, "d", KeyCodeType.CHARACTER), E(69, "e", KeyCodeType.CHARACTER), 
		F(70, "f", KeyCodeType.CHARACTER), G(71, "g", KeyCodeType.CHARACTER), H(72, "h", KeyCodeType.CHARACTER), I(73, "i", KeyCodeType.CHARACTER), J(74, "j", KeyCodeType.CHARACTER), 
		K(75, "k", KeyCodeType.CHARACTER), L(76, "l", KeyCodeType.CHARACTER), M(77, "m", KeyCodeType.CHARACTER), N(78, "n", KeyCodeType.CHARACTER), O(79, "o", KeyCodeType.CHARACTER),
		P(80, "p", KeyCodeType.CHARACTER), Q(81, "q", KeyCodeType.CHARACTER), R(82, "r", KeyCodeType.CHARACTER), S(83, "s", KeyCodeType.CHARACTER), T(84, "t", KeyCodeType.CHARACTER), 
		U(85, "u", KeyCodeType.CHARACTER), V(86, "v", KeyCodeType.CHARACTER), W(87, "w", KeyCodeType.CHARACTER), X(88, "x", KeyCodeType.CHARACTER), Y(89, "y", KeyCodeType.CHARACTER), 
		Z(90, "z", KeyCodeType.CHARACTER),
		
		F1(112, "F1", KeyCodeType.SPECIAL), F2(113, "F2", KeyCodeType.SPECIAL), F3(114, "F3", KeyCodeType.SPECIAL), F4(115, "F4", KeyCodeType.SPECIAL), F5(116, "F5", KeyCodeType.SPECIAL),
		F6(117, "F6", KeyCodeType.SPECIAL), F7(118, "F7", KeyCodeType.SPECIAL), F8(119, "F8", KeyCodeType.SPECIAL), F9(120, "F9", KeyCodeType.SPECIAL), F10(121, "F10", KeyCodeType.SPECIAL),
		F11(122, "F11", KeyCodeType.SPECIAL), F12(123, "F12", KeyCodeType.SPECIAL),
		
		NUM_0(155, "0", KeyCodeType.NUMERIC), NUM_1(49, "1", KeyCodeType.NUMERIC), NUM_2(50, "2", KeyCodeType.NUMERIC), NUM_3(51, "3", KeyCodeType.NUMERIC), NUM_4(52, "4", KeyCodeType.NUMERIC), 
		NUM_5(53, "5", KeyCodeType.NUMERIC), NUM_6(54, "6", KeyCodeType.NUMERIC), NUM_7(55, "7", KeyCodeType.NUMERIC), NUM_8(56, "8", KeyCodeType.NUMERIC), NUM_9(57, "9", KeyCodeType.NUMERIC),
		KEY_0(155, "0", KeyCodeType.NUMERIC), KEY_1(49, "1", KeyCodeType.NUMERIC), KEY_2(50, "2", KeyCodeType.NUMERIC), KEY_3(51, "3", KeyCodeType.NUMERIC), KEY_4(52, "4", KeyCodeType.NUMERIC), 
		KEY_5(53, "5", KeyCodeType.NUMERIC), KEY_6(54, "6", KeyCodeType.NUMERIC), KEY_7(55, "7", KeyCodeType.NUMERIC), KEY_8(56, "8", KeyCodeType.NUMERIC), KEY_9(57, "9", KeyCodeType.NUMERIC),
		
		ARROW_LEFT(37, "Left Arrow", KeyCodeType.SPECIAL), ARROW_UP(38, "Up Arrow", KeyCodeType.SPECIAL),
		ARROW_RIGHT(39, "Right Arrow", KeyCodeType.SPECIAL), ARROW_DOWN(40, "Down Arrow", KeyCodeType.SPECIAL),
		
		SPACE(32, " ", KeyCodeType.SPECIAL), ENTER(10, "\\n", KeyCodeType.SPECIAL), BACKSLASH(8, "Backslash", KeyCodeType.SPECIAL), SHIFT(16, "Shift", KeyCodeType.SPECIAL), CAPS_LOCK(20, "Caps Lock", KeyCodeType.SPECIAL), ESCAPE(112, "Escape", KeyCodeType.SPECIAL),
		POINT(46, ".", KeyCodeType.SPECIAL_SELECT);
		
		private final int value;
		private final String text;
		private final KeyCodeType type;
		
		private KeyCode(final int value, final String text, final KeyCodeType type)
		{
			this.value = value;
			this.text = text;
			this.type = type;
		}
		
		public int value()
		{
			return value;
		}
		
		public String text()
		{
			return text;
		}
		
		public KeyCodeType getType()
		{
			return type;
		}
	}
	
	private class Key
	{
		private boolean isPressed;
		private boolean isReleased;
		private boolean isTyped;
		
		private boolean useable = true;
		
		public void setPressed(boolean isPressed)
		{
			this.isPressed = isPressed;
		}
		
		public boolean isPressed()
		{
			return isPressed;
		}
		
		public void setReleased(boolean isReleased)
		{
			this.isReleased = isReleased;
		}
		
		public boolean isReleased()
		{
			return isReleased;
		}
		
		public void setTyped(boolean isTyped)
		{
			this.isTyped = isTyped;
		}
		
		public boolean isTyped()
		{
			return isTyped;
		}
		
		public void setUseability(boolean useable)
		{
			this.useable = useable;
		}
		
		public boolean getUseability()
		{
			return useable;
		}
	}
	
	private static final int MAX_KEYS = 256;
	
	private static Key[] keys = new Key[MAX_KEYS];
	
	private static KeyCode lastKeyCode;
	
	public Keyboard()
	{
		Display.get().getJFrame().addKeyListener(this);
		Display.get().getCanvas().addKeyListener(this);
		
		for(int i = 0; i < MAX_KEYS; i++)
		{
			keys[i] = new Key();
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		int keycode = e.getKeyCode();
		
		if(keycode < MAX_KEYS)
		{
			lastKeyCode = findKeyCode(keycode);

			keys[keycode].setReleased(false);
			keys[keycode].setTyped(true);
			
			if(keys[keycode].getUseability())
			{
				keys[keycode].setPressed(true);
				keys[keycode].setUseability(false);
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		int keycode = e.getKeyCode();
		
		if(keycode < MAX_KEYS)
		{
			keys[keycode].setPressed(false);
			keys[keycode].setTyped(false);
			
			if(!keys[keycode].getUseability())
			{
				keys[keycode].setReleased(true);
				keys[keycode].setUseability(true);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		
	}
	
	public static boolean getKey(int keycode)
	{
		return(keys[keycode].isTyped());
	}
	
	public static boolean getKey(KeyCode keycode)
	{
		return getKey(keycode.value());
	}
	
	public static boolean getKeyUp(int keycode)
	{
		if(keys[keycode].isReleased())
		{
			return !(keys[keycode].isReleased = false);
		}
		return false;
	}
	
	public static boolean getKeyUp(KeyCode keycode)
	{
		return getKeyUp(keycode.value());
	}
	
	public static boolean getKeyDown(int keycode)
	{
		if(keys[keycode].isPressed())
		{
			return !(keys[keycode].isPressed = false);
		}
		return false;
	}
	
	public static boolean getKeyDown(KeyCode keycode)
	{
		return getKeyDown(keycode.value());
	}
	
	public static KeyCode getLastKeyCode()
	{
		KeyCode keycode = lastKeyCode;
		
		lastKeyCode = null;
		
		return keycode;
	}
	
	public static boolean isNumeric(KeyCode keycode)
	{
		return keycode.getType().equals(KeyCodeType.NUMERIC);
	}
	
	public static boolean isCharacter(KeyCode keycode)
	{
		return keycode.getType().equals(KeyCodeType.CHARACTER);
	}
	
	public static boolean isSpecial(KeyCode keycode)
	{
		return keycode.getType().equals(KeyCodeType.SPECIAL);
	}
	
	public static KeyCode findKeyCode(int keycode)
	{
		for(KeyCode code : KeyCode.values())
		{
			if(code.value() == keycode)
			{
				return code;
			}
		}
		return null;
	}
	
	public static boolean isAnyKeyPressed()
	{
		for(int i = 0; i < MAX_KEYS; i++)
		{
			if(keys[i].isTyped())
			{
				return true;
			}
		}
		return false;
	}
}