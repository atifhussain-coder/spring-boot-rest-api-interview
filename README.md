# Java Interview Assignment
Spring Boot application that will be used by our candidates to implement interview assignment.

## Dependencies
This project relies mainly on Spring Boot. Mainly:
  - Spring Data JPA
  - Spring Security
  - Spring Web
  
Full list of dependencies can be found in [pom.xml][1].

## Requirements:
  - Candidates expected to implement required features based on provided scenario
  - Each implementation should be equipped with unit tests
  - Integration tests are require to demonstrate API usages

### Scenario
The are two roles in the system; `LIBRARIAN` and `MEMBER`

#### As a Librarian
  - I can add, update, and remove Books from the system
  - I can add, update, view, and remove Member from the system
  
#### As a Member
  - I can view, borrow, and return available Books
  - Once a book is borrowed, its status will change to `BORROWED`
  - Once a book is returned, its status will change to `AVAILABLE`
  - I can delete my own account

## Nice to Have:
It will be an advantage for candidates to demonstrate the following:

  - proper usage of Http Methods and REST practices
  - understanding of [SOLID Principle][2]
  - understanding of Design patterns

Requirement:
    Java 17

[1]: pom.xml
[2]: https://en.wikipedia.org/wiki/SOLID
