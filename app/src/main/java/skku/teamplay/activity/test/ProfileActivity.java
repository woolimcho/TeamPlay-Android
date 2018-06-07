package skku.teamplay.activity.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;
import skku.teamplay.R;
import skku.teamplay.adapter.TeamListAdapter;
import skku.teamplay.adapter.TeamListCardAdapter;
import skku.teamplay.api.OnRestApiListener;
import skku.teamplay.api.RestApiResult;
import skku.teamplay.api.RestApiTask;
import skku.teamplay.api.impl.GetAllUsersByTeam;
import skku.teamplay.api.impl.GetTeamByUser;
import skku.teamplay.api.impl.MakeTeam;
import skku.teamplay.api.impl.res.TeamListResult;
import skku.teamplay.api.impl.res.UserListResult;
import skku.teamplay.app.TeamPlayApp;
import skku.teamplay.model.Team;
import skku.teamplay.model.User;
import skku.teamplay.util.RecyclerItemClickListener;

/**
 * Created by 우림 on 2018-05-21.
 */

public class ProfileActivity extends AppCompatActivity implements OnRestApiListener {
    boolean MEMBER_LOAD_FLAG;
    TeamListCardAdapter teamAdapter;
    @BindView(R.id.layout_update_timetable)
    LinearLayout layout_update_timetable;

    @BindView(R.id.tv_profile_name)
    TextView tv_profile;

    @BindView(R.id.lv_team_list)
    ListView lv_teamList;

    @BindView(R.id.fab_main_profile)
    FabSpeedDial fab;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profile);
        ButterKnife.bind(this);

        layout_update_timetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ProfileActivity.this, EverytimeParseTestActivity.class));
            }
        });

        tv_profile.setText(TeamPlayApp.getAppInstance().getUser().getName());
        fab.setMenuListener(new SimpleMenuListenerAdapter(){
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                int temp = 0;
                switch (menuItem.getItemId()){
                    case R.id.menu1:
                        Intent intent = new Intent(ProfileActivity.this, MakeTeamActivity.class);
                        startActivity(intent);
                        temp = 1;
                        break;
                    case R.id.menu2:
                        temp = 2;
                        break;
                    case R.id.menu3:
                        temp = 3;
                        break;
                }
                Toast.makeText(getApplicationContext(), "Fab selected #" + temp, Toast.LENGTH_LONG).show();
                return false;
            }
        });
        new RestApiTask(this).execute(new GetTeamByUser(TeamPlayApp.getAppInstance().getUser().getId()));
    }

    @Override
    public void onRestApiDone(RestApiResult restApiResult) {
        int teamIdx = 0;
        switch (restApiResult.getApiName()) {
            case "getteambyuser":
                //해당 유저의 팀 가져옴
                TeamListResult result = (TeamListResult) restApiResult;
                final ArrayList<Team> teamList = result.getTeamList();
                teamAdapter = new TeamListCardAdapter(teamList);
                        lv_teamList.setAdapter(teamAdapter);
                        lv_teamList.hasFocus();
                        lv_teamList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Team team = teamList.get(i);
                                TeamPlayApp.getAppInstance().setTeam(team);
                                new RestApiTask(ProfileActivity.this).execute(new GetAllUsersByTeam(team.getId()));
                                MEMBER_LOAD_FLAG = false;
                    }
                });
                MEMBER_LOAD_FLAG = true;
                for (Team team : teamList){
                    new RestApiTask(ProfileActivity.this).execute(new GetAllUsersByTeam(team.getId()));
                }

                break;
            case "getallusersbyteam":
                UserListResult userListResult = (UserListResult) restApiResult;
                if (MEMBER_LOAD_FLAG == false) {
                    final ArrayList<User> userList = userListResult.getUserList();
                    TeamPlayApp.getAppInstance().setUserList(userList);
                    //탭 액티비티로 이동
                    startActivity(new Intent(ProfileActivity.this, TabTestActivity.class));
                }
                else{ //load team members to a grid view

                    ArrayList<User> tempList = userListResult.getUserList();
                    teamAdapter.setContributorTitle("참여자 - 총 " + tempList.size() + "명", teamIdx);
                    teamIdx++;
                }
                break;
        }
    }
}
