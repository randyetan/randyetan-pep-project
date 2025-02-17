package Controller;

import Service.AccountService;
import Service.MessageService;
import io.javalin.Javalin;
import io.javalin.http.Context;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;

    public SocialMediaController() {
        this.accountService = new AccountService(); 
        this.messageService = new MessageService(); 
    }

    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */
    public Javalin startAPI() {

        /* example from mini project
        Javalin app = Javalin.create();
        app.get("/books", this::getAllBooksHandler);
        app.post("/books", this::postBookHandler);
        app.get("/authors", this::getAllAuthorsHandler);
        app.post("/authors", this::postAuthorHandler);
        app.get("/books/available", this::getAvailableBooksHandler);
        app.start(8080); 


        additional info: 
        Login
        As a user, I should be able to verify my login on the endpoint POST localhost:8080/login. The request body will contain a JSON representation of an Account, not containing an account_id. 
        In the future, this action may generate a Session token to allow the user to securely use the site. We will not worry about this for now.

        The login will be successful if and only if the username and password provided in the request body JSON match a real account existing on the database. 
        If successful, the response body should contain a JSON of the account in the response body, including its account_id. The response status should be 200 OK, which is the default.

        If the login is not successful, the response status should be 401. (Unauthorized)
        */

        
        Javalin app = Javalin.create();
        // app.get("example-endpoint", this::exampleHandler);

        // create a new Account on the endpoint POST localhost:8080/register
        app.post("/register", this::registrationHandler);
        // verify my login on the endpoint POST localhost:8080/login
        app.post("/login", this::loginHandler);
        // submit a new post on the endpoint POST localhost:8080/messages
        app.post("/messages", this::createMessageHandler);
        // submit a GET request on the endpoint GET localhost:8080/messages
        app.get("/messages", this::getAllMessagesHandler);
        // submit a GET request on the endpoint GET localhost:8080/messages/{message_id}
        app.get("/messages/{message_id}", this::getMessageByIdHandler);
        // submit a DELETE request on the endpoint DELETE localhost:8080/messages/{message_id}
        app.delete("/messages/{message_id}", this::deleteMessageByIdHandler);
        // submit a PATCH request on the endpoint PATCH localhost:8080/messages/{message_id}
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        // submit a GET request on the endpoint GET localhost:8080/accounts/{account_id}/messages
        app.get("/accounts/{account_id}/messages", this::getMessagesByUserIdHandler);
        return app;
    }

    // /**
    //  * This is an example handler for an example endpoint.
    //  * @param context The Javalin Context object manages information about both the HTTP request and response.
    //  */
    // private void exampleHandler(Context context) {
    //     context.json("sample text");
    // }


}