package com.refknowledgebase.refknowledgebase.buffer;

import android.net.Uri;

import com.refknowledgebase.refknowledgebase.model.Working_Hour_Model;

import java.util.List;

public class mBuffer {

    public static String Access_Type = "";
    public static String oAuth_token = "";
    public static String token_type = "";
    public static Uri user_imgUrl;
    public static String user_name = "";
    public static String fb_user_name = "";
    public static String fb_user_id = "";
    public static String fb_user_email = "";

    public static int SELECTED_CONTENT_id = 0;
    public static String SELECTED_CONTENT_question = "";
    public static String SELECTED_CONTENT_question_normalize = "";
    public static String SELECTED_CONTENT_answer = "";
    public static String SELECTED_CONTENT_status = "";
    public static int SELECTED_CONTENT_created_by = 0;
    public static String SELECTED_CONTENT_visible = "";
    public static int[] SELECTED_CONTENT_service_category_ids;
    public static int[] SELECTED_CONTENT_country_ids;
    public static int[] SELECTED_CONTENT_nationality_ids;
    public static String[] SELECTED_CONTENT_hashtags;

    public static int SELECTED_DIR_LIST_ID = 0;
//    selected directory info
    public static String Directory_Phone_number;
    public static String Directory_eMail;
    public static String Directory_site;
    public static String Directory_Landmark;
    public static String Directory_Metro;
    public static String Directory_PlusCode;
    public static String Directory_BuildingName;
    public static String Directory_City;
    public static String Directory_Country;

    public static List<Working_Hour_Model> workingHourModelList;
    public static String Directory_Hashtags;

    public static String Search_key = "a";

    public static String map_lat = "-34";
    public static String map_long = "151";
    public static String map_selected_country = "Country";
    public static int service_category_ids = 107;
    public static String service_category_name = "Assistance";
    public static int selectedItem = 0;
    public static String To_where = "Home";
    public static boolean is_search = false;
    public static String type_search = "faq";
    public static String selected_media = "";
    public static String selected_media_type = "";
    public static String selected_media_id = "";
    public static boolean isDirectMAP = true;
}