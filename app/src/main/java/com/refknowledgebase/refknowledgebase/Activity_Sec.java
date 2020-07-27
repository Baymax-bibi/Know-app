package com.refknowledgebase.refknowledgebase;

import android.accounts.Account;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.refknowledgebase.refknowledgebase.adapter.CountryLVAdapter;
import com.refknowledgebase.refknowledgebase.adapter.LanguageLVAdapter;
import com.refknowledgebase.refknowledgebase.buffer.mBuffer;
import com.refknowledgebase.refknowledgebase.model.Country_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Country_Model;
import com.refknowledgebase.refknowledgebase.model.Language_BaseModel;
import com.refknowledgebase.refknowledgebase.model.Language_Model;
import com.refknowledgebase.refknowledgebase.model.oAuth_Model;
import com.refknowledgebase.refknowledgebase.utils.Constant;
import com.refknowledgebase.refknowledgebase.utils.GeocodingLocation;
import com.refknowledgebase.refknowledgebase.utils.Methods;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Activity_Sec extends AppCompatActivity implements View.OnClickListener {

    LinearLayout ly_google, ly_facebook, ly_without, ly_hello;
    RelativeLayout rl_sec_country, rl_sec_language, country_lv, rl_sec, lan_lv;
    TextView tv_sec_lanugage, tv_sec_country, tv_sec_country_lv, tv_sec_lan_lv;
    ImageView img_country_down, img_lan_down;
    LoginButton login_button;
    ListView lv_country, lv_language;
    Country_Model country_model;
    Language_Model language_model;
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 0;
    List<Language_BaseModel> languagelv;
    Uri personePhoto;
    String personeDisplayName, personEmail;
    private CallbackManager callbackManager;
    oAuth_Model oAuth_model;
    AccessTokenTracker accessTokenTracker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sec);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FacebookSdk.sdkInitialize(Activity_Sec.this);
        Methods.closeProgress();
        getCountryData();
        getLanguageData();
//        country list
        tv_sec_country_lv = findViewById(R.id.tv_sec_country_lv);
//        language list
        tv_sec_lan_lv = findViewById(R.id.tv_sec_lan_lv);
        tv_sec_country = findViewById(R.id.tv_sec_country);
        tv_sec_lanugage = findViewById(R.id.tv_sec_lanugage);
        ly_google = findViewById(R.id.ly_google);
        ly_without = findViewById(R.id.ly_without);
        img_country_down = findViewById(R.id.img_country_down);
        img_lan_down = findViewById(R.id.img_lan_down);
        lan_lv = findViewById(R.id.lan_lv);
        rl_sec = findViewById(R.id.rl_sec);
        rl_sec.setOnClickListener(this);
        country_lv = findViewById(R.id.country_lv);
        ly_hello = findViewById(R.id.ly_hello);
        ly_hello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        rl_sec_country = findViewById(R.id.rl_sec_country);
        rl_sec_language = findViewById(R.id.rl_sec_language);
        rl_sec_country.setOnClickListener(this);
        rl_sec_language.setOnClickListener(this);
        ly_google.setOnClickListener(this);
        ly_without.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        lv_country = findViewById(R.id.lv_country);
        lv_country.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView textView = (TextView) view.findViewById(R.id.tv_country_name);
                String selected_country = textView.getText().toString();
                country_lv.setVisibility(View.GONE);
                tv_sec_country.setText(selected_country);

                insertString("MAPCOUNTRY",selected_country);
                mBuffer.map_selected_country = selected_country;
                GeocodingLocation locationAddress = new GeocodingLocation();
                locationAddress.getAddressFromLocation(selected_country,
                        getApplicationContext(), new GeocoderHandler());
                insertString(Constant.SELECTED_COUNTRY, selected_country);
            }
        });

        lv_language = findViewById(R.id.lv_language);
        lv_language.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView textView = (TextView) view.findViewById(R.id.tv_lang_name);
                String selected_language = textView.getText().toString();
                lan_lv.setVisibility(View.GONE);
                tv_sec_lanugage.setText(selected_language);
                insertString(Constant.SELECTED_LANGUAGE, selected_language);
            }
        });
//Adding callback manager
        ly_facebook = findViewById(R.id.ly_facebook);
        ly_facebook.setOnClickListener(this);

        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code

//                startActivity(new Intent(Activity_Sec.this, Landing_three.class));
//                finish();
                Fragment fragment = new LandingFragment();
                loadFragment(fragment);
            }
            @Override
            public void onCancel() {
                // App code
            }
            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        login_button = findViewById(R.id.login_button);
        login_button.setReadPermissions("email");

        login_button.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                insertBoolean(Constant.AFTERLANG, true);
                insertString(Constant.LOGINTYPE, "FB");
                mBuffer.Access_Type = "FB";
                GraphLoginRequest(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(getApplicationContext(), R.string.cancel_login, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(getApplicationContext(), R.string.error_login, Toast.LENGTH_SHORT).show();
            }
        });
        // Detect user is login or not. If logout then clear the TextView and delete all the user info from TextView.
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken accessToken2) {
                if (accessToken2 == null){
                }
            }
        };

    }

    private void GraphLoginRequest(AccessToken currentAccessToken) {
        GraphRequest graphRequest = GraphRequest.newMeRequest(currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject jsonObject, GraphResponse graphResponse) {
            try{
                String fb_id = jsonObject.getString("id");
                String fb_name = jsonObject.getString("name");
                String fb_email = jsonObject.getString("email");

                mBuffer.fb_user_name = fb_name;
                mBuffer.fb_user_id = fb_id;
                mBuffer.fb_user_email = fb_email;

                insertString(Constant.LOGIN_EMAIL, fb_email);
                insertString(Constant.LOGIN_FB_NAME, fb_name);
                insertString(Constant.LOGIN_FB_PHOTO, fb_id);
                insertString(Constant.LOGINTYPE, "FB");

//                startActivity(new Intent(Activity_Sec.this, Landing_three.class));
//                finish();
                Fragment fragment = new LandingFragment();
                loadFragment(fragment);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        });

        Bundle bundle = new Bundle();
        bundle.putString(
                "fields",
                "id,name,email"
        );
        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();
    }

    private void setUpData() {
        List<Country_BaseModel> countrylv = new ArrayList<Country_BaseModel>(country_model.getEntities());
        lv_country.setAdapter(new CountryLVAdapter(Activity_Sec.this, countrylv));
        ListUtils.setDynamicHeight(lv_country);
    }
    private void setUpData_lang(){

        languagelv = new ArrayList<Language_BaseModel>(language_model.getEntities());
        lv_language.setAdapter(new LanguageLVAdapter(Activity_Sec.this, languagelv));
        ListUtils.setDynamicHeight(lv_language);
    }
    private void getCountryData() {
        RequestQueue queue = Volley.newRequestQueue(Activity_Sec.this);
        StringRequest sr = new StringRequest(Request.Method.GET,Constant.URL+Constant.COUNTRY, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                country_model = gson.fromJson(response, Country_Model.class);
                if (country_model != null){
                    setUpData();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("page","1");
                params.put("per_page","10");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
                return params;
            }
        };
        sr.setRetryPolicy(new DefaultRetryPolicy(
                5000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(sr);

    }
    private void getLanguageData(){
        RequestQueue queue = Volley.newRequestQueue(Activity_Sec.this);
        StringRequest sr = new StringRequest(Request.Method.GET,Constant.URL+Constant.API_LANGUAGE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                language_model = gson.fromJson(response, Language_Model.class);
                if (language_model != null){
                    setUpData_lang();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Language","Language failed" + error.toString());
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("page","1");
                params.put("per_page","10");

                return params;
            }

            @Override
            public Map<String, String> getHeaders() {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                params.put("Authorization", mBuffer.token_type + " " + mBuffer.oAuth_token);
                return params;
            }
        };
        sr.setRetryPolicy(new RetryPolicy() {
            @Override
            public int getCurrentTimeout() {
                return 5000;
            }

            @Override
            public int getCurrentRetryCount() {
                return 5000;
            }

            @Override
            public void retry(VolleyError error) {

            }
        });
        queue.add(sr);
    }

    @Override
    public void onClick(View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(Activity_Sec.this);
        switch (v.getId()){
            case R.id.ly_google:
                if (!(tv_sec_country.getText().equals(getResources().getString(R.string.spinner_country))) && !(tv_sec_lanugage.getText().equals(getResources().getString(R.string.spinner_language)))){
                    signIn();
                }else {
                    Snackbar.make(v, "Please select your country and language", Snackbar.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.ly_facebook:
                if (!(tv_sec_country.getText().equals(getResources().getString(R.string.spinner_country))) && !(tv_sec_lanugage.getText().equals(getResources().getString(R.string.spinner_language)))){
                    //        Checking the Access Token
                    if (AccessToken.getCurrentAccessToken() != null){
                        GraphLoginRequest(AccessToken.getCurrentAccessToken());
                    }else {
                    }
                    login_button.performClick();
                }else {
                    Snackbar.make(v, "Please select your country and language", Snackbar.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.ly_without:
                if (!(tv_sec_country.getText().equals(getResources().getString(R.string.spinner_country))) && !(tv_sec_lanugage.getText().equals(getResources().getString(R.string.spinner_language)))){
                    Fragment fragment = new LandingFragment();
                    loadFragment(fragment);
                    mBuffer.Access_Type = "WITHOUT";
                    insertBoolean(Constant.AFTERLANG, true);
                }else {
                    Snackbar.make(v, "Please select your country and language", Snackbar.LENGTH_LONG)
                            .show();
                }
                break;
            case R.id.rl_sec_country:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    rl_sec_language.setBackgroundDrawable(getDrawable(R.drawable.bg_radius_line));
                    rl_sec_country.setBackgroundDrawable(getDrawable(R.drawable.bg_radius_white));
                    tv_sec_country.setTextColor(getColor(R.color.txt_dark));
                    img_country_down.setColorFilter(getColor(R.color.txt_dark));
                    tv_sec_lanugage.setTextColor(getColor(R.color.white));
                    img_lan_down.setColorFilter(getColor(R.color.white));
                    country_lv.setVisibility(View.VISIBLE);
                    lan_lv.setVisibility(View.GONE);
                }
                break;
            case R.id.rl_sec_language:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    tv_sec_country.setTextColor(getColor(R.color.white));
                    img_country_down.setColorFilter(getColor(R.color.white));
                    tv_sec_lanugage.setTextColor(getColor(R.color.txt_dark));
                    img_lan_down.setColorFilter(getColor(R.color.txt_dark));
                }
                rl_sec_language.setBackgroundDrawable(getDrawable(R.drawable.bg_radius_white));
                rl_sec_country.setBackgroundDrawable(getDrawable(R.drawable.bg_radius_line));
                lan_lv.setVisibility(View.VISIBLE);
                country_lv.setVisibility(View.GONE);
                break;
            case R.id.tv_sec_country_lv:
                country_lv.setVisibility(View.GONE);
                break;
            case R.id.tv_sec_lan_lv:
                lan_lv.setVisibility(View.GONE);
                break;
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount acct = completedTask.getResult(ApiException.class);
            if (acct != null){

                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                personEmail = acct.getEmail();
                String personId = acct.getId();
                personePhoto = acct.getPhotoUrl();
                personeDisplayName = acct.getDisplayName();
                String personeIdToken = acct.getIdToken();
                Account personeAccount = acct.getAccount();
                String personeServerAuthCode = acct.getServerAuthCode();
                Set<Scope> personeRequestedScopes = acct.getRequestedScopes();
                Set<Scope> personeGrantedScopes = acct.getGrantedScopes();
                mBuffer.user_imgUrl = personePhoto;
                mBuffer.user_name = personeDisplayName;
                insertString(Constant.LOGIN_EMAIL, personEmail);
                insertString(Constant.LOGIN_NAME, personeDisplayName);
                insertString(Constant.LOGIN_PHOTO, String.valueOf(personePhoto));

                UserSignup();
            }

//            startActivity(new Intent(Activity_Sec.this, Landing_three.class));
//            finish();
            Fragment fragment = new LandingFragment();
            loadFragment(fragment);

            insertString(Constant.LOGINTYPE, "GOOGLE");
            mBuffer.Access_Type = "GOOGLE";
            insertBoolean(Constant.AFTERLANG, true);
        }catch (ApiException e){
            Log.e("Google sign in error", "SignIResult: failed code "+ e);
        }
    }

    private void UserSignup() {
        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest sr = new StringRequest(Request.Method.POST,Constant.URL+Constant.OAuth, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Gson gson = new Gson();
                oAuth_model = gson.fromJson(response, oAuth_Model.class);
                if (oAuth_model != null){
                    String token_type = oAuth_model.getToken_type();
                    String expires_in = oAuth_model.getExpires_in();
                    String access_token = oAuth_model.getAccess_token();
                    String refresh_token = oAuth_model.getRefresh_token();
                    mBuffer.oAuth_token = access_token;
                    mBuffer.token_type = token_type;
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("grant_type","password");
                params.put("client_id","6");
                params.put("client_secret", "BQ8GZifIDRI9b6LR7PAhhO2nddeqOdEk8WFZLy6c");
                params.put("username","app@user.com");
                params.put("password","aRURx{3Yx^^r");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);
    }

    public synchronized void insertBoolean(String key, boolean value) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putBoolean(key, value);
        mEditor.apply();
    }

    public static class ListUtils {
        public static void setDynamicHeight(ListView mListView) {
            ListAdapter mListAdapter = mListView.getAdapter();
            if (mListAdapter == null) {
                return;
            }
            int height = 0;
            int desiredWidth = View.MeasureSpec.makeMeasureSpec(mListView.getWidth(), View.MeasureSpec.UNSPECIFIED);
            for (int i = 0; i < mListAdapter.getCount(); i++) {
                View listItem = mListAdapter.getView(i, null, mListView);
                listItem.measure(desiredWidth, View.MeasureSpec.UNSPECIFIED);
                height += listItem.getMeasuredHeight();
            }
            ViewGroup.LayoutParams params = mListView.getLayoutParams();
            params.height = height + (mListView.getDividerHeight() * (mListAdapter.getCount() - 1));
            mListView.setLayoutParams(params);
            mListView.requestLayout();
        }
    }

    public synchronized void insertString(String key, String value) {
        SharedPreferences mSharedPreferences = getSharedPreferences(Constant.PREF, MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSharedPreferences.edit();
        mEditor.putString(key, value);
        mEditor.apply();
    }

    private class GeocoderHandler extends Handler {
        @Override
        public void handleMessage(Message message) {
            String locationAddress;
            switch (message.what) {
                case 1:
                    Bundle bundle = message.getData();
                    locationAddress = bundle.getString("address");
                    break;
                default:
                    locationAddress = null;
            }
            String[] location = locationAddress.split(",");
            mBuffer.map_lat = location[0];
            mBuffer.map_long = location[1];
            insertString("MAPLAT", location[0]);
            insertString("MAPLONG", location[1]);

        }
    }
    private boolean loadFragment(Fragment fragment){

        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_sec, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}