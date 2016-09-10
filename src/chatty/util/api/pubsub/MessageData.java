
package chatty.util.api.pubsub;

import java.util.Map;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

/**
 * Data of a message. Different subclasses contain topic specific data.
 * 
 * @author tduva
 */
public class MessageData {
    
    public final String topic;
    public final String message;
    
    public MessageData(String topic, String message) {
        this.topic = topic;
        this.message = message;
    }
    
    public static MessageData decode(JSONObject data, Map<Long, String> userIds) throws ParseException {
        if (data == null) {
            return null;
        }
        String topic = (String)data.get("topic");
        String message = (String)data.get("message");
        if (topic.startsWith("chat_moderator_actions")) {
            return ModeratorActionData.decode(topic, message, userIds);
        }
        return new MessageData(topic, message);
    }
    
}
