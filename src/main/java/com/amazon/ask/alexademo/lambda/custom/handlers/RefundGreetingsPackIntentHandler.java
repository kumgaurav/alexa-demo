package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.util.List;
import java.util.Optional;

import com.amazon.ask.alexademo.lambda.custom.util.IspUtil;
import com.amazon.ask.alexademo.lambda.custom.util.SkillData;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.services.monetization.InSkillProduct;
import com.amazon.ask.model.services.monetization.InSkillProductsResponse;
import com.amazon.ask.model.services.monetization.MonetizationServiceClient;
import com.amazon.ask.request.RequestHelper;

public class RefundGreetingsPackIntentHandler implements IntentRequestHandler {

    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("RefundGreetingsPackIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
        final RequestHelper requestHelper = RequestHelper.forHandlerInput(handlerInput);
        final String locale = requestHelper.getLocale();
        final MonetizationServiceClient client = handlerInput.getServiceClientFactory().getMonetizationService();
        final InSkillProductsResponse response = client.getInSkillProducts(locale, null, null, null, null, null);
        final List<InSkillProduct> products = response.getInSkillProducts();
        final Optional<InSkillProduct> inSkillProduct = IspUtil.getGreetingsPackProduct(products);
        if(inSkillProduct.isPresent()) {
            return handlerInput.getResponseBuilder()
                    .addDirective(IspUtil.getDirectiveByType(inSkillProduct.get().getProductId(), "Cancel"))
                    .build();
        }
        final String repromptText = IspUtil.getRandomObject(SkillData.YES_NO_STRINGS);
        return handlerInput.getResponseBuilder()
                .withSpeech(String.format("Sorry, no in-skill product found. Here's your simple greeting: %s. %s", IspUtil.getRandomObject(SkillData.HELLO_STRINGS), repromptText))
                .withReprompt(repromptText)
                .build();
    }

}
