(defprotocol LangDBAPI
  (ingest-stats [this language] [this race-name name-type])
  (lang-stats [this language])
  (name-stats [this race-name name-type])
  ;; Note that pos-tag (part-of-speech tag) is one of the tags defined by the
  ;; Penn Treebank Project. For a full listing, see:
  ;;  * https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html
  (update-dictionary [this word pos-tag definition]))
