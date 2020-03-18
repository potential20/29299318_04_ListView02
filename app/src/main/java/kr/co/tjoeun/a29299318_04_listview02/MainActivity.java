package kr.co.tjoeun.a29299318_04_listview02;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import kr.co.tjoeun.a29299318_04_listview02.adapters.RoomAdapter;
import kr.co.tjoeun.a29299318_04_listview02.databinding.ActivityMainBinding;
import kr.co.tjoeun.a29299318_04_listview02.datas.Room;

public class MainActivity extends BaseActivity {

    List<Room> roomDatas = new ArrayList<>();
    RoomAdapter roomAdapter = null;

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.roomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Log.i("리스트뷰 아이템 클릭",String.format("%d번 줄 클릭",position));

//                "클릭된 방"의 주소를 Toast로 출력

                Room clickedRoom = roomDatas.get(position);
                Toast.makeText(mContext, clickedRoom.getAddress(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public void setValues() {
        roomAdapter = new RoomAdapter(mContext,R.layout.room_list_item,roomDatas);
        binding.roomListView.setAdapter(roomAdapter);

        addRooms();

    }

    private void addRooms() {
        roomDatas.add(new Room(8000,"서울시 은평구",4,"살기 좋은 방입니다."));
        roomDatas.add(new Room(25000,"서울시 노원구",2,"노원구의 투룸"));
        roomDatas.add(new Room(18000,"서울시 종로구",1,"종로구의 투룸"));
        roomDatas.add(new Room(6000,"경기도 부천시",0,"부천시 원룸"));
        roomDatas.add(new Room(45000,"경기도 고양시",-1,"고양시 단독주택"));

        roomAdapter.notifyDataSetChanged();
    }


}
