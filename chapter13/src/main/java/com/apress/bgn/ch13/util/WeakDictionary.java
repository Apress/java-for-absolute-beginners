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
package com.apress.bgn.ch13.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.ref.Cleaner;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class WeakDictionary {
    private static final Logger log = LoggerFactory.getLogger(WeakDictionary.class);
    private static WeakDictionary instance = new WeakDictionary();
    private static Cleaner cleaner;
    private WeakReference<Map<Integer, String>> dictionary;

    private WeakDictionary() {
        cleaner = Cleaner.create();
        dictionary = new WeakReference<>(initDictionary());
    }

    public synchronized String getExplanationFor(Integer key) {
        Map<Integer, String> dict = dictionary.get();
        if (dict == null) {
            dict = initDictionary();
            dictionary = new WeakReference<>(dict);
            return dict.get(key);
        } else {
            return dict.get(key);
        }
    }

    public WeakReference<Map<Integer, String>> getDictionary() {
        return dictionary;
    }

    public synchronized static WeakDictionary getInstance() {
        return instance;
    }

    private Map<Integer, String> initDictionary() {
        final Map<Integer, String> dict = new HashMap<>();
        log.info("Starting to create dictionary: {}", System.currentTimeMillis());
        final NameGenerator valGen = new NameGenerator(200);
        for (int i = 0; i < 100_000; ++i) {
            dict.put(i, valGen.genName());
        }
        log.info("Done creating dictionary: {}", System.currentTimeMillis());
        cleaner.register(dict, ()-> log.info("Cleaned up the dictionary!"));
        return dict;
    }
}
