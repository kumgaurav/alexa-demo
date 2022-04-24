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

public class RevenueIntentHandler implements IntentRequestHandler {

	private static final Logger log = LoggerFactory.getLogger(RevenueIntentHandler.class);
    @Override
    public boolean canHandle(HandlerInput handlerInput, IntentRequest intentRequest) {
        return intentRequest.getIntent().getName().equals("RevenueIntent");
    }

    @Override
    public Optional<Response> handle(HandlerInput handlerInput, IntentRequest intentRequest) {
    	Random random = new Random();
    	String speechText;
        String repromptText = IspUtil.getRandomObject(SkillData.REVENUE_STRINGS);
        Intent intent = intentRequest.getIntent();
        Map<String, Slot> slots = intent.getSlots();
        log.info("slots :"+slots);
        Slot dateSlot = slots.get(AlexaConstants.SLOT_DATE);
        log.info("dateSlot :"+dateSlot);
        if(dateSlot != null && dateSlot.getValue() != null ){
        	 String favoriteTime = dateSlot.getValue();
             log.info("Revenue favoriteTime :"+favoriteTime);
             if("2022".equalsIgnoreCase(favoriteTime) ) {
            	 speechText = "Sorry Ameet !! that is insider information. there are lot of people here not on the insider trading list ";
             }else {
            	 String amount = String.format("%.3f", 5123864748.2809266);
            	 speechText = String.format("The combined net revenue for %s. is $%s ", favoriteTime , amount);
             }
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
