# To-Do-V2 ![Status](https://img.shields.io/badge/Status-In%20Development-yellow) ![Completion](https://img.shields.io/badge/-Completed%20about%2075%25-green)

To Do application made in Spring Boot and React with Typescipt.

## About the application

The application allows you to save personalized tasks. Each task may have:

- Name
- Description
- Date
- Start time
- End time
- Importance
- Completion
- Subtasks (have a name and completion)
  <br/>
  The application has a built-in calendar that automatically visualizes added tasks.

## Things to do:

- Showing only today's tasks ("my day" section)
- Showing only important tasks ("important" section)
- Showing only completed tasks ("completed" section)
- Option to delete tasks
- Safe logout and login option (currently there is only a registration option)
- Improving the appearance of the registration form and fixing its responsiveness

## Running the application

1.  Clone the repository
2.  In the `/to-do-server.../resources` create new directory `certs` and generate security keys runing following commands: - `openssl genrsa -out keypair.pem 2048` - `openssl rsa -in keypair.pem -pubout -out public.pem` - `openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private.pem`
        After that for now you need to download and install any sql server and create a database called `to-do`. Start the server. Run application using Intellij Idea Community/Ultimate with `ToDoServerApplication.java` as a start file or by using `mvn spring-boot:run`.
    In the near future, I am going to add SQLite to the application and place the database in a file, which will facilitate the process of running the application.
    ​
3.  In the `/to-do-client` run `npm install` and then `npm run start`.

## Other problems or bugs

Notify me of them via `kamil.codemate@gmail.com` or create an issue.
