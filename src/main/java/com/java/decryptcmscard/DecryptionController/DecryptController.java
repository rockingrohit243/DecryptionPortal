package com.java.decryptcmscard.DecryptionController;

import com.java.decryptcmscard.Pojo.EncryptedCardNumberRequest;
import com.java.decryptcmscard.Service.DecryptServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DecryptController {
    @Autowired
    DecryptServiceImpl decryptService;
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String loadDecryptPortalPage()
    {
        return "DecryptPortal";
    }

    @RequestMapping(value = "decrypt",method = RequestMethod.GET)
    public String decryptCardNumber(@RequestParam("type") String type, @RequestParam("encrypted") String encryptedData, Model model) throws Exception {
        System.out.println("type" + type + "cardnumber" + encryptedData);
        String decryptedData;
        try {
            if (type.equalsIgnoreCase("OTP")) {
                decryptedData = decryptService.decryptEncryptedOTP(encryptedData);
            } else
                decryptedData = decryptService.decrypt(encryptedData);
            model.addAttribute("decryptedData", decryptedData);
            return "Success";
        }
        catch (Exception exception) {
            model.addAttribute("exception", exception.getMessage());
            return "Exception";
        }
    }

    @PostMapping("otp")
    public String decryptEncryptedOtp(@RequestBody String encryptedOtp) throws Exception {
        return decryptService.decryptEncryptedOTP(encryptedOtp);
    }

}
