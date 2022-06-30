package com.revature.mathtagon.problem;

import com.revature.mathtagon.problem.dtos.NewProblemRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@CrossOrigin({"http://localhost:4200/", "http://mathtagon-frontend-2.s3-website-us-east-1.amazonaws.com"})
@RestController
@RequestMapping("/problems")
public class ProblemServer {
    private static final Logger logger = Logger.getLogger(ProblemServer.class.getName());

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes = "application/json", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody ResponseEntity<List<String>> serve(@RequestBody NewProblemRequest req) {
        ProblemGenerator g = new ProblemGenerator(req.getMaxDigits(), req.getMaxOperations());
        List<String> probs = new ArrayList<>();

        for (int i = 0; i < req.getProblems(); i++) probs.add(g.generate().toString());

        return ResponseEntity.ok(probs);
    }
}
