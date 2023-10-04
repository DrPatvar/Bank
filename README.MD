    REST BANK Operation using Hibernate/Spring.
Stack
Spring Boot, Spring Data JPA, Hibernate, Lombok, H2, Spring Data REST

    Application is available at : Bank

    Technical requirement:

Design and implement a RESTful API, backing service and data model to create bank accounts
and transfer money between them. Interaction with API will be using HTTP requests.
Requirements
• Accounts are created by supplying a name and four-digit PIN code. Account number is automatically created.
• Once account is created one can Deposit, Withdraw or Transfer money between accounts.
• Any operation which deducts funds from the account needs to include the correct PIN code.
• A specific call will fetch all the accounts: the name and their current balance.
• APIs will use JSON payloads when applicable.
• Use in-memory database as a backing store.


    Recommendations
• Use any technologies you feel comfortable with, but it is recommended you set up
your project using Spring Boot.
• Make use of best practices when implementing your code to ensure a high-quality
result. Use dependency injection, avoid statics, immutable types, etc…
• However, keep it simple and to the point. Do not over-engineer. e.g. there is no need
to add authorization layers.

http://localhost:8080/api/explorer

    Authorized user parameters:

util/SecurityUtil

clientId = 1

passCode = 1234

curl samples

All the accounts: the name their current balance
curl -s http://localhost:8080/api/profile

All client accounts and their balance
curl -s http://localhost:8080/api/account


