/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 or later
 * as published by the Free Software Foundation. This program is distributed
 * in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Lesser General Public License for more details.
 *
 * The License is available on the internet at:
 *       http://www.gnu.org/copyleft/lgpl.html
 * or by writing to:
 *      Free Software Foundation, Inc.
 *      59 Temple Place - Suite 330
 *      Boston, MA 02111-1307, USA
 *
 * Copyright: 2005
 *     The copyright to this program is held by it's authors.
 *
 * ID: $Id:OrQuery.java 984 2006-01-23 14:18:33 -0500 (Mon, 23 Jan 2006) dmsmith $
 */
package org.crosswire.jsword.index.query;

import org.crosswire.jsword.book.BookException;
import org.crosswire.jsword.index.Index;
import org.crosswire.jsword.passage.Key;
import org.crosswire.jsword.passage.PassageTally;

/**
 * An OR query specifies that a result is the union of the left and the right
 * query results.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author DM Smith [ dmsmith555 at yahoo dot com]
 */
public class OrQuery extends AbstractBinaryQuery {

    /**
     *
     */
    public OrQuery(Query theLeftQuery, Query theRightQuery) {
        super(theLeftQuery, theRightQuery);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.crosswire.jsword.index.search.parse.Query#find(org.crosswire.jsword
     * .index.search.Index)
     */
    public Key find(Index index) throws BookException {
        Key left = getLeftQuery().find(index);
        Key right = getRightQuery().find(index);

        if (left.isEmpty()) {
            return right;
        }

        if (right.isEmpty()) {
            return left;
        }

        // If ranking was requested then prioritize it.
        if (right instanceof PassageTally) {
            right.addAll(left);
            return right;
        }

        left.addAll(right);

        return left;
    }
}
