# springboot-oauth2

Sample Authorization Server and Client with React UI.

## build

### This project contains 3 modules

- oauth-authorization-server - the Authorization Server (port = 8081)
- oauth-resource-server - the Resource Server (port = 8082)
- oauth-ui-authorization-code-react - React UI App (port = 3000)

You can run resource and auth server using command line:

  mvn spring-boot:run

In order to run the React Module we need to build the app first:
  
  mvn clean install

Then we need to navigate to our React app directory:

  cd src/main/resources

And run the command to download the dependencies:
  
  npm install

Finally, we will start our app:

  npm start

## future work

- Check token expiry date, delete token when expire
- Create logout handler, revoke  token on logout
