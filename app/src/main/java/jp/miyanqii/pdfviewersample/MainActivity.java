package jp.miyanqii.pdfviewersample;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import de.psdev.licensesdialog.LicensesDialog;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_SELECTED_PDF = "EXTRA_SELECTED_PDF";
    private RecyclerView.Adapter mRecyclerViewAdapter;
    private RecyclerView mRecyclerView;

    private ArrayList<PdfFile> mPdfFiles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mPdfFiles = new ArrayList<>();
        mPdfFiles.add(new PdfFile("kawaraban_201611.pdf", "サンプル1", "サブタイトル1"));
        mPdfFiles.add(new PdfFile("sample.pdf", "サンプル2", "サブタイトル2"));
        mPdfFiles.add(new PdfFile("sample.pdf", "サンプル3", "サブタイトル3"));

        mRecyclerView = (RecyclerView) findViewById(R.id.main_recycler_view);
        mRecyclerViewAdapter = new MainActivityRecyclerViewAdapter(mPdfFiles, new MainActivityRecyclerViewAdapter.MainActivityRecyclerViewInteractionListener() {
            @Override
            public void onSeeDetail(PdfFile pdfFile) {

                Intent intent = new Intent();
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra(EXTRA_SELECTED_PDF, pdfFile);
                intent.setClass(MainActivity.this, PdfActivity.class);
                startActivity(intent);

            }
        });
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about_this_app) {
            new AlertDialog.Builder(this).setTitle(getString(R.string.app_name)).setMessage(" Version " + BuildConfig.VERSION_NAME)
                    .setCancelable(true)
                    .setOnCancelListener(new DialogInterface.OnCancelListener() {
                        @Override
                        public void onCancel(DialogInterface dialogInterface) {
                            dialogInterface.dismiss();
                        }
                    }).setNeutralButton("Close", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            }).create().show();
        }
        if (id == R.id.action_licenses) {
            new LicensesDialog.Builder(this)
                    .setNotices(R.raw.notices)
                    .setIncludeOwnLicense(true)
                    .build()
                    .show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
