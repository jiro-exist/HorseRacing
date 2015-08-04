package com.manio.horserace;

import java.util.Random;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Random;
import java.util.LinkedHashMap;
import java.util.Collections;

public class Utilities {
    
    //check if the data is a decimal
    public static boolean isNumeric(String str) {
        if (!"".equals(str) && str != null) {
            return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal. Must start with 0 if only decimal eg 0.1
        }
        else {
            return false;
        }
    }

    //check if the data is int
    public static boolean isInt(String str) {
        if (!"".equals(str) && str != null) {
            return str.matches("\\d*");  //match an int
        }
        else {
            return false;
        }
    }

}