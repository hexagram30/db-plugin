(defprotocol SessionDBAPI
  (id [this user-id]
    "Return the unique ID that for the given user's session.")
  (type [this user-id]
    "A keyword representing one of the supported session types, e.g., :telnet,
    :telnet-ssl, :http.")
  (login-attempts [this user-id]
    "The number (integer) of login attempts a user has made.")
  (authenticated? [this user-id]
    "Returns a boolean indicating whether the current session instance
    is one where the use has successfully logged in.")
  (user-data [this user-id]
    "Aribtrary data associated with the current user.")
  (shell-stack [this user-id]
    "Returns the shell history of the current user, most recent (current) shell
    first.")
  (current-shell [this user-id]
    "Offered as a convenience to return the shell at the top of the shell
    stack.")
  (update [this user-id data]
    "Update (deep merge) all of the given user's session data with the
    provided data."))
