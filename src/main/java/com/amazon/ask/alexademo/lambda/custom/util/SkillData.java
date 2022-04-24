/**
 * 
 */
package com.amazon.ask.alexademo.lambda.custom.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author gaurav
 *
 */
public class SkillData {

    public static final String [] HELLO_STRINGS = new String[] {"Howdy!", "Hello!", "How are you?", "Hiya!"};
    public static final String [] YES_NO_STRINGS = new String[] {"Would you like another information?", "Can I give you another information?", "Do you want to hear another information?"};
    public static final String [] BOOKING_STRINGS = new String[] {"What information you would like to know about booking ?", "Can I tell you todays booking?", "Do you want to hear another booking?"};
    public static final String [] REVENUE_STRINGS = new String[] {"What information you would like to know about revenue ?", "Can I tell you last year's earnings?"};
    public static final String [] PRODUCT_STRINGS = new String[] {"What information you would like to know about product ?"};
    public static final String [] LEARN_MORE_STRINGS = new String[] {"Want to learn more about it?", "Should I tell you more about it?", "Want to learn about it?", "Interested in learning more about it?"};
    public static final String [] GOODBYE_STRINGS = new String[] {"OK.  Goodbye!", "Have a great day!", "Come back again soon!"};

    public static final Map<String, String[]> VOICE_PERSONALITIES = new HashMap<String, String[]>() {{
        put("hindi", new String[]{"Aditi", "Raveena"});
        put("german", new String[]{"Hans", "Marlene", "Vicki"});
        put("spanish", new String[]{"Conchita", "Enrique"});
        put("french", new String[]{"Celine", "Lea", "Mathieu"});
        put("japanese", new String[]{"Mizuki", "Takumi"});
        put("italian", new String[]{"Carla", "Giorgio"});
    }};

    public static final Map<String, String> PREMIUM_GREETING_MAP = new HashMap<String, String>() {{
        put("hindi", "Namaste");
        put("french", "Bonjour");
        put("spanish", "Hola");
        put("japanese", "Konichiwa");
        put("italian", "Ciao");
    }};

}