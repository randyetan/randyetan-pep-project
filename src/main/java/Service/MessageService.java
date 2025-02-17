package Service;

import DAO.MessageDAO;
import Model.Message;

import java.util.List;

import DAO.AccountDAO;

public class MessageService {
    private MessageDAO messageDAO;
    private AccountDAO accountDAO;

    public MessageService() {
        messageDAO = new MessageDAO();
        accountDAO = new AccountDAO();
    }

    public Message createMessage(int posted_by, String message_text, int time_posted_epoch) {
        // Create New Message
        // As a user, I should be able to submit a new post on the endpoint POST localhost:8080/messages. 
        // The request body will contain a JSON representation of a message, which should be persisted to the 
        // database, but will not contain a message_id.

        // The creation of the message will be successful if and only if the message_text is not blank, 
        // is under 255 characters, and posted_by refers to a real, existing user. If successful, the response 
        // body should contain a JSON of the message, including its message_id. The response status should be 
        // 200, which is the default. The new message should be persisted to the database.

        // If the creation of the message is not successful, the response status should be 400. (Client error)

        if(message_text == null || message_text.length() > 255) {
            return null;
        }

        if(accountDAO.getAccountById(posted_by) == null) {
            return null;
        }

        return messageDAO.createMessage(posted_by, message_text, time_posted_epoch);
    }

    public List<Message> getAllMessages() {
        // Get All Messages
        // As a user, I should be able to submit a GET request on the endpoint GET localhost:8080/messages.

        // The response body should contain a JSON representation of a list containing all messages 
        // retrieved from the database. It is expected for the list to simply be empty if there are no 
        // messages. The response status should always be 200, which is the default.
        return messageDAO.getAllMessages();
    }

    public Message getMessageById(int message_id) {
        // Get One Message Given Message Id
        // As a user, I should be able to submit a GET request on the endpoint 
        // GET localhost:8080/messages/{message_id}.

        // The response body should contain a JSON representation of the message identified by the 
        // message_id. It is expected for the response body to simply be empty if there is no such message. 
        // The response status should always be 200, which is the default.
        return messageDAO.getMessageById(message_id);
    }

    public boolean deleteMessageById(int message_id) {
        // Delete a Message Given Message Id
        // As a User, I should be able to submit a DELETE request on the endpoint 
        // DELETE localhost:8080/messages/{message_id}.

        // The deletion of an existing message should remove an existing message from the database. 
        // If the message existed, the response body should contain the now-deleted message. 
        // The response status should be 200, which is the default.

        // If the message did not exist, the response status should be 200, but the response body should be 
        // empty. This is because the DELETE verb is intended to be idempotent, ie, multiple calls to the DELETE 
        // endpoint should respond with the same type of response.
        return messageDAO.deleteMessageById(message_id);
    }

    public boolean updateMessage(int message_id, String message_text) {
        // Update Message Given Message Id
        // As a user, I should be able to submit a PATCH request on the endpoint 
        // PATCH localhost:8080/messages/{message_id}. 
        // The request body should contain a new message_text values to replace the message 
        // identified by message_id. The request body can not be guaranteed to contain any other information.

        // The update of a message should be successful if and only if the message id already exists and the 
        // new message_text is not blank and is not over 255 characters. If the update is successful, the 
        // response body should contain the full updated message (including message_id, posted_by, message_text, 
        // and time_posted_epoch), and the response status should be 200, which is the default. 
        // he message existing on the database should have the updated message_text.

        // If the update of the message is not successful for any reason, 
        // the response status should be 400. (Client error)
        if(message_text == null || message_text == "" || message_text.length() > 255) {
            return false;
        }
        return messageDAO.updateMessage(message_id, message_text);
    }

    public List<Message> getMessagesByUserId(int account_id) {
        // Get All Messages From User Given Account Id
        // As a user, I should be able to submit a GET request on the endpoint 
        // GET localhost:8080/accounts/{account_id}/messages.

        // The response body should contain a JSON representation of a list containing all messages posted by a 
        // particular user, which is retrieved from the database. It is expected for the list to simply be empty 
        // if there are no messages. The response status should always be 200, which is the default
        return messageDAO.getMessagesByUserId(account_id);
    }
}