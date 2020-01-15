package si.kuharskimojster.api.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import si.kuharskimojster.api.model.ResponseModel;

@RestController
public class HealthCheckController {

    @GetMapping("/")
    public ResponseEntity<ResponseModel> getHealth() {
        return new ResponseEntity<>(new ResponseModel("Recommendation app is running ...", HttpStatus.OK.value()), HttpStatus.OK);
    }
}
