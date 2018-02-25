package com.zinfosoftware.b2w.activities;

import android.Manifest;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.zinfosoftware.b2w.ProdAdapter;
import com.zinfosoftware.b2w.R;
import com.zinfosoftware.b2w.auxiliar.Logs;
import com.zinfosoftware.b2w.model.ConsultaProdutos;
import com.zinfosoftware.b2w.model.Produto;
import com.zinfosoftware.b2w.network.ConsultaServico;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "MainActivity";
    private final static int PERMISSAO = 120;
    //variaveis para os cards
    private RecyclerView recyclerView;
    private ProdAdapter adapter;
    private List<Produto> prodList;
    private boolean aguarde = false;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setLogo(R.drawable.americanas_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        //inicializa variaveis globais
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        prodList = new ArrayList<>();
        adapter = new ProdAdapter(this, prodList);

//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(adapter);

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        hendleSearch(getIntent());

        Logs.Info(TAG, "onCreate", this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        hendleSearch(intent);
    }

    public void hendleSearch(Intent intent) {
        if (Intent.ACTION_SEARCH.equalsIgnoreCase(intent.getAction())) {
            String q = intent.getStringExtra(SearchManager.QUERY);

            toolbar.setTitle(q);
            atualizar(q);
        }
        else {
            atualizar("boneco");
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
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);

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

        if (id == R.id.busca) {
            //startActivity(new Intent(this, ConfigActivity.class));
            return true;
        } else if (id == R.id.atualizar) {
            atualizar("boneco");
            //Toast.makeText(this, "Atualizando...", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void carregarLista(List<Produto> lista) {

        List<Produto> tmp = lista == null ? new ArrayList<Produto>() : lista;

        adapter = new ProdAdapter(this, tmp);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    public void atualizar(String text) {
        try {
            TarefaInBackground tarefa = new TarefaInBackground();
            tarefa.execute(text);
        } catch (Exception e) {
            Logs.Erro(TAG, "(Atualizar) " + e.getMessage(), this);
            Toast.makeText(this, "Erro: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

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
                                           String permissions[], int[] grantResults) {
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
                    InformarPermissaoNegada();
                }
            }
        }
    }

    public void InformarPermissaoNegada() {

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

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    public class TarefaInBackground extends AsyncTask<String, Void, List<Produto>> {
        //variaveis
        private ProgressDialog Dialog = new ProgressDialog(MainActivity.this);
        private String erro;

        @Override
        protected void onPreExecute() {
            try {
                Dialog.setMessage("Baixando Produtos...");
                Dialog.setCancelable(false);
                Dialog.show();
            } catch (Exception er) {

            }
            erro = "";
        }

        @Override
        protected List<Produto> doInBackground(String... params) {

            try {
                String busca = params[0];
                ConsultaServico consultaServico = new ConsultaServico(MainActivity.this);
                ConsultaProdutos ret = consultaServico.getProdutos(busca, 10);
                return ret.getProdutos();
            } catch (Exception er) {
                erro = "Erro ao receber dados!";
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Produto> lista) {

            try {
                Dialog.dismiss();
            } catch (Exception er) {
                Logs.Erro(TAG, er.getMessage(), MainActivity.this);
            }

            if (erro.equals("")) {
                if (lista == null || lista.size() < 1)
                    Toast.makeText(MainActivity.this, "Produto não localizado", Toast.LENGTH_LONG).show();
                carregarLista(lista);
            } else {
                Toast.makeText(MainActivity.this, erro, Toast.LENGTH_LONG).show();
                Logs.Erro(TAG, erro, MainActivity.this);
            }
        }

        @Override
        protected void onCancelled() {

            super.onCancelled();
        }
    }

    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

}
