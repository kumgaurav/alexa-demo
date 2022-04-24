package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.alexademo.lambda.custom.util.IspUtil;
import com.amazon.ask.alexademo.lambda.custom.util.SkillData;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;

public class OverdueSingaporeIntentHandler implements IntentRequestHandler {

	private static final Logger log = LoggerFactory.getLogger(ProductCountryIntentHandler.class);
	@Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("OverdueSingaporeIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	String speechText;
        String repromptText = IspUtil.getRandomObject(SkillData.PRODUCT_STRINGS);
        speechText = String.format("There are 8 overdue orders for Tri Leaflet in Singapore.");
        return handlerInput.getResponseBuilder()
                .withSimpleCard("BookingSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
