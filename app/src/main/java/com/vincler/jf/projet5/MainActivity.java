package com.vincler.jf.projet5;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.vincler.jf.projet5.data.NewsService;
import com.vincler.jf.projet5.models.ArticleListType;
import com.vincler.jf.projet5.models.ArticlesSearchResponse;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        configureToolbar();
        configureViewPagerAndTabs();
        configureDrawerLayout();
        configureNavigationView();
    }

    @Override

    public void onBackPressed() {

        if (this.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            this.drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            super.onBackPressed();
        }
    }


    public boolean onNavigationItemSelected(MenuItem item) {
        String category = "";
        int id = item.getItemId();
        switch (id) {
            case R.id.activity_main_drawer_1:
                category = "art";
                break;
            case R.id.activity_main_drawer_2:
                category = "business";
                break;
            case R.id.activity_main_drawer_3:
                category = "entrepreneurs";
                break;
            case R.id.activity_main_drawer_4:
                category = "politics";
                break;
            case R.id.activity_main_drawer_5:
                category = "sports";
                break;
            case R.id.activity_main_drawer_6:
                category = "travel";
                break;
            default:
                break;
        }
        if (!category.isEmpty()) {

            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            OkHttpClient.Builder builder = UnsafeOkHttpClient.getUnsafeOkHttpClient().addInterceptor(interceptor);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://api.nytimes.com/svc/")
                    .client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            final NewsService service = retrofit.create(NewsService.class);
            service.listCategorySearch(category).enqueue(new Callback<ArticlesSearchResponse>() {
                @Override
                public void onResponse(Call<ArticlesSearchResponse> call, Response<ArticlesSearchResponse> response) {


                    Intent intent = new Intent(MainActivity.this, ResultSearchActivity.class);
                    Bundle b = new Bundle();
                    b.putParcelableArrayList("data", (ArrayList<? extends Parcelable>) response.body().getResults());
                    intent.putExtras(b);
                    startActivity(intent);

                }

                @Override
                public void onFailure(Call<ArticlesSearchResponse> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
        this.drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_activity_main_search:
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
                return true;

            case R.id.menu_activity_main_notification:
                Intent intent2 = new Intent(MainActivity.this, NotificationsActivity.class);
                startActivity(intent2);
                return true;

            case R.id.menu_activity_main_help:
                Intent intent3 = new Intent(MainActivity.this, HelpActivity.class);
                startActivity(intent3);
                return true;

            case R.id.menu_activity_main_about:
                Intent intent4 = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent4);
                return true;



            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void configureToolbar() {
        this.toolbar = findViewById(R.id.activity_main_toolbar);
        setSupportActionBar(toolbar);
    }


    private void configureDrawerLayout() {
        this.drawerLayout = findViewById(R.id.activity_main_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


    }

    private void configureNavigationView() {
        this.navigationView = findViewById(R.id.activity_main_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void configureViewPagerAndTabs() {
        ViewPager pager = findViewById(R.id.activity_main_viewpager);
        List<ArticleListType> listTypesArticles = new ArrayList<>();

        listTypesArticles.add(ArticleListType.TOP_STORIES);
        listTypesArticles.add(ArticleListType.MOST_POPULAR);
        listTypesArticles.add(ArticleListType.BUSINESS);

        pager.setAdapter(new PageAdapter(getSupportFragmentManager(), listTypesArticles));
        TabLayout tabs = findViewById(R.id.activity_main_tabs);
        tabs.setupWithViewPager(pager);
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

}