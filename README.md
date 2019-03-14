### Required
First Install Node.js
[Node.js](https://nodejs.org/en/download/) and NPM

# Frontend

### INSTALL
Open folder frontend and run
```
  $ npm install
  $ npm start
```

# Backend USER

### POSTS
| Methods  | Route                        | Description                  |
| -------- | ---------------------------- | ---------------------------- |
|   GET    | post/test                    | Test Post Route              |
|   GET    | post/:id                     | Get posts                    |
|   POST   | post/                        | Test Post Route              |
|   POST   | post/:id                     | Get post by id               |
|   POST   | post/like/:id                | Like a post                  |
|   POST   | post/unlike/:id              | Unlike a post                |
|   POST   | post/comment/:id             | Give your commet to a post   |
|   DELETE | post/:id                     | Delete a Post                |
|   DELETE | post/comment/:id/:comment_id | Remove the comment in a post |

### PROFILE
| Methods  | Route                        | Description                            |
| -------- | ---------------------------- | -------------------------------------- |
|   GET    | profile/test                 | Test Profile Route                     |
|   GET    | profile/                     | Get Current user profile               |
|   GET    | profile/all                  | Get all profiles                       |
|   GET    | profile/handle/:handle       | Get profile by Handle                  |
|   GET    | profile//user/:user_id       | Get probile by User ID                 |
|   POST   | profile/                     | Create or Edit user profile            |
|   POST   | profile/experience           | Add experience to profile              |
|   POST   | profile/education            | Add education to profile               |
|   DELETE | profile/                     | Remove my user profile                 |
|   DELETE | profile/experience/:exp_id   | Remove an experience from user profile |
|   DELETE | profile/education/:exp_id    | Remove an education from user profile  |

### USER
| Methods  | Route          | Description       |
| -------- | -------------- | ----------------- |
|   GET    | user/test      | Test User Route   |
|   GET    | user/current   | Get Current user  |
|   POST   | user/register  | Create new user   |
|   POST   | user/login     | Login user        |



### INSTALL
Open folder backend-user and run

```
  $ npm install
```

Next need config .env and config/keys-dev.js (With you don't have this file create), copy and paste .env.example and rename to .env.dev.
In .env.dev set MONGO_URI (URL your database) and SECRET_OR_KEY (To JWT key)

```
  $ npm start
```

# Backend SCM
Open folder backend-scm and run

```
  $ npm install
```

Next you need create a OAuth application to github