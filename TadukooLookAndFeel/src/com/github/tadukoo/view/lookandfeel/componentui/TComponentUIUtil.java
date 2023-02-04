package com.github.tadukoo.view.lookandfeel.componentui;

import com.github.tadukoo.view.components.interfaces.paint.HasDisabledForegroundPaint;
import com.github.tadukoo.view.components.interfaces.TComponent;
import com.github.tadukoo.view.lookandfeel.TadukooTheme;
import com.github.tadukoo.view.components.interfaces.paint.HasDisabledTextPaint;
import com.github.tadukoo.view.components.interfaces.paint.HasSelectAndFocusPaints;
import com.github.tadukoo.view.components.interfaces.paint.HasSizablePaints;
import com.github.tadukoo.view.paint.SizablePaint;
import com.github.tadukoo.view.shapes.ShapeInfo;
import com.github.tadukoo.view.components.interfaces.Shaped;
import com.github.tadukoo.view.uimanager.UIManagerUtil;

import javax.swing.plaf.UIResource;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Paint;

/**
 * This interface provides utilities for {@link TComponent}s - this interface is to be implemented in
 * Component UI classes for them to utilize the methods present to help in accessing customizations
 * from the {@link TadukooTheme} and/or the components themselves and installing and uninstalling said
 * customizations. It also contains some common paint methods.
 *
 * @author Logan Ferree (Tadukoo)
 * @version Alpha v.0.4
 * @since Alpha v.0.3
 */
public interface TComponentUIUtil{
	
	/**
	 * Retrieves the Property Prefix string present on the Component UI class.
	 * <br><br>
	 * This method is necessary due to the normal propertyPrefix method being default,
	 * so it's not available here in this interface.
	 *
	 * @return The property prefix string for this Component UI
	 */
	String getPropertyPrefixString();
	
	/*
	 * Accessor Methods
	 */
	
	/**
	 * @param c A {@link Component} which may have the foreground paint on it
	 * @param size The {@link Dimension}s of the surface to be painted
	 * @return The sized {@link Paint} for the foreground - the {@link SizablePaint} used may come from the
	 * {@link Component} if it has it, or default to the Look &amp; Feel's paint
	 */
	default Paint getForegroundPaint(Component c, Dimension size){
		SizablePaint paint;
		// Grab the foreground paint from the component if it has it
		if(c instanceof HasSizablePaints){
			paint = ((HasSizablePaints) c).getForegroundPaint();
		}else{
			// Default to the Look & Feel's setting
			paint = UIManagerUtil.getForegroundPaint(getPropertyPrefixString());
		}
		// Return the paint based on the given size
		return paint.getPaint(size);
	}
	
	/**
	 * @param c A {@link Component} which may have the background paint on it
	 * @param size The {@link Dimension}s of the surface to be painted
	 * @return The sized {@link Paint} for the background - the {@link SizablePaint} used may come from the
	 * {@link Component} if it has it, or default to the Look &amp; Feel's paint
	 */
	default Paint getBackgroundPaint(Component c, Dimension size){
		SizablePaint paint;
		// Grab the background paint from the component if it has it
		if(c instanceof HasSizablePaints){
			paint = ((HasSizablePaints) c).getBackgroundPaint();
		}else{
			// Default to the Look & Feel's setting
			paint = UIManagerUtil.getBackgroundPaint(getPropertyPrefixString());
		}
		// Return the paint based on the given size
		return paint.getPaint(size);
	}
	
	/**
	 * @param c A {@link Component} which may be {@link Shaped}
	 * @return The {@link ShapeInfo} from the {@link Component} if it has it, or from the Look &amp; Feel otherwise
	 */
	default ShapeInfo getShape(Component c){
		// Grab the shape info from the component if it has it
		if(c instanceof Shaped){
			return ((Shaped) c).getShapeInfo();
		}else{
			// Default to the Look & Feel's setting
			return UIManagerUtil.getDefaultShapeInfo(getPropertyPrefixString());
		}
	}
	
	/**
	 * @param c A {@link Component} which may have the select paint on it
	 * @param size The {@link Dimension}s of the surface to be painted
	 * @return The sized {@link Paint} for the select - the {@link SizablePaint} used may come from the
	 * {@link Component} if it has it, or default to the Look &amp; Feel's paint
	 */
	default Paint getSelectPaint(Component c, Dimension size){
		SizablePaint paint;
		// Grab the select paint from the component if it has it
		if(c instanceof HasSelectAndFocusPaints){
			paint = ((HasSelectAndFocusPaints) c).getSelectPaint();
		}else{
			// Default to the Look & Feel's setting
			paint = UIManagerUtil.getSelectPaint(getPropertyPrefixString());
		}
		// Return the paint based on the given size
		return paint.getPaint(size);
	}
	
	/**
	 * @param c A {@link Component} which may have the focus paint on it
	 * @param size The {@link Dimension}s of the surface to be painted
	 * @return The sized {@link Paint} for the focus - the {@link SizablePaint} used may come from the
	 * {@link Component} if it has it, or default to the Look &amp; Feel's paint
	 */
	default Paint getFocusPaint(Component c, Dimension size){
		SizablePaint paint;
		// Grab the focus paint from the component if it has it
		if(c instanceof HasSelectAndFocusPaints){
			paint = ((HasSelectAndFocusPaints) c).getFocusPaint();
		}else{
			// Default to the Look & Feel's setting
			paint = UIManagerUtil.getFocusPaint(getPropertyPrefixString());
		}
		// Return the paint based on the given size
		return paint.getPaint(size);
	}
	
	
	/**
	 * @param c A {@link Component} which may have the disabled text paint on it
	 * @param size The {@link Dimension}s of the surface to be painted
	 * @return The sized {@link Paint} for the disabled text - the {@link SizablePaint} used may come from the
	 * {@link Component} if it has it, or default to the Look &amp; Feel's paint
	 */
	default Paint getDisabledTextPaint(Component c, Dimension size){
		SizablePaint paint;
		// Grab the disabled text paint from the component if it has it
		if(c instanceof HasDisabledTextPaint){
			paint = ((HasDisabledTextPaint) c).getDisabledTextPaint();
		}else{
			// Default to the Look & Feel's setting
			paint = UIManagerUtil.getDisabledTextPaint(getPropertyPrefixString());
		}
		// Return the paint based on the given size
		return paint.getPaint(size);
	}
	
	/**
	 * @param c A {@link Component} which may have the disabled foreground paint on it
	 * @param size The {@link Dimension}s of the surface to be painted
	 * @return The sized {@link Paint} for the disabled foreground - the {@link SizablePaint} used may come from the
	 * {@link Component} if it has it, or default to the Look &amp; Feel's paint
	 */
	default Paint getDisabledForegroundPaint(Component c, Dimension size){
		SizablePaint paint;
		// Grab the disabled foreground paint from the component if it has it
		if(c instanceof HasDisabledForegroundPaint){
			paint = ((HasDisabledForegroundPaint) c).getDisabledForegroundPaint();
		}else{
			// Default to the Look & Feel's setting
			paint = UIManagerUtil.getDisabledForegroundPaint(getPropertyPrefixString());
		}
		// Return the paint based on the given size
		return paint.getPaint(size);
	}
	
	/*
	 * Install Methods
	 */
	
	/**
	 * Installs foreground and background paint on the given Component
	 *
	 * @param c The {@link Component} that {@link HasSizablePaints} and is to have paints installed
	 */
	default void installHasSizablePaints(HasSizablePaints c){
		c.setForegroundPaint(UIManagerUtil.getForegroundPaint(getPropertyPrefixString()));
		c.setBackgroundPaint(UIManagerUtil.getBackgroundPaint(getPropertyPrefixString()));
	}
	
	/**
	 * Installs shape info on the given Component
	 *
	 * @param c The {@link Component} that is {@link Shaped} and is to have shape info installed
	 */
	default void installShaped(Shaped c){
		c.setShapeInfo(UIManagerUtil.getDefaultShapeInfo(getPropertyPrefixString()));
	}
	
	/**
	 * Installs the customizations associated with {@link TComponent} on the given {@link Component},
	 * which are the foreground and background paints and the shape info.
	 *
	 * @param c The {@link TComponent} that is to have customizations installed
	 */
	default void installTComponent(TComponent c){
		installHasSizablePaints(c);
		installShaped(c);
	}
	
	/**
	 * Checks if the given {@link Component} has any of the custom interfaces present in {@link TComponent} and
	 * will install any customizations on it if it has those interfaces.
	 *
	 * @param c The {@link Component} that is to have customizations installed
	 */
	default void installTComponentDefaults(Component c){
		// If we have a TComponent, we can do some stuff easily
		if(c instanceof TComponent){
			installTComponent((TComponent) c);
		}else{
			// TComponent covers HasSizablePaints and Shaped
			
			// Remove the default foreground and background paints on the button if it's supported and using them
			if(c instanceof HasSizablePaints){
				installHasSizablePaints((HasSizablePaints) c);
			}
			
			// Remove shape function if it's a Shaped button and if it's using the default
			if(c instanceof Shaped){
				installShaped((Shaped) c);
			}
		}
	}
	
	/**
	 * Installs focus and select paint on the given Component
	 *
	 * @param c The {@link Component} that {@link HasSelectAndFocusPaints} and is to have paints installed
	 */
	default void installHasSelectAndFocusPaints(HasSelectAndFocusPaints c){
		c.setSelectPaint(UIManagerUtil.getSelectPaint(getPropertyPrefixString()));
		c.setFocusPaint(UIManagerUtil.getFocusPaint(getPropertyPrefixString()));
	}
	
	/**
	 * Checks if the given {@link Component} has the {@link HasSelectAndFocusPaints} interface and will install
	 * the select and focus paints if the interface is present.
	 *
	 * @param c The {@link Component} that is to have customizations installed
	 */
	default void installHasSelectAndFocusPaintsDefaults(Component c){
		if(c instanceof HasSelectAndFocusPaints){
			installHasSelectAndFocusPaints((HasSelectAndFocusPaints) c);
		}
	}
	
	/**
	 * Installs disabledText paint on the given Component
	 *
	 * @param c The {@link Component} that {@link HasDisabledTextPaint} and is to have the paint installed
	 */
	default void installHasDisabledTextPaint(HasDisabledTextPaint c){
		c.setDisabledTextPaint(UIManagerUtil.getDisabledTextPaint(getPropertyPrefixString()));
	}
	
	/**
	 * Checks if the given {@link Component} has the {@link HasDisabledTextPaint} interface and will install
	 * the disabledText paint if the interface is present.
	 *
	 * @param c The {@link Component} that is to have customizations installed
	 */
	default void installHasDisabledTextPaintDefaults(Component c){
		if(c instanceof HasDisabledTextPaint){
			installHasDisabledTextPaint((HasDisabledTextPaint) c);
		}
	}
	
	/**
	 * Installs disabledForeground paint on the given Component
	 *
	 * @param c The {@link Component} that {@link HasDisabledForegroundPaint} and is to have the paint installed
	 */
	default void installHasDisabledForegroundPaint(HasDisabledForegroundPaint c){
		c.setDisabledForegroundPaint(UIManagerUtil.getDisabledForegroundPaint(getPropertyPrefixString()));
	}
	
	/**
	 * Checks if the given {@link Component} has the {@link HasDisabledForegroundPaint} interface and will install
	 * the disabledForeground paint if the interface is present.
	 *
	 * @param c The {@link Component} that is to have customizations installed
	 */
	default void installHasDisabledForegroundPaintDefaults(Component c){
		if(c instanceof HasDisabledForegroundPaint){
			installHasDisabledForegroundPaint((HasDisabledForegroundPaint) c);
		}
	}
	
	/*
	 * Uninstall Methods
	 */
	
	/**
	 * Uninstalls foreground and background paint from the given Component
	 *
	 * @param c The {@link Component} that {@link HasSizablePaints} and is to have paints uninstalled
	 */
	default void uninstallHasSizablePaints(HasSizablePaints c){
		if(c.getForegroundPaint() instanceof UIResource){
			c.setForegroundPaint(null);
		}
		if(c.getBackgroundPaint() instanceof UIResource){
			c.setBackgroundPaint(null);
		}
	}
	
	/**
	 * Uninstalls shape info from the given Component
	 *
	 * @param c The {@link Component} that is {@link Shaped} and is to have shape info uninstalled
	 */
	default void uninstallShaped(Shaped c){
		if(c.getShapeInfo() instanceof UIResource){
			c.setShapeInfo(null);
		}
	}
	
	/**
	 * Uninstalls the customizations associated with {@link TComponent} from the given {@link Component},
	 * which are the foreground and background paints and the shape info.
	 *
	 * @param c The {@link TComponent} that is to have customizations uninstalled
	 */
	default void uninstallTComponent(TComponent c){
		uninstallHasSizablePaints(c);
		uninstallShaped(c);
	}
	
	/**
	 * Checks if the given {@link Component} has any of the custom interfaces present in {@link TComponent} and
	 * will uninstall any customizations from it if it has those interfaces.
	 *
	 * @param c The {@link Component} that is to have customizations uninstalled
	 */
	default void uninstallTComponentDefaults(Component c){
		// If we have a TComponent, we can do some stuff easily
		if(c instanceof TComponent){
			uninstallTComponent((TComponent) c);
		}else{
			// TComponent covers HasSizablePaints and Shaped
			
			// Remove the default foreground and background paints on the button if it's supported and using them
			if(c instanceof HasSizablePaints){
				uninstallHasSizablePaints((HasSizablePaints) c);
			}
			
			// Remove shape function if it's a Shaped button and if it's using the default
			if(c instanceof Shaped){
				uninstallShaped((Shaped) c);
			}
		}
	}
	
	/**
	 * Uninstalls select and focus paint from the given Component
	 *
	 * @param c The {@link Component} that {@link HasSelectAndFocusPaints} and is to have paints uninstalled
	 */
	default void uninstallHasSelectAndFocusPaints(HasSelectAndFocusPaints c){
		if(c.getSelectPaint() instanceof UIResource){
			c.setSelectPaint(null);
		}
		if(c.getFocusPaint() instanceof UIResource){
			c.setFocusPaint(null);
		}
	}
	
	/**
	 * Checks if the given {@link Component} has the {@link HasSelectAndFocusPaints} interface and will uninstall
	 * the select and focus paints if the interface is present.
	 *
	 * @param c The {@link Component} that is to have customizations uninstalled
	 */
	default void uninstallHasSelectAndFocusPaintsDefaults(Component c){
		if(c instanceof HasSelectAndFocusPaints){
			uninstallHasSelectAndFocusPaints((HasSelectAndFocusPaints) c);
		}
	}
	
	/**
	 * Uninstalls disabledText paint from the given Component
	 *
	 * @param c The {@link Component} that {@link HasDisabledTextPaint} and is to have the paint uninstalled
	 */
	default void uninstallHasDisabledTextPaint(HasDisabledTextPaint c){
		if(c.getDisabledTextPaint() instanceof UIResource){
			c.setDisabledTextPaint(null);
		}
	}
	
	/**
	 * Checks if the given {@link Component} has the {@link HasDisabledTextPaint} interface and will uninstall
	 * the disabledText paint if the interface is present.
	 *
	 * @param c The {@link Component} that is to have customizations uninstalled
	 */
	default void uninstallHasDisabledTextPaintDefaults(Component c){
		if(c instanceof HasDisabledTextPaint){
			uninstallHasDisabledTextPaint((HasDisabledTextPaint) c);
		}
	}
	
	/**
	 * Uninstalls disabledForeground paint from the given Component
	 *
	 * @param c The {@link Component} that {@link HasDisabledForegroundPaint} and is to have the paint uninstalled
	 */
	default void uninstallHasDisabledForegroundPaint(HasDisabledForegroundPaint c){
		if(c.getDisabledForegroundPaint() instanceof UIResource){
			c.setDisabledForegroundPaint(null);
		}
	}
	
	/**
	 * Checks if the given {@link Component} has the {@link HasDisabledForegroundPaint} interface and will uninstall
	 * the disabledForeground paint if the interface is present.
	 *
	 * @param c The {@link Component} that is to have customizations uninstalled
	 */
	default void uninstallHasDisabledForegroundPaintDefaults(Component c){
		if(c instanceof HasDisabledForegroundPaint){
			uninstallHasDisabledForegroundPaint((HasDisabledForegroundPaint) c);
		}
	}
	
	/*
	 * Paint Methods
	 */
	
	/**
	 * Paints the background for the {@link Component} using its {@link ShapeInfo} (if it has it), and using
	 * its background paint (if it has it). If the background paint turns out to be null, the background will not
	 * be painted.
	 * <br><br>
	 * This method is to be used in the overridden update method of Component UI classes (where the background is
	 * normally painted)
	 *
	 * @param g The {@link Graphics} to use to paint
	 * @param c The {@link Component} to use for painting its background (potentially)
	 */
	default void paintBackground(Graphics g, Component c){
		// Cast Graphics to Graphics2D for our purposes
		Graphics2D g2d = (Graphics2D) g;
		
		// Grab dimensions
		int width = c.getWidth();
		int height = c.getHeight();
		
		// Grab the background paint and set it
		Paint backgroundPaint = getBackgroundPaint(c, new Dimension(width, height));
		if(backgroundPaint != null){
			g2d.setPaint(backgroundPaint);
			
			// Paint the background
			g2d.fill(getShape(c).getShapeFunc().apply(0, 0, width, height));
		}
		// If background paint is null, it signifies we don't want to paint it
	}
}
