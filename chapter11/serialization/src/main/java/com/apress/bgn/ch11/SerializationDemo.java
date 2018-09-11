package com.apress.bgn.ch11;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;

public class SerializationDemo {

    private static final Logger log = LoggerFactory.getLogger(SerializationDemo.class);
    
    public static void main(String... args) throws ClassNotFoundException {
        LocalDate johnBd = LocalDate.of(1977, Month.OCTOBER, 16);
        Singer john = new Singer("John Mayer", 5.0, johnBd);

        File file = new File("chapter11/serialization/src/main/resources/output/john.txt");
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(john);
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))){
            Singer copyOfJohn = (Singer) in.readObject();
            log.info("Are objects equal? {}", copyOfJohn.equals(john));
            log.info("--> {}", copyOfJohn.toString());
        } catch (IOException e) {
            log.info("Something went wrong! ", e);
        }
    }

}
