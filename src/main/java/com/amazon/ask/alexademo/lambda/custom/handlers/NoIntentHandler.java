package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.util.Optional;

import com.amazon.ask.alexademo.lambda.custom.util.IspUtil;
import com.amazon.ask.alexademo.lambda.custom.util.SkillData;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

public class NoIntentHandler implements IntentRequestHandler {
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("AMAZON.NoIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        final String speechText = IspUtil.getRandomObject(SkillData.GOODBYE_STRINGS);
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .build();
    }

}
