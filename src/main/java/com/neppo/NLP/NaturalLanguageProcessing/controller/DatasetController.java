package com.neppo.NLP.NaturalLanguageProcessing.controller;


import com.neppo.NLP.NaturalLanguageProcessing.domain.Dataset;
import com.neppo.NLP.NaturalLanguageProcessing.service.Classify;
import com.neppo.NLP.NaturalLanguageProcessing.service.ServiceConfiguration;
import com.neppo.NLP.NaturalLanguageProcessing.service.TextNormalization;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;


@RestController
public class DatasetController {


    @PostMapping(value = "/configuration", produces = "application/json")
    public Dataset configuration(@RequestBody Dataset dataset) throws Exception {
        return ServiceConfiguration.initialize(dataset);
    }

    @PostMapping(value = "/talk", produces = "application/json")
    public String talk(@RequestBody HashMap<String, String> body) throws Exception {
        return Classify.predict(body.get("phrase")).toLowerCase();
    }
}
