namespace Sprint4.Services.Email.EmailService;

using System;
using System.Net;
using System.Net.Mail;
using Sprint4.Services.Email.Interface.IEmailService;

public class EmailService : IEmailService{

    public void sendEmail(String email, String subject, String body, bool ishtml = false){

        MailMessage mail = new MailMessage();

        mail.From = new MailAddress("labenvioemail@gmail.com");
        mail.To.Add(email);
        mail.Subject = subject;
        mail.Body = body;
        mail.IsBodyHtml = ishtml;

        SmtpClient smtp = new SmtpClient("smtp.gmail.com", 587);
        
        smtp.Credentials = new NetworkCredential("labenvioemail@gmail.com", "oiuklmbzfkgoqtsw");
        smtp.EnableSsl = true;
        smtp.Send(mail);
         


    }
}