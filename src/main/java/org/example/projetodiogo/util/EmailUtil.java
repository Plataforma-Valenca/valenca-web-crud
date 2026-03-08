package org.example.projetodiogo.util;
import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EmailUtil {

    public static void enviarEmail(String destino, String assunto, String mensagemTexto)
            throws MessagingException {

        final String remetente = System.getenv("EMAIL_GOOGLE");
        final String senha = System.getenv("SENHA_GOOGLE");

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remetente, senha);
            }
        });

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(remetente));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(destino));
        message.setSubject(assunto);
        message.setText(mensagemTexto);

        Transport.send(message);
    }
}