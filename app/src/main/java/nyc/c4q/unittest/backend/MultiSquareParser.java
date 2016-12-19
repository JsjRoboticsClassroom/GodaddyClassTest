package nyc.c4q.unittest.backend;


import org.json.JSONException;
import org.json.JSONObject;

public class MultiSquareParser {
    private static final String JSON_BOX1 = "box1";
    private static final String JSON_BOX2 = "box2";
    private static final String JSON_BOX3 = "box3";
    private static final String JSON_BOX4 = "box4";

    public static MultiSquareResponse parse(String data){
        try {
            JSONObject json = new JSONObject(data);
            String box1 = json.getString(JSON_BOX1);
            String box2 = json.getString(JSON_BOX2);
            String box3 = json.getString(JSON_BOX3);
            String box4 = json.getString(JSON_BOX4);
            return new MultiSquareResponse(box1, box2, box3, box4);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
