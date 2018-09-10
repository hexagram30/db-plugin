(defprotocol LangDBAPI
  (ingest-stats [this data]
                [this language generator-type data]
                [this race-name name-type generator-type data])
  (lang-stats [this language generator-type])
  (name-stats [this race-name name-type generator-type])
  (get-stats [this language generator-type]
             [this race-name name-type generator-type])
  ;; Note that pos-tag (part-of-speech tag) is one of the tags defined by the
  ;; Penn Treebank Project. For a full listing, see:
  ;;  * https://www.ling.upenn.edu/courses/Fall_2003/ling001/penn_treebank_pos.html
  (update-dictionary [this word pos-tag definition]))
