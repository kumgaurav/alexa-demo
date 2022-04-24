package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.alexademo.lambda.custom.util.AlexaConstants;
import com.amazon.ask.alexademo.lambda.custom.util.IspUtil;
import com.amazon.ask.alexademo.lambda.custom.util.SkillData;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class ProductCountryIntentHandler implements IntentRequestHandler {
	private static final Logger log = LoggerFactory.getLogger(ProductCountryIntentHandler.class);
	@Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("ProductCountryIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Random random = new Random();
    	String speechText;
        String repromptText = IspUtil.getRandomObject(SkillData.PRODUCT_STRINGS);
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        log.info("slots :"+slots);
        Slot favoriteTimeSlot = slots.get(AlexaConstants.SLOT_DATE);
        log.info("favoriteTimeSlot :"+favoriteTimeSlot);
        if(favoriteTimeSlot != null && favoriteTimeSlot.getValue() != null ){
        	 String favoriteTime = favoriteTimeSlot.getValue();
             log.info("Product favoriteTime :"+favoriteTime);
             speechText = String.format("ITALY. They sold 2602264 units. Their net sales were $105402486.31");
        }else {
            // Render an error since user input is out of list of color defined in interaction model.
            speechText = "Please provide a valid time to hear more.";
            repromptText = IspUtil.getRandomObject(SkillData.YES_NO_STRINGS);
        }

        return handlerInput.getResponseBuilder()
                .withSimpleCard("BookingSession", speechText)
                .withSpeech(speechText)
                .withReprompt(repromptText)
                .withShouldEndSession(false)
                .build();
    }
}
