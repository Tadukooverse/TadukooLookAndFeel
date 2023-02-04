package com.github.tadukoo.view.lookandfeel;

import com.github.tadukoo.view.constants.TitlePosition;
import com.github.tadukoo.view.font.FontFamilies;
import com.github.tadukoo.view.font.FontFamily;
import com.github.tadukoo.view.font.FontResourceLoader;
import com.github.tadukoo.view.lookandfeel.componentui.TadukooButtonUI;
import com.github.tadukoo.view.lookandfeel.componentui.TadukooLabelUI;
import com.github.tadukoo.view.paint.ColorPaintUIResource;
import com.github.tadukoo.view.paint.NoPaintUIResource;
import com.github.tadukoo.view.paint.PaintUIResource;
import com.github.tadukoo.view.shapes.ShapeInfoUIResource;
import com.github.tadukoo.view.shapes.Shapes;
import org.junit.jupiter.api.Test;

import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.LabelUI;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.metal.MetalLabelUI;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Locale;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TadukooThemeFactoryTest{
	// Dummy Graphics Environment used for Font Resource Loading
	private final GraphicsEnvironment dummyGraphEnv = new GraphicsEnvironment(){
		@Override
		public GraphicsDevice[] getScreenDevices() throws HeadlessException{
			return new GraphicsDevice[0];
		}
		
		@Override
		public GraphicsDevice getDefaultScreenDevice() throws HeadlessException{
			return null;
		}
		
		@Override
		public Graphics2D createGraphics(BufferedImage img){
			return null;
		}
		
		@Override
		public Font[] getAllFonts(){
			return new Font[0];
		}
		
		@Override
		public String[] getAvailableFontFamilyNames(){
			return new String[]{Font.DIALOG, Font.DIALOG_INPUT, Font.MONOSPACED, Font.SANS_SERIF, Font.SERIF,
					FontFamilies.CARLITO.getFamily().getName()};
		}
		
		@Override
		public String[] getAvailableFontFamilyNames(Locale l){
			return new String[0];
		}
	};
	// Font Resource Loader to be used to avoid errors
	private final FontResourceLoader fontResourceLoader = new FontResourceLoader(false, null,
			dummyGraphEnv, "fonts/");
	
	/*
	 * The Default Theme Settings (used in multiple tests/methods below)
	 */
	private final PaintUIResource defaultForegroundPaint = new ColorPaintUIResource(Color.BLACK);
	private final PaintUIResource defaultBackgroundPaint = new ColorPaintUIResource(Color.GREEN);
	private final PaintUIResource defaultLabelBackgroundPaint = new NoPaintUIResource();
	private final PaintUIResource defaultFocusPaint = new ColorPaintUIResource(Color.GRAY);
	private final PaintUIResource defaultSelectPaint = new ColorPaintUIResource(0, 200, 0);
	private final PaintUIResource defaultDisabledTextPaint = new ColorPaintUIResource(Color.GRAY);
	private final PaintUIResource defaultDisabledForegroundPaint = new ColorPaintUIResource(Color.GRAY);
	private final FontFamily defaultFontFamily = FontFamilies.CARLITO.getFamily();
	private final int defaultFontStyle = Font.PLAIN;
	private final int defaultFontSize = 14;
	private final ShapeInfoUIResource defaultShapeInfo =
			new ShapeInfoUIResource(Shapes.RECTANGLE_WITH_CUT_CORNERS_TR_BL.getShapeInfo());
	//private final BorderUIResource defaultBorder = new BorderUIResource(ShapedLineBorder.builder().build());
	private final ColorUIResource defaultTitledBorderColor = new ColorUIResource(Color.BLACK);
	private final TitlePosition defaultTitledBorderPosition = TitlePosition.TOP;
	
	private void verifyTheme(TadukooTheme theme,
	                         Class<? extends ButtonUI> buttonUIClass, Class<? extends LabelUI> labelUIClass,
	                         PaintUIResource buttonForegroundPaint, PaintUIResource buttonBackgroundPaint,
	                         PaintUIResource buttonFocusPaint, PaintUIResource buttonSelectPaint,
	                         PaintUIResource buttonDisabledTextPaint,
	                         FontFamily buttonFontFamily, int buttonFontStyle, int buttonFontSize,
	                         ShapeInfoUIResource buttonShapeInfo,
	                         PaintUIResource labelForegroundPaint, PaintUIResource labelBackgroundPaint,
	                         PaintUIResource labelDisabledForegroundPaint,
	                         FontFamily labelFontFamily, int labelFontStyle, int labelFontSize,
	                         ShapeInfoUIResource labelShapeInfo,
	                         FontFamily titledBorderFontFamily, int titledBorderFontStyle, int titledBorderFontSize,
	                         ColorUIResource titledBorderColor, TitlePosition titledBorderPosition){
		// Check Component UI Classes
		assertEquals(buttonUIClass.getCanonicalName(), theme.getButtonUI());
		assertEquals(labelUIClass.getCanonicalName(), theme.getLabelUI());
		
		// Check Button Settings
		assertEquals(buttonForegroundPaint, theme.getButtonForegroundPaint());
		assertEquals(buttonBackgroundPaint, theme.getButtonBackgroundPaint());
		assertEquals(buttonFocusPaint, theme.getButtonFocusPaint());
		assertEquals(buttonSelectPaint, theme.getButtonSelectPaint());
		assertEquals(buttonDisabledTextPaint, theme.getButtonDisabledTextPaint());
		FontUIResource buttonFont = theme.getButtonFont();
		assertEquals(buttonFontFamily.getName(), buttonFont.getName());
		assertEquals(buttonFontStyle, buttonFont.getStyle());
		assertEquals(buttonFontSize, buttonFont.getSize());
		assertEquals(buttonShapeInfo, theme.getButtonShapeInfo());
		assertNotNull(theme.getButtonBorder());
		
		// Check Label Settings
		assertEquals(labelForegroundPaint, theme.getLabelForegroundPaint());
		assertEquals(labelBackgroundPaint, theme.getLabelBackgroundPaint());
		assertEquals(labelDisabledForegroundPaint, theme.getLabelDisabledForegroundPaint());
		FontUIResource labelFont = theme.getLabelFont();
		assertEquals(labelFontFamily.getName(), labelFont.getName());
		assertEquals(labelFontStyle, labelFont.getStyle());
		assertEquals(labelFontSize, labelFont.getSize());
		assertEquals(labelShapeInfo, theme.getLabelShapeInfo());
		assertNotNull(theme.getLabelBorder());
		
		// Check Titled Border Settings
		assertNotNull(theme.getTitledBorderBorder());
		FontUIResource titledBorderFont = theme.getTitledBorderFont();
		assertEquals(titledBorderFontFamily.getName(), titledBorderFont.getName());
		assertEquals(titledBorderFontStyle, titledBorderFont.getStyle());
		assertEquals(titledBorderFontSize, titledBorderFont.getSize());
		assertEquals(titledBorderColor, theme.getTitledBorderColor());
		assertEquals(titledBorderPosition.getValue(), theme.getTitledBorderPosition());
	}
	
	/**
	 * Method used to verify that the settings on the given {@link TadukooTheme} match those of the Default Tadukoo
	 * Theme. This is used in multiple tests, and is easier to maintain here instead of maintaining the code in
	 * multiple places.
	 *
	 * @param defaultTheme The {@link TadukooTheme} that should contain the Default Tadukoo Theme settings
	 */
	private void verifyDefaultTheme(TadukooTheme defaultTheme){
		verifyTheme(defaultTheme, TadukooButtonUI.class, TadukooLabelUI.class,
				// Default Button Settings
				defaultForegroundPaint, defaultBackgroundPaint,
				defaultFocusPaint, defaultSelectPaint,
				defaultDisabledTextPaint,
				defaultFontFamily, defaultFontStyle, defaultFontSize,
				defaultShapeInfo,
				// Default Label Settings
				defaultForegroundPaint, defaultLabelBackgroundPaint,
				defaultDisabledForegroundPaint,
				defaultFontFamily, defaultFontStyle, defaultFontSize,
				defaultShapeInfo,
				// Default Titled Border Settings
				defaultFontFamily, defaultFontStyle, defaultFontSize,
				defaultTitledBorderColor, defaultTitledBorderPosition);
	}
	
	@Test
	public void testDefaultThemeBuilder() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme defaultTheme = TadukooThemeFactory.defaultThemeBuilder()
				.fontResourceLoader(fontResourceLoader).build();
		
		// Verify that the settings are right
		verifyDefaultTheme(defaultTheme);
	}
	
	@Test
	public void testCreateDefaultTheme() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme defaultTheme = TadukooThemeFactory.createDefaultTheme();
		
		// Verify that the settings are right
		verifyDefaultTheme(defaultTheme);
	}
	
	/**
	 * Method used to verify that the settings on the given {@link TadukooTheme} match those of the Metal Tadukoo
	 * Theme. This is used in multiple tests, and is easier to maintain here instead of maintaining the code in
	 * multiple places.
	 *
	 * @param metalTheme The {@link TadukooTheme} that should contain the Metal Tadukoo Theme settings
	 */
	private void verifyMetalTheme(TadukooTheme metalTheme){
		verifyTheme(metalTheme, MetalButtonUI.class, MetalLabelUI.class,
				// Button Settings
				defaultForegroundPaint, defaultBackgroundPaint,
				defaultFocusPaint, defaultSelectPaint,
				defaultDisabledTextPaint,
				defaultFontFamily, defaultFontStyle, defaultFontSize,
				defaultShapeInfo,
				// Label Settings
				defaultForegroundPaint, defaultLabelBackgroundPaint,
				defaultDisabledForegroundPaint,
				defaultFontFamily, defaultFontStyle, defaultFontSize,
				defaultShapeInfo,
				// Titled Border Settings
				defaultFontFamily, defaultFontStyle, defaultFontSize,
				defaultTitledBorderColor, defaultTitledBorderPosition);
	}
	
	@Test
	public void testMetalThemeBuilder() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme metalTheme = TadukooThemeFactory.metalThemeBuilder()
				.fontResourceLoader(fontResourceLoader).build();
		
		// Verify that the settings are right
		verifyMetalTheme(metalTheme);
	}
	
	@Test
	public void testCreateMetalTheme() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme metalTheme = TadukooThemeFactory.createMetalTheme();
		
		// Verify that the settings are right
		verifyMetalTheme(metalTheme);
	}
	
	/**
	 * Method used to verify that the contents of the given {@link MetalTheme} have been copied over to the given
	 * {@link TadukooTheme} correctly. This is done multiple times in these tests, and to have the logic present
	 * in all the tests and maintain it over time would be ridiculous and prone to error or to be outdated.
	 *
	 * @param metalTheme The {@link MetalTheme} that should have had its contents copied
	 * @param theme The {@link TadukooTheme} which should contain the contents from the Metal Theme
	 */
	private void verifyMetalTheme(MetalTheme metalTheme, TadukooTheme theme, Class<? extends ButtonUI> buttonUIClass,
	                              Class<? extends LabelUI> labelUIClass,
	                              ShapeInfoUIResource buttonShapeInfo, ShapeInfoUIResource labelShapeInfo,
	                              TitlePosition titledBorderPosition){
		// Grab Paints
		PaintUIResource controlPaint = new ColorPaintUIResource(metalTheme.getControl());
		PaintUIResource controlTextPaint = new ColorPaintUIResource(metalTheme.getControlTextColor());
		PaintUIResource controlShadowPaint = new ColorPaintUIResource(metalTheme.getControlShadow());
		PaintUIResource focusPaint = new ColorPaintUIResource(metalTheme.getFocusColor());
		PaintUIResource inactiveControlTextPaint = new ColorPaintUIResource(metalTheme.getInactiveControlTextColor());
		PaintUIResource inactiveSystemTextPaint = new ColorPaintUIResource(metalTheme.getInactiveSystemTextColor());
		PaintUIResource systemTextPaint = new ColorPaintUIResource(metalTheme.getSystemTextColor());
		
		// Grab Fonts
		FontUIResource controlTextFont = metalTheme.getControlTextFont();
		FontFamily controlTextFontFamily = Objects.requireNonNull(FontFamilies.fromName(controlTextFont.getName())).
				getFamily();
		int controlTextFontStyle = controlTextFont.getStyle();
		int controlTextFontSize = controlTextFont.getSize();
		
		// Verify the settings
		verifyTheme(theme,
				buttonUIClass, labelUIClass,
				// Button foreground + background paints
				controlTextPaint, controlPaint,
				// Button focus + select paints
				focusPaint, controlShadowPaint,
				// Button disabled text paint
				inactiveControlTextPaint,
				// Button font
				controlTextFontFamily, controlTextFontStyle, controlTextFontSize,
				// Button shape
				buttonShapeInfo,
				// Label foreground + background paints
				systemTextPaint, controlPaint,
				// Label disabled foreground paint
				inactiveSystemTextPaint,
				// Label font
				controlTextFontFamily, controlTextFontStyle, controlTextFontSize,
				// Label shape
				labelShapeInfo,
				// Titled Border font
				controlTextFontFamily, controlTextFontStyle, controlTextFontSize,
				// Titled Border color + position
				metalTheme.getSystemTextColor(), titledBorderPosition);
	}
	
	private void verifyMetalTheme(MetalTheme metalTheme, TadukooTheme theme){
		verifyMetalTheme(metalTheme, theme, MetalButtonUI.class, MetalLabelUI.class,
				defaultShapeInfo, defaultShapeInfo,
				defaultTitledBorderPosition);
	}
	
	@Test
	public void testDefaultMetalThemeBuilder() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme defaultMetalTheme = TadukooThemeFactory.defaultMetalThemeBuilder()
				.fontResourceLoader(fontResourceLoader).build();
		
		// Verify that the settings are right
		verifyMetalTheme(new DefaultMetalTheme(), defaultMetalTheme);
	}
	
	@Test
	public void testCreateDefaultMetalTheme() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme defaultMetalTheme = TadukooThemeFactory.createDefaultMetalTheme();
		
		// Verify that the settings are right
		verifyMetalTheme(new DefaultMetalTheme(), defaultMetalTheme);
	}
	
	@Test
	public void testOceanThemeBuilder() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme oceanTheme = TadukooThemeFactory.oceanThemeBuilder()
				.fontResourceLoader(fontResourceLoader).build();
		
		// Verify that the settings are right
		verifyMetalTheme(new OceanTheme(), oceanTheme);
	}
	
	@Test
	public void testCreateOceanTheme() throws IOException, FontFormatException{
		// Create Theme
		TadukooTheme oceanTheme = TadukooThemeFactory.createOceanTheme();
		
		// Verify that the settings are right
		verifyMetalTheme(new OceanTheme(), oceanTheme);
	}
	
	@Test
	public void testCopyMetalTheme() throws IOException, FontFormatException{
		// Setup a Metal Theme
		MetalTheme metalTheme = new MetalTheme(){
			@Override
			public String getName(){
				return null;
			}
			
			@Override
			protected ColorUIResource getPrimary1(){
				return new ColorUIResource(Color.RED);
			}
			
			@Override
			protected ColorUIResource getPrimary2(){
				return new ColorUIResource(Color.WHITE);
			}
			
			@Override
			protected ColorUIResource getPrimary3(){
				return new ColorUIResource(Color.BLUE);
			}
			
			@Override
			protected ColorUIResource getSecondary1(){
				return new ColorUIResource(Color.BLACK);
			}
			
			@Override
			protected ColorUIResource getSecondary2(){
				return new ColorUIResource(Color.YELLOW);
			}
			
			@Override
			protected ColorUIResource getSecondary3(){
				return new ColorUIResource(Color.PINK);
			}
			
			@Override
			public FontUIResource getControlTextFont(){
				return new FontUIResource(Font.DIALOG, Font.PLAIN, 12);
			}
			
			@Override
			public FontUIResource getSystemTextFont(){
				return new FontUIResource(Font.DIALOG_INPUT, Font.BOLD, 13);
			}
			
			@Override
			public FontUIResource getUserTextFont(){
				return new FontUIResource(Font.MONOSPACED, Font.ITALIC, 15);
			}
			
			@Override
			public FontUIResource getMenuTextFont(){
				return new FontUIResource(Font.SANS_SERIF, Font.BOLD, 29);
			}
			
			@Override
			public FontUIResource getWindowTitleFont(){
				return new FontUIResource(Font.SERIF, Font.ITALIC, 36);
			}
			
			@Override
			public FontUIResource getSubTextFont(){
				return new FontUIResource(Font.DIALOG_INPUT, Font.PLAIN, 24);
			}
		};
		
		// Copy the metal theme and build the Tadukoo Theme
		TadukooTheme.TadukooThemeBuilder builder = TadukooTheme.builder();
		TadukooThemeFactory.copyMetalTheme(builder, metalTheme);
		TadukooTheme theme = builder.fontResourceLoader(fontResourceLoader).build();
		
		// Verify the Metal Theme
		verifyMetalTheme(metalTheme, theme, TadukooButtonUI.class, TadukooLabelUI.class,
				defaultShapeInfo, defaultShapeInfo,
				defaultTitledBorderPosition);
	}
}
