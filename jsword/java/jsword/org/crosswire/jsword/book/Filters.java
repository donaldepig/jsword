
package org.crosswire.jsword.book;

/**
 * Some common implementations of BookFilter.
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
 * @see docs.Licence
 * @author Joe Walker [joe at eireneh dot com]
 * @version $Id$
 */
public class Filters
{
    /**
     * Ensure we cant be created
     */
    private Filters()
    {
    }

    /**
     * A filter that accepts everything that implements Bible
     */
    public static BookFilter getBibles()
    {
        return new BookFilter()
        {
            public boolean test(BookMetaData bmd)
            {
                return bmd instanceof BibleMetaData;
            }
        };
    }

    /**
     * A filter that accepts everything that implements Dictionary
     */
    public static BookFilter getDictionaries()
    {
        return new BookFilter()
        {
            public boolean test(BookMetaData bmd)
            {
                return bmd instanceof DictionaryMetaData;
            }
        };
    }

    /**
     * A filter that accepts everything that implements Commentary
     */
    public static BookFilter getCommentaries()
    {
        return new BookFilter()
        {
            public boolean test(BookMetaData bmd)
            {
                return bmd instanceof CommentaryMetaData;
            }
        };
    }

    /**
     * A filter that accepts everything faster that a set minimum.
     */
    public static BookFilter getFaster(final int slowest)
    {
        return new BookFilter()
        {
            public boolean test(BookMetaData bmd)
            {
                return bmd.getSpeed() > slowest;
            }
        };
    }

    /**
     * A filter that accepts everything faster that a set minimum.
     */
    public static BookFilter both(final BookFilter b1, final BookFilter b2)
    {
        return new BookFilter()
        {
            public boolean test(BookMetaData bmd)
            {
                return b1.test(bmd) && b2.test(bmd);
            }
        };
    }

    /**
     * A filter that accepts everything faster that a set minimum.
     */
    public static BookFilter either(final BookFilter b1, final BookFilter b2)
    {
        return new BookFilter()
        {
            public boolean test(BookMetaData bmd)
            {
                return b1.test(bmd) || b2.test(bmd);
            }
        };
    }
}