package com.github.tadukoo.view.lookandfeel;

import com.github.tadukoo.view.border.NoBorderUIResource;
import com.github.tadukoo.view.paint.ColorPaintUIResource;
import com.github.tadukoo.view.font.FontFamilies;
import com.github.tadukoo.view.font.FontFamily;

import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.metal.DefaultMetalTheme;
import javax.swing.plaf.metal.MetalBorders;
import javax.swing.plaf.metal.MetalButtonUI;
import javax.swing.plaf.metal.MetalLabelUI;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.MetalTheme;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import java.util.Objects;

/**
 * Tadukoo Theme Factory provides some standard {@link TadukooTheme}s and
 * {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilders}, along with some util methods for making your own
 * {@link TadukooTheme}.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3
 * @since Alpha v.0.2
 */
public class TadukooThemeFactory{
	
	/** Not allowed to build a Tadukoo Theme Factory */
	private TadukooThemeFactory(){ }
	
	/*
	 * Themes and Theme Builders
	 */
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that has the default customizations
	 */
	public static TadukooTheme.TadukooThemeBuilder defaultThemeBuilder(){
		return TadukooTheme.builder();
	}
	
	/**
	 * @return A {@link TadukooTheme} that has the default customizations
	 * @throws IOException If something goes wrong in loading fonts
	 * @throws FontFormatException If something goes wrong in loading fonts
	 */
	public static TadukooTheme createDefaultTheme() throws IOException, FontFormatException{
		return defaultThemeBuilder().build();
	}
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that uses the
	 * {@link MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders, but uses the
	 * regular {@link TadukooLookAndFeel} colors and fonts.
	 */
	public static TadukooTheme.TadukooThemeBuilder metalThemeBuilder(){
		return TadukooTheme.builder()
				.buttonUI(MetalButtonUI.class).labelUI(MetalLabelUI.class)
				.buttonBorder(new BorderUIResource(MetalBorders.getButtonBorder()))
				.labelBorder(new NoBorderUIResource());
	}
	
	/**
	 * @return A {@link TadukooTheme} that uses the
	 * {@link MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders, but uses the
	 * regular {@link TadukooLookAndFeel} colors and fonts.
	 * @throws IOException If something goes wrong in loading fonts
	 * @throws FontFormatException If something goes wrong in loading fonts
	 */
	public static TadukooTheme createMetalTheme() throws IOException, FontFormatException{
		return metalThemeBuilder().build();
	}
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that uses the
	 * {@link MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link DefaultMetalTheme} for colors and fonts.
	 */
	public static TadukooTheme.TadukooThemeBuilder defaultMetalThemeBuilder(){
		return copyMetalTheme(metalThemeBuilder(), new DefaultMetalTheme());
	}
	
	/**
	 * @return A {@link TadukooTheme} that uses the
	 * {@link MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link DefaultMetalTheme} for colors and fonts.
	 * @throws IOException If something goes wrong in loading fonts
	 * @throws FontFormatException If something goes wrong in loading fonts
	 */
	public static TadukooTheme createDefaultMetalTheme() throws IOException, FontFormatException{
		return defaultMetalThemeBuilder().build();
	}
	
	/**
	 * @return A {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} that uses the
	 * {@link MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link OceanTheme} for colors and fonts.
	 */
	public static TadukooTheme.TadukooThemeBuilder oceanThemeBuilder(){
		return copyMetalTheme(metalThemeBuilder(), new OceanTheme());
	}
	
	/**
	 * @return A {@link TadukooTheme} that uses the
	 * {@link MetalLookAndFeel MetalLookAndFeel} Component UIs and Borders and uses the
	 * {@link OceanTheme} for colors and fonts.
	 * @throws IOException If something goes wrong in loading fonts
	 * @throws FontFormatException If something goes wrong in loading fonts
	 */
	public static TadukooTheme createOceanTheme() throws IOException, FontFormatException{
		return oceanThemeBuilder().build();
	}
	
	/*
	 * Util Functions
	 */
	
	/**
	 * Copies the colors and fonts from the given {@link MetalTheme} to the given
	 * {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} and returns the TadukooThemeBuilder to easily
	 * continue building the {@link TadukooTheme}.
	 *
	 * @param themeBuilder The {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder} to add parameters to
	 * @param metalTheme The {@link MetalTheme} to grab colors and fonts from
	 * @return The {@link TadukooTheme.TadukooThemeBuilder TadukooThemeBuilder}, to continue building a theme
	 */
	public static TadukooTheme.TadukooThemeBuilder copyMetalTheme(TadukooTheme.TadukooThemeBuilder themeBuilder,
	                                                              MetalTheme metalTheme){
		// Grab colors
		ColorPaintUIResource controlPaint = new ColorPaintUIResource(metalTheme.getControl());
		ColorPaintUIResource controlShadowPaint = new ColorPaintUIResource(metalTheme.getControlShadow());
		ColorPaintUIResource controlTextPaint = new ColorPaintUIResource(metalTheme.getControlTextColor());
		ColorPaintUIResource focusPaint = new ColorPaintUIResource(metalTheme.getFocusColor());
		ColorPaintUIResource inactiveControlTextPaint =
				new ColorPaintUIResource(metalTheme.getInactiveControlTextColor());
		ColorPaintUIResource inactiveSystemTextPaint =
				new ColorPaintUIResource(metalTheme.getInactiveSystemTextColor());
		ColorPaintUIResource systemTextPaint = new ColorPaintUIResource(metalTheme.getSystemTextColor());
		
		// Sort out fonts
		Font controlTextFont = metalTheme.getControlTextFont();
		FontFamily controlTextFontFamily = Objects.requireNonNull(
				FontFamilies.fromName(controlTextFont.getName())).getFamily();
		int controlTextFontStyle = controlTextFont.getStyle();
		int controlTextFontSize = controlTextFont.getSize();
		
		return themeBuilder
				// Button Settings
				.buttonForegroundPaint(controlTextPaint).buttonBackgroundPaint(controlPaint)
				.buttonSelectPaint(controlShadowPaint).buttonFocusPaint(focusPaint)
				.buttonDisabledTextPaint(inactiveControlTextPaint)
				.buttonFont(controlTextFontFamily, controlTextFontStyle, controlTextFontSize)
				// Label Settings
				.labelForegroundPaint(systemTextPaint).labelBackgroundPaint(controlPaint)
				.labelDisabledForegroundPaint(inactiveSystemTextPaint)
				.labelFont(controlTextFontFamily, controlTextFontStyle, controlTextFontSize)
				// Titled Border Settings
				.titledBorderBorder(new BorderUIResource(
						new BorderUIResource.LineBorderUIResource(metalTheme.getControlShadow())))
				.titledBorderFont(controlTextFontFamily, controlTextFontStyle, controlTextFontSize)
				.titledBorderColor(metalTheme.getSystemTextColor());
	}
}
