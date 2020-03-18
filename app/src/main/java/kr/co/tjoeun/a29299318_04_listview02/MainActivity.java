package kr.co.tjoeun.a29299318_04_listview02;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
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

//                클릭 된 방의 정보를 목록에서 빼옴. position번째
                Room clickedRoom = roomDatas.get(position);

//                방 상세 화면으로 이동.
                Intent intent = new Intent(mContext,RoomDetailActivity.class);
                intent.putExtra("room",clickedRoom);
                startActivity(intent);


            }
        });

        binding.roomListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                //        꾹 누르고 있으면, 해당 방의 설명을 Toast로 출력.
//                Room data = roomDatas.get(position);
//                Toast.makeText(mContext, data.getDescription(), Toast.LENGTH_SHORT).show();

//                꾹 누르면, 해당 아이템을 목록에서 삭제.
//                지우기 전에, 정말 지울건지? 확인받자.

                AlertDialog.Builder alert = new AlertDialog.Builder(mContext);
                alert.setTitle("방 삭제 확인");
                alert.setMessage("정말 이 방을 삭제하시겠습니까?");
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//                        얼럿에서도 확인을 누른 경우.
                        roomDatas.remove(position);
//                어댑터에게 새로고침 시킴.
                        roomAdapter.notifyDataSetChanged();
                    }
                });
                   alert.setNegativeButton("취소", null);
                   alert.show();



                return false; //        true: 롱클릭만. false 그냥 클릭도 같이.
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
