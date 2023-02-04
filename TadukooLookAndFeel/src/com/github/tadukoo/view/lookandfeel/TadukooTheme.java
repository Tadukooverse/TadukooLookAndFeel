package com.github.tadukoo.view.lookandfeel;

import com.github.tadukoo.util.ListUtil;
import com.github.tadukoo.util.logger.EasyLogger;
import com.github.tadukoo.view.border.NoBorderUIResource;
import com.github.tadukoo.view.constants.TitlePosition;
import com.github.tadukoo.view.lookandfeel.componentui.TadukooButtonUI;
import com.github.tadukoo.view.lookandfeel.componentui.TadukooLabelUI;
import com.github.tadukoo.view.paint.ColorPaintUIResource;
import com.github.tadukoo.view.paint.NoPaintUIResource;
import com.github.tadukoo.view.paint.PaintUIResource;
import com.github.tadukoo.view.border.ShapedLineBorder;
import com.github.tadukoo.view.font.FontFamilies;
import com.github.tadukoo.view.font.FontFamily;
import com.github.tadukoo.view.font.FontResourceLoader;
import com.github.tadukoo.view.shapes.ShapeInfoUIResource;
import com.github.tadukoo.view.shapes.Shapes;

import javax.swing.border.Border;
import javax.swing.plaf.BorderUIResource;
import javax.swing.plaf.ButtonUI;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.FontUIResource;
import javax.swing.plaf.LabelUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Theme class for {@link TadukooLookAndFeel}. You can use the {@link TadukooThemeBuilder builder} via the
 * {@link #builder()} method to construct it and specify whatever customizations you want.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.3
 * @since Alpha v.0.2
 */
public class TadukooTheme{
	
	/**
	 * Builder for {@link TadukooTheme}. There are no required fields - all of them will be
	 * defaulted based on the default Tadukoo Theme. The following fields may be specified:
	 * <br><br>
	 *
	 * <table>
	 *     <caption><b>Component UI Classes</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonUI</td>
	 *         <td>The {@link ButtonUI} class to use</td>
	 *         <td>TadukooButtonUI.class</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelUI</td>
	 *         <td>The {@link LabelUI} class to use</td>
	 *         <td>TadukooLabelUI.class</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Paint Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultForegroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified foreground paints</td>
	 *         <td>new ColorPaintUIResource(Color.BLACK)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonForegroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for the foreground on Buttons</td>
	 *         <td>null (defaults to the {@code defaultForegroundPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelForegroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for the foreground on Labels</td>
	 *         <td>null (defaults to the {@code defaultForegroundPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultBackgroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified background paints</td>
	 *         <td>new ColorPaintUIResource(Color.WHITE)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonBackgroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for the background on Buttons</td>
	 *         <td>null (defaults to the {@code defaultBackgroundPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelBackgroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for the background on Labels</td>
	 *         <td>new {@link NoPaintUIResource} (can set to null to use the {@code defaultBackgroundPaint} value
	 *         if you want to)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFocusPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified focus paints</td>
	 *         <td>new ColorPaintUIResource(Color.YELLOW)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFocusPaint</td>
	 *         <td>The {@link PaintUIResource} to use for focus on Buttons</td>
	 *         <td>null (defaults to the {@code defaultFocusPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultSelectPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified select paints</td>
	 *         <td>new ColorPaintUIResource(Color.RED)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonSelectPaint</td>
	 *         <td>The {@link PaintUIResource} to use for select on Buttons</td>
	 *         <td>null (defaults to the {@code defaultSelectPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultDisabledTextPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified disabled text paints</td>
	 *         <td>new ColorPaintUIResource(Color.GRAY)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonDisabledTextPaint</td>
	 *         <td>The {@link PaintUIResource} to use for disabled text on Buttons</td>
	 *         <td>null (defaults to the {@code defaultDisabledTextPaint} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultDisabledForegroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for all unspecified disabled foreground paints</td>
	 *         <td>new ColorPaintUIResource(Color.GRAY)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelDisabledForegroundPaint</td>
	 *         <td>The {@link PaintUIResource} to use for disabled foreground on Labels</td>
	 *         <td>null (defaults to the {@code defaultDisabledForegroundPaint} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Font Parameters
	 *     <br>Specified as a {@link FontFamily}, font style, and font size</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultFont</td>
	 *         <td>The font to use for unspecified font</td>
	 *         <td>{@link FontFamilies#CALIBRI}, style {@link Font#PLAIN}, and size 12</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonFont</td>
	 *         <td>The font to use for Buttons</td>
	 *         <td>null (defaults to the {@code defaultFont} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelFont</td>
	 *         <td>The font to use for Labels</td>
	 *         <td>null (defaults to the {@code defaultFont} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Font Resource Loading Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *        <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>logFontResourceLoaderWarnings</td>
	 *         <td>Whether to log warnings generated by the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>false</td>
	 *     </tr>
	 *     <tr>
	 *         <td>logger</td>
	 *         <td>An {@link EasyLogger} that will be sent to the FontResourceLoader by default
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>null (since logging warnings is set to false by default)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>graphEnv</td>
	 *         <td>The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>{@link GraphicsEnvironment#getLocalGraphicsEnvironment()}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>fontFolder</td>
	 *         <td>The path to the fonts folder to find font files in if needed in the FontResourceLoader
	 *         - can be ignored if you specify your own FontResourceLoader</td>
	 *         <td>"fonts/"</td>
	 *     </tr>
	 *     <tr>
	 *         <td>fontResourceLoader</td>
	 *        <td>The {@link FontResourceLoader} to use in loading fonts and/or ensuring they're in the system</td>
	 *        <td>a new FontResourceLoader with the specified values for {@link #logFontResourceLoaderWarnings},
	 *         {@link #logger}, {@link #graphEnv}, and {@link #fontFolder}</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Shape Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultShapeInfo</td>
	 *         <td>The {@link ShapeInfoUIResource} to use for all unspecified shapes</td>
	 *         <td>{@link Shapes#RECTANGLE_WITH_CUT_CORNERS_TR_BL}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonShapeInfo</td>
	 *         <td>The {@link ShapeInfoUIResource} to use for Buttons</td>
	 *         <td>null (defaults to the {@code defaultShapeInfo} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelShapeInfo</td>
	 *         <td>The {@link ShapeInfoUIResource} to use for Labels</td>
	 *         <td>null (defaults to the {@code defaultShapeInfo} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Border Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>defaultBorder</td>
	 *         <td>The {@link BorderUIResource} to use for all unspecified borders</td>
	 *         <td>new TadukooBorder()</td>
	 *     </tr>
	 *     <tr>
	 *         <td>buttonBorder</td>
	 *         <td>The {@link BorderUIResource} to use on Buttons</td>
	 *         <td>null (defaults to the {@code defaultBorder} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>labelBorder</td>
	 *         <td>The {@link BorderUIResource} to use on Labels</td>
	 *         <td>new {@link NoBorderUIResource}() (can be changed to null to use the {@code defaultBorder} value)</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Titled Border Parameters</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderBorder</td>
	 *         <td>The default border to use in Titled Borders</td>
	 *         <td>null (defaults to the {@code defaultBorder} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderFont</td>
	 *         <td>The default font to use in Titled Borders</td>
	 *         <td>null (defaults to the {@code defaultFont} value)</td>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderColor</td>
	 *         <td>The default color to use in Titled Borders</td>
	 *         <td>{@link Color#BLACK}</td>
	 *     </tr>
	 *     <tr>
	 *         <td>titledBorderPosition</td>
	 *         <td>The default position for the title in Titled Borders</td>
	 *         <td>{@link TitlePosition#TOP}</td>
	 *     </tr>
	 * </table>
	 * <br>
	 * <table>
	 *     <caption><b>Other Customizations</b></caption>
	 *     <tr>
	 *         <th>Field</th>
	 *         <th>Description</th>
	 *         <th>Default Value</th>
	 *     </tr>
	 *     <tr>
	 *         <td>classDefaults</td>
	 *         <td>Class defaults beyond those specified in the "Component UI Classes" section</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 *     <tr>
	 *         <td>systemColorDefaults</td>
	 *         <td>System Color defaults</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 *     <tr>
	 *         <td>componentDefaults</td>
	 *         <td>Component defaults beyond those specified in the above sections</td>
	 *         <td>Empty list</td>
	 *     </tr>
	 * </table>
	 *
	 * @author Logan Ferree (Tadukoo)
	 * @version Alpha v.0.3
	 * @since Alpha v.0.2
	 */
	public static class TadukooThemeBuilder{
		/*
		 * Component UIs
		 */
		
		/** The {@link ButtonUI} class to use */
		private Class<? extends ButtonUI> buttonUI = TadukooButtonUI.class;
		/** The {@link LabelUI} class to use */
		private Class<? extends LabelUI> labelUI = TadukooLabelUI.class;
		
		/*
		 * Paints
		 */
		
		// TODO: Set (actual) defaults for colors
		
		// Foreground Paints
		/** The {@link PaintUIResource} to use for all unspecified foreground paints */
		private PaintUIResource defaultForegroundPaint = new ColorPaintUIResource(Color.BLACK);
		/** The {@link PaintUIResource} to use for the foreground on Buttons */
		private PaintUIResource buttonForegroundPaint = null;
		/** The {@link PaintUIResource} to use for the foreground on Labels */
		private PaintUIResource labelForegroundPaint = null;
		
		// Background Paints
		/** The {@link PaintUIResource} to use for all unspecified background paints */
		private PaintUIResource defaultBackgroundPaint = new ColorPaintUIResource(Color.GREEN);
		/** The {@link PaintUIResource} to use for the background on Buttons */
		private PaintUIResource buttonBackgroundPaint = null;
		/** The {@link PaintUIResource} to use for the background on Labels */
		private PaintUIResource labelBackgroundPaint = new NoPaintUIResource();
		
		// Focus Paints
		/** The {@link PaintUIResource} to use for all unspecified focus paints */
		private PaintUIResource defaultFocusPaint = new ColorPaintUIResource(Color.GRAY);
		/** The {@link PaintUIResource} to use for focus on Buttons */
		private PaintUIResource buttonFocusPaint = null;
		
		// Select Paints
		/** The {@link PaintUIResource} to use for all unspecified select paints */
		private PaintUIResource defaultSelectPaint = new ColorPaintUIResource(0, 200, 0);
		/** The {@link PaintUIResource} to use for select on Buttons */
		private PaintUIResource buttonSelectPaint = null;
		
		// Disabled Text Paints
		/** The {@link PaintUIResource} to use for all unspecified disabled text paints */
		private PaintUIResource defaultDisabledTextPaint = new ColorPaintUIResource(Color.GRAY);
		/** The {@link PaintUIResource} to use for disabled text on Buttons */
		private PaintUIResource buttonDisabledTextPaint = null;
		
		// Disabled Foreground Paints
		/** The {@link PaintUIResource} to use for all unspecified disabled foreground paints */
		private PaintUIResource defaultDisabledForegroundPaint = new ColorPaintUIResource(Color.GRAY);
		/** The {@link PaintUIResource} to use for disabled foreground on Labels */
		private PaintUIResource labelDisabledForegroundPaint = null;
		
		/*
		 * Fonts
		 */
		
		// Default Font
		/** The {@link FontFamily} to use for unspecified fonts */
		private FontFamily defaultFontFamily = FontFamilies.CARLITO.getFamily();
		/** The font style to use for unspecified fonts */
		private int defaultFontStyle = Font.PLAIN;
		/** The font size to use for unspecified fonts */
		private int defaultFontSize = 14;
		
		// Button Font
		/** The {@link FontFamily} to use for Buttons */
		private FontFamily buttonFontFamily = null;
		/** The Font style to use for Buttons */
		private int buttonFontStyle = -1;
		/** The font size to use for Buttons */
		private int buttonFontSize = -1;
		
		// Label Font
		/** The {@link FontFamily} to use for Labels */
		private FontFamily labelFontFamily = null;
		/** The Font style to use for Labels */
		private int labelFontStyle = -1;
		/** The font size to use for Labels */
		private int labelFontSize = -1;
		
		/*
		 * Font Resource Loading
		 */
		
		/** Whether to log warnings generated by the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private boolean logFontResourceLoaderWarnings = false;
		/** An {@link EasyLogger} that will be sent to the FontResourceLoader by default
		 *  - can be ignored if you specify your own FontResourceLoader */
		private EasyLogger logger = null;
		/** The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private GraphicsEnvironment graphEnv = GraphicsEnvironment.getLocalGraphicsEnvironment();
		/** The path to the fonts folder to find font files in if needed in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader */
		private String fontFolder = "fonts/";
		/** The {@link FontResourceLoader} to use in loading fonts and/or ensuring they're in the system */
		private FontResourceLoader fontResourceLoader = null;
		
		/*
		 * Shapes
		 */
		
		/** The {@link ShapeInfoUIResource} to use for unspecified shapes */
		private ShapeInfoUIResource defaultShapeInfo =
				new ShapeInfoUIResource(Shapes.RECTANGLE_WITH_CUT_CORNERS_TR_BL.getShapeInfo());
		/** The {@link ShapeInfoUIResource} to use for Buttons */
		private ShapeInfoUIResource buttonShapeInfo = null;
		/** The {@link ShapeInfoUIResource} to use for Labels */
		private ShapeInfoUIResource labelShapeInfo = null;
		
		/*
		 * Borders
		 */
		/** The {@link BorderUIResource} to use for all unspecified borders */
		private BorderUIResource defaultBorder = new BorderUIResource(ShapedLineBorder.builder().build());
		/** The {@link BorderUIResource} to use on Buttons */
		private BorderUIResource buttonBorder = null;
		/** The {@link BorderUIResource} to use on Labels */
		private BorderUIResource labelBorder = new NoBorderUIResource();
		
		/*
		 * Titled Border Parameters
		 */
		
		/** The default {@link BorderUIResource} to use in Titled Borders */
		private BorderUIResource titledBorderBorder = null;
		/** The default {@link FontFamily} to use in Titled Borders */
		private FontFamily titledBorderFontFamily = null;
		/** The default font style to use in Titled Borders */
		private int titledBorderFontStyle = -1;
		/** The default font size to use in Titled Borders */
		private int titledBorderFontSize = -1;
		/** The default color to use in Titled Borders */
		private ColorUIResource titledBorderColor = new ColorUIResource(Color.BLACK);
		/** The default position for the title in Titled Borders */
		private TitlePosition titledBorderPosition = TitlePosition.TOP;
		
		/*
		 * Other Customizations
		 */
		
		/** Class defaults beyond those specified in the "Component UI Classes" section */
		private Map<String, Class<?>> classDefaults = new HashMap<>();
		 /** System Color defaults */
		private Map<String, ColorUIResource> systemColorDefaults = new HashMap<>();
		/** Component defaults beyond those specified in the above sections */
		private Map<String, Object> componentDefaults = new HashMap<>();
		
		/** Cannot create TadukooThemeBuilder outside of TadukooTheme */
		private TadukooThemeBuilder(){ }
		
		/*
		 * Component UI Classes
		 */
		
		/**
		 * @param buttonUI The {@link ButtonUI} class to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonUI(Class<? extends ButtonUI> buttonUI){
			this.buttonUI = buttonUI;
			return this;
		}
		
		/**
		 * @param labelUI The {@link LabelUI} class to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelUI(Class<? extends LabelUI> labelUI){
			this.labelUI = labelUI;
			return this;
		}
		
		/*
		 * Foreground Paint Methods
		 */
		
		/**
		 * @param defaultForegroundPaint The {@link PaintUIResource} to use for all unspecified foreground paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultForegroundPaint(PaintUIResource defaultForegroundPaint){
			this.defaultForegroundPaint = defaultForegroundPaint;
			return this;
		}
		
		/**
		 * @param buttonForegroundPaint The {@link PaintUIResource} to use for the foreground on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonForegroundPaint(PaintUIResource buttonForegroundPaint){
			this.buttonForegroundPaint = buttonForegroundPaint;
			return this;
		}
		
		/**
		 * @param labelForegroundPaint The {@link PaintUIResource} to use for the foreground on Labels
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelForegroundPaint(PaintUIResource labelForegroundPaint){
			this.labelForegroundPaint = labelForegroundPaint;
			return this;
		}
		
		/*
		 * Background Paint Methods
		 */
		
		/**
		 * @param defaultBackgroundPaint The {@link PaintUIResource} to use for all unspecified background paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultBackgroundPaint(PaintUIResource defaultBackgroundPaint){
			this.defaultBackgroundPaint = defaultBackgroundPaint;
			return this;
		}
		
		/**
		 * @param buttonBackgroundPaint The {@link PaintUIResource} to use for the background on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonBackgroundPaint(PaintUIResource buttonBackgroundPaint){
			this.buttonBackgroundPaint = buttonBackgroundPaint;
			return this;
		}
		
		/**
		 * @param labelBackgroundPaint The {@link PaintUIResource} to use for the background on Labels
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelBackgroundPaint(PaintUIResource labelBackgroundPaint){
			this.labelBackgroundPaint = labelBackgroundPaint;
			return this;
		}
		
		/*
		 * Focus Paint Methods
		 */
		
		/**
		 * @param defaultFocusPaint The {@link PaintUIResource} to use for all unspecified focus paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFocusPaint(PaintUIResource defaultFocusPaint){
			this.defaultFocusPaint = defaultFocusPaint;
			return this;
		}
		
		/**
		 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFocusPaint(PaintUIResource buttonFocusPaint){
			this.buttonFocusPaint = buttonFocusPaint;
			return this;
		}
		
		/*
		 * Select Paint Methods
		 */
		
		/**
		 * @param defaultSelectPaint The {@link PaintUIResource} to use for all unspecified select paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultSelectPaint(PaintUIResource defaultSelectPaint){
			this.defaultSelectPaint = defaultSelectPaint;
			return this;
		}
		
		/**
		 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonSelectPaint(PaintUIResource buttonSelectPaint){
			this.buttonSelectPaint = buttonSelectPaint;
			return this;
		}
		
		/*
		 * Disabled Text Paint Methods
		 */
		
		/**
		 * @param defaultDisabledTextPaint The {@link PaintUIResource} to use for all unspecified disabled text paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultDisabledTextPaint(PaintUIResource defaultDisabledTextPaint){
			this.defaultDisabledTextPaint = defaultDisabledTextPaint;
			return this;
		}
		
		/**
		 * @param buttonDisabledTextPaint The {@link PaintUIResource} to use for disabled text on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonDisabledTextPaint(PaintUIResource buttonDisabledTextPaint){
			this.buttonDisabledTextPaint = buttonDisabledTextPaint;
			return this;
		}
		
		/*
		 * Disabled Foreground Paint Methods
		 */
		
		/**
		 * @param defaultDisabledForegroundPaint The {@link PaintUIResource} to use for all unspecified disabled
		 *                                       foreground paints
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultDisabledForegroundPaint(PaintUIResource defaultDisabledForegroundPaint){
			this.defaultDisabledForegroundPaint = defaultDisabledForegroundPaint;
			return this;
		}
		
		/**
		 * @param labelDisabledForegroundPaint The {@link PaintUIResource} to use for disabled foreground on Labels
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelDisabledForegroundPaint(PaintUIResource labelDisabledForegroundPaint){
			this.labelDisabledForegroundPaint = labelDisabledForegroundPaint;
			return this;
		}
		
		/*
		 * Font Methods
		 */
		
		/**
		 * Specifies the font to use for unspecified fonts
		 *
		 * @param defaultFontFamily The {@link FontFamily} to use
		 * @param defaultFontStyle The font style to use
		 * @param defaultFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultFont(FontFamily defaultFontFamily, int defaultFontStyle, int defaultFontSize){
			this.defaultFontFamily = defaultFontFamily;
			this.defaultFontStyle = defaultFontStyle;
			this.defaultFontSize = defaultFontSize;
			return this;
		}
		
		/**
		 * Specifies the font to use for Buttons
		 *
		 * @param buttonFontFamily The {@link FontFamily} to use
		 * @param buttonFontStyle The font style to use
		 * @param buttonFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonFont(FontFamily buttonFontFamily, int buttonFontStyle, int buttonFontSize){
			this.buttonFontFamily = buttonFontFamily;
			this.buttonFontStyle = buttonFontStyle;
			this.buttonFontSize = buttonFontSize;
			return this;
		}
		
		/**
		 * Specifies the font to use for Labels
		 *
		 * @param labelFontFamily The {@link FontFamily} to use
		 * @param labelFontStyle The font style to use
		 * @param labelFontSize the font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelFont(FontFamily labelFontFamily, int labelFontStyle, int labelFontSize){
			this.labelFontFamily = labelFontFamily;
			this.labelFontStyle = labelFontStyle;
			this.labelFontSize = labelFontSize;
			return this;
		}
		
		/*
		 * Font Resource Loading Methods
		 */
		
		/**
		 * @param logFontResourceLoaderWarnings Whether to log warnings generated by the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder logFontResourceLoaderWarnings(boolean logFontResourceLoaderWarnings){
			this.logFontResourceLoaderWarnings = logFontResourceLoaderWarnings;
			return this;
		}
		
		/**
		 * @param logger An {@link EasyLogger} that will be sent to the FontResourceLoader by default
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder logger(EasyLogger logger){
			this.logger = logger;
			return this;
		}
		
		/**
		 * @param graphEnv The {@link GraphicsEnvironment} to load fonts to in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder graphEnv(GraphicsEnvironment graphEnv){
			this.graphEnv = graphEnv;
			return this;
		}
		
		/**
		 * @param fontFolder The path to the fonts folder to find font files in if needed in the FontResourceLoader
		 *  - can be ignored if you specify your own FontResourceLoader
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder fontFolder(String fontFolder){
			this.fontFolder = fontFolder;
			return this;
		}
		
		/**
		 * @param fontResourceLoader The {@link FontResourceLoader} to use in loading fonts and/or ensuring
		 *  they're in the system
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder fontResourceLoader(FontResourceLoader fontResourceLoader){
			this.fontResourceLoader = fontResourceLoader;
			return this;
		}
		
		/*
		 * Shape Methods
		 */
		
		/**
		 * @param defaultShapeInfo The {@link ShapeInfoUIResource} to use for unspecified shapes
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultShapeInfo(ShapeInfoUIResource defaultShapeInfo){
			this.defaultShapeInfo = defaultShapeInfo;
			return this;
		}
		
		/**
		 * @param buttonShapeInfo The {@link ShapeInfoUIResource} to use for Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonShapeInfo(ShapeInfoUIResource buttonShapeInfo){
			this.buttonShapeInfo = buttonShapeInfo;
			return this;
		}
		
		/**
		 * @param labelShapeInfo The {@link ShapeInfoUIResource} to use for Labels
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelShapeInfo(ShapeInfoUIResource labelShapeInfo){
			this.labelShapeInfo = labelShapeInfo;
			return this;
		}
		
		/*
		 * Border Methods
		 */
		
		/**
		 * @param defaultBorder The {@link Border} to use for all unspecified borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder defaultBorder(BorderUIResource defaultBorder){
			this.defaultBorder = defaultBorder;
			return this;
		}
		
		/**
		 * @param buttonBorder The {@link Border} to use on Buttons
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder buttonBorder(BorderUIResource buttonBorder){
			this.buttonBorder = buttonBorder;
			return this;
		}
		
		/**
		 * @param labelBorder The {@link BorderUIResource} to use on Labels
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder labelBorder(BorderUIResource labelBorder){
			this.labelBorder = labelBorder;
			return this;
		}
		
		/*
		 * Titled Border Parameters
		 */
		
		/**
		 * @param titledBorderBorder The default {@link BorderUIResource} to use in Titled Borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderBorder(BorderUIResource titledBorderBorder){
			this.titledBorderBorder = titledBorderBorder;
			return this;
		}
		
		/**
		 * Specifies the default font to use for Titled Borders
		 *
		 * @param titledBorderFontFamily The {@link FontFamily} to use
		 * @param titledBorderFontStyle The font style to use
		 * @param titledBorderFontSize The font size to use
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderFont(FontFamily titledBorderFontFamily, int titledBorderFontStyle,
		                                      int titledBorderFontSize){
			this.titledBorderFontFamily = titledBorderFontFamily;
			this.titledBorderFontStyle = titledBorderFontStyle;
			this.titledBorderFontSize = titledBorderFontSize;
			return this;
		}
		
		/**
		 * @param titledBorderColor The default color to use in Titled Borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderColor(ColorUIResource titledBorderColor){
			this.titledBorderColor = titledBorderColor;
			return this;
		}
		
		/**
		 * @param titledBorderPosition The default position for the title in Titled Borders
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder titledBorderPosition(TitlePosition titledBorderPosition){
			this.titledBorderPosition = titledBorderPosition;
			return this;
		}
		
		/*
		 * Other Customizations
		 */
		
		/**
		 * @param classDefaults Class defaults beyond those specified in the "Component UI Classes" section
		 *                      - this overwrites the map
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder classDefaults(Map<String, Class<?>> classDefaults){
			this.classDefaults = classDefaults;
			return this;
		}
		
		/**
		 * Adds a class default beyond those specified in the "Component UI Classes" section - this
		 * adds to the map instead of overwriting it
		 *
		 * @param key The key for the class default
		 * @param clazz The actual Class for the class default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder classDefault(String key, Class<?> clazz){
			classDefaults.put(key, clazz);
			return this;
		}
		
		/**
		 * @param systemColorDefaults System Color defaults
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder systemColorDefaults(Map<String, ColorUIResource> systemColorDefaults){
			this.systemColorDefaults = systemColorDefaults;
			return this;
		}
		
		/**
		 * Adds a system color default - this adds to the map instead of overwriting it
		 *
		 * @param key The key for the system color default
		 * @param color The actual color for the system color default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder systemColorDefault(String key, ColorUIResource color){
			systemColorDefaults.put(key, color);
			return this;
		}
		
		/**
		 * @param componentDefaults Component defaults beyond those specified in the above sections
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder componentDefaults(Map<String, Object> componentDefaults){
			this.componentDefaults = componentDefaults;
			return this;
		}
		
		/**
		 * Adds a component default beyond those specified in the above sections - this adds to the map instead
		 * of overwriting it
		 *
		 * @param key The key for the component default
		 * @param value The actual value for the component default
		 * @return this, to continue building
		 */
		public TadukooThemeBuilder componentDefault(String key, Object value){
			componentDefaults.put(key, value);
			return this;
		}
		
		/**
		 * Checks for any errors in the parameters that were set
		 */
		public void checkForErrors(){
			// TODO: Check for errors
		}
		
		/**
		 * Builds a {@link TadukooTheme} using the given customizations (or default customizations for unspecified
		 * parameters).
		 *
		 * @return A new {@link TadukooTheme}
		 * @throws IOException If something goes wrong in loading fonts
		 * @throws FontFormatException If something goes wrong in loading fonts
		 */
		public TadukooTheme build() throws IOException, FontFormatException{
			checkForErrors();
			
			/*
			 * Handle Default Paints
			 */
			
			// Foreground Paints
			if(buttonForegroundPaint == null){
				buttonForegroundPaint = defaultForegroundPaint;
			}
			if(labelForegroundPaint == null){
				labelForegroundPaint = defaultForegroundPaint;
			}
			
			// Background Paints
			if(buttonBackgroundPaint == null){
				buttonBackgroundPaint = defaultBackgroundPaint;
			}
			if(labelBackgroundPaint == null){
				labelBackgroundPaint = defaultBackgroundPaint;
			}
			
			// Focus Paints
			if(buttonFocusPaint == null){
				buttonFocusPaint = defaultFocusPaint;
			}
			
			// Select Paints
			if(buttonSelectPaint == null){
				buttonSelectPaint = defaultSelectPaint;
			}
			
			// Disabled Text Paints
			if(buttonDisabledTextPaint == null){
				buttonDisabledTextPaint = defaultDisabledTextPaint;
			}
			
			// Disabled Foreground Paints
			if(labelDisabledForegroundPaint == null){
				labelDisabledForegroundPaint = defaultDisabledForegroundPaint;
			}
			
			/*
			 * Handle Default Fonts
			 */
			if(buttonFontFamily == null){
				buttonFontFamily = defaultFontFamily;
				buttonFontStyle = defaultFontStyle;
				buttonFontSize = defaultFontSize;
			}
			if(labelFontFamily == null){
				labelFontFamily = defaultFontFamily;
				labelFontStyle = defaultFontStyle;
				labelFontSize = defaultFontSize;
			}
			
			if(titledBorderFontFamily == null){
				titledBorderFontFamily = defaultFontFamily;
				titledBorderFontStyle = defaultFontStyle;
				titledBorderFontSize = defaultFontSize;
			}
			
			// Handle font resource loading
			if(fontResourceLoader == null){
				fontResourceLoader = new FontResourceLoader(logFontResourceLoaderWarnings, logger, graphEnv,
						fontFolder);
			}
			
			// Load fonts
			List<FontFamily> fontFamilies = ListUtil.createList(buttonFontFamily, labelFontFamily,
					titledBorderFontFamily);
			List<String> foundFonts = fontResourceLoader.loadFonts(fontFamilies, true);
			
			// Create the FontUIResources
			FontUIResource buttonFont = new FontUIResource(foundFonts.get(0), buttonFontStyle, buttonFontSize);
			FontUIResource labelFont = new FontUIResource(foundFonts.get(1), labelFontStyle, labelFontSize);
			FontUIResource titledBorderFont = new FontUIResource(foundFonts.get(2),
					titledBorderFontStyle, titledBorderFontSize);
			
			/*
			 * Handle Default Shapes
			 */
			if(buttonShapeInfo == null){
				buttonShapeInfo = defaultShapeInfo;
			}
			if(labelShapeInfo == null){
				labelShapeInfo = defaultShapeInfo;
			}
			
			/*
			 * Handle Default Borders
			 */
			if(buttonBorder == null){
				buttonBorder = defaultBorder;
			}
			if(labelBorder == null){
				labelBorder = defaultBorder;
			}
			
			if(titledBorderBorder == null){
				titledBorderBorder = defaultBorder;
			}
			
			/*
			 * Handle Other Customizations
			 */
			
			// Handle Class Defaults
			int classDefaultsNum = classDefaults.keySet().size();
			Object[] classDefaultsArray = new Object[classDefaultsNum*2];
			Iterator<String> classDefaultsIt = classDefaults.keySet().iterator();
			for(int i = 0; i < classDefaultsNum; i++){
				String key = classDefaultsIt.next();
				classDefaultsArray[i*2] = key;
				classDefaultsArray[i*2+1] = classDefaults.get(key).getCanonicalName();
			}
			
			// Handle System Color Defaults
			int systemColorDefaultsNum = systemColorDefaults.keySet().size();
			Object[] systemColorDefaultsArray = new Object[systemColorDefaultsNum*2];
			Iterator<String> systemColorDefaultsIt = systemColorDefaults.keySet().iterator();
			for(int i = 0; i < systemColorDefaultsNum; i++){
				String key = systemColorDefaultsIt.next();
				systemColorDefaultsArray[i*2] = key;
				systemColorDefaultsArray[i*2+1] = systemColorDefaults.get(key);
			}
			
			// Handle Component Defaults
			int componentDefaultsNum = componentDefaults.keySet().size();
			Object[] componentDefaultsArray = new Object[componentDefaultsNum*2];
			Iterator<String> componentDefaultsIt = componentDefaults.keySet().iterator();
			for(int i = 0; i < componentDefaultsNum; i++){
				String key = componentDefaultsIt.next();
				componentDefaultsArray[i*2] = key;
				componentDefaultsArray[i*2+1] = componentDefaults.get(key);
			}
			
			return new TadukooTheme(buttonUI.getCanonicalName(), labelUI.getCanonicalName(),
					buttonForegroundPaint, buttonBackgroundPaint,
					buttonFocusPaint, buttonSelectPaint, buttonDisabledTextPaint,
					buttonFont, buttonShapeInfo, buttonBorder,
					labelForegroundPaint, labelBackgroundPaint, labelDisabledForegroundPaint,
					labelFont, labelShapeInfo, labelBorder,
					titledBorderBorder, titledBorderFont, titledBorderColor, titledBorderPosition.getValue(),
					classDefaultsArray, systemColorDefaultsArray, componentDefaultsArray);
		}
	}
	
	/*
	 * Component UI Classes
	 */
	/** The {@link ButtonUI} class to use */
	private final String buttonUI;
	/** The {@link LabelUI} class to use */
	private final String labelUI;
	
	/*
	 * Button Paints
	 */
	/** The {@link PaintUIResource} to use for the foreground on Buttons */
	private final PaintUIResource buttonForegroundPaint;
	/** The {@link PaintUIResource} to use for the background on Buttons */
	private final PaintUIResource buttonBackgroundPaint;
	/** The {@link PaintUIResource} to use for focus on Buttons */
	private final PaintUIResource buttonFocusPaint;
	/** The {@link PaintUIResource} to use for select on Buttons */
	private final PaintUIResource buttonSelectPaint;
	/** The {@link PaintUIResource} to use for disabled text on Buttons */
	private final PaintUIResource buttonDisabledTextPaint;
	
	/*
	 * Other Button Customizations
	 */
	/** The {@link FontUIResource} to use for Buttons */
	private final FontUIResource buttonFont;
	/** The {@link ShapeInfoUIResource} to use on Buttons */
	private final ShapeInfoUIResource buttonShapeInfo;
	/** The {@link Border} to use on Buttons */
	private final BorderUIResource buttonBorder;
	
	/*
	 * Label Paints
	 */
	/** The {@link PaintUIResource} to use for the foreground on Labels */
	private final PaintUIResource labelForegroundPaint;
	/** The {@link PaintUIResource} to use for the background on Labels */
	private final PaintUIResource labelBackgroundPaint;
	/** The {@link PaintUIResource} to use for the disabled foreground on Labels */
	private final PaintUIResource labelDisabledForegroundPaint;
	
	/*
	 * Other Label Customizations
	 */
	/** The {@link FontUIResource} to use for Labels */
	private final FontUIResource labelFont;
	/** The {@link ShapeInfoUIResource} to use on Labels */
	private final ShapeInfoUIResource labelShapeInfo;
	/** The {@link BorderUIResource} to use on Labels */
	private final BorderUIResource labelBorder;
	
	/*
	 * Titled Border Customizations
	 */
	/** The default {@link BorderUIResource} to use in Titled Borders */
	private final BorderUIResource titledBorderBorder;
	/** The default {@link FontUIResource} to use in Titled Borders */
	private final FontUIResource titledBorderFont;
	/** The default color to use in Titled Borders */
	private final ColorUIResource titledBorderColor;
	/** The default position for the title in Titled Borders */
	private final int titledBorderPosition;
	
	/*
	 * Other Defaults
	 */
	/** Class defaults beyond those specified in the "Component UI Classes" section */
	private final Object[] classDefaults;
	/** System Color defaults */
	private final Object[] systemColorDefaults;
	/** Component defaults beyond those specified in the other sections */
	private final Object[] componentDefaults;
	
	/**
	 * Constructs a new TadukooTheme with the given customizations.
	 *
	 * @param buttonUI The {@link ButtonUI} class to use
	 * @param labelUI The {@link LabelUI} class to use
	 * @param buttonForegroundPaint The {@link PaintUIResource} to use for the foreground on Buttons
	 * @param buttonBackgroundPaint The {@link PaintUIResource} to use for the background on Buttons
	 * @param buttonFocusPaint The {@link PaintUIResource} to use for focus on Buttons
	 * @param buttonSelectPaint The {@link PaintUIResource} to use for select on Buttons
	 * @param buttonDisabledTextPaint The {@link PaintUIResource} to use for disabled text on Buttons
	 * @param buttonFont The {@link FontUIResource} to use for Buttons
	 * @param buttonShapeInfo The {@link ShapeInfoUIResource} to use on Buttons
	 * @param buttonBorder The {@link Border} to use on Buttons
	 * @param labelForegroundPaint The {@link PaintUIResource} to use for the foreground on Labels
	 * @param labelBackgroundPaint The {@link PaintUIResource} to use for the background on Labels
	 * @param labelDisabledForegroundPaint The {@link PaintUIResource} to use for the disabled foreground on Labels
	 * @param labelFont The {@link FontUIResource} to use for Labels
	 * @param labelShapeInfo The {@link ShapeInfoUIResource} to use for Labels
	 * @param labelBorder The {@link BorderUIResource} to use for Labels
	 * @param titledBorderBorder The default {@link BorderUIResource} to use in Titled Borders
	 * @param titledBorderFont The default {@link FontUIResource} to use in Titled Borders
	 * @param titledBorderColor The default color to use in Titled Borders
	 * @param titledBorderPosition The default position for the title in Titled Borders
	 * @param classDefaults Class defaults beyond those specified in the "Component UI Classes" section
	 * @param systemColorDefaults System Color defaults
	 * @param componentDefaults Component defaults beyond those specified in the other sections
	 */
	private TadukooTheme(String buttonUI, String labelUI,
	                     PaintUIResource buttonForegroundPaint, PaintUIResource buttonBackgroundPaint,
	                     PaintUIResource buttonFocusPaint, PaintUIResource buttonSelectPaint,
	                     PaintUIResource buttonDisabledTextPaint, FontUIResource buttonFont,
	                     ShapeInfoUIResource buttonShapeInfo, BorderUIResource buttonBorder,
	                     PaintUIResource labelForegroundPaint, PaintUIResource labelBackgroundPaint,
	                     PaintUIResource labelDisabledForegroundPaint,
	                     FontUIResource labelFont, ShapeInfoUIResource labelShapeInfo, BorderUIResource labelBorder,
	                     BorderUIResource titledBorderBorder, FontUIResource titledBorderFont,
	                     ColorUIResource titledBorderColor, int titledBorderPosition,
	                     Object[] classDefaults, Object[] systemColorDefaults, Object[] componentDefaults){
		// Set Component UI Classes
		this.buttonUI = buttonUI;
		this.labelUI = labelUI;
		
		// Set Button Paints
		this.buttonForegroundPaint = buttonForegroundPaint;
		this.buttonBackgroundPaint = buttonBackgroundPaint;
		this.buttonFocusPaint = buttonFocusPaint;
		this.buttonSelectPaint = buttonSelectPaint;
		this.buttonDisabledTextPaint = buttonDisabledTextPaint;
		
		// Set Other Button Customizations
		this.buttonFont = buttonFont;
		this.buttonShapeInfo = buttonShapeInfo;
		this.buttonBorder = buttonBorder;
		
		// Set Label Paints
		this.labelForegroundPaint = labelForegroundPaint;
		this.labelBackgroundPaint = labelBackgroundPaint;
		this.labelDisabledForegroundPaint = labelDisabledForegroundPaint;
		
		// Set Other Label Customizations
		this.labelFont = labelFont;
		this.labelShapeInfo = labelShapeInfo;
		this.labelBorder = labelBorder;
		
		// Set Titled Border Customizations
		this.titledBorderBorder = titledBorderBorder;
		this.titledBorderFont = titledBorderFont;
		this.titledBorderColor = titledBorderColor;
		this.titledBorderPosition = titledBorderPosition;
		
		// Set Other Defaults
		this.classDefaults = classDefaults;
		this.systemColorDefaults = systemColorDefaults;
		this.componentDefaults = componentDefaults;
	}
	
	/**
	 * @return A {@link TadukooThemeBuilder} to use in building a TadukooTheme
	 */
	public static TadukooThemeBuilder builder(){
		return new TadukooThemeBuilder();
	}
	
	/*
	 * Component UI Classes
	 */
	
	/**
	 * @return The {@link ButtonUI} class to use
	 */
	public String getButtonUI(){
		return buttonUI;
	}
	
	/**
	 * @return The {@link LabelUI} class to use
	 */
	public String getLabelUI(){
		return labelUI;
	}
	
	/*
	 * Button Paints
	 */
	
	/**
	 * @return The {@link PaintUIResource} to use for the foreground on Buttons
	 */
	public PaintUIResource getButtonForegroundPaint(){
		return buttonForegroundPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for the background on Buttons
	 */
	public PaintUIResource getButtonBackgroundPaint(){
		return buttonBackgroundPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for focus on Buttons
	 */
	public PaintUIResource getButtonFocusPaint(){
		return buttonFocusPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for select on Buttons
	 */
	public PaintUIResource getButtonSelectPaint(){
		return buttonSelectPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for disabled text on Buttons
	 */
	public PaintUIResource getButtonDisabledTextPaint(){
		return buttonDisabledTextPaint;
	}
	
	/*
	 * Other Button Customizations
	 */
	
	/**
	 * @return The {@link FontUIResource} to use for Buttons
	 */
	public FontUIResource getButtonFont(){
		return buttonFont;
	}
	
	/**
	 * @return The {@link ShapeInfoUIResource} to use on Buttons
	 */
	public ShapeInfoUIResource getButtonShapeInfo(){
		return buttonShapeInfo;
	}
	
	/**
	 * @return The {@link BorderUIResource} to use on Buttons
	 */
	public BorderUIResource getButtonBorder(){
		return buttonBorder;
	}
	
	/*
	 * Label Paints
	 */
	
	/**
	 * @return The {@link PaintUIResource} to use for the foreground on Labels
	 */
	public PaintUIResource getLabelForegroundPaint(){
		return labelForegroundPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for the background on Labels
	 */
	public PaintUIResource getLabelBackgroundPaint(){
		return labelBackgroundPaint;
	}
	
	/**
	 * @return The {@link PaintUIResource} to use for the disabled foreground on Labels
	 */
	public PaintUIResource getLabelDisabledForegroundPaint(){
		return labelDisabledForegroundPaint;
	}
	
	/*
	 * Other Label Customizations
	 */
	
	/**
	 * @return The {@link FontUIResource} to use for Labels
	 */
	public FontUIResource getLabelFont(){
		return labelFont;
	}
	
	/**
	 * @return The {@link ShapeInfoUIResource} to use for Labels
	 */
	public ShapeInfoUIResource getLabelShapeInfo(){
		return labelShapeInfo;
	}
	
	/**
	 * @return The {@link BorderUIResource} to use on Labels
	 */
	public BorderUIResource getLabelBorder(){
		return labelBorder;
	}
	
	/*
	 * Titled Border Customizations
	 */
	
	/**
	 * @return The default {@link BorderUIResource} to use in Titled Borders
	 */
	public BorderUIResource getTitledBorderBorder(){
		return titledBorderBorder;
	}
	
	/**
	 * @return The default {@link FontUIResource} to use in Titled Borders
	 */
	public FontUIResource getTitledBorderFont(){
		return titledBorderFont;
	}
	
	/**
	 * @return The default color to use in Titled Borders
	 */
	public ColorUIResource getTitledBorderColor(){
		return titledBorderColor;
	}
	
	/**
	 * @return The default position for the title in Titled Borders
	 */
	public int getTitledBorderPosition(){
		return titledBorderPosition;
	}
	
	/*
	 * Other Defaults
	 */
	
	/**
	 * @return Class defaults beyond those specified in the "Component UI Classes" section
	 */
	public Object[] getClassDefaults(){
		return classDefaults;
	}
	
	/**
	 * @return System Color defaults
	 */
	public Object[] getSystemColorDefaults(){
		return systemColorDefaults;
	}
	
	/**
	 * @return Component defaults beyond those specified in the other sections
	 */
	public Object[] getComponentDefaults(){
		return componentDefaults;
	}
}
