package com.example.smartcarddialog;

import android.util.Log;

import java.util.HashMap;

/**
 * Created by gradydun on 2019/8/27.
 */

public class Log2 {

    public enum LogLevels {
        VERBOSE(android.util.Log.VERBOSE), DEBUG(android.util.Log.DEBUG), INFO(android.util.Log.INFO), WARN(
                android.util.Log.WARN), ERROR(android.util.Log.ERROR), ASEERT(android.util.Log.ASSERT);

        int level;

        private LogLevels(int logLevel) {
            level = logLevel;
        }

        public int getLevel() {
            return level;
        }
    };

    static private HashMap<String, Integer> logLevels = new HashMap<String, Integer>();

    public static void setLogLevel(String tag, LogLevels level) {
        logLevels.put(tag, level.getLevel());
    }

    public static int v(String tag, String msg) {
        return Log2.v(tag, msg, null);
    }

    public static int v(String tag, String msg, Throwable tr) {
        if (logLevels.containsKey(tag)) {
            if (logLevels.get(tag) > android.util.Log.VERBOSE) {
                return -1;
            }
        }
        return Log.v(tag, msg, tr);
    }

    public static int d(String tag, String msg) {
        return Log2.d(tag, msg, null);
    }

    public static int d(String tag, String msg, Throwable tr) {
        if (logLevels.containsKey(tag)) {
            if (logLevels.get(tag) > android.util.Log.DEBUG) {
                return -1;
            }
        }
        return Log.d(tag, msg);
    }

    public static int i(String tag, String msg) {
        return Log2.i(tag, msg, null);
    }

    public static int i(String tag, String msg, Throwable tr) {
        if (logLevels.containsKey(tag)) {
            if (logLevels.get(tag) > android.util.Log.INFO) {
                return -1;
            }
        }
        return Log.i(tag, msg);
    }

    public static int w(String tag, String msg) {
        return Log2.w(tag, msg, null);
    }

    public static int w(String tag, String msg, Throwable tr) {
        if (logLevels.containsKey(tag)) {
            if (logLevels.get(tag) > android.util.Log.WARN) {
                return -1;
            }
        }
        return Log.w(tag, msg, tr);
    }

    public static int e(String tag, String msg) {
        return Log2.e(tag, msg, null);
    }

    public static int e(String tag, String msg, Throwable tr) {
        if (logLevels.containsKey(tag)) {
            if (logLevels.get(tag) > android.util.Log.ERROR) {
                return -1;
            }
        }
        return Log.e(tag, msg, tr);
    }

}