/**
 * Distribution License:
 * JSword is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License, version 2.1 as published by
 * the Free Software Foundation. This program is distributed in the hope
 * that it will be useful, but WITHOUT ANY WARRANTY; without even the
 * implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
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
 * ID: $Id$
 */
package org.crosswire.jsword.passage;

import java.util.EnumSet;

import junit.framework.TestCase;

import org.crosswire.jsword.book.CaseType;
import org.crosswire.jsword.versification.BibleBook;
import org.crosswire.jsword.versification.BibleInfo;
import org.crosswire.jsword.versification.BookName;

/**
 * JUnit Test.
 * 
 * @see gnu.lgpl.License for license details.<br>
 *      The copyright to this program is held by it's authors.
 * @author Joe Walker [joe at eireneh dot com]
 */
public class BibleInfoTest extends TestCase {
    public BibleInfoTest(String s) {
        super(s);
    }

    private CaseType storedCase;

    @Override
    protected void setUp() {
        storedCase = BookName.getDefaultCase();
    }

    @Override
    protected void tearDown() {
        BookName.setCase(storedCase);
    }

    public void testCase() {
        BookName.setCase(CaseType.LOWER);
        assertEquals(CaseType.LOWER, BookName.getDefaultCase());

        BookName.setCase(CaseType.UPPER);
        assertEquals(CaseType.UPPER, BookName.getDefaultCase());

        BookName.setCase(CaseType.SENTENCE);
        assertEquals(CaseType.SENTENCE, BookName.getDefaultCase());
    }

    public void testGetLongBookName() throws Exception {
        BookName.setCase(CaseType.SENTENCE);
        assertEquals("Genesis", BibleBook.GEN.getLongName());
        assertEquals("Revelation of John", BibleBook.REV.getLongName());

        BookName.setCase(CaseType.LOWER);
        assertEquals("genesis", BibleBook.GEN.getLongName());
        assertEquals("revelation of john", BibleBook.REV.getLongName());

        BookName.setCase(CaseType.UPPER);
        assertEquals("GENESIS", BibleBook.GEN.getLongName());
        assertEquals("REVELATION OF JOHN", BibleBook.REV.getLongName());

    }

    public void testGetShortBookName() throws Exception {
        BookName.setCase(CaseType.SENTENCE);
        assertEquals("Gen", BibleBook.GEN.getShortName());
        assertEquals("Exo", BibleBook.EXOD.getShortName());
        assertEquals("Judg", BibleBook.JUDG.getShortName());
        assertEquals("Mal", BibleBook.MAL.getShortName());
        assertEquals("Mat", BibleBook.MATT.getShortName());
        assertEquals("Phili", BibleBook.PHIL.getShortName());
        assertEquals("Phile", BibleBook.PHLM.getShortName());
        assertEquals("Jude", BibleBook.JUDE.getShortName());
        assertEquals("Rev", BibleBook.REV.getShortName());

        BookName.setCase(CaseType.LOWER);
        assertEquals("gen", BibleBook.GEN.getShortName());
        assertEquals("exo", BibleBook.EXOD.getShortName());
        assertEquals("judg", BibleBook.JUDG.getShortName());
        assertEquals("mal", BibleBook.MAL.getShortName());
        assertEquals("mat", BibleBook.MATT.getShortName());
        assertEquals("phili", BibleBook.PHIL.getShortName());
        assertEquals("phile", BibleBook.PHLM.getShortName());
        assertEquals("jude", BibleBook.JUDE.getShortName());
        assertEquals("rev", BibleBook.REV.getShortName());

        BookName.setCase(CaseType.UPPER);
        assertEquals("GEN", BibleBook.GEN.getShortName());
        assertEquals("EXO", BibleBook.EXOD.getShortName());
        assertEquals("JUDG", BibleBook.JUDG.getShortName());
        assertEquals("MAL", BibleBook.MAL.getShortName());
        assertEquals("MAT", BibleBook.MATT.getShortName());
        assertEquals("PHILI", BibleBook.PHIL.getShortName());
        assertEquals("PHILE", BibleBook.PHLM.getShortName());
        assertEquals("JUDE", BibleBook.JUDE.getShortName());
        assertEquals("REV", BibleBook.REV.getShortName());
    }

    public void testGetBookJogger() throws Exception {
        assertEquals("Gen", BibleBook.GEN.getOSIS());
        assertEquals("Exod", BibleBook.EXOD.getOSIS());
        assertEquals("Rev", BibleBook.REV.getOSIS());
    }

    public void testGetBookNumber() {
        assertEquals(BibleBook.GEN, BibleBook.getBook("Genesis"));
        assertEquals(BibleBook.GEN, BibleBook.getBook("Gene"));
        assertEquals(BibleBook.GEN, BibleBook.getBook("Gen"));
        assertEquals(BibleBook.GEN, BibleBook.getBook("G"));
        assertEquals(BibleBook.GEN, BibleBook.getBook("g"));
        assertEquals(BibleBook.GEN, BibleBook.getBook("GEN"));
        assertEquals(BibleBook.GEN, BibleBook.getBook("genesis"));
        assertEquals(BibleBook.PS, BibleBook.getBook("psa"));
        assertEquals(BibleBook.PS, BibleBook.getBook("ps"));
        assertEquals(BibleBook.PS, BibleBook.getBook("pss"));
        assertEquals(BibleBook.PS, BibleBook.getBook("psalter"));
        assertEquals(BibleBook.ECCL, BibleBook.getBook("ecc"));
        assertEquals(BibleBook.ECCL, BibleBook.getBook("Qohelot"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("son"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("song"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("song of solomon"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("songofsolomon"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("ss"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("canticle"));
        assertEquals(BibleBook.SONG, BibleBook.getBook("can"));
        assertEquals(BibleBook.PHIL, BibleBook.getBook("phi"));
        assertEquals(BibleBook.PHIL, BibleBook.getBook("phil"));
        assertEquals(BibleBook.PHIL, BibleBook.getBook("phili"));
        assertEquals(BibleBook.PHLM, BibleBook.getBook("phile"));
        assertEquals(BibleBook.REV, BibleBook.getBook("revelations"));
        assertEquals(BibleBook.REV, BibleBook.getBook("rev"));

        assertEquals(null, BibleBook.getBook("1"));
    }

    public void testIn() throws Exception {
        assertEquals(1533, BibleInfo.versesInBook(BibleBook.GEN));
        assertEquals(404, BibleInfo.versesInBook(BibleBook.REV));

        // Counts using loops
        int viw_b = 0;
        int viw_c = 0;
        int ciw = 0;

        // For all the books
        // for (BibleBook b : BibleBook.values()) {
        for (BibleBook b: EnumSet.range(BibleBook.GEN, BibleBook.REV)) {
            // Count and check the verses in this book
            int vib = 0;
            for (int c = 1; c <= BibleInfo.chaptersInBook(b); c++) {
                vib += BibleInfo.versesInChapter(b, c);
            }
            assertEquals(vib, BibleInfo.versesInBook(b));

            // Continue the verse counts for the whole Bible
            for (int c = 1; c <= BibleInfo.chaptersInBook(b); c++) {
                viw_c += BibleInfo.versesInChapter(b, c);
            }

            viw_b += BibleInfo.versesInBook(b);

            // Continue the chapter count for the whole Bible
            ciw += BibleInfo.chaptersInBook(b);
        }
        assertEquals(BibleInfo.versesInBible(), viw_b);
        assertEquals(BibleInfo.versesInBible(), viw_c);
        assertEquals(BibleInfo.chaptersInBible(), ciw);
        assertEquals(BibleInfo.booksInBible(), 66);
    }

    public void testOrdinal() throws Exception {
        int first_verse_ord = 1;
        int last_verse_ord = 1;
        // for (BibleBook b : BibleBook.values()) {
        for (BibleBook b: EnumSet.range(BibleBook.GEN, BibleBook.REV)) {
            for (int c = 1; c <= BibleInfo.chaptersInBook(b); c++) {
                last_verse_ord = first_verse_ord + BibleInfo.versesInChapter(b, c) - 1;

                assertEquals(first_verse_ord, BibleInfo.verseOrdinal(new Verse(b, c, 1)));
                assertEquals(first_verse_ord + 1, BibleInfo.verseOrdinal(new Verse(b, c, 2)));
                assertEquals(last_verse_ord, BibleInfo.verseOrdinal(new Verse(b, c, BibleInfo.versesInChapter(b, c))));

                assertEquals(new Verse(b, c, 1), BibleInfo.decodeOrdinal(first_verse_ord));
                assertEquals(new Verse(b, c, 2), BibleInfo.decodeOrdinal(first_verse_ord + 1));
                assertEquals(new Verse(b, c, BibleInfo.versesInChapter(b, c)), BibleInfo.decodeOrdinal(last_verse_ord));

                first_verse_ord += BibleInfo.versesInChapter(b, c);
            }
        }
    }

    public void testValidate() throws Exception {
        try {
            BibleInfo.validate(null, 1, 1);
            fail();
        } catch (NoSuchVerseException ex) {
        }
        // for (BibleBook b : BibleBook.values()) {
        for (BibleBook b: EnumSet.range(BibleBook.GEN, BibleBook.REV)) {
            try {
                BibleInfo.validate(b, 0, 1);
                fail();
            } catch (NoSuchVerseException ex) {
            }
            try {
                BibleInfo.validate(b, 1, 0);
                fail();
            } catch (NoSuchVerseException ex) {
            }

            for (int c = 1; c <= BibleInfo.chaptersInBook(b); c++) {
                try {
                    BibleInfo.validate(b, c, 0);
                    fail();
                } catch (NoSuchVerseException ex) {
                }

                for (int v = 1; v <= BibleInfo.versesInChapter(b, c); v++) {
                    BibleInfo.validate(b, c, v);
                }
                try {
                    BibleInfo.validate(b, c, BibleInfo.versesInChapter(b, c) + 1);
                    fail();
                } catch (NoSuchVerseException ex) {
                }
            }

            try {
                BibleInfo.validate(null, BibleInfo.chaptersInBook(b) + 1, 1);
                fail();
            } catch (NoSuchVerseException ex) {
            }
            try {
                BibleInfo.validate(null, 1, BibleInfo.versesInBook(b) + 1);
                fail();
            } catch (NoSuchVerseException ex) {
            }
        }
    }

    public void testPatch() throws Exception {
        int all = 1;
        // for (BibleBook b : BibleBook.values()) {
        for (BibleBook b: EnumSet.range(BibleBook.GEN, BibleBook.REV)) {
            int cib = BibleInfo.chaptersInBook(b);
            for (int c = 1; c <= cib; c++) {
                int vic = BibleInfo.versesInChapter(b, c);
                for (int v = 1; v <= vic; v++) {
                    Verse pv = BibleInfo.patch(BibleBook.GEN, 1, all);

                    assertEquals(b, pv.getBook());
                    assertEquals(c, pv.getChapter());
                    assertEquals(v, pv.getVerse());
                    all++;
                }
            }
        }
        Verse gen11 = new Verse(BibleBook.GEN, 1, 1);
        assertEquals(gen11, BibleInfo.patch(BibleBook.GEN, 1, 1));
        assertEquals(gen11, BibleInfo.patch(BibleBook.GEN, 1, 0));
        assertEquals(gen11, BibleInfo.patch(BibleBook.GEN, 0, 1));
        assertEquals(gen11, BibleInfo.patch(BibleBook.GEN, 0, 0));
        assertEquals(gen11, BibleInfo.patch(null, 1, 1));
        assertEquals(gen11, BibleInfo.patch(null, 1, 0));
        assertEquals(gen11, BibleInfo.patch(null, 0, 1));
        assertEquals(gen11, BibleInfo.patch(null, 0, 0));
    }

    public void testVerseCount() throws Exception {
        int count_up = 0;
        int count_down = 31102;
        Verse gen11 = new Verse(BibleBook.GEN, 1, 1);
        Verse gen110 = new Verse(BibleBook.GEN, 1, 10);
        Verse exo11 = new Verse(BibleBook.EXOD, 1, 1);
        Verse rev99 = new Verse(BibleBook.REV, 22, 21);
        // for (BibleBook b : BibleBook.values()) {
        for (BibleBook b: EnumSet.range(BibleBook.GEN, BibleBook.REV)) {
            for (int c = 1; c <= BibleInfo.chaptersInBook(b); c++) {
                for (int v = 1; v <= BibleInfo.versesInChapter(b, c); v++) {
                    Verse curVerse = new Verse(b, c, v);
                    int up = curVerse.subtract(gen11) + 1;
                    int down = rev99.subtract(curVerse) + 1;

                    assertEquals(++count_up, up);
                    assertEquals(count_down--, down);

                    assertEquals(verseCountSlow(gen11, curVerse), up);
                }
            }

            assertEquals(BibleInfo.versesInBook(b), new Verse(b, BibleInfo.chaptersInBook(b), BibleInfo.versesInChapter(b, BibleInfo.chaptersInBook(b))).subtract(new Verse(b, 1, 1)) + 1 );
        }
        assertEquals(BibleInfo.versesInBook(BibleBook.GEN), exo11.subtract(gen11));
        assertEquals(9, gen110.subtract(gen11));
    }

    public void testNames() {
        assertEquals(0, BibleBook.GEN.ordinal());
        assertEquals(65, BibleBook.REV.ordinal());
    }

    /**
     * This is code from BibleInfo that was needed only as part of testing, so I
     * Moved it here. How many verses between ref1 and ref2 (inclusive).
     * 
     * @param verse1
     *            The earlier verse
     * @param verse2
     *            The later verse
     * @exception NoSuchVerseException
     *                If either reference is illegal
     */
    protected int verseCountSlow(Verse verse1, Verse verse2) throws NoSuchVerseException {
        int count = 0;

        int ch1 = verse1.getChapter();
        int ver1 = verse1.getVerse();

        // If we are in different books, count the verses until the books are
        // the same
        if (verse1.getBook() != verse2.getBook()) {
            // 1st count to the end of the chapter
            count += BibleInfo.versesInChapter(verse1.getBook(), verse1.getChapter()) - verse1.getVerse() + 1;

            // Then count from the end of the chapter to the end of the book
            for (int c = verse1.getChapter() + 1; c <= BibleInfo.chaptersInBook(verse1.getBook()); c++) {
                count += BibleInfo.versesInChapter(verse1.getBook(), c);
            }

            // Then count until the books are the same
            for (BibleBook b = BibleInfo.getNextBook(verse1.getBook()); b.compareTo(verse2.getBook()) < 0; b = BibleInfo.getNextBook(b)) {
                count += BibleInfo.versesInBook(b);
            }

            // The new position
            ch1 = 1;
            ver1 = 1;
        }

        // Count the verses in the chapters so that we are in the same chapter
        for (int c = ch1; c < verse2.getChapter(); c++) {
            count += BibleInfo.versesInChapter(verse2.getBook(), c);
        }

        // And finally the verses in the final chapter
        count += verse2.getVerse() - ver1 + 1;

        return count;
    }
}
