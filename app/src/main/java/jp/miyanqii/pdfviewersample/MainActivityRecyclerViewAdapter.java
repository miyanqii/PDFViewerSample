package jp.miyanqii.pdfviewersample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by miyaki on 16/09/19.
 */
public class MainActivityRecyclerViewAdapter extends RecyclerView.Adapter {

    ArrayList<PdfFile> mPdfFiles;
    MainActivityRecyclerViewInteractionListener mListener;

    public MainActivityRecyclerViewAdapter(ArrayList<PdfFile> pdfFiles, MainActivityRecyclerViewInteractionListener listener) {
        mPdfFiles = pdfFiles;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final PdfFile pdfFile = mPdfFiles.get(position);

        ViewHolder myHolder = (ViewHolder) holder;

        myHolder.mTitleTextView.setText(pdfFile.getTitle());
        myHolder.mTitleTextView.setTag(pdfFile);

        myHolder.mTitleTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onSeeDetail((PdfFile) view.getTag());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return mPdfFiles.size();
    }

    public interface MainActivityRecyclerViewInteractionListener {
        void onSeeDetail(PdfFile pdfFile);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            mTitleTextView = (TextView) itemView.findViewById(R.id.pdf_title);
        }
    }

}
