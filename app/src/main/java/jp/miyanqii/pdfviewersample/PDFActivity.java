package jp.miyanqii.pdfviewersample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.github.barteksc.pdfviewer.PDFView;
import com.github.barteksc.pdfviewer.listener.OnErrorListener;
import com.github.barteksc.pdfviewer.listener.OnPageChangeListener;

public class PdfActivity extends AppCompatActivity {

    public static final String INSTANCESTATE_PDFFILE = "INSTANCESTATE_PDFFILE";
    private static final String INSTANCESTATE_CURRENT_PAGE = "INSTANCESTATE_CURRENT_PAGE";
    private PdfFile mPdfFile;
    private PDFView mPdfView;
    private int mCurrentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent != null) {
            mPdfFile = (PdfFile) intent.getParcelableExtra(MainActivity.EXTRA_SELECTED_PDF);
            getSupportActionBar().setTitle(mPdfFile.getTitle());
//            getSupportActionBar().setSubtitle(mPdfFile.getSubTitle());

        }
        if (savedInstanceState != null) {
            mPdfFile = savedInstanceState.getParcelable(INSTANCESTATE_PDFFILE);
            mCurrentPage = savedInstanceState.getInt(INSTANCESTATE_CURRENT_PAGE);
        }
        Log.d(this.getClass().getName(), "onCreate mPdfFile: " + mPdfFile + " mCurrentPage: " + mCurrentPage);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(this.getClass().getName(), "onResume mPdfFile: " + mPdfFile + " mCurrentPage: " + mCurrentPage);

        if (mPdfFile != null) {
            mPdfView = (PDFView) findViewById(R.id.pdfView);

            mPdfView.recycle();
            mPdfView.fromAsset(mPdfFile.getFileName())
//                .pages(0, 2, 1, 3, 3, 3) // all pages are displayed by default
                    .enableSwipe(true)
                    .swipeHorizontal(false)
                    .enableDoubletap(true)
                    .defaultPage(mCurrentPage)
//                .onDraw(onDrawListener)
//                .onLoad(onLoadCompleteListener)
                    .onPageChange(new OnPageChangeListener() {
                        @Override
                        public void onPageChanged(int page, int pageCount) {
                            Log.d(this.getClass().getName(), "onPageChanged page: " + page);
                            mCurrentPage = page;
                        }
                    })
//                .onPageScroll(onPageScrollListener)
                    .onError(new OnErrorListener() {
                        @Override
                        public void onError(Throwable t) {
                            Log.d(this.getClass().getName(), t.getMessage());
                        }
                    })
                    .enableAnnotationRendering(false)
                    .password(null)
                    .scrollHandle(null)
                    .load();
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mPdfFile = savedInstanceState.getParcelable(INSTANCESTATE_PDFFILE);
        mCurrentPage = savedInstanceState.getInt(INSTANCESTATE_CURRENT_PAGE);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(this.getClass().getName(), "onSaveInstanceState mPdfFile: " + mPdfFile + " mCurrentPage: " + mCurrentPage);
        outState.putParcelable(INSTANCESTATE_PDFFILE, mPdfFile);
        outState.putInt(INSTANCESTATE_CURRENT_PAGE, mCurrentPage);
    }

}
