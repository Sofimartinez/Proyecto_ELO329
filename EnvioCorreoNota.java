import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * @throws Clase que permite el envío de correos electrónicos para un remitente en particular.
 * @author Sofía Martínez
 */
public class EnvioCorreoNota extends javax.swing.JFrame {
    //Campos privados
    /**
     *   Correo electrónico desde el cual se enviará el correo como String
     */
    private static String emailFrom = "sofiamartineza45@gmail.com"; //Valor por defecto para poder enviar correos desde dirección real
    /**
     *   Contraseña para aplicaciones configurada previamente desde la cuenta que permite realizar el envío de los correos
     */
    private static String passwordFrom = "kpsblyvfayongeyy"; //contraseña necesaria para poder acceder a enviar correos
    /**
     *    Correo electrónico al cual se le enviará el correo como String
     */
    private static String emailTo;
    /**
     *    Asunto del correo
     */
    private String subject;
    /**
     *    Contenido del correo
     */
    private String content;
    /**
     *    Objeto Properties para configurar las características del correo que se enviará
     */
    private Properties mProperties;
    /**
     *    Objeto Session representa una sesión de correo
     */
    private Session mSession;
    /**
     *    Objeto MimeMessage representa un mensaje de correo electrónico de estilo MIME.
     */
    private MimeMessage mCorreo;
    /**
     * Constructor de EnvioCorreoNota, inicializa el envío de correos.
     */
    public EnvioCorreoNota() {
        mProperties = new Properties();
    }
    /**
     * Crea y configura las propiedades del correo electrónico
     * @param emailFrom correo del remitente.
     * @param emailTo correo del destinatario.
     * @param content contenido del correo.
     * @param subject asunto del correo.
     */
    public void createEmail(String emailFrom,String emailTo, String content, String subject) {
        this.emailFrom = emailFrom;
        this.emailTo = emailTo;
        this.subject = subject;
        this.content = content;

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
            mCorreo.setRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setText(content);
            
        } catch (AddressException ex) {
            Logger.getLogger(EnvioCorreoNota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreoNota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Envia el correo electrónico generado con el método createEmail
     */
    public void sendEmail() {
        try {
            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

        } catch (NoSuchProviderException ex) {
            Logger.getLogger(EnvioCorreoNota.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MessagingException ex) {
            Logger.getLogger(EnvioCorreoNota.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
