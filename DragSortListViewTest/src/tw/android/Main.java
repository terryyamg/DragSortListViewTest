package tw.android;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.mobeta.android.dslv.DragSortListView;
import com.mobeta.android.dslv.DragSortListView.RemoveListener;

public class Main extends ListActivity {
	private ArrayAdapter<String> adapter;

	/*��Կﶵ*/
	private DragSortListView.DropListener onDrop = new DragSortListView.DropListener() {
		public void drop(int from, int to) {
			Log.i("from", from+"");
			Log.i("to", to+"");
			if (from != to) { //�ﶵ�����ʦ�m
				DragSortListView list = getListView();
				String item = adapter.getItem(from);
				adapter.remove(item); //������lA����m
				adapter.insert(item, to); //�NA�[�J��B����m
				list.moveCheckState(from, to);
			}
		}
	};

	/*�����ﶵ*/
	private RemoveListener onRemove = new DragSortListView.RemoveListener() {
		public void remove(int which) {
			Log.i("which", which+"");
			DragSortListView list = getListView();
			String item = adapter.getItem(which);
			adapter.remove(item); //�����襤��m
			list.removeCheckState(which);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		/*�إ߿��*/
		String[] array = getResources().getStringArray(R.array.mlb_team); //�bres/valuse/string.xml �إ߿��
		ArrayList<String> arrayList = new ArrayList<String>(
				Arrays.asList(array));

		adapter = new ArrayAdapter<String>(this, R.layout.list_item_checkable, //���layout�˦�
				R.id.text, arrayList);

		setListAdapter(adapter);

		DragSortListView list = getListView();
		list.setDropListener(onDrop);
		list.setRemoveListener(onRemove);
	}

	@Override
	public DragSortListView getListView() {
		return (DragSortListView) super.getListView();
	}

}
