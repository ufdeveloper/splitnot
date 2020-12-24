# splitnot-api
Notifications pushed to you when a transaction is added to your bank account.

This is a Java based REST API which exposes a few API endpoints to be consumed by the splitnot frontend app.

It uses plaid(https://plaid.com/) to interface with your bank account.

Basic overview of Phase 1 can be found here - [part1](https://gist.githubusercontent.com/ufdeveloper/6a676da5169c3009d896e3246c905628/raw/a01f7ea6ad351d98a548fb72db7684562077b326/flow1.jpg)
[part2](https://gist.githubusercontent.com/ufdeveloper/6a676da5169c3009d896e3246c905628/raw/a01f7ea6ad351d98a548fb72db7684562077b326/flow2.jpg)


The project is planned to be done in 2 phases,

Phase 1 - Use a ReactJS webapp to display credit card transaction notifactions and add bills to splitwise.

Phase 2 - Create android/iOS apps intead of a webapp for a more seamless experience.
