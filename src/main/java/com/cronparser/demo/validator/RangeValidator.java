package com.cronparser.demo.validator;

import com.cronparser.demo.model.Constants;
import com.cronparser.demo.model.ExpressionType;

import java.util.HashMap;
import java.util.Map;


public class RangeValidator {
    public static class Range{
        int minValue;
        int maxValue;
        Range(int minValue,int maxValue){
            this.minValue=minValue;
            this.maxValue=maxValue;
        }

        public int getMinValue() {
            return minValue;
        }

        public int getMaxValue() {
            return maxValue;
        }
    }
    public static Map<ExpressionType,Range> rangeMap;
    static{
        rangeMap = new HashMap<>();
        rangeMap.put(ExpressionType.MINUTE,new Range(Constants.MIN_MINUTE,Constants.MAX_MINUTE));
        rangeMap.put(ExpressionType.HOUR,new Range(Constants.MIN_HOUR,Constants.MAX_HOUR));
        rangeMap.put(ExpressionType.DAY_OF_MONTH,new Range(Constants.MIN_DAY_OF_MONTH,Constants.MAX_DAY_OF_MONTH));
        rangeMap.put(ExpressionType.MONTH,new Range(Constants.MIN_MONTH,Constants.MAX_MONTH));
        rangeMap.put(ExpressionType.DAY_OF_WEEK,new Range(Constants.MIN_DAY_OF_WEEK,Constants.MAX_DAY_OF_WEEK));
    }

    /**
     * Validate Range for value based on the ExpressionType
     *
     * @param value
     * @param type
     * @return
     */
    public static boolean validateRange(int value, ExpressionType type){
        Range range = rangeMap.get(type);
        if(range==null){
            return true;
        }
        return value>=range.minValue&&value<= range.maxValue;
    }
}
