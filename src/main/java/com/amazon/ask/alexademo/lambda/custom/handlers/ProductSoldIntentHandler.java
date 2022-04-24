package com.amazon.ask.alexademo.lambda.custom.handlers;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazon.ask.alexademo.lambda.custom.util.AlexaConstants;
import com.amazon.ask.alexademo.lambda.custom.util.IspUtil;
import com.amazon.ask.alexademo.lambda.custom.util.SkillData;
import com.amazon.ask.alexademo.lambda.custom.util.SnowFlakeUtil;
import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.amazon.ask.dispatcher.request.handler.impl.IntentRequestHandler;
import com.amazon.ask.model.Intent;
import com.amazon.ask.model.IntentRequest;
import com.amazon.ask.model.Response;
import com.amazon.ask.model.Slot;

public class ProductSoldIntentHandler implements IntentRequestHandler {

	private static final Logger log = LoggerFactory.getLogger(ProductSoldIntentHandler.class);
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("ProductSoldIntent");
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
             double index = random.nextDouble()+1;
             String productName = "SAPIEN 3 ULTRA + COMMANDER";
             speechText = String.format(productName+", The Business Unit was TRANSCATHETER HEART VALVE. The number of units was 28397 and the gross sales was $922483000.000");
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
    
    public String getUnits(String org) {
    	String noOfUnits = "0";
    	String sql = "select sum(INV_NET_SALES_UNITS) AS number_of_units from \"EDW\".\"TAKEOFF\".\"S360_TRIM\" where YEAR = 2021 and EPAC_X7_DESC = '"+org+"';";
    	log.info("SQL -> "+sql);
    	try {
    		Connection connection = SnowFlakeUtil.getConnection();
    		Statement statement = connection.createStatement();
    		ResultSet resultSet = statement.executeQuery(sql);
    		while(resultSet.next()) {
    			noOfUnits = Long.toString(resultSet.getBigDecimal(1).longValue());
    		}
    		resultSet.close();
    		statement.close();
    		connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
    	return noOfUnits;
    }
}
