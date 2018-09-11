/*
Freeware License, some rights reserved

Copyright (c) 2018 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy 
of this software and associated documentation files (the "Software"), 
to work with the Software within the limits of freeware distribution and fair use. 
This includes the rights to use, copy, and modify the Software for personal use. 
Users are also allowed and encouraged to submit corrections and modifications 
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for 
commercial use in any way, or for a user's educational materials such as books 
or blog articles without prior permission from the copyright holder. 

The above copyright notice and this permission notice need to be included 
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.bgn.ch11.json;

import com.apress.bgn.ch11.xml.Singer;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class JsonSerializationDemo {
    private static final Logger log = LoggerFactory.getLogger(JsonSerializationDemo.class);

        public static void main(String... args) {
            LocalDate johnBd = LocalDate.of(1977, Month.OCTOBER, 16);
            Singer john = new Singer("John Mayer", 5.0, johnBd);

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("name", john.getName());
            jsonObject.put("rating", john.getRating());
            jsonObject.put("birthdate", john.getBirthDate().toString());
            String jsonData = jsonObject.toString(2);
            log.info("--> Serialized {}", jsonData);

            JSONObject readJson = new JSONObject(jsonData);
            Singer copyOfJohn = new Singer((String) readJson.get("name"),
                    Double.parseDouble(((Integer)readJson.get("rating")).toString()),
                    LocalDate.parse((String)readJson.get("birthdate"), DateTimeFormatter.ISO_LOCAL_DATE));
            log.info("Are objects equal? {}", copyOfJohn.equals(john));
            log.info("--> Deserialized {}", copyOfJohn);
        }
}
