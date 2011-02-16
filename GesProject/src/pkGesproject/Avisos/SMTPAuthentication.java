package pkGesproject.Avisos;

import javax.mail.PasswordAuthentication;

class SMTPAuthentication extends javax.mail.Authenticator
{

    public PasswordAuthentication getPasswordAuthentication()
    {

        String username = "muweb";

        String password = "muweb";

        return new PasswordAuthentication(username, password);

    }

}