package com.example.thatsmyfirstapp.util;

import android.widget.EditText;

public class UtilHelper {

    public  static boolean ValidateData(String email, String password, EditText editText) {
        if (email.isEmpty()){
            editText.setError("Email is required!");
            return false;
        }
        if (password.isEmpty()){
            editText.setError("Password is required!");
            return false;
        }
        if (password.length() < 6) {
            editText.setError("Password is too short.");
            return false;
        }
        return true;
    }
}
