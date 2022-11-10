namespace Sprint4.Services.Email.Interface.IEmailService;

public interface IEmailService{

    public void sendEmail(String email, String subject, String body, bool ishtml = false);
}