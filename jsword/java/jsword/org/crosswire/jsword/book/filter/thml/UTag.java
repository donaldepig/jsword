package org.crosswire.jsword.book.filter.thml;

import org.crosswire.jsword.book.OSISUtil;
import org.jdom.Element;
import org.xml.sax.Attributes;

/**
 * THML Tag to process the underline element.
 * 
 * <p><table border='1' cellPadding='3' cellSpacing='0'>
 * <tr><td bgColor='white' class='TableRowColor'><font size='-7'>
 *
 * Distribution Licence:<br />
 * JSword is free software; you can redistribute it
 * and/or modify it under the terms of the GNU General Public License,
 * version 2 as published by the Free Software Foundation.<br />
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br />
 * The License is available on the internet
 * <a href='http://www.gnu.org/copyleft/gpl.html'>here</a>, or by writing to:
 * Free Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 * MA 02111-1307, USA<br />
 * The copyright to this program is held by it's authors.
 * </font></td></tr></table>
 * @see gnu.gpl.Licence
 * @author Joe Walker [joe at eireneh dot com]
 * @version $Id$
 */
public class UTag implements Tag
{
    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.filter.thml.Tag#getTagName()
     */
    public String getTagName()
    {
        return "u"; //$NON-NLS-1$
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.filter.thml.Tag#processTag(org.jdom.Element, org.xml.sax.Attributes)
     */
    public Element processTag(Element ele, Attributes attrs)
    {
        Element hi = OSISUtil.factory().createHI();
        hi.setAttribute(OSISUtil.ATTRIBUTE_HI_REND, OSISUtil.HI_UNDERLINE);
        ele.addContent(hi);
        return hi;
    }
}
