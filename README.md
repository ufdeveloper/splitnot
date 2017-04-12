# splitnot-api
Notifications pushed to you when a transaction is added to your bank account.

This is a Java based REST API which exposes a few API endpoints to be consumed by the splitnot frontend app.
The basic architecture diagram can be found here - https://gist.github.com/ufdeveloper/6b032ee594bb21436274af9e385043c3#file-splitnot_architecture-png

It uses plaid(https://plaid.com/) to interface with your bank account.





The project is planned to be done in 3 phases,

Phase 1 - Retrieve your account transactions and display them in the webapp.

Phase 2 - Create alerts when new transactions are posted in your account. The alert will be displayed on the top of the webpage.

Phase 3 - Create an android app which notifies you when a transaction is posted.
