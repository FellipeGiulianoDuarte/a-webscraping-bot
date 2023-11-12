package com.bcs.webscraping;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    String userEmail;

    List<Product> selectedProducts;

    public EmailSender() {
    }

    public void setSelectedProducts(List<Product> selectedProducts){
        this.selectedProducts = selectedProducts;

    }

    public void setEmailRecipient(String userEmail) {
        this.userEmail = userEmail;
    }

    public void emailProductDetails() {
        // Assuming you have a method to format the email content
        String emailContent = formatEmailContent();

        String senderEmail = "youremail@gmail.com";
        String senderPassword = "yourpassword";
        String host = "smtp.gmail.com";

        // Set the properties for the JavaMail session
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Create a Session object with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        });

        try {
            // Create a MimeMessage object
            MimeMessage message = new MimeMessage(session);

            // Set the sender and recipient addresses
            message.setFrom(new InternetAddress(senderEmail));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.userEmail));

            // Set the email subject and content
            message.setSubject("Product Details");
            message.setText(emailContent);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully to " + this.userEmail);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    String formatEmailContent() {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Product Details:\n\n");

        for (Product product : this.selectedProducts) {
            contentBuilder.append("Title: ").append(product.getTitle()).append("\n");
            contentBuilder.append("Price: ").append(product.getPrice()).append("\n\n");
        }

        return contentBuilder.toString();
    }
}