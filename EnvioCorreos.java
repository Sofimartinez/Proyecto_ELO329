import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioCorreos extends javax.swing.JFrame {

    private static String emailFrom = "sofiamartineza45@gmail.com";
    private static String passwordFrom = "kpsblyvfayongeyy";
    private String subject;
    private String content;
    private Properties mProperties;
    private Session mSession;
    private MimeMessage mCorreo;

    public EnvioCorreos() {
        mProperties = new Properties();
    }

    public void createEmail(String emailFrom,String recipents, String content, String subject) {
        this.emailFrom = emailFrom;
        this.subject = subject;
        this.content = content;

        //Convierte string de emails de destino a un array de InternetAddress
        String[] recipientList = recipents.split(",");
        InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
        for(int i=0; i<recipientList.length; i++){
            try {
                recipientAddress[i] = new InternetAddress(recipientList[i].trim());
            } catch (AddressException ex) {
                throw new RuntimeException(ex);
            }
        }

         // Simple mail transfer protocol
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user",emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");
        
        mSession = Session.getDefaultInstance(mProperties);

        try {
            mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.setRecipients(Message.RecipientType.TO, recipientAddress);
            mCorreo.setSubject(subject);
            mCorreo.setText(content);
            
        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
