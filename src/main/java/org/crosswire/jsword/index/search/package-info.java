/**
<p>
  This package helps implementors of the various Book interfaces implement
  search.
</p>

<p>
  Search can be split into 2 sections, the actual indexing, and the parsing
  of queries. The Index and Parser interfaces represent these sections. Some
  implementations of the Searcher interface (like ser) will use this
  splitting, whilst Lucene uses it's own code.
</p>
*/
package org.crosswire.jsword.index.search;
