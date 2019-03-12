package ir.mostashar.model.userFeedback.controller;


import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.BaseDTO;
import ir.mostashar.model.userFeedback.service.UserFeedBackService;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/userfeedbacks")
public class UserFeedBackController {

    @Autowired
    UserFeedBackService ufbService;

    /**
     * Add FeedBack client From Lawyer By Client
     *
     * @param clientId
     * @param description
     * @param score
     * @return
     */
    @ApiOperation(value = "Create User FeedBack")
    @PostMapping(value = "/create", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> createFeedBack(@RequestParam("clientid") String clientId, @RequestParam("description") String description, @RequestParam("score") int score) {
        ufbService.create(clientId, description, score);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseDTO(HttpStatus.OK.value(), Constants.KEY_SUCESSE));
    }
}
