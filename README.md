Personal Finance Tracker
As a squad, our mission is to collaboratively design and implement a Personal Finance Tracker application, emphasizing the proper application of Object-Oriented Programming (OOP) principles and adherence to the SOLID design principles, along with correct use of Git Flow throughout the development process.

General Guidelines:
- The application is a console-based (CLI) program—no graphical interface is required.
- This is a single-user system; there is no need for login or account management.

Functional Requirements:
- The user should be able to add, view, edit, and delete transactions (both income and expenses).
- Each transaction must be associated with a category, such as Food, Rent, Salary, etc.
- The system must provide a monthly summary and balance report, based on the date of each transaction (Hint: transactions must include a Date field to support this feature).

Technical Requirements:
- All logic-heavy functionalities must include check functions for unit testing.
- The Dependency Inversion Principle must be applied to ensure loosely-coupled code.
- In-memory storage is acceptable for this project (data will be lost upon program termination), as the storage layer is abstracted and easily replaceable due to proper inversion of dependencies.
- Each Git commit must include a meaningful description.
- All new features must be developed in dedicated feature branches, with pull requests opened to the develop branch for review upon completion.

Extras:

- Junior-Level Extra Task: Implement an alternative storage mechanism that persists data to a text file using serialization. This allows users to retain their transaction data across sessions.

- Senior-Level Extra Task (for mentors or experienced mentees): Create a custom GitHub Action that automatically runs all check functions upon each pull request. The outcome of these checks should be visible directly in the PR page, helping maintain code quality and consistency.
