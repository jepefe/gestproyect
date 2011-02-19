package pkGesproject.Avisos;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


class SendAuthentication{
	   static String asunto = "No-Reply-avisos";
	//Message msg = new Message;

    public static void Send(){

        String host ="127.0.0.1";//Suponiendo que el servidor SMTPsea la propia máquina
        String from ="freygil@msn.com";
        String to = "freygil@gmail.com";
     

        //System.out.println("aqui");
        System.out.println ("Prueba para envios mail...Avisos Gesproject. " + new java.util.Date());

        Properties prop = new Properties();

        /*Añadir esta linea si queremos ver una salida detallada del programa*/
        //prop.put("mail.debug", "true");
        
        /*prop.put("mail.smtp.host", host);
        *Esta línea es la que indica al API que debe autenticarse*
       prop.put("mail.smtp.auth", "true");*/
       
       prop.put("mail.smtp.host", host);
       /*Esta línea es la que indica al API que debe autenticarse*/
      prop.put("mail.smtp.auth","true");
       

        try{

            SMTPAuthentication auth = new SMTPAuthentication();
            Session session = Session.getInstance(prop , auth );
            Message par = getMessage(session, from, to);
            System.out.println ("Enviando ..." );

            Transport.send(par);
            System.out.println("Transport contiene:"+ par);

            System.out.println ("Mensaje enviado!");

        }

        catch (Exception e)
        {

            ExceptionManager.ManageException(e);

        }

    }

    private static MimeMessage getMessage(Session session, String from, String to)
    {

        try{

            MimeMessage msg = new MimeMessage(session);
            //System.out.println("Llego hasta aqui.");
            msg.setText("Este mail es una aviso enviado desde la aplicación Gesproject," +
            		" El presupuesto de XXXX se ha sobre pasado en YYYY.");
            System.out.println("El mesnaje contiene:");
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            msg.setFrom(new InternetAddress(from,"freygil@msn.com"));
            msg.setSubject(asunto);
            Transport.send(msg);
            
            return msg;

        }

        catch (java.io.UnsupportedEncodingException ex)
        {

            ExceptionManager.ManageException(ex);
            return null;

        }

        catch (MessagingException ex)
        {

            ExceptionManager.ManageException(ex);
            return null;

        } 

    }

}