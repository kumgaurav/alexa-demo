package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.util.Optional;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.LaunchRequestHandler;
import com.amazon.ask.model.LaunchRequest;
import com.amazon.ask.model.Response;


public class LaunchHandler implements LaunchRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, LaunchRequest launchRequest) {
        return true;
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, LaunchRequest launchRequest) {
        final String speechText = "Analytics guru Activated !! Ask me questions .";
        return handlerInput.getResponseBuilder()
                .withSpeech(speechText)
                .withSimpleCard("Premium Hello World", speechText)
                .withReprompt(speechText)
                .build();
    }

}
