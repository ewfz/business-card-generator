package ewfz.project.controllers;

import ewfz.project.entities.User;
import ewfz.project.fileupload.FileUploadService;
import ewfz.project.qrcode.QRCodeServiceImpl;
import ewfz.project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UserRestController {

    @Autowired
    UserService userService;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private QRCodeServiceImpl qrCodeService;
    @CrossOrigin
    //request body est de type application/pdf
    @PostMapping(path = "/users", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getUserInfos(@RequestParam("file") MultipartFile file,@RequestPart("user") User user) throws Exception {
        //on télécharge l'image reçu dans 'request' et on renvoie le nom pour l'utiliser dans la template HTML
        String fileName = fileUploadService.uploadFile(file);
        System.out.println(fileName);
        System.out.println(user.getNom());
        // on génere l'image du qrcode en utilisant les informations de l'user et on la stocke
        // il y a aussi un path a cet image dans la template HTML utilisé pour la conversion pdf
        qrCodeService.generate(user);
        // user : les donner de l'utilisateur
        // fileName : le nom de l'image
        // on utilise ces deux paramétres pour remplir la template html et génerer le pdf
        byte[] pdf = userService.GeneratePDF(user,fileName);
        return pdf;

    }


}
