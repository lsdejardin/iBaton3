package ellesse.ibaton;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class CreateTable extends ActionBarActivity {


    final static String TAG="CreateTable";
    final static int ToastDuration = Toast.LENGTH_SHORT;
    Button mSave;
    Button mClear;
    Button mCreate;

    EditText mAdd_user;
    ListView mUserListView;
    Table mTable;

    ArrayList<String> mPlayerList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_table);


        mAdd_user=(EditText) findViewById(R.id.add_user);
        mUserListView=(ListView) findViewById(R.id.userListView);
        mSave=(Button) findViewById(R.id.save);
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String getInput = mAdd_user.getText().toString().trim();

                if(mPlayerList.contains(getInput)){
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.toast_user_exists),ToastDuration).show();
                }
                else if (getInput == null || getInput.trim().equals("") ){
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.toast_user_empty),ToastDuration).show();
                }
                else{
                    mPlayerList.add(getInput);
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(CreateTable.this,android.R.layout.simple_list_item_1,mPlayerList);
                    mUserListView.setAdapter(adapter);
                    ((EditText) findViewById(R.id.add_user)).setText("");
                }


                Log.d(TAG,"Create Table");



            }
        });

        mClear=(Button) findViewById(R.id.clear);
        mClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlayerList.clear();
                ArrayAdapter<String> adapter=new ArrayAdapter<String>(CreateTable.this,android.R.layout.simple_list_item_1,mPlayerList);
                mUserListView.setAdapter(adapter);
                ((EditText) findViewById(R.id.add_user)).setText("");
            }
        });

        mCreate=(Button) findViewById(R.id.create);
        mCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTable = new Table();
                if (mPlayerList.isEmpty()){
                    Toast.makeText(getBaseContext(),getResources().getString(R.string.toast_table_empty),ToastDuration).show();
                }
                else{
                    for (int i = 0; i < mPlayerList.size(); i++) {
                        mTable.addPlayer(mPlayerList.get(i));
                    }
                    Intent intent = new Intent(CreateTable.this,iBaton.class);
                    intent.putExtra(iBaton.EXTRA,mTable);
                    startActivity(intent);
                }




            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_table, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
