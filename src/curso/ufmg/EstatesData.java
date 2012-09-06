package curso.ufmg;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import static android.provider.BaseColumns._ID;
import static curso.ufmg.Constants.TABLE_NAME;
import static curso.ufmg.Constants.TYPE;
import static curso.ufmg.Constants.STATUS;
import static curso.ufmg.Constants.SIZE;
import static curso.ufmg.Constants.PHONE;

public class EstatesData extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "curso_ufmg";
	private static final int DATABASE_VERSION = 1;
	
	public EstatesData (Context ctx){
		super(ctx, DATABASE_NAME,null,DATABASE_VERSION);
	}

	public void onCreate(final SQLiteDatabase db){
		db.execSQL("CREATE TABLE "+TABLE_NAME+" ("+_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
	    TYPE + " TEXT NOT NULL,"+
	    SIZE + " TEXT NOT NULL,"+
	    STATUS + " TEXT,"+
	    PHONE + " TEXT NOT NULL);");
	}
	
	public void onUpgrade(final SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXIST "+ TABLE_NAME );
		onCreate(db);
	}
	
	

}
