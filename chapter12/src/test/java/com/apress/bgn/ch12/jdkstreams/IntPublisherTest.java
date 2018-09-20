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
package com.apress.bgn.ch12.jdkstreams;

import org.reactivestreams.tck.TestEnvironment;
import org.reactivestreams.tck.flow.FlowPublisherVerification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Flow;

/**
 * @author Iuliana Cosmina
 * @since 1.0
 */
public class IntPublisherTest extends FlowPublisherVerification<Integer> {
    private static final Logger log = LoggerFactory.getLogger(FilterCharProcessor.class);

    public IntPublisherTest() {
        super(new TestEnvironment(300));
    }

    @Override
    public Flow.Publisher<Integer> createFlowPublisher(final long elements) {
        return new IntPublisher(30) {
            @Override
            public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
                intStream.forEach(subscriber::onNext);
                subscriber.onComplete();
            }
        };
    }

    @Override
    public Flow.Publisher<Integer> createFailedFlowPublisher() {
        return new IntPublisher(0) {
            @Override
            public void subscribe(Flow.Subscriber<? super Integer> subscriber) {
                subscriber.onError(new RuntimeException("There be dragons!"));
            }
        };
    }
}