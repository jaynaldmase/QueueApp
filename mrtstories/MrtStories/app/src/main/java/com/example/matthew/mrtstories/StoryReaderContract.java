package com.example.matthew.mrtstories;

import android.provider.BaseColumns;

/**
 * Created by matthew on 6/26/15.
 */
public class StoryReaderContract {
    public StoryReaderContract(){}

    public static abstract class StoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "story";
        public static final String COLUMN_NAME_ID = "id";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_BODY = "body";
        public static final String COLUMN_NAME_CREATED_ON = "created_on";
    }
}
