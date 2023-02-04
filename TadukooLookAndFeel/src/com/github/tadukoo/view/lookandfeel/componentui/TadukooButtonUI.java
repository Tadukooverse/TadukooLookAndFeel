package com.github.tadukoo.view.lookandfeel.componentui;

import com.github.tadukoo.view.components.interfaces.TButton;
import com.github.tadukoo.view.lookandfeel.TadukooLookAndFeel;
import com.github.tadukoo.view.uimanager.ButtonThemeProperties;

import javax.swing.AbstractButton;
import javax.swing.ButtonModel;
import javax.swing.JComponent;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.metal.MetalButtonUI;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

/**
 * A {@link ComponentUI} class for use with {@link Button}s, used by {@link TadukooLookAndFeel} to
 * provide customizations for the Buttons beyond those present in existing Look &amp; Feels.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 * @since Alpha v.0.1
 */
public class TadukooButtonUI extends MetalButtonUI implements TComponentUIUtil{
	
	/**
	 * Returns an instance of {@code TadukooButtonUI}.
	 *
	 * @param c a component
	 * @return an instance of {@code TadukooButtonUI}
	 */
	public static ComponentUI createUI(JComponent c){
		return new TadukooButtonUI();
	}
	
	/** {@inheritDoc} */
	@Override
	public String getPropertyPrefixString(){
		return ButtonThemeProperties.PROPERTY_PREFIX;
	}
	
	/*
	 * Install/Uninstall Defaults
	 */
	
	/** {@inheritDoc} */
	@Override
	public void installDefaults(AbstractButton b){
		super.installDefaults(b);
		
		// Most of the shapes do not cover the entire area, causing problems if we leave opaque set to true
		b.setOpaque(false);
		
		// If we have a TButton, we can set everything easily
		if(b instanceof TButton t){
			installTComponent(t);
			installHasSelectAndFocusPaints(t);
			installHasDisabledTextPaint(t);
		}else{
			// If we don't have a TButton, we have to check the various smaller interfaces
			installTComponentDefaults(b);
			installHasSelectAndFocusPaintsDefaults(b);
			installHasDisabledTextPaintDefaults(b);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void uninstallDefaults(AbstractButton b){
		super.uninstallDefaults(b);
		
		// If we have a TButton, we can uninstall everything easily
		if(b instanceof TButton t){
			uninstallTComponent(t);
			uninstallHasSelectAndFocusPaints(t);
			uninstallHasDisabledTextPaint(t);
		}else{
			// If we don't have a TButton, we have to check the various smaller interfaces
			uninstallTComponentDefaults(b);
			uninstallHasSelectAndFocusPaintsDefaults(b);
			uninstallHasDisabledTextPaintDefaults(b);
		}
	}
	
	/*
	 * Paint Methods
	 */
	
	/** {@inheritDoc} */
	@Override
	public void update(Graphics g, JComponent c){
		paintBackground(g, c);
		
		// Do the other painting
		paint(g, c);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void paintButtonPressed(Graphics g, AbstractButton b){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		if(b.isContentAreaFilled()){
			// Grab button size for use in paint and shape functions
			Dimension size = b.getSize();
			
			// Grab the paint to use and set it on the graphics
			g2d.setPaint(getSelectPaint(b, size));
			
			// Grab the shape and fill it
			g2d.fill(getShape(b).getShapeFunc().apply(0, 0, size.width, size.height));
		}
	}
	
	/** {@inheritDoc} */
	@Override
	protected void paintFocus(Graphics g, AbstractButton b,
	                          Rectangle viewRect, Rectangle textRect, Rectangle iconRect){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		Rectangle focusRect = new Rectangle();
		String text = b.getText();
		boolean isIcon = b.getIcon() != null;
		
		// If there is text
		if(text != null && !text.isEmpty()){
			if(!isIcon){
				focusRect.setBounds(textRect);
			}else{
				focusRect.setBounds(iconRect.union(textRect));
			}
		}else if(isIcon){
			// If there is an icon and no text
			focusRect.setBounds(iconRect);
		}
		
		// Grab the paint to use and set it on the graphics
		g2d.setPaint(getFocusPaint(b, focusRect.getSize()));
		
		// Draw the focus rectangle
		g2d.drawRect((focusRect.x-1), (focusRect.y-1),
				focusRect.width+1, focusRect.height+1);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void paintText(Graphics g, JComponent c, Rectangle textRect, String text){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		AbstractButton b = (AbstractButton) c;
		ButtonModel model = b.getModel();
		FontMetrics fm = c.getFontMetrics(g.getFont());
		//int mnemIndex = b.getDisplayedMnemonicIndex();
		
		// Determine the size to be painted
		Dimension size = new Dimension(fm.stringWidth(text), fm.getHeight());
		
		// Determine the paint to use
		if(model.isEnabled()){
			// Paint the text normally using foreground paint
			g2d.setPaint(getForegroundPaint(b, size));
		}else{
			// Paint the text disabled
			g2d.setPaint(getDisabledTextPaint(b, size));
		}
		
		// Draw the actual text
		g.drawString(text, textRect.x, textRect.y + fm.getAscent());
		
		// TODO: Implement more of this string drawing method to further support stuff
		// SwingUtilities2.drawStringUnderlineCharAt(c, g,text,mnemIndex,
		//		textRect.x, textRect.y + fm.getAscent());
	}
}
