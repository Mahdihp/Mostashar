package ir.mostashar.model.feedback.controller.v1;

import io.swagger.annotations.ApiOperation;
import ir.mostashar.model.feedback.dto.ListFeedBackDTO;
import ir.mostashar.model.feedback.service.FeedbackService;
import ir.mostashar.model.file.dto.ListFileDTO;
import ir.mostashar.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by mnm
 * User: mnm
 * Date: 3/14/19
 * Time: 11:16
 * http://github.com/ghaseminya
 */


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/feedback")
public class FeedBackController {
    @Autowired
    FeedbackService feedbackService;

    @ApiOperation(value = "Find All Feedbacks Client", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/request", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFileByClient(@RequestParam("clientid") String clientUid,
                                                 @RequestParam("requestid") String requestUid) {
        Optional<ListFeedBackDTO>  listFeedBackDTO= feedbackService.findByLawyerUidAndRequestUid(clientUid,requestUid);
        if (listFeedBackDTO.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(listFeedBackDTO.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ListFeedBackDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_FILE));
    }

    @ApiOperation(value = "Find All Feedbacks", notes = "RequestParam :" + MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @PostMapping(value = "/request", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity<?> findAllFeedBackFile(@RequestParam("requestid") String requestid) {
        Optional<ListFeedBackDTO> list = feedbackService.findAllDTO(2, requestid);
        if (list.isPresent())
            return ResponseEntity.status(HttpStatus.OK).body(list.get());
        else
            return ResponseEntity.status(HttpStatus.OK).body(new ListFileDTO(HttpStatus.OK.value(), Constants.KEY_NOT_FOUND_REQUEST));

    }
}
