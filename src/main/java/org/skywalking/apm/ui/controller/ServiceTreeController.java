package org.skywalking.apm.ui.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.skywalking.apm.ui.service.ServiceTreeService;
import org.skywalking.apm.ui.web.ControllerBase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengys5
 */
@RestController
public class ServiceTreeController extends ControllerBase {

    private Logger logger = LogManager.getFormatterLogger(ServiceTreeController.class);

    @Autowired
    private ServiceTreeService service;

    @GetMapping("/service/tree")
    public void load(@ModelAttribute("entryServiceId") int entryServiceId,
        @ModelAttribute("timeBucketType") String timeBucketType,
        @ModelAttribute("startTime") long startTime, @ModelAttribute("endTime") long endTime,
        HttpServletResponse response) throws IOException {

        logger.info("load service tree, entryServiceId: %s, timeBucketType: %s, startTime: %s, endTime: %s", entryServiceId, timeBucketType, startTime, endTime);
        reply(service.load(entryServiceId, startTime, endTime).toString(), response);
    }
}
