package curso.ufmg;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MeuApActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        //final EditText newApButton = (EditText) findViewById(R.id.newApButton);
        // final EditText searchApButton = (EditText) findViewById(R.id.searchApButton);
        // final EditText mapApButton = (EditText) findViewById(R.id.mapApButton);
        
        Button newApButton = (Button) findViewById(R.id.newApButton);
        newApButton.setOnClickListener(this);
        
        Button searchApButton = (Button) findViewById(R.id.searchApButton);
        searchApButton.setOnClickListener(this);
        
        Button mapApButton = (Button) findViewById(R.id.mapApButton);
        mapApButton.setOnClickListener(this);
   
    }

	//@Override
	public void onClick(View v) {
		Intent i;
		switch (v.getId()) {
		case R.id.newApButton: 
    		i = new Intent(this, InsertActivity.class);
    		startActivity(i);
    		break;
		case R.id.searchApButton: 
    		i = new Intent(this, InsertActivity.class);
    		startActivity(i);
    		break;
		case R.id.mapApButton: 
    		i = new Intent(this, InsertActivity.class);
    		startActivity(i);
    		break;
    	}
	}
}