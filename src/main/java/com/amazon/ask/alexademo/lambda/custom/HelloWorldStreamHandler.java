/**
 * 
 */
package com.amazon.ask.alexademo.lambda.custom;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.alexademo.lambda.custom.handlers.ProductSoldIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.OverdueSingaporeIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.BuyResponseHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.OverdueCostRicaIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.CancelandStopIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.CancelfromConnectionsHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.FallbackIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.GenericExceptionHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.GetAnotherHelloHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.HelloWorldIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.HelpIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.LaunchHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.LaunchRequestHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.NoIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.ProductCountryIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.RefundGreetingsPackIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.SessionEndedHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.SessionEndedRequestHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.BookingIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.RevenueIntentHandler;

/**
 * @author gaurav
 *
 */
public class HelloWorldStreamHandler extends SkillStreamHandler {

    @SuppressWarnings("unchecked")
	private static Skill getSkill() {
		/*
		 * return Skills.standard() .addRequestHandlers( new
		 * CancelandStopIntentHandler(), new HelloWorldIntentHandler(), new
		 * HelpIntentHandler(), new LaunchRequestHandler(), new
		 * SessionEndedRequestHandler()) .build();
		 */
        return Skills.standard()
                .addRequestHandlers(
                        new LaunchHandler(),
                        new BookingIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new GetAnotherHelloHandler(),
                        new NoIntentHandler(),
                        new BuyResponseHandler(),
                        new FallbackIntentHandler(),
                        new RevenueIntentHandler(),
                        new ProductSoldIntentHandler(),
                        new OverdueSingaporeIntentHandler(),
                        new ProductCountryIntentHandler(),
                        new RefundGreetingsPackIntentHandler(),
                        new OverdueCostRicaIntentHandler(),
                        new CancelfromConnectionsHandler(),
                        new HelpIntentHandler(),
                        //new LaunchRequestHandler(),
                        new CancelandStopIntentHandler(),
                        new SessionEndedHandler(),
                        new SessionEndedRequestHandler())
                .addExceptionHandlers(new GenericExceptionHandler())
                // Add your skill id below
                //.withSkillId("")
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}