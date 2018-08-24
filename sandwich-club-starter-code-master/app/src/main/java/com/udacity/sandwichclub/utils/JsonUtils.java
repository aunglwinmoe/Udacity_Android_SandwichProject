package com.udacity.sandwichclub.utils;

import android.util.Log;
import com.udacity.sandwichclub.model.Sandwich;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String LOG_TAG = JsonUtils.class.getSimpleName();
    private static final String NAME = "name";
    private static final String MAIN_NAME = "mainName";
    private static final String ALSO_KNOWN_AS = "alsoKnownAs";
    private static final String PLACE_ORIGIN = "placeOfOrigin";
    private static final String DESCRIPTION = "description";
    private static final String IMAGE = "image";
    private static final String INGREDIENTS = "ingredients";

    public static Sandwich parseSandwichJson(String json) {
        JSONObject jsonObject;
        String mainName = null;
        String placeOfOrigin = null;
        String description = null;
        String image = null;
        List<String> ingredients = new ArrayList<>();
        List<String> alsoKnownAs = new ArrayList<>();
        try {
            jsonObject = new JSONObject(json);
            JSONObject jsonObjectName = jsonObject.getJSONObject(NAME);
            mainName = jsonObjectName.optString(MAIN_NAME);
            placeOfOrigin = jsonObject.optString(PLACE_ORIGIN);
            description = jsonObject.optString(DESCRIPTION);
            image = jsonObject.optString(IMAGE);
            alsoKnownAs = jsonList(jsonObjectName.getJSONArray(ALSO_KNOWN_AS));
            ingredients = jsonList(jsonObject.getJSONArray(INGREDIENTS));
        } catch (JSONException e) {
            Log.e(LOG_TAG, "JSONException at parseSandwichJson() ", e);
        }
        return new Sandwich(mainName, alsoKnownAs, placeOfOrigin, description, image, ingredients);
    }

    private static List<String> jsonList(JSONArray array) {
        List<String> list = new ArrayList<>();
        if (array.length() >0) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    list.add(array.getString(i));
                } catch (JSONException e) {
                    Log.e(LOG_TAG, "JSONException at jsonList() ", e);
                }
            }
        }
        return list;
    }
}

