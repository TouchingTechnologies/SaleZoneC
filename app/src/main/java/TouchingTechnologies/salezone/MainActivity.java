package TouchingTechnologies.salezone;

import java.util.ArrayList;
import java.util.List;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import common_methods.Methods;
import dbcontent.DiscountDB;

import util.GsonRequest;
import util.Vars;
import adapters.FeedListAdapter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.os.Build;

public class MainActivity extends Activity {
	
	Vars vars;
    private ListView listView;
    private FeedListAdapter listAdapter;
    private List<FeedItem> feedItems;
    Methods methods;
    private String URL_FEED = "http://54.68.162.113/sugaapp/uploads/feedsalezone.json";
    private ProgressBar progressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_feed_list);
		
		progressBar = (ProgressBar) findViewById(R.id.progressBar1); 
		methods = new Methods();
		methods.log("Seen this:...... ");
		GsonRequest<FeedResult> gsonRequest = new GsonRequest<FeedResult>(URL_FEED, FeedResult.class,
                new Response.Listener<FeedResult>() {
                    @Override
                    public void onResponse(FeedResult response) {
                    	methods.log("STRING REQUEST:...... ");
                    	 if (response!=null){
                         	progressBar.setVisibility(View.GONE);
                         }
                        feedItems = response.getFeedItems();
                        listAdapter.setData(feedItems);
                        listAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    	methods.log("Error: "+error.getMessage());
                       
                    }
                });
     // Adding request to volley request queue
		   AppController.getInstance(this).addRequest(gsonRequest, "MainActivity");
        listView = (ListView) findViewById(R.id.feed_list);
        feedItems = new ArrayList<FeedItem>();
       
        listAdapter = new FeedListAdapter(this, feedItems);
        listView.setAdapter(listAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id){
		case R.id.list:
			List<DiscountDB> discoutlist = DiscountDB.listAll(DiscountDB.class);
			if (discoutlist.isEmpty()){
				Toast.makeText(getApplicationContext(), "You have no discounts saved", Toast.LENGTH_LONG).show();
				
			}else{
			Intent discouts = new Intent(this,DiscountActivity.class);
			startActivity(discouts);
			}
			break;
			
		}
		return super.onOptionsItemSelected(item);
	}
	

	


}
