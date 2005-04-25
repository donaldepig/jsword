package org.crosswire.jsword.book.search.lucene;

import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.book.search.Index;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.NoSuchKeyException;

/**
 * A range token specifies how a range should be included in the search.
 * It provides a range, a modifier (AND [+] or AND NOT [-]).
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
 * @author DM Smith [ dmsmith555 at hotmail dot com]
 * @version $Id$
 */
public class RangeQuery implements Query
{

    /**
     * 
     */
    public RangeQuery(String theRange)
    {
        range = theRange;
    }

    /* (non-Javadoc)
     * @see org.crosswire.jsword.book.search.parse.Query#find(org.crosswire.jsword.book.search.Index)
     */
    public Key find(Index index) throws BookException
    {
        try
        {
            return index.getKey(range);
        }
        catch (NoSuchKeyException e)
        {
            throw new BookException(Msg.ILLEGAL_PASSAGE, e, new Object[] { range });
        }
    }

    /**
     * @return the range
     */
    public String getRange()
    {
        return range;
    }

    private String range;
}
