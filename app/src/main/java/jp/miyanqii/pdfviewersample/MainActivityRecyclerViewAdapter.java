package jp.miyanqii.pdfviewersample;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 *
 * Created by miyaki on 16/09/19.
 */
public class MainActivityRecyclerViewAdapter extends RecyclerView.Adapter {

    private ArrayList<PdfFile> mPdfFiles;
    private MainActivityRecyclerViewInteractionListener mListener;

    MainActivityRecyclerViewAdapter(ArrayList<PdfFile> pdfFiles, MainActivityRecyclerViewInteractionListener listener) {
        mPdfFiles = pdfFiles;
        mListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_main, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        final PdfFile pdfFile = mPdfFiles.get(position);

        ViewHolder myHolder = (ViewHolder) holder;

        myHolder.mBaseLinearLayout.setTag(pdfFile);
        myHolder.mTitleTextView.setText(pdfFile.getTitle());
        myHolder.mSubTitleTextView.setText(pdfFile.getSubTitle());

        myHolder.mBaseLinearLayout.setOnClickListener(new View.OnClickListener() {
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

    interface MainActivityRecyclerViewInteractionListener {
        void onSeeDetail(PdfFile pdfFile);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTitleTextView;
        TextView mSubTitleTextView;
        CardView mCardView;
        LinearLayout mBaseLinearLayout;

        ViewHolder(View itemView) {
            super(itemView);

            mBaseLinearLayout = (LinearLayout) itemView.findViewById(R.id.base_linear);
//            mCardView = (CardView) itemView.findViewById(R.id.cardview);
            mTitleTextView = (TextView) itemView.findViewById(R.id.pdf_title);
            mSubTitleTextView = (TextView) itemView.findViewById(R.id.pdf_subtitle);
        }
    }

}
