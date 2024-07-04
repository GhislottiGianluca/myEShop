package com.example.myEShop.email;

/**
 * Interface for sending emails.
 * <p>
 * This interface defines the contract for sending emails, providing a method
 * to send an email to a specified recipient.
 * </p>
 */
public interface EmailSender {

    /**
     * Sends an email to the specified recipient.
     *
     * @param to the email address of the recipient
     * @param email the content of the email to be sent
     */
    void send(String to, String email);
}
