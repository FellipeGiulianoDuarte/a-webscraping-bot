//TODO: chatGPT fez essa BOMBA de código, revisar e testar isso

//TODO: montar um package

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.Properties;

public class EmailSender {
    private String userEmail;

    private List<Product> selectedProducts;

    public EmailSender() {
    }

    public void setSelectedProducts(List<Product> selectedProducts){
        this.selectedProducts = selectedProducts;

    }

    public void setEmailRecipient(String userEmail) {
        this.userEmail = userEmail;
    }

    public void emailProductDetails(String email) {
        // Assuming you have a method to format the email content
        String emailContent = formatEmailContent();

        //TODO: TIRAR A PORRA DO MEU EMAIL DAQUI
        String senderEmail = "";
        String senderPassword = "";
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
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(email));

            // Set the email subject and content
            message.setSubject("Product Details");
            message.setText(emailContent);

            // Send the message
            Transport.send(message);

            System.out.println("Email sent successfully to " + email);
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

    private String formatEmailContent() {
        StringBuilder contentBuilder = new StringBuilder();
        contentBuilder.append("Product Details:\n\n");

        for (Product product : selectedProducts) {
            contentBuilder.append("Title: ").append(product.getTitle()).append("\n");
            contentBuilder.append("Price: ").append(product.getPrice()).append("\n\n");
        }

        return contentBuilder.toString();
    }

/*    public static void main(String[] args) {
        // Create an instance of EmailSender
        EmailSender emailSender = new EmailSender();

        // Create a list of dummy products for testing
        List<Product> dummyProducts = new ArrayList<>();
        dummyProducts.add(new Product("Dummy Product 1", 29.99));
        dummyProducts.add(new Product("Dummy Product 2", 49.99));
        dummyProducts.add(new Product("Dummy Product 3", 19.99));
        emailSender.setSelectedProducts(dummyProducts);

        // Set the email recipient (replace with your email)
        emailSender.setEmailRecipient("fefellipe03@gmail.com");

        // Test the emailProductDetails method
        emailSender.emailProductDetails(emailSender.userEmail);

        System.out.println("Email sent successfully!");
    }*/
}