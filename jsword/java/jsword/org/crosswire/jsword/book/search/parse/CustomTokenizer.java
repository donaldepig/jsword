package org.crosswire.jsword.book.search.parse;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.crosswire.jsword.book.BookException;

/**
 * Our command line parsing is a little specialized, so StringTokenizer is not
 * up to the job. The specific problem is that there is sometimes no separator
 * between parts of the command, and since this is specialized we also leave the
 * results in a Vector of SearchWords.
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
public class CustomTokenizer
{
    /**
     * Prevent Instansiation
     */
    private CustomTokenizer()
    {
    }

    /**
     * Convenience method to generate a Vector of SearchWords
     * @param sought The text to parse
     * @param commands The Hashtable of SearchWords to select from
     * @return A List of selected SearchWords
     */
    public static List tokenize(String sought, Map commands) throws BookException
    {
        List output = new ArrayList();
        String commandChars = getSingleCharWords(commands);
        int currentType = charType(sought.charAt(0), commandChars);
        int startIndex = 0;

        // If the first character is a [ then we have a problem because
        // the loop starts with the second character because it needs
        // something to compare with - so if we do start with a [ then
        // we make sure that we prepend with a " "
        if (sought.length() > 0 && sought.charAt(0) == '[')
        {
            sought = " " + sought; //$NON-NLS-1$
        }

        // Loop, comparing each character with the previous one
        for (int i = 1; i <= sought.length(); i++)
        {
            // An escaped section
            if (i != sought.length() && sought.charAt(i) == '[')
            {
                int end = sought.indexOf(']', i);
                if (end == -1)
                {
                    throw new BookException(Msg.UNMATCHED_ESCAPE);
                }

                addWord(output, commands, "["); //$NON-NLS-1$
                addWord(output, commands, sought.substring(i + 1, end));
                addWord(output, commands, "]"); //$NON-NLS-1$

                currentType = CHAR_SPACE;
                i = end + 1;
            }

            // If this is the last word then so long as this letter is not
            // a space (in which case it has been added already) then add all
            // the word in
            if (i == sought.length())
            {
                if (currentType != CHAR_SPACE)
                {
                    addWord(output, commands, sought.substring(startIndex));
                }
            }
            else
            {
                // If this is the start of a new section of the command
                // then add the word in
                int new_type = charType(sought.charAt(i), commandChars);
                if (currentType != new_type || new_type == CHAR_COMMAND)
                {
                    if (currentType != CHAR_SPACE)
                    {
                        addWord(output, commands, sought.substring(startIndex, i));
                    }

                    startIndex = i;
                    currentType = charType(sought.charAt(i), commandChars);
                }
            }
        }

        return output;
    }

    /**
     * What class of character is this?
     * @param sought The string to be searched for
     * @return The chatacter class
     */
    private static final int charType(char sought, String commands)
    {
        if (Character.isWhitespace(sought))
        {
            return CHAR_SPACE;
        }

        if (commands.indexOf(sought) != -1)
        {
            return CHAR_COMMAND;
        }

        return CHAR_PARAM;
    }

    /**
     * Convenience function to add a Word to the Vector being created.
     * @param output The Vector to alter
     * @param commands The Word source
     * @param word The trigger to look for
     */
    private static void addWord(List output, Map commands, String word)
    {
        Object wordObj = commands.get(word);
        if (wordObj == null)
        {
            wordObj = new DefaultWord(word);
        }

        output.add(wordObj);
    }

    /**
     * Convenience function to add a Word to the Vector being created.
     * @param commands The Word source
     */
    private static String getSingleCharWords(Map commands)
    {
        Iterator it = commands.keySet().iterator();
        StringBuffer buf = new StringBuffer();

        while (it.hasNext())
        {
            String cmd = (String) it.next();
            if (cmd.length() == 1)
            {
                buf.append(cmd);
            }
        }

        return buf.toString();
    }

    /**
     * The type of character (see charType)
     */
    private static final int CHAR_PARAM = 0;

    /**
     * The type of character (see charType)
     */
    private static final int CHAR_COMMAND = 1;

    /**
     * The type of character (see charType)
     */
    private static final int CHAR_SPACE = 2;
}
