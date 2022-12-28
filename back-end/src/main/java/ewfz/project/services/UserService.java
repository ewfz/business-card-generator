package ewfz.project.services;

import ewfz.project.entities.User;
import org.springframework.stereotype.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class UserService {

    public byte[] GeneratePDF(User user, String filename) throws Exception {
        Path pathHtml = null;
        Path pathPdf = null;
        try {
            // génerer un fichier html et pdf pour stocker les donner
            // on vas détruire ces fichiers ultérieurement
            String uuid = UUID.randomUUID().toString();
            pathHtml = Paths.get("/tmp/" + uuid + ".html");
            pathPdf = Paths.get("/tmp/" + uuid + ".pdf");

            // Date d'éxpiration de la carte ( aprés deux ans de la géneration du pdf)
            LocalDateTime ldt = LocalDateTime.now().plusYears(2);
            DateTimeFormatter formmat1 = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.ENGLISH);
            String formatter = formmat1.format(ldt);
            System.out.println(formatter);
            //le numéro ID est random
            Random rand = new Random();
            int upperbound = 10000;
            int int_random = rand.nextInt(upperbound);
            String num = Integer.toString(int_random);

            // changer les valeurs défini comme variables dans la template HTML qui contient
            // le layout de la carte visite
            String htmlContent = new Scanner(getClass().getClassLoader().getResourceAsStream("template.html"), "UTF-8")
                    .useDelimiter("\\A")
                    .next();
            htmlContent = htmlContent.replace("$prenom", user.getPrenom())
                    .replace("$nom", user.getNom())
                    .replace("$arnom",user.getNomArabe())
                    .replace("$prenom",user.getPrenom())
                    .replace("$arpre",user.getPrenomArabe())
                    .replace("$adresse",user.getAdresse())
                    .replace("$cin",user.getCIN())
                    .replace("$numero", num)
                    .replace("$currentdate",formatter)
                    .replace("$picture",filename);
                   Files.write(pathHtml, htmlContent.getBytes());

            // conversion du html au pdf avec l'outil wkhtmltopdf
            Process generateToPdf = Runtime.getRuntime().exec("./wkhtmltopdf -T 0 -B 0 -L 0 -R 0 --page-width 86mm  --page-height 54mm " + pathHtml.toString() + " " + pathPdf.toString() + "");
            generateToPdf.waitFor();

            return Files.readAllBytes(pathPdf);

        } finally {

            Files.delete(pathHtml);
            Files.delete(pathPdf);
        }
    }

    }

