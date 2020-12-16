package ilyos.app.mvvm_hilt_example.utils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import ilyos.app.mvvm_hilt_example.repo.model.APIError;
import retrofit2.Response;

public class ErrorUtils {

    public static APIError parseError(Response<?> response) {
        APIError error = new APIError("", "");
        if (response.code() == 401) {
            return new APIError("Unauthorized", String.valueOf(response.code()));
        }
        if (response.code() == 417) {
            return new APIError("Expectation Failed", String.valueOf(response.code()));
        }
        if (response.code() == 404) {
            return new APIError("Not found", String.valueOf(response.code()));
        }
        if (response.code() == 426) {
            return new APIError("Upgrade Required", String.valueOf(response.code()));
        }
        try {
            if (response.errorBody() != null) {
                JSONObject jObjError = null;
                jObjError = new JSONObject(response.errorBody().string());
                JSONObject jsonObject = jObjError.getJSONObject("Error");
                String msg = null;
                if (jsonObject != null) {
                    msg = jsonObject.getString("Message");
                    error = new APIError(msg, jsonObject.getString("Code"));
                    if (error.getCode() != null && error.getCode().equals("ALREADY_EXISTS")) {
                        error.setMessage("This email has already been used, please log in with that email or use a different email to sign up");
                    }
                }
            }
            return error;
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

        return error;
    }

}
