(defprotocol GraphDBAPI
  (add-edge
    [this src dst]
    [this src dst attrs]
    [this src dst label attrs]
    "")
  (add-vertex
    [this]
    [this attrs]
    [this label attrs]
    "")
  (cypher
    [this query-str]
    "")
  (edges
    [this]
    [this ids]
    "")
  (features
    [this]
    "")
  (get-edge
    [this id]
    "")
  (get-edges
    [this]
    "")
  (get-index
    [this data-type]
    [this data-type id]
    "")
  (get-relations
    [this]
    "")
  (get-vertex
    [this id]
    "")
  (get-vertex-relations
    [this id]
    "")
  (get-vertices
    [this]
    "")
  (get-vertices-relations
    [this ids]
    "")
  (graph-name
    [this]
    "")
  (relations
    [this]
    [this ids]
    "")
  (remove-edge
    [this id]
    "")
  (remove-edges
    [this]
    "")
  (remove-relation
    [this rid vid]
    "")
  (remove-relations
    [this vid]
    "")
  (remove-vertex
    [this id]
    "")
  (remove-vertices
    [this]
    "")
  (show-features
    [this]
    "")
  (variables
    [this]
    "")
  (vertices
    [this]
    [this ids]
    ""))
