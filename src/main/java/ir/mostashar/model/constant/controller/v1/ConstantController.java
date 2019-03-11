package ir.mostashar.model.constant.controller.v1;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/9/19
 * Time: 10:46
 * http://github.com/ghaseminya
 */

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.constant.Constant;
import ir.mostashar.model.constant.service.ConstantService;
import ir.mostashar.model.request.dto.RequestDTO;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/constant")
public class ConstantController {


    @Autowired
    ConstantService constantService;


    @ApiOperation(value = "Get All Slide show Images", notes ="")
    @GetMapping(value = "/slideshow")
    public ResponseEntity<?> findAllslideshow() {
        Optional<Constant> allslideshow = constantService.findConstantBytype("slideshow");
        if (allslideshow.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(allslideshow.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }

    @ApiOperation(value = "Get gif image for wait request", notes ="")
    @GetMapping(value = "/waitimage")
    public ResponseEntity<?> findwaitimage() {
        Optional<Constant> waitimage = constantService.findConstantBytype("waitimage");
        if (waitimage.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(waitimage.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new RequestDTO(HttpStatus.NOT_FOUND.value(), Constants.KEY_NOT_FOUND_REQUEST));
    }
}
