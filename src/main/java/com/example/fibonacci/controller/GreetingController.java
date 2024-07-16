package com.example.fibonacci.controller;

import com.example.fibonacci.domain.model.Serie;
import com.example.fibonacci.provider.SerieService;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

@Controller
public class GreetingController {
    @Autowired
    private JavaMailSender mailSender;
    private final SerieService serieService;

    public GreetingController(SerieService serieService) {
        this.serieService = serieService;
    }
    @GetMapping("/list")
    @ResponseBody
    public List<Serie> getAccounts() {
        return serieService.getSeries();
    }
    @PostMapping("/serie")
    @ResponseBody
    public ResponseEntity<String> greeting(
            @RequestParam(name = "hora", required = false) String hora) {

        if (hora != null) {

            //cdivide las horas minutos y segundos
            String[] timeParts = hora.split(":");
            String hours = timeParts[0];
            String minutes = timeParts[1];
            String seconds = timeParts[2];

            String x = minutes.substring(0, 1);  // Obtiene el primer dígito
            String y = minutes.substring(1, 2);

            String resultado = printDescendingFibonacciSeries(x, y, seconds);
            LocalTime currentTime = LocalTime.now();

            //gguarda la serie generada en base de datos con la hora actual

            Serie ser = new Serie();
            ser.setSerie(resultado);
            ser.setHoraActual(currentTime.toString());
            serieService.saveSerie(ser); //guardaddo

            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.connectiontimeout", "10000");
            props.put("mail.smtp.timeout", "20000");
            props.put("mail.smtp.quitwait", "false");
            props.put("mail.smtp.auth", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.debug", "true");
            String destinatario = "connievilla99@hotmail.com";
            Session session = Session.getInstance(props, null);
            try {
                sendEmail(destinatario, "Serie Generada", "La serie generada es: " + resultado + " a la hora: " + currentTime.toString());
            } catch (MessagingException e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error enviando email", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            return new ResponseEntity<>(resultado, HttpStatus.OK);


        }
     else {
        return ResponseEntity.badRequest().body("No time parameter found in the query string");
    }


    }
    private void sendEmail(String to, String subject, String text) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);

        mailSender.send(message);
    }
    public static String printDescendingFibonacciSeries(String x, String y, String repetitions) {
        List<Integer> fibonacciSeries = new ArrayList<>();

        int a = Integer.parseInt(x);
        int b = Integer.parseInt(y);
        int rep = Integer.parseInt(repetitions);

        fibonacciSeries.add(a);
        fibonacciSeries.add(b);

        for (int i = 2; i < rep + 2; i++) {
            int next = a + b;
            fibonacciSeries.add(next);
            a = b;
            b = next;
        }

        // Invertir cadena
        Collections.reverse(fibonacciSeries);

        // Contruir String
        StringBuilder result = new StringBuilder();
        for (Integer num : fibonacciSeries) {
            result.append(num).append(" ");
        }

        return result.toString();
    }



}
