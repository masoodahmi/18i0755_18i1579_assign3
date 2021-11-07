package com.masoodahmad.i180755_i181579;

import android.net.Uri;
import android.provider.BaseColumns;

public class Database {
    public static String DB_NAME = "myDB.db";
    public static int DB_VERSION= 1;

    public static class users implements BaseColumns{
        public static String tablename="users";
        public static String name="name";
        public static String gender="gender";
        public static String bio="bio";
        public static String _EMAIL="emailLLLL";
        public static String _PASSWORD="password";
        public static String pic = "pic";




    }

    public static class user_chat implements BaseColumns {
        public static String tablename = "user_chat";
        public static String name = "name";
        public static String text = "text";
        public static String pic = "pic";
        public static String time = "time";
        public static String currentuser="currentuser";

    }



    public static class chatss implements BaseColumns {
        public static String tablename = "chatss";
        public static String text = "text";
        public static String time = "time";
        public static String userid="userid";

    }


    public static class calllog implements BaseColumns {
        public static String tablename = "calllog";
        public static String arrow = "arrow";
        public static String userid="userid";
        public static String time = "time";
        public static String currentuser="currentuser";



    }



    }
