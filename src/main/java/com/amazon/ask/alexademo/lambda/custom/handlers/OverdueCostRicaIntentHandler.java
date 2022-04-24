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

public class OverdueCostRicaIntentHandler implements IntentRequestHandler {

	private static final Logger log = LoggerFactory.getLogger(OverdueCostRicaIntentHandler.class);
	@Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("OverdueCostRicaIntent");
    }

	@Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	String speechText;
        String repromptText = IspUtil.getRandomObject(SkillData.PRODUCT_STRINGS);
        speechText = String.format("There is 1 overdue work order in Costa Rica for the Item group STENT_SUB_ASSY_AOR_11500A.");
        return handlerInput.getResponseBuilder()
                .withSimpleCard("BookingSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }

}
