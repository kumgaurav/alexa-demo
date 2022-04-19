/**
 * 
 */
package com.amazon.ask.alexademo.lambda.custom.handlers;

import static com.amazon.ask.request.Predicates.intentName;

import java.util.Optional;

import com.amazon.ask.alexademo.lambda.custom.util.IspUtil;
import com.amazon.ask.alexademo.lambda.custom.util.SkillData;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.RequestHandler;
import com.amazon.ask.model.Response;

/**
 * @author gaurav
 *
 */
public class CancelandStopIntentHandler implements RequestHandler {

    @Override
    public boolean canHandle(HandlerInput input) {
        return input.matches(intentName("AMAZON.StopIntent").or(intentName("AMAZON.CancelIntent")));
    }

    @Override
    public Optional<Response> handle(HandlerInput input) {
    	final String speechText = IspUtil.getRandomObject(SkillData.GOODBYE_STRINGS);
        return input.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("HelloWorld", "Goodbye")
                .withShouldEndSession(true)
                .build();
    }
    
}