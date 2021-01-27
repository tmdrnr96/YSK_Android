package com.ysk.ex_0126;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.List;

public class PermissionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);

        //전화걸기 권한의 수락 여부 확인
        // (PackageManager.PERMISSION_GRANTED :수락이 되어 있는 상태, PackageManager.PERMISSION_DENIED 수락이 안된 상태 )
        if(ActivityCompat.checkSelfPermission(
                        PermissionActivity.this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            setPermission();
            return;
        }

        //주소록 접근 권한의 수락 여부 확인
        // (PackageManager.PERMISSION_GRANTED :수락이 되어 있는 상태, PackageManager.PERMISSION_DENIED 수락이 안된 상태 )
        if(ActivityCompat.checkSelfPermission(
                PermissionActivity.this, Manifest.permission.READ_CONTACTS)!= PackageManager.PERMISSION_GRANTED){
            setPermission();
            return;
        }
    }//onCreate()

    //권한체크
    public void setPermission(){

        TedPermission.with(PermissionActivity.this).
                            setPermissionListener(permissionListener).
                            setDeniedMessage("수락하지 않은 권한이 있습니다.").
                            setPermissions(Manifest.permission.CALL_PHONE,
                                           Manifest.permission.READ_CONTACTS).check();

    }

    //권한 설정 감지자
    //라이브러리가 추가되어 있어야 감지되는 클래스
    PermissionListener permissionListener = new PermissionListener() {

        @Override
        public void onPermissionGranted() {
            //앱에서 사용할 모든 권한이 수락되었을 경우 호출되는 메서드
            Toast.makeText(PermissionActivity.this,"모든 권한이 수락됨",Toast.LENGTH_SHORT).show();

            //권한을 모두 수락했다면 다시 PermissionActivity호출한다.
            Intent i = new Intent(PermissionActivity.this,PermissionActivity.class);
            startActivity(i);
            finish();
        }

        @Override
        public void onPermissionDenied(List<String> deniedPermissions) {
            //앱에서 사용할 권한중 한가지라도 수락되지 않은 것이 있을 떄 호출되는 메서드
            finish();//권한이 모두 수락하지 않았으므로 앱을 강제종료!
        }
    };
}