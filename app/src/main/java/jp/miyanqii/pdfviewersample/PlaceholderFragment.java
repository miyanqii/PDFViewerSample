package jp.miyanqii.pdfviewersample;


import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "ARG_SECTION_NUMBER";
    private static final String ARG_PDF_FILE = "ARG_PDF_FILE";
    private PhotoViewAttacher mAttacher;


    public PlaceholderFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaceholderFragment newInstance(int sectionNumber, PdfFile pdfFile) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putParcelable(ARG_PDF_FILE, pdfFile);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_pdf_viewer, container, false);

        final int sectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        final PdfFile pdfFile = (PdfFile) getArguments().getParcelable(ARG_PDF_FILE);

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ParcelFileDescriptor fileDescriptor = null;
            PdfRenderer pdfRenderer = null;
            PdfRenderer.Page currentPage = null;
            try {
                fileDescriptor = getActivity().getAssets().openFd(pdfFile.getFileName()).getParcelFileDescriptor();
                pdfRenderer = new PdfRenderer(fileDescriptor);

                currentPage = pdfRenderer.openPage(sectionNumber);

                Bitmap bitmap = Bitmap.createBitmap(
                        currentPage.getWidth(),
                        currentPage.getHeight(),
                        Bitmap.Config.ARGB_8888);

                currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);

                ImageView imageView = (ImageView) rootView.findViewById(R.id.pdf_image_view);
                imageView.setImageBitmap(bitmap);
                mAttacher = new PhotoViewAttacher(imageView);

            } catch (Throwable t) {
                Snackbar.make(getActivity().findViewById(R.id.toolbar), t.toString(), Snackbar.LENGTH_LONG).show();
            } finally {
                if (currentPage != null) {
                    currentPage.close();
                }
                if (pdfRenderer != null) {
                    pdfRenderer.close();
                }
                try {
                    fileDescriptor.close();
                } catch (Throwable t) {
                    Snackbar.make(getActivity().findViewById(R.id.toolbar), t.toString(), Snackbar.LENGTH_LONG).show();
                }
            }
        }

        return rootView;
    }
}
