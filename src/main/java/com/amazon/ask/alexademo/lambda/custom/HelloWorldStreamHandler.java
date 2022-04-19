/**
 * 
 */
package com.amazon.ask.alexademo.lambda.custom;

import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazon.ask.alexademo.lambda.custom.handlers.CancelandStopIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.HelloWorldIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.HelpIntentHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.LaunchRequestHandler;
import com.amazon.ask.alexademo.lambda.custom.handlers.SessionEndedRequestHandler;

/**
 * @author gaurav
 *
 */
public class HelloWorldStreamHandler extends SkillStreamHandler {

    @SuppressWarnings("unchecked")
	private static Skill getSkill() {
        return Skills.standard()
                .addRequestHandlers(
                        new CancelandStopIntentHandler(),
                        new HelloWorldIntentHandler(),
                        new HelpIntentHandler(),
                        new LaunchRequestHandler(),
                        new SessionEndedRequestHandler())
                .build();
    }

    public HelloWorldStreamHandler() {
        super(getSkill());
    }

}