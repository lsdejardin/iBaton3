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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class iBaton extends ActionBarActivity {

    final static String TAG="iBaton";
    final static private String RECORDS_FILENAME = "myTables.txt";
    public static final String EXTRA="NEWTABLE";
    Button mCreateTableButton;
    ListView mTableView;
    ArrayList<Table> mTableList = new ArrayList<Table>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_i_baton);

        //mTableList.add(new Table());
        //mTableList.add(new Table());
        //mTableList.add(new Table());

            this.readRecordsFromFile();


        if (getIntent().getSerializableExtra(EXTRA) != null) {
            mTableList.add((Table) getIntent().getSerializableExtra(EXTRA));
        }
        //mTableList.add(new Table());
        mTableList.add(new Table());
        mTableList.add(new Table());
        this.writeRecordsToFile(mTableList);
        this.readRecordsFromFile();



        mCreateTableButton=(Button) findViewById(R.id.CreateTableButton);
        mCreateTableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(iBaton.this,CreateTable.class);

                startActivity(i);
            }
        });
        mTableView=(ListView) findViewById(R.id.ListTable);
        if (mTableList.size()>0) {
            ArrayAdapter<Table> adapter=new ArrayAdapter<Table>(iBaton.this,android.R.layout.simple_list_item_1,mTableList);
            mTableView.setAdapter(adapter);
        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_i_baton, menu);
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

    public boolean writeRecordsToFile(ArrayList<Table> records){
        FileOutputStream fos;
        ObjectOutputStream oos=null;
        try{
            fos = getApplicationContext().openFileOutput(RECORDS_FILENAME, Context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(records);
            oos.close();
            Log.d(TAG,"Write to file");
            return true;
        }catch(Exception e){
            //Log.e(getClassName(), "Cant save records"+e.getMessage());
            Log.d(TAG,"Error writing file");
            return false;
        }
        finally{
            if(oos!=null)
                try{
                    oos.close();
                }catch(Exception e){
                    //Log.e(getClassName(), "Error while closing stream "+e.getMessage());
                    Log.d(TAG,"Error writing file");
                }
        }
    }

    private boolean readRecordsFromFile(){
        FileInputStream fin;
        ObjectInputStream ois=null;
        try{
            fin = getApplicationContext().openFileInput(RECORDS_FILENAME);
            ois = new ObjectInputStream(fin);
            mTableList = (ArrayList<Table>) ois.readObject();
            ois.close();
            Log.d(TAG, "Records read successfully");
            return true;
        }catch(Exception e){
            Log.d(TAG, "Cant read saved records"+e.getMessage());
            return false;
        }
        finally{
            if(ois!=null)
                try{
                    ois.close();
                }catch(Exception e){
                    Log.d(TAG, "Error in closing stream while reading records"+e.getMessage());
                }
        }
    }
}

