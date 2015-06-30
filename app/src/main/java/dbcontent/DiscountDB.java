package dbcontent;

import android.content.Context;

import com.orm.SugarRecord;

public class DiscountDB extends SugarRecord<DiscountDB> {
	public String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUnqcode() {
		return unqcode;
	}

	public void setUnqcode(String unqcode) {
		this.unqcode = unqcode;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String description;
	public String unqcode;
	public String date;

	public DiscountDB(Context context) {
		super(context);

	}
	public DiscountDB (Context ctx,String titles, String desc, String unqicode,String date){
		super(ctx);
		this.title= titles;
		this.description=desc;
		this.date= date;
		this.unqcode= unqicode;
	}
	

}
