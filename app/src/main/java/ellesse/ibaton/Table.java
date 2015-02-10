package ellesse.ibaton;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by lsdejardin on 06/02/15.
 */
public class Table implements Serializable {

    private final static String TAG="Table";
    private Date mCreationDate;
    private String mLocation="SG";
    private ArrayList<Player> mPlayerList = new ArrayList<Player>();

    public Table(){
        mCreationDate= new Date();
    }

    public int getPlayerCount(){
        return mPlayerList.size();
    }

    public void addPlayer(String name){
        Player NewPlayer=new Player(name);
        mPlayerList.add(NewPlayer);
    }

    public String getTableID(){
        return mCreationDate.toString();
    }

    @Override
    public String toString() {
        return this.mLocation+ " created on " + this.mCreationDate.toString();
    }



}
