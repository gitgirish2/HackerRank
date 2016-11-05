// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class PhoneBill {
    private static int sumCallDuration(HashMap<String, Integer> phoneBillMap) {
        int sum = 0;
        for (int phoneBill : phoneBillMap.values()) {
            sum += phoneBill;
        }
        return sum;
    }

    private static int calculateBill(int duration) {
        if (duration < 5 * 60) return 3 * duration;
        else {
            if (duration / 60 == 0) return (duration / 60) * 150;
            return (duration / 60 + 1) * 150;
        }
    }

    private static String getHighestDurationNums(HashMap<String, Integer> callDurationMap) {
        long max = Long.MIN_VALUE;
        String highestPhoneNum = null;
        List<String> highestDurationNums = new ArrayList<>();
        for (String phoneNum : callDurationMap.keySet()) {
            long callDuration = callDurationMap.get(phoneNum);
            if (callDuration >= max) {
                max = callDuration;
                highestPhoneNum = phoneNum;
            }
        }
        return highestPhoneNum;
    }

    private static int convertToSeconds(String s) {
        String[] units = s.split(":");
        int hours = Integer.parseInt(units[0]);
        int minutes = Integer.parseInt(units[1]);
        int seconds = Integer.parseInt(units[2]);

        return hours * 60 * 60 + minutes * 60 + seconds;
    }

    public int solution(String S) {
        String[] inp = S.split("\\r?\\n");
        HashMap<String, Integer> callDurationMap = new HashMap<>();
        for (String str : inp) {
            String[] durationNumPair = str.split(",");
            Integer currentDuration = callDurationMap.get(durationNumPair[1]);
            callDurationMap.put(durationNumPair[1], (currentDuration == null) ? convertToSeconds(durationNumPair[0]) : currentDuration + convertToSeconds(durationNumPair[0]));
        }

        HashMap<String, Integer> phoneBillMap = new HashMap<>();


        String highestDurationNum = getHighestDurationNums(callDurationMap);
        phoneBillMap.put(highestDurationNum, 0);

        for (String phoneNum : callDurationMap.keySet()) {
            if (phoneNum == highestDurationNum) continue;
            phoneBillMap.put(phoneNum, calculateBill(callDurationMap.get(phoneNum)));
        }

        return sumCallDuration(phoneBillMap);
    }
}