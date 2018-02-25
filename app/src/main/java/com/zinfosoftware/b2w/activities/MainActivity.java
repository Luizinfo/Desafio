package com.zinfosoftware.b2w.activities;

import android.Manifest;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.zinfosoftware.b2w.ProdAdapter;
import com.zinfosoftware.b2w.R;
import com.zinfosoftware.b2w.auxiliar.GridSpacingItemDecoration;
import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.model.Produto;
import com.zinfosoftware.b2w.mvp.MVP;
import com.zinfosoftware.b2w.mvp.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MVP.IView {
    //variaveis de controle
    public static final String TAG = "MainActivity";
    private final static int PERMISSAO = 120;
    //mvp
    private static MVP.IPresenter presenter;
    //variaveis globais
    private RecyclerView recyclerView;
    private ProdAdapter adapter;
    private boolean aguarde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.americanas_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //mvp
        if (presenter == null) {
            presenter = new Presenter();
        }
        presenter.setView(this);
        //orientação da tela somente na vertical
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        //utilizado na pesquisa
        hendleSearch(getIntent());

        Logs.Info(TAG, "onCreate", this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        //inicializando o recyclerView
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        adapter = new ProdAdapter(this, new ArrayList<Produto>());
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        //RecyclerView item decoration - give equal margin around grid item
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, presenter.dpToPx(10), true));// dpToPx => Converter dp em pixels
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        hendleSearch(intent);
    }

    public void hendleSearch(Intent intent) {
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())) {
            String q = intent.getStringExtra(SearchManager.QUERY);

            //toolbar.setTitle(q);
            presenter.atualizar(q);
        } else {
            presenter.atualizar("boneco");
        }
    }

    @Override
    protected void onDestroy() {
        Logs.Info(TAG, "onDestroy", this);
        super.onDestroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //adiciona itens do menu
        getMenuInflater().inflate(R.menu.menu_principal, menu);

        //utilizado para pesquisa no toolbar
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView;
        MenuItem item = menu.findItem(R.id.busca);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            searchView = (SearchView) item.getActionView();
        } else {
            searchView = (SearchView) MenuItemCompat.getActionView(item);
        }

        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint(getResources().getString(R.string.search_hint));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        return id == R.id.busca || super.onOptionsItemSelected(item);
    }

    public void carregarLista(List<Produto> lista) {

        List<Produto> tmp = lista == null ? new ArrayList<Produto>() : lista;
        adapter = new ProdAdapter(this, tmp);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    //Utilizado para verificar permissão
    private boolean VerificaPermissao() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                || ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_PHONE_STATE},
                    PERMISSAO);

            Logs.Erro(TAG, "WRITE_EXTERNAL_STORAGE sem Permissão!", this);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSAO: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED) {

                    Logs.Info(TAG, "Permissão de WRITE_EXTERNAL_STORAGE e READ_PHONE_STATE liberadas!", this);

                } else {
                    Logs.Erro(TAG, "Permissão de WRITE_EXTERNAL_STORAGE ou READ_PHONE_STATE negada!", this);
                    informarPermissaoNegada();
                }
            }
        }
    }

    //notificar caso a permissão seja obrigatória
    public void informarPermissaoNegada() {

        aguarde = true;

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(this).setTitle("Você negou uma permissão necessária!");
        builder.setMessage("A aplicação não pode ser iniciada até a permissão ser concedida.\nDeseja dar permissão agora?")
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        Uri uri = Uri.fromParts("package", getPackageName(), null);
                        intent.setData(uri);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                        startActivity(intent);
                        MainActivity.this.finish();
                    }
                })
                .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                }).show();

    }

    @Override
    public void showToast(String mensagem) {
        Toast.makeText(this, mensagem, Toast.LENGTH_LONG).show();
    }

}
