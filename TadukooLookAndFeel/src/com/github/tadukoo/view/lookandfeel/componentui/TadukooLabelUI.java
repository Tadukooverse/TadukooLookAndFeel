package com.github.tadukoo.view.lookandfeel.componentui;

import com.github.tadukoo.view.components.interfaces.TLabel;
import com.github.tadukoo.view.lookandfeel.TadukooLookAndFeel;
import com.github.tadukoo.view.uimanager.LabelThemeProperties;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicLabelUI;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Label;

/**
 * A {@link ComponentUI} class for use with {@link Label}s, used by {@link TadukooLookAndFeel} to
 * provide customizations for the Labels beyond those present in existing Look &amp; Feels.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 * @since Alpha v.0.3
 */
public class TadukooLabelUI extends BasicLabelUI implements TComponentUIUtil{
	
	/**
	 * Returns an instance of {@code TadukooLabelUI}.
	 *
	 * @param c a component
	 * @return an instance of {@code TadukooLabelUI}
	 */
	public static ComponentUI createUI(JComponent c){
		return new TadukooLabelUI();
	}
	
	/** {@inheritDoc} */
	@Override
	public String getPropertyPrefixString(){
		return LabelThemeProperties.PROPERTY_PREFIX;
	}
	
	/*
	 * Install/Uninstall Defaults
	 */
	
	/** {@inheritDoc} */
	@Override
	public void installDefaults(JLabel c){
		super.installDefaults(c);
		
		// If we have a TLabel, we can set everything easily
		if(c instanceof TLabel t){
			installTComponent(t);
			installHasDisabledForegroundPaint(t);
		}else{
			// If we don't have a TLabel, we need to check the various smaller interfaces
			installTComponentDefaults(c);
			installHasDisabledForegroundPaintDefaults(c);
		}
	}
	
	/** {@inheritDoc} */
	@Override
	public void uninstallDefaults(JLabel c){
		super.uninstallDefaults(c);
		
		// If we have a TLabel, we can uninstall everything easily
		if(c instanceof TLabel t){
			uninstallTComponent(t);
			uninstallHasDisabledForegroundPaint(t);
		}else{
			// If we don't have a TLabel, we need to check the various smaller interfaces
			uninstallTComponentDefaults(c);
			uninstallHasDisabledForegroundPaintDefaults(c);
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
	protected void paintEnabledText(JLabel l, Graphics g, String s, int textX, int textY){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		// Set the Paint
		FontMetrics fm = l.getFontMetrics(g.getFont());
		g2d.setPaint(getForegroundPaint(l, new Dimension(fm.stringWidth(s), fm.getHeight())));
		
		// Draw the actual text
		g2d.drawString(s, textX, textY);
		
		// TODO: Implement more of this string drawing method to further support stuff
		//int mnemIndex = l.getDisplayedMnemonicIndex();
		//SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex,
		//		textX, textY);
	}
	
	/** {@inheritDoc} */
	@Override
	protected void paintDisabledText(JLabel l, Graphics g, String s, int textX, int textY){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		// Set the Paint
		FontMetrics fm = l.getFontMetrics(g.getFont());
		g2d.setPaint(getDisabledForegroundPaint(l, new Dimension(fm.stringWidth(s), fm.getHeight())));
		
		// Draw the actual text
		g2d.drawString(s, textX, textY);
		
		// TODO: Implement more of this string drawing method to further support stuff
		//int mnemIndex = l.getDisplayedMnemonicIndex();
		//SwingUtilities2.drawStringUnderlineCharAt(l, g, s, mnemIndex,
		//		textX, textY);
	}
}
