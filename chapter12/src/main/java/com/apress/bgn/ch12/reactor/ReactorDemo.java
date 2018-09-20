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
package com.apress.bgn.ch12.reactor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Random;
import java.util.stream.Stream;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class ReactorDemo {
    private static final Logger log = LoggerFactory.getLogger(ReactorDemo.class);
    private static final Random random = new Random();


    public static void main(String... args) {
        String[] names = {"Joy", "John", "Anemona", "Takeshi"};
        Flux<String> namesFlux = Flux.fromArray(names);
        namesFlux.subscribe(new GenericSubscriber<>());
        log.info(" ----------------------- ");

        Flux<Integer> intFlux = Flux.empty();
        intFlux.subscribe(new GenericSubscriber<>());
        log.info(" ----------------------- ");

        String[] names2 = {"Hanna", "Eugen", "Anthony", "David"};
        Flux<String> names2Flux = Flux.fromArray(names2);
        Flux<String> combined = Flux.concat(namesFlux, names2Flux);
        combined.subscribe(new GenericSubscriber<>());

        Flux<String> dummyStr = Flux.just("one", "two", "three");
        Flux<Integer> dummyInt = Flux.just(1, 2, 3);

        Mono<Integer> one = Mono.just(1);

        Mono<String> empty = Mono.empty();
        log.info(" ----------------------- ");

        Flux<String> numbers = Flux.just("11", "22", "33");
        Flux<Long> periodFlux = Flux.interval(Duration.ofSeconds(2));
        Flux.zip(numbers, periodFlux).map(Tuple2::getT1).doOnNext(log::info);

        log.info(" ----------------------- ");
        Flux<Integer> infiniteFlux = Flux.fromStream(
                Stream.generate(() -> random.nextInt(150))
        );

        Flux<Long> delay = Flux.interval(Duration.ofSeconds(1));
        Flux<Integer> delayedInfiniteFlux = infiniteFlux.zipWith(delay, (s, l) -> s);

        delayedInfiniteFlux
                .filter(element -> (element >= 0 && element < 127))
                .map(item -> {
                    if (item % 2 == 0 && item >= 98 && item <= 122) {
                        item -= 32;
                    }
                    return item;
                })
                .map(element -> (char) element.intValue())
                .subscribe(new GenericSubscriber<>());


    }


}
