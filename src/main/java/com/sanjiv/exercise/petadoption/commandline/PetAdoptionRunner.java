package com.sanjiv.exercise.petadoption.commandline;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @author Sanjiv on 8/17/18.
 */
@Component
public class PetAdoptionRunner implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(PetAdoptionRunner.class);
    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("PetAdoption app started with option names : {}", args.getOptionNames());

        logger.info("Application started with command-line arguments: {}", Arrays.toString(args.getSourceArgs()));
        logger.info("NonOptionArgs: {}", args.getNonOptionArgs());
        logger.info("OptionNames: {}", args.getOptionNames());

        for (String name : args.getOptionNames()){
            logger.info("arg-" + name + "=" + args.getOptionValues(name));
        }
    }
}
