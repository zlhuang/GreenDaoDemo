package com.huang.greendaodemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.huang.greendaodemo.adapter.MyAdapter;
import com.huang.greendaodemo.base.BaseApplication;
import com.huang.greendaodemo.bean.User;
import com.huang.greendaodemo.gen.DaoSession;
import com.huang.greendaodemo.gen.UserDao;

import org.greenrobot.greendao.query.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private DaoSession mDaoSession;
    private UserDao mUserDao;

    private EditText name, text, id;
    private Button btnAdd, btnDelete, btnUpdate;
    private ListView mListView;

    private List<User> mUsersList = new ArrayList<>();
//    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        mDaoSession = BaseApplication.getInstances().getDaoSession();
        mUserDao = mDaoSession.getUserDao();

        initData();


    }

    private void initView() {
        name = (EditText) findViewById(R.id.name);
        text = (EditText) findViewById(R.id.text);
        id = (EditText) findViewById(R.id.id);
        btnAdd = (Button) findViewById(R.id.add);//增
        btnDelete = (Button) findViewById(R.id.delete);//删除
        btnUpdate = (Button) findViewById(R.id.update);//改
        mListView = (ListView) findViewById(R.id.listview);

        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    /**
     * 刷新显示数据
     */
    private void initData() {
        QueryBuilder<User> qb = mUserDao.queryBuilder();
        mUsersList = qb.list();

        MyAdapter myAdapter = new MyAdapter(this, mUsersList);
        mListView.setAdapter(myAdapter);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add://添加

                String mName = name.getText().toString().trim();
                String mText = text.getText().toString().trim();
                //判断输入不能为空
                if (mName.length() > 0 && mText.length() > 0) {

                    //添加到数据库
                    User mUser = new User();
                    mUser.setName(mName);
                    mUser.setText(mText);
                    mUserDao.insert(mUser);

                    //刷新数据
                    initData();
                } else {
                    Toast.makeText(this, "姓名或内容输入不能为空", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.delete://删

                String deleteId = id.getText().toString().trim();
                if (deleteId.length() > 0) {

                    Long mLong=Long.parseLong(deleteId);
                    mUserDao.deleteByKey(mLong);
                    //也可以根据某个bean删除
//                    QueryBuilder<User> qb = mUserDao.queryBuilder();
//                    User bean = qb.where(UserDao.Properties.Id.eq(deleteId)).build().unique();
//                    if (bean != null) {
//                        mUserDao.delete(bean);
//                        //刷新数据
//                        initData();
//                    }

                    initData();

                } else {
                    Toast.makeText(this, "id输入不能为空", Toast.LENGTH_SHORT).show();

                }

                break;
            case R.id.update://改
                String chamgeId = id.getText().toString().trim();
                String chamgeName = name.getText().toString().trim();
                String chamgeText = text.getText().toString().trim();
                if (chamgeId.length() > 0) {

                    User user =new User(26l,"2666" +
                            "","266");
                    mUserDao.update(user);
                    initData();

//                    QueryBuilder<User> qb = mUserDao.queryBuilder();
//                    //找到相关id
//                    User bean = qb.where(UserDao.Properties.Id.eq(chamgeId)).build().unique();
//                    if (bean != null) {
//
//                        if (chamgeName.length() > 0 && chamgeText.length() > 0) {
//                            bean.setName(chamgeName);
//                            bean.setText(chamgeText);
//                            mUserDao.update(bean);
//                            //刷新数据
//                            initData();
//                        } else {
//                            Toast.makeText(this, "姓名或内容输入不能为空", Toast.LENGTH_SHORT).show();
//                        }
//
//                    }

                } else {
                    Toast.makeText(this, "id输入不能为空", Toast.LENGTH_SHORT).show();

                }
                break;
        }
    }
}
