/*
 * A11y.java
 *
 * Copyright (C) 2009-19 by RStudio, Inc.
 *
 * Unless you have received this program directly from RStudio pursuant
 * to the terms of a commercial license agreement with RStudio, then
 * this program is licensed to you under the terms of version 3 of the
 * GNU Affero General Public License. This program is distributed WITHOUT
 * ANY EXPRESS OR IMPLIED WARRANTY, INCLUDING THOSE OF NON-INFRINGEMENT,
 * MERCHANTABILITY OR FITNESS FOR A PARTICULAR PURPOSE. Please refer to the
 * AGPL (http://www.gnu.org/licenses/agpl-3.0.txt) for more details.
 *
 */
package org.rstudio.core.client.a11y;

import com.google.gwt.aria.client.LiveValue;
import com.google.gwt.aria.client.Roles;
import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import org.rstudio.core.client.StringUtil;
import org.rstudio.core.client.theme.res.ThemeStyles;

/**
 * Accessibility helpers including newer ARIA stuff not included with GWT
 */
public class A11y
{
   public static void setARIADialogModal(Element element)
   {
      element.setAttribute("aria-modal", "true");
   }

   /**
    * Flag an image that does not convey content, is decorative, or is
    * redundant (purpose already conveyed in text).
    * @param element Image element
    */
   public static void setDecorativeImage(Element element)
   {
      element.setAttribute("alt", "");
   }

   public static void setARIATablistOrientation(Element element, boolean vertical)
   {
      element.setAttribute("aria-orientation", vertical ? "vertical" : "horizontal");
   }

   /**
    * Associate a label element with another element via the <code>for</code> attribute.
    * @param label the element to add <code>for</code> attribute to (assumed to be label)
    * @param field the element whose id should be used; will add an id if necessary
    */
   public static void associateLabelWithField(Element label, Element field)
   {
      String fieldId = field.getId();
      if (StringUtil.isNullOrEmpty(fieldId))
      {
         fieldId = DOM.createUniqueId();
         field.setId(fieldId);
      }
      label.setAttribute("for", fieldId);
   }

   /**
    * Set aria-current value on an element
    * @param element element to mark
    * @param value value ("page", "step", "location", "date", "time", "true", "false")
    */
   public static void setARIACurrent(Element element, String value)
   {
      if (StringUtil.isNullOrEmpty(value) || StringUtil.equals(value, "false"))
         element.removeAttribute("aria-current");
      else
         element.setAttribute("aria-current", value);
   }
   
   /**
    * Set aria-current value on an element
    * @param widget widget to mark
    * @param value value ("page", "step", "location", "date", "time", "true", "false")
    */
   public static void setARIACurrent(Widget widget, String value)
   {
      setARIACurrent(widget.getElement(), value);
   }

   /**
    * Make a widget hidden to screen readers.
    * @param widget
    */
   public static void setARIAHidden(Widget widget)
   {
      setARIAHidden(widget.getElement());
   }

   /**
    * Make an element hidden to screen readers.
    * @param el
    */
   public static void setARIAHidden(Element el)
   {
      el.setAttribute("aria-hidden", "true");
   }

   /**
    * Mark as widget as an aria-live alert
    *
    * @param widget
    * @param assertive
    */
   public static void setAlertRole(Widget widget, boolean assertive)
   {
      setAlertRole(widget.getElement(), assertive);
   }

   /**
    * Mark an element as an aria-live alert
    *
    * @param el
    * @param assertive
    */
   public static void setAlertRole(Element el, boolean assertive)
   {
      Roles.getAlertRole().set(el);
      Roles.getAlertRole().setAriaLiveProperty(el, assertive ? LiveValue.ASSERTIVE : LiveValue.POLITE);
      Roles.getAlertRole().setAriaAtomicProperty(el, true);
   }

   /**
    * Mark a widget as an aria-live status
    * @param widget
    * @param assertive
    */
   public static void setStatusRole(Widget widget, boolean assertive)
   {
      setStatusRole(widget.getElement(), assertive);
   }

   /**
    * Mark as widget as an aria-live status
    * @param el
    * @param assertive
    */
   public static void setStatusRole(Element el, boolean assertive)
   {
      Roles.getStatusRole().set(el);
      Roles.getAlertRole().setAriaLiveProperty(el, assertive ? LiveValue.ASSERTIVE : LiveValue.POLITE);
      Roles.getAlertRole().setAriaAtomicProperty(el, true);
   }

   public static void setVisuallyHidden(Widget widget)
   {
      setVisuallyHidden(widget.getElement());
   }

   public static void setVisuallyHidden(Element el)
   {
      el.setClassName(ThemeStyles.INSTANCE.visuallyHidden());
   }

   public static void unsetVisuallyHidden(Widget widget)
   {
      unsetVisuallyHidden(widget.getElement());
   }

   public static void unsetVisuallyHidden(Element el)
   {
      el.removeClassName(ThemeStyles.INSTANCE.visuallyHidden());
   }
}
