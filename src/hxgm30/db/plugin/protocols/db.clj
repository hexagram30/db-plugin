(defprotocol DBAPI
  (backup [this] [this path]
    "")
  (closed? [this]
    "")
  (commit [this]
    "")
  (configuration [this]
    "")
  (disconnect [this]
    "")
  (dump [this]
    "")
  (explain [this query-str]
    "")
  (flush [this]
    "")
  (open? [this]
    "")
  (rollback [this]
    "")
  (tx [this]
    ""))
