package ellesse.ibaton;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by lsdejardin on 06/02/15.
 */
public class Player implements Serializable{

    private final static String TAG="Player";
    private String mName;
    private int mBatonCount;

    public Player(String Name){
        mName=Name;
        Log.d(TAG, "User " + mName + " created.");
        mBatonCount=1;
    }

    public int addBaton(){
        mBatonCount++;
        return mBatonCount;
    }

    public int remBaton(){
        mBatonCount--;
        return mBatonCount;
    }

    public int getBatonCount(){
        return mBatonCount;
    }

    public String getName(){
        return mName;
    }


}
