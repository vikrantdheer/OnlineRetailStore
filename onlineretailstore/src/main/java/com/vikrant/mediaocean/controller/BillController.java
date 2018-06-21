package com.vikrant.mediaocean.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(description = "Bills")
public class BillController {

    private final Logger logger = LoggerFactory.getLogger(BillController.class);

}
