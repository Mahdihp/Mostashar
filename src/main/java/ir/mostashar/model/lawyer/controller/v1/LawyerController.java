package ir.mostashar.model.lawyer.controller.v1;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/lawyer")
public class LawyerController {


    @PostMapping(value = "/adddevice", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> addDevice(HttpServletRequest httpRequest) {

        return null;
    }

    public ResponseEntity<?> findAllLawyerOnline() {
        return null;

    }


}
