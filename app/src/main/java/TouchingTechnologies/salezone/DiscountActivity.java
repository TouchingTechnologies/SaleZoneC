package TouchingTechnologies.salezone;

import java.util.List;

import com.orm.query.Select;

import dbcontent.DiscountDB;
import adapters.DiscountListAdapter;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

public class DiscountActivity extends Activity {
	ListView discout_list;
	DiscountListAdapter adapter;
	DiscountDB db;
	AlertDialog alertDialog ;
	Context context;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.discounts);
		discout_list = (ListView) findViewById(R.id.discout_list);
		context = this;
		List<DiscountDB> alldiscount = DiscountDB.listAll(DiscountDB.class);
		adapter = new DiscountListAdapter(this, alldiscount);
		discout_list.setAdapter(adapter);
		discout_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				DiscountDB disc = (DiscountDB) adapter.getItem(position);
				String code = disc.getUnqcode().toString();
				Intent qrcode = new Intent(DiscountActivity.this, GenerateQRCodeActivity.class);
				qrcode.putExtra("code", code);
				startActivity(qrcode);
				finish();
				
				/*LayoutInflater li = LayoutInflater.from(context);
				View promptsView;
			
				
				 promptsView = li.inflate(R.layout.showcode, null);
				 

				 Button confirm = (Button) promptsView.findViewById(R.id.confirm);
				confirm.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						alertDialog.dismiss();
						
					}
				});
				 AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
					alertDialogBuilder.setView(promptsView);
					alertDialog = alertDialogBuilder.create();
					alertDialog.show();*/
			}
		});
			
			
	
		}
	
	}
