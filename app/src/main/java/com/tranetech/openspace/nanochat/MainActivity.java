package com.tranetech.openspace.nanochat;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.firebase.ui.FirebaseListAdapter;
import com.firebase.ui.auth.core.AuthProviderType;
import com.firebase.ui.auth.core.FirebaseLoginBaseActivity;
import com.firebase.ui.auth.core.FirebaseLoginError;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Firebase mFirebaseRef;
    FirebaseListAdapter<ChatMessage> mListAdapter;
    EditText textEdit;
    Button sendButton;
    ListView listView;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Firebase.setAndroidContext(this);
        setContentView(R.layout.activity_main);

        mFirebaseRef = new Firebase("https://amber-inferno-1525.firebaseio.com/");

        mFirebaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listView.setSelection(listView.getAdapter().getCount()-1);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        textEdit = (EditText) this.findViewById(R.id.text_edit);
        sendButton = (Button) this.findViewById(R.id.send_button);
        listView = (ListView) this.findViewById(android.R.id.list);

//        Bundle bundle = getIntent().getExtras();
//        String message = bundle.getString("message");
        final String Name = getIntent().getExtras().getString("Name");

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String text = textEdit.getText().toString();
                    ChatMessage message = new ChatMessage(""+Name, text);
                    mFirebaseRef.push().setValue(message);
                    textEdit.setText("");
                    listView.deferNotifyDataSetChanged();
                    listView.setAdapter(mListAdapter);
                    listView.setSelection(listView.getAdapter().getCount()+2);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        mListAdapter = new FirebaseListAdapter<ChatMessage>(this, ChatMessage.class,
                android.R.layout.two_line_list_item, mFirebaseRef) {
            @Override
            protected void populateView(View v, ChatMessage model, int position) {
                ((TextView)v.findViewById(android.R.id.text1)).setText(model.getName());
                ((TextView)v.findViewById(android.R.id.text2)).setText(model.getText());
            }
        };

        mListAdapter.notifyDataSetChanged();
        listView.setAdapter(mListAdapter);
        listView.setSelection(listView.getAdapter().getCount() - 1);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mListAdapter.cleanup();
    }

}
