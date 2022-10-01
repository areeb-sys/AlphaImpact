package wasif.fyp.smartrestaurant;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {
    private EditText UserEmail, UserPassword;
    private Button buttonSignin;
    private TextView buttonRegister;
    public static final String PREFERENCE = "preference";
    public static final String PREF_USERIMAGE = "img";
    public static final String PREF_CITY = "name";
    public static final String PREF_AGE = "HEYLO";
    public static final String PREF_GENDER = "HEYLO";
    public static final String PREF_ROLE = " ";
    public static final String PREF_SKIP_LOGIN = "skip_login";
    public static final String PREF_TYPE = "1";
    public static final String PREF_STATUS_CHECK = "locked";
    public static final String PREF_USERID = "";
    public static final String PREF_TOKEN = "token";
    public SharedPreferences mSharedPreferences;
    private LinearLayout ButtonFacebookSignin;
    // ...
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        buttonSignin = findViewById(R.id.sign_in);
        buttonRegister = findViewById(R.id.register);
        ButtonFacebookSignin = findViewById(R.id.buttonFacebookSignin);
        mSharedPreferences = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);

        if (mSharedPreferences.contains(PREF_SKIP_LOGIN)) {
            Intent i = new Intent(SignInActivity.this, MainActivity.class);
            startActivity(i);
            finish();
        } else {
            buttonSignin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(SignInActivity.this, MainActivity.class);
                    SharedPreferences.Editor mEditor = mSharedPreferences.edit();
                    mEditor.putString(PREF_SKIP_LOGIN, "skip");
                    mEditor.commit();
                    startActivity(i);
                    finish();
                }
            });
            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(SignInActivity.this, RegisterActivity.class);
                    startActivity(i);

                }
            });
        }

    }
}
