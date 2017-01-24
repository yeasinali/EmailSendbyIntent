package dataputextra.nayan.com.emailsendbyintent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText to,subject,message;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Allfind();
    }

    private void Allfind() {
        to = (EditText)findViewById(R.id.et_to);
        subject = (EditText)findViewById(R.id.et_subject);
        message = (EditText)findViewById(R.id.et_message);
        send = (Button)findViewById(R.id.btn_send);
        String email_to = to.getText().toString().trim();


            send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String email_to = to.getText().toString().trim();
        Email_valid(email_to);
        String email_subject = subject.getText().toString().trim();
        String email_message = message.getText().toString().trim();
        if (isValidEmail(email_to)) {
            Intent email = new Intent(Intent.ACTION_SEND);
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{email_to});
            email.putExtra(Intent.EXTRA_SUBJECT, email_subject);
            email.putExtra(Intent.EXTRA_TEXT, email_message);
            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email, "Send Email Via"));

        }



    }
    private void Email_valid(String email){
        if (!isValidEmail(email)) {
            to.setError("Invalid Email");
        }

    }
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
