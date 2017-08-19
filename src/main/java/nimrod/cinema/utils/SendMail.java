package nimrod.cinema.utils;

import com.sun.deploy.util.StringUtils;
import nimrod.cinema.objects.*;

import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class SendMail {

       // public static void main(String[] args) {

    public void SendMail(PurchaseDetails purchase, MovieDetails movie,Screening screening){

            final String username = "nimpashcinema@gmail.com"; // enter your mail id
            final String password = "nimpashcinema123456789";// enter ur password

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");




        Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(username, password);
                        }
                    });

            try {

                Message message = new MimeMessage(session);
                message.setFrom(new InternetAddress("nimpashcinema@gmail.com")); //from  same email id
                message.setRecipients(Message.RecipientType.TO,
                        InternetAddress.parse(purchase.Email));//TO  whome u have to send mails that person id

                message.setSubject("NimPashCinema Order Summary ");
                message.setText("Dear " +  purchase.GivenName + "  "  +  purchase.LastName  +  "\n\n" +
                 "Your Order Number: "  +  purchase.Id  + "\n\n"  +
                 "Your Order Details: \n\n"   +  ConstructPurchaseSummery(movie,screening,purchase.Seats)  + "\n"  +
                  "Thank you for purchasing a tickets from NimPashCinema" + "\n\n"  +
                  " Enjoy the Screening ! "

                );

                Transport.send(message);

                System.out.println("Done");

            } catch (MessagingException e) {
                throw new RuntimeException(e);
            }
        }


    private String ConstructPurchaseSummery(MovieDetails movie, Screening scr, List<Seat> selectedSeats)
    {
        StringBuilder builder = new StringBuilder();
        builder.append("\t\t\t\t Movie name: ").append(movie.Name).append(" (" + movie.Duration + " minutes) ").append("\n\n").append("\t\t\t\t Hall ").append(scr.HallId)
        .append(" at ").append(scr.Time.toString(" dd/MM   HH:mm ")).append("\n\n").append("\t\t\t\t Seats: ");

        builder.append(selectedSeats
                .parallelStream()
                .map(seat -> "Row " +
                        seat.RowNumber +
                        " Seat " +
                        seat.SeatNumber)
                .collect(Collectors.joining(", ")));

        builder.append("\n\n").append("\t\t\t\t Amount: ").append(scr.Price  + "\n\n\n");
        return builder.toString();
    }




    }

