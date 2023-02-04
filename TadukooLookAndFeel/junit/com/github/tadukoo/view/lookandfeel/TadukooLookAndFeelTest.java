package com.github.tadukoo.view.lookandfeel;

import com.github.tadukoo.view.paint.ColorPaintUIResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.awt.FontFormatException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TadukooLookAndFeelTest{
	private TadukooLookAndFeel lookAndFeel;
	
	@BeforeEach
	public void setup() throws IOException, FontFormatException{
		lookAndFeel = new TadukooLookAndFeel();
	}
	
	@Test
	public void testDefaultConstructor(){
		assertNotNull(lookAndFeel.getTheme());
	}
	
	@Test
	public void testThemeConstructor() throws IOException, FontFormatException{
		TadukooTheme theme = TadukooTheme.builder().buttonFocusPaint(new ColorPaintUIResource(Color.ORANGE)).build();
		lookAndFeel = new TadukooLookAndFeel(theme);
		assertEquals(theme, lookAndFeel.getTheme());
	}
	
	@Test
	public void testGetName(){
		assertEquals("Tadukoo", lookAndFeel.getName());
	}
	
	@Test
	public void testGetID(){
		assertEquals("Tadukoo", lookAndFeel.getID());
	}
	
	@Test
	public void testGetDescription(){
		assertEquals("The Tadukoo Look and Feel", lookAndFeel.getDescription());
	}
	
	@Test
	public void testIsNativeLookAndFeel(){
		assertFalse(lookAndFeel.isNativeLookAndFeel());
	}
	
	@Test
	public void testIsSupportedLookAndFeel(){
		assertTrue(lookAndFeel.isSupportedLookAndFeel());
	}
}
